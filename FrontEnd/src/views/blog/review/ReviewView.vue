<template>
  <div v-if="isLoaded"></div>
  <div class="container">
    <div class="header">
      <div class="title">
        <div>
          <h1>전형 리뷰 리스트</h1>
          <p>내 등록/스크랩 공고의 전형 리뷰 리스트입니다.</p>
        </div>
        <div>
          <RouterLink :to="{ name: 'CustomJobCreate' }">
            <a class="btn solid-c h-bright a-dark">공고 만들기</a>
          </RouterLink>
        </div>
      </div>
    </div>
    <div class="content">
      <ReviewItem v-for="data in myJobs" :key="data.id" :data="data" @close="reloadData" />
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import ReviewItem from "./component/ReviewItem.vue";
import { useBlogReviewStore } from "@/stores/blogReview";

const blogReviewStore = useBlogReviewStore();
const myJobs = ref([]);
const isLoaded = ref(false);

const reloadData = async () => {
  isLoaded.value = false;
  await blogReviewStore.getMyJobs();
  myJobs.value = blogReviewStore.myJobs;
  console.log(myJobs);
  isLoaded.value = true;
};

onMounted(async () => {
  await blogReviewStore.getMyJobs();
  console.log(myJobs);
  myJobs.value = blogReviewStore.myJobs;
  isLoaded.value = true;
});
</script>

<style scoped>
.container {
  padding: 20px 0;
}
.title {
  display: flex;
  justify-content: space-between;
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
