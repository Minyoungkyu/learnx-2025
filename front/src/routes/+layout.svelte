<script lang="ts">
  import { onMount } from 'svelte';
  import { writable } from 'svelte/store';
  import { page } from '$app/stores';
  import hotkeys from 'hotkeys-js';
  import { onDestroy } from 'svelte';

  import SideMenu from "$lib/components/SideMenu.svelte";

  import '$lib/app.pcss';

  import rq from '$lib/rq/rq.svelte';

  const { children } = $props();

  onMount(() => {
    // rq.initAuth();
  });

  let backgroundColor:string = $state('bg-base-100');

  let needHeader:boolean = $state(false);
  let needSideMenu:boolean = $state(false);
  let needFooter:boolean = $state(false);

  let isOverlayVisible = writable(false);
  let isModalOpen = $state(() => rq.dialogStack.length > 0);

  function disableScroll(e: any) {
      // 이벤트가 발생한 요소나 그 부모가 모달 내부인지 확인
      const isModalContent = e.target.closest('.modal-content');
      if (!isModalContent) {
          e.preventDefault(); // 모달 외부의 스크롤만 방지
      }
  }

  $effect(() => {
      isOverlayVisible.set(isModalOpen());

      if (isModalOpen()) {    
          document.addEventListener("wheel", disableScroll, { passive: false });
          document.addEventListener("touchmove", disableScroll, { passive: false });
      } else {
          document.removeEventListener("wheel", disableScroll);
          document.removeEventListener("touchmove", disableScroll);
      }

      const path = $page.url.pathname;

      if (path.startsWith('/menu')) {
        backgroundColor = 'bg-gray-100';
      } else {
        backgroundColor = 'bg-base-100';
      }

      if (path.startsWith('/learn')) {
        needHeader = false;
      } else {
        needHeader = true;
      }

      if (path == '/' || path == '/class' || path == '/learn' || path == '/learn2') {
        needSideMenu = false;
      } else {
        needSideMenu = true;
      }

      if (path == '/') {
        needFooter = true;
      } else {
        needFooter = false;
      }

  });
</script>

<!-- 오버레이 (클릭하면 모달 닫힘) -->
{#if $isOverlayVisible}
    <div class="fixed inset-0 bg-black opacity-50 transition-opacity duration-300 z-40">
    </div>
{/if}

{#if needHeader}
<header class="navbar flex justify-center sticky p-4 top-0 z-10 h-12 {backgroundColor}">
  <div class="flex-none">
    <div class="flex items-start gap-2 cursor-pointer" onclick={() => rq.goTo('/')}>
      <img src="/images/learnx_logo.png" alt="LearnX" class="w-38 h-8" />
    </div>
  </div>

  <div class="flex w-full justify-center">
    <div class="w-fit flex items-center justify-center gap-2 text-sm font-bold bg-gray-200 rounded-full px-2 py-1">
      <a href="/" class="px-2 py-1 rounded-full hover:bg-gray-100 {$page.url.pathname === '/' ? 'bg-white' : ''}">홈</a>
      <a href="/class" class="px-2 py-1 rounded-full hover:bg-gray-100 {$page.url.pathname === '/class' || $page.url.pathname.startsWith('/menu/lecture') ? 'bg-white' : ''}">클래스</a>
      <a href="/coding" class="px-2 py-1 rounded-full hover:bg-gray-100 {$page.url.pathname === '/coding' ? 'bg-white' : ''}">코딩 챌린지</a>
    </div>
  </div>

  <div class="flex-none">
    <div class="dropdown dropdown-end">
      <div tabindex="0" role="button" class="btn btn-ghost btn-circle avatar">
        <div class="w-10 rounded-full">
          <img
            alt="프로필 이미지"
            src="/images/profile_sample.png" />
        </div>
      </div>
      <ul
        tabindex="0"
        class="menu menu-sm dropdown-content bg-base-100 rounded-box z-1 mt-3 w-52 p-2 shadow">
        <li>
          <div class="flex items-center gap-2 py-2">
            <div class="w-12 rounded-full">
              <img
              alt="프로필 이미지"
              src="/images/profile_sample.png" />
            </div>
            <div class="flex flex-col items-start justify-center">
              <span class="text-lg font-bold">TestUser</span>
              <span>학생</span>
            </div>
          </div>
        </li>
        <div class="divider my-0"></div>
        <li>
          <div class="flex gap-2 items-center">
            <div class="flex items-center gap-1">
              <span class="text-gray-500">레벨</span>
              <span class="text-sm font-bold">1</span>
            </div>
            <div class="w-[1px] h-3 bg-gray-300"></div>
            <div class="flex items-center gap-1">
              <span class="text-gray-500 font-bold">포인트</span>
              <span class="text-sm font-bold">1200 P</span>
            </div>
          </div>
        </li>
        <div class="divider my-0"></div>
        <li>
          <a class="px-4 py-2 text-md">
            <i class="fa-solid fa-chalkboard-user"></i>
            <span>내 학습</span>
          </a>
        </li>
        <li>
          <a class="px-4 py-2 text-md">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>로그아웃</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</header>
{/if}


<main class="w-full flex flex-row">
  {#if needSideMenu}
    <div class="w-[250px] min-w-[250px] sideMenuContainer">
      <SideMenu />
    </div>
  {/if}
  {@render children()}
</main>

{#if needFooter}
<footer class="w-full bg-white border-t">
  <div class="max-w-6xl mx-auto px-4 md:px-8 py-12">
    <div class="flex flex-col md:flex-row justify-between items-start gap-8">
      <div class="flex flex-col md:flex-row gap-4">
        <div class="flexbox-col">
          <p class="text-[20px]">|주|TMD교육그룹</p>
          <p class="text-xs">TMD Educatiion Group Inc.</p>
          <p class="text-xs">로고에용</p>
        </div>
  
        <div class="flex flex-col">
          <div class="text-xs text-gray-500 space-y-1">
            <p>본사 : 서울특별시 서초구 서초중앙로8길 26 유노피스빌딩</p>
            <p>전화 : 02-595-2222 &nbsp;&nbsp;&nbsp; 팩스 : 02-512-1304</p>
            <p>사업자등록번호 : 211-87-96577 &nbsp;&nbsp;&nbsp; 통신판매신고번호 : 2013-서울서초-1364</p>
          </div>
        </div>
      </div>

      <div class="flex gap-20">
        <div class="flex gap-4">
          <i class="fa-brands fa-facebook text-gray-400"></i>
          <i class="fa-brands fa-twitter text-gray-400"></i>
          <i class="fa-brands fa-instagram text-gray-400"></i>
          <i class="fa-brands fa-youtube text-gray-400"></i>
        </div>
      </div>
    </div>

    <div class="flex justify-between items-center mt-12 pt-8 border-t">
      <p class="text-sm text-gray-400">copyright © 2007-2018 TMD Education Group Inc All rights reserved. Designed & Programmed by WHOISDREAM</p>
      <div class="flex gap-2 text-xs text-gray-700">
        <p>이용약관</p>
        <p>개인정보 처리방침</p>
        <p>도움말</p>
      </div>
    </div>
  </div>
</footer>
{/if}

<style>
  .menu {
    --menu-active-fg: unset;
    --menu-active-bg: unset;
  }

  .menu li *:hover {
    background-color: white;
  }
</style>