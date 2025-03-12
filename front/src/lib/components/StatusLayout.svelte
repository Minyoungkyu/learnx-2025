<script lang="ts">
  import { page } from '$app/stores';
  import { goto } from '$app/navigation';

  // 메뉴 확인
  $: menu = $page.url.pathname.includes('/menu/status') ? 'status' : 'report';

  // 현재 활성화된 탭 확인
  $: activeTab = $page.url.pathname.includes('member') ? 'member' : 'lecture';

  // 더미 데이터 - 실제로는 props나 store로 관리해야 함
  const chapters = [
    {
      id: 'all',
      title: '차시 전체',
      progress: 75
    },
    {
      id: '1',
      title: '1차시: 프로그래밍 기초',
      progress: 100,
      contents: [
        { id: '1-1', title: '프로그래밍 소개', type: 'video' },
        { id: '1-2', title: '변수와 자료형', type: 'quiz' },
        { id: '1-3', title: '실습 과제', type: 'assignment' }
      ]
    },
    {
      id: '2',
      title: '2차시: 제어문',
      progress: 80,
      contents: [
        { id: '2-1', title: '조건문', type: 'video' },
        { id: '2-2', title: '반복문', type: 'quiz' }
      ]
    },
    {
      id: '3',
      title: '3차시: 함수',
      progress: 65,
      contents: [
        { id: '3-1', title: '함수의 개념', type: 'video' },
        { id: '3-2', title: '매개변수와 반환값', type: 'video' },
        { id: '3-3', title: '함수 실습', type: 'assignment' },
        { id: '3-4', title: '함수 퀴즈', type: 'quiz' }
      ]
    },
    {
      id: '4',
      title: '4차시: 리스트와 튜플',
      progress: 45,
      contents: [
        { id: '4-1', title: '리스트 기초', type: 'video' },
        { id: '4-2', title: '리스트 메서드', type: 'video' },
        { id: '4-3', title: '튜플', type: 'video' },
        { id: '4-4', title: '데이터 구조 실습', type: 'assignment' }
      ]
    },
    {
      id: '5',
      title: '5차시: 딕셔너리와 집합',
      progress: 30,
      contents: [
        { id: '5-1', title: '딕셔너리 개념', type: 'video' },
        { id: '5-2', title: '딕셔너리 활용', type: 'assignment' },
        { id: '5-3', title: '집합 자료형', type: 'video' },
        { id: '5-4', title: '자료형 퀴즈', type: 'quiz' }
      ]
    },
    {
      id: '6',
      title: '6차시: 클래스와 객체',
      progress: 15,
      contents: [
        { id: '6-1', title: '클래스 기초', type: 'video' },
        { id: '6-2', title: '객체지향 프로그래밍', type: 'video' },
        { id: '6-3', title: '상속과 다형성', type: 'video' },
        { id: '6-4', title: '클래스 실습', type: 'assignment' },
        { id: '6-5', title: '최종 퀴즈', type: 'quiz' }
      ]
    }
  ];

  // 구성원 목록 데이터
  const members = Array(8).fill(null).map((_, i) => ({
    id: i + 1,
    name: '홍길동',
    email: 'asdf1234@naver.com',
    profileImage: 'P'
  }));

  // 필터 옵션
  let selectedFilter = '';

  // 탭 변경 함수
  function changeTab(tab: string) {
    goto(`/menu/${menu}/${tab}`);
  }

  // 선택된 콘텐츠 관리
  let selectedContent = chapters[0];
  
  // 차시 펼침/접힘 상태 관리 - 초기에 모든 차시 펼침
  let expandedChapters = new Set(chapters.filter(chapter => chapter.contents).map(chapter => chapter.id));
  
  function toggleChapter(chapterId: string) {
    if (expandedChapters.has(chapterId)) {
      expandedChapters.delete(chapterId);
    } else {
      expandedChapters.add(chapterId);
    }
    expandedChapters = expandedChapters; // Svelte 반응성을 위해 재할당
  }

  function selectContent(content: any) {
    selectedContent = content;
  }
</script>

