<script lang="ts">
  import StatusLayout from '$lib/components/StatusLayout.svelte';
  import { onMount } from 'svelte';
  import { Chart, type ChartConfiguration } from 'chart.js/auto';

  // 역량 분포 데이터
  const roleData = [
    { label: '문제 해결 역량', count: 5 },
    { label: '추상화 및 알고리즘 설계 역량', count: 4 },
    { label: '창의적 사고 역량', count: 2 },
    { label: '컴퓨팅 사고 역량', count: 1 },
    { label: '데이터 활용 역량', count: 1 }
  ];

  // 총 학생 수 계산
  const totalStudents = roleData.reduce((sum, item) => sum + item.count, 0);

  // 퍼센트 계산
  const roleDataWithPercentage = roleData.map(item => ({
    ...item,
    percentage: Math.round((item.count / totalStudents) * 100)
  }));

  // 키워드 분포 데이터
  const keywordData = [
    { label: '적극적인', count: 7 },
    { label: '창의적인', count: 6 },
    { label: '성실한', count: 5 },
    { label: '집중하는', count: 4 },
    { label: '응용하는', count: 3 }
  ];

  // 총 키워드 수 계산
  const totalKeywords = keywordData.reduce((sum, item) => sum + item.count, 0);

  // 퍼센트 계산
  const keywordDataWithPercentage = keywordData.map(item => ({
    ...item,
    percentage: Math.round((item.count / totalKeywords) * 100)
  }));

  let pieChart: Chart;
  let keywordPieChart: Chart;

  onMount(() => {
    const pieCtx = document.getElementById('pieChart') as HTMLCanvasElement;
    if (pieCtx) {
      const pieConfig: ChartConfiguration = {
        type: 'doughnut',
        data: {
          labels: roleDataWithPercentage.map(item => item.label),
          datasets: [{
            data: roleDataWithPercentage.map(item => item.percentage),
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
              ctx.font = 'bold 14px "Font Awesome 5 Free"';
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

              // 명수 표시 (다른 색상으로)
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

    // 키워드 파이 차트 초기화
    const keywordPieCtx = document.getElementById('keywordPieChart') as HTMLCanvasElement;
    if (keywordPieCtx) {
      const keywordPieConfig: ChartConfiguration = {
        type: 'doughnut',
        data: {
          labels: keywordDataWithPercentage.map(item => item.label),
          datasets: [{
            data: keywordDataWithPercentage.map(item => item.percentage),
            backgroundColor: [
              'rgba(168, 85, 247, 1)',    // purple-500
              'rgba(168, 85, 247, 0.8)',
              'rgba(168, 85, 247, 0.6)',
              'rgba(168, 85, 247, 0.4)',
              'rgba(168, 85, 247, 0.2)'
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
                  case 0: return '\uf51c'; // smile-beam
                  case 1: return '\uf0eb'; // lightbulb
                  case 2: return '\uf005'; // star
                  case 3: return '\uf04b'; // play
                  case 4: return '\uf0ad'; // wrench
                  default: return '';
                }
              };

              const getIconColor = (index: number) => {
                switch(index) {
                  case 0: return 'rgb(244, 114, 182)'; // pink-500
                  case 1: return 'rgb(234, 179, 8)';   // yellow-500
                  case 2: return 'rgb(34, 197, 94)';   // green-500
                  case 3: return 'rgb(59, 130, 246)';  // blue-500
                  case 4: return 'rgb(249, 115, 22)';  // orange-500
                  default: return '#666';
                }
              };

              const xText = x3 + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? -10 : -10);
              
              // 아이콘 표시
              ctx.font = 'bold 14px "Font Awesome 5 Free"';
              ctx.fillStyle = getIconColor(i);
              const iconX = xText + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? 20 : -2);
              ctx.fillText(getIcon(i), iconX, y3 - 15);

              // 키워드 표시
              ctx.fillStyle = '#666';
              ctx.font = '16px Arial';
              const textX = xText + (angle > Math.PI / 2 && angle < 3 * Math.PI / 2 ? 0 : 20);
              ctx.fillText((label as string), textX, y3 - 15);

              // 퍼센트 표시 (다른 색상으로)
              ctx.fillStyle = 'rgb(168, 85, 247)';  // purple-500
              ctx.font = 'bold 16px Arial';
              ctx.fillText(`${value}%`, textX, y3 + 12);
            });
            ctx.restore();
          }
        }]
      };
      keywordPieChart = new Chart(keywordPieCtx, keywordPieConfig);
    }
  });
</script>

<svelte:head>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</svelte:head>

<StatusLayout>
  <!-- 학생 상위 역량 분포 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <div class="flex items-center gap-2 mb-6">
      <i class="fas fa-chart-pie text-cyan-500"></i>
      <h3 class="font-bold">학생 상위 역량 분포</h3>
    </div>
    <div class="h-[400px]">
      <canvas id="pieChart"></canvas>
    </div>
  </div>

  <!-- 학생 상위 키워드 분포 -->
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    <div class="flex items-center gap-2 mb-6">
      <i class="fas fa-chart-pie text-purple-500"></i>
      <h3 class="font-bold">학생 상위 키워드 분포</h3>
    </div>
    <div class="h-[400px]">
      <canvas id="keywordPieChart"></canvas>
    </div>
  </div>
</StatusLayout> 