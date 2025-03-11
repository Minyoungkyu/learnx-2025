<script lang="ts">
  import rq from "$lib/rq/rq.svelte";

  let lectureTitle = "발 맞추어 천천히 따라가는 중학생 기초 파이썬";
  let progress = 15;

  let lectureList = [{
    title: "프로그램과 프로그래밍 언어, 출력과 순차구조",
    progress: 100,
    contents: [
      {
      type: "video",
      title: "01-수업목표",
      time: "9분",
    },
    {
      type: "pdf",
      title: "02-프로그램과 프로그래밍 언어",
    },
    {
      type: "pdf",
      title: "03-출력",
    },
    {
      type: "text",
      title: "실습1-5라는 숫자를 출력해 봅시다",
    }
    ],
  },
  {
    title: "변수와 자료",
    progress: 100,
    contents: [
      {
      type: "video",
      title: "01-수업목표",
      time: "9분",
    },
    {
      type: "pdf",
      title: "02-프로그램과 프로그래밍 언어",
    },
    {
      type: "pdf",
      title: "03-출력",
    },
    {
      type: "text",
      title: "실습1-5라는 숫자를 출력해 봅시다",
    }
    ]
  },
  {
    title: "입력과 자료형 변환",
    progress: 100,
    contents: [
      {
      type: "video",
      title: "01-수업목표",
      time: "9분",
    },
    {
      type: "pdf",
      title: "02-프로그램과 프로그래밍 언어",
    },
    {
      type: "pdf",
      title: "03-출력",
    },
    {
      type: "text",
      title: "실습1-5라는 숫자를 출력해 봅시다",
    }
    ]
  },



];
  

  /**
     * @param {string} title
     */
  function truncateTitle(title: string, maxLength = 25) {
    return title.length > maxLength 
      ? title.slice(0, maxLength) + '...'
      : title;
  }

  // 선택된 차시 인덱스 (기본값: 0)
  let selectedLectureIndex = 0;

  // 차시 선택 핸들러
  /**
     * @param {number} index
     */
  function handleLectureSelect(index: number) {
    selectedLectureIndex = index;
  }

  // 메뉴 상태 관리
  let openLectureMenu = -1;  // 차시 메뉴
  let openContentMenu = { lectureIndex: -1, contentIndex: -1 };  // 콘텐츠 메뉴

  // 메뉴 토글 함수
  /**
     * @param {number} index
     * @param {Event} event
     */
  function toggleLectureMenu(index: number, event: Event) {
    event.stopPropagation();  // 차시 클릭 이벤트 전파 방지
    openLectureMenu = openLectureMenu === index ? -1 : index;
    openContentMenu = { lectureIndex: -1, contentIndex: -1 };  // 다른 메뉴 닫기
  }

  /**
     * @param {number} lectureIndex
     * @param {number} contentIndex
     * @param {Event} event
     */
  function toggleContentMenu(lectureIndex: number, contentIndex: number, event: Event) {
    event.stopPropagation();  // 상위 요소 클릭 이벤트 전파 방지
    openContentMenu = openContentMenu.lectureIndex === lectureIndex && openContentMenu.contentIndex === contentIndex
      ? { lectureIndex: -1, contentIndex: -1 }
      : { lectureIndex, contentIndex };
    openLectureMenu = -1;  // 다른 메뉴 닫기
  }

  // 메뉴 닫기 함수
  function closeAllMenus() {
    openLectureMenu = -1;
    openContentMenu = { lectureIndex: -1, contentIndex: -1 };
  }

  // 전역 클릭 이벤트 핸들러
  function handleGlobalClick() {
    closeAllMenus();
  }

  // 차시 추가 팝업 상태 관리
  let isAddLectureModalOpen = false;
  let newLectureData = {
    title: '',
    description: ''
  };

  function openAddLectureModal() {
    isAddLectureModalOpen = true;
  }

  function closeAddLectureModal() {
    isAddLectureModalOpen = false;
    newLectureData = { title: '', description: '' };
  }

  function handleAddLecture() {
    // TODO: 차시 추가 로직 구현
    closeAddLectureModal();
  }

  // 모달 타입 정의
  type ModalType = 
    | 'Lecture'
    | 'Content'
    | 'PDFContent'
    | 'VideoContent'
    | 'QuizContent'
    | 'CLIContent'
    | 'BoardContent'
    | 'LinkContent';

  // 모달 컴포넌트 저장 객체
  let modalComponents: Partial<Record<ModalType, any>> = {};

  // 제네릭 모달 로드 함수
  async function loadModal(type: ModalType) {
    if (!modalComponents[type]) {
      const module = await import(`$lib/components/${type}Modal.svelte`);
      modalComponents[type] = module.default;
    }
    return modalComponents[type];
  }

  // 제네릭 모달 오픈 함수
  async function openModal(type: ModalType) {
    if (!modalComponents[type]) {
      await loadModal(type);
    }
    rq.showModal(`${type}Modal`);
  }

  // Content 모달 오픈 시 필요한 props
  async function openContentModal() {
    await openModal('Content');
    if (modalComponents.Content) {
      const props = {
        openPDFContentModal: () => openModal('PDFContent'),
        openVideoContentModal: () => openModal('VideoContent'),
        openQuizContentModal: () => openModal('QuizContent'),
        openCLIContentModal: () => openModal('CLIContent'),
        openBoardContentModal: () => openModal('BoardContent'),
        openLinkContentModal: () => openModal('LinkContent')
      };
      return props;
    }
  }
