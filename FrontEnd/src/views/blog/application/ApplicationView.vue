<template>
  <SubNav/>

  <div class="application-container">
    <div class="application-left">
      <div class="application-header">
        <div>
          <h1>이력서</h1>
        </div>
        <div class="header-right">
          <RouterLink :to="{ name: 'ResumeCreate' }"> <h1>+</h1></RouterLink>
        </div>
        <hr>
      </div>
      <div class="application-list">
        이력서 나올 곳
        <ResumeListView/>
      </div>
    </div>
    <div class="application-right">
      <div class="application-header">
          <h1>자기소개서</h1>
        <div class="header-right">
          <button @click="toggleModal" class="w-btn w-btn-indigo">+ 추가하기</button>
          <button class="dummy-essay" @click="addDummyEssay">자소서 있는척 해보기</button>
        </div>
        <hr>
      </div>
      <EssayList :essayList="essayList"/>
    </div> 
  </div>
  <div v-if="showModal" class="modal">
    <div class="modal-content">
      <h2>새 항목 작성</h2>
      <div>
        <label for="job-post">채용공고:</label>
        <input type="text" class="job-post-dropdown">
        <label for="category">카테고리:</label>
        <input type="text" class="category-dropdown" v-model="newEssay.category">
        <button v-if="!showAdd" @click="toggleShowAdd" class="w-btn w-btn-indigo" type="button">추가</button>
        <button v-if="showAdd" @click="toggleShowAdd" class="w-btn w-btn-indigo" type="button">취소</button>

        <div v-if="showAdd" class="add-category">
          <input type="text" placeholder="카테고리를 추가하세요">
        </div>
      </div>
      <label for="question">질문:</label>
      <input type="text" id="question" v-model="newEssay.question">

      <label for="answer">답변:</label>
      <textarea id="answer" v-model="newEssay.answer"></textarea>
      <div>{{ newEssay.answer.length }}자</div>
      <div class="modal-buttons">
        <button @click="submitEssay" class="w-btn w-btn-indigo" type="button">제출</button>

        <button @click="toggleModal" class="w-btn w-btn-indigo" type="button">닫기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, computed, onMounted } from 'vue';

import SubNav from '../_component/SubNav.vue';
import EssayList from '@/views/blog/application/components/EssayList.vue'
import DummyUser from './resume/dummyuser.json'
import ResumeListView from './resume/ResumeListView.vue';


const essayList = ref([])

const showModal = ref(false);
const toggleModal = function() {
  showModal.value = !showModal.value
  console.log(showModal.value)
}

//testing code
let currentId = 1;

const addDummyEssay = () => {
  const dummyData = {
    essayId: currentId,
    categoryId: 1,
    userId: 1,
    recruitId: 1,
    question: `Dummy Resume Title ${currentId}`,
    answer: `Dummy resume content for item ${currentId}`,
    // Add other fields as needed
  };

  essayList.value.push(dummyData);
  console.log(essayList.value);
  // Increment currentId for the next essay to be added
  currentId++;
};

const newEssay = ref({ 
  categoryId: '',
  question: '',
  answer: ''
})

const submitEssay = () => {
  let test = 1
  const dummyData = {
    essayId: test,
    categoryId: 1,
    userId: 1,
    recruitId: 1,
    question: newEssay.value.question,
    answer: newEssay.value.answer,
    // Add other fields as needed
  }

  essayList.value.push(dummyData)

  
  showModal.value = false
  newEssay.value = { 
    categoryId: '',
    question: '',
    answer: ''
  }
}
// ------------------------------------------------


const showAdd = ref(false)
const toggleShowAdd = function() {
  showAdd.value = !showAdd.value
}


</script>

<style scoped>

  .application-container {
    display: grid;  
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    width: 1344px;
    box-sizing: border-box;
    padding: 20px;
    border: 1px solid black;
    border-radius: 8px;
    margin-top: 20px;
  }

  .application-left, .application-right {
    display: grid;
    grid-template-rows: 1fr 9fr;
    border: 1px solid black;
    border-radius: 8px;
    box-sizing: border-box;
    padding: 40px;
    max-height: 900px;
  }

  .application-header {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .header-right {
    display: flex;
    margin-left: auto;
    justify-content: center;
  }

  .application-list, .essay-list {
    border: 1px solid black;
    border-radius: 8px;
  }

  .empty-essay {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 500px;
    font-size: 24px;
  }

  .modal {
    position: fixed;
    top: 10%;
    width: 1100px;
    height: 700px;
    background-color: rgba(0, 0, 0, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1;
  }

  .modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    width: 1000px;
    height: 600px;
  }

  .modal-content textarea {
    resize: none;
    width: 994px;
    height: 200px
  }

  .modal-buttons {
    margin-left: auto;
  }
  .modal-content h2 {
    margin-top: 0;
  }


  p {
    margin: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: fit-content;
  }

  label {
    font-size: 20px;
    margin-right: 10px;
  }

  input {
    font-size: 24px;
    margin-right: 30px;
  }
</style>