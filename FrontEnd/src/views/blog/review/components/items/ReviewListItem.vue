<template>
  <div class="review-item">
    <div class="image-placeholder"></div>
    <div class="title">제목</div>
    <div class="date">작성일</div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.review-item {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 16px;
  text-align: center;
}
.image-placeholder {
  background-color: #f0f0f0;
  height: 150px; 
  margin-bottom: 8px;
}
.title, .date {
  margin-bottom: 4px;
}
</style>