</script>


<!-- svelte-ignore a11y_click_events_have_key_events -->
<!-- svelte-ignore a11y_no_static_element_interactions -->
<div class="bg-gray-100 w-full h-full" onclick={handleGlobalClick}>
  <div class="w-[1000px] mx-auto px-4 py-8">
    <!-- 타이틀과 구분선 -->
    <div class="mb-8">
      <div class="flex items-center justify-between mb-4">
        <h1 class="text-2xl font-bold">차시 목록</h1>
        <div class="flex items-center gap-2">
          <button class="flex items-center gap-2 px-4 py-2 bg-white rounded-lg border border-gray-200 hover:bg-gray-50 transition-colors duration-150">
            <i class="fas fa-sort text-gray-600"></i>
            <span>순서 변경</span>
          </button>
          <button 
            class="flex items-center gap-2 px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors duration-150"
            onclick={() => openModal('Lecture')}
          >
            <i class="fas fa-plus"></i>
            <span>차시 추가</span>
          </button>
        </div>
      </div>
      <div class="border-b border-gray-300"></div>
    </div>

    <!-- 차시 목록 -->
    <div class="space-y-4">
      {#each lectureList as lecture, index}
        <!-- 대단원 제목과 진행률 -->
        <!-- svelte-ignore a11y_click_events_have_key_events -->
        <!-- svelte-ignore a11y_no_static_element_interactions -->
        <div class="rounded-xl outline outline-2 outline-gray-200 shadow-md p-6 mb-4 cursor-pointer bg-white"
             onclick={() => handleLectureSelect(index)}>
          <div class="flex items-center justify-between">
            <div class="flex items-center gap-3">
              <span class="bg-cyan-500 text-white px-3 py-1 rounded-md text-sm font-bold">
                {index + 1}차시
              </span>
              <h2 class="text-lg font-bold">{lecture.title}</h2>
            </div>
            <div class="flex items-center gap-4">
              <div class="flex items-center gap-2">
                <div class="bg-gray-200 rounded-full h-2 w-32">
                  <div class="bg-cyan-600 h-2 rounded-full" style="width: {lecture.progress}%"></div>
                </div>
                <span class="text-sm font-bold text-gray-500">{lecture.progress}%</span>
              </div>
              <!-- 차시 메뉴 버튼 -->
              <div class="relative">
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button 
                  class="p-2 hover:bg-gray-100 rounded-full"
                  onclick={(e) => toggleLectureMenu(index, e)}
                >
                  <i class="fas fa-ellipsis-v text-gray-600"></i>
                </button>
                {#if openLectureMenu === index}
                  <div 
                    class="absolute right-0 mt-2 w-32 bg-white rounded-lg shadow-lg border border-gray-200 z-10"
                  >
                    <button class="w-full text-left px-4 py-2 hover:bg-gray-50 rounded-t-lg">
                      <i class="fas fa-edit mr-2"></i>수정
                    </button>
                    <button class="w-full text-left px-4 py-2 hover:bg-gray-50 text-red-600 rounded-b-lg">
                      <i class="fas fa-trash-alt mr-2"></i>삭제
                    </button>
                  </div>
                {/if}
              </div>
            </div>
          </div>

          <!-- 차시 목록 - 선택된 차시만 표시 -->
          {#if selectedLectureIndex === index}
          <!-- 구분선 추가 -->
          <div class="border-b border-gray-200 pt-4 mb-4"></div>
            <div class="space-y-1">
              {#each lecture.contents as content, contentIndex}
                <div class="bg-white p-1 flex items-center gap-4 hover:bg-cyan-100 transition-colors duration-150 rounded-lg cursor-pointer">
                  <!-- 차시 아이콘 -->
                  <div class="bg-gray-50 rounded-lg p-2 flex items-center justify-center w-12 h-12">
                    {#if content.type === 'video'}
                      <i class="fas fa-play-circle text-2xl text-gray-600"></i>
                    {:else if content.type === 'pdf'}
                      <i class="fas fa-file-pdf text-2xl text-gray-600"></i>
                    {:else}
                      <i class="fas fa-file-alt text-2xl text-gray-600"></i>
                    {/if}
                  </div>

                  <!-- 차시 정보 -->
                  <div class="flex-grow">
                    <div class="flex items-center gap-2">
                      <h3 class="font-bold">{content.title}</h3>
                      {#if content.time}
                        <span class="text-sm text-gray-500">{content.time}</span>
                      {/if}
                    </div>
                  </div>

                  <!-- 콘텐츠 메뉴 버튼 -->
                  <div class="relative">
                    <!-- svelte-ignore a11y_click_events_have_key_events -->
                    <!-- svelte-ignore a11y_no_static_element_interactions -->
                    <!-- svelte-ignore a11y_consider_explicit_label -->
                    <button 
                      class="p-2 hover:bg-gray-100 rounded-full"
                      onclick={(e) => toggleContentMenu(index, contentIndex, e)}
                    >
                      <i class="fas fa-ellipsis-v text-gray-600"></i>
                    </button>
                    {#if openContentMenu.lectureIndex === index && openContentMenu.contentIndex === contentIndex}
                      <div 
                        class="absolute right-0 mt-2 w-32 bg-white rounded-lg shadow-lg border border-gray-200 z-10"
                      >
                        <button class="w-full text-left px-4 py-2 hover:bg-gray-50 rounded-t-lg">
                          <i class="fas fa-edit mr-2"></i>수정
                        </button>
                        <button class="w-full text-left px-4 py-2 hover:bg-gray-50 text-red-600 rounded-b-lg">
                          <i class="fas fa-trash-alt mr-2"></i>삭제
                        </button>
                      </div>
                    {/if}
                  </div>
                </div>
              {/each}
              
              <!-- 콘텐츠 추가 버튼 -->
              <div class="mt-4 flex justify-center">
                <button 
                  onclick={openContentModal}
                  class="flex items-center gap-2 px-4 py-2 text-sm bg-white rounded-lg border border-gray-200 hover:bg-gray-50 transition-colors duration-150"
                >
                  <i class="fas fa-plus text-gray-600"></i>
                  <span>콘텐츠 추가</span>
                </button>
              </div>
            </div>
          {/if}
        </div>
      {/each}
    </div>
  </div>
</div>

<!-- 모달 컴포넌트 렌더링 -->
{#each Object.entries(modalComponents) as [type, Component]}
  {#if Component}
    <svelte:component 
      this={Component} 
      {...type === 'Content' ? { loadModal } : {}} 
    />
  {/if}
{/each}