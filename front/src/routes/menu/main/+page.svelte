<script>
  import { goto } from '$app/navigation';

  let classTitle = "발 맞춰서 천천히 따라가는 중학생 기초 파이썬";
  let progress = 15;

  let curriculumList = [
    {
      title: "프로그램과 프로그래밍 언어, 출력과 순차구조",
      progress: 100,
      description: "프로그램과 프로그래밍 언어의 개념을 이해하고, 파이썬에서 출력하는 방법과 순차구조에 대해 학습합니다. 간단한 출력 프로그램을 직접 작성해보는 실습도 포함되어 있습니다.",
      contents: [
        {
          title: "프로그램과 프로그래밍 언어",
          description: "프로그램과 프로그래밍 언어의 개념에 대해 알아봅니다.",
          time: "20분",
          completed: true
        },
        {
          title: "출력과 순차구조",
          description: "파이썬에서 출력하는 방법과 순차구조에 대해 학습합니다.",
          time: "25분",
          completed: true
        },
        {
          title: "실습: 첫 번째 프로그램 작성하기",
          description: "간단한 출력 프로그램을 작성해봅니다.",
          time: "15분",
          completed: true
        }
      ]
    },
    {
      title: "변수와 자료형",
      progress: 80,
      description: "변수의 개념과 선언 방법, 파이썬의 기본 자료형에 대해 학습합니다. 변수를 활용한 간단한 프로그램을 작성해보는 실습을 통해 개념을 확실히 이해할 수 있습니다.",
      contents: [
        {
          title: "변수의 개념과 선언",
          description: "변수의 개념과 선언 방법에 대해 알아봅니다.",
          time: "20분",
          completed: true
        },
        {
          title: "기본 자료형",
          description: "파이썬의 기본 자료형에 대해 학습합니다.",
          time: "25분",
          completed: true
        },
        {
          title: "실습: 변수 활용하기",
          description: "변수를 활용한 간단한 프로그램을 작성해봅니다.",
          time: "15분",
          completed: false
        }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 0,
      description: "사용자로부터 입력을 받는 방법과 다양한 자료형 간의 변환 방법에 대해 학습합니다. 입력과 형변환을 활용한 프로그램을 직접 작성해보는 실습이 포함되어 있습니다.",
      contents: [
        {
          title: "사용자 입력 받기",
          description: "사용자로부터 입력을 받는 방법에 대해 알아봅니다.",
          time: "20분",
          completed: false
        },
        {
          title: "자료형 변환",
          description: "다양한 자료형 간의 변환 방법에 대해 학습합니다.",
          time: "25분",
          completed: false
        },
        {
          title: "실습: 입력과 형변환 활용하기",
          description: "입력과 형변환을 활용한 프로그램을 작성해봅니다.",
          time: "15분",
          completed: false
        }
      ]
    },
    {
      title: "연산자",
      progress: 0,
      description: "산술 연산자, 비교 연산자, 논리 연산자 등 파이썬의 다양한 연산자에 대해 학습합니다. 여러 연산자를 활용한 프로그램을 직접 작성해보는 실습을 통해 실전 감각을 키울 수 있습니다.",
      contents: [
        {
          title: "산술 연산자",
          description: "덧셈, 뺄셈 등의 산술 연산자에 대해 알아봅니다.",
          time: "15분",
          completed: false
        },
        {
          title: "비교 연산자와 논리 연산자",
          description: "비교 연산자와 논리 연산자의 사용법을 학습합니다.",
          time: "20분",
          completed: false
        },
        {
          title: "실습: 연산자 활용하기",
          description: "다양한 연산자를 활용한 프로그램을 작성해봅니다.",
          time: "15분",
          completed: false
        }
      ]
    }
  ];

  // 선택된 차시 인덱스 (기본값: -1, 모두 닫힌 상태)
  let selectedCurriculumIndex = -1;

  // 차시 선택 핸들러
  /**
   * @param {number} index
   */
  function handleCurriculumSelect(index) {
    // 이미 선택된 차시를 다시 클릭하면 닫기
    selectedCurriculumIndex = selectedCurriculumIndex === index ? -1 : index;
  }

  /**
   * @param {string} title
   */
  function truncateTitle(title, maxLength = 25) {
    return title.length > maxLength 
      ? title.slice(0, maxLength) + '...'
      : title;
  }

  /**
   * 학습하기 버튼 클릭 핸들러
   * @param {Event} event
   */
  function handleStudyClick(event) {
    event.stopPropagation(); // 상위 요소 클릭 이벤트 전파 방지
    goto('/menu/lecture'); // 학습 페이지로 이동
  }

  /**
   * 수정하기 버튼 클릭 핸들러
   */
  function handleEditClick() {
    // TODO: 클래스 수정 기능 구현
    console.log('클래스 수정하기 버튼 클릭됨');
  }
</script>

<div class="bg-gray-100 w-full">
  <div class="max-w-4xl mx-auto px-4 py-8">
    <!-- 상단 클래스 정보 타이틀 -->
    <div class="mb-6">
      <div class="flex items-center justify-between">
        <h1 class="text-xl font-bold">클래스 정보</h1>
        <button 
          class="bg-indigo-600 text-white px-4 py-2 rounded-lg hover:bg-indigo-700 transition-colors duration-150"
          on:click={handleEditClick}
        >
          수정하기
        </button>
      </div>
      <div class="border-b border-gray-300 mt-2"></div>
    </div>

    <!-- 클래스 정보 -->
    <div class="bg-white rounded-xl shadow-md p-6 mb-8">
      <div class="flex items-center justify-between mb-4">
        <h1 class="text-2xl font-bold text-cyan-500">{classTitle}</h1>
        <span class="bg-cyan-100 text-cyan-800 px-3 py-1 rounded-full text-sm font-bold">
          진행률: {progress}%
        </span>
      </div>
      <div class="bg-gray-200 rounded-full h-2 mb-4">
        <div class="bg-cyan-600 h-2 rounded-full" style="width: {progress}%"></div>
      </div>
      <div class="flex items-center gap-2 mb-4">
        <i class="fas fa-user-graduate text-gray-600"></i>
        <span class="text-gray-600">By 김철수 강사님</span>
      </div>
      
      <!-- 클래스 상세 설명 -->
      <p class="text-gray-600 mb-4">
        파이썬으로 변수를 다루고, 조건문과 반복문으로 스마트한 프로그램을 만들어 보세요!<br>
        리스트와 함수로 데이터를 효율적으로 처리하며, 간단한 게임과 도전 과제를 해결해 봅니다.<br>
        중학생 눈높이에 맞춘 쉽고 재미있는 코딩 수업이 여러분을 기다립니다!
      </p>
      
      <!-- 태그 -->
      <div class="flex flex-wrap gap-2">
        <span class="bg-gray-100 px-3 py-1 rounded-full text-sm text-gray-600">#텍스트 코딩</span>
        <span class="bg-gray-100 px-3 py-1 rounded-full text-sm text-gray-600">#파이썬</span>
        <span class="bg-gray-100 px-3 py-1 rounded-full text-sm text-gray-600">#기초</span>
      </div>
    </div>

    <!-- 커리큘럼 타이틀 -->
    <div class="mb-6">
      <div class="flex items-center justify-between">
        <h2 class="text-xl font-bold">커리큘럼</h2>
        <div class="text-sm text-gray-500">총 12차시</div>
      </div>
      <div class="border-b border-gray-300 mt-2"></div>
    </div>

    <!-- 커리큘럼 목록 -->
    <div class="space-y-4 mb-6">
      {#each curriculumList as curriculum, index}
        <!-- svelte-ignore a11y_click_events_have_key_events -->
        <!-- svelte-ignore a11y_no_static_element_interactions -->
        <div class="bg-white rounded-xl shadow-md cursor-pointer"
             on:click={() => handleCurriculumSelect(index)}>
          <!-- 차시 헤더 -->
          <div class="p-5 flex items-center justify-between">
            <div class="flex items-center gap-3">
              <span class="bg-cyan-500 text-white px-3 py-1 rounded-md text-sm font-bold">
                {index + 1}차시
              </span>
              <h3 class="font-bold">{curriculum.title}</h3>
            </div>
            <div class="flex items-center gap-4">
              <div class="flex items-center gap-2">
                <div class="bg-gray-200 rounded-full h-2 w-24">
                  <div class="bg-cyan-600 h-2 rounded-full" style="width: {curriculum.progress}%"></div>
                </div>
                <span class="text-sm font-bold text-gray-500">{curriculum.progress}%</span>
              </div>
              <i class="fas fa-chevron-{selectedCurriculumIndex === index ? 'up' : 'down'} text-gray-500"></i>
            </div>
          </div>

          <!-- 차시 내용 (선택된 경우에만 표시) -->
          {#if selectedCurriculumIndex === index}
            <div class="border-t border-gray-200 p-4">
              <p class="text-gray-600 mb-4">{curriculum.description}</p>
              <div class="flex justify-end">
                <button 
                  class="bg-indigo-600 text-white px-6 py-2 rounded-lg hover:bg-indigo-700 transition-colors duration-150"
                  on:click={handleStudyClick}
                >
                  학습하기
                </button>
              </div>
            </div>
          {/if}
        </div>
      {/each}
    </div>

    <!-- 학습요소 마인드맵 -->
    <div class="mb-6">
      <div class="flex items-center justify-between">
        <h2 class="text-xl font-bold">학습요소 마인드맵</h2>
      </div>
      <div class="border-b border-gray-300 mt-2"></div>
    </div>
  </div>
</div>
