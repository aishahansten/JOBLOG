<template>
  <div class="job-detail-container">
    <div class="job-detail-content">
      <div class="job-detail">
        <div class="job-title">
          <h1>공고명</h1>
          <h3>회사명</h3>
        </div>
        <div class="job-info">
          <p>업종</p>
          <p>지역</p>
          <p>근무형태</p>
          <p>연봉(급여)</p>
        </div>
        <div class="job-info">
          <p>공고 기간 (공고 시작 ~ 공고 마감)</p>
          <p>학력</p>
          <p>경력</p>
        </div>
      </div>
      <div class="job-interaction">
        <div class="job-check">
          <span>마감 00일 전</span>
          <!-- <span>마감</span> -->
          <!-- <span>접수 00일 전</span> -->
          <div class="job-scrap-info">
            <i class="fa-regular fa-star fa-xl"></i> <span>178</span>
          </div>
        </div>
        <div class="update-btn">이력서 쓰기</div>
        <div class="update-btn">자소서 쓰기</div>
      </div>
    </div>
    <ScreenReview />
    <CompanyReview />
  </div>
</template>

<script setup>
import ScreenReview from "./components/ScreenReview.vue";
import CompanyReview from "./components/CompanyReview.vue";
</script>

<style scoped>
.job-detail-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  min-height: 1000px;
  padding: 30px 0;
}

.job-detail-content {
  display: flex;
  justify-content: space-between;
  padding: 30px;
  border: 1px solid;
  border-radius: 10px;
}

.job-detail {
  width: 100%;
}

.job-title {
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}
.job-info {
  padding: 20px 0;
}

.job-interaction {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: end;
  padding: 20px 0;
}

.job-check {
  display: flex;
}

.job-scrap-info {
  display: flex;
  flex-direction: column;
}
</style>
