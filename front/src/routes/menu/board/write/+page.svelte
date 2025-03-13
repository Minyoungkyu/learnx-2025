<script lang="ts">
  import { goto } from '$app/navigation';
  
  const isAdmin = true; // 관리자 여부
  let title = '';
  let content = '';
  let isNotice = false;
  let selectedLecture: number | null = null;

  const lectures = Array.from({ length: 10 }, (_, i) => ({
    id: i + 1,
    title: `${i + 1}차시`
  }));

  function handleSubmit() {
    // TODO: 게시글 작성 로직 구현
    console.log('제출된 내용:', { 
      title, 
      content, 
      isNotice, 
      selectedLecture 
    });
  }
</script>

<div class="p-6 w-[1000px] mx-auto">
  <div class="border-b border-gray-200 pb-4 mb-6">
    <h2 class="text-2xl font-bold">게시물 작성</h2>
  </div>

  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    {#if isAdmin}
      <div class="flex items-center gap-4 mb-4">
        <div class="flex items-center gap-2">
          <input
            type="checkbox"
            id="notice"
            class="rounded border-gray-300"
            bind:checked={isNotice}
          />
          <label for="notice" class="text-sm">공지사항</label>
        </div>

        <select
          class="px-3 py-1.5 text-sm border rounded-lg"
          bind:value={selectedLecture}
        >
          <option value={null}>차시 선택</option>
          {#each lectures as lecture}
            <option value={lecture.id}>{lecture.title}</option>
          {/each}
        </select>
      </div>
    {/if}

    <div class="mb-4">
      <input
        type="text"
        placeholder="제목을 입력하세요"
        class="w-full px-4 py-2 border rounded-lg text-sm"
        bind:value={title}
      />
    </div>

    <div class="mb-4">
      <div class="border rounded-lg">
        <div class="flex items-center gap-1 p-2 border-b bg-gray-50">
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-bold"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-italic"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-underline"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-strikethrough"></i>
          </button>
          <div class="w-px h-4 bg-gray-300 mx-1"></div>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-subscript"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-superscript"></i>
          </button>
          <div class="w-px h-4 bg-gray-300 mx-1"></div>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-list-ul"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-list-ol"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-tasks"></i>
          </button>
          <div class="w-px h-4 bg-gray-300 mx-1"></div>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-align-left"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-align-center"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-align-right"></i>
          </button>
          <div class="w-px h-4 bg-gray-300 mx-1"></div>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-table"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-image"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-link"></i>
          </button>
          <button class="p-1.5 hover:bg-gray-200 rounded">
            <i class="fas fa-code"></i>
          </button>
        </div>
        <textarea
          placeholder="내용을 입력하세요"
          class="w-full h-[300px] p-4 text-sm resize-none focus:outline-none"
          bind:value={content}
        ></textarea>
      </div>
    </div>

    <div class="mb-6">
      <div class="flex items-center gap-2 p-4 bg-gray-50 rounded-lg border border-dashed">
        <i class="fas fa-file-upload text-gray-400"></i>
        <span class="text-sm text-gray-600">파일을 드래그 앤 드롭 하세요</span>
        <button class="px-3 py-1 text-sm border rounded-lg hover:bg-gray-100">파일찾기</button>
      </div>
    </div>

    <div class="flex justify-between items-center">
      <div></div>
      <div class="flex gap-2">
        <button class="px-3 py-1.5 text-sm border rounded-lg hover:bg-gray-100" on:click={() => goto('/menu/board')}>
          목록
        </button>
        <button
          class="px-3 py-1.5 text-sm bg-cyan-500 text-white rounded-lg hover:bg-cyan-600"
          on:click={handleSubmit}
        >
          저장
        </button>
      </div>
    </div>
  </div>
</div>
