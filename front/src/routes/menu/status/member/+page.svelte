<script lang="ts">
  import StatusLayout from '$lib/components/StatusLayout.svelte';
  import { onMount } from 'svelte';
  import { Chart, type ChartConfiguration } from 'chart.js/auto';

  // 사용자 정보
  const userInfo = {
    name: '홍길동',
    email: 'asdf1234@naver.com',
    badges: ['medal', 'trophy', 'star'],
    joinDate: '2024.12.01',
    role: '구성원'
  };

  // 학습 현황 데이터
  const learningStatus = {
    progressRate: {
      current: 71,
      total: 100
    },
    testScore: 550
  };

  // 차트 데이터
  const chartData = {
    labels: ['1차시', '2차시', '3차시', '4차시'],
    data: [50, 85, 92, 88]
  };

  // 원형 차트 데이터
  const circleChartData = {
    상위: 78,
    점수: 224,
    전체점수: 2452,
    반평균: {
      current: 510,
      total: 7631
    },
    전체평균: {
      current: 775,
      total: 8779
    }
  };

  // 학습 상세 현황 데이터
  const learningDetails = {
    진단평가: {
      title: '진단 평가',
      score: '100/800점',
      items: [
        { id: 1, title: '[평가1] 숫자를 출력하세요', attempts: 1, submissions: 2, score: '0/100', status: '수업자료보기' },
        { id: 2, title: '[평가2] 문자를 출력하세요', attempts: 1, submissions: 1, score: '100/100', status: '완료' },
        { id: 3, title: '[평가3] 변수를 출력하세요', attempts: 1, submissions: 1, score: '0/100', status: '수업자료보기' },
        { id: 4, title: '[평가4] 이름을 입력받은 후 출력하세요', attempts: '--', submissions: '--', score: '0/100', status: '수업자료보기' },
        { id: 5, title: '[평가5] 숫자 두개를 입력받아 합을 출력하세요', attempts: '--', submissions: '--', score: '0/100', status: '수업자료보기' },
        { id: 6, title: '[평가6] 입력 받은 숫자만큼 안녕을 출력하세요', attempts: '--', submissions: '--', score: '0/100', status: '수업자료보기' },
        { id: 7, title: '[평가7] for문을 사용하여 숫자를 출력해보세요', attempts: '--', submissions: '--', score: '0/100', status: '수업자료보기' },
        { id: 8, title: '[평가8] 입력받은 숫자를 비교한 결과를 출력해보...', attempts: '--', submissions: '--', score: '0/100', status: '수업자료보기' }
      ]
    },
    차시1: {
      title: '1차시 - 프로그래밍과 파이썬',
      score: '100/800점',
      items: [
        { id: 1, title: '[평가1] 숫자를 출력하세요', attempts: 1, submissions: 2, score: '--', status: '완료' },
        { id: 2, title: '[평가2] 문자를 출력하세요', attempts: 1, submissions: 1, score: '--', status: '완료' },
        { id: 3, title: '[평가3] 변수를 출력하세요', attempts: 1, submissions: 1, score: '--', status: '완료' },
        { id: 4, title: '[평가4] 이름을 입력받은 후 출력하세요', attempts: 1, submissions: '--', score: '0/100', status: '완료' },
        { id: 5, title: '[평가5] 숫자 두개를 입력받아 합을 출력하세요', attempts: 1, submissions: 2, score: '0/100', status: '완료' },
        { id: 6, title: '[평가6] 입력 받은 숫자만큼 안녕을 출력하세요', attempts: 1, submissions: '--', score: '0/100', status: '완료' },
        { id: 7, title: '[평가7] for문을 사용하여 숫자를 출력해보세요', attempts: 2, submissions: 2, score: '0/100', status: '완료' },
        { id: 8, title: '[평가8] 입력받은 숫자를 비교한 결과를 출력해보...', attempts: 4, submissions: 6, score: '0/100', status: '완료' }
      ]
    }
  };

  let chart: Chart;
  let circleChart: Chart;

  onMount(() => {
    // 라인 차트 초기화
    const ctx = document.getElementById('lineChart') as HTMLCanvasElement;
    if (ctx) {
      const config: ChartConfiguration = {
        type: 'line',
        data: {
          labels: chartData.labels,
          datasets: [
            {
              data: chartData.data,
              borderColor: 'rgb(99, 102, 241)',
              backgroundColor: 'rgb(99, 102, 241)',
              tension: 0.4,
              pointRadius: 4,
              pointBackgroundColor: 'white',
              pointBorderColor: 'rgb(99, 102, 241)',
              pointBorderWidth: 2
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: false
            }
          },
          scales: {
            y: {
              beginAtZero: true,
              grid: {
                color: 'rgba(0, 0, 0, 0.05)'
              }
            },
            x: {
              grid: {
                display: false
              }
            }
          }
        }
      };
      chart = new Chart(ctx, config);
    }

    // 원형 차트 초기화
    const circleCtx = document.getElementById('circleChart') as HTMLCanvasElement;
    if (circleCtx) {
      const circleConfig: ChartConfiguration = {
        type: 'doughnut',
        data: {
          datasets: [
            {
              data: [circleChartData.상위, 100 - circleChartData.상위],
              backgroundColor: [
                'rgb(99, 102, 241)',
                'rgba(0, 0, 0, 0.05)'
              ],
              borderWidth: 0
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          cutout: '80%',
          plugins: {
            legend: {
              display: false
            }
          }
        }
      };
      circleChart = new Chart(circleCtx, circleConfig);
    }
  });
</script>

<StatusLayout>
  <!-- 사용자 프로필 카드 -->
  <div class="bg-indigo-500 rounded-xl p-6 mb-6 text-white">
    <div class="flex items-start gap-4">
      <div class="w-16 h-16 rounded-full bg-white/20 flex items-center justify-center">
        <i class="fas fa-user text-2xl"></i>
      </div>
      <div class="flex-grow">
        <h2 class="text-xl font-bold">{userInfo.name}</h2>
        <p class="text-indigo-200">{userInfo.email}</p>
        <div class="flex gap-2 mt-2">
          {#each userInfo.badges as badge}
            <div class="w-8 h-8 rounded-full bg-white/20 flex items-center justify-center">
              <i class="fas fa-{badge} text-sm"></i>
            </div>
          {/each}
        </div>
      </div>
      <div class="text-right text-indigo-200">
        <p>{userInfo.joinDate}</p>
        <p class="mt-1">{userInfo.role}</p>
      </div>
    </div>
  </div>

  <!-- 학습 현황 카드 -->
  <div class="grid grid-cols-2 gap-6 mb-6">
    <div class="bg-white rounded-xl p-6 outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 mb-4">
        <i class="fas fa-chart-line text-gray-400"></i>
        <h3 class="font-medium">학습 진행률</h3>
      </div>
      <p class="text-lg">
        <span class="text-cyan-500">{learningStatus.progressRate.current}</span>
        <span class="text-gray-400">/ {learningStatus.progressRate.total}%</span>
      </p>
    </div>
    <div class="bg-white rounded-xl p-6 outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 mb-4">
        <i class="fas fa-calculator text-gray-400"></i>
        <h3 class="font-medium">테스트 총 점수 합계</h3>
      </div>
      <p class="text-lg">
        <span class="text-cyan-500">{learningStatus.testScore}</span>
        <span class="text-gray-400">점</span>
      </p>
    </div>
  </div>

  <!-- 현황 차트 -->
  <div class="grid grid-cols-2 gap-6 mb-6">
    <!-- 라인 차트 -->
    <div class="bg-white rounded-xl p-6 outline outline-2 outline-gray-100">
      <div class="flex justify-between items-center mb-4">
        <h3 class="font-medium">차시 현황</h3>
        <select class="form-select text-sm border-gray-200 rounded-lg">
          <option>실습 점수</option>
        </select>
      </div>
      <div class="w-full h-px bg-gray-100 mb-4"></div>
      <div class="h-[200px]">
        <canvas id="lineChart"></canvas>
      </div>
    </div>

    <!-- 원형 차트 -->
    <div class="bg-white rounded-xl p-6 outline outline-2 outline-gray-100">
      <div class="flex justify-between items-center mb-4">
        <h3 class="font-medium">학습 성취도</h3>
        <button class="text-gray-400">
          <i class="fas fa-ellipsis-h"></i>
        </button>
      </div>
      <div class="w-full h-px bg-gray-100 mb-4"></div>
      <div class="grid grid-cols-2 gap-6">
        <div class="relative h-[200px]">
          <canvas id="circleChart"></canvas>
          <div class="absolute inset-0 flex flex-col items-center justify-center">
            <p class="text-sm text-gray-500">상위</p>
            <p class="text-2xl font-bold">{circleChartData.상위}%</p>
          </div>
        </div>
        <div class="flex flex-col justify-center space-y-4">
          <div>
            <div class="flex justify-between text-sm mb-1">
              <span>점수</span>
              <span class="text-indigo-500">{circleChartData.점수}</span>
            </div>
            <div class="flex justify-between text-sm mb-1">
              <span>전체 점수</span>
              <span>{circleChartData.전체점수}</span>
            </div>
          </div>
          <div>
            <div class="flex justify-between text-sm mb-1">
              <span>반 평균</span>
              <span class="text-indigo-500">{circleChartData.반평균.current} / {circleChartData.반평균.total}</span>
            </div>
            <div class="flex justify-between text-sm">
              <span>전체 평균</span>
              <span class="text-indigo-500">{circleChartData.전체평균.current} / {circleChartData.전체평균.total}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 학습 상세 현황 -->
  <div class="bg-white rounded-xl p-6 outline outline-2 outline-gray-100">
    <div class="flex justify-between items-center mb-4">
      <h3 class="font-medium">학습 상세 현황</h3>
      <label class="flex items-center gap-2">
        <input type="checkbox" class="form-checkbox text-indigo-500 rounded">
        <span class="text-sm text-gray-500">테스트 수업자료만 보기</span>
      </label>
    </div>

    {#each Object.values(learningDetails) as section}
      <div class="mb-8 last:mb-0">
        <div class="flex justify-between items-center mb-4">
          <h4 class="font-medium">{section.title}</h4>
          <p class="text-sm text-gray-500">{section.score}</p>
        </div>
        <div class="overflow-x-auto">
          <table class="w-full">
            <thead>
              <tr class="border-b border-gray-100">
                <th class="py-2 text-left text-sm font-medium text-gray-500">제목</th>
                <th class="py-2 text-center text-sm font-medium text-gray-500">실행 수</th>
                <th class="py-2 text-center text-sm font-medium text-gray-500">제출 수</th>
                <th class="py-2 text-center text-sm font-medium text-gray-500">점수</th>
                <th class="py-2 text-center text-sm font-medium text-gray-500">현황</th>
              </tr>
            </thead>
            <tbody>
              {#each section.items as item}
                <tr class="border-b border-gray-50">
                  <td class="py-3 text-sm">{item.title}</td>
                  <td class="py-3 text-sm text-center">{item.attempts}</td>
                  <td class="py-3 text-sm text-center">{item.submissions}</td>
                  <td class="py-3 text-sm text-center">{item.score}</td>
                  <td class="py-3 text-sm text-center">
                    {#if item.status === '완료'}
                      <span class="text-gray-400">
                        <i class="fas fa-check"></i>
                      </span>
                    {:else}
                      <button class="text-indigo-500 hover:text-indigo-600">
                        {item.status} <i class="fas fa-chevron-right ml-1"></i>
                      </button>
                    {/if}
                  </td>
                </tr>
              {/each}
            </tbody>
          </table>
        </div>
      </div>
    {/each}
  </div>
</StatusLayout> 