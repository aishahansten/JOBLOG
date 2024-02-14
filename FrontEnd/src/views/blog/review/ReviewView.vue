<template>
  <div v-if="isLoaded"></div>
  <div class="container">
    <div class="header">
      <div class="title">
        <h1>전형 리뷰 리스트</h1>
        <p>내 등록/스크랩 공고의 전형 리뷰 리스트입니다.</p>
      </div>
    </div>
    <div class="content">
      <ReviewItem v-for="data in myJobs" :key="data.id" :data="data" />
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import ReviewItem from "./component/ReviewItem.vue";

const processedData = computed(() => {
  return myJobs.value.map((item) => {
    return {
      ...item,
      openingDate: new Date(item.openingDate),
      expirationDate: new Date(item.expirationDate),
      showReviews: false, // Initially, reviews are not shown
    };
  });
});

console.log(processedData);

import { useBlogReviewStore } from "@/stores/blogReview";
const blogReviewStore = useBlogReviewStore();
const myJobs = ref([]);
const isLoaded = ref(false);

onMounted(async () => {
  await blogReviewStore.getMyJobs();
  myJobs.value = blogReviewStore.myJobs;
  console.log(myJobs.value);
  isLoaded.value = true;
});
</script>

<style scoped>
.container {
  padding: 20px 0;
}
.title {
  display: flex;
  flex-direction: column;
  gap: 5px;
  padding: 30px 0;
  border-bottom: 1px solid var(--border-gray);
}
.content {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 30px 0;
}
</style>
