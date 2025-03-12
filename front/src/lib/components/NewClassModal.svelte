<script>
  import { createEventDispatcher } from 'svelte';
  const dispatch = createEventDispatcher();

  export let isOpen = false;

  let newClassData = {
    title: '',
    description: '',
    difficulty: '기초',
    customDifficulty: '',
    language: '파이썬',
    customLanguage: '',
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

  function closeModal() {
    dispatch('close');
    resetForm();
  }

  function handleCreateClass() {
    dispatch('create', newClassData);
    resetForm();
  }

  function resetForm() {
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
    newCapability = '';
  }
</script>

{#if isOpen}
  <!-- 모달 배경 -->
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <!-- 모달 컨테이너 -->
    <div class="bg-white rounded-lg w-[700px] p-6">
      <!-- 모달 헤더 -->
      <div class="flex items-center justify-between mb-6">
        <h2 class="text-xl font-bold">새 클래스 만들기</h2>
        <!-- svelte-ignore a11y_consider_explicit_label -->
        <button 
          class="text-gray-500 hover:text-gray-700"
          on:click={closeModal}
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
          <div class="flex items-center justify-between mb-3">
            <!-- svelte-ignore a11y_label_has_associated_control -->
            <label class="text-sm font-bold">
              AI 퍼스널리포트 역량
            </label>
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
                    <i class="fas fa-project-diagram text-purple-500"></i>
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
                  <input 
                    type="number"
                    min="0"
                    max="100"
                    class="w-16 p-1 border border-gray-300 rounded text-right text-sm"
                    bind:value={capability.value}
                    on:input={(e) => {
                      const oldValue = capability.value;
                      const newValue = Math.min(100, Math.max(0, parseInt(/** @type {HTMLInputElement} */ (e.target).value) || 0));
                      capability.value = newValue;
                      adjustCapabilities(i, true, oldValue, newValue);
                    }}
                  />
                  <span class="text-sm text-gray-600">%</span>
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
                    <input 
                      type="number"
                      min="0"
                      max="100"
                      class="w-16 p-1 border border-gray-300 rounded text-right text-sm"
                      bind:value={capability.value}
                      on:input={(e) => {
                        const oldValue = capability.value;
                        const newValue = Math.min(100, Math.max(0, parseInt(/** @type {HTMLInputElement} */ (e.target).value) || 0));
                        capability.value = newValue;
                        adjustCapabilities(i, false, oldValue, newValue);
                      }}
                    />
                    <span class="text-sm text-gray-600">%</span>
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
          on:click={closeModal}
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