<script lang="ts">
  import { goto } from '$app/navigation';
  import { onMount } from 'svelte';
  import NewClassModal from '$lib/components/NewClassModal.svelte';

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
  let newClassData: {
    title: string;
    description: string;
    difficulty: string;
    customDifficulty: string;
    language: string;
    customLanguage: string;
    categories: string[];
    aiCapabilities: Array<{ name: string; value: number }>;
    customCapabilities: Array<{ name: string; value: number }>;
  } = {
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
  function removeCategory(category: string) {
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
  function removeCapability(index: number) {
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
  function adjustCapabilities(index: number, isFixed: boolean, oldValue: number, newValue: number) {
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

  function handleCreateClass(event: CustomEvent) {
    const newClassData = event.detail;
    console.log('새 클래스 데이터:', newClassData);
    closeNewClassModal();
  }

  /**
   * @param {string} title
   */
  function truncateTitle(title: string, maxLength = 25) {
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
          <button class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg" on:click={openNewClassModal}>새 클래스 만들기</button>
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
                <button class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg" on:click={() => goto('/menu/lecture')}>입장하기</button>
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
                    <div class="w-[190px]">
                      <h3 class="font-bold">{truncateTitle(class_item.title)}</h3>
                      <p class="text-sm text-gray-500 font-bold">{class_item.time}</p>
                    </div>
                  </div>
                  <button 
                    class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg"
                    on:click={() => goto('/menu/main')}
                  >
                    클래스 정보
                  </button>
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
                    <div class="w-[190px]">
                      <h3 class="font-bold">{truncateTitle(class_item.title)}</h3>
                      <p class="text-sm text-gray-500 font-bold">{class_item.time}</p>
                    </div>
                  </div>
                  <button 
                    class="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg"
                    on:click={() => goto('/menu/main')}
                  >
                    클래스 정보
                  </button>
                </div>
              </div>
            {/each}
            <!-- 클래스 목록 더보기 버튼 -->
            <div class="flex justify-center mt-4">
              <button 
                class="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded-lg flex items-center gap-2"
                on:click={() => goto('/class/search')}
              >
                <span>더보기</span>
                <i class="fas fa-plus text-sm"></i>
              </button>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>

  <!-- 새 클래스 만들기 팝업 -->
  <NewClassModal 
    isOpen={isNewClassModalOpen}
    on:close={closeNewClassModal}
    on:create={handleCreateClass}
  />
</div>