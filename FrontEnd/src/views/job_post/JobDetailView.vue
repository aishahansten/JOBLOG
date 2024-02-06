<template>
  <div class="container">
    <div id="job-post" class="content">
      <div class="job-detail">
        <div class="job-title">
          <h1>공고명</h1>
          <h3>회사명</h3>
        </div>
        <div class="job-info">
          <div>
            <div>
              <span>업종</span>
              <p>IT 개발</p>
            </div>
            <div>
              <span>지역</span>
              <p>충남 천안시</p>
            </div>
            <div>
              <span>근무 형태</span>
              <p>정규직</p>
            </div>
            <div>
              <span>급여</span>
              <p>면접 시 결정</p>
            </div>
          </div>
          <div>
            <div>
              <span>접수 기간</span>
              <p>2024년 1월 28일 ~ 2024년 2월 5일</p>
            </div>
            <div>
              <span>학력</span>
              <p>4년제 대졸 이상</p>
            </div>
            <div>
              <span>경력</span>
              <p>신입/경력</p>
            </div>
          </div>
        </div>
      </div>
      <div class="job-interaction">
        <div class="job-check">
          <h3>마감 00일 전</h3>
          <!-- <span>마감</span> -->
          <!-- <span>접수 00일 전</span> -->
          <div class="job-scrap-info">
            <i class="fa-regular fa-star fa-xl"></i>
            <span>178</span>
          </div>
        </div>
        <div class="job-apply">
          <div class="btn solid-c h-bright a-dark f-size-20">이력서 쓰기</div>
          <div class="btn solid-c h-bright a-dark f-size-20">자소서 쓰기</div>
        </div>
      </div>
    </div>
    <div class="content">
      <ScreenReview />
    </div>
    <div class="content">
      <CompanyReview />
    </div>
  </div>
</template>

<script setup>
import ScreenReview from "./components/detail/ScreenReview.vue";
import CompanyReview from "./components/detail/CompanyReview.vue";
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  min-height: 1000px;
  padding: 30px 0;
}

.content {
  display: flex;
  justify-content: space-between;
  padding: 30px 40px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
.content#job-post {
  gap: 40px;
  background-color: rgb(104, 128, 255, 0.2);
}

.job-detail {
  width: 100%;
}

.job-title {
  padding: 20px 0;
  border-bottom: 1px solid white;
}
.job-info {
  display: grid;
  grid-template-columns: 1fr 1fr;
  padding: 20px 0;
}
.job-info div div {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.job-interaction {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: end;
  gap: 20px;

  width: 200px;
  padding: 20px 0;
}

.job-check {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  width: 100%;
}
.job-check h3 {
  border-bottom: 2px double var(--main-blue);
}
.job-scrap-info {
  display: flex;
  align-items: center;
  gap: 5px;
}
.job-apply {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.job-apply .btn {
  width: 200px;
}
</style>
