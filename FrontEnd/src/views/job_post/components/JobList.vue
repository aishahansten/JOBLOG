<template>
  <div class="job-posts">
    <JobListItem/>
    <JobListItem/>
    <JobListItem/>
    <JobListItem/>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import JobListItem from './items/JobListItem.vue'

const dummyData = ref({
  title: '홀서비스 신입/경력 채용',
  company_name: '(주)에이치제이푸드남부지점',
  experience_level: '경력1년↑',
  industry: '외식업·식음료',

})


</script>

<style scoped>
  .job-posts {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 10px;
    width: 100%;
  }

</style>