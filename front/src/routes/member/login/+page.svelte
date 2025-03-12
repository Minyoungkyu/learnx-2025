<script lang="ts">
  import rq from "$lib/rq/rq.svelte";
</script>

<div class="w-full h-screen flexbox-col bg-gray-100">
  <div class="flex items-center gap-2 mb-8 cursor-pointer" onclick={() => rq.goTo('/')}>
    <img src="/images/learnx_logo.png" alt="LearnX" class="w-38 h-8" />
  </div>

  <div class="min-w-[400px] mx-auto p-8 bg-white rounded-2xl shadow-md">
    <h1 class="text-center text-2xl mb-2.5 font-bold">로그인</h1>
    
    <div class="bg-[#848688] rounded-lg p-4 mb-6">
      <div class="flex items-center gap-2">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-200" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd" />
        </svg>
        <span class="text-white text-sm">내 컴퓨터가 아니라면 사용 후 반드시 로그아웃해세요.</span>
      </div>
    </div>

    <div class="flexbox-col gap-4">
      <input 
        type="text" 
        placeholder="아이디" 
        class="w-full p-3 border rounded-lg focus:outline-none focus:border-indigo-500"
      />
      <div class="relative w-full">
        <input 
          type="password" 
          placeholder="비밀번호" 
          class="w-full p-3 border rounded-lg focus:outline-none focus:border-indigo-500"
        />
      </div>
    </div>

    <button class="w-full mt-6 py-2.5 bg-[#5DDBDB] text-white rounded font-medium">
      아이디로 로그인
    </button>

    <div class="mt-4 flex justify-center items-center text-sm border-b pb-4">
      <a href="/member/join" class="text-gray-600">회원가입</a>
      <span class="text-gray-600">&nbsp; | &nbsp;</span>
      <a href="#" class="text-gray-600">계정찾기</a>
    </div>

    <div class="w-full flex flex-col gap-2 mt-4">
      <button class="btn bg-[#FEE502] text-[#181600] border-[#f1d800]">
        <svg aria-label="Kakao logo" width="16" height="16" viewBox="0 0 512 512" xmlns="http://www.w3.org/2000/svg"><path fill="#181600" d="M255.5 48C299.345 48 339.897 56.5332 377.156 73.5996C414.415 90.666 443.871 113.873 465.522 143.22C487.174 172.566 498 204.577 498 239.252C498 273.926 487.174 305.982 465.522 335.42C443.871 364.857 414.46 388.109 377.291 405.175C340.122 422.241 299.525 430.775 255.5 430.775C241.607 430.775 227.262 429.781 212.467 427.795C148.233 472.402 114.042 494.977 109.892 495.518C107.907 496.241 106.012 496.15 104.208 495.248C103.486 494.706 102.945 493.983 102.584 493.08C102.223 492.177 102.043 491.365 102.043 490.642V489.559C103.126 482.515 111.335 453.169 126.672 401.518C91.8486 384.181 64.1974 361.2 43.7185 332.575C23.2395 303.951 13 272.843 13 239.252C13 204.577 23.8259 172.566 45.4777 143.22C67.1295 113.873 96.5849 90.666 133.844 73.5996C171.103 56.5332 211.655 48 255.5 48Z"></path></svg>
        카카오 로그인
      </button>
  
      <button class="btn bg-[#03C75A] text-white border-[#00b544]">
        <img src="/images/naver_logo.png" alt="네이버 로그인" class="w-[30px] h-[30px]" />
        네이버 로그인
      </button>
  
      <button class="btn bg-white text-black border-[#e5e5e5]">
        <svg aria-label="Google logo" width="16" height="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><g><path d="m0 0H512V512H0" fill="#fff"></path><path fill="#34a853" d="M153 292c30 82 118 95 171 60h62v48A192 192 0 0190 341"></path><path fill="#4285f4" d="m386 400a140 175 0 0053-179H260v74h102q-7 37-38 57"></path><path fill="#fbbc02" d="m90 341a208 200 0 010-171l63 49q-12 37 0 73"></path><path fill="#ea4335" d="m153 219c22-69 116-109 179-50l55-54c-78-75-230-72-297 55"></path></g></svg>
        Login with Google
      </button>
    </div>
  </div>
</div>
