<template>
  <div class="job-posts">
    <JobListItem v-for="data in jobPosts" :key="data.Id" :data="data" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import JobListItem from "./items/JobListItem.vue";

const props = defineProps({
  jobPosts: Array
})
</script>

<style scoped>
.job-posts {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  width: 100%;
  gap: 20px;
  margin-bottom: 50px;
}
</style>
