<template>
    <RouterLink :to="{ name: 'JobDetail', params: { id: data.id } }">
      <div class="job-post">
        <div class="content">
          <div class="title">{{ data.title }}</div>
          <div class="experience">
            {{ data.experienceLevel }} | {{ data.industryDesc }}
          </div>
          <div class="company-name">{{ data.companyName }}</div>
        </div>
        <div class="etc">
          <span>D-7</span>
          <a class="scrap">
            <i class="fa-regular fa-star pointer" @click="scrapPost(scrap)"></i>
          </a>
        </div>
      </div>
    </RouterLink>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useJobPostStore } from "@/stores/jobPosts";
  
  const jobPostStore = useJobPostStore();
  const props = defineProps({
    data: Object,
  });
  
  const scrap = ref({
    userId: null,
    recruitId: props.data.id,
  });
  
  const scrapPost = function (scrap) {
    jobPostStore.scrapJobPost(scrap.value);
  };
  </script>
  
  <style scoped>
  .job-post {
    display: grid;
    grid-template-columns: 1fr 0.3fr;
    padding: 30px 20px;
  
    border: 1px solid var(--border-gray);
    border-radius: 20px;
  }
  .job-post:hover {
    background-color: rgb(104, 128, 255, 0.1);
    transition: 0.2s;
  }
  
  .job-post .content {
    display: grid;
    grid-template-rows: 1.5fr 1fr;
    grid-gap: 5px;
  }
  
  .job-post .content .title {
    font-size: 20px;
    font-weight: bold;
  }
  .job-post .content .experience {
    color: var(--gray);
  }
  .job-post .content .company-name {
    font-weight: 500;
  }
  
  .job-post .etc {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: end;
  }
  .etc span {
    color: var(--gray);
  }
  .etc .scrap {
    display: flex;
    justify-content: end;
    opacity: 50%;
  }
  .etc .scrap:hover {
    opacity: 100%;
  }
  </style>
  