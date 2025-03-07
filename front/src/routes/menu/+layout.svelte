<script lang="ts">
    import SideMenu from "../menu/sideMenu.svelte";
</script>

<div class="w-full flex flex-row adm-area">
    <div class="w-[250px] sideMenuContainer">
        <SideMenu />
    </div>
    <div class="flex flex-col items-center justify-center w-screen h-screen bg-gray-100">
        <div class="w-full h-full contentBody flex flex-col items-center bg-white overflow-auto">
            <slot></slot>
        </div>
    </div>
</div>