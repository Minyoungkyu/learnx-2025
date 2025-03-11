<script lang="ts">
    import { onMount } from 'svelte';

    export let direction: 'horizontal' | 'vertical' = 'horizontal';
    
    let resizer: HTMLElement;
    let isResizing = false;
    let startPos = { x: 0, y: 0 };
    
    function handleMouseDown(e: MouseEvent) {
        isResizing = true;
        startPos = { x: e.clientX, y: e.clientY };
        
        document.addEventListener('mousemove', handleMouseMove);
        document.addEventListener('mouseup', handleMouseUp);
        
        // 드래그 중 텍스트 선택 방지
        document.body.style.userSelect = 'none';
    }
    
    function handleMouseMove(e: MouseEvent) {
        if (!isResizing) return;
        
        const delta = {
            x: e.clientX - startPos.x,
            y: e.clientY - startPos.y
        };
        
        if (direction === 'horizontal') {
            const prevElement = resizer.previousElementSibling as HTMLElement;
            const nextElement = resizer.nextElementSibling as HTMLElement;
            
            if (prevElement && nextElement) {
                const prevWidth = prevElement.getBoundingClientRect().width;
                const nextWidth = nextElement.getBoundingClientRect().width;
                
                prevElement.style.width = `${prevWidth + delta.x}px`;
                nextElement.style.width = `${nextWidth - delta.x}px`;
                
                startPos.x = e.clientX;
            }
        } else {
            const prevElement = resizer.previousElementSibling as HTMLElement;
            const nextElement = resizer.nextElementSibling as HTMLElement;
            
            if (prevElement && nextElement) {
                const prevHeight = prevElement.getBoundingClientRect().height;
                const nextHeight = nextElement.getBoundingClientRect().height;
                
                prevElement.style.height = `${prevHeight + delta.y}px`;
                nextElement.style.height = `${nextHeight - delta.y}px`;
                
                startPos.y = e.clientY;
            }
        }
    }
    
    function handleMouseUp() {
        isResizing = false;
        document.removeEventListener('mousemove', handleMouseMove);
        document.removeEventListener('mouseup', handleMouseUp);
        document.body.style.userSelect = '';
    }
    
    onMount(() => {
        return () => {
            document.removeEventListener('mousemove', handleMouseMove);
            document.removeEventListener('mouseup', handleMouseUp);
        };
    });
</script>

<div
    bind:this={resizer}
    class="resizer {direction === 'horizontal' ? 'horizontal' : 'vertical'}"
    on:mousedown={handleMouseDown}
/>

<style>
    .resizer {
        background-color: #2a2a2a;
        opacity: 0.2;
        transition: opacity 0.15s ease-in-out;
        position: relative;
        z-index: 10;
    }
    
    .resizer:hover {
        opacity: 0.5;
    }
    
    .horizontal {
        cursor: col-resize;
        width: 4px;
        height: 100%;
    }
    
    .vertical {
        cursor: row-resize;
        height: 4px;
        width: 100%;
    }
</style> 