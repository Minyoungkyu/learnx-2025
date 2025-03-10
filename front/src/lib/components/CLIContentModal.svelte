<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    const id = "CLIContentModal";
    const title = "CLI 콘텐츠 추가";

    let dialog: HTMLDialogElement;
    
    // 탭 관련 상태
    let activeTab = $state('문제정보'); // '문제정보' | '채점정보' | '힌트'
    const tabs = ['문제정보', '채점정보', '힌트'];

    // 문제정보 탭 상태
    let contentName = $state('');
    let problemDescription = $state('');
    let expectedOutput = $state('');
    let starterCode = $state('');
    let difficultyLevel = $state(1);

    // 채점정보 탭 상태
    let testCaseInputs = $state<string[]>(['', '', '']);
    let testCaseOutputs = $state<string[]>(['', '', '']);
    let keywords = $state('');

    // 힌트 탭 상태
    let hint1Description = $state('');
    let hint1Example = $state('');
    let hint2Description = $state('');
    let hint2Example = $state('');
    let hint3Description = $state('');
    let hint3Example = $state('');

    // 태그 상태
    let selectedTag = $state('');
    const availableTags = [
        '조건문', '리스트', '함수', '합수', '파일입출력', 'for 문'
    ];

    function handleSubmit() {
        // TODO: 실제 저장 로직
        console.log({
            contentName,
            problemDescription,
            expectedOutput,
            starterCode,
            difficultyLevel,
            selectedTag
        });
        
        rq.hideModal(id);
    }

    function toggleTag(tag: string) {
        if (selectedTag === tag) {
            selectedTag = '';
        } else {
            selectedTag = tag;
        }
    }

    // 테스트케이스 추가 함수
    function addTestCaseInput() {
        testCaseInputs = [...testCaseInputs, ''];
    }

    function addTestCaseOutput() {
        testCaseOutputs = [...testCaseOutputs, ''];
    }
</script>

