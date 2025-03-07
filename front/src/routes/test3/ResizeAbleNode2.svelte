<script lang="ts">
  import { Handle, NodeResizer, Position, type NodeProps } from '@xyflow/svelte';
 
  type $$Props = NodeProps;
 
  export let data: $$Props['data'] = { label: '' };
  export let selected: $$Props['selected'] = false;
</script>
 
<NodeResizer minWidth={100} minHeight={50} isVisible={selected} color="rgb(255, 64, 0)"/>
<div class="node">
  {data.label}
  <Handle type="source" position={Position.Top} id="a" class="handle opacity-0" style="width: 50%; height: 10px; border-radius: 0;" />
  <Handle type="source" position={Position.Right} id="b" class="handle opacity-0" style="width: 10px; height: 50%; border-radius: 0;"/>
  <Handle type="source" position={Position.Bottom} id="c" class="handle opacity-0" style="width: 50%; height: 10px; border-radius: 0;"/>
  <Handle type="source" position={Position.Left} id="d" class="handle opacity-0" style="width: 10px; height: 50%; border-radius: 0;"/>
</div>