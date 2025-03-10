<script lang="ts">
    import { page } from '$app/stores';
    import { goto } from '$app/navigation';

    const menuItems = [
        { icon: 'fa-home', text: '메인', path: '/class' },
        { icon: 'fa-book', text: '차시 목록', path: '/menu/lecture', active: true },
        { icon: 'fa-chart-line', text: '학습 현황', path: '/class' },
        { icon: 'fa-robot', text: 'AI퍼스널리포트', path: '/class' },
        { icon: 'fa-map-marked-alt', text: '모험맵', path: '/class' },
        { icon: 'fa-clipboard', text: '게시판', path: '/class' }
    ];
</script>

<div class="fixed left-0 top-0 bg-white text-gray-800 min-w-[170px] w-[250px] h-screen p-4 shadow-lg">
    <!-- 로고 영역 -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <div class="flex items-center gap-2 mb-8 cursor-pointer" on:click={() => goto('/')}>
        <img src="/images/learnx_logo.png" alt="LearnX" class="w-8 h-8" />
        <span class="text-2xl font-bold">LearnX</span>
    </div>

    <!-- 강의 정보 -->
    <div class="relative my-8">
        <!-- 상단 구분선 -->
        <div class="absolute -top-4 left-0 right-0 h-[1px] bg-cyan-500"></div>
        
        <div class="py-4">
            <div class="flex items-center gap-2 mb-5">
                <img src="/images/python_icon.png" alt="Python" class="w-8 h-8" />
                <span class="text-sm font-bold">2025 디지털새싹 AI기반 학습멘토링</span>
            </div>
            <h1 class="text-xl font-bold leading-tight">발 맞추어 천천히 따라가는 중학생 기초 파이썬</h1>
        </div>

        <!-- 하단 구분선 -->
        <div class="absolute -bottom-4 left-0 right-0 h-[1px] bg-cyan-500"></div>
    </div>

    <!-- 메뉴 목록 -->
    <nav>
        <ul class="space-y-2">
            {#each menuItems as item}
                <li>
                    <a 
                        href={item.path}
                        class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-cyan-100 transition-colors duration-150 {$page.url.pathname === item.path ? 'bg-cyan-100' : ''}"
                    >
                        <i class="fas {item.icon} w-5 text-center"></i>
                        <span class="font-medium">{item.text}</span>
                    </a>
                </li>
            {/each}
        </ul>
    </nav>
</div>