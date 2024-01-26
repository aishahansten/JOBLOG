<template>
    <div class="header">
        <div class="title">
            <h1 style="margin-bottom: 0px;">취업 공고</h1>
        </div>
        <div class="to-diary">
            <RouterLink class="to-diary" :to="{ name:'Home' }"><h4 style="margin-top: 0px;">스크랩 공고 모아보기</h4></RouterLink>
        </div>
        <div class="filter-search-container">
            <div class="filter">필터가 들어갑니다</div>
            <div class="search"><input type="text" class="search-bar"></div>
        </div>
        <div class="recommendation">
            <h2>사용자 맞춤 추천 채용 공고</h2>
        </div>
        <div class="job-posts">
            <div class="job-post">
                <div class="upper-post">이미지, 스크랩 버튼
                <RouterLink :to="{ name:'JobDetail' }"><button>임시 상세페이지</button></RouterLink>
                </div>
                <div class="lower-post">공고명, 회사명, 회사 로고</div>
            </div>
            <div class="job-post">
                <div class="upper-post">이미지, 스크랩 버튼</div>
                <div class="lower-post">공고명, 회사명, 회사 로고</div>
            </div>
            <div class="job-post">
                <div class="upper-post">이미지, 스크랩 버튼</div>
                <div class="lower-post">공고명, 회사명, 회사 로고</div>
            </div>
            <div class="job-post">
                <div class="upper-post">이미지, 스크랩 버튼</div>
                <div class="lower-post">공고명, 회사명, 회사 로고</div>
            </div>
        </div>
    </div>

</template>

<script setup>

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

  .filter-search-container {
    display: grid;
    grid-template-columns: 7fr 3fr;
    width: 100%;
    text-align: center;
    gap: 10px;
    padding: 10px;  
    box-sizing: border-box; 
  }

  .filter {
    border: 1px black solid;
    border-radius: 8px;
    height: 50px;
    justify-content: center;
  }

  .search {
    border: 1px black solid;
    border-radius: 8px;
    height: 50px;
    justify-content: center;
  }

  .recommendation {
    display: flex;
    justify-content: flex-start;
    width: 100%;
    margin-bottom: 0px;
  }

  .job-posts {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 10px;
    width: 100%;
  }

  .job-post {
    display: grid;
    grid-template-rows: 1.5fr 1fr;
    
  }

  .upper-post {
    border: black 1px solid;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
    height: 200px;
    background-color: rgb(61, 61, 61);
    color: white;
  }

  .lower-post {
    border: black 1px solid;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
    height: 134px;
  }

  .search-bar {
    height: 40px;
    font-size: 16pt;
    background-color: aliceblue;
    border-radius: 30px;
    text-align: end;
  }
</style>
