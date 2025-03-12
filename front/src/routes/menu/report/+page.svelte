<script lang="ts">
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';
  import { page } from '$app/stores';

  // 현재 활성화된 탭 확인
  $: activeTab = $page.url.pathname.includes('member') ? 'member' : 'lecture';

  // 탭 변경 함수
  function changeTab(tab: string) {
    goto(`/menu/report/${tab}`);
  }

  onMount(() => {
    goto('/menu/report/lecture');
  });
</script>

<div class="max-w-7xl min-h-[90vh] mx-auto px-4 py-8">
  <h1 class="text-2xl font-bold mb-8">학습 현황</h1>

  <!-- 탭 메뉴 -->
  <div class="flex gap-4 border-b border-gray-200 mb-6">
    <button 
      class="px-4 py-2 font-bold {activeTab === 'lecture' ? 'text-indigo-600 border-b-2 border-indigo-600' : 'text-gray-500 hover:text-gray-700'}"
      on:click={() => changeTab('lecture')}
    >
      <div class="flex items-center gap-2">
        <i class="fa-solid fa-book text-xl"></i>
        <span>수업</span>
      </div>
    </button>
    <button 
      class="px-4 py-2 font-bold {activeTab === 'member' ? 'text-indigo-600 border-b-2 border-indigo-600' : 'text-gray-500 hover:text-gray-700'}"
      on:click={() => changeTab('member')}
    >
      <div class="flex items-center gap-2">
        <i class="fa-solid fa-user text-xl"></i>
        <span>구성원</span>
      </div>
    </button>
  </div>

  <!-- 탭 컨텐츠 -->
  <slot />
</div> 