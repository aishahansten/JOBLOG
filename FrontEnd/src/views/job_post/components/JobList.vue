<template>
  <div class="job-posts">
    <JobListItem
      v-for="data in dummyData"
      :key="data.Id"
      :data="data"
    />

  </div>
</template>

<script setup>
import { ref } from 'vue';
import JobListItem from './items/JobListItem.vue'
import dummyData from '@/views/job_post/data/dummy_data.json'



</script>

<style scoped>
  .job-posts {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 10px;
    width: 100%;
  }

</style>