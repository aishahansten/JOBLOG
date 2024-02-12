<template>
  <div class="container">
    <div class="header">
      <h1>나의 다이어리</h1>
      <RouterLink :to="{ name: 'JournalCreate' }" class="btn lined-c h-solid-c a-bright f-color-c"
        >+ 다이어리 쓰기</RouterLink
      >
    </div>
    <div class="content">
      <JournalList />
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from "vue-router";
import JournalList from "./components/JournalList.vue";
</script>

<style scoped>
.container {
  padding: 20px 0;
}
.header {
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
}

.content {
  height: 100%;
  padding: 10px 0;
}
</style>
