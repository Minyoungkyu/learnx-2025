<script lang="ts">
    import { writable } from 'svelte/store';
    import type { Writable } from 'svelte/store';
    import { SvelteFlow, MarkerType, Background, Controls, MiniMap, type Edge, type Node, ConnectionMode, useSvelteFlow } from '@xyflow/svelte';
   
    import '@xyflow/svelte/dist/style.css';

    import ResizeAbleNode from './ResizeAbleNode2.svelte';
    import CustomEdge from './CustomEdge2.svelte';
    import Sidebar from './Sidebar.svelte';

    import { useDnD } from './utils';

    const { screenToFlowPosition } = useSvelteFlow();
    const type = useDnD();

    const onDragOver = (event: DragEvent) => {
    event.preventDefault();

    if (event.dataTransfer) {
        event.dataTransfer.dropEffect = 'move';
        }
    };

    let { isEditMode } = $props<{ isEditMode: Writable<boolean> }>();
    
    const onDrop = (event: DragEvent) => {
        event.preventDefault();
    
        if (!$type) {
        return;
        }
    
        const position = screenToFlowPosition({
        x: event.clientX - 50,
        y: event.clientY - 25
        });
    
        const newNode = {
        id: `${$nodes.length + 1}`,
        type: 'ResizeAbleNode',
        position,
        data: { label: `Node ${$nodes.length + 1}`, styleData: baseData},
        style: nodeStyle,
        width: 100,
        height: 50
        } satisfies Node;
    
        $nodes.push(newNode);
        $nodes = $nodes;
    };

    const nodeTypes = {
        ResizeAbleNode
    };

    const edgeTypes = {
        default: CustomEdge
    };

    const nodeStyle =
    'background: #fff; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;';

    const edgeStyle =
    'stroke: #000; stroke-width: 1; stroke-dasharray: 0;';

    const baseData = {
        backgroundColor: '#ffffff',
        borderWidth: '1px',
        borderStyle: 'solid',
        borderColor: '#000',
        borderRadius: '15px',
        fontSize: '12px',
        bold: false
    }

    const edgeStyleData = {
        label: '',
        type: 'bezier',
        marker: 'none',
        color: '#000',
        width: 1,
        dasharray: '0'
    }

    const initialNodes: Node[] = [
        {
            id: '1',
            type: 'ResizeAbleNode',
            width: 120,
            height: 50,
            data: {
                label: 'TMD_파이썬_리스트',
                styleData: {
                    backgroundColor: '#8F82C8',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 150,
                y: 100
            },
            style: 'background: #8F82C8; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '2', 
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: '1.개념',
                styleData: {
                    backgroundColor: '#E0E622',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 0,
                y: 200
            },
            style: 'background: #E0E622; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '3',
            type: 'ResizeAbleNode', 
            width: 100,
            height: 50,
            data: {
                label: '2.생성',
                styleData: {
                    backgroundColor: '#F86D38',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 150,
                y: 200
            },
            style: 'background: #F86D38; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '4',
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: '3.인덱스',
                styleData: {
                    backgroundColor: '#A1C5F9',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 300,
                y: 200
            },
            style: 'background: #A1C5F9; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '5',
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: '데이터구조',
                styleData: {
                    backgroundColor: '#F6F9A3',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 0,
                y: 300
            },
            style: 'background: #F6F9A3; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '6',
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: 'List() 함수',
                styleData: {
                    backgroundColor: '#F9B9A1',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 150,
                y: 300
            },
            style: 'background: #F9B9A1; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '7',
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: '메타데이터',
                styleData: {
                    backgroundColor: '#F6F9A3',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 0,
                y: 400
            },
            style: 'background: #F6F9A3; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        },
        {
            id: '8',
            type: 'ResizeAbleNode',
            width: 100,
            height: 50,
            data: {
                label: 'String split 함수',
                styleData: {
                    backgroundColor: '#F9B9A1',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    borderColor: '#000',
                    borderRadius: '15px',
                    fontSize: '12px',
                    bold: false
                }
            },
            position: {
                x: 150,
                y: 400
            },
            style: 'background: #F9B9A1; border: 1px solid black; border-radius: 15px; font-size: 12px; display: flex; align-items: center; justify-content: center;',
            draggable: false,
            selectable: false,
            deletable: false,
            connectable: false
        }
    ];
   
    const initialEdges: Edge[] = [
        {
            id: 'edge-1',
            source: '1',
            sourceHandle: 'c',
            target: '2',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '1차시',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: "1차시",
            type: "bezier",
            selectable: false,
            deletable: false
        },
        {
            id: 'edge-2',
            source: '2',
            sourceHandle: 'c',
            target: '5',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '개념 심화',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '개념 심화',
            type: "bezier",
            selectable: false,
            deletable: false
        },
        {
            id: 'edge-3',
            source: '5',
            sourceHandle: 'c',
            target: '7',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '개념 복습',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '개념 복습',
            type: "bezier",
            selectable: false,
            deletable: false
        },
        {
            id: 'edge-4',
            source: '1',
            sourceHandle: 'c',
            target: '3',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '2차시',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '2차시',
            type: "bezier"
        },
        {
            id: 'edge-5',
            source: '3',
            sourceHandle: 'c',
            target: '6',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '생성 기본',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '생성 기본',
            type: "bezier",
            selectable: false,
            deletable: false
        },
        {
            id: 'edge-6',
            source: '6',
            sourceHandle: 'c',
            target: '8',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '생성 심화',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '생성 심화',
            type: "bezier",
            selectable: false,
            deletable: false
        },
        {
            id: 'edge-7',
            source: '1',
            sourceHandle: 'c',
            target: '4',
            targetHandle: 'a',
            data: {
                styleData: {
                    label: '3차시',
                    type: 'bezier',
                    marker: 'none',
                    color: '#000',
                    width: 1,
                    dasharray: '0'
                }
            },
            style: edgeStyle,
            label: '3차시',
            type: "bezier",
            selectable: false,
            deletable: false
        }

    ];
   
    const nodes = writable<Node[]>(initialNodes);
    const edges = writable(initialEdges);
   
    let selectedNode: any = $state();
    let selectedEdge: any = $state();

    function selectNode(node: Node) {
        selectedNode = node;
        selectedEdge = undefined;
    }

    function selectEdge(edge: Edge) {
        selectedEdge = edge;
        selectedNode = undefined;
    }

    function selectAny() {
        selectedNode = undefined;
        selectedEdge = undefined;
    }

    $effect(() => {
        const node = $nodes.find(n => n.selected);
        if (node) {
            selectedNode = node;
        }
    });

    $effect(() => {
        if (selectedNode?.data?.styleData) {
            updateNodeStyle(selectedNode);
        }
    });

    $effect(() => {
        if (selectedEdge?.data?.styleData) {
            updateEdgeStyle(selectedEdge);
        }
    });

    $effect(() => {
        $effect(() => {
            if ($edges && $edges.length > 0) {
            // 각 엣지에 대해 styleData가 없는 경우 추가
            const updatedEdges = $edges.map((edge) => {
                if (!edge.data?.styleData) {
                return {
                    ...edge,
                    data: { ...edge.data, styleData: edgeStyleData }
                };
                }
                return edge;
            });

            // 변경된 엣지가 있으면 스토어 업데이트 (참조가 달라진 경우에만)
            if (JSON.stringify(updatedEdges) !== JSON.stringify($edges)) {
                edges.set(updatedEdges);
            }
            }
        });
    });

    function updateNodeStyle(selectedNode: Node & { data: { styleData: typeof baseData } }) {
        const {
            backgroundColor,
            borderWidth,
            borderStyle,
            borderColor,
            borderRadius,
            fontSize,
            bold
        } = selectedNode.data.styleData;

        selectedNode.style = `background: ${backgroundColor}; border: ${borderWidth} ${borderStyle} ${borderColor}; border-radius: ${borderRadius}; font-size: ${fontSize}; display: flex; align-items: center; justify-content: center; font-weight: ${bold ? 'bold' : 'normal'};`;

        nodes.update((ns) =>
            ns.map((node) => (node.id === selectedNode.id ? selectedNode : node))
        );
    }

    function updateEdgeStyle(selectedEdge: Edge & { data: { styleData: typeof edgeStyleData } }) {
        const {
            label,
            type,
            marker,
            color,
            width,
            dasharray
        } = selectedEdge.data.styleData;

        let markerStart = undefined;
        let markerEnd = undefined;

        if(marker === 'none') {
            markerStart = undefined;
            markerEnd = undefined;
        } else if(marker === 'start') {
            markerStart = { type: MarkerType.ArrowClosed, color: color };
            markerEnd = undefined;
        } else if(marker === 'end') {
            markerStart = undefined;
            markerEnd = { type: MarkerType.ArrowClosed, color: color };
        } else if(marker === 'both') {
            markerStart = { type: MarkerType.ArrowClosed, color: color };
            markerEnd = { type: MarkerType.ArrowClosed, color: color };
        }

        const updatedEdge = {
            ...selectedEdge,
            label,
            type,
            style: `stroke: ${color}; stroke-width: ${width}; stroke-dasharray: ${dasharray};`,
            markerStart,
            markerEnd
        };

        edges.update((es) =>
            es.map((edge) => (edge.id === selectedEdge.id ? updatedEdge : edge))
        );
    }
    
   
    function addNode() {
      const newNode: Node = {
        id: `${$nodes.length + 1}`,
        type: 'ResizeAbleNode',
        position: { x: 0, y: 0 },
        data: { label: `Node ${$nodes.length + 1}`, styleData: baseData},
        style: nodeStyle,
        width: 100,
        height: 50
      };
      
      $nodes = [...$nodes, newNode];
    }

    function deleteNode(node: Node) {
        $nodes = $nodes.filter((n) => n.id !== node.id);

        selectAny();
    }

    function deleteEdge(edge: Edge) {
        $edges = $edges.filter((e) => e.id !== edge.id);

        selectAny();
    }

    // let isEditMode = writable(false);

    // let isEditMode = $state(false);

    // // function toggleEditMode() {
    // //     isEditMode.update(mode => !mode);

    // //     nodes.update(ns => ns.map(node => ({ ...node, draggable: $isEditMode, selectable: $isEditMode, deletable: $isEditMode })));
    // // }

    export function toggleEditMode() {

        nodes.update(ns => ns.map(node => ({ ...node, draggable: $isEditMode, selectable: $isEditMode, deletable: $isEditMode, connectable: $isEditMode })));
        edges.update(es => es.map(edge => ({ ...edge, selectable: $isEditMode, deletable: $isEditMode })));

        selectedNode = undefined;
        selectedEdge = undefined;

        // let nodesJson = JSON.stringify($nodes, null, 2);
        // let edgesJson = JSON.stringify($edges, null, 2);
    }

    const initialViewport = {
        x: 200,   // x축 위치
        y: 0,   // y축 위치
        zoom: 1.2 // 줌 레벨
    };

  </script>
   
  <div class="h-[80vh]">
    <SvelteFlow 
        {nodes} 
        {edges} 
        {nodeTypes} 
        {edgeTypes}
        {initialViewport}
        connectionMode={ConnectionMode.Loose}
        on:nodeclick={(event)=> {selectNode(event.detail.node)}}
        on:edgeclick={(event)=> {selectEdge(event.detail.edge)}}
        on:paneclick={() => {selectAny()}}
        on:dragover={onDragOver}
        on:drop={onDrop}
    >

        <Sidebar {isEditMode} /> 

        {#if selectedNode && $isEditMode}
        <div class="absolute right-0 top-0 w-[270px] h-full z-[4] bg-base-200 shadow-lg p-2 overflow-y-auto">
            <h2 class="text-xl font-bold mb-6">노드 설정</h2>

            <div class="form-control mb-4">
                <label class="label" for="node-label">
                    <span class="label-text">노드 이름</span>
                </label>
                <input 
                    id="node-label"
                    class="input input-bordered w-full" 
                    bind:value={selectedNode.data.label}
                />
            </div>

            <div class="form-control mb-4">
                <label class="label" for="node-font-size">
                    <span class="label-text">글자 크기</span>
                </label>
                <input 
                    id="node-font-size"
                    type="text"
                    class="input input-bordered w-full" 
                    bind:value={selectedNode.data.styleData.fontSize}
                />
            </div>

            <div class="form-control mb-4">
                <label class="label" for="node-bold">
                    <span class="label-text">글자 굵게</span>
                </label>
                <input 
                    id="node-bold"
                    type="checkbox"
                    class="checkbox checkbox-sm" 
                    bind:checked={selectedNode.data.styleData.bold}
                />
            </div>

            <div class="flex flex-row items-center justify-start gap-2">
                
                <div class="form-control mb-4 min-w-[173px]">
                    <label class="label" for="node-border-width">
                        <span class="label-text">테두리 두께</span>
                    </label>
                    <input 
                        id="node-border-width"
                        type="text"
                        class="input input-bordered w-full min-w-[173px]" 
                        bind:value={selectedNode.data.styleData.borderWidth}
                    />
                </div>

                <div class="form-control mb-4 flex flex-col justify-center items-center w-full">
                    <label class="label" for="node-bg">
                        <span class="label-text">배경 색상</span>
                    </label>
                    <input 
                        id="node-bg"
                        type="color"
                        class="input-bordered w-10 h-10" 
                        bind:value={selectedNode.data.styleData.backgroundColor}
                    />
                </div>
    
            </div>

            <div class="flex items-center justify-start gap-2">

                <div class="form-control mb-4">
                    <label class="label" for="node-border-style">
                        <span class="label-text">테두리 스타일</span>
                    </label>
                    <select 
                        id="node-border-style"
                        class="select select-bordered w-full" 
                        bind:value={selectedNode.data.styleData.borderStyle}
                    >
                        <option value="solid">직선</option>
                        <option value="dotted">점선</option>
                        <option value="dashed">짧은선</option>
                    </select>
                </div>
    
                <div class="form-control mb-4 flex flex-col justify-center items-center">
                    <label class="label" for="node-border-color">
                        <span class="label-text">테두리 색상</span>
                    </label>
                    <input 
                        id="node-border-color"
                        type="color"
                        class="input-bordered w-10 h-10" 
                        bind:value={selectedNode.data.styleData.borderColor}
                    />
                </div>
    

            </div>


            <div class="form-control mb-4">
                <label class="label" for="node-border-radius">
                    <span class="label-text">테두리 둥근정도</span>
                </label>
                <input 
                    id="node-border-radius"
                    type="text"
                    class="input input-bordered w-full" 
                    bind:value={selectedNode.data.styleData.borderRadius}
                />
            </div>

            <button class="btn btn-sm btn-error" onclick={() => deleteNode(selectedNode)}>
                노드삭제
            </button>
        </div>
        {/if}

        {#if selectedEdge && $isEditMode}
        <div class="absolute right-0 top-0 w-[270px] h-full z-[4] bg-base-200 shadow-lg p-2 overflow-y-auto">
            <h2 class="text-xl font-bold mb-6">연결선 설정</h2>

            <div class="form-control mb-4">
                <label class="label" for="edge-label">
                    <span class="label-text">연결선 라벨</span>
                </label>
                <input 
                    id="edge-label"
                    class="input input-bordered w-full" 
                    type="text"
                    bind:value={selectedEdge.data.styleData.label}
                />
            </div>

            <div class="form-control mb-4">
                <label class="label" for="edge-type">
                    <span class="label-text">연결선 타입</span>
                </label>
                <select 
                    id="edge-type"
                    class="select select-bordered w-full" 
                    bind:value={selectedEdge.data.styleData.type}
                >
                    <option value="straight">직선</option>
                    <option value="step">직각선</option>
                    <option value="bezier">곡선</option>
                </select>
            </div>

            <div class="form-control mb-4">
                <label class="label" for="edge-marker">
                    <span class="label-text">연결선 마커</span>
                </label>
                <select 
                    id="edge-marker"
                    class="select select-bordered w-full" 
                    bind:value={selectedEdge.data.styleData.marker}
                >
                    <option value="none">없음</option>
                    <option value="start">시작</option>
                    <option value="end">끝</option>
                    <option value="both">양쪽</option>
                </select>
            </div>

            
            <div class="flex items-center justify-start gap-2">

                <div class="form-control mb-4">
                    <label class="label" for="edge-width">
                        <span class="label-text">연결선 두께</span>
                    </label>
                    <input 
                        id="edge-width"
                        class="input input-bordered w-full" 
                        type="number"
                        bind:value={selectedEdge.data.styleData.width}
                    />
                </div>
                
                <div class="form-control mb-4 flex flex-col justify-center items-center">
                    <label class="label" for="edge-color">
                        <span class="label-text">연결선 색상</span>
                    </label>
                    <input 
                        id="edge-color"
                        class="input-bordered w-10 h-10" 
                        type="color"
                        bind:value={selectedEdge.data.styleData.color}
                    />
                </div>

            </div>


            <div class="form-control mb-4">
                <label class="label" for="edge-dasharray">
                    <span class="label-text">연결선 대시 배열</span>
                </label>
                <input 
                    id="edge-dasharray"
                    class="input input-bordered w-full" 
                    type="number"
                    bind:value={selectedEdge.data.styleData.dasharray}
                />
            </div>

            <button class="btn btn-sm btn-error" onclick={() => deleteEdge(selectedEdge)}>
                연결선삭제
            </button>

        </div>
        {/if}
        <Background />
        <!-- <Background patternColor="#aaa" gap={16} /> -->
        <!-- <Controls />
        <MiniMap zoomable pannable height={120} /> -->

        <style>
            .svelte-flow__attribution {
                display: none !important;
            }
            </style>
    </SvelteFlow>
    
  </div>
