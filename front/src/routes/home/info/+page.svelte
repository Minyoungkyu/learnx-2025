<script lang="ts">
  import rq from "$lib/rq/rq.svelte";

  let points = 300;
  let lastUpdated = '25.02.03';
</script>

<div class="max-w-[1000px] w-full mx-auto p-5">
  <!-- 프로필 섹션 -->
  <div class="bg-gray-100 rounded-2xl p-5 mb-10">
    <div class="flex justify-between items-center border-b">
      <div class="flex items-center gap-5">
        <img src="/images/icon_img.png" alt="프로필 이미지" class="w-20 h-20 rounded-full" />
        <div>
          <h2 class="text-2xl text-[#4FC0D3] font-bold m-0">지치지 않는 도전을 하는</h2>
          <h1 class="text-2xl font-bold my-1">홍길동님!</h1>
          <div class="text-purple-600 font-bold">포인트 {points}P</div>
        </div>
      </div>
      <div class="h-full flex flex-row items-end gap-2.5 mt-20">
        <img src="/images/badge1.png" alt="뱃지1" class="w-10 h-10" />
        <img src="/images/badge2.png" alt="뱃지2" class="w-10 h-10" />
        <img src="/images/badge3.png" alt="뱃지3" class="w-10 h-10" />
      </div>
    </div>
    <div class="flex flex-row justify-between mt-3">
      <div class="text-xs text-gray-600">마지막 학습일 {lastUpdated}</div>
      <div class="text-xs">
          <button class="btn btn-sm bg-[#574DDE] text-white rounded-full" onclick={() => rq.goTo('/home/info')}>
              업적관리
          </button>
      </div>
  </div>
  </div>

  <!-- 경험치 내역 섹션 -->
  <div class="bg-gray-100 rounded-2xl p-5 mt-10">
    <div class="flex justify-between items-center mb-4">
      <h3 class="text-lg font-semibold">경험치 내역</h3>
      <div class="flex items-center gap-2">
        <span class="text-xs text-gray-500">경험치가 쌓이면 레벨을 올릴 수 있어요!</span>
        <span class="text-gray-500 cursor-pointer">›</span>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">발맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-sm text-gray-500">8차시 | 학습완료</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">발맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-sm text-gray-500">7차시 | 학습완료</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">발맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-sm text-gray-500">6차시 | 학습완료</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">발맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-sm text-gray-500">5차시 | 학습완료</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">발맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-sm text-gray-500">4차시 | 학습완료</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex justify-center gap-2 mt-4">
      <button class="w-6 h-6 bg-blue-500 text-white rounded-full">1</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">2</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">3</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">4</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">5</button>
    </div>
  </div>

  <!-- 포인트 내역 섹션 -->
  <div class="bg-gray-100 rounded-2xl p-5 mt-10">
    <div class="flex justify-between items-center mb-4">
      <h3 class="text-lg font-semibold">포인트 내역</h3>
      <div class="flex items-center gap-2">
        <span class="text-xs text-gray-500">포인트는 어떻게 얻을 수 있나요?</span>
        <span class="text-gray-500 cursor-pointer">›</span>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">차시 100% 달성!</div>
            <div class="text-sm text-gray-500">8차시 | 적립</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md text-red-500">힌트 1단계</div>
            <div class="text-sm text-gray-500">8차시 | <span class="text-red-500">사용</span></div>
          </div>
        </div>
        <div class="text-red-500 font-bold">-20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">차시 100% 달성!</div>
            <div class="text-sm text-gray-500">7차시 | 적립</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">차시 100% 달성!</div>
            <div class="text-sm text-gray-500">6차시 | 적립</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center justify-between py-2.5 border-b border-gray-200">
        <div class="flex flex-row">
          <div class="w-20 text-sm font-gray-300 mt-1">25.02.01</div>
          <div>
            <div class="font-bold text-md">차시 100% 달성!</div>
            <div class="text-sm text-gray-500">5차시 | 적립</div>
          </div>
        </div>
        <div class="text-[#4FC0D3] font-bold">+20</div>
      </div>
    </div>
    <div class="flex justify-center gap-2 mt-4">
      <button class="w-6 h-6 bg-blue-500 text-white rounded-full">1</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">2</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">3</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">4</button>
      <button class="w-6 h-6 text-gray-500 rounded-full">5</button>
    </div>
  </div>
</div>
