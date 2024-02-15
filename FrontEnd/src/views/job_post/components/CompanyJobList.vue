<template>
  <h3>채용공고</h3>
  <div class="job-posts">
    <CompanyJobItem 
    v-for="data in companyRecruit.value" 
    :key="data.Id" 
    :data="data" />
  </div>
</template>
  
<script setup>
  import CompanyJobItem from "./items/CompanyJobItem.vue";
  
  const props = defineProps({
    companyRecruit: Array
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
  