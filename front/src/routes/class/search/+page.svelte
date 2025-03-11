<script lang="ts">
  import NewClassModal from '$lib/components/NewClassModal.svelte';
  import { onMount } from 'svelte';

  interface SelectedTags {
    language: string[];
    field: string[];
    level: string[];
    etc: string[];
  }

  interface ClassItem {
    icon: string;
    title: string;
    time: string;
  }

  // 검색어
  let searchQuery = '';

  // 선택된 태그들
  let selectedTags: {
    language: string[];
    field: string[];
    level: string[];
    etc: string[];
  } = {
    language: [],
    field: [],
    level: [],
    etc: []
  };

  // 태그 옵션들
  const tagOptions = {
    language: ['파이썬', '자바스크립트', 'C#', 'C++', '아두이노', '스케치'],
    field: ['웹', '임베디드', '인공지능', '빅데이터', '앱', '머신러닝'],
    level: ['기초', '초급', '중급', '고급', '신화'],
    etc: ['최근 개설', '베스트']
  };

  // 추천 클래스 목록
  let recommendedClasses = [
    {
      icon: "js",
      title: "자바스크립트",
      time: "4차시"
    },
    {
      icon: "python",
      title: "데이터 분석",
      time: "4차시"
    },
    {
      icon: "python",
      title: "알고리즘으로 알아보는 생활 속 숨겨진 비밀",
      time: "8차시"
    }
  ];

  // 팝업 상태 관리
  let isNewClassModalOpen = false;

  function openNewClassModal() {
    isNewClassModalOpen = true;
    document.body.style.overflow = 'hidden';
  }

  function closeNewClassModal() {
    isNewClassModalOpen = false;
    document.body.style.overflow = '';
  }

  function handleCreateClass(event: CustomEvent) {
    const newClassData = event.detail;
    console.log('새 클래스 데이터:', newClassData);
    closeNewClassModal();
  }

  onMount(() => {
    return () => {
      // 컴포넌트가 언마운트될 때 스크롤 상태 복원
      document.body.style.overflow = '';
    };
  });

  /**
   * 태그 선택/해제 함수
   * @param {string} category - 태그 카테고리
   * @param {string} tag - 선택한 태그
   */
  function toggleTag(category: keyof typeof selectedTags, tag: string) {
    if (selectedTags[category].includes(tag)) {
      selectedTags[category] = selectedTags[category].filter(t => t !== tag);
    } else {
      selectedTags[category] = [...selectedTags[category], tag];
    }
  }

  /**
   * 태그 삭제 함수
   * @param {string} category - 태그 카테고리
   * @param {string} tag - 삭제할 태그
   */
  function removeTag(category: keyof typeof selectedTags, tag: string) {
    selectedTags[category] = selectedTags[category].filter(t => t !== tag);
  }

  /**
   * 검색 실행 함수
   */
  function handleSearch() {
    // TODO: 검색 로직 구현
    console.log('검색어:', searchQuery);
    console.log('선택된 태그들:', selectedTags);
  }

  /**
   * @param {string} title
   */
  function truncateTitle(title: string, maxLength = 25) {
    return title.length > maxLength 
      ? title.slice(0, maxLength) + '...'
      : title;
  }
</script>

