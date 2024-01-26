<template>
    <div class="QnAItem-container">
        <div class="container-left">
          <div>제목</div>
          <div>내용 몇 글자</div>
          <div>질문자</div>
        </div>
        <div class="container-right">
          <div>질문일(생성일자)</div>
          <div>좋아요</div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
  .QnAItem-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    width: 1344px;
    padding: 50px;
    box-sizing: border-box;
  }

  .container-right {
    margin-left: auto;
    display: grid;
    grid-template-rows: 1fr 1fr;
  }
</style>