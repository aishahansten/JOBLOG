<template>
  <div class="main-container">
      <h1>취준생/현직자 질문</h1>
      <h4>현직자라면!</h4>
      <p>1. 현직자 커뮤니티 게시판 이용 가능</p>
      <p>2. 채터로 등록 가능!</p>
      <p>- 회사 메일 인증하기</p>
      <div class="button-container">
      <button>아직 다닐 회사를 찾고 있어요</button>
      <button>네 인증할래요</button>
      </div>
  </div>

</template>

<script setup>

</script>

<style scoped>

.main-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
}
.button-container {
  display: flex;
  flex-direction: row;
}

button {
  width: 200px;
  margin: 50px;
}
</style>