<template>
    <form @submit.prevent="submitReview()">
      <div>
        <label for="applyJob">지원 직무</label>
        <textarea id="applyJob" v-model="formData.applyJob" required></textarea>
      </div>

      <!-- Type -->
      <div class="type radio-box">
        <label for="type">유형</label>
        <div class="radio-group">
          <input type="radio" id="type1" value="일대일" v-model="formData.type" required>
          <label for="type1">일대일</label>

          <input type="radio" id="type2" value="일대다" v-model="formData.type" required>
          <label for="type2">일대다</label>

          <input type="radio" id="type3" value="다대다" v-model="formData.type" required>
          <label for="type3">다대다</label>

          <input type="radio" id="type4" value="코딩테스트" v-model="formData.type" required>
          <label for="type4">코딩테스트</label>

          <input type="radio" id="type5" value="과제면접" v-model="formData.type" required>
          <label for="type5">과제면접</label>
          
          <input type="radio" id="type6" value="기타" v-model="formData.type" required>
          <label for="type6">기타</label>
        </div>
      </div>

      <!-- Total Question -->
      <div class="total-question radio-box">
        <label for="totalQuestion">총 문항수</label>
        <div class="radio-group">
          <input type="radio" id="totalQuestion1" value="2" v-model="formData.totalQuestion" required>
          <label for="totalQuestion1">1~2</label>

          <input type="radio" id="totalQuestion2" value="4" v-model="formData.totalQuestion" required>
          <label for="totalQuestion2">3~4</label>

          <input type="radio" id="totalQuestion3" value="6" v-model="formData.totalQuestion" required>
          <label for="totalQuestion3">5~6</label>

          <input type="radio" id="totalQuestion4" value="7" v-model="formData.totalQuestion" required>
          <label for="totalQuestion4">7+</label>

          <input type="radio" id="totalQuestion5" value="0" v-model="formData.totalQuestion" required>
          <label for="totalQuestion5">해당 없음</label>
        </div>
      </div>

      <!-- Time Required -->
      <div class="time-required radio-box">
        <label for="timeRequired">소요시간</label>
        <div class="radio-group">
          <input type="radio" id="timeRequired1" value="20" v-model="formData.timeRequired" required>
          <label for="timeRequired1">10~20분</label>

          <input type="radio" id="timeRequired2" value="30" v-model="formData.timeRequired" required>
          <label for="timeRequired2">20~30분</label>

          <input type="radio" id="timeRequired3" value="40" v-model="formData.timeRequired" required>
          <label for="timeRequired3">30~40분</label>

          <input type="radio" id="timeRequired4" value="50" v-model="formData.timeRequired" required>
          <label for="timeRequired4">40~50</label>

          <input type="radio" id="timeRequired5" value="0" v-model="formData.timeRequired" required>
          <label for="timeRequired5">50+</label>
        </div>
      </div>

      <!-- Difficulty -->
      <div class="difficulty radio-box">
        <label for="difficulty">난이도</label>
        <div class="radio-group">
          <input type="radio" id="difficulty1" value="1" v-model="formData.difficulty" required>
          <label for="difficulty1">매우 쉬움</label>

          <input type="radio" id="difficulty2" value="2" v-model="formData.difficulty" required>
          <label for="difficulty2">쉬움</label>

          <input type="radio" id="difficulty3" value="3" v-model="formData.difficulty" required>
          <label for="difficulty3">보통</label>

          <input type="radio" id="difficulty4" value="4" v-model="formData.difficulty" required>
          <label for="difficulty4">어려움</label>

          <input type="radio" id="difficulty5" value="5" v-model="formData.difficulty" required>
          <label for="difficulty5">매우 어려움</label>
        </div>
      </div>

      <!-- Atmosphere -->
      <div class="atmosphere radio-box">
        <label for="atmosphere">분위기</label>
        <div class="radio-group">
          <input type="radio" id="atmosphere1" value="1" v-model="formData.atmosphere" required>
          <label for="atmosphere1">매우 편안함</label>

          <input type="radio" id="atmosphere2" value="2" v-model="formData.atmosphere" required>
          <label for="atmosphere2">편안함</label>

          <input type="radio" id="atmosphere3" value="3" v-model="formData.atmosphere" required>
          <label for="atmosphere3">보통</label>

          <input type="radio" id="atmosphere4" value="4" v-model="formData.atmosphere" required>
          <label for="atmosphere4">엄격함</label>

          <input type="radio" id="atmosphere5" value="5" v-model="formData.atmosphere" required>
          <label for="atmosphere5">매우 엄격함</label>
        </div>
      </div>

      <!-- Comment -->
      <div>
        <label for="comment">코멘트</label>
        <textarea id="comment" v-model="formData.comment" required></textarea>
      </div>

      <!-- Review -->
      <div>
          <label for="review">리뷰</label>
          <textarea id="review" v-model="formData.review" required></textarea>
      </div>
        <button type="submit">저장하기</button>
        <button @click="closeModal">닫기</button>
    </form>
</template>

<script setup>
import { ref } from 'vue';
import { useBlogReviewStore } from '@/stores/blogReview'

const blogReviewStore = useBlogReviewStore()

import LoginViewLocalVue from '@/views/home/LoginViewLocal.vue';

const props = defineProps({
  step: Object
})

const formData = ref({
  selectionId: props.step.id,
  type: "",
  totalQuestion: "",
  timeRequired: "",
  applyJob: "",
  difficulty: "",
  atmosphere: "",
  comment: "",
  review: "",
})

const emit = defineEmits(['close2']);

const closeModal = () => {
  emit('close2');
};

const submitReview = function() {
  if (
    !formData.value.comment || 
    !formData.value.review || 
    !formData.value.type ||
    !formData.value.applyJob ||
    !formData.value.totalQuestion || 
    !formData.value.timeRequired || 
    !formData.value.difficulty ||
    !formData.value.atmosphere
  ) {
    alert("모든 필드를 채워주세요!");
    console.log(formData.value)
    return;
  }
  blogReviewStore.createStepReview(formData)
  console.log('submit',formData.value)
}
</script>

<style scoped>
  .form-container {
    display: grid;
    grid-template-columns: 1fr;
    gap: 20px;
    max-width: 500px;
    margin: auto;
  }

  /* Styles for form elements */
  input[type="text"],
  input[type="date"],
  select,
  textarea {
    width: 100%;
    padding: 8px;
    box-sizing: border-box; /* Ensures padding does not increase the size */
    margin: 5px 0; /* Adds a little space above and below */
  }

  /* Fixed size for input boxes and textareas */
  input[type="text"],
  input[type="date"],
  select {
    height: 40px; /* Fixed height for input and select */
  }

  textarea {
    height: 100px; /* Fixed height for textarea */
    resize: none; /* Disables resizing */
  }

  /* Styles for the radio buttons and their labels */
  .radio-box {
    display: flex;
    flex-direction: column; /* Stack vertically */
    margin-bottom: 10px;
    margin-top: 10px;
    padding-bottom: 10px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.289);
  }

  .radio-group {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); /* Creates a responsive layout for radio buttons */
    gap: 10px;
  }

  /* Ensuring radio buttons and labels are neatly organized */
  input[type="radio"] {
    margin-right: 5px;
    cursor: pointer;
  }

  label {
    cursor: pointer;
  }

  /* Button styles */
  button {
    width: 100%;
    padding: 10px;
    margin-top: 10px; /* Adds space above the buttons */
    cursor: pointer;
  }

  /* Second button to align with the first */
  button[type="button"] {
    margin-top: 5px; /* Adjust if necessary to align with the submit button */
  }
</style>