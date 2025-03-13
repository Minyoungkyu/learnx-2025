<script lang="ts">
  import { goto } from '$app/navigation';

  const isAdmin = true;
  let selectedPosts: number[] = [];

  // 게시글 데이터
  const posts = [
    {
      id: 1,
      type: '공지',
      title: '1월 공지사항 확인',
      hasAttachment: true,
      author: '선생님',
      date: '2025.01.08'
    },
    {
      id: 2,
      type: '공지',
      title: '공동일 학습관련 가정통신문',
      hasAttachment: true,
      author: '선생님',
      date: '2025.01.08'
    },
    {
      id: 3,
      type: '질문',
      title: '1차시 출력 관련 질문이 있습니다',
      lecture: 1,
      author: '홍길동',
      date: '2025.01.08'
    },
    {
      id: 4,
      type: '질문',
      title: '3차시 입력 부분이 이해가 안돼요',
      lecture: 3,
      author: '김철수',
      date: '2025.01.08'
    },
    {
      id: 5,
      type: '질문',
      title: '5차시 실습 오류 해결 방법',
      lecture: 5,
      isNew: true,
      author: '이영희',
      date: '2025.01.08'
    },
    {
      id: 6,
      type: '4',
      title: '우리반 규칙',
      isNew: true,
      author: '선생님',
      date: '2025.01.08'
    },
    {
      id: 7,
      type: '3',
      title: '수업시간에 지켜야 할 내용',
      author: '선생님',
      date: '2025.01.08'
    },
    {
      id: 8,
      type: '2',
      title: 'AI 튜터를 사용하는 방법',
      author: '선생님',
      date: '2025.01.08'
    }
  ];

  let currentPage = 1;
  const itemsPerPage = 10;
  const totalPages = Math.ceil(posts.length / itemsPerPage);

  $: paginatedPosts = posts.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage
  );

  function handleSelectAll(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    if (checkbox.checked) {
      selectedPosts = paginatedPosts.map(post => post.id);
    } else {
      selectedPosts = [];
    }
  }

  function handleSelectPost(postId: number) {
    const index = selectedPosts.indexOf(postId);
    if (index === -1) {
      selectedPosts = [...selectedPosts, postId];
    } else {
      selectedPosts = selectedPosts.filter(id => id !== postId);
    }
  }

  function handleDelete() {
    if (selectedPosts.length === 0) {
      alert('삭제할 게시글을 선택해주세요.');
      return;
    }
    if (confirm('선택한 게시글을 삭제하시겠습니까?')) {
      // TODO: 삭제 로직 구현
      console.log('삭제할 게시글:', selectedPosts);
    }
  }
</script>

<div class="p-6 w-[1000px] mx-auto">
  <div class="border-b border-gray-200 pb-4 mb-6">
    <h2 class="text-2xl font-bold">게시판</h2>
  </div>
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    <div class="flex justify-end mb-6">
      <div class="relative">
        <input type="text" placeholder="검색" class="pl-3 pr-8 py-1 border rounded-lg text-sm" />
        <button class="absolute right-2 top-1/2 -translate-y-1/2">
          <i class="fas fa-search text-gray-400 text-sm"></i>
        </button>
      </div>
    </div>

    <div class="overflow-x-auto">
      <table class="w-full">
        <thead>
          <tr class="border-y border-gray-200">
            {#if isAdmin}
              <th class="py-3 text-center w-12 font-normal text-sm text-gray-600">
                <input 
                  type="checkbox" 
                  class="rounded border-gray-300"
                  on:change={handleSelectAll}
                />
              </th>
            {/if}
            <th class="py-3 text-center w-20 font-normal text-sm text-gray-600">번호</th>
            <th class="py-3 text-left font-normal text-sm text-gray-600">제목</th>
            <th class="py-3 text-center w-32 font-normal text-sm text-gray-600">작성자</th>
            <th class="py-3 text-center w-32 font-normal text-sm text-gray-600">작성일</th>
          </tr>
        </thead>
        <tbody>
          {#each paginatedPosts as post}
            <tr class="border-b border-gray-200 hover:bg-gray-50">
              {#if isAdmin}
                <td class="py-4 text-center">
                  <input 
                    type="checkbox" 
                    class="rounded border-gray-300"
                    checked={selectedPosts.includes(post.id)}
                    on:change={() => handleSelectPost(post.id)}
                  />
                </td>
              {/if}
              <td class="py-4 text-center text-sm text-gray-600">
                {#if post.type === '공지'}
                  <span class="font-bold">공지</span>
                {:else}
                  {post.id}
                {/if}
              </td>
              <td class="text-sm">
                <div class="flex items-center gap-2">
                  {#if post.lecture}
                    <span class="px-2 py-0.5 bg-cyan-100 text-cyan-700 rounded text-xs font-medium">{post.lecture}차시</span>
                  {/if}
                  <span>{post.title}</span>
                  {#if post.hasAttachment}
                    <i class="fas fa-paperclip text-gray-400"></i>
                  {/if}
                  {#if post.isNew}
                    <span class="text-xs text-red-500 font-medium">NEW</span>
                  {/if}
                </div>
              </td>
              <td class="text-center text-sm">{post.author}</td>
              <td class="text-center text-sm">{post.date}</td>
            </tr>
          {/each}
        </tbody>
      </table>
    </div>

    <div class="mt-4">
      <div class="flex justify-center items-center gap-1 mb-4">
        <button 
          class="w-8 h-8 flex items-center justify-center rounded-lg hover:bg-gray-100"
          disabled={currentPage === 1}
          on:click={() => currentPage = Math.max(1, currentPage - 1)}
        >
          <i class="fas fa-chevron-left text-gray-400"></i>
        </button>
        
        {#each Array(totalPages) as _, i}
          <button 
            class="w-8 h-8 flex items-center justify-center rounded-lg {currentPage === i + 1 ? 'bg-cyan-500 text-white' : 'hover:bg-gray-100'}"
            on:click={() => currentPage = i + 1}
          >
            {i + 1}
          </button>
        {/each}
        
        <button 
          class="w-8 h-8 flex items-center justify-center rounded-lg hover:bg-gray-100"
          disabled={currentPage === totalPages}
          on:click={() => currentPage = Math.min(totalPages, currentPage + 1)}
        >
          <i class="fas fa-chevron-right text-gray-400"></i>
        </button>
      </div>
      <div class="flex justify-end gap-2">
        <button class="px-3 py-1.5 text-sm border rounded-lg hover:bg-gray-100 flex items-center gap-1" on:click={() => goto('/menu/board/write')}>
          <i class="fas fa-pen text-sm"></i>
          글쓰기
        </button>
        {#if isAdmin}
          <button 
            class="px-3 py-1.5 text-sm border rounded-lg hover:bg-gray-100 text-red-500 hover:text-red-600 flex items-center gap-1"
            on:click={handleDelete}
          >
            <i class="fas fa-trash-alt text-sm"></i>
            삭제
          </button>
        {/if}
      </div>
    </div>
  </div>
</div>
