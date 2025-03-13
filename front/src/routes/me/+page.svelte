<script lang="ts">
    import SideMenu from "./sideMenu.svelte";

    let editor: any;

</script>

<div class="w-full h-full flex flex-row overflow-hidden">
    <div class="w-[250px] min-w-[200px] sideMenuContainer">
        <SideMenu />
    </div>
    <div class="flex-1 flex min-w-[600px]">
        <div class="w-full h-full contentBody flex flex-col items-center">
            <div class="w-full max-w-[1000px] h-full flex flex-col items-center px-4 py-8">
                <div class="flex justify-start items-center border-b border-gray-300 w-full pb-6">
                    <h1 class="text-2xl font-bold">내 프로필</h1>
                </div>
                <div class="w-full h-full flex flex-col rounded-xl outline-1 outline-gray-200 shadow-md mb-4 bg-white mt-8">
                    <div class="w-full h-full flex flex-row border-b-2 border-gray-200">
                        <div class="w-[200px] min-w-[200px] h-full pl-8 py-[20px] flex flex-col items-start bg-[#E5F8F7] text-md font-bold">
                            <div class="h-[210px] mt-4 flex justify-start">
                                <div>프로필 이미지</div>
                            </div>
                            <div class="flex flex-col items-start gap-9 mt-2 pt-[20px]">
                                <div>닉네임</div>
                                <div>이메일</div>
                                <div>비밀번호</div>
                                <div>전화번호</div>
                                <div>소셜연동</div>
                            </div>
                        </div>
                        <div class="w-full h-full flex flex-col items-start">
                            <div class="w-full h-[280px] flex flex-col items-start p-[20px] gap-6 border-b-2 border-gray-200">
                                <div class="w-36 rounded-full">
                                    <img
                                        class="w-full h-full"
                                        alt="프로필 이미지"
                                        src="/images/icon_img.png" />
                                </div>
                                <button class="btn btn-primary">
                                    사진 변경 <i class="fa-solid fa-pen-to-square"></i>
                                </button>
                            </div>
                            <div class="w-full h-full flex flex-col items-start p-[20px] gap-6">
                                <div>
                                    <input 
                                        type="text" 
                                        placeholder="닉네임을 입력해주세요."
                                        value="홍길동" 
                                        class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
                                    />
                                </div>
        
                                <div>
                                    <div>
                                        abcd123@gmail.com
                                    </div>
                                </div>
        
                                <div>
                                    <input 
                                        type="password" 
                                        placeholder="비밀번호 수정"
                                        class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
                                    />
                                </div>
        
                                <div>
                                    <input 
                                        type="text" 
                                        placeholder="전화번호를 입력해주세요."
                                        value="010 1234 4321" 
                                        class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
                                    />
                                </div>
        
                                <div>
                                    <div class="flex items-center gap-2">
                                        <div class="flex items-center gap-2 px-4 py-2 bg-white rounded-lg border border-gray-200">
                                            <img src="/images/naver_logo.png" alt="Google" class="w-5 h-5"/>
                                            <span class="text-sm font-medium">Naver 계정 연동됨</span>
                                        </div>
                                        <button class="px-3 py-2 text-sm text-red-500 hover:text-red-600 font-medium">
                                            연동해제
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="w-full flexbox-row py-4">
                        <button class="btn w-36 h-12 bg-[#4FC0D3] rounded-3xl text-white text-lg">
                            설정 저장
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>