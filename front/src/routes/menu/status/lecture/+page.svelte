<script lang="ts">
  import StatusLayout from '$lib/components/StatusLayout.svelte';
  import { onMount } from 'svelte';
  import { Chart, type ChartConfiguration } from 'chart.js/auto';

  // 통계 데이터
  const stats = {
    totalStudents: 0,
    averageProgress: 71,
    totalLectures: 12490,
    completedLectures: 4860
  };

  // 학습 유형 선택 옵션
  const studyTypes = [
    { value: 'practiceAll', label: '실습 전체' },
    { value: 'practice1', label: '실습1' },
    { value: 'practice2', label: '실습2' },
    { value: 'practice3', label: '실습3' }
  ];
  let selectedStudyType = studyTypes[0];

  // 차트 데이터
  const chartData = {
    labels: ['0~30', '30~60', '60~90', '90~120', '120~150', '150~180'],
    scores: [0, 1, 0, 3, 5, 2],
    experience: [0, 1, 3, 4, 2, 1]
  };

  let chart: Chart;

  // 퀴즈 유형 선택 옵션
  const quizTypes = [
    { value: 'quizAll', label: '퀴즈 전체' },
    { value: 'quiz1', label: '퀴즈1' },
    { value: 'quiz2', label: '퀴즈2' }
  ];
  let selectedQuizType = quizTypes[0];

  // 퀴즈 차트 데이터
  const quizChartData = {
    labels: ['0~30', '30~60', '60~90', '90~120', '120~150', '150~180'],
    scores: [1, 2, 4, 6, 3, 1],
    experience: [1, 3, 5, 4, 2, 1]
  };

  let quizChart: Chart;

  onMount(() => {
    const ctx = document.getElementById('distributionChart') as HTMLCanvasElement;
    if (ctx) {
      const config: ChartConfiguration = {
        type: 'bar',
        data: {
          labels: chartData.labels,
          datasets: [
            {
              label: '평균 점수',
              data: chartData.scores,
              backgroundColor: 'rgb(34, 211, 238)',
              order: 2
            },
            {
              label: '평균 경험치',
              data: chartData.experience,
              type: 'line',
              borderColor: 'rgb(168, 85, 247)',
              backgroundColor: 'rgb(168, 85, 247)',
              borderWidth: 2,
              fill: false,
              tension: 0.4,
              order: 1
            }
          ]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: '학생 수'
              }
            }
          },
          plugins: {
            legend: {
              position: 'top' as const,
              align: 'end' as const
            }
          }
        }
      };
      chart = new Chart(ctx, config);
    }

    // 퀴즈 차트 초기화
    const quizCtx = document.getElementById('quizChart') as HTMLCanvasElement;
    if (quizCtx) {
      const quizConfig: ChartConfiguration = {
        type: 'bar',
        data: {
          labels: quizChartData.labels,
          datasets: [
            {
              label: '평균 점수',
              data: quizChartData.scores,
              backgroundColor: 'rgb(34, 211, 238)',
              order: 2
            },
            {
              label: '평균 경험치',
              data: quizChartData.experience,
              type: 'line',
              borderColor: 'rgb(168, 85, 247)',
              backgroundColor: 'rgb(168, 85, 247)',
              borderWidth: 2,
              fill: false,
              tension: 0.4,
              order: 1
            }
          ]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: '학생 수'
              }
            }
          },
          plugins: {
            legend: {
              position: 'top' as const,
              align: 'end' as const
            }
          }
        }
      };
      quizChart = new Chart(quizCtx, quizConfig);
    }
  });

  function handleStudyTypeChange() {
    // 실제 구현시에는 여기서 새로운 데이터를 가져와서 차트를 업데이트
    if (chart) {
      chart.update();
    }
  }

  function handleQuizTypeChange() {
    // 실제 구현시에는 여기서 새로운 데이터를 가져와서 차트를 업데이트
    if (quizChart) {
      quizChart.update();
    }
  }

  // 학습 분포 데이터
  const distributionData = [
    { time: 0, count: 0 },
    { time: 30, count: 10 },
    { time: 60, count: 15 },
    { time: 90, count: 20 },
    { time: 120, count: 45 },
    { time: 150, count: 40 },
    { time: 180, count: 15 }
  ];

  // 상세 테이블 데이터
  const tableData = [
    { id: '단계 1', progress: 80, students: 43, avgScore: 25, avgTime: '3:22', startDate: '8/15/23', endDate: '9/15/23' },
    { id: '단계 2', progress: 75, students: 43, avgScore: 25, avgTime: '3:22', startDate: '8/15/23', endDate: '9/15/23' },
    { id: '단계 3', progress: 65, students: 43, avgScore: 25, avgTime: '3:22', startDate: '8/15/23', endDate: '9/15/23' },
    { id: '단계 4', progress: 40, students: 43, avgScore: 25, avgTime: '3:22', startDate: '8/15/23', endDate: '9/15/23' }
  ];

  // 학습 목록 데이터
  const learningList = Array(12).fill(null).map((_, i) => ({
    id: i + 1,
    name: '홍길동',
    role: '학생',
    progress: '100%',
    quizScore: '100점',
    practiceScore: '100점',
    solvedProblems: '100문제'
  }));

  // 실습 노력 분포 데이터
  const practiceEffortData = [
    { 
      id: '전체 실습',
      correctRate: 80,
      executions: 43,
      submissions: 25,
      solveTime: '3:22',
      avgScore: 85,
      avgExp: 120
    },
    { 
      id: '실습 1',
      correctRate: 75,
      executions: 40,
      submissions: 22,
      solveTime: '3:15',
      avgScore: 82,
      avgExp: 110
    },
    { 
      id: '실습 2',
      correctRate: 70,
      executions: 38,
      submissions: 20,
      solveTime: '3:30',
      avgScore: 78,
      avgExp: 100
    },
    { 
      id: '실습 3',
      correctRate: 65,
      executions: 35,
      submissions: 18,
      solveTime: '3:45',
      avgScore: 75,
      avgExp: 90
    }
  ];
