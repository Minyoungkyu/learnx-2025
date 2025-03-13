<script lang="ts">
  import rq from '$lib/rq/rq.svelte';
  import { onMount } from 'svelte';
  import AOS from 'aos';
  import 'aos/dist/aos.css';

  onMount(() => {
    AOS.init({
      duration: 500,
      once: true,
      offset: 50
    });

    const observerOptions = {
      root: null,
      rootMargin: '0px',
      threshold: 0.1
    };

    const startCountAnimation = (element: any) => {
      const target = parseInt(element.dataset.target);
      const duration = 2000;
      const step = (target / duration) * 10;
      let current = 0;

      const timer = setInterval(() => {
        current += step;
        if (current >= target) {
          element.textContent = target;
          clearInterval(timer);
        } else {
          element.textContent = Math.floor(current);
        }
      }, 10);
    };

    const startBarAnimation = (element: any) => {
      const targetHeight = element.dataset.height;
      element.style.height = targetHeight;
    };

    const observer = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          if (entry.target.classList.contains('counter')) {
            startCountAnimation(entry.target);
          }
          if (entry.target.hasAttribute('data-height')) {
            startBarAnimation(entry.target);
          }
          observer.unobserve(entry.target);
        }
      });
    }, observerOptions);

    document.querySelectorAll('.counter').forEach(counter => {
      observer.observe(counter);
    });

    document.querySelectorAll('[data-height]').forEach(bar => {
      observer.observe(bar);
    });
  });
</script>

