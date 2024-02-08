<template>
  <div class="container">
    <div class="header">
      <div>
        <h1 class="f-weight-t">나의 이력서·자소서 관리</h1>
        <i class="fa-solid fa-angle-right fa-xl"></i>
      </div>
    </div>

    <div class="content">
      <div class="applications">
        <div class="application">
          <div class="info">
            <h2>신한은행 지원 이력서</h2>
            <p>2024.02.04</p>
          </div>
          <img src="@/assets/img/icon/resume.svg" alt="" />
        </div>
        <div class="application">
          <div class="info">
            <h2>삼성SDS 지원 이력서</h2>
            <p>2024.02.02</p>
          </div>
          <img src="@/assets/img/icon/resume.svg" alt="" />
        </div>
        <div class="application">
          <div class="info">
            <h2>국민은행 지원 이력서</h2>
            <p>2024.01.20</p>
          </div>
          <img src="@/assets/img/icon/resume.svg" alt="" />
        </div>
      </div>

      <div class="applications">
        <div class="application">
          <div class="info">
            <h2>신한은행 지원 자소서</h2>
            <p>2024.02.04</p>
          </div>
          <img src="@/assets/img/icon/essay.svg" alt="" />
        </div>
        <div class="application">
          <div class="info">
            <h2>삼성SDS 지원 자소서</h2>
            <p>2024.02.02</p>
          </div>
          <img src="@/assets/img/icon/essay.svg" alt="" />
        </div>
        <div class="application">
          <div class="info">
            <h2>삼성SDS 지원 자소서 v2</h2>
            <p>2024.02.03</p>
          </div>
          <img src="@/assets/img/icon/essay.svg" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.header div {
  display: flex;
  align-items: center;
  gap: 10px;
}

.content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 30px;
}

.applications {
  display: flex;
  flex-direction: column;
  gap: 30px;
}
.application {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 30px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
.application img {
  padding: 0 10px;
}

.info {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
</style>
