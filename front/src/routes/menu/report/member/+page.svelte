<script lang="ts">
  import StatusLayout from '$lib/components/StatusLayout.svelte';
  import { onMount } from 'svelte';
  import { Chart, type ChartConfiguration } from 'chart.js/auto';

  // 총점 데이터
  const totalScore = {
    내점수: [15, 20, 25, 18],
    반평균점수: [12, 18, 22, 15],
    labels: ['1차시', '2차시', '3차시', '4차시'],
    전체학습점수: 120,
    목표학습점수: 118
  };

  // 선생님 한마디
  const teacherComment = {
    comment1: '길동이가 코딩에 재미를 느껴하며 하루하루 실력이 늘어가는 모습이 정말로 보기 좋더구나 항상 문제가 막혀도 끈기있게 방법을 찾아가며 문제를 해결하면서 주변 친구들도 같이 도와주는 리더쉽이 눈에 띄었단다.',
    comment2: '길동이는 분명 동에 번쩍 서에 번쩍 불려나가는 훌륭한 코딩 기사가 될거라고 확신한다!'
  };

  // 퍼널 데이터
  const funnelData = [
    { label: '문제 해결 역량', percentage: 32 },
    { label: '추상화 및 알고리즘 설계 역량', percentage: 21 },
    { label: '창의적 사고 역량', percentage: 19 },
    { label: '컴퓨팅 사고 역량', percentage: 17 },
    { label: '데이터 활용 역량', percentage: 11 }
  ];

  // 역량 전체 분석
  const roleAnalysis = [
    {
      title: 'API 적성에서는 전체적인 적성에 대한 정량적 평가 내용',
      details: [
        '내용내용내용내용내용내용내용내용내용내용',
        '내용내용내용내용내용내용내용내용내용내용',
        '내용내용내용내용내용내용내용내용내용내용'
      ]
    }
  ];

  // 역량별 세부 분석
  const roleDetailAnalysis = [
    {
      title: '문제 해결 역량',
      details: [
        '문제 해결 역량은 문제를 해결하는 능력을 측정하는 역량입니다.',
      ]
    },
    {
      title: '추상화 및 알고리즘 설계 역량',
      details: [
        '추상화 및 알고리즘 설계 역량은 추상화와 알고리즘을 설계하는 능력을 측정하는 역량입니다.',
      ]
    },
    {
      title: '창의적 사고 역량',
      details: [
        '창의적 사고 역량은 창의적인 사고를 하는 능력을 측정하는 역량입니다.',
      ]
    },
    {
      title: '컴퓨팅 사고 역량',
      details: [
        '컴퓨팅 사고 역량은 컴퓨팅 사고를 하는 능력을 측정하는 역량입니다.',
      ]
    },
    {
      title: '데이터 활용 역량',
      details: [
        '데이터 활용 역량은 데이터를 활용하는 능력을 측정하는 역량입니다.',
      ]
    }
  ];

  

  let chart: Chart;
  let pieChart: Chart;

  onMount(() => {
    const ctx = document.getElementById('scoreChart') as HTMLCanvasElement;
    if (ctx) {
      const config: ChartConfiguration = {
        type: 'line',
        data: {
          labels: totalScore.labels,
          datasets: [
            {
              label: '내 점수',
              data: totalScore.내점수,
              borderColor: 'rgb(99, 102, 241)',
              backgroundColor: 'rgb(99, 102, 241)',
              tension: 0.4,
              pointRadius: 4,
              pointBackgroundColor: 'white',
              pointBorderColor: 'rgb(99, 102, 241)',
              pointBorderWidth: 2
            },
            {
              label: '반 평균',
              data: totalScore.반평균점수,
              borderColor: 'rgb(203, 213, 225)',
              backgroundColor: 'rgb(203, 213, 225)',
              tension: 0.4,
              pointRadius: 4,
              pointBackgroundColor: 'white',
              pointBorderColor: 'rgb(203, 213, 225)',
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

    // 파이 차트 초기화
    const pieCtx = document.getElementById('pieChart') as HTMLCanvasElement;
    if (pieCtx) {
      const pieConfig: ChartConfiguration = {
        type: 'doughnut',
        data: {
          labels: funnelData.map(item => item.label),
          datasets: [{
            data: funnelData.map(item => item.percentage),
            backgroundColor: [
              'rgba(6, 182, 212, 1)',    // cyan-500
              'rgba(6, 182, 212, 0.8)',
              'rgba(6, 182, 212, 0.6)',
              'rgba(6, 182, 212, 0.4)',
              'rgba(6, 182, 212, 0.2)'
            ],
            borderWidth: 0
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          layout: {
            padding: 80
          },
          plugins: {
            legend: {
              display: false
            }
          }
        },
        plugins: [{
          id: 'outsideLabels',
          afterDraw(chart) {
            const { ctx, width, height } = chart;
            ctx.save();
            const xCenter = width / 2;
            const yCenter = height / 2;
            const radius = Math.min(width, height) * 0.325;

            chart.data.labels?.forEach((label, i) => {
              const value = chart.data.datasets[0].data[i] as number;
              const startAngle = (chart as any)._metasets[0].data[i].startAngle;
              const endAngle = (chart as any)._metasets[0].data[i].endAngle;
              const angle = startAngle + (endAngle - startAngle) / 2;

              // 선 시작점
              const x1 = xCenter + Math.cos(angle) * radius;
              const y1 = yCenter + Math.sin(angle) * radius;

              // 선 중간점 (꺾이는 지점)
              const x2 = xCenter + Math.cos(angle) * (radius + 20);
              const y2 = yCenter + Math.sin(angle) * (radius + 20);

              // 선 끝점
              const x3 = x2 + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? -30 : 30);
              const y3 = y2;

              // 선 그리기
              ctx.beginPath();
              ctx.moveTo(x1, y1);
              ctx.lineTo(x2, y2);
              ctx.lineTo(x3, y3);
              ctx.strokeStyle = '#999';
              ctx.lineWidth = 1;
              ctx.stroke();

              // 텍스트 정렬 설정
              ctx.textAlign = angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? 'right' : 'left';
              ctx.textBaseline = 'middle';
              ctx.fillStyle = '#666';
              ctx.font = '16px Arial';

              // 아이콘 추가
              const getIcon = (index: number) => {
                switch(index) {
                  case 0: return '\uf12e'; // puzzle-piece (fas fa-puzzle-piece)
                  case 1: return '\uf542'; // project-diagram (fas fa-project-diagram)
                  case 2: return '\uf0eb'; // lightbulb (fas fa-lightbulb)
                  case 3: return '\uf5dc'; // brain (fas fa-brain)
                  case 4: return '\uf080'; // chart-bar (fas fa-chart-bar)
                  default: return '';
                }
              };

              const getIconColor = (index: number) => {
                switch(index) {
                  case 0: return 'rgb(59, 130, 246)'; // blue-500
                  case 1: return 'rgb(168, 85, 247)'; // purple-500
                  case 2: return 'rgb(234, 179, 8)';  // yellow-500
                  case 3: return 'rgb(6, 182, 212)';  // cyan-500
                  case 4: return 'rgb(34, 197, 94)';  // green-500
                  default: return '#666';
                }
              };

              const xText = x3 + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? -10 : -10);
              
              // 아이콘 표시
              ctx.font = 'bold 14px "Font Awesome 5 Free"';  // bold 추가
              ctx.fillStyle = getIconColor(i);
              const iconX = xText + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? 20 : -2);
              ctx.fillText(getIcon(i), iconX, y3 - 15);

              // 역량명 표시
              ctx.fillStyle = '#666';
              ctx.font = '16px Arial';
              const textX = xText + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? 0 : 20);
              
              // 긴 텍스트를 두 줄로 나누기
              const words = (label as string).split(' ');
              const firstLine = words.slice(0, 2).join(' ');
              const secondLine = words.slice(2).join(' ');
              
              ctx.fillText(firstLine, textX, y3 - 15);
              if (secondLine) {
                ctx.fillText(secondLine, textX, y3);
              }

              // 퍼센트 표시 (다른 색상으로)
              ctx.fillStyle = 'rgb(6, 182, 212)';  // cyan-500
              ctx.font = 'bold 16px Arial';
              ctx.fillText(`${value}%`, textX, y3 + (secondLine ? 16 : 12));
            });
            ctx.restore();
          }
        }]
      };
      pieChart = new Chart(pieCtx, pieConfig);
    }
  });
</script>

<StatusLayout>
  <!-- 지치지 않는 도전을 하는 홍길동님! -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <h3 class="font-bold mb-6">지치지 않는 도전을 하는 홍길동님!</h3>
    
    <!-- 총점 점수 -->
    <div class="grid grid-cols-2 gap-6 mb-6">
      <div>
        <div class="text-sm text-gray-500 mb-2">총점 점수</div>
        <div class="h-[200px]">
          <canvas id="scoreChart"></canvas>
        </div>
      </div>
      <div class="flex items-center justify-center">
        <div class="text-center">
          <img src="/images/grade_a.png" alt="A등급" class="w-50 h-50 mx-auto mb-4" />
          <div class="flex gap-4 justify-center text-sm">
            <div>
              <div class="text-gray-500">전체 학습 점수</div>
              <div class="font-bold text-amber-500">{totalScore.전체학습점수}</div>
            </div>
            <div>
              <div class="text-gray-500">목표 학습 점수</div>
              <div class="font-bold text-amber-500">{totalScore.목표학습점수}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 선생님 한마디 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex items-center gap-2 mb-4">
      <i class="fas fa-user-circle text-2xl text-gray-400"></i>
      <h3 class="font-bold">선생님 한마디</h3>
    </div>
    <div class="space-y-4 text-gray-600">
      <p>{teacherComment.comment1}</p>
      <p>{teacherComment.comment2}</p>
    </div>
  </div>

  <!-- 역량 분포 차트 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex items-center gap-2 mb-6">
      <i class="fas fa-chart-pie text-cyan-500"></i>
      <h3 class="font-bold">역량 분포</h3>
    </div>
    <div class="h-[400px]">
      <canvas id="pieChart"></canvas>
    </div>
  </div>

  <!-- 역량별 세부 분석 -->
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    <div class="flex items-center gap-2 mb-6">
      <i class="fas fa-trophy text-amber-500"></i>
      <h3 class="font-bold">역량별 세부 분석</h3>
    </div>
    
    <!-- <div class="space-y-4">
      {#each roleAnalysis as analysis}
        <div class="rounded-lg bg-gray-50 p-4">
          <h4 class="font-medium mb-4">{analysis.title}</h4>
          <div class="space-y-2">
            {#each analysis.details as detail}
              <p class="text-gray-600">{detail}</p>
            {/each}
          </div>
        </div>
      {/each}
    </div> -->

    <!-- 접힌 섹션들 -->
    {#each roleDetailAnalysis as analysis, i}
      <div class="mt-4">
        <button class="w-full flex items-center justify-between p-4 rounded-lg bg-gray-50 hover:bg-gray-100">
          <div class="flex items-center gap-2">
            {#if i === 0}
              <i class="fas fa-puzzle-piece text-blue-500"></i>
            {:else if i === 1}
              <i class="fas fa-project-diagram text-purple-500"></i>
            {:else if i === 2}
              <i class="fas fa-lightbulb text-yellow-500"></i>
            {:else if i === 3}
              <i class="fas fa-brain text-cyan-500"></i>
            {:else if i === 4}
              <i class="fas fa-chart-bar text-green-500"></i>
            {/if}
            <span class="font-medium">{analysis.title}</span>
          </div>
          <i class="fas fa-chevron-down text-gray-400"></i>
        </button>
      </div>
    {/each}
  </div>
</StatusLayout>
