<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    const { openPDFContentModal, openVideoContentModal, openQuizContentModal, openCLIContentModal, openBoardContentModal, openLinkContentModal } = $props<{ openPDFContentModal: () => void, openVideoContentModal: () => void, openQuizContentModal: () => void, openCLIContentModal: () => void, openBoardContentModal: () => void, openLinkContentModal: () => void }>();

    const id = "ContentModal";
    const title = "콘텐츠 추가";

    let dialog: HTMLDialogElement;
    let selectedType = $state('');

    const contentTypes = [
        { id: 'pdf', label: 'PDF', icon: 'fa-regular fa-file-pdf' },
        { id: 'video', label: '동영상', icon: 'fa-regular fa-circle-play' },
        { id: 'quiz', label: '퀴즈', icon: 'fa-regular fa-square-check' },
        { id: 'cli', label: 'CLI실습', icon: 'fa-solid fa-terminal' },
        { id: 'board', label: '보드', icon: 'fa-regular fa-comments' },
        { id: 'link', label: '링크', icon: 'fa-solid fa-link' }
    ];

    function handleSubmit(event: Event) {
        event.preventDefault();
        if (!selectedType) return;
        
        rq.hideModal(id);
        
        if (selectedType === 'pdf') {
            openPDFContentModal();
        } else if (selectedType === 'video') {
            openVideoContentModal();
        } else if (selectedType === 'quiz') {
            openQuizContentModal();
        } else if (selectedType === 'cli') {
            openCLIContentModal();
        } else if (selectedType === 'board') {
            openBoardContentModal();
        } else if (selectedType === 'link') {
            openLinkContentModal();
        }
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