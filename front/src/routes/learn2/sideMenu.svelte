<script lang="ts">
    import { page } from '$app/stores';
    import { goto } from '$app/navigation';

    const menuItems = [
        { icon: 'fa-book', text: '01 수업 시작하기', path: '#' },
        { icon: 'fa-book', text: '02 프로그램과 프로그래밍 언어', path: '#' },
        { icon: 'fa-book', text: '03 출력', path: '#', active: true },
        { icon: 'fa-book', text: '04 실습1:숫자 5를 출력', path: '#' },
        { icon: 'fa-book', text: '05 실습2:명령어의 대소문자', path: '#' },
        { icon: 'fa-book', text: '06 실습3:안녕', path: '#' },
        { icon: 'fa-book', text: '07 실습4:시작과 끝', path: '#' },
        { icon: 'fa-book', text: '08 실습8:따옴표 안', path: '#' }
    ];
</script>

<div class="fixed left-0 top-0 bg-[#44484B] text-white font-bold min-w-[170px] w-[250px] h-screen p-4 shadow-lg rounded-tr-2xl z-[11]">
    <!-- 로고 영역 -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <div class="flex justify-start gap-4">
        <div>
            <img src="/images/python_icon.png" alt="Python" class="w-8 h-8 rounded-md" />
        </div>
        <div class="flex items-center">
            <span>기초 파이썬</span>
        </div>
    </div>

    <!-- 강의 정보 -->
    <div class="relative my-8">
        <!-- 상단 구분선 -->
        <div class="absolute -top-4 left-0 right-0 h-[1px] bg-cyan-500"></div>
        
        <div class="flex  text-2xl gap-2">
            <span class="flex items-center cursor-pointer"><i class="fa-solid fa-angle-left"></i></span>
            <span>1차시 - 프로그래밍과 파이썬</span>
            <span class="flex items-center cursor-pointer"><i class="fa-solid fa-angle-right"></i></span>
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
                        class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-[#4FC0D3] transition-colors duration-150 {index === 0 ? 'bg-[#4FC0D3]' : ''}"
                    >
                        <span class="font-medium text-md">{item.text}</span>
                    </a>
                </li>
            {/each}
        </ul>
    </nav>
</div>