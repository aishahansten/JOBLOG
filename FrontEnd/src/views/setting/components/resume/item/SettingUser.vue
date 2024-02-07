<template>
  <div>
    testing
    {{ userInfo.realName }}
  </div>
</template>

<script setup>

const props = defineProps({
    userInfo: Object
})

</script>

<style scoped>

</style>