<dialog bind:this={dialog} id={id} class="modal modal-content fixed inset-0 flex items-center justify-center z-50 select-none">
    <div class="bg-white rounded-lg shadow-lg w-full min-w-[800px] max-w-[1200px] max-h-[90vh] overflow-y-auto">
        <!-- 모달 헤더 -->
        <div class="flex justify-between items-center p-6 border-b">
            <h2 class="text-xl font-semibold">{title}</h2>
            <button 
                class="text-gray-500 hover:text-gray-700" 
                aria-label="닫기"
                onclick={() => rq.hideModal(id)}
            >
                <i class="fa-solid fa-xmark text-2xl"></i>
            </button>
        </div>

        <!-- 콘텐츠명 입력 -->
        <div class="p-6 border-b">
            <label class="block text-sm mb-2">콘텐츠 명</label>
            <input 
                type="text"
                bind:value={contentName}
                placeholder="콘텐츠 명을 입력해주세요."
                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>

        <!-- 탭 네비게이션 -->
        <div class="flex border-b bg-gray-100">
            {#each tabs as tab}
                <button 
                    class="px-6 py-3 font-medium {activeTab === tab ? 'bg-white border-t-2 border-t-blue-500' : 'text-gray-600'}"
                    onclick={() => activeTab = tab}
                >
                    {tab}
                </button>
            {/each}
        </div>

        <!-- 탭 콘텐츠 -->
        <div class="p-6">
            {#if activeTab === '문제정보'}
                <div class="space-y-6">
                    <!-- 실습 문제 -->
                    <div>
                        <label class="block text-sm mb-2">실습 문제</label>
                        <textarea 
                            bind:value={problemDescription}
                            placeholder="해결해야 할 프로그래밍 과제를 설명하는 텍스트를 입력해주세요.
                            
                            문제상황, 요구사항, 입력/출력 형식, 제약조건 등을 포함해서 입력해주세요."
                            class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                        />
                    </div>

                    <!-- 모범 답안 -->
                    <div>
                        <label class="block text-sm mb-2">모범 답안</label>
                        <textarea 
                            bind:value={expectedOutput}
                            placeholder="문제를 완벽히 해결한느 코드의 정답 예시를 입력해주세요.
                            
                            정확하고 효율적인 코드 구현으로, 채점의 기준이 됩니다."
                            class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                        />
                    </div>

                    <!-- 스켈레톤 코드 -->
                    <div>
                        <label class="block text-sm mb-2">스켈레톤 코드</label>
                        <textarea 
                            bind:value={starterCode}
                            placeholder="학생이 문제를 풀 때 제공받는 기초 코드 틀을 입력해주세요.
                            
                            함수 정의, 클래스 구조, 기본 로직 등이 포함되어, 학생이 코드를 작성하기 쉽도록 도와줍니다."
                            class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                        />
                    </div>

                    <!-- 문제 난이도 -->
                    <div>
                        <label class="block text-sm mb-2">문제 난이도</label>
                        <div class="flex gap-2">
                            {#each Array(5) as _, i}
                                <button 
                                    class="w-8 h-8 rounded border {difficultyLevel === i + 1 ? 'border-4 border-[#574DDE]' : ''}"
                                    onclick={() => difficultyLevel = i + 1}
                                >
                                    {i + 1}
                                </button>
                            {/each}
                        </div>
                    </div>

                    <!-- 태그 선택 -->
                    <div>
                        <label class="block text-sm mb-2">태그 유형</label>
                        <div class="grid grid-cols-3 gap-2">
                            {#each availableTags as tag}
                                <button 
                                    class="px-4 py-2 rounded-lg  
                                           {selectedTag === tag ? 'border-2 border-[#574DDE] text-[#574DDE]' : 'border'}"
                                    onclick={() => selectedTag = tag}
                                >
                                    {tag}
                                </button>
                            {/each}
                        </div>
                    </div>
                </div>
            {:else if activeTab === '채점정보'}
                <!-- TODO: 채점정보 탭 내용 -->
                <div class="space-y-6">
                    <!-- 테이터케이스 입력 섹션 -->
                    <div>
                        <div class="flex justify-between items-center mb-2">
                            <label class="block text-sm">데이터케이스 입력</label>
                            <button 
                                onclick={addTestCaseInput}
                                class="px-2 py-1 text-sm rounded-lg bg-[#5DDBDB] text-white hover:bg-[#4DCDCD]"
                            >
                                추가+
                            </button>
                        </div>
                        {#each Array(testCaseInputs.length) as _, i}
                            <textarea 
                                placeholder="해결해야 할 프로그래밍 과제를 설명하는 텍스트를 입력해주세요."
                                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-20 mb-2"
                            />
                        {/each}
                    </div>
            
                    <!-- 테이터케이스 출력 섹션 -->
                    <div>
                        <div class="flex justify-between items-center mb-2">
                            <label class="block text-sm">데이터케이스 출력</label>
                            <button 
                                onclick={addTestCaseInput}
                                class="px-2 py-1 text-sm rounded-lg bg-[#5DDBDB] text-white hover:bg-[#4DCDCD]"
                            >
                                추가+
                            </button>
                        </div>
                        {#each Array(testCaseInputs.length) as _, i}
                            <textarea 
                                placeholder="테스트케이스 입력에 대해 정확한 출력 결과를 입력해주세요."
                                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-20 mb-2"
                            />
                        {/each}
                    </div>
            
                    <!-- 키워드 섹션 -->
                    <div>
                        <label class="block text-sm mb-2">키워드</label>
                        <textarea 
                            placeholder="해결해야 할 프로그래밍 과제를 설명하는 텍스트를 입력해주세요.
                            
                            문제상황, 요구사항, 입력/출력 형식, 제약조건 등을 포함해서 입력해주세요."
                            class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                        />
                    </div>
                </div>
            {:else}
                <!-- 힌트 탭 내용 -->
                <div class="space-y-6">
                    <!-- 힌트 1단계 -->
                    <div>
                        <label class="block text-sm mb-2">힌트 1단계</label>
                        <div class="space-y-2">
                            <textarea 
                                bind:value={hint1Description}
                                placeholder="문제 해결 방향성을 제공하는 최소한의 단서를 입력하세요.
                                
                                예) 출력은 영어로 'print'를 사용합니다."
                                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                            />
                        </div>
                    </div>

                    <!-- 힌트 2단계 -->
                    <div>
                        <label class="block text-sm mb-2">힌트 2단계</label>
                        <div class="space-y-2">
                            <textarea 
                                bind:value={hint2Description}
                                placeholder="문제 해결 과정에서 참고할 구체적 도움말을 입력하세요.
                                
                                예) 문자열은 큰 따옴표 또는 작은 따옴표로 묶어야 합니다."
                                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                            />
                        </div>
                    </div>

                    <!-- 힌트 3단계 -->
                    <div>
                        <label class="block text-sm mb-2">힌트 3단계</label>
                        <div class="space-y-2">
                            <textarea 
                                bind:value={hint3Description}
                                placeholder="문제 해결을 위해 사용할 특정 문법이나 코드 조각의 해답에 가까운 정보를 입력하세요.
                                
                                예) 숫자는 print(10) 과 같이 출력합니다."
                                class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                            />
                        </div>
                    </div>
                </div>
            {/if}
        </div>

        <!-- 모달 푸터 -->
        <div class="flex justify-end gap-2 p-6 border-t">
            <button 
                class="px-4 py-2 rounded-lg border hover:bg-gray-100"
                onclick={() => rq.hideModal(id)}
            >
                취소
            </button>
            <button 
                class="px-4 py-2 rounded-lg bg-[#4FC0D3] text-white hover:bg-[#3AA0B3]"
                onclick={handleSubmit}
            >
                저장
            </button>
        </div>
    </div>
</dialog> 

<style>
    textarea::placeholder {
      white-space: pre-line;
    }
</style>