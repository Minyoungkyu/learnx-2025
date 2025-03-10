<script lang="ts">
    import rq from "$lib/rq/rq.svelte";
    import { onMount, onDestroy } from 'svelte';
    import * as pdfjs from 'pdfjs-dist';
    import 'pdfjs-dist/build/pdf.worker.entry';

    const id = "PDFContentModal";
    const title = "PDF 콘텐츠 추가";

    let dialog: HTMLDialogElement;
    let fileInput: HTMLInputElement;

    // PDF 페이지 표시 및 선택 관련 상태
    let isFileSelected = $state(false);
    let pdfPages: string[] = $state([]);
    let selectedPages: number[] = $state([]);
    let isDragging = false;
    let dragStartIndex = -1;
    let initialSelectedPages: number[] = [];
    let dragRange: number[] = [];

    // 콘텐츠명 입력 및 생성 관련 상태
    let contentName = $state('');
    // 생성된 PDF 콘텐츠 리스트
    let createdContents: { name: string; pages: string[] }[] = $state([]);

    let isSuccess = $state(false);

    onMount(() => {
        // 마우스가 문서 밖에서 릴리즈되더라도 드래그가 종료되도록 window에 등록
        window.addEventListener('mouseup', handleDragEnd);
    });

    onDestroy(() => {
        window.removeEventListener('mouseup', handleDragEnd);
    });

    // PDF 파일 선택 및 렌더링
    async function handleFileChange(event: Event) {
        const file = (event.target as HTMLInputElement).files?.[0];
        if (!file) {
            isFileSelected = false;
            return;
        }

        isFileSelected = true;

        const fileReader = new FileReader();
        fileReader.onload = async function() {
            const typedarray = new Uint8Array(this.result as ArrayBuffer);
            const pdf = await pdfjs.getDocument(typedarray).promise;
            
            pdfPages = [];
            for (let i = 1; i <= pdf.numPages; i++) {
                const page = await pdf.getPage(i);
                const viewport = page.getViewport({ scale: 1.0 });
                const canvas = document.createElement('canvas');
                const context = canvas.getContext('2d');
                
                canvas.height = viewport.height;
                canvas.width = viewport.width;
                
                await page.render({
                    canvasContext: context!,
                    viewport: viewport
                }).promise;
                
                pdfPages = [...pdfPages, canvas.toDataURL()];
            }
        };
        fileReader.readAsArrayBuffer(file);
    }

    /**
     * 페이지 클릭 시:
     * 이미 선택된 페이지라면 해제,
     * 아니면 배열 끝에 추가.
     * (배열 순서를 정렬하지 않고 “선택 순서”를 그대로 유지)
     */
    function handlePageClick(index: number) {
        const alreadySelected = selectedPages.indexOf(index);
        if (alreadySelected !== -1) {
            // 이미 선택된 페이지이면 해제
            selectedPages = selectedPages.filter(p => p !== index);
        } else {
            // 아직 선택되지 않았다면 배열 끝에 추가
            selectedPages = [...selectedPages, index];
        }
    }

    /**
     * 드래그 시작:
     * - 현재까지 선택된 페이지(initialSelectedPages) 기록
     * - 드래그 범위(dragRange) 초기화
     */
    function handleDragStart(index: number, event: MouseEvent) {
        isDragging = true;
        dragStartIndex = index;
        initialSelectedPages = [...selectedPages];
        dragRange = [index];

        // 이미지 등의 기본 드래그 동작 방지
        event.preventDefault();
    }

    /**
     * 드래그 중:
     * - 드래그 시작 인덱스부터 현재 인덱스까지 범위를 구해 dragRange 에 저장
     * - 기존 선택 + dragRange 를 합쳐서 selectedPages 로 반영 (순서 유지)
     */
    function handleDragEnter(index: number) {
        if (!isDragging) return;
        
        const start = Math.min(dragStartIndex, index);
        const end = Math.max(dragStartIndex, index);
        dragRange = Array.from({ length: end - start + 1 }, (_, i) => start + i);

        // 기존 선택에 드래그 범위 요소를 순서대로 추가
        let updated = [...initialSelectedPages];
        for (let pageIndex = start; pageIndex <= end; pageIndex++) {
            if (!updated.includes(pageIndex)) {
                updated.push(pageIndex);
            }
        }
        selectedPages = updated;
    }

    /**
     * 드래그 종료:
     * - 드래그 상태 및 범위 초기화
     */
    function handleDragEnd() {
        if (!isDragging) return;
        isDragging = false;
        dragRange = [];
        dragStartIndex = -1;
    }

    /**
     * “생성” 버튼 클릭 시:
     * - 선택된 페이지의 이미지 배열을 createdContents 에 추가
     * - contentName 및 selectedPages 초기화
     */
    function handleCreate() {
        if (!contentName || selectedPages.length === 0) return;

        // 선택한 페이지(선택 순서대로)의 이미지를 추출
        const selectedImages = selectedPages.map(i => pdfPages[i]);

        createdContents = [
            ...createdContents,
            { name: contentName, pages: selectedImages }
        ];

        // 입력 및 선택 상태 초기화
        contentName = '';
        selectedPages = [];
    }

    /**
     * 생성된 콘텐츠 리스트에서 특정 아이템 삭제
     */
    function removeCreatedContent(idx: number) {
        createdContents = createdContents.filter((_, i) => i !== idx);
    }

    /**
     * 기존 저장 로직 (필요시 수정/삭제 가능)
     */
    function handleSubmit() {
        // TODO: 실제 PDF 생성 및 저장 로직
        console.log('Selected pages:', selectedPages);
        console.log('Content name:', contentName);
        
        isSuccess = true;
        setTimeout(() => {
            isSuccess = false;
            rq.hideModal(id);
        }, 2000);
    }
