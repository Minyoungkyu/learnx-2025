<script>
    import { SvelteFlowProvider } from '@xyflow/svelte';
   
    import Flow from './Flow.svelte';
    import DnDProvider from './DnDProvider2.svelte';
  </script>
   
  <SvelteFlowProvider>
    <DnDProvider>
      <Flow />
    </DnDProvider>
  </SvelteFlowProvider>