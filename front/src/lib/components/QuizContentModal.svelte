<script lang="ts">
    import rq from "$lib/rq/rq.svelte";

    const id = "QuizContentModal";
    const title = "퀴즈 콘텐츠 추가";

    let dialog: HTMLDialogElement;

    // 퀴즈 콘텐츠 상태
    let contentName = $state('');
    let quizText = $state('');
    let selectedType = $state('객관식'); // 객관식/주관식
    let options = $state(['', '', '', '']); // 객관식 보기 목록
    let correctAnswer = $state(''); // 정답
    let quizExplanation = $state(''); // 퀴즈 해설

    let quizAnswer = $state(''); // 정답

    // 생성된 퀴즈 콘텐츠 리스트
    let createdContents: {
        name: string;
        text: string;
        type: string;
        options?: string[];
        answer: string;
    }[] = $state([]);

    let isSuccess = $state(false);

    // 객관식 보기 추가
    function addOption() {
        options = [...options, ''];
    }

    // 객관식 보기 삭제
    function removeOption(index: number) {
        options = options.filter((_, i) => i !== index);
    }

    // 객관식 보기 업데이트
    function updateOption(index: number, value: string) {
        options = options.map((opt, i) => i === index ? value : opt);
    }

    // 콘텐츠 생성
    function handleCreate() {
        if (!contentName || !quizText || !correctAnswer) return;
        if (selectedType === '객관식' && options.some(opt => !opt)) return;

        createdContents = [
            ...createdContents,
            {
                name: contentName,
                text: quizText,
                type: selectedType,
                ...(selectedType === '객관식' ? { options } : {}),
                answer: correctAnswer
            }
        ];

        // 입력 상태 초기화
        contentName = '';
        quizText = '';
        correctAnswer = '';
        options = [''];
    }

    // 생성된 콘텐츠 삭제
    function removeCreatedContent(idx: number) {
        createdContents = createdContents.filter((_, i) => i !== idx);
    }

    // 저장
    function handleSubmit() {
        // TODO: 실제 퀴즈 저장 로직
        console.log('Created contents:', createdContents);
        
        isSuccess = true;
        setTimeout(() => {
            isSuccess = false;
            rq.hideModal(id);
        }, 2000);
    }
</script>

