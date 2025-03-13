<script lang="ts">

  // 차시 데이터
  let lectureList = [
    {
      title: "프로그램과 프로그래밍 언어, 출력과 순차구조",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ],
    },
    {
      title: "변수와 자료",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 80,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 30,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 30,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 30,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 30,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
    {
      title: "입력과 자료형 변환",
      progress: 100,
      contents: [
        {
        type: "video",
        title: "01-수업목표",
        time: "9분",
      },
      {
        type: "pdf",
        title: "02-프로그램과 프로그래밍 언어",
      },
      {
        type: "pdf",
        title: "03-출력",
      },
      {
        type: "text",
        title: "실습1-5라는 숫자를 출력해 봅시다",
      }
      ]
    },
  ];

  // 달성 업적 데이터
  const members = [
    { 
      id: 1, 
      nickname: '홍종우',
      level: 'Lv.3',
      badges: ['/images/badge1.png', '/images/badge2.png', '/images/badge3.png'],
      lectureIndex: 0,
      contentsIndex: 3
    },
    { 
      id: 2, 
      nickname: '김민수',
      level: 'Lv.2',
      badges: ['/images/badge1.png', '/images/badge2.png'],
      lectureIndex: 11,
      contentsIndex: 3
    },
    { 
      id: 3, 
      nickname: '이지은',
      level: 'Lv.4',
      badges: ['/images/badge1.png', '/images/badge2.png', '/images/badge3.png'],
      lectureIndex: 2,
      contentsIndex: 1
    },
    { 
      id: 4, 
      nickname: '박준호',
      level: 'Lv.3',
      badges: ['/images/badge1.png', '/images/badge3.png'],
      lectureIndex: 3,
      contentsIndex: 0
    },
    { 
      id: 5, 
      nickname: '최유진',
      level: 'Lv.2',
      badges: ['/images/badge2.png', '/images/badge3.png'],
      lectureIndex: 4,
      contentsIndex: 2
    },
    { 
      id: 6, 
      nickname: '정다인',
      level: 'Lv.1',
      badges: ['/images/badge1.png'],
      lectureIndex: 5,
      contentsIndex: 1
    },
    { 
      id: 7, 
      nickname: '강현우',
      level: 'Lv.3',
      badges: ['/images/badge1.png', '/images/badge2.png', '/images/badge3.png'],
      lectureIndex: 6,
      contentsIndex: 3
    },
    { 
      id: 8, 
      nickname: '김영훈',
      level: 'Lv.3',
      badges: ['/images/badge1.png', '/images/badge2.png', '/images/badge3.png'],
      lectureIndex: 11,
      contentsIndex: 0
    }
  ];

  let currentPage = 1;
  const itemsPerPage = 7;
  const totalPages = Math.ceil(members.length / itemsPerPage);

  $: paginatedMembers = members.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage
  );

  // SVG path points for positioning circles (모든 좌표를 퍼센트로 통일)
  let pathPoints = [
    {
      start: { x: 61.05471204763684, y: 75.5 },
      cp1: { x: 54.99219968798752, y: 65.25 },
      cp2: { x: 49.226560611720245, y: 74.75 },
      end: { x: 41.49216672892268, y: 65.25 }
    },
    {
      start: { x: 41.49216672892268, y: 65.25 },
      cp1: { x: 40.92966535562831, y: 40.5 },
      cp2: { x: 60.05471204763684, y: 66 },
      end: { x: 60.05471204763684, y: 46.5 }
    },
    {
      start: { x: 60.05471204763684, y: 46.5 },
      cp1: { x: 57.38283052448858, y: 32 },
      cp2: { x: 49.08593526839665, y: 43.5 },
      end: { x: 41.77341741556987, y: 42.75 }
    },
    {
      start: { x: 41.77341741556987, y: 42.75 },
      cp1: { x: 36.85153039924414, y: 40.25 },
      cp2: { x: 41.632792072246275, y: 17 },
      end: { x: 60, y: 25 }
    }
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
    const progress = index / (lectureList.length);
    
    // 커브 개수에 따라 구간 나누기
    const segmentLength = 1 / (pathPoints.length);
    const curveIndex = Math.min(Math.floor(progress / segmentLength), pathPoints.length - 1);
    
    // 현재 커브 구간 내에서의 진행도 계산 (0 ~ 1)
    const segmentProgress = (progress - (curveIndex * segmentLength)) / segmentLength;
    const t = Math.max(0, Math.min(1, segmentProgress));

    // console.log("--------------------------------");
    // console.log(`index: ${index}`);
    // console.log(`progress: ${progress}`);
    // console.log(`segmentLength: ${segmentLength}`);
    // console.log(`curveIndex: ${curveIndex}`);
    // console.log(`segmentProgress: ${segmentProgress}`);
    // console.log(`t: ${t}`);
    // console.log("--------------------------------");
    
    // 현재 커브에서의 위치 계산
    return cubicBezier(t, pathPoints[curveIndex]);
  });

  let hoveredLectureIndex = -1;
  let hoveredMemberIndex = -1;
  let lastHoverDistance = Infinity;

  function handleMouseEnter(index: number, type: 'lecture' | 'member', event: MouseEvent) {
    const rect = (event.currentTarget as HTMLElement).getBoundingClientRect();
    const distance = Math.sqrt(
      Math.pow(event.clientX - (rect.left + rect.width/2), 2) + 
      Math.pow(event.clientY - (rect.top + rect.height/2), 2)
    );

    if (distance < lastHoverDistance) {
      hoveredLectureIndex = type === 'lecture' ? index : -1;
      hoveredMemberIndex = type === 'member' ? index : -1;
      lastHoverDistance = distance;
    }
  }

  function handleMouseLeave() {
    hoveredLectureIndex = -1;
    hoveredMemberIndex = -1;
    lastHoverDistance = Infinity;
  }

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

  // Calculate positions for each member along the bezier curves
  $: memberPositions = members.map(member => {
    // lectureIndex가 유효한 범위 내에 있는지 확인
    const validLectureIndex = Math.min(member.lectureIndex, lectureList.length - 1);
    const lecture = lectureList[validLectureIndex];
    
    // 기본 진행도 (현재 차시의 시작 위치)
    const baseProgress = validLectureIndex / lectureList.length;
    
    // 다음 차시까지의 거리 계산
    const distanceToNextLecture = 1 / lectureList.length;
    
    // 콘텐츠 진행도를 현재 차시와 다음 차시 사이의 거리에 적용
    const contentProgress = (member.contentsIndex / lecture.contents.length) * distanceToNextLecture;
    
    // 전체 진행도 계산
    const progress = baseProgress + contentProgress;
    
    // 커브 개수에 따라 구간 나누기
    const segmentLength = 1 / pathPoints.length;
    const curveIndex = Math.min(Math.floor(progress / segmentLength), pathPoints.length - 1);
    
    // 현재 커브 구간 내에서의 진행도 계산 (0 ~ 1)
    const segmentProgress = (progress - (curveIndex * segmentLength)) / segmentLength;
    const t = Math.max(0, Math.min(1, segmentProgress));
    
    // 현재 커브에서의 위치 계산
    const basePosition = cubicBezier(t, pathPoints[curveIndex]);
    
    // 약간의 랜덤 오프셋 추가 (멤버들이 겹치지 않도록)
    const offset = 2;
    const angle = (member.id * Math.PI * 0.5); // 각 멤버마다 다른 각도
    return {
      x: basePosition.x + Math.cos(angle) * offset,
      y: basePosition.y + Math.sin(angle) * offset
    };
  });
