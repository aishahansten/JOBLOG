<template>
  <div class="qnaDetailContainer">
    <div class="question">
      <div class="header">
        <div>
          <img src="@/assets/img/community/Q.svg" class="question-icon">
        </div>
        <div class="title">
            param 으로 받아온 질문 제목
        </div>
      </div>
      <div class="content">
        테스트 내용 삽입
      </div>
    </div>
    <div class="buttons">
      <button class="w-btn w-btn-indigo">답변하기</button>
    </div>
    <div class="answer">
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>

  * {
    font-size: 24px;
  }
  .qnaDetailContainer {
    display: flex;
    flex-direction: column;
    width: 1344px;
    border: 1px black solid;
    justify-content: center;
    gap: 20px;
  }

  .question, .answer {
    min-width: 1200px;
    border: 1px black solid;
    border-radius: 8px;
    min-height: 600px;
    padding: 30px;
    box-sizing: border-box;
  }

  .question-icon {
    height: 100px;
    width: 100px;
  }

  .header { 
    display: grid;
    grid-template-columns: 150px auto;
  }

  .content {
    padding: 30px;
    box-sizing: border-box;
  }
  .title {
    font-size: 40px;
    font-weight: bold;
    width: 987px;
  }

  .buttons {
    display: flex;
    justify-content: flex-end;
  }
</style>