<template>
  <div class="review">
    <RouterLink :to="{ name: 'JournalDetail', params: { id: diaryId } }">
      <p>{{ createdDate }} 나의 복기</p>
      <div class="image-placeholder"></div>
      <h3>{{ content }}</h3>
    </RouterLink>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  diaryId: Number,
  content: String,
  createdDate : String,
  modifiedDate: String,
})
</script>

<style>
.review {
  /* styles for the review component */
  border: 1px solid #ccc;
  padding: 16px;
  text-align: center;
}
.image-placeholder {
  /* styles for the image placeholder */
  background-color: #eaeaea;
  width: 100%;
  height: 150px;
  margin-bottom: 8px;
}
</style>
