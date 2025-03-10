<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    export let id;
    export let title;
    export let content = "이곳에 모달 내용을 추가하세요.";

    let dialog: HTMLDialogElement;

</script>

<dialog bind:this={dialog} id={id} class="modal fixed inset-0 flex items-center justify-center z-50">
    <div class="bg-white p-6 rounded shadow-lg w-[400px]">
        <h2 class="text-xl font-bold">{title}</h2>
        <p>{content}</p>
        <button class="mt-4 bg-blue-500 text-white px-4 py-2 rounded" onclick={() => rq.hideModal("myModal")}>닫기</button>
    </div>
</dialog>
