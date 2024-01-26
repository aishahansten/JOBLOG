<template>
    <div>

    </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useApplicationStore } from "@/stores/application";


onMounted(() => {
    useApplicationStore.getResume()
})
</script>

<style scoped>

</style>