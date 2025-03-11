<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    const { loadModal } = $props<{
        loadModal: (type: ModalType) => Promise<any>
    }>();

    type ModalType = 
        | 'Lecture'
        | 'Content'
        | 'PDFContent'
        | 'VideoContent'
        | 'QuizContent'
        | 'CLIContent'
        | 'BoardContent'
        | 'LinkContent';

    const id = "ContentModal";
    const title = "콘텐츠 추가";

    let dialog: HTMLDialogElement;
    let selectedType = $state('');

    const contentTypes = [
        { id: 'PDF', label: 'PDF', icon: 'fa-regular fa-file-pdf' },
        { id: 'Video', label: '동영상', icon: 'fa-regular fa-circle-play' },
        { id: 'Quiz', label: '퀴즈', icon: 'fa-regular fa-square-check' },
        { id: 'CLI', label: 'CLI실습', icon: 'fa-solid fa-terminal' },
        { id: 'Board', label: '보드', icon: 'fa-regular fa-comments' },
        { id: 'Link', label: '링크', icon: 'fa-solid fa-link' }
    ];

    async function handleSubmit(event: Event) {
        event.preventDefault();
        if (!selectedType) return;
        
        rq.hideModal(id);
        
        // selectedType을 ModalType 형식으로 변환 (Content 접미사 추가)
        const modalType = selectedType + 'Content' as ModalType;
        await loadModal(modalType);
        rq.showModal(`${modalType}Modal`);
    }
</script>

<dialog bind:this={dialog} id={id} class="modal modal-content fixed inset-0 flex items-center justify-center z-50">
    <div class="bg-white p-6 rounded-lg shadow-lg w-full min-w-[400px] max-w-[600px]">
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

        <form onsubmit={handleSubmit} class="space-y-4">
            <div class="grid grid-cols-3 gap-4">
                {#each contentTypes as type}
                    <button
                        type="button"
                        class="flex flex-col items-center justify-center p-4 border rounded-lg
                               {selectedType === type.id ? 'border-[#4FC0D3] bg-[#4FC0D3]/10' : 'hover:bg-gray-50'}"
                        onclick={() => selectedType = type.id}
                    >
                        <i class="{type.icon} text-2xl mb-2"></i>
                        <span class="text-sm">{type.label}</span>
                    </button>
                {/each}
            </div>
        </form>

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
                disabled={!selectedType}
            >
                다음
            </button>
        </div>
    </div>
</dialog> 