<script lang="ts">
    import SideMenu from "./sideMenu.svelte";

    let editor: any;

</script>

<div class="w-full h-full flex flex-row overflow-hidden">
    <div class="w-[250px] min-w-[200px] sideMenuContainer">
        <SideMenu />
    </div>
    <div class="flex-1 flex min-w-[600px]">
        <div class="w-full h-full contentBody flex flex-col">
        </div>
    </div>
</div>