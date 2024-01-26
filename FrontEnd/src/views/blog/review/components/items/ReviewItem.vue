<template>
  <div class="review-item">
    <div class="image-placeholder"></div>
    <h3 class="title">제목</h3>
    <div class="details">
      <p><strong>시간:</strong> 0000년 00월 00일 00 : 00</p>
      <p><strong>전형 유형:</strong> 면접</p>
      <p><strong>소요 시간:</strong> 3</p>
      <p><strong>날짜:</strong> 00.00</p>
      <p><strong>면접 유형:</strong> 1: N</p>
      <p><strong>담당 직무:</strong> 개발 FE</p>
    </div>
    <div class="actions">
      <button class="icon-button" aria-label="Edit"><i class="fas fa-edit"></i></button>
      <button class="icon-button" aria-label="Share"><i class="fas fa-share-alt"></i></button>
    </div>
    <button class="add-button">새 항목 추가</button>
  </div>
</template>

<script setup>
</script>

<style scoped>
.image-placeholder {
  background-color: #f0f0f0;
  height: 200px;
  margin-bottom: 8px;
}
.title {
  margin-bottom: 4px;
}
.details p {
  margin: 4px 0;
}
.actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}
.icon-button {
  background: none;
  border: none;
  cursor: pointer;
}
.icon-button i {
  font-size: 1.25rem;
}
.add-button {
  background-color: #007bff;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 16px;
}
.add-button:hover {
  background-color: #0056b3;
}
</style>
