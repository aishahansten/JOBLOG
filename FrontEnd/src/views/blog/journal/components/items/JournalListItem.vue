<template>
  <div class="review">
    <RouterLink :to="{ name: 'JournalDetail', params: { id: diaryId } }">
      <div class="image-placeholder"></div>
      <h3 class="preview">{{ content }}</h3>
      <p>{{ createdDate }}</p>
    </RouterLink>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  diaryId: Number,
  content: String,
  createdDate : String,
})
</script>

<style>
.review {
  border: 1px solid #ccc;
  padding: 16px;
  text-align: center;
}
.image-placeholder {
  background-color: #eaeaea;
  width: 100%;
  height: 150px;
  margin-bottom: 8px;
}

.preview {
  display: -webkit-box;
  height: 50px;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  word-wrap: break-word;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 14px;
}
</style>
