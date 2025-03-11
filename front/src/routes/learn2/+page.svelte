<script lang="ts">
    import SideMenu from "./sideMenu.svelte";
    import { setupAceEditor } from "$lib/aceEdit/aceEditorSetup.svelte";
    import { onMount } from "svelte";
    import * as pdfjsLib from 'pdfjs-dist';
    import 'pdfjs-dist/build/pdf.worker.entry';

    let pdfDoc: any = null;
    let pageNum = 1;
    let pageRendering = false;
    let pageNumPending: number | null = null;
    let canvas: HTMLCanvasElement;
    let ctx: CanvasRenderingContext2D;
    let scale = 1.5;
    let pageCount: number;
    
    // 드래그 관련 변수
    let isDragging = false;
    let startX = 0;
    let startY = 0;
    let scrollLeft = 0;
    let scrollTop = 0;
    let container: HTMLElement;

    onMount(async () => {
        // PDF 로드 및 렌더링
        canvas = document.createElement('canvas');
        ctx = canvas.getContext('2d')!;
        container = document.getElementById('pdf-container')!;
        container.appendChild(canvas);
        
        // 드래그 이벤트 설정
        setupDragEvents();
        
        try {
            const loadingTask = pdfjsLib.getDocument('/sample/sample_pdf.pdf');
            pdfDoc = await loadingTask.promise;
            pageCount = pdfDoc.numPages;
            renderPage(pageNum);
        } catch (error) {
            console.error('PDF 로딩 에러:', error);
        }
    });

    function setupDragEvents() {
        container.style.cursor = 'grab';

        container.addEventListener('mousedown', (e: MouseEvent) => {
            isDragging = true;
            container.style.cursor = 'grabbing';
            startX = e.pageX - container.offsetLeft;
            startY = e.pageY - container.offsetTop;
            scrollLeft = container.scrollLeft;
            scrollTop = container.scrollTop;
        });

        container.addEventListener('mouseleave', () => {
            isDragging = false;
            container.style.cursor = 'grab';
        });

        container.addEventListener('mouseup', () => {
            isDragging = false;
            container.style.cursor = 'grab';
        });

        container.addEventListener('mousemove', (e: MouseEvent) => {
            if (!isDragging) return;
            
            e.preventDefault();
            const x = e.pageX - container.offsetLeft;
            const y = e.pageY - container.offsetTop;
            const moveX = x - startX;
            const moveY = y - startY;
            
            container.scrollLeft = scrollLeft - moveX;
            container.scrollTop = scrollTop - moveY;
        });
    }

    async function renderPage(num: number) {
        pageRendering = true;
        const page = await pdfDoc.getPage(num);
        
        const viewport = page.getViewport({ scale });
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        const renderContext = {
            canvasContext: ctx,
            viewport: viewport
        };

        try {
            await page.render(renderContext).promise;
            pageRendering = false;
            if (pageNumPending !== null) {
                renderPage(pageNumPending);
                pageNumPending = null;
            }
        } catch (error) {
            console.error('페이지 렌더링 에러:', error);
            pageRendering = false;
        }
    }

    function onPrevPage() {
        if (pageNum > 1) {
            pageNum--;
            renderPage(pageNum);
        }
    }

    function onNextPage() {
        if (pageNum < pageCount) {
            pageNum++;
            renderPage(pageNum);
        }
    }

    function zoomOut() {
        if (scale > 0.5) {
            scale -= 0.1;
            renderPage(pageNum);
        }
    }

    function zoomIn() {
        if (scale < 3) {
            scale += 0.1;
            renderPage(pageNum);
        }
    }
</script>

<div class="w-full h-screen flex flex-row overflow-hidden">
    <div class="w-[250px] min-w-[200px] sideMenuContainer bg-[#1E273B]">
        <SideMenu />
    </div>
    <div class="flex-1 flex min-w-[600px]">
        <div class="w-full h-full contentBody flex flex-col bg-[#323436]">
            <div class="w-full h-full flex flex-col text-white font-bold">
                <div class="flex justify-between items-end w-full h-[60px] bg-[#323436]">
                    <div class="flex h-full items-end">
                        <div class="p-2 bg-[#1E273B] text-white rounded-t-lg h-[45px] w-[250px] flex items-center justify-between border-t border-l border-r border-gray-700">
                            <span>02 프로그램과 프로그래밍 언어</span>
                            <span class="flex item-center"><i class="fa-solid fa-xmark"></i></span>
                        </div>
                    </div>
                </div>
                <div id="pdf-container" class="flex items-center justify-center w-full h-full bg-[#1E273B] overflow-auto">
                </div>
                <div id="control-container" class="flex justify-between items-center w-full h-[80px] border-t border-gray-700 bg-[#1E273B] px-4">
                    <div class="flex items-center gap-8">
                        <!-- 페이지 이동 컨트롤 -->
                        <div class="flex items-center gap-4">
                            <button 
                                class="px-3 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors" 
                                on:click={onPrevPage}
                                disabled={pageNum <= 1}
                            >
                                <i class="fa-solid fa-chevron-left"></i>
                            </button>
                            <span class="min-w-[60px] text-center">{pageNum} / {pageCount}</span>
                            <button 
                                class="px-3 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors"
                                on:click={onNextPage}
                                disabled={pageNum >= pageCount}
                            >
                                <i class="fa-solid fa-chevron-right"></i>
                            </button>
                        </div>

                        <!-- 줌 컨트롤 -->
                        <div class="flex items-center gap-4">
                            <button class="px-3 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors" on:click={zoomOut}>
                                <i class="fa-solid fa-minus"></i>
                            </button>
                            <span class="min-w-[60px] text-center">{Math.round(scale * 100)}%</span>
                            <button class="px-3 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors" on:click={zoomIn}>
                                <i class="fa-solid fa-plus"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="flex justify-center items-center w-full h-[60px] border-t border-gray-700">
                    <div class="flex items-center gap-4">
                        <button class="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors">
                            <i class="fa-solid fa-angle-left"></i> 이전
                        </button>
                        <div class="flex items-center gap-2">
                            <span class="text-white">1/10</span>
                        </div>
                        <button class="px-4 py-2 text-white rounded bg-[#4FC0D3] hover:bg-[#45AAB9] transition-colors">
                            다음 <i class="fa-solid fa-angle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
             
        </div>
    </div>
</div>