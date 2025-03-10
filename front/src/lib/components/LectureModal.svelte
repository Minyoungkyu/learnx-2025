<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    // const { title } = $props<{ title: string }>();
    const id = "LectureModal";
    const title = "차시 추가";

    let dialog: HTMLDialogElement;
    let className = $state('');
    let description = $state('');

    let createLectureForm: HTMLFormElement;

    function handleSubmit(event: Event) {
        // event.preventDefault();

        console.log(className, description);        

        // TODO: 제출 로직 구현
        // rq.hideModal(id);
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
            <div>
                <label class="block text-sm mb-2" for="lectureName">차시 명</label>
                <input 
                    type="text" 
                    bind:value={className}
                    placeholder="차시 명을 입력해 주세요."
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>

            <div>
                <label class="block text-sm mb-2" for="description">차시 설명</label>
                <textarea 
                    bind:value={description}
                    placeholder="차시 설명을 입력해 주세요."
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 min-h-52 resize-none"
                ></textarea>
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
            >
                만들기
            </button>
        </div>
    </div>
</dialog>