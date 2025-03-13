<script lang="ts">
    import { onMount, createEventDispatcher } from 'svelte';
    
    const dispatch = createEventDispatcher();
    let isVisible = false;

    let excalidrawWrapper: HTMLElement;
    let ExcalidrawLib: any;
    let excalidrawAPI: any;

    onMount(async () => {
        if (isVisible) {
            await loadExcalidraw();
        }
    });

    async function loadExcalidraw() {
        try {
            // React와 ReactDOM 스크립트 로드
            await loadScript('https://unpkg.com/react@17.0.2/umd/react.development.js');
            await loadScript('https://unpkg.com/react-dom@17.0.2/umd/react-dom.development.js');
            
            // Excalidraw CSS 로드
            const cssLink = document.createElement('link');
            cssLink.rel = 'stylesheet';
            cssLink.href = 'https://unpkg.com/@excalidraw/excalidraw@0.15.2/dist/excalidraw.production.min.css';
            document.head.appendChild(cssLink);

            // Excalidraw 스크립트 로드
            await loadScript('https://unpkg.com/@excalidraw/excalidraw@0.15.2/dist/excalidraw.production.min.js');
            
            const ExcalidrawComponent = () => {
                // @ts-ignore
                return window.React.createElement(window.ExcalidrawLib.Excalidraw, {
                    onMount: (api: any) => {
                        excalidrawAPI = api;
                        dispatch('excalidrawMount', { api });
                    },
                    theme: "light",
                    langCode: "ko-KR",
                    viewModeEnabled: false,
                    zenModeEnabled: false,
                    gridModeEnabled: false,
                    initialData: {
                        appState: {
                            viewBackgroundColor: "transparent"
                        }
                    }
                });
            };

            // @ts-ignore
            window.ReactDOM.render(window.React.createElement(ExcalidrawComponent), excalidrawWrapper);
        } catch (error) {
            console.error('Excalidraw 로드 에러:', error);
        }
    }

    function loadScript(src: string) {
        return new Promise((resolve, reject) => {
            const script = document.createElement('script');
            script.src = src;
            script.onload = resolve;
            script.onerror = reject;
            document.head.appendChild(script);
        });
    }

    function handleClose() {
        isVisible = false;
    }

    async function handleOpen() {
        isVisible = true;
        await loadExcalidraw();
    }
</script>

<style>
    .excalidraw-wrapper {
        width: 100%;
        height: 100%;
        position: relative;
        background: transparent;
    }

    :global(.excalidraw) {
        background: transparent !important;
    }

    :global(.excalidraw .layer-ui__wrapper) {
        background: transparent !important;
    }

    :global(.excalidraw .App-menu_top .buttonList) {
        display: flex;
    }

    :global(:root[dir="ltr"] .excalidraw .layer-ui__wrapper .zen-mode-transition.App-menu_bottom--transition-left) {
        transform: none;
    }

    :global(.excalidraw .layer-ui__wrapper__top-right) {
        background: transparent !important;
    }

    /* 메뉴 버튼 숨기기 */
    :global(.excalidraw .Island .ToolIcon__hamburger) {
        display: none !important;
    }

    /* 라이브러리 버튼 숨기기 */
    :global(.excalidraw .Island .ToolIcon__library) {
        display: none !important;
    }

    /* 도움말 버튼 숨기기 */
    :global(.excalidraw .help-icon) {
        display: none !important;
    }

    /* 추가 버튼 숨기기 */
    :global(.excalidraw .library-button) {
        display: none !important;
    }

    :global(.excalidraw .dropdown-menu-button) {
        display: none !important;
    }

    :global(.excalidraw button[data-testid="main-menu"]) {
        display: none !important;
    }
</style>

{#if !isVisible}
    <div class="absolute top-2 right-10 z-10">
        <button 
            class="px-4 py-1 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors"
            on:click={handleOpen}
        >
            <i class="fas fa-pencil-alt"></i> 보드
        </button>
    </div>
{/if}

{#if isVisible}
    <div class="fixed inset-0 z-50 bg-transparent">
        <div class="absolute inset-4 overflow-hidden bg-transparent">
            <div class="absolute top-4 right-4 z-10">
                <button 
                    class="px-4 py-2 bg-white text-black rounded hover:bg-indigo-100 transition-colors"
                    on:click={handleClose}
                >
                    <i class="fas fa-times"></i>
                </button>
            </div>
            <div class="excalidraw-wrapper" bind:this={excalidrawWrapper}></div>
        </div>
    </div>
{/if} 