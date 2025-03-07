<script lang="ts">
    import {
      getBezierPath,
      BaseEdge,
      type EdgeProps,
      EdgeLabelRenderer,
      useEdges
    } from '@xyflow/svelte';

    type $$Props = EdgeProps;

    const edgeStyleData = {
        label: '',
        type: 'straight',
        marker: 'none',
        color: '#000',
        width: 1,
        dasharray: '0'
    }
   
    export let sourceX: $$Props['sourceX'];
    export let sourceY: $$Props['sourceY'];
    export let sourcePosition: $$Props['sourcePosition'];
    export let targetX: $$Props['targetX'];
    export let targetY: $$Props['targetY'];
    export let targetPosition: $$Props['targetPosition'];
    export let style: $$Props['style'] = undefined;
   
    $: [edgePath, labelX, labelY] = getBezierPath({
      sourceX,
      sourceY,
      sourcePosition,
      targetX,
      targetY,
      targetPosition
    });
   
    const edges = useEdges();
  </script>
   
  <BaseEdge path={edgePath} {style}/>
  <EdgeLabelRenderer>
    <div
      class="edgeButtonContainer nodrag nopan text-[50px]"
      style:transform="translate(-50%, -50%) translate({labelX}px,{labelY}px)"
    >
    </div>
  </EdgeLabelRenderer>
   