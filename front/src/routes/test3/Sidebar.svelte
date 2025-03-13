<script lang="ts">
    import { useDnD } from './utils';
    import type { Writable } from 'svelte/store';

    let { isEditMode } = $props<{ isEditMode: Writable<boolean> }>();
   
    const type = useDnD();
   
    const onDragStart = (event: DragEvent, nodeType: string) => {
      if (!event.dataTransfer) {
        return null;
      }
   
      type.set(nodeType);
   
      event.dataTransfer.effectAllowed = 'move';
    };
  </script>
   
  {#if $isEditMode}
  <aside>
    <div class="nodes-container absolute z-[999999] top-0 left-0 btn w-32 h-16 rounded-xl border-black shadow-md">
      <div
        class="input-node node"
        ondragstart={(event) => onDragStart(event, 'custom')}
        draggable={true}
      >
        끌어서 노드추가
      </div>
    </div>
  </aside>
  {/if}
   
  <style>
    aside {
      width: 10%;
      background: #f4f4f4;
      font-size: 12px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
   
    .label {
      margin: 1rem 0;
      font-size: 0.9rem;
    }
   
    .nodes-container {
      display: flex;
      align-items: center;
      justify-content: center;
    }
   
    /* .node {
      margin: 0.5rem;
      border: 1px solid #111;
      padding: 0.5rem 1rem;
      font-weight: 700;
      border-radius: 3px;
      cursor: grab;
      width: 50px;
    } */
  </style>