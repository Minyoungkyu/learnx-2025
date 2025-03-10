<script>
  import { goto } from '$app/navigation';
  import { onMount } from 'svelte';

  let myClasses = [
    {
      title: "발 맞춰서 천천히 따라가는 중학생 기초 파이썬",
      progress: 15,
      time: "3 / 12차시"
    }
  ];

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

  let isRecommendedOpen = false;
  let isClassListOpen = false;

  // 팝업 상태 관리
  let isNewClassModalOpen = false;
  let newClassData = {
    title: '',
    description: '',
    difficulty: '기초',
    customDifficulty: '',
    language: '파이썬',  // 언어 기본값
    customLanguage: '',  // 기타 언어 입력값
    categories: /** @type {string[]} */ ([]),
    aiCapabilities: [
      { name: '문제 해결 역량', value: 20 },
      { name: '추상화 및 알고리즘 설계 역량', value: 20 },
      { name: '창의적 사고 역량', value: 20 },
      { name: '컴퓨팅 사고 역량', value: 20 },
      { name: '데이터 활용 역량', value: 20 }
    ],
    customCapabilities: /** @type {Array<{name: string, value: number}>} */ ([])
  };

  // 난이도 옵션
  const difficultyOptions = ['기초', '중급', '고급', '기타'];
  
  // 언어 옵션
  const languageOptions = ['파이썬', '엔트리', 'C+', '기타'];
  
  // 분야 옵션
  const categoryOptions = ['웹', '블록 코딩', '테스트 코딩', '데이터 분석', '인공지능', '알고리즘'];
  
  // 새 분야 입력값
  let newCategory = '';
  
  // 분야 추가 함수
  function addCategory() {
    if (newCategory.trim() && !newClassData.categories.includes(newCategory.trim())) {
      newClassData.categories = [...newClassData.categories, newCategory.trim()];
      newCategory = '';
    }
  }
  
  // 분야 삭제 함수
  /**
   * @param {string} category
   */
  function removeCategory(category) {
    newClassData.categories = newClassData.categories.filter(c => c !== category);
  }

  // 새 역량 입력값
  let newCapability = '';
  
  // 역량 추가 함수
  function addCapability() {
    if (newCapability.trim()) {
      // 새 역량 추가 시 기존 역량들의 값을 조정
      const totalCapabilities = newClassData.aiCapabilities.length + newClassData.customCapabilities.length;
      const newValue = Math.floor(100 / (totalCapabilities + 1)); // 균등하게 분배
      
      // 기존 역량들 조정
      newClassData.aiCapabilities = newClassData.aiCapabilities.map(cap => ({
        ...cap,
        value: newValue
      }));
      
      newClassData.customCapabilities = newClassData.customCapabilities.map(cap => ({
        ...cap,
        value: newValue
      }));
      
      // 새 역량 추가
      newClassData.customCapabilities = [...newClassData.customCapabilities, { name: newCapability.trim(), value: newValue }];
      
      // 합이 100%가 되도록 첫 번째 역량 조정
      adjustCapabilitiesToTotal();
      
      newCapability = '';
    }
  }
  
  // 역량 삭제 함수
  /**
   * @param {number} index
   */
  function removeCapability(index) {
    // 삭제할 역량의 값을 저장
    const removedValue = newClassData.customCapabilities[index].value;
    
    // 역량 삭제
    newClassData.customCapabilities = newClassData.customCapabilities.filter((_, i) => i !== index);
    
    // 남은 역량들에 삭제된 역량의 값을 분배
    const totalCapabilities = newClassData.aiCapabilities.length + newClassData.customCapabilities.length;
    if (totalCapabilities > 0) {
      const valueToAdd = Math.floor(removedValue / totalCapabilities);
      
      newClassData.aiCapabilities = newClassData.aiCapabilities.map(cap => ({
        ...cap,
        value: cap.value + valueToAdd
      }));
      
      newClassData.customCapabilities = newClassData.customCapabilities.map(cap => ({
        ...cap,
        value: cap.value + valueToAdd
      }));
      
      // 합이 100%가 되도록 첫 번째 역량 조정
      adjustCapabilitiesToTotal();
    }
  }
  
  /**
   * 역량 값이 변경될 때 다른 역량들의 값을 조정하는 함수
   * @param {number} index - 변경된 역량의 인덱스
   * @param {boolean} isFixed - 고정 역량인지 여부
   * @param {number} oldValue - 이전 값
   * @param {number} newValue - 새 값
   */
  function adjustCapabilities(index, isFixed, oldValue, newValue) {
    const diff = newValue - oldValue;
    if (diff === 0) return;
    
    // 변경된 역량을 제외한 다른 역량들
    const otherCapabilities = [
      ...newClassData.aiCapabilities.filter((_, i) => !(isFixed && i === index)),
      ...newClassData.customCapabilities.filter((_, i) => !(!isFixed && i === index))
    ];
    
    if (otherCapabilities.length === 0) return;
    
    // 다른 역량들의 총합
    const totalOtherValues = otherCapabilities.reduce((sum, cap) => sum + cap.value, 0);
    
    // 각 역량에서 차감할 비율 계산
    const reductionRatio = diff / totalOtherValues;
    
    // 다른 역량들의 값 조정
    newClassData.aiCapabilities = newClassData.aiCapabilities.map((cap, i) => {
      if (isFixed && i === index) return cap;
      
      // 비례적으로 값 감소
      let newVal = Math.max(0, Math.round(cap.value - (cap.value * reductionRatio)));
      return { ...cap, value: newVal };
    });
    
    newClassData.customCapabilities = newClassData.customCapabilities.map((cap, i) => {
      if (!isFixed && i === index) return cap;
      
      // 비례적으로 값 감소
      let newVal = Math.max(0, Math.round(cap.value - (cap.value * reductionRatio)));
      return { ...cap, value: newVal };
    });
    
    // 합이 100%가 되도록 조정
    adjustCapabilitiesToTotal();
  }
  
  /**
   * 모든 역량의 합이 100%가 되도록 조정하는 함수
   */
  function adjustCapabilitiesToTotal() {
    const total = [
      ...newClassData.aiCapabilities,
      ...newClassData.customCapabilities
    ].reduce((sum, cap) => sum + cap.value, 0);
    
    // 합이 100%가 아니면 첫 번째 역량을 조정
    if (total !== 100 && newClassData.aiCapabilities.length > 0) {
      const diff = 100 - total;
      newClassData.aiCapabilities[0].value = Math.max(0, newClassData.aiCapabilities[0].value + diff);
    }
  }

  function openNewClassModal() {
    isNewClassModalOpen = true;
    document.body.style.overflow = 'hidden';
  }

  function closeNewClassModal() {
    isNewClassModalOpen = false;
    document.body.style.overflow = '';
    newClassData = { 
      title: '', 
      description: '', 
      difficulty: '기초', 
      customDifficulty: '',
      language: '파이썬',
      customLanguage: '',
      categories: [],
      aiCapabilities: [
        { name: '문제 해결 역량', value: 20 },
        { name: '추상화 및 알고리즘 설계 역량', value: 20 },
        { name: '창의적 사고 역량', value: 20 },
        { name: '컴퓨팅 사고 역량', value: 20 },
        { name: '데이터 활용 역량', value: 20 }
      ],
      customCapabilities: []
    };
    newCategory = '';
  }

  function handleCreateClass() {
    // TODO: 클래스 생성 로직 구현
    closeNewClassModal();
  }

  /**
   * @param {string} title
   */
  function truncateTitle(title, maxLength = 25) {
    return title.length > maxLength 
      ? title.slice(0, maxLength) + '...'
      : title;
  }

  onMount(() => {
    return () => {
      // 컴포넌트가 언마운트될 때 스크롤 상태 복원
      document.body.style.overflow = '';
    };
  });
