<template>
  <div class="sub-nav">
    <RouterLink id="blog-schedule" :to="{ name: 'BlogSchedule' }"
      >나의 캘린더</RouterLink
    >
    ·
    <RouterLink id="blog-journal" :to="{ name: 'BlogJournal' }"
      >다이어리 목록</RouterLink
    >
    ·
    <RouterLink id="blog-review" :to="{ name: 'BlogReview' }"
      >전형리뷰 목록</RouterLink
    >
    ·
    <RouterLink id="blog-application" :to="{ name: 'BlogApplication' }"
      >이력서/자소서</RouterLink
    >
  </div>
</template>

<script setup></script>

<style scoped>
.sub-nav {
  display: flex;
  height: 70px;
  justify-content: center;
  align-items: center;
  gap: 10px;
  
  font-size: 16px;
}
</style>