<dialog bind:this={dialog} id={id} class="modal modal-content fixed inset-0 flex items-center justify-center z-50 select-none">
    <div class="bg-white p-6 rounded-lg shadow-lg w-full min-w-[800px] max-w-[1200px] max-h-[90vh] overflow-y-auto">
        <!-- 모달 헤더 -->
        <div class="flex justify-between items-center mb-4 border-b pb-2">
            <h2 class="text-xl font-semibold">{title}</h2>
            <button 
                class="text-gray-500 hover:text-gray-700" 
                aria-label="닫기"
                onclick={() => rq.hideModal(id)}
            >
                <i class="fa-solid fa-xmark text-2xl"></i>
            </button>
        </div>

        <!-- 퀴즈 입력 섹션 -->
        <div class="space-y-4">
            <!-- 콘텐츠명 -->
            <div>
                <label class="block text-sm mb-2">콘텐츠 명</label>
                <input 
                    type="text"
                    bind:value={contentName}
                    placeholder="콘텐츠명을 입력해주세요."
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>

            <!-- 퀴즈 문제 -->
            <div>
                <label class="block text-sm mb-2">퀴즈 문제</label>
                <textarea 
                    bind:value={quizText}
                    placeholder="퀴즈 문제를 입력해주세요."
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                />
            </div>
            <!-- 문제 유형 -->
            <div>
                <label class="block text-sm mb-2">정답 유형</label>
                <div class="flex gap-2">
                    <button 
                        class="w-32 px-4 py-2 rounded-lg font-bold border {selectedType === '객관식' ? 'border-2 border-[#574DDE] text-[#574DDE]' : 'border-gray-300'}"
                        onclick={() => selectedType = '객관식'}
                    >
                        객관식
                    </button>
                    <button
                        class="w-32 px-4 py-2 rounded-lg font-bold border {selectedType === '주관식' ? 'border-2 border-[#574DDE] text-[#574DDE]' : 'border-gray-300'}"
                        onclick={() => selectedType = '주관식'}
                    >
                        주관식
                    </button>
                </div>
            </div>

            <!-- 보기 (객관식인 경우) -->
            {#if selectedType === '객관식'}
                <div class="space-y-2">
                    <label class="block text-sm mb-2">보기</label>
                    {#each options as option, i}
                        <div class="flex gap-2">
                            <input 
                                type="text"
                                value={option}
                                oninput={(e) => updateOption(i, (e.target as HTMLInputElement).value)}
                                placeholder={`보기 ${i + 1}`}
                                class="flex-1 px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {#if i > 0}
                                <button 
                                    onclick={() => removeOption(i)}
                                    class="px-3 py-2 text-red-500"
                                >
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            {:else}
                                <div class="w-[38px]"></div>
                            {/if}
                        </div>
                    {/each}
                    <button 
                        onclick={addOption}
                        class="px-4 py-2 rounded-lg border bg-[#5DDBDB] text-white hover:bg-[#4DCDCD]"
                    >
                        + 보기 추가
                    </button>
                </div>
                
                <!-- 정답 -->
                <div>
                    <label class="block text-sm mb-2">정답</label>
                    <div class="flex gap-2">
                        {#each options as _, i}
                            <button
                                class="w-12 h-12 rounded-lg border {correctAnswer === (i+1).toString() ? 'border-4 border-[#574DDE]' : ''}"
                                onclick={() => correctAnswer = (i+1).toString()}
                            >
                                {i + 1}
                            </button>
                        {/each}
                    </div>
                </div>

                <!-- 퀴즈 해설 -->
                <div>
                    <label class="block text-sm mb-2">퀴즈 해설</label>
                    <textarea 
                        bind:value={quizExplanation}
                        placeholder="퀴즈 해설을 입력해주세요."
                        class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                    />
                </div>
            {:else}
                <!-- 정답 -->
                <div>
                    <label class="block text-sm mb-2">정답</label>
                    <textarea 
                        bind:value={quizAnswer}
                        placeholder="퀴즈의 정답을 입력해주세요."
                        class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                    />
                </div>
            
                <!-- 힌트 1단계 -->
                <div>
                    <label class="block text-sm mb-2">힌트 1단계</label>
                    <textarea 
                        placeholder="문제 해결을 위한 1단계 힌트를 입력하세요."
                        class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                    />
                </div>
            
                <!-- 힌트 2단계 -->
                <div>
                    <label class="block text-sm mb-2">힌트 2단계</label>
                    <textarea 
                        placeholder="문제 해결을 위한 2단계 힌트를 입력하세요."
                        class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 h-32"
                    />
                </div>
            {/if}
            
            <!-- 생성된 콘텐츠 리스트 -->
            {#if createdContents.length > 0}
                <div class="mt-4 space-y-4">
                    {#each createdContents as item, idx}
                        <div class="flex items-center gap-4 border p-2 rounded-md">
                            <div class="flex items-center gap-2">
                                <i class="fa-solid fa-bars text-gray-400"></i>
                                <i class="fa-solid fa-question-circle text-purple-500 text-xl"></i>
                            </div>

                            <div class="flex-1">
                                <div class="font-semibold">{item.name}</div>
                                <div class="text-sm text-gray-600">
                                    <div>문제: {item.text}</div>
                                    <div>유형: {item.type}</div>
                                    {#if item.type === '객관식'}
                                        <div>보기: {item.options?.join(', ')}</div>
                                    {/if}
                                    <div>정답: {item.answer}</div>
                                </div>
                            </div>

                            <button 
                                class="text-red-500 font-bold"
                                onclick={() => removeCreatedContent(idx)}
                            >
                                X
                            </button>
                        </div>
                    {/each}
                </div>
            {/if}

            <!-- 생성 성공 모달 -->
            {#if isSuccess}
                <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div class="bg-white p-6 rounded-lg shadow-lg text-center">
                        <i class="fa-solid fa-check text-4xl text-green-500 mb-4"></i>
                        <p class="text-xl">새로운 퀴즈 콘텐츠가 생성되었습니다!</p>
                    </div>
                </div>
            {/if}
        </div>

        <!-- 모달 하단 버튼 -->
        <div class="flex justify-end gap-2 mt-6">
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