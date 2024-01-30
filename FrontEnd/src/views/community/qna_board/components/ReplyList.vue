<template>
<div class="answers" v-if="answers.length > 0">
  <div class="answer" v-for="(answer, index) in answers" :key="index">
    <div class="header">
      <div><img src="@/assets/img/community/A.svg" class="answer-icon"></div>
      <div class="title">{{ answer.title }}</div>
    </div>
    <div class="content">{{ answer.content }}</div>
    <!-- Add other fields you want to display, like created_date -->
  </div>
</div>
</template>
  
<script>
  export default {
    props: {
      answers: Array
    }
  }
</script>

<style scoped>
  .header { 
    display: grid;
    grid-template-columns: 150px auto;
  }
  .answer {
    min-width: 1200px;
    border: 1px black solid;
    border-radius: 8px;
    min-height: 600px;
    padding: 30px;
    box-sizing: border-box;
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

  .answer-icon {
    height: 100px;
    width: 100px;
  }
</style>
  