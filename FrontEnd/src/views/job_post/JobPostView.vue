<script setup>
import { ref, onMounted, computed } from "vue";
import JobList from "./components/JobList.vue";
import SearchFilter from "./components/SearchFilter.vue";
import { useJobPostStore } from "@/stores/jobPosts";

const jobPostStore = useJobPostStore();
const search = ref({
  jobCategory: null,
  expLv: null,
  keyword: null,
  page: 0,
  size: 30,
});

const jobPosts = ref({});

const totalPages = computed(() => jobPosts.value.totalPages || 0);

onMounted(async () => {
  await fetchJobPosts();
});

const fetchJobPosts = async () => {
  await jobPostStore.getJobPost(search.value);
  jobPosts.value = jobPostStore.jobPosts;
};

const changePage = async (page) => {
  search.value.page = page;
  await fetchJobPosts();
};

const handleSearch = async (searchCrit) => {
  Object.entries(searchCrit.searchConditions).forEach(([key, value]) => {
    search.value[key] = value || null;
  });

  search.value.page = 0;
  await fetchJobPosts();
};

const maxVisiblePages = 5;
const currentPageRange = computed(() => {
  let start = search.value.page - Math.floor(maxVisiblePages / 2);
  start = Math.max(start, 0);
  start = Math.min(start, Math.max(totalPages.value - maxVisiblePages, 0));

  const end = Math.min(start + maxVisiblePages, totalPages.value);

  return Array.from({ length: end - start }, (_, i) => start + i + 1);
});

const canGoBack = computed(() => search.value.page > 0);
const canGoForward = computed(() => search.value.page < totalPages.value - 1);

const goToNextSet = () => {
  if (canGoForward.value) {
    changePage(Math.min(search.value.page + maxVisiblePages, totalPages.value - 1));
  }
};

const goToPrevSet = () => {
  if (canGoBack.value) {
    changePage(Math.max(search.value.page - maxVisiblePages, 0));
  }
};
</script>

<template>
  <div class="container">
    <div class="search">
      <SearchFilter @search="handleSearch" />
      <div class="bottom">
        <div class="saramin">
          Powered by
          <a href="http://www.saramin.co.kr" target="_blank">취업 사람인</a>
        </div>
        <RouterLink :to="{ name: 'BlogReview' }" id="job-scrap-link" class="h-transparent-c">
          <i class="fa-solid fa-star"></i>
          <span>스크랩 공고 모아보기</span>
          <i class="fa-solid fa-chevron-right"></i>
        </RouterLink>
      </div>
      <div>검색 결과 : {{ jobPosts.totalElements }} 개</div>
    </div>
    <div class="list">
      <div class="recommend">
        <div class="title">
          <h2>새로운 채용 공고!</h2>
        </div>
        <JobList :jobPosts="jobPosts.recruitListResponseDtos" />
      </div>
    </div>
  </div>
  <div class="pagination">
    <button v-if="canGoBack" @click="goToPrevSet" class="pagination-button">«</button>

    <button
      v-for="pageNum in currentPageRange"
      :key="pageNum"
      :class="{ active: pageNum === search.page + 1 }"
      @click="changePage(pageNum - 1)"
      class="pagination-button"
    >
      {{ pageNum }}
    </button>

    <button v-if="canGoForward" @click="goToNextSet" class="pagination-button">»</button>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 10px 0;
}

.saramin {
  padding: 10px;
}
.search {
  width: 1420px;
}
.bottom {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
}
.saramin a {
  font-weight: bold;
  color: rgba(61, 28, 205, 0.784);
}
#job-scrap-link {
  display: flex;
  align-items: center;
  gap: 5px;
  padding: 10px;
  font-weight: 700;
  border-radius: 10px;
}

.list {
  display: flex;
  width: 100%;
  flex-direction: column;
}
.recommend {
  display: flex;
  flex-direction: column;
}
.recommend .title {
  padding: 30px 0;
  border-top: 1px solid var(--border-gray);
}

.pagination {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.pagination-button {
  background-color: white;
  border: 1px solid rgba(255, 255, 255, 0.626);
  width: 20px;
  font-size: 16px;
}
</style>
