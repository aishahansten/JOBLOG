<template>
  <div>
    <h1>내역 복기 모아보기</h1>
    <div class="Review-grid">
      <ReviewItem v-for="item in items" :key="item.id" />
    </div>
    <Pagination />
  </div>
</template>

<script setup>
import { ref } from "vue";
import ReviewItem from "./ReviewItem.vue";
import Pagination from "./Pagination.vue";

const items = ref([]);
</script>

<style scoped>
h1 {
  text-align: center;
  margin-bottom: 16px;
}
.Review-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 16px;
}
@media (max-width: 768px) {
  .Review-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
