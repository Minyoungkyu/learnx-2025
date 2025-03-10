<script lang="ts">
    import rq from "$lib/rq/rq.svelte";
    import Modal from "$lib/components/Modal.svelte";
    import { onMount } from "svelte";

    let LectureModalComponent: any = null;
    let ContentModalComponent: any = null;
    let PDFContentModalComponent: any = null;
    let VideoContentModalComponent: any = null;
    let QuizContentModalComponent: any = null;
    let CLIContentModalComponent: any = null;
    let BoardContentModalComponent: any = null;
    let LinkContentModalComponent: any = null;

    async function loadLectureModal() {
        const module = await import("$lib/components/LectureModal.svelte");
        LectureModalComponent = module.default;
    }

    async function loadContentModal() {
        const module = await import("$lib/components/ContentModal.svelte");
        ContentModalComponent = module.default;
    }

    async function loadPDFContentModal() {
        const module = await import("$lib/components/PDFContentModal.svelte");
        PDFContentModalComponent = module.default;
    }

    async function loadVideoContentModal() {
        const module = await import("$lib/components/VideoContentModal.svelte");
        VideoContentModalComponent = module.default;
    }

    async function loadQuizContentModal() {
        const module = await import("$lib/components/QuizContentModal.svelte");
        QuizContentModalComponent = module.default;
    }

    async function loadCLIContentModal() {
        const module = await import("$lib/components/CLIContentModal.svelte");
        CLIContentModalComponent = module.default;
    }

    async function loadBoardContentModal() {
        const module = await import("$lib/components/BoardContentModal.svelte");
        BoardContentModalComponent = module.default;
    }

    async function loadLinkContentModal() {
        const module = await import("$lib/components/LinkContentModal.svelte");
        LinkContentModalComponent = module.default;
    }

    async function openLectureModal() {
        if (!LectureModalComponent) {
            await loadLectureModal();
        }
        rq.showModal("LectureModal");
    }

    async function openContentModal() {
        if (!ContentModalComponent) {
            await loadContentModal();
        }
        rq.showModal("ContentModal");
    }

    async function openPDFContentModal() {
        if (!PDFContentModalComponent) {
            await loadPDFContentModal();
        }
        rq.showModal("PDFContentModal");
    }

    async function openVideoContentModal() {
        if (!VideoContentModalComponent) {
            await loadVideoContentModal();
        }
        rq.showModal("VideoContentModal");
    }

    async function openQuizContentModal() {
        if (!QuizContentModalComponent) {
            await loadQuizContentModal();
        }
        rq.showModal("QuizContentModal");
    }

    async function openCLIContentModal() {
        if (!CLIContentModalComponent) {
            await loadCLIContentModal();
        }
        rq.showModal("CLIContentModal");
    }

    async function openBoardContentModal() {
        if (!BoardContentModalComponent) {
            await loadBoardContentModal();
        }
        rq.showModal("BoardContentModal");
    }

    async function openLinkContentModal() {
        if (!LinkContentModalComponent) {
            await loadLinkContentModal();
        }
        rq.showModal("LinkContentModal");
    }
</script>

<div class="space-x-2">
    <button onclick={openLectureModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        차시 추가
    </button>
    <button onclick={openContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        콘텐츠 추가
    </button>
    <button onclick={openPDFContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        PDF 추가
    </button>
    <button onclick={openVideoContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        동영상 추가
    </button>
    <button onclick={openQuizContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        퀴즈 추가
    </button>
    <button onclick={openCLIContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        CLI 콘텐츠 추가
    </button>
    <button onclick={openBoardContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        보드 추가
    </button>
    <button onclick={openLinkContentModal} class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]">
        링크 콘텐츠 추가
    </button>
</div>

<Modal id="myModal" title="테스트 모달" content="이 모달은 컴포넌트화 되었습니다."/> 

{#if LectureModalComponent}
    <svelte:component this={LectureModalComponent} />
{/if}

{#if ContentModalComponent}
    <svelte:component this={ContentModalComponent} {openPDFContentModal} {openVideoContentModal} {openQuizContentModal} {openCLIContentModal} {openBoardContentModal} {openLinkContentModal}/>
{/if}

{#if PDFContentModalComponent}
    <svelte:component this={PDFContentModalComponent} />
{/if}

{#if VideoContentModalComponent}
    <svelte:component this={VideoContentModalComponent} />
{/if}

{#if QuizContentModalComponent}
    <svelte:component this={QuizContentModalComponent} />
{/if}

{#if CLIContentModalComponent}
    <svelte:component this={CLIContentModalComponent} />
{/if}

{#if BoardContentModalComponent}
    <svelte:component this={BoardContentModalComponent} />
{/if}

{#if LinkContentModalComponent}
    <svelte:component this={LinkContentModalComponent} />
{/if}
