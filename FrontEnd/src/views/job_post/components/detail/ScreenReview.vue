<script setup>
import { ref, onMounted } from 'vue'
import { useJobPostStore } from '@/stores/jobPosts';
import ScreenReviewItem from '../items/ScreenReviewItem.vue';
const jobPostStore = useJobPostStore()

const props = defineProps({
  currentJob: Object
})

const isLoaded = ref(false)

const companyReview = ref([])

onMounted(async() => {
  await jobPostStore.getCompanyReveiw(props.currentJob.value.companyCode)
  companyReview.value = jobPostStore.companyReview
  console.log(companyReview.value)
  isLoaded.value = true
}),

console.log('리뷰', props.currentJob.value.companyCode)

</script>

<template>
  <div class="review-container">
    <div class="header">
      <div class="title">
        <h2>'{{ currentJob.value.companyName }}' 의 전형 리뷰</h2>
        <p>해당 회사의 여러 채용 전형에 대한 사람들의 리뷰입니다.</p>
      </div>
      <div class="link">전형리뷰 쓰러가기</div>
    </div>
    <div class="list">
      <article>
        <section>유형 : 면접</section>
        <section>진행방식 : 1대1</section>
      </article>
    </div>
  </div>
  <div v-if="!isLoaded">
    <ScreenReviewItem
      v-for="review in companyReview"
      
    />
  </div>
</template>

<style scoped>
  .review-container {
    display: flex;
    justify-content: space-between;
  }

  .list {
    /* margin-left: auto; */
  }
</style>
