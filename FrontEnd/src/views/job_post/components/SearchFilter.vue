<template>
  <div class="search-filter-header">
    <div class="search-bar">
      <input
        type="text"
        class="f-size-20"
        placeholder="'공고명/회사명'을 검색해주세요!"
        v-model="searchTerm"
      />
      <a @click="performSearch">
      <i
        class="fa-solid fa-magnifying-glass clickable-icon"
      ></i>
    </a>
    </div>
    <div class="search-filter-check">
      <div class="radio-check">
        <div class="radio">
          <input
            type="radio"
            id="experience1"
            name="experience"
            value="0"
            v-model="selectedExperience"
            checked
          />
          <label for="experience1">경력무관</label>
        </div>
        <div class="radio">
          <input
            type="radio"
            id="experience2"
            name="experience"
            value="1"
            v-model="selectedExperience"
          />
          <label for="experience2">신입</label>
        </div>
        <div class="radio">
          <input
            type="radio"
            id="experience3"
            name="experience"
            value="2"
            v-model="selectedExperience"
          />
          <label for="experience3">경력</label>
        </div>
        <div class="radio">
          <input
            type="radio"
            id="experience4"
            name="experience"
            value="3"
            v-model="selectedExperience"
          />
          <label for="experience4">신입/경력</label>
        </div>
      </div>
      <div>
        <input class="active" type="checkbox" :class="activeOnly" />
        현재 진행중인 공고만 보기
      </div>
    </div>
  </div>

  <div class="search-filter-container">
    <div class="search-filter-main">
      <div class="sidebar">
        <div
          class="category h-txt"
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

    <div class="search-filter-footer">
      <div v-if="clickedCategory.length" class="clicked-container">
        <div
          v-for="(name, index) in clickedCategory"
          :key="name.jobCode"
          class="clicked-categories pointer"
          @click.stop="discardCategory(index)"
        >
          {{ name.jobName }}
          <i class="fa-solid fa-xmark" style="color: #b8b8b8"></i>
        </div>
      </div>
      <div
        v-if="clickedCategory.length"
        class="empty-filter h-txt"
        @click="emptyCategory"
      >
        필터 초기화
        <i class="fa-solid fa-rotate-right"></i>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import jobCategories from "@/assets/data/jobcategory.json";
import SearchFilterItem from "./items/SearchFilterItem.vue";

const selectedCategory = ref(null);

function selectCategory(category) {
  selectedCategory.value = category;
  console.log(category);
}

const clickedCategory = ref([]);

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
};


const discardCategory = (index) => {
  clickedCategory.value.splice(index, 1);
};

const emptyCategory = function () {
  clickedCategory.value = [];
};

const searchTerm = ref("");
const activeOnly = ref(true);
const selectedExperience = ref(null);

const performSearch = () => {
  const searchConditions = {
    active: activeOnly.value ? 1 : 0,
    jobCategory: clickedCategory.value.map((c) => c.jobCode).join(","),
    expLv: selectedExperience.value,
    keyword: searchTerm.value.trim(),
    page: 0,
  };
  emits('search', {
    searchConditions: searchConditions
  })
  console.log(searchConditions);
};
const emits = defineEmits(['search'])


</script>

<style scoped>
/* 검색 필터 */

.search-filter-header {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  gap: 10px;
  padding: 30px 200px;
}
.search-bar {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  border: none;
  border-radius: 70px;
  background-color: var(--light-gray);
}
.search-bar input {
  width: 100%;
  border: none;
  border-radius: 0px;
  background-color: var(--light-gray);
  padding: 0%;
}
.search-bar:focus-within {
  background-color: rgb(104, 128, 255, 0.2);
  transition: 0.2s;
}
.search-bar input:focus {
  background-color: rgb(104, 128, 255, 0);
  transition: 0.2s;
}

.search-filter-check {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}
.radio-check {
  display: flex;
  gap: 10px;
}
.radio-check .radio {
  display: flex;
  gap: 3px;
}

/* 카테고리 필터 */

.search-filter-container {
  display: flex;
  flex-direction: column;
  height: 400px;
  padding: 20px;
  overflow: visible;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.search-filter-main {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.sidebar {
  flex: 1;
  border-right: 1px solid var(--border-gray);
  max-width: 200px;
  overflow-y: auto; /* Enables vertical scrolling */
  overflow-x: hidden; /* Keeps the horizontal overflow hidden */
}
.sidebar .category {
  padding: 10px;
  cursor: pointer;
  border-radius: 10px 0 0 10px;
}
.sidebar .category:hover {
  background-color: var(--light-gray);
  transition: 0.2s;
}

.content {
  padding: 10px;
  flex: 3;
  overflow-y: auto;
  overflow-x: hidden;
}

/* 선택한 카테고리 표시 */

.search-filter-footer {
  display: grid;
  grid-template-columns: 1fr 0.1fr;
  padding: 20px 20px 0 0;
}

.clicked-container {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}
.clicked-categories {
  padding: 10px;
  border-radius: 5px;
  background-color: rgb(104, 128, 255, 0.2);
}
.clicked-categories:hover {
  background-color: rgb(104, 128, 255, 0.5);
}
.clicked-categories:active {
  background-color: var(--gray);
}

.empty-filter {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  margin-left: auto;
  cursor: pointer;
}
</style>