<div class="w-full flex flex-col items-center justify-center">
  <section class="flexbox-col w-full h-[calc(100vh-64px)] bg-[url('/images/index_main.jpg')] bg-cover bg-center px-4 text-center relative">
    <div class="absolute inset-0 bg-white opacity-50"></div>
    <h3 class="relative z-10 text-4xl md:text-5xl lg:text-6xl font-bold text-[#00CA5B]">Learn-X</h3>
    <p class="relative z-10 text-2xl md:text-3xl lg:text-4xl font-bold">AI시대, 배움을 새롭게 디자인하다!</p>
  </section>

  <section class="flexbox-col gap-6 py-20 w-full bg-gradient-to-b from-white to-[#F0F6FF] text-lg font-bold px-4 text-center">
    <h3 class="mb-4 text-3xl md:text-4xl lg:text-5xl font-bold text-[#00CA5B]">Learn-X</h3>
    <p class="text-base md:text-lg">우리는 개인, 가정, 나라에 희망이 되는 교육을 연구합니다.</p>
    <p class="text-base md:text-lg">미래는 밝히고, 세상을 밝혀 나가는 빛 된 교육을 연구합니다.</p>
    <p class="text-base md:text-lg">이를 통해 우리는 특별한 재능, 세상을 이끄는 리더십, 섬김의 성품을</p>
    <p class="text-base md:text-lg">가진 진정한 미래인재를 양성합니다.</p>
    <div class="flex flex-col md:flex-row gap-6 mt-8 items-center justify-center">

      <div class="card bg-base-100 w-80 shadow-sm" data-aos="fade-up">
        <figure class="w-full h-80 overflow-hidden cursor-pointer">
          <img
            src="/images/main_1.jpg"
            alt=""
            class="w-full h-full object-cover transition-transform duration-300 hover:scale-110" />
        </figure>
        <div class="card-body">
          <h2 class="card-title">희망이 되는 교육</h2>
          <p class="text-sm text-start">개인과 가정, 나아가 우리 사회에 희망이 되는 교육을 실천합니다. 모든 학생의 잠재력을 믿고 발전시키는 교육을 지향합니다.</p>
        </div>
      </div>

      <div class="card bg-base-100 w-80 shadow-sm" data-aos="fade-up" data-aos-delay="400">
        <figure class="w-full h-80 overflow-hidden cursor-pointer">
          <img
            src="/images/main_2.jpg"
            alt=""
            class="w-full h-full object-cover transition-transform duration-300 hover:scale-110" />
        </figure>
        <div class="card-body">
          <h2 class="card-title">미래를 밝히는 교육</h2>
          <p class="text-sm text-start">혁신적인 교육 방법으로 미래를 이끌어갈 인재를 양성합니다. AI 시대에 걸맞은 창의적이고 실용적인 교육을 제공합니다.</p>
        </div>
      </div>

      <div class="card bg-base-100 w-80 shadow-sm" data-aos="fade-up" data-aos-delay="600">
        <figure class="w-full h-80 overflow-hidden cursor-pointer">
          <img
            src="/images/main_3.jpg"
            alt=""
            class="w-full h-full object-cover transition-transform duration-300 hover:scale-110" />
        </figure>
        <div class="card-body">
          <h2 class="card-title">리더십과 섬김의 인재</h2>
          <p class="text-sm text-start">탁월한 실력과 함께 겸손한 섬김의 자세를 갖춘 진정한 리더를 키웁니다. 세상에 긍정적인 영향을 미치는 인재로 성장합니다.</p>
        </div>
      </div>

    </div>
  </section>

  <section class="flexbox-col w-full py-20">
    <h3 class="text-3xl md:text-4xl font-bold text-[#00CA5B] text-center mb-12 px-4" data-aos="fade-down">혁신적인 교육 도구</h3>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 md:gap-10 max-w-6xl mx-auto px-4 md:px-8">
      <div class="flex items-center bg-gray-50 rounded-xl p-8 hover:shadow-lg transition-shadow" data-aos="fade-right">
        <i class="fa-solid fa-clipboard-question text-5xl text-[#00CA5B] mr-6"></i>
        <div>
          <h2 class="text-2xl font-bold mb-3">다양한 평가 방식</h2>
          <p class="text-gray-600 leading-relaxed">객관식, 주관식부터 실시간 코딩 테스트까지 학습 성과를 다각도로 평가할 수 있습니다</p>
        </div>
      </div>

      <div class="flex items-center bg-gray-50 rounded-xl p-8 hover:shadow-lg transition-shadow" data-aos="fade-left">
        <i class="fa-solid fa-chalkboard-user text-5xl text-[#00CA5B] mr-6"></i>
        <div>
          <h2 class="text-2xl font-bold mb-3">효과적인 수업 도구</h2>
          <p class="text-gray-600 leading-relaxed">주목 기능과 실시간 판서 기능으로 더욱 효과적인 수업 진행이 가능합니다</p>
        </div>
      </div>

      <div class="flex items-center bg-gray-50 rounded-xl p-8 hover:shadow-lg transition-shadow" data-aos="fade-right">
        <i class="fa-solid fa-robot text-5xl text-[#00CA5B] mr-6"></i>
        <div>
          <h2 class="text-2xl font-bold mb-3">AI 학습 도우미</h2>
          <p class="text-gray-600 leading-relaxed">AI가 학생의 학습 과정을 분석하고 맞춤형 학습 가이드를 제공합니다</p>
        </div>
      </div>

      <div class="flex items-center bg-gray-50 rounded-xl p-8 hover:shadow-lg transition-shadow" data-aos="fade-left">
        <i class="fa-solid fa-laptop-code text-5xl text-[#00CA5B] mr-6"></i>
        <div>
          <h2 class="text-2xl font-bold mb-3">실습 환경</h2>
          <p class="text-gray-600 leading-relaxed">별도의 설치 없이 브라우저에서 바로 실습할 수 있는 온라인 코딩 환경을 제공합니다</p>
        </div>
      </div>
    </div>
  </section>

  <section class="w-full py-20 bg-[#6D6DFE] text-white">
    <div class="max-w-6xl mx-auto px-4 md:px-8">
      <h3 class="text-3xl md:text-4xl font-bold text-center mb-6" data-aos="fade-down">게임처럼 재미있게 배우세요</h3>
      <p class="text-center text-base md:text-lg mb-12" data-aos="fade-up">게이미피케이션 요소를 통해 학습 동기를 높이고 성취감을 경험하세요</p>
      
      <div class="flex flex-col space-y-16">
        <div class="flex flex-col md:flex-row items-center gap-8" data-aos="fade-right">
          <div class="w-full md:w-1/2">
            <h4 class="text-2xl md:text-3xl font-bold mb-4">레벨 시스템</h4>
            <p class="text-base md:text-lg leading-relaxed">학습을 진행하여 레벨을 올리고 포인트를 획득해보세요!</p>
          </div>
          <div class="w-full md:w-1/2 md:pl-12">
            <div class="bg-gray-200 h-72 rounded-2xl transition-transform duration-300 hover:scale-105 cursor-pointer">
              <img src="/images/leveling_index.PNG" class="w-full h-full object-contain rounded-2xl" alt="">
            </div>
          </div>
        </div>

        <div class="flex flex-col-reverse md:flex-row items-center gap-8" data-aos="fade-left">
          <div class="w-full md:w-1/2">
            <div class="bg-white h-72 rounded-2xl transition-transform duration-300 hover:scale-105 cursor-pointer">
              <img src="/images/achieve_index.jpg" class="w-full h-full object-contain rounded-2xl" alt="">
            </div>
          </div>
          <div class="w-full md:w-1/2 md:pl-12">
            <h4 class="text-2xl md:text-3xl font-bold mb-4">도전 과제</h4>
            <p class="text-base md:text-lg leading-relaxed">다양한 미션에 도전하여 업적을 달성하고 트로피를 획득해보세요!</p>
          </div>
        </div>

        <div class="flex flex-col md:flex-row items-center gap-8" data-aos="fade-right">
          <div class="w-full md:w-1/2">
            <h4 class="text-2xl md:text-3xl font-bold mb-4">재미와 학습</h4>
            <p class="text-base md:text-lg leading-relaxed">게임처럼 학습하는 코딩 교육 시스템</p>
          </div>
          <div class="w-full md:w-1/2 md:pl-12">
            <div class="bg-[#E6D0B3] h-72 rounded-2xl transition-transform duration-300 hover:scale-105 cursor-pointer">
              <img src="/images/map.PNG" class="w-full h-full object-contain rounded-2xl" alt="">
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section class="flexbox-col w-full py-20 bg-[#F7FAF4]">
    <h3 class="text-3xl md:text-4xl font-bold text-[#00CA5B] text-center mb-12" data-aos="fade-down">사용자 후기</h3>
    <div class="max-w-6xl mx-auto px-4 md:px-8">
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        
        <div class="bg-white p-6 rounded-lg shadow" data-aos="fade-up" data-aos-delay="100">
          <div class="flex items-center mb-4">
            <div class="w-12 h-12 bg-gray-200 rounded-full mr-4 flexbox-col">
              <i class="fa-regular fa-user text-2xl"></i>
            </div>
            <div>
              <h4 class="font-bold">김민수 선생님</h4>
            </div>
          </div>
          <p class="text-gray-600">"수업 준비 시간이 크게 줄었고, 학생들의 참여도도 높아졌습니다. AI 기능이 특히 유용해요!"</p>
        </div>

        <div class="bg-white p-6 rounded-lg shadow" data-aos="fade-up" data-aos-delay="300">
          <div class="flex items-center mb-4">
            <div class="w-12 h-12 bg-gray-200 rounded-full mr-4 flexbox-col">
              <i class="fa-regular fa-user text-2xl"></i>
            </div>
            <div>
              <h4 class="font-bold">이지현 학부모</h4>
            </div>
          </div>
          <p class="text-gray-600">"아이의 학습 진도와 성과를 실시간으로 확인할 수 있어서 매우 만족스럽습니다."</p>
        </div>

        <div class="bg-white p-6 rounded-lg shadow" data-aos="fade-up" data-aos-delay="500">
          <div class="flex items-center mb-4">
            <div class="w-12 h-12 bg-gray-200 rounded-full mr-4 flexbox-col">
              <i class="fa-regular fa-user text-2xl"></i>
            </div>
            <div>
              <h4 class="font-bold">박준호 학생</h4>
            </div>
          </div>
          <p class="text-gray-600">"온라인으로 바로 실습할 수 있어서 편리하고, AI가 부족한 부분을 찾아주니 공부하기가 훨씬 수월해요."</p>
        </div>

      </div>
    </div>
  </section>

  <section class="w-full py-20 bg-white">
    <div class="max-w-6xl mx-auto px-4 md:px-8">
      <p class="text-gray-600 text-center text-xl md:text-2xl mb-16">수많은 학생들이 Learn-X와 함께 성장하고 있습니다</p>
      
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-20">
        <div class="text-center">
          <div class="text-5xl font-bold text-[#00CA5B] mb-4">
            <span class="counter" data-target="15000">0</span>
            <span>+</span>
          </div>
          <p class="text-gray-600">누적 수강생</p>
        </div>
        <div class="text-center">
          <div class="text-5xl font-bold text-[#00CA5B] mb-4">
            <span class="counter" data-target="98">0</span>
            <span>%</span>
          </div>
          <p class="text-gray-600">수강생 만족도</p>
        </div>
        <div class="text-center">
          <div class="text-5xl font-bold text-[#00CA5B] mb-4">
            <span class="counter" data-target="250">0</span>
            <span>+</span>
          </div>
          <p class="text-gray-600">협력 교육기관</p>
        </div>
      </div>

      <div class="flex flex-row justify-center items-end h-64 gap-8">
        <div class="w-24 bg-[#00CA5B] rounded-t-lg transition-all duration-1000 h-0" data-height="85%" style="opacity: 0.3;"></div>
        <div class="w-24 bg-[#00CA5B] rounded-t-lg transition-all duration-1000 h-0" data-height="92%" style="opacity: 0.5;"></div>
        <div class="w-24 bg-[#00CA5B] rounded-t-lg transition-all duration-1000 h-0" data-height="98%" style="opacity: 0.7;"></div>
        <div class="w-24 bg-[#00CA5B] rounded-t-lg transition-all duration-1000 h-0" data-height="100%"></div>
      </div>
      <div class="flex justify-center gap-8 mt-4 text-sm text-gray-600">
        <div class="w-24 text-center">2021</div>
        <div class="w-24 text-center">2022</div>
        <div class="w-24 text-center">2023</div>
        <div class="w-24 text-center">2024</div>
      </div>
    </div>
  </section>

  <section class="flexbox-col gap-4 w-full h-[calc(100vh)] bg-[#F9FFF3] px-4 text-center">
    <h3 class="text-3xl md:text-4xl font-bold text-[#00CA5B]" data-aos="fade-down">Learn-X</h3>
    <p class="text-4xl md:text-5xl lg:text-6xl font-bold" data-aos="fade-up" data-aos-delay="200">지금 바로 시작해보세요</p>
    <button 
      onclick={() => rq.goTo('/class')}
      class="mt-10 text-white rounded-full bg-[#5DDBDB] hover:bg-[#4BC5C5] transition-colors text-lg md:text-xl font-bold px-6 md:px-8 py-2" data-aos="zoom-in" data-aos-delay="400">
      시작하기
    </button>
  </section>

</div>


