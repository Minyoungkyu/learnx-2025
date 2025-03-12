<script lang="ts">
  import rq from "$lib/rq/rq.svelte";

  let userId = $state("");
  let password = $state("");
  let passwordConfirm = $state("");
  let email = $state("");

  let checked1 = $state(false);
  let checked2 = $state(false);

  function handleCheck(checkboxNum: number) {
    if (checkboxNum === 1) {
      checked1 = true;
      checked2 = false;
    } else {
      checked1 = false; 
      checked2 = true;
    }
  }
</script>

<div class="w-full h-screen flexbox-col bg-gray-100">
  <div class="flex items-center gap-2 mb-8 cursor-pointer" onclick={() => rq.goTo('/')}>
    <img src="/images/learnx_logo.png" alt="LearnX" class="w-38 h-8" />
  </div>

  <div class="min-w-[600px] mx-auto px-28 py-10 bg-white rounded-2xl shadow-md">
    <h1 class="text-center text-2xl mb-10 font-bold">런엑스 회원가입</h1>

    <div class="flex flex-col gap-2 mb-10 border-y py-10">
      <div class="flex items-center">
        <label class="text-md font-bold w-32">회원유형</label>
        <div class="flex gap-4">
          <label for="checked1" class="w-32 flex items-center p-2 border rounded-lg gap-2 {checked1 ? 'bg-indigo-200' : 'bg-[#EFEFFF]'} cursor-pointer text-md font-bold">
            <input id="checked1" type="checkbox" checked={checked1} onclick={() => handleCheck(1)} class="checkbox checkbox-xs" />
            <span>일반회원</span>
          </label>
          <label for="checked2" class="w-32 flex items-center p-2 border rounded-lg gap-2 {checked2 ? 'bg-indigo-200' : 'bg-[#EFEFFF]'} cursor-pointer text-md font-bold">
            <input id="checked2" type="checkbox" checked={checked2} onclick={() => handleCheck(2)} class="checkbox checkbox-xs" />
            <span>선생님회원</span>
          </label>
        </div>
      </div>
    </div>

    <div class="flex flex-col gap-4">
      <div class="flex items-center">
        <label class="text-md font-bold w-32">아이디</label>
        <input 
          type="text" 
          bind:value={userId} 
          placeholder="아이디를 입력해주세요." 
          class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
        />
      </div>

      <div class="flex items-center">
        <label class="text-md font-bold w-32">비밀번호</label>
        <input 
          type="password" 
          bind:value={password} 
          placeholder="비밀번호를 입력해주세요." 
          class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
        />
      </div>

      <div class="flex items-center">
        <label class="text-md font-bold w-32">비밀번호 확인</label>
        <input 
          type="password" 
          bind:value={passwordConfirm} 
          placeholder="비밀번호를 다시 한 번 입력해주세요." 
          class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
        />
      </div>

      <div class="flex items-center">
        <label class="text-md font-bold w-32">이메일</label>
        <div class="flex flex-1 gap-2">
          <input 
            type="email" 
            bind:value={email} 
            placeholder="이메일을 입력해주세요." 
            class="flex-1 px-3 py-2 border rounded text-sm font-bold focus:outline-none focus:border-gray-400" 
          />
        </div>
      </div>

      <button class="w-full mt-6 py-2.5 bg-gray-300 text-white rounded font-medium">
        가입하기
      </button>
    </div>
  </div>
</div>
