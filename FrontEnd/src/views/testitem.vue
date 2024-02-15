<template>
    <div class="item-container">
      <input type="text" v-model="searchTerm" placeholder="Search child categories..." class="search-input" />
      <div class="title">{{ category.jobName }}</div>
      <div class="sub-grid-container" v-if="filteredChildCategories.length">
        <div class="sub-grid-item" v-for="childCategory in filteredChildCategories" :key="childCategory.jobCode">
          {{ childCategory.jobName }}
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { computed, ref, defineProps } from 'vue';
  
  const props = defineProps({
    category: Object
  });
  
  const searchTerm = ref('');
  
  const filteredChildCategories = computed(() => {
    if (!searchTerm.value) {
      return props.category.child;
    }
  
    return props.category.child.filter(child =>
      child.jobName.toLowerCase().includes(searchTerm.value.toLowerCase())
    );
  });
  </script>
  
  <style scoped>
  
  .search-input {
    margin-bottom: 10px;
    padding: 8px;
    width: 100%;
    box-sizing: border-box;
  }
  
  .title {
    font-size: 20px;
    font-weight: bold;
  }
  
  .sub-grid-container {
    display: grid;
    grid-template-columns: repeat(10, 1fr);
    gap: 5px;
  }
  
  .sub-grid-item {
    border: 1px solid #ccc;
    padding: 10px;
  }
  </style>
  