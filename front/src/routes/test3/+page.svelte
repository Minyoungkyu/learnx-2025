<script>
    import { SvelteFlowProvider } from '@xyflow/svelte';
   
    import Flow from './Flow.svelte';
    import DnDProvider from './DnDProvider2.svelte';
  </script>
   
  <div class="w-full h-full">
    <SvelteFlowProvider>
      <DnDProvider>
        <Flow />
      </DnDProvider>
    </SvelteFlowProvider>
  </div>