<div class="bg-gray-100 w-full">
  <div class="max-w-7xl min-h-[90vh] mx-auto px-4 py-8">
    <!-- 타이틀과 새 클래스 만들기 버튼 -->
    <div class="flex items-center justify-between mb-6">
      <div class="flex items-center gap-2">
        <i class="fa-solid fa-list text-2xl text-blue-500"></i>
        <h1 class="text-xl font-bold">클래스 목록</h1>
      </div>
      <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg hover:bg-indigo-700 transition-colors duration-150"
        on:click={openNewClassModal}
      >
        새 클래스 만들기
      </button>
    </div>
    <div class="border-b border-gray-300 mb-6"></div>

    <!-- 검색 섹션 -->
    <div class="bg-white rounded-xl p-6 shadow-md mb-8">
      <!-- 태그 필터 -->
      <div class="space-y-4 mb-6">
        <!-- 언어 -->
        <div class="flex items-center gap-4">
          <h3 class="text-base font-bold whitespace-nowrap">언어</h3>
          <div class="flex flex-wrap gap-2">
            {#each tagOptions.language as tag}
              <button 
                class="text-base px-4 py-1.5 rounded-full border {selectedTags.language.includes(tag) ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                on:click={() => toggleTag('language', tag)}
              >
                {tag}
              </button>
            {/each}
          </div>
        </div>

        <!-- 분야 -->
        <div class="flex items-center gap-4">
          <h3 class="text-base font-bold whitespace-nowrap">분야</h3>
          <div class="flex flex-wrap gap-2">
            {#each tagOptions.field as tag}
              <button 
                class="text-base px-4 py-1.5 rounded-full border {selectedTags.field.includes(tag) ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                on:click={() => toggleTag('field', tag)}
              >
                {tag}
              </button>
            {/each}
          </div>
        </div>

        <!-- 난이도 -->
        <div class="flex items-center gap-4">
          <h3 class="text-base font-bold whitespace-nowrap">난이도</h3>
          <div class="flex flex-wrap gap-2">
            {#each tagOptions.level as tag}
              <button 
                class="text-base px-4 py-1.5 rounded-full border {selectedTags.level.includes(tag) ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                on:click={() => toggleTag('level', tag)}
              >
                {tag}
              </button>
            {/each}
          </div>
        </div>

        <!-- 기타 -->
        <div class="flex items-center gap-4">
          <h3 class="text-base font-bold whitespace-nowrap">기타</h3>
          <div class="flex flex-wrap gap-2">
            {#each tagOptions.etc as tag}
              <button 
                class="text-base px-4 py-1.5 rounded-full border {selectedTags.etc.includes(tag) ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                on:click={() => toggleTag('etc', tag)}
              >
                {tag}
              </button>
            {/each}
          </div>
        </div>
      </div>

      <div>
        <!-- 검색 입력창 -->
        <div class="flex items-center gap-4 mb-4">
          <div class="relative flex-grow">
            <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <i class="fas fa-search text-gray-400"></i>
            </div>
            <input
              type="text"
              class="w-full pl-10 pr-4 py-3 bg-white rounded-lg border border-gray-300 focus:outline-none focus:border-indigo-500"
              placeholder="클래스를 검색해보세요"
              bind:value={searchQuery}
            />
          </div>
          <button 
            class="px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors duration-150"
            on:click={handleSearch}
          >
            검색
          </button>
        </div>

        <!-- 선택된 태그들 -->
        {#if Object.values(selectedTags).some(tags => tags.length > 0)}
          <div class="flex flex-wrap gap-2">
            {#each Object.entries(selectedTags) as [category, tags]}
              {#each tags as tag}
                <div class="bg-cyan-100 px-3 py-1 rounded-full flex items-center gap-1">
                  <span class="text-cyan-800 text-base">{tag}</span>
                  <button 
                    class="text-cyan-600 hover:text-cyan-800"
                    on:click={() => removeTag(category as keyof typeof selectedTags, tag)}
                  >
                    <i class="fas fa-times"></i>
                  </button>
                </div>
              {/each}
            {/each}
          </div>
        {/if}
      </div>
    </div>

    <!-- 검색 결과 섹션 -->
    <div class="bg-white rounded-xl p-6 shadow-md">
      <h2 class="text-xl font-bold mb-4">검색 결과</h2>
      <div class="grid grid-cols-2 gap-4">
        {#each recommendedClasses as class_item}
          <div class="bg-white rounded-lg p-6 shadow-md outline outline-2 outline-gray-100">
            <div class="flex items-center justify-between">
              <div class="flex items-center gap-4">
                <div class="bg-white rounded-lg p-2 shadow-md outline outline-2 outline-gray-100 flex items-center justify-center w-16 h-16">
                  <img 
                    src="/images/python_icon.png" 
                    alt={class_item.icon} 
                    class="w-10 h-10 object-contain"
                  />
                </div>
                <div class="w-[200px]">
                  <h3 class="font-bold">{truncateTitle(class_item.title)}</h3>
                  <p class="text-sm text-gray-500 font-bold">{class_item.time}</p>
                </div>
              </div>
              <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg hover:bg-indigo-700 transition-colors duration-150">
                클래스 정보
              </button>
            </div>
          </div>
        {/each}
      </div>
    </div>
  </div>
</div>

{#if isNewClassModalOpen}
  <NewClassModal
    isOpen={isNewClassModalOpen}
    on:close={closeNewClassModal}
    on:create={handleCreateClass}
  />
{/if} 