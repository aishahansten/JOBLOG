<template>
  <div class="review">
    <RouterLink :to="{ name: 'JournalDetail', params: { id: diaryId } }">
      <div class="profile-image">{{ createdDate }}</div>
      <h3 class="preview">{{ content }}</h3>
    </RouterLink>
  </div>
</template>

<script setup>
import { defineProps } from "vue";
const props = defineProps({
  diaryId: Number,
  content: String,
  createdDate: String,
  size: {
    type: Number,
    default: 200, // 기본 크기는 200px
  },
});
</script>

<style>
.review {
  border: 1px solid #ccc;
  padding: 16px;
  text-align: center;
  background-color: rgb(210, 172, 137);
  border-radius: 10px;
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
.profile-image {
  width: 300px;
  height: 200px;
  background-color: rgba(255, 255, 255, 0.373);
  border-radius: 10px;
  font-size: 18pt;
  font-weight: bold;
}
</style>
