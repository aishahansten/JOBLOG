<template>
  <div>
    <SubNav/>
  </div>
  <div v-if="isLoaded">

  </div>
  <div class="scrap-container">
    <ReviewItem
      v-for="data in myJobs"
      :key="data.id"
      :data="data"
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import ReviewItem from './component/ReviewItem.vue'
import SubNav from '../_component/SubNav.vue';
//날짜 계산용 함수 -> 마감일까지의 정보



const processedData = computed(() => {
  return myJobs.value.map(item => {

    return {
      ...item,
      openingDate: new Date(item.openingDate),
      expirationDate: new Date(item.expirationDate),
      showReviews: false  // Initially, reviews are not shown
    };
  });
});

console.log(processedData)

//--------------------------------------------------------------
import { useBlogReviewStore } from '@/stores/blogReview';
const blogReviewStore = useBlogReviewStore()
const myJobs = ref([])
const isLoaded = ref(false)

onMounted(async() => {
  await blogReviewStore.getMyJobs()
  myJobs.value = blogReviewStore.myJobs
  console.log(myJobs.value)
  isLoaded.value = true
})




</script>

<style scoped>

  .item-container {
    display: flex;
    flex-direction: column;
    margin-bottom: 5px;

  }
  .scrap-item {
    display: grid;
    grid-template-columns: 1fr 5fr 1fr 0.1fr;
    font-size: 20px;
    border: 1px solid black;
    height: 80px;
  }

  .title {
    font-weight: bold;
  }

  .main {
    display: grid;
    grid-template-rows: 1fr 1fr;
  }
  
  .date-time {
    display: flex;
  }

  .status-yet-opened {
    background-color: rgba(0, 0, 255, 0.159); /* Color for yet opened */
  }

  .status-ongoing {
    background-color: rgba(0, 128, 0, 0.175); /* Color for ongoing */
  }

  .status-expired {
    background-color: rgba(255, 0, 0, 0.148); /* Color for expired */
  }

  .review {
    height: 80px;
    border: 1px solid black;
  }
</style>