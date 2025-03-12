<script lang="ts">
  import rq from "$lib/rq/rq.svelte";

  let agreed = $state(false);
  let agreement2 = $state(false);
</script>

<div class="w-full h-screen flexbox-col bg-gray-100">
  <div class="flex items-center gap-2 mb-8 cursor-pointer" onclick={() => rq.goTo('/')}>
    <img src="/images/learnx_logo.png" alt="LearnX" class="w-38 h-8" />
  </div>

  <div class="min-w-[600px] mx-auto p-5 bg-white rounded-2xl shadow-md">
    <h1 class="text-center text-2xl mb-2.5 font-bold">런엑스 회원가입</h1>
    <p class="text-center text-gray-500 mb-8 font-bold text-sm">런엑스 이용을 위해 아래 약관과 정책을 확인해 주세요.</p>
  
    <div class="flexbox-col gap-2">
      <label for="agreement" class="w-full flex items-center p-2 border rounded-lg gap-2 {agreed ? 'bg-indigo-200' : 'bg-[#EFEFFF]'} cursor-pointer text-md font-bold">
        <input id="agreement" type="checkbox" bind:checked={agreed} class="checkbox checkbox-xs" />
        <span>런엑스 이용약관에 동의합니다. <span class="text-[#4A90E2]">(필수)</span></span>
      </label>
      <div class="bg-[#F5F5F5] p-4 rounded-lg text-sm w-full h-[100px]">
        런엑스 서비스 이용약관
        <div class="mt-2.5 text-gray-600">제 1조 목적</div>
      </div>
    </div>

    <div class="flexbox-col gap-2 mt-10">
      <label for="agreement2" class="w-full flex items-center p-2 border rounded-lg gap-2 {agreement2 ? 'bg-indigo-200' : 'bg-[#EFEFFF]'} cursor-pointer text-md font-bold">
        <input id="agreement2" type="checkbox" bind:checked={agreement2} class="checkbox checkbox-xs" />
        <span>개인정보 수집 및 이용에 동의합니다. <span class="text-[#4A90E2]">(필수)</span></span>
      </label>
      <div class="bg-[#F5F5F5] p-4 rounded-lg text-sm w-full h-[100px]">
        [개인정보 수집 및 이용 동의]
        <div class="mt-2.5 text-gray-600">(주)TMD는 다음과 같이 개인정보를 수집 및 이용합니다.</div>
      </div>
    </div>
  
    <button 
      onclick={() => rq.goTo('/member/join2')}
      class="w-full mt-10 py-2 rounded-lg text-white text-base cursor-pointer transition-colors duration-200 {agreed && agreement2 ? 'bg-indigo-600 hover:bg-indigo-700' : 'bg-gray-400 cursor-not-allowed'}" 
      disabled={!agreed || !agreement2}
    >
      다음
    </button>
  </div>
</div>