</script>

<div class="p-6 w-[1300px] mx-auto">
  <!-- 모험맵 -->
  <div class="bg-white rounded-xl p-6 mb-6">
    <div class="border-b border-gray-200 pb-4 mb-6">
      <h2 class="text-2xl font-bold">모험맵</h2>
    </div>
    <div class="relative h-[700px] rounded-lg overflow-hidden">
      <img src="/images/map1.png" alt="모험맵" class="w-full h-full object-cover" />
      
      <!-- 점선 경로 -->
      <!-- svelte-ignore a11y_no_static_element_interactions -->
      <svg 
        class="absolute inset-0 w-full h-full" 
        viewBox={svgViewBox} 
        preserveAspectRatio="none"
        on:mousemove={isEditMode ? handleMouseMove : undefined}
        on:mouseup={isEditMode ? handleMouseUp : undefined}
        on:mouseleave={isEditMode ? handleMouseUp : undefined}
      >
        
        
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
        <!-- svelte-ignore a11y_no_static_element_interactions -->
        <div 
          class="absolute w-10 h-10 -translate-x-1/2 -translate-y-1/2 cursor-pointer"
          style="left: {lecturePositions[i].x}%; top: {lecturePositions[i].y}%;"
          on:mouseenter={(e) => handleMouseEnter(i, 'lecture', e)}
          on:mouseleave={handleMouseLeave}
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
        </div>

        <!-- 차시 툴팁 (분리된 위치) -->
        <div 
          class="absolute pointer-events-none z-20"
          style="left: {lecturePositions[i].x}%; top: {lecturePositions[i].y}%;"
        >
          <div class="absolute bottom-full left-1/2 -translate-x-1/2 -translate-y-2 w-48 bg-white rounded-lg shadow-lg p-2 
            transition-opacity duration-200 {hoveredLectureIndex === i ? 'opacity-100' : 'opacity-0'}"
          >
            <p class="text-sm font-medium text-gray-900">{lecture.title}</p>
            <p class="text-xs text-gray-500">진행도: {lecture.progress}%</p>
          </div>
        </div>
      {/each}

      <!-- 멤버 캐릭터 -->
      {#each members as member, i}
        <!-- svelte-ignore a11y_no_static_element_interactions -->
        <div 
          class="absolute w-6 h-6 -translate-x-1/2 -translate-y-1/2 cursor-pointer"
          style="left: {memberPositions[i].x}%; top: {memberPositions[i].y}%;"
          on:mouseenter={(e) => handleMouseEnter(i, 'member', e)}
          on:mouseleave={handleMouseLeave}
        >
          <!-- 멤버 아바타 -->
          <div class="absolute inset-0 bg-gray-200 rounded-full flex items-center justify-center">
            <i class="fas fa-user text-gray-600 text-xs"></i>
          </div>
        </div>

        <!-- 멤버 툴팁 -->
        <div 
          class="absolute pointer-events-none z-20"
          style="left: {memberPositions[i].x}%; top: {memberPositions[i].y}%;"
        >
          <div class="absolute bottom-full left-1/2 -translate-x-1/2 -translate-y-2 w-48 bg-white rounded-lg shadow-lg p-2 
            transition-opacity duration-200 {hoveredMemberIndex === i ? 'opacity-100' : 'opacity-0'}">
            <div class="flex items-center gap-2">
              <div class="w-6 h-6 rounded-full bg-gray-200 flex items-center justify-center">
                <i class="fas fa-user text-gray-600 text-xs"></i>
              </div>
              <div>
                <p class="text-sm font-medium text-gray-900">{member.nickname}</p>
                <p class="text-xs text-gray-500">{member.level}</p>
              </div>
            </div>
            <div class="mt-2 flex gap-1">
              {#each member.badges as badge}
                <img src={badge} alt="뱃지" class="w-6 h-6" />
              {/each}
            </div>
          </div>
        </div>
      {/each}
    </div>
  </div>

  <!-- 달성 업적 -->
  <div class="bg-white rounded-xl p-6 shadow-sm outline outline-2 outline-gray-100 w-[1000px] mx-auto">
    <h3 class="font-bold mb-6">학습 중인 멤버</h3>
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead>
          <tr class="border-b">
            <th class="py-2 text-center w-30">프로필</th>
            <th class="py-2 text-center w-40">닉네임</th>
            <th class="py-2 text-center w-40">레벨</th>
            <th class="py-2 text-center">보유 뱃지</th>
          </tr>
        </thead>
        <tbody>
          {#each paginatedMembers as member}
            <tr class="border-b last:border-b-0">
              <td class="py-4 text-center w-20">
                <div class="w-8 h-8 rounded-full bg-gray-200 flex items-center justify-center mx-auto">
                  <i class="fas fa-user text-gray-400"></i>
                </div>
              </td>
              <td class="text-center w-32">{member.nickname}</td>
              <td class="text-center w-24">{member.level}</td>
              <td>
                <div class="flex gap-2">
                  {#each member.badges as badge}
                    <div class="flex items-center gap-1">
                      <img src={badge} alt="뱃지" class="w-6 h-6" />
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
