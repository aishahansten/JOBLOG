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
      <button class="w-btn w-btn-indigo" v-if="!showModal" @click="toggleModal">답변하기</button>
      <button class="w-btn w-btn-indigo" v-if="showModal" @click="toggleModal">답변취소</button>
    </div>
    <div class="answer-create-container" v-if="showModal">
      <div class="a-title">
        <input type="text" v-model="newAnswer.title" placeholder="제목을 입력하세요">
      </div>
      <div class="a-content">
        <textarea v-model="newAnswer.content" placeholder="본문 내용을 입력하세요"></textarea>
      </div>
      <div class="submit">
        <button class="w-btn w-btn-indigo" @click="submitAnswer">작성하기</button>
      </div>
  </div>
    <div class="answer">
      <ReplyList :answers="dummyAnswerList"/>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ReplyList from './components/ReplyList.vue';
// 답변 div 띄울 때
const showModal = ref(false)

const toggleModal = function() {
  showModal.value = !showModal.value
}
//----------------------------------
const newAnswer = ref({
  user_id: 1,
  title: '',
  content: '',
  created_date: '', // You might want to handle this differently, usually set on the server-side
})

const dummyAnswerList = ref ([])

const submitAnswer = function() {
  const answerCopy = { ...newAnswer.value };
  dummyAnswerList.value.push(answerCopy);

  // Reset newAnswer
  newAnswer.value = {
    user_id: 1,
    title: '',
    content: '',
    created_date: '', // Resetting the date, handle appropriately
  };
}

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

  .question {
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

  .answer-create-container {
    display: flex;
    flex-direction: column;
    width: 1344px;
    min-height: 600px;
    border: 1px solid black;
    padding: 20px; /* Add padding for better spacing */
    box-sizing: border-box;
  }

  .a-title input {
    width: 100%; /* Full width of the container */
    height: 40px; /* Fixed height */
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-bottom: 20px; /* Spacing between title and content */
  }

  .a-content textarea {
    width: 100%; /* Full width of the container */
    height: 400px; /* Fixed height */
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: none; /* Prevents resizing */
  }

  .submit {
    margin-left: auto;
  }
</style>