</script>

<svelte:head>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</svelte:head>

<StatusLayout>
  <!-- 통계 카드 섹션 -->
  <div class="grid grid-cols-4 gap-4 mb-6">
    <div class="bg-white rounded-xl p-4 shadow-sm outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 text-gray-600 mb-2">
        <i class="fas fa-users"></i>
        <span class="text-sm">총 학생 수</span>
      </div>
      <p class="text-2xl font-bold text-cyan-500">{stats.totalStudents}명</p>
    </div>
    <div class="bg-white rounded-xl p-4 shadow-sm outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 text-gray-600 mb-2">
        <i class="fas fa-chart-line"></i>
        <span class="text-sm">평균 진도율</span>
      </div>
      <p class="text-2xl font-bold text-cyan-500">{stats.averageProgress}%</p>
    </div>
    <div class="bg-white rounded-xl p-4 shadow-sm outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 text-gray-600 mb-2">
        <i class="fas fa-play"></i>
        <span class="text-sm">학습 시도 수</span>
      </div>
      <p class="text-2xl font-bold text-cyan-500">{stats.totalLectures}회</p>
    </div>
    <div class="bg-white rounded-xl p-4 shadow-sm outline outline-2 outline-gray-100">
      <div class="flex items-center gap-2 text-gray-600 mb-2">
        <i class="fas fa-check-circle"></i>
        <span class="text-sm">학습 완료 수</span>
      </div>
      <p class="text-2xl font-bold text-cyan-500">{stats.completedLectures}회</p>
    </div>
  </div>

  <!-- 학습 분포 그래프 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex justify-between items-center mb-4">
      <h3 class="font-bold">학습 분포</h3>
      <select 
        class="form-select rounded-lg border-gray-200 text-sm"
        bind:value={selectedStudyType}
        on:change={handleStudyTypeChange}
      >
        {#each studyTypes as type}
          <option value={type}>{type.label}</option>
        {/each}
      </select>
    </div>
    <div class="w-full h-px bg-gray-100 mb-4"></div>
    <div class="h-[300px]">
      <canvas id="distributionChart"></canvas>
    </div>
  </div>

  <!-- 실습 노력 분포 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex justify-between items-center mb-4">
      <h3 class="font-bold">실습 노력 분포</h3>
    </div>
    <div class="w-full h-px bg-gray-100 mb-4"></div>
    <table class="w-full">
      <thead>
        <tr class="border-b border-gray-100">
          <th class="text-center py-2 text-sm font-medium text-gray-500">실습</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">정답률</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">전체 실행수</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">전체 제출수</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">풀이시간</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">평균 점수</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">평균 경험치</th>
        </tr>
      </thead>
      <tbody>
        {#each practiceEffortData as item}
          <tr class="border-b border-gray-50">
            <td class="py-3 text-sm font-medium text-center">{item.id}</td>
            <td class="py-3 text-sm text-cyan-500 text-center">{item.correctRate}%</td>
            <td class="py-3 text-sm text-center">{item.executions}회</td>
            <td class="py-3 text-sm text-center">{item.submissions}회</td>
            <td class="py-3 text-sm text-center">{item.solveTime}</td>
            <td class="py-3 text-sm text-cyan-500 text-center">{item.avgScore}점</td>
            <td class="py-3 text-sm text-purple-500 text-center">{item.avgExp}XP</td>
          </tr>
        {/each}
      </tbody>
    </table>
  </div>

  <!-- 퀴즈 분포 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex justify-between items-center mb-4">
      <h3 class="font-bold">퀴즈 분포</h3>
      <select 
        class="form-select rounded-lg border-gray-200 text-sm"
        bind:value={selectedQuizType}
        on:change={handleQuizTypeChange}
      >
        {#each quizTypes as type}
          <option value={type}>{type.label}</option>
        {/each}
      </select>
    </div>
    <div class="w-full h-px bg-gray-100 mb-4"></div>
    <div class="h-[300px]">
      <canvas id="quizChart"></canvas>
    </div>
  </div>

  <!-- 학습 목록 -->
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    <div class="flex justify-between items-center mb-4">
      <h3 class="font-bold">구성원 목록</h3>
    </div>
    <table class="w-full">
      <thead>
        <tr class="border-b border-gray-100">
          <th class="text-center py-2 text-sm font-medium text-gray-500">
            <input type="checkbox" class="rounded text-cyan-500">
          </th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">이름</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">권한</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">학습 진행률</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">퀴즈 점수</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">실습 점수</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">푼 문제</th>
          <th class="text-center py-2 text-sm font-medium text-gray-500">이수 승인</th>
        </tr>
      </thead>
      <tbody>
        {#each learningList as item}
          <tr class="border-b border-gray-50">
            <td class="py-3 text-center">
              <input type="checkbox" class="rounded text-cyan-500">
            </td>
            <td class="py-3 text-sm text-center">{item.name}</td>
            <td class="py-3 text-sm text-center">{item.role}</td>
            <td class="py-3 text-sm text-center">{item.progress}</td>
            <td class="py-3 text-sm text-center">{item.quizScore}</td>
            <td class="py-3 text-sm text-center">{item.practiceScore}</td>
            <td class="py-3 text-sm text-center">{item.solvedProblems}</td>
            <td class="py-3 text-center">
              <button class="text-xs text-white bg-cyan-500 px-3 py-1 rounded-full hover:bg-cyan-600 transition-colors">승인</button>
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
    <div class="flex justify-center mt-4 gap-1">
      <button class="w-8 h-8 flex items-center justify-center rounded-lg border border-gray-200 text-gray-500">
        <i class="fas fa-chevron-left text-xs"></i>
      </button>
      <button class="w-8 h-8 flex items-center justify-center rounded-lg bg-cyan-500 text-white">1</button>
      <button class="w-8 h-8 flex items-center justify-center rounded-lg border border-gray-200 text-gray-500">2</button>
      <button class="w-8 h-8 flex items-center justify-center rounded-lg border border-gray-200 text-gray-500">
        <i class="fas fa-chevron-right text-xs"></i>
      </button>
    </div>
  </div>
</StatusLayout> 