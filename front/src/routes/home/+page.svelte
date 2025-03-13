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
                정보보기
            </button>
        </div>
    </div>
  </div>
  <!-- 학습 내용 섹션 -->

  <div class="flex flex-row w-full gap-5">
    <div class="bg-gray-100 rounded-2xl p-5 w-1/2">
        <div class="flex justify-between items-center">
        <h3 class="text-lg font-semibold">오늘의 학습 내용</h3>
            <button class="bg-[#574DDE] text-white px-4 py-1.5 rounded-full">입장하기</button>
        </div>
        <div class="flex gap-4 mt-4">
        <div class="text-2xl">📚</div>
        <div>
            <div class="font-medium">발 맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-gray-700">01-수업목표</div>
            <div class="text-sm text-gray-500">2차시. 복습와 자료형</div>
        </div>
        </div>
    </div>

    <div class="bg-gray-100 rounded-2xl p-5 w-1/2">
        <div class="flex justify-between items-center">
        <h3 class="text-lg font-semibold">최근 학습 강의</h3>
        <button class="bg-[#574DDE] text-white px-4 py-1.5 rounded-full">입장하기</button>
        </div>
        <div class="flex gap-4 mt-4">
        <div class="text-2xl">📄</div>
        <div>
            <div class="font-medium">발 맞추어 천천히 따라가는 중학생 기초 파이썬</div>
            <div class="text-gray-700">01-수업목표</div>
            <div class="text-sm text-gray-500">1차시. 프로그램과 프로그래밍</div>
        </div>
        </div>
    </div>
  </div>

  <!-- 공지사항 섹션 -->
  <div class="bg-gray-100 rounded-2xl p-5 mt-10">
    <div class="flex justify-between items-center mb-4">
      <h3 class="text-lg font-semibold">공지사항</h3>
      <span class="text-gray-500 cursor-pointer">더보기 +</span>
    </div>
    <div class="flex flex-col gap-2.5">
      <div class="flex items-center gap-2.5 py-2.5 border-b border-gray-200">
        <i class="fa-solid fa-bullhorn"></i>
        <span class="flex-grow">포인트는 어떻게 얻을 수 있나요?</span>
        <span class="text-gray-400 text-xl">›</span>
      </div>
      <div class="flex items-center gap-2.5 py-2.5 border-b border-gray-200">
        <i class="fa-solid fa-bullhorn"></i>
        <span class="flex-grow">업적 리스트 보기</span>
        <span class="text-gray-400 text-xl">›</span>
      </div>
      <div class="flex items-center gap-2.5 py-2.5 border-b border-gray-200">
        <i class="fa-solid fa-bullhorn"></i>
        <span class="flex-grow">LearnX가 궁금해요!</span>
        <span class="text-gray-400 text-xl">›</span>
      </div>
    </div>
  </div>
</div>
