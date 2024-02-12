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
      </div>
      <div class="application-list">
        <ResumeListView/>
      </div>
    </div>
    <div class="application-right">
      <div class="application-header">
          <h1>자기소개서</h1>
        <div class="header-right">
          <button @click="toggleModal" class="w-btn w-btn-indigo">+ 추가하기</button>
        </div>
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
        <input type="text" class="category-dropdown" v-model="essays.category">
        <button v-if="!showAdd" @click="toggleShowAdd" class="w-btn w-btn-indigo" type="button">추가</button>
        <button v-if="showAdd" @click="toggleShowAdd" class="w-btn w-btn-indigo" type="button">취소</button>

        <div v-if="showAdd" class="add-category">
          <input type="text" placeholder="카테고리를 추가하세요">
        </div>
      </div>
      <label for="question">질문:</label>
      <input type="text" id="question" v-model="essays.question">

      <label for="answer">답변:</label>
      <textarea id="answer" v-model="essays.answer"></textarea>
      <div>{{ essays.answer.length }}자</div>
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
import { useEssayResumeStore } from '@/stores/essayResume';

const essayResumeStore = useEssayResumeStore()

const essays = ref({
    userId: '',
    // recruitId: null,
    // categoryId: null,
    question: '답변을 입력해주세요',
    answer: '문항을 입력해주세요',
  });

const submitEssay = async() => {
  await essayResumeStore.createEssay(essays)
  console.log('에세이 컴포 벨류', essays)
  // showModal.value = false
  // essays.value = {
  //   userId: '',
  //   recruitId: 1,
  //   categoryId: null,
  //   question: '',
  //   answer: '',
  // }
}
const essayList = ref([])

const showModal = ref(false);
const toggleModal = function() {
  showModal.value = !showModal.value
  console.log(showModal.value)
}

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