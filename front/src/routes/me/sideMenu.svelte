<script lang="ts">
    import { page } from '$app/stores';
    import rq from '$lib/rq/rq.svelte';
    import { goto } from '$app/navigation';

    const menuItems = [
        { icon: 'fa-solid fa-circle-user', text: '내 프로필', path: '#' },
        { icon: 'fa-book', text: '수강내역', path: '#' },
        { icon: 'fa-regular fa-heart', text: '찜', path: '#', active: true },
        { icon: 'fa-regular fa-comments', text: 'Q&A', path: '#' },
        { icon: 'fa-solid fa-gear', text: '설정', path: '#' }
    ];
</script>

<div class="fixed left-0 top-0 bg-white font-bold min-w-[170px] w-[250px] h-screen p-4 shadow-lg rounded-tr-2xl z-[11]">
    <!-- 로고 영역 -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <div class="flex items-center gap-2 mb-8 cursor-pointer" onclick={() => rq.goTo('/')}>
        <img src="/images/learnx_logo.png" alt="LearnX" class="w-38 h-8" />
    </div>

    <!-- 강의 정보 -->
    <div class="relative my-8">
        <!-- 상단 구분선 -->
        <div class="absolute -top-4 left-0 right-0 h-[1px] bg-cyan-500"></div>
        
        <div class="flex flex-col items-start justify-center  text-2xl gap-2">
            <div class="w-16 rounded-full">
                <img
                  alt="프로필 이미지"
                  src="/images/profile_sample.png" />
            </div>
            <span>홍길동님</span>
            <span class="flex items-center text-sm">abcd123@gmail.com</span>
        </div>

        <!-- 하단 구분선 -->
        <div class="absolute -bottom-4 left-0 right-0 h-[1px] bg-cyan-500"></div>
    </div>

    <!-- 메뉴 목록 -->
    <nav>
        <ul class="space-y-2">
            {#each menuItems as item, index}
                <li>
                    <a 
                        href={item.path}
                        class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-[#4FC0D3] transition-colors duration-150 {index === 0 ? 'bg-[#E5F8F7]' : ''}"
                    >
                        <i class="fas {item.icon} w-5 text-center"></i>
                        <span class="font-medium text-md">{item.text}</span>
                    </a>
                </li>
            {/each}
        </ul>
    </nav>
</div>