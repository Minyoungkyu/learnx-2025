<script lang="ts">

  // 차시 데이터
  let lectureList = [
    // {
    //   title: "프로그램과 프로그래밍 언어, 출력과 순차구조",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ],
    // },
    // {
    //   title: "변수와 자료",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ]
    // },
    // {
    //   title: "입력과 자료형 변환",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ]
    // },
    // {
    //   title: "입력과 자료형 변환",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ]
    // },
    // {
    //   title: "입력과 자료형 변환",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ]
    // },
    // {
    //   title: "입력과 자료형 변환",
    //   progress: 100,
    //   contents: [
    //     {
    //     type: "video",
    //     title: "01-수업목표",
    //     time: "9분",
    //   },
    //   {
    //     type: "pdf",
    //     title: "02-프로그램과 프로그래밍 언어",
    //   },
    //   {
    //     type: "pdf",
    //     title: "03-출력",
    //   },
    //   {
    //     type: "text",
    //     title: "실습1-5라는 숫자를 출력해 봅시다",
    //   }
    //   ]
    // },
    
  ];

  // 달성 업적 데이터
  const achievements = [
    { 
      id: 1, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 2, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 3, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 4, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 5, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 6, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    },
    { 
      id: 7, 
      nickname: '홍종우',
      level: 'LV.3',
      badges: ['문제해결', '창의력', '컴퓨팅사고']
    }
  ];

  let currentPage = 1;
  const itemsPerPage = 7;
  const totalPages = Math.ceil(achievements.length / itemsPerPage);

  $: paginatedAchievements = achievements.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage
  );

  function getBadgeIcon(badge: string) {
    switch(badge) {
      case '문제해결':
        return 'fas fa-puzzle-piece text-blue-500';
      case '창의력':
        return 'fas fa-lightbulb text-yellow-500';
      case '컴퓨팅사고':
        return 'fas fa-brain text-cyan-500';
      default:
        return 'fas fa-star text-gray-500';
    }
  }

  // SVG path points for positioning circles (모든 좌표를 퍼센트로 통일)
  let pathPoints = [
    // {
    //   start: { x: 60, y: 25 },
    //   cp1: { x: 41.632792072246275, y: 17 },
    //   cp2: { x: 36.85153039924414, y: 40.25 },
    //   end: { x: 41.77341741556987, y: 42.75 }
    // },
    // {
    //   start: { x: 26.304629649974732, y: 48.25 },
    //   cp1: { x: 49.08593526839665, y: 43.5 },
    //   cp2: { x: 57.38283052448858, y: 32 },
    //   end: { x: 60.05471204763684, y: 46.5 }
    // },
    // {
    //   start: { x: 76.64850255982071, y: 66.5 },
    //   cp1: { x: 60.05471204763684, y: 66 },
    //   cp2: { x: 40.92966535562831, y: 40.5 },
    //   end: { x: 41.49216672892268, y: 65.25 }
    // },
    // {
    //   start: { x: 33.757772846125114, y: 81.25 },
    //   cp1: { x: 49.226560611720245, y: 74.75 },
    //   cp2: { x: 54.99219968798752, y: 65.25 },
    //   end: { x: 60.05471204763684, y: 72.5 }
    // }
  ];

  let isEditMode = false; // 에디팅 모드 제어 변수 추가

  type Point = { x: number; y: number };
  type CurvePoint = {
    start: Point;
    cp1: Point;
    cp2: Point;
    end: Point;
  };

  // 3차 베지어 커브 포인트 계산 함수
  function cubicBezier(t: number, curve: any) {
    const mt = 1 - t;
    return {
      x: mt * mt * mt * curve.start.x + 3 * mt * mt * t * curve.cp1.x + 3 * mt * t * t * curve.cp2.x + t * t * t * curve.end.x,
      y: mt * mt * mt * curve.start.y + 3 * mt * mt * t * curve.cp1.y + 3 * mt * t * t * curve.cp2.y + t * t * t * curve.end.y
    };
  }

  // Calculate positions for each lecture point along the bezier curves
  $: lecturePositions = lectureList.map((_, index) => {
    // 전체 진행도 계산 (0 ~ 1)
    const progress = index / (lectureList.length - 1);
    
    // 커브 개수에 따라 구간 나누기
    const segmentLength = 1 / (pathPoints.length);
    const curveIndex = Math.min(Math.floor(progress / segmentLength), pathPoints.length - 1);
    
    // 현재 커브 구간 내에서의 진행도 계산 (0 ~ 1)
    const segmentProgress = (progress - (curveIndex * segmentLength)) / segmentLength;
    const t = Math.max(0, Math.min(1, segmentProgress));
    
    // 현재 커브에서의 위치 계산
    return cubicBezier(t, pathPoints[curveIndex]);
  });

  // SVG viewBox 설정
  const svgViewBox = "0 0 100 100";

  // SVG 경로 문자열 생성 (베지어 커브)
  $: pathString = pathPoints.map((curve, i) => 
    i === 0 
      ? `M ${curve.start.x} ${curve.start.y} C ${curve.cp1.x} ${curve.cp1.y}, ${curve.cp2.x} ${curve.cp2.y}, ${curve.end.x} ${curve.end.y}`
      : `C ${curve.cp1.x} ${curve.cp1.y}, ${curve.cp2.x} ${curve.cp2.y}, ${curve.end.x} ${curve.end.y}`
  ).join(' ');

  let isDragging = false;
  let selectedPoint: Point | null = null;
  let selectedCurveIndex = -1;
  let selectedPointType: keyof CurvePoint | '' = '';

  function handleMouseDown(event: MouseEvent, curve: CurvePoint, i: number, pointType: keyof CurvePoint) {
    isDragging = true;
    selectedPoint = curve[pointType];
    selectedCurveIndex = i;
    selectedPointType = pointType;
  }

  function handleMouseMove(event: MouseEvent) {
    if (!isDragging || !selectedPoint || selectedPointType === '') return;

    const svg = event.currentTarget as SVGElement;
    const rect = svg.getBoundingClientRect();
    const x = ((event.clientX - rect.left) / rect.width) * 100;
    const y = ((event.clientY - rect.top) / rect.height) * 100;

    // 좌표 범위 제한
    const boundedX = Math.max(0, Math.min(100, x));
    const boundedY = Math.max(0, Math.min(100, y));

    pathPoints = pathPoints.map((curve, i) => {
      if (i === selectedCurveIndex) {
        return {
          ...curve,
          [selectedPointType]: { x: boundedX, y: boundedY }
        };
      }
      return curve;
    });

    // pathPoints 업데이트될 때마다 콘솔에 출력
    console.log('Updated pathPoints:', JSON.stringify(pathPoints, null, 2));
  }

  function handleMouseUp() {
    if (isDragging) {
      // 드래그가 끝날 때 최종 pathPoints 출력
      console.log('Final pathPoints:', JSON.stringify(pathPoints, null, 2));
    }
    isDragging = false;
    selectedPoint = null;
    selectedCurveIndex = -1;
    selectedPointType = '';
  }
</script>

<div class="p-6 max-w-7xl mx-auto">
  <!-- 모험맵 -->
  <div class="bg-white rounded-xl p-6 shadow-sm mb-6 outline outline-2 outline-gray-100">
    <h3 class="font-bold mb-6">모험맵</h3>
    <div class="relative h-[400px] bg-sky-500 rounded-lg overflow-hidden">
      <img src="/images/map1.png" alt="모험맵" class="w-full h-full object-cover" />
      
      <!-- 점선 경로 -->
      <svg 
        class="absolute inset-0 w-full h-full" 
        viewBox={svgViewBox} 
        preserveAspectRatio="none"
        on:mousemove={isEditMode ? handleMouseMove : undefined}
        on:mouseup={isEditMode ? handleMouseUp : undefined}
        on:mouseleave={isEditMode ? handleMouseUp : undefined}
      >
        <!-- 베지어 커브 -->
        <path 
          d={pathString} 
          stroke="white" 
          stroke-width="1" 
          stroke-dasharray="2,2" 
          fill="none"
        />
        
        <!-- 제어점 시각화 -->
        {#if isEditMode}
          {#each pathPoints as curve, i}
            <!-- 제어점 선 -->
            <path 
              d={`M ${curve.start.x} ${curve.start.y} L ${curve.cp1.x} ${curve.cp1.y} M ${curve.end.x} ${curve.end.y} L ${curve.cp2.x} ${curve.cp2.y}`}
              stroke="rgba(255,255,0,0.3)" 
              stroke-width="0.5"
              stroke-dasharray="1,1"
            />
            
            <!-- 제어점들 (드래그 가능) -->
            <circle 
              cx={curve.start.x} 
              cy={curve.start.y} 
              r="2" 
              fill="yellow"
              style="cursor: move;"
              on:mousedown={(e) => handleMouseDown(e, curve, i, 'start')}
            />
            <circle 
              cx={curve.cp1.x} 
              cy={curve.cp1.y} 
              r="2" 
              fill="yellow"
              style="cursor: move;"
              on:mousedown={(e) => handleMouseDown(e, curve, i, 'cp1')}
            />
            <circle 
              cx={curve.cp2.x} 
              cy={curve.cp2.y} 
              r="2" 
              fill="yellow"
              style="cursor: move;"
              on:mousedown={(e) => handleMouseDown(e, curve, i, 'cp2')}
            />
            <circle 
              cx={curve.end.x} 
              cy={curve.end.y} 
              r="2" 
              fill="yellow"
              style="cursor: move;"
              on:mousedown={(e) => handleMouseDown(e, curve, i, 'end')}
            />
          {/each}
        {/if}
      </svg>

      <!-- 차시 포인트 -->
      {#each lectureList as lecture, i}
        <div 
          class="absolute w-10 h-10 -translate-x-1/2 -translate-y-1/2 cursor-pointer group"
          style="left: {lecturePositions[i].x}%; top: {lecturePositions[i].y}%;"
        >
          <!-- 배경 원 -->
          <div class="absolute inset-0 bg-white rounded-full shadow-lg"></div>
          
          <!-- 진행도에 따른 색상 원 -->
          <div 
            class="absolute inset-1 rounded-full {lecture.progress === 100 ? 'bg-green-500' : 'bg-amber-500'} 
            flex items-center justify-center text-white font-bold"
          >
            {i + 1}
          </div>

          <!-- 툴팁 -->
          <div class="absolute bottom-full left-1/2 -translate-x-1/2 mb-2 w-48 bg-white rounded-lg shadow-lg p-2 
            opacity-0 group-hover:opacity-100 transition-opacity duration-200 pointer-events-none z-10">
            <p class="text-sm font-medium text-gray-900 truncate">{lecture.title}</p>
            <div class="mt-1 h-1 w-full bg-gray-200 rounded">
              <div 
                class="h-full rounded {lecture.progress === 100 ? 'bg-green-500' : 'bg-amber-500'}" 
                style="width: {lecture.progress}%"
              ></div>
            </div>
            <p class="text-xs text-gray-500 mt-1">진행률 {lecture.progress}%</p>
          </div>
        </div>
      {/each}
    </div>
  </div>

  <!-- 달성 업적 -->
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100">
    <h3 class="font-bold mb-6">달성 업적</h3>
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead>
          <tr class="border-b">
            <th class="py-2 text-left">프로필</th>
            <th class="py-2 text-left">닉네임</th>
            <th class="py-2 text-left">레벨</th>
            <th class="py-2 text-left">달성업적</th>
          </tr>
        </thead>
        <tbody>
          {#each paginatedAchievements as achievement}
            <tr class="border-b last:border-b-0">
              <td class="py-4">
                <div class="w-8 h-8 rounded-full bg-gray-200 flex items-center justify-center">
                  <i class="fas fa-user text-gray-400"></i>
                </div>
              </td>
              <td>{achievement.nickname}</td>
              <td>{achievement.level}</td>
              <td>
                <div class="flex gap-2">
                  {#each achievement.badges as badge}
                    <div class="flex items-center gap-1">
                      <i class={getBadgeIcon(badge)}></i>
                    </div>
                  {/each}
                </div>
              </td>
            </tr>
          {/each}
        </tbody>
      </table>
    </div>

    <!-- 페이지네이션 -->
    <div class="flex justify-center items-center gap-2 mt-4">
      <button 
        class="w-8 h-8 flex items-center justify-center rounded-lg hover:bg-gray-100"
        disabled={currentPage === 1}
        on:click={() => currentPage = Math.max(1, currentPage - 1)}
      >
        <i class="fas fa-chevron-left text-gray-400"></i>
      </button>
      
      {#each Array(totalPages) as _, i}
        <button 
          class="w-8 h-8 flex items-center justify-center rounded-lg {currentPage === i + 1 ? 'bg-cyan-500 text-white' : 'hover:bg-gray-100'}"
          on:click={() => currentPage = i + 1}
        >
          {i + 1}
        </button>
      {/each}
      
      <button 
        class="w-8 h-8 flex items-center justify-center rounded-lg hover:bg-gray-100"
        disabled={currentPage === totalPages}
        on:click={() => currentPage = Math.min(totalPages, currentPage + 1)}
      >
        <i class="fas fa-chevron-right text-gray-400"></i>
      </button>
    </div>
  </div>
</div>
