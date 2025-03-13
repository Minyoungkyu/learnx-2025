<script lang="ts">
    import SideMenu from "./sideMenu.svelte";
    import { setupAceEditor } from "$lib/aceEdit/aceEditorSetup.svelte";
    import { onMount } from "svelte";
    import Resizer from "$lib/components/Resizer.svelte";
    import rq from '$lib/rq/rq.svelte';
    import ExcalidrawComponent from '$lib/components/ExcalidrawComponent.svelte';
    let editor: any;

    onMount(() => {
        editor = setupAceEditor("editor");
    });
</script>

<div class="w-full h-screen flex flex-row overflow-hidden">
    <ExcalidrawComponent/>
    <div class="w-[250px] min-w-[200px] sideMenuContainer bg-[#1E273B]">
        <SideMenu />
    </div>
    <div class="flex-1 flex min-w-[600px]">
        <div class="w-full h-full contentBody flex flex-col bg-[#323436]">
            <div class="w-full h-full flex flex-col text-white font-bold">
                <div class="flex justify-between items-end w-full h-[60px] bg-[#323436]">
                    <div class="flex h-full items-end">
                        <div class="p-2 bg-[#1E273B] text-white rounded-t-lg h-[45px] w-[250px] flex items-center justify-between border-t border-l border-r border-gray-700">
                            <span>04 실습1: 숫자5를 출력</span>
                            <span class="flex item-center"><i class="fa-solid fa-xmark"></i></span>
                        </div>
                    </div>
                </div>
                <div class="w-full h-full flex bg-[#1E273B]">
                    <div id="cli-side" style="width: 25%; min-width: 100px;" class="p-4 flex flex-col justify-center border-r border-gray-700">
                        <div class="flex flex-col gap-2 pb-4">
                            <span class="text-2xl">문제 설명</span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                📢 [퀘스트: 숫자 비교 마스터]
                            </span>
                            <span class="text-[#90B9E8] text-md">
                                🛡️ 당신의 미션은 두 숫자를 비교하는 것입니다!
                            </span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                두 숫자가 같다면 → 1 점수를 획득!
                            </span>
                            <span class="text-[#90B9E8] text-md">
                                두 숫자가 다르면 → -1 패널티!
                            </span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                🔥 미션 예제
                            </span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                🚀 플레이어 입력: num1 = 42, num2 = 42
                            </span>
                            <span class="text-[#90B9E8] text-md">
                                ✅ 결과: 1 (두 숫자가 같음!)
                            </span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                🚀 플레이어 입력: num1 = 7, num2 = 13
                            </span>
                            <span class="text-[#90B9E8] text-md">
                                ❌ 결과: -1 (두 숫자가 다름!)
                            </span>
                            <br>
                            <span class="text-[#90B9E8] text-md">
                                💡 if-else를 활용해보세요!
                            </span>
                            <span class="text-[#90B9E8] text-md">
                                💡 더 간결한 한 줄짜리 코드도 도전해 볼 수 있어요!
                            </span>
                        </div>
                    </div>
                    <Resizer direction="horizontal" />
                    <div id="cli-body" style="flex: 1; min-width: 400px;" class="h-full flex flex-col">
                        <div class="editor-area flex-1 relative pt-4">
                            <div id="editor" class="w-full h-[96%]"></div>
                        </div>
                        <Resizer direction="vertical" />
                        <div class="console-area border-t border-gray-700" style="height: 40%; min-height: 10px;">
                            <div class="w-full p-4 border-b border-gray-700">
                                <span>실행 결과</span>
                            </div>
                            <div class="w-full h-full p-4 font-light text-sm">
                                <span>실행결과가 여기에 표시됩니다.</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex justify-between items-center w-full h-[80px] border-t border-gray-700 bg-[#1E273B]">
                    <div class="flex items-center gap-2 px-4">
                        <button class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition-colors">
                            힌트 보기
                        </button>
                    </div>
                    <div class="flex items-center gap-6 px-4">
                        <button class="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors">
                            초기화
                        </button>
                        <button class="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition-colors">
                            실행
                        </button>
                        <button class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition-colors">
                            제출
                        </button>
                    </div>
                </div>
                <div class="flex justify-center items-center w-full h-[60px] border-t border-gray-700">
                    <div class="flex items-center gap-4">
                        <button class="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 transition-colors">
                            <i class="fa-solid fa-angle-left"></i> 이전
                        </button>
                        <div class="flex items-center gap-2">
                            <span class="text-white">1/10</span>
                        </div>
                        <button class="px-4 py-2 text-white rounded bg-[#4FC0D3] hover:bg-[#45AAB9] transition-colors" onclick={() => rq.goTo('/learn2')}>
                            다음 <i class="fa-solid fa-angle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
             
        </div>
    </div>
</div>