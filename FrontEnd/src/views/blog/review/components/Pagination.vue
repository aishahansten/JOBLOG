<template>
  <div class="pagination">
    <button @click="changePage(-1)" :disabled="currentPage === 1">이전</button>
    <span>{{ currentPage }}</span>
    <button @click="changePage(1)" :disabled="currentPage === pageCount">다음</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const currentPage = ref(1);
const pageCount = ref(5); 

const changePage = (delta) => {
  currentPage.value += delta;
};
</script>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 16px;
}
button {
  margin: 0 8px;
}
</style>