</script>

<div class="max-w-7xl min-h-[90vh] mx-auto px-4 py-8">
  <div class="flex flex-col md:flex-row gap-8">
    <!-- 왼쪽: 내 클래스 섹션 -->
    <div class="w-full md:w-[480px] flex-shrink-0">
      <section class="bg-gray-50 rounded-xl p-6">
        <div class="flex items-center justify-between mb-6">
          <div class="flex items-center gap-2">
            <i class="fa-solid fa-book-open text-2xl text-yellow-400"></i>
            <h2 class="text-xl font-bold">내 클래스</h2>
          </div>
          <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg" on:click={openNewClassModal}>새 클래스 만들기</button>
        </div>

        <div class="space-y-4">
          {#each myClasses as class_item}
            <div class="bg-white rounded-lg p-6 shadow-md outline outline-2 outline-gray-100">
              <div class="flex items-center justify-between mb-4">
                <div class="flex items-center gap-4">
                  <div class="bg-white rounded-lg p-2 shadow-md outline outline-2 outline-gray-100 flex items-center justify-center w-20 h-16">
                    <img src="/images/python_icon.png" alt="Python" class="w-10 h-10 object-contain" />
                  </div>
                  <div>
                    <h3 class="font-bold">{truncateTitle(class_item.title)}</h3>
                    <p class="text-sm text-gray-500 font-bold">{class_item.time}</p>
                  </div>
                </div>
                <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg" on:click={() => goto('/menu/lecture')}>입장하기</button>
              </div>
              <div class="bg-gray-200 rounded-full h-2">
                <div class="bg-cyan-600 h-2 rounded-full" style="width: {class_item.progress}%"></div>
              </div>
            </div>
          {/each}
        </div>
      </section>
    </div>

    <!-- 오른쪽: 추천 클래스와 클래스 목록 섹션 -->
    <div class="w-full flex-grow space-y-8">
      <!-- 추천 클래스 섹션 -->
      <section class="bg-gray-50 rounded-xl p-6 w-[480px] ml-auto">
        <div class="flex flex-col gap-4">
          <!-- svelte-ignore a11y_click_events_have_key_events -->
          <!-- svelte-ignore a11y_no_static_element_interactions -->
          <div class="flex items-center justify-between cursor-pointer"
               on:click={() => isRecommendedOpen = !isRecommendedOpen}>
            <div class="flex items-center gap-2 pb-3">
              <i class="fa-solid fa-thumbs-up text-2xl text-green-500"></i>
              <h2 class="text-xl font-bold">추천 클래스</h2>
            </div>
            <i class="fas fa-chevron-{isRecommendedOpen ? 'up' : 'down'} text-gray-500"></i>
          </div>
          {#if !isRecommendedOpen}
            <div class="flex gap-2">
              {#each recommendedClasses as class_item, i}
                {#if i < 3}
                  <div class="bg-white rounded-lg p-2 shadow-md outline outline-2 outline-gray-100 flex items-center justify-center w-16 h-16">
                    <img 
                      src="/images/python_icon.png" 
                      alt={class_item.icon} 
                      class="w-10 h-10 object-contain"
                    />
                  </div>
                {/if}
              {/each}
            </div>
          {/if}
        </div>
        <div class="w-full" class:hidden={!isRecommendedOpen}>
          <div class="grid grid-cols-1 gap-4">
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
                  <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg">클래스 정보</button>
                </div>
              </div>
            {/each}
          </div>
        </div>
      </section>

      <!-- 클래스 목록 섹션 -->
      <section class="bg-gray-50 rounded-xl p-6 w-[480px] ml-auto">
        <div class="flex flex-col gap-4">
          <!-- svelte-ignore a11y_click_events_have_key_events -->
          <!-- svelte-ignore a11y_no_static_element_interactions -->
          <div class="flex items-center justify-between cursor-pointer"
               on:click={() => isClassListOpen = !isClassListOpen}>
            <div class="flex items-center gap-2 pb-3">
              <i class="fa-solid fa-list text-2xl text-blue-500"></i>
              <h2 class="text-xl font-bold">클래스 목록</h2>
            </div>
            <i class="fas fa-chevron-{isClassListOpen ? 'up' : 'down'} text-gray-500"></i>
          </div>
          {#if !isClassListOpen}
            <div class="flex gap-2">
              {#each recommendedClasses as class_item, i}
                {#if i < 3}
                  <div class="bg-white rounded-lg p-2 shadow-md outline outline-2 outline-gray-100 flex items-center justify-center w-16 h-16">
                    <img 
                      src="/images/python_icon.png" 
                      alt={class_item.icon} 
                      class="w-10 h-10 object-contain"
                    />
                  </div>
                {/if}
              {/each}
            </div>
          {/if}
        </div>
        <div class="w-full" class:hidden={!isClassListOpen}>
          <div class="grid grid-cols-1 gap-4">
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
                  <button class="bg-indigo-600 text-white px-4 py-2 rounded-lg">클래스 정보</button>
                </div>
              </div>
            {/each}
          </div>
        </div>
      </section>
    </div>
  </div>

  <!-- 새 클래스 만들기 팝업 -->
  {#if isNewClassModalOpen}
    <!-- 모달 배경 -->
    <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <!-- 모달 컨테이너 -->
      <div class="bg-white rounded-lg w-[500px] p-6">
        <!-- 모달 헤더 -->
        <div class="flex items-center justify-between mb-6">
          <h2 class="text-xl font-bold">새 클래스 만들기</h2>
          <!-- svelte-ignore a11y_consider_explicit_label -->
          <button 
            class="text-gray-500 hover:text-gray-700"
            on:click={closeNewClassModal}
          >
            <i class="fas fa-times text-xl"></i>
          </button>
        </div>

        <!-- 모달 콘텐츠 -->
        <div class="space-y-6 max-h-[70vh] overflow-y-auto pr-2">
          <!-- 클래스 명 -->
          <div>
            <label class="block text-sm font-bold mb-2" for="class-title">
              클래스 명
            </label>
            <input
              type="text"
              id="class-title"
              class="w-full p-2 border border-gray-300 rounded-lg"
              placeholder="클래스 명을 입력하세요."
              bind:value={newClassData.title}
            />
          </div>

          <!-- 이미지 업로드 -->
          <div>
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block text-sm font-bold mb-2">
              이미지 업로드
            </label>
            <div class="border-2 border-dashed border-gray-300 rounded-lg p-8 text-center">
              <div class="w-20 h-20 mx-auto mb-4 bg-gray-100 rounded-lg flex items-center justify-center">
                <i class="fas fa-image text-3xl text-gray-400"></i>
              </div>
              <button class="text-sm text-gray-600">
                이미지를 선택해주세요.
              </button>
            </div>
          </div>

          <!-- 클래스 설명 -->
          <div>
            <label class="block text-sm font-bold mb-2" for="class-description">
              클래스 설명
            </label>
            <textarea
              id="class-description"
              class="w-full p-2 border border-gray-300 rounded-lg h-24 resize-none"
              placeholder="클래스 설명을 입력하세요."
              bind:value={newClassData.description}
            ></textarea>
          </div>
          
          <!-- 분야 선택 -->
          <div>
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block text-sm font-bold mb-2">
              분야
            </label>
            
            <!-- 선택된 분야 표시 -->
            {#if newClassData.categories.length > 0}
              <div class="flex flex-wrap gap-2 mb-3">
                {#each newClassData.categories as category}
                  <div class="bg-cyan-100 px-3 py-1 rounded-full flex items-center gap-1">
                    <span>{category}</span>
                    <!-- svelte-ignore a11y_consider_explicit_label -->
                    <button 
                      class="text-gray-500 hover:text-gray-700"
                      on:click={() => removeCategory(category)}
                    >
                      <i class="fas fa-times"></i>
                    </button>
                  </div>
                {/each}
              </div>
            {/if}
            
            <!-- 분야 옵션 -->
            <div class="flex flex-wrap gap-2 mb-3">
              {#each categoryOptions as option}
                <button 
                  class="px-3 py-1 rounded-lg border border-gray-300 hover:bg-gray-50"
                  on:click={() => {
                    if (!newClassData.categories.includes(option)) {
                      newClassData.categories = [...newClassData.categories, option];
                    }
                  }}
                >
                  {option}
                </button>
              {/each}
            </div>
            
            <!-- 직접 입력 -->
            <div class="flex gap-2">
              <input
                type="text"
                class="flex-grow p-2 border border-gray-300 rounded-lg"
                placeholder="직접 입력"
                bind:value={newCategory}
                on:keypress={(e) => e.key === 'Enter' && addCategory()}
              />
              <button 
                class="px-4 py-2 bg-cyan-500 text-white rounded-lg hover:bg-cyan-600"
                on:click={addCategory}
              >
                추가
              </button>
            </div>
          </div>

          <!-- 언어 선택 -->
          <div class="mb-6">
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block text-sm font-bold mb-2">
              언어
            </label>
            <div class="flex flex-wrap gap-2">
              {#each languageOptions as option}
                <button 
                  class="px-4 py-2 rounded-lg border {newClassData.language === option ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                  on:click={() => newClassData.language = option}
                >
                  {option}
                </button>
              {/each}
            </div>
            
            <!-- 기타 선택 시 추가 입력 필드 -->
            {#if newClassData.language === '기타'}
              <div class="mt-2">
                <input
                  type="text"
                  class="w-full p-2 border border-gray-300 rounded-lg"
                  placeholder="언어를 직접 입력하세요."
                  bind:value={newClassData.customLanguage}
                />
              </div>
            {/if}
          </div>

          <!-- 난이도 선택 -->
          <div class="mb-6">
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block text-sm font-bold mb-2">
              난이도
            </label>
            <div class="flex flex-wrap gap-2">
              {#each difficultyOptions as option}
                <button 
                  class="px-4 py-2 rounded-lg border {newClassData.difficulty === option ? 'bg-cyan-500 text-white border-cyan-500' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}"
                  on:click={() => newClassData.difficulty = option}
                >
                  {option}
                </button>
              {/each}
            </div>
            
            <!-- 기타 선택 시 추가 입력 필드 -->
            {#if newClassData.difficulty === '기타'}
              <div class="mt-2">
                <input
                  type="text"
                  class="w-full p-2 border border-gray-300 rounded-lg"
                  placeholder="난이도를 직접 입력하세요."
                  bind:value={newClassData.customDifficulty}
                />
              </div>
            {/if}
          </div>

          <!-- AI 퍼스널리포트 역량 -->
          <div class="mb-6">
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="block text-sm font-bold mb-2">
              AI 퍼스널리포트 역량
            </label>
            
            <!-- 역량 합계 표시 -->
            <div class="mb-3 text-right">
              <span class="font-bold text-sm">
                총합: {newClassData.aiCapabilities.concat(newClassData.customCapabilities).reduce((sum, cap) => sum + cap.value, 0)}%
              </span>
            </div>
            
            <!-- 고정 역량 항목 -->
            <div class="space-y-3 mb-4">
              {#each newClassData.aiCapabilities as capability, i}
                <div class="flex items-center justify-between bg-gray-50 p-3 rounded-lg">
                  <div class="flex items-center gap-2 w-[60%]">
                    {#if i === 0}
                      <i class="fas fa-puzzle-piece text-blue-500"></i>
                    {:else if i === 1}
                      <i class="fas fa-sitemap text-purple-500"></i>
                    {:else if i === 2}
                      <i class="fas fa-lightbulb text-yellow-500"></i>
                    {:else if i === 3}
                      <i class="fas fa-brain text-cyan-500"></i>
                    {:else if i === 4}
                      <i class="fas fa-chart-bar text-green-500"></i>
                    {/if}
                    <span>{capability.name}</span>
                  </div>
                  <div class="flex items-center gap-2">
                    <span class="text-right w-12 font-medium text-cyan-700">{capability.value}%</span>
                    <input 
                      type="range" 
                      min="0" 
                      max="100" 
                      step="5"
                      class="w-32 appearance-none bg-gray-200 rounded-full h-2 accent-cyan-500" 
                      bind:value={capability.value}
                      on:input={(e) => {
                        const oldValue = capability.value;
                        const newValue = parseInt(/** @type {HTMLInputElement} */ (e.target).value);
                        capability.value = newValue;
                        adjustCapabilities(i, true, oldValue, newValue);
                      }}
                    />
                    <!-- 고정 역량에는 삭제 버튼 대신 빈 공간 추가 -->
                    <div class="w-[20px]"></div>
                  </div>
                </div>
              {/each}
            </div>
            
            <!-- 추가된 역량 항목 -->
            {#if newClassData.customCapabilities.length > 0}
              <div class="space-y-3 mb-4">
                {#each newClassData.customCapabilities as capability, i}
                  <div class="flex items-center justify-between bg-gray-50 p-3 rounded-lg">
                    <div class="flex items-center gap-2 w-[60%]">
                      {#if i % 5 === 0}
                        <i class="fas fa-rocket text-orange-500"></i>
                      {:else if i % 5 === 1}
                        <i class="fas fa-code text-indigo-500"></i>
                      {:else if i % 5 === 2}
                        <i class="fas fa-cogs text-red-500"></i>
                      {:else if i % 5 === 3}
                        <i class="fas fa-flask text-pink-500"></i>
                      {:else}
                        <i class="fas fa-shield-alt text-teal-500"></i>
                      {/if}
                      <span>{capability.name}</span>
                    </div>
                    <div class="flex items-center gap-2">
                      <span class="text-right w-12 font-medium text-cyan-700">{capability.value}%</span>
                      <input 
                        type="range" 
                        min="0" 
                        max="100" 
                        step="5"
                        class="w-32 appearance-none bg-gray-200 rounded-full h-2 accent-cyan-500" 
                        bind:value={capability.value}
                        on:input={(e) => {
                          const oldValue = capability.value;
                          const newValue = parseInt(/** @type {HTMLInputElement} */ (e.target).value);
                          capability.value = newValue;
                          adjustCapabilities(i, false, oldValue, newValue);
                        }}
                      />
                      <!-- svelte-ignore a11y_consider_explicit_label -->
                      <button 
                        class="text-gray-500 hover:text-gray-700 w-[20px] text-center"
                        on:click={() => removeCapability(i)}
                      >
                        <i class="fas fa-times"></i>
                      </button>
                    </div>
                  </div>
                {/each}
              </div>
            {/if}
            
            <!-- 새 역량 추가 -->
            <div class="flex gap-2">
              <input
                type="text"
                class="flex-grow p-2 border border-gray-300 rounded-lg"
                placeholder="새 역량 입력"
                bind:value={newCapability}
                on:keypress={(e) => e.key === 'Enter' && addCapability()}
              />
              <button 
                class="px-4 py-2 bg-cyan-500 text-white rounded-lg hover:bg-cyan-600"
                on:click={addCapability}
              >
                추가
              </button>
            </div>
          </div>
        </div>

        <!-- 모달 푸터 -->
        <div class="flex justify-end gap-2 mt-6">
          <button 
            class="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg"
            on:click={closeNewClassModal}
          >
            취소
          </button>
          <button 
            class="px-4 py-2 bg-cyan-500 text-white rounded-lg hover:bg-cyan-600"
            on:click={handleCreateClass}
          >
            만들기
          </button>
        </div>
      </div>
    </div>
  {/if}
</div>