<script setup>
import JobList from "./components/JobList.vue";
import SearchFilter from "./components/SearchFilter.vue";
</script>

<template>
  <div class="container">
    <div class="search">
      <SearchFilter />
      <div class="bottom">
        <div class="saramin">
          Powered by
          <a href="http://www.saramin.co.kr" target="_blank">취업 사람인</a>
        </div>
        <RouterLink
          :to="{ name: 'JobScrap' }"
          id="job-scrap-link"
          class="h-transparent-c"
        >
          <i class="fa-solid fa-star"></i>
          <span>스크랩 공고 모아보기</span>
          <i class="fa-solid fa-chevron-right"></i>
        </RouterLink>
      </div>
    </div>
    <div class="list">
      <div class="recommend">
        <div class="title">
          <h2>{000}님 맞춤 추천 채용 공고!</h2>
        </div>
        <JobList />
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
</style>