<div class="w-[1000px] mx-auto px-4 py-8">
  <h1 class="text-2xl font-bold mb-4">학습 현황</h1>
  <div class="w-full h-px bg-gray-100 mb-8"></div>

  <div class="flex gap-8">
    <!-- 왼쪽 사이드바 -->
    <div class="w-[280px] flex-shrink-0">
      <div class="bg-white rounded-xl shadow-md flex flex-col h-full outline outline-2 outline-gray-100">
        <!-- 탭 메뉴 -->
        <div class="flex border-b border-gray-100">
          <button 
            class="flex-1 flex flex-col items-center gap-1 px-4 py-3 relative transition-colors {activeTab === 'lecture' ? 'text-indigo-600' : 'text-gray-500 hover:text-gray-700'}"
            on:click={() => changeTab('lecture')}
          >
            <div class="w-6 h-6 flex items-center justify-center">
              <i class="fa-solid fa-book text-lg"></i>
            </div>
            <span class="text-sm font-bold">수업</span>
            {#if activeTab === 'lecture'}
              <div class="absolute bottom-0 left-0 w-full h-0.5 bg-indigo-600"></div>
            {/if}
          </button>
          <button 
            class="flex-1 flex flex-col items-center gap-1 px-4 py-3 relative transition-colors {activeTab === 'member' ? 'text-indigo-600' : 'text-gray-500 hover:text-gray-700'}"
            on:click={() => changeTab('member')}
          >
            <div class="w-6 h-6 flex items-center justify-center">
              <i class="fa-solid fa-user text-lg"></i>
            </div>
            <span class="text-sm font-bold">구성원</span>
            {#if activeTab === 'member'}
              <div class="absolute bottom-0 left-0 w-full h-0.5 bg-indigo-600"></div>
            {/if}
          </button>
        </div>

        <!-- 차시 목록 또는 구성원 목록 -->
        {#if activeTab === 'lecture'}
          <div class="flex-grow">
            <div class="p-4 space-y-2">
              {#each chapters as chapter}
                <div class="space-y-2">
                  <button 
                    class="w-full flex items-center justify-between p-2 rounded-lg hover:bg-gray-50 transition-colors {selectedContent.id === chapter.id ? 'bg-indigo-50 text-indigo-600' : 'text-gray-700'}"
                    on:click={() => {
                      selectContent(chapter);
                      if (chapter.contents) toggleChapter(chapter.id);
                    }}
                  >
                    <div class="flex items-center gap-2">
                      {#if chapter.contents}
                        <i class="fas fa-{expandedChapters.has(chapter.id) ? 'chevron-down' : 'chevron-right'} text-sm"></i>
                      {/if}
                      <span class="text-sm font-medium">{chapter.title}</span>
                    </div>
                    {#if chapter.progress !== undefined}
                      <span class="text-xs text-gray-500">{chapter.progress}%</span>
                    {/if}
                  </button>

                  {#if chapter.contents && expandedChapters.has(chapter.id)}
                    <div class="ml-4 space-y-1">
                      {#each chapter.contents as content}
                        <button 
                          class="w-full flex items-center gap-2 p-2 rounded-lg hover:bg-gray-50 transition-colors text-sm {selectedContent.id === content.id ? 'bg-indigo-50 text-indigo-600' : 'text-gray-600'}"
                          on:click={() => selectContent(content)}
                        >
                          <i class="fas fa-{content.type === 'video' ? 'play-circle' : content.type === 'quiz' ? 'question-circle' : 'tasks'} text-xs"></i>
                          <span>{content.title}</span>
                        </button>
                      {/each}
                    </div>
                  {/if}
                </div>
              {/each}
            </div>
          </div>
        {:else}
          <div class="flex-grow">
            <div class="p-4">
              <div class="relative">
                <div class="absolute inset-y-0 left-3 flex items-center pointer-events-none">
                  <i class="fas fa-search text-gray-400"></i>
                </div>
                <input 
                  type="text"
                  placeholder="검색"
                  class="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-200 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-100 focus:border-indigo-300"
                  bind:value={selectedFilter}
                />
              </div>
            </div>
            <div class="px-4 space-y-2">
              {#each members as member}
                <button class="w-full flex items-center gap-3 p-3 rounded-lg hover:bg-gray-50 transition-colors text-left">
                  <div class="w-10 h-10 rounded-full bg-gray-100 flex items-center justify-center text-gray-500 font-medium">
                    {member.profileImage}
                  </div>
                  <div class="flex-grow">
                    <div class="text-sm font-medium">{member.name}</div>
                    <div class="text-xs text-gray-500">{member.email}</div>
                  </div>
                </button>
              {/each}
            </div>
          </div>
        {/if}
      </div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="flex-grow bg-white rounded-xl shadow-md p-6 outline outline-2 outline-gray-100">
      <slot {selectedContent} />
    </div>
  </div>
</div> 