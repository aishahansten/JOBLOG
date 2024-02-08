<script setup>
import JobList from "./components/JobList.vue";
import SearchFilter from "./components/SearchFilter.vue";
import { useJobPostStore } from "@/stores/jobPosts"

const jobPostStore = useJobPostStore()

import { ref, onMounted } from 'vue'

const search = ref({
  jobCategory: null,
  expLv: null,
  keyword: null,
  page: 0,
  size: 30,
});

const jobPosts = ref([])

onMounted(async () => {
  await jobPostStore.getJobPost(search.value); // Pass the reactive search object's value
  try {
    jobPosts.value = jobPostStore.jobPosts
    console.log(jobPosts)
  } catch (err) {
    console.error(err)
  }
})

const handleSearch = async (searchCrit) => {
  // Iterate over each property in searchCrit.searchConditions
  for (const key in searchCrit.searchConditions) {
    if (Object.hasOwnProperty.call(searchCrit.searchConditions, key)) {
      // If the property's value is an empty string, change it to null
      if (searchCrit.searchConditions[key] === "") {
        searchCrit.searchConditions[key] = null;
      }
    }
  }

  await jobPostStore.getJobPost(searchCrit.searchConditions);
  jobPosts.value = jobPostStore.jobPosts;

  console.log('검색하면', searchCrit.searchConditions);
  console.log('검색 이후 리스트', jobPostStore.jobPosts);
};


</script>

<template>
  <div class="job-header">
    <div class="search-filter">
      <SearchFilter 
        @search="handleSearch"
      />
    </div>
    <div class="search-footer">
      <div class="saramin">
        Powered by
        <a href="http://www.saramin.co.kr" target="_blank">취업 사람인</a>
      </div>
      <RouterLink
        :to="{ name: 'JobScrap' }"
        id="job-scrap-link"
        class="clickable-transparent"
      >
        <i class="fa-solid fa-star"></i>
        <span>스크랩 공고 모아보기</span>
        <i class="fa-solid fa-chevron-right"></i>
      </RouterLink>
    </div>
    <div class="job-list-space">
      <div class="job-recommend">
        <div class="title">
          <h2>{000}님 맞춤 추천 채용 공고!</h2>
        </div>
        <JobList :jobPosts="jobPosts"/>
      </div>
      <!-- 검색 시 추천 공고 div가 사라지고 나타날 div -->
      <!-- <div class="job-search-result">
        <div class="title">
          <h2>검색 결과</h2>
        </div>
        <JobList />
      </div> -->
    </div>
  </div>
</template>

<style scoped>
.job-header {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.saramin {
  padding: 10px;
}
.saramin a {
  font-weight: bold;
  color: rgba(61, 28, 205, 0.784);
}
.search-filter {
  width: 1420px;
}

.search-footer {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
}
#job-scrap-link {
  display: flex;
  align-items: center;
  gap: 5px;
  padding: 10px;
  font-weight: 700;
  border-radius: 10px;
}

.job-list-space {
  display: flex;
  width: 100%;
  flex-direction: column;
}
.job-recommend {
  display: flex;
  flex-direction: column;
}
.job-recommend .title {
  padding: 30px 0;
  border-top: 1px solid var(--border-gray);
}
</style>
