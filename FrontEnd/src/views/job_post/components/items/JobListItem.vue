<template>
<div class="job-post">
  <div class="upper-post">이미지, 스크랩 버튼
    <RouterLink :to="{ name:'JobDetail' }"><button>임시 상세페이지</button></RouterLink>
  </div>
  <div class="lower-post">
    <div class="title">{{ data.title }}</div>
    <div class="experience">{{ data.experienceLevel }}, {{ data.industryDesc }}</div>
    <div class="company-name">{{ data.companyName }}</div>
  </div>
</div>
</template>

<script setup>
import { ref } from 'vue';


const props = defineProps ({
  data: Object
})
</script>

<style scoped>
  .job-post {
    display: grid;
    grid-template-rows: 1.5fr 1fr;
    
  }

  .upper-post {
    border: black 1px solid;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
    height: 200px;
    background-color: rgb(61, 61, 61);
    color: white;
  }

  .lower-post {
    border: black 1px solid;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
    height: 134px;
  }

  .title {
    font-size: 20px;
    font-weight: bold;
  }

  .experience {
    font-size: 18px;
    color: grey;
  }
</style>