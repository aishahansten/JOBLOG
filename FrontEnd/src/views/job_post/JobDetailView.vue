<template>
    <div class="detail-header">
        <div class="header-left">
            <h4>공고명</h4>
            <h3>회사명</h3>
            <p></p>
            <p>공고 기간</p>
            <p>마감 ~~ 전</p>
            <p></p>
            <p>경력</p>
            <p>근무형태</p>
            <p>급여</p>
        </div>
        <div class="header-right">
            <div class="box-container">
                <div class="company-logo">회사로고</div>
                <div class="company-logo">지원하기</div>
                <div class="company-logo">이력서 쓰기</div>
                <div class="company-logo">자소서 쓰기</div>
            </div>
        </div>
    </div>
    <div class="detail-body">
        회사 공고 이미지 파일로 받아온 것 넣는 곳
    </div>
</template>

<script setup>

</script>

<style scoped>
  .detail-header {
    display: grid;
    grid-template-columns: 1fr 1fr;
    width: 1000px;
    border: 1px solid black;
    border-radius: 8px;
    padding: 20px 100px;
    box-sizing: border-box;
  }

  .header-left {
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
  }
  
  .header-left p {
    margin-bottom: 0px;
  }

  .header-right {
    display: flex;
    justify-content: center;
    flex-direction: column;
  }

  .company-logo {
    width: 100px;
    height: 100px;
    border: 1px solid black;
    border-radius: 8px;
    margin-left: auto;
  }
  
  .apply-related {
    margin-left: auto;
  }

  .box-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    width: 230px;
    margin-left: auto;
  }

  .detail-body {
    width: 1000px;
    border: 1px solid black;
    border-radius: 8px;
  }
</style>