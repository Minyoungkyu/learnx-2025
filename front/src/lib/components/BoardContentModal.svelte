<script lang="ts">
    import rq from "$lib/rq/rq.svelte";
    import { onMount } from 'svelte';
    import ToastUiEditor from '$lib/components/ToastUiEditor.svelte';

    const id = "BoardContentModal";
    const title = "보드 콘텐츠 추가";

    let dialog: HTMLDialogElement;
    let toastUiEditor: any | undefined;
    
    // 상태 관리
    let contentName = $state('');
    let boardItems = $state<string[]>([]);


    // 보드 아이템 삭제
    function removeBoardItem(index: number) {
        boardItems = boardItems.filter((_, i) => i !== index);
    }

    // 저장
    function handleSubmit() {

        // TODO: 실제 저장 로직
        console.log({
            contentName,
            boardItems
        });
        
        rq.hideModal(id);
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
                onclick={() => rq.hideModal(id)}
            >
                <i class="fa-solid fa-xmark text-2xl"></i>
            </button>
        </div>

        <!-- 콘텐츠명 -->
        <div class="mb-4">
            <label class="block text-sm mb-2">콘텐츠 명</label>
            <input 
                type="text"
                bind:value={contentName}
                placeholder="콘텐츠 명을 입력해주세요."
                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>

        <div class="mb-4">
            <label class="block text-sm mb-2">콘텐츠 내용</label>
            <ToastUiEditor bind:this={toastUiEditor} body={''} />
        </div>

        <!-- 보드 아이템 섹션 -->
        <div class="mb-4">
            <div class="flex justify-between items-center mb-2">
                <label class="block text-sm">기본보드 생성</label>
                <button 
                    onclick={() => {
                        boardItems = [...boardItems, ''];
                    }}
                    class="px-2 py-1 text-sm rounded-lg bg-[#5DDBDB] text-white hover:bg-[#4DCDCD]"
                >
                    추가+
                </button>
            </div>

            {#each boardItems as item, i}
                <div class="mb-4 p-4 border rounded-lg">
                    <div class="flex justify-between items-center mb-2">
                        <h3 class="font-medium">보드 아이템 #{i + 1}</h3>
                        <button 
                            onclick={() => removeBoardItem(i)}
                            class="text-red-500 hover:text-red-700"
                        >
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </div>

                    <div class="space-y-3">
                        <input 
                            type="text"
                            bind:value={boardItems[i]}
                            placeholder="보드 명을 입력해주세요."
                            class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                </div>
            {/each}
        </div>


        <!-- 모달 푸터 -->
        <div class="flex justify-end gap-2 mt-6">
            <button 
                class="px-4 py-2 rounded-lg border hover:bg-gray-100"
                onclick={() => rq.hideModal(id)}
            >
                취소
            </button>
            <button 
                class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]"
                onclick={handleSubmit}
            >
                저장
            </button>
        </div>
    </div>
</dialog> 