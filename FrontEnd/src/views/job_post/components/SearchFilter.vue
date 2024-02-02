<template>
    <div class="container">
      <div class="sidebar">
        <div class="category" v-for="category in jobCategories" :key="category.jobCode" @click="selectCategory(category)">
          {{ category.jobName }}
        </div>
      </div>
      <div class="content">
        <SearchFilterItem 
        :category="selectedCategory" 
        v-if="selectedCategory"
        />
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import jobCategories from '@/assets/data/jobcategory.json';
  import SearchFilterItem from './items/SearchFilterItem.vue';
  
  const selectedCategory = ref(null);
  
  function selectCategory(category) {
    selectedCategory.value = category;
    console.log(category)
  }
  
  </script>
  
  <style scoped>
    .container {
      display: flex;
      max-height: 300px; 
      overflow: hidden; 
      border: 1px solid #ccc;
    }
  
    .sidebar {
      flex: 1;
      border-right: 1px solid #ccc;
      max-width: 200px;
      overflow-y: auto; /* Enables vertical scrolling */
      overflow-x: hidden; /* Keeps the horizontal overflow hidden */
    }
  
    .content {
      flex: 3;
      overflow-y: auto; /* Enables vertical scrolling for the content area */
      overflow-x: hidden; /* Keeps the horizontal overflow hidden */
    }
  
  
  .category {
    padding: 10px;
    cursor: pointer;
  }
  .category:hover {
    background-color: #f0f0f0;
  }
  </style>
  