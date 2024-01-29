<template>
  <div class="links">
    <RouterLink :to="{ name: 'BlogSchedule' }">나의 캘린더</RouterLink> |
    <RouterLink :to="{ name: 'BlogJournal' }">다이어리 목록</RouterLink> |
    <RouterLink :to="{ name: 'BlogReview' }">전형리뷰 목록</RouterLink> |
    <RouterLink :to="{ name: 'BlogApplication' }">이력서/자소서</RouterLink>
  </div>
</template>

<script setup></script>

<style scoped>
.links {
  display: flex;
  gap: 5px;
  height: 40px;
  font-size: 24px;
}
</style>
