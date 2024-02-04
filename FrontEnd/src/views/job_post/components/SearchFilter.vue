<template>
  <div class="search-filter-container">
    <div class="search">
      <div>
        <input 
          type="text" 
          class="search-bar" 
          placeholder="'공고명/회사명'을 검색해주세요!"
          v-model="searchTerm"  
        >
      </div>
    </div>
    <div class="expLv">
      <div class="radio-box">
        <label class="experience-label">경력 유무</label>
        <div class="radio-group">
            <input type="radio" id="experience1" name="experience" value="0" v-model="selectedExperience">
            <label for="experience1">경력무관</label>

            <input type="radio" id="experience2" name="experience" value="1" v-model="selectedExperience">
            <label for="experience2">신입</label>

            <input type="radio" id="experience3" name="experience" value="2" v-model="selectedExperience">
            <label for="experience3">경력</label>

            <input type="radio" id="experience4" name="experience" value="3" v-model="selectedExperience">
            <label for="experience4">신입/경력</label>
        </div>
      </div> 
    </div>
    <div>
      현재 진행중인 공고만 보기
      <input class="active" type="checkbox" :class="activeOnly">
    </div>
    <div @click="performSearch">검색하기</div>
  </div>
  <div class="container">
    <div class="sidebar">
      <div 
        class="category" 
        v-for="category in jobCategories" 
        :key="category.jobCode" 
        @click="selectCategory(category)"
      >
        {{ category.jobName }}
      </div>
    </div>
    <div class="content">
      <SearchFilterItem 
      :category="selectedCategory" 
      v-if="selectedCategory"
      @select="handleClicked"
      />
    </div>
  </div>
  <div class="filter-footer">
    <div v-if="clickedCategory.length" class="clicked-container">
      <div 
        v-for="(name, index) in clickedCategory" 
        :key="name.jobCode" 
        class="clicked-categories"
      >
        {{  name.jobName }}
        <span class="discard" @click.stop="discardCategory(index)">x</span>
      </div>
    </div>
    <div
      v-if="clickedCategory.length" 
      class="empty-filter"
      @click="emptyCategory"
      > 필터 초기화</div>
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
  
  const clickedCategory = ref([])

  const handleClicked = (selected) => {
  // Find the index of the item in the array
  const index = clickedCategory.value.findIndex(
      (item) => item.jobCode === selected.jobCode
    );

    // If the item is found, remove it from the array
    if (index > -1) {
      clickedCategory.value.splice(index, 1);
    } else {
      // If the item is not found, add it to the array
      clickedCategory.value.push(selected);
    }

    console.log(clickedCategory.value);
  };

  const discardCategory = (index) => {
    clickedCategory.value.splice(index, 1);
  };

  const emptyCategory = function() {
    clickedCategory.value = []
  }

  const searchTerm = ref('')
  const activeOnly = ref(true)
  const selectedExperience = ref(null)

  const performSearch = () => {
    const searchConditions = {
      active: activeOnly.value ? 1 : 0,
      jobCategory: clickedCategory.value.map(c => c.jobCode).join(','),
      expLv: selectedExperience.value,
      keyword: searchTerm.value.trim()
    }

    console.log(searchConditions)
  }
  </script>
  
  <style scoped>

  .search-filter-container {
    display: flex;
    gap: 20px;
  }
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

  .filter-footer {
    display: grid;
    grid-template-columns: 1fr 0.2fr;
  }
  .clicked-container {
    display: flex;
    gap: 5px;
  }
  .clicked-categories {
    border: 2px solid #6880ff;
    border-radius: 8px;
    padding: 2px;
  
  }

  .discard {
    cursor: pointer;
    width: 10px;
    color: #a9a9a9;
  }

  .empty-filter {
    margin-left: auto;
    cursor: pointer;
  }
  </style>
  