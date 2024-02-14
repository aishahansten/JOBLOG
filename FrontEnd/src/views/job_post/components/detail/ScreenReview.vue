<script setup>
import { ref, onMounted } from "vue";
import { useJobPostStore } from "@/stores/jobPosts";
import ScreenReviewItem from "../items/ScreenReviewItem.vue";
const jobPostStore = useJobPostStore();

const props = defineProps({
  currentJob: Object,
});

const isLoaded = ref(false);

const companyReview = ref([]);

onMounted(async () => {
  await jobPostStore.getCompanyReveiw(props.currentJob.value.companyCode);
  companyReview.value = jobPostStore.companyReview.value;
  isLoaded.value = true;
});
</script>

<template>
  <div class="review-container">
    <div class="header">
      <div class="title">
        <h2>'{{ currentJob.value.companyName }}' 의 전형 리뷰</h2>
        <p>해당 회사의 여러 채용 전형에 대한 사람들의 리뷰입니다.</p>
      </div>
    </div>
    <div class="list">
      <article>
        <section>리뷰 개수 : {{ companyReview.length }}</section>
      </article>
    </div>
  </div>
  <hr />

  <div v-if="companyReview.length > 0">
    <ScreenReviewItem v-for="review in companyReview" :key="review.id" :review="review" />
  </div>
  <div v-else>아직 작성된 리뷰가 없습니다</div>
</template>

<style scoped>
.review-container {
  display: flex;
  justify-content: space-between;
}

section {
  margin-right: 10px;
}

hr {
  margin-bottom: 20px;
}
</style>
