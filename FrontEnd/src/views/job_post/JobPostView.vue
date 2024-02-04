<template>

  <div class="header">
    <div class="title">
        <h1 style="margin-bottom: 0px;">취업 공고</h1>
    </div>
    <div class="saramin">
      Powered by <a href="http://www.saramin.co.kr" target="_blank">취업 사람인</a>
    </div>
    <div class="search-filter">
      <SearchFilter/>
    </div>
    <div class="to-diary">
        <RouterLink class="to-diary" :to="{ name:'Home' }"><h4 style="margin-top: 0px;">스크랩 공고 모아보기</h4></RouterLink>
    </div>
    <div class="recommendation">
        <h2>사용자 맞춤 추천 채용 공고</h2>
    </div>
    <JobList/>
  </div>

</template>

<script setup>
import JobList from './components/JobList.vue';
import SearchFilter from './components/SearchFilter.vue';


</script>

<style scoped>
  .header {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-width: 1344px; /* Ensure the header takes full width */
    
  }

  .title {
    display: flex;
    width: 100%;
    justify-content: center;
    align-items: center;
  }

  .to-diary {
    display: flex;
    width: 100%;
    justify-content: flex-end; /* Aligns the content to the left */
  }


  .recommendation {
    display: flex;
    justify-content: flex-start;
    width: 100%;
    margin-bottom: 0px;
  }

  .search-filter {
    width: 1420px;
  }

  .saramin {
    justify-content: center;
    align-items: center;
    margin-left: auto ;
  }

  .saramin a {
    font-weight: bold;
    color: rgba(61, 28, 205, 0.784);
  }
</style>