</script>

<dialog bind:this={dialog} id={id} class="modal modal-content fixed inset-0 flex items-center justify-center z-50 select-none">
    <div class="bg-white p-6 rounded-lg shadow-lg w-full min-w-[800px] max-w-[1200px] max-h-[90vh] overflow-y-auto">
        <!-- 모달 헤더 -->
        <div class="flex justify-between items-center mb-4 border-b pb-2">
            <h2 class="text-xl font-semibold">{title}</h2>
            <button 
                class="text-gray-500 hover:text-gray-700" 
                aria-label="닫기"
                on:click={() => rq.hideModal(id)}
            >
                <i class="fa-solid fa-xmark text-2xl"></i>
            </button>
        </div>

        <!-- 파일 업로드 섹션 -->
        <div class="space-y-4">
            <div>
                <label class="block text-sm mb-2">파일 선택</label>
                <input 
                    type="file" 
                    accept=".pdf"
                    bind:this={fileInput}
                    on:change={handleFileChange}
                    class="w-full"
                />
            </div>

            <!-- PDF 페이지 미리보기 및 선택 -->
            {#if pdfPages.length > 0}
                <div class="border rounded-lg p-4">
                    <div class="grid grid-cols-4 gap-4">
                        {#each pdfPages as page, i}
                            <div 
                                class="relative border rounded-lg overflow-hidden cursor-pointer
                                    {selectedPages.includes(i) ? 'ring-2 ring-[#4FC0D3]' : ''}
                                    {dragRange.includes(i) ? 'bg-blue-100' : ''}"
                                on:mousedown={(e) => handleDragStart(i, e)}
                                on:mouseenter={() => handleDragEnter(i)}
                                on:click={() => handlePageClick(i)}
                            >
                                <!-- PDF 페이지 썸네일 -->
                                <img 
                                    src={page} 
                                    alt={`Page ${i + 1}`} 
                                    class="w-full h-auto select-none" 
                                    draggable="false" 
                                />
                                <!-- 우측 상단: 페이지 번호 (PDF 내 페이지) -->
                                <div class="absolute top-2 right-2 bg-white rounded-full w-6 h-6 flex items-center justify-center text-sm">
                                    {i + 1}
                                </div>
                                <!-- 좌측 상단: 선택 순서 (selectedPages.indexOf(i) + 1) -->
                                {#if selectedPages.includes(i)}
                                    <div class="absolute top-2 left-2 bg-white rounded-full w-6 h-6 flex items-center justify-center text-sm">
                                        {selectedPages.indexOf(i) + 1}
                                    </div>
                                {/if}
                            </div>
                        {/each}
                    </div>
                </div>
            {/if}

            <!-- 콘텐츠명 입력 + 생성 버튼 (PDF 파일이 선택된 경우만 표시) -->
            {#if isFileSelected}
            <div>
                <label class="block text-sm mb-2">콘텐츠명</label>
                <div class="flex gap-2">
                    <input 
                        type="text"
                        bind:value={contentName}
                        placeholder="콘텐츠명을 입력해 주세요."
                        class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                    <button 
                        class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]"
                        on:click={handleCreate}
                        disabled={selectedPages.length === 0 || !contentName}
                    >
                        생성
                    </button>
                </div>
            </div>
            {/if}

            <!-- 생성된 콘텐츠 리스트 -->
            {#if createdContents.length > 0}
                <div class="mt-4 space-y-4">
                    {#each createdContents as item, idx}
                        <div class="flex items-center gap-4 border p-2 rounded-md">
                            <!-- 드래그 핸들 아이콘(예시) -->
                            <div class="flex items-center gap-2">
                                <i class="fa-solid fa-bars text-gray-400"></i>
                                <i class="fa-solid fa-file-pdf text-red-500 text-xl"></i>
                            </div>

                            <!-- 콘텐츠명 및 페이지 썸네일 -->
                            <div class="flex-1">
                                <div class="font-semibold">{item.name}</div>
                                <div class="flex gap-2 mt-2">
                                    {#each item.pages as pageImage}
                                        <img 
                                            src={pageImage} 
                                            alt="PDF Page" 
                                            class="w-16 h-auto border"
                                        />
                                    {/each}
                                </div>
                            </div>

                            <!-- 삭제 버튼 -->
                            <button 
                                class="text-red-500 font-bold"
                                on:click={() => removeCreatedContent(idx)}
                            >
                                X
                            </button>
                        </div>
                    {/each}
                </div>
            {/if}

            <!-- 생성 성공 모달 (기존 로직 유지) -->
            {#if isSuccess}
                <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div class="bg-white p-6 rounded-lg shadow-lg text-center">
                        <i class="fa-solid fa-check text-4xl text-green-500 mb-4"></i>
                        <p class="text-xl">새로운 PDF 파일이 생성되었습니다!</p>
                    </div>
                </div>
            {/if}
        </div>

        <!-- 모달 하단: 기존 '취소' / '저장' 버튼 (필요시 유지/수정) -->
        <div class="flex justify-end gap-2 mt-6">
            <button 
                class="px-4 py-2 rounded-lg border hover:bg-gray-100"
                on:click={() => rq.hideModal(id)}
            >
                취소
            </button>
            <button 
                class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]"
                on:click={handleSubmit}
            >
                저장
            </button>
        </div>
    </div>
</dialog>
