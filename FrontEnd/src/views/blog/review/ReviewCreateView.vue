<template>
  <div class="form-container">
    <form @submit.prevent="submitForm">
      <!-- Company Name -->
      <div>
        <label for="companyName">회사 이름</label>
        <input type="text" id="companyName" v-model="formData.company_name" :readonly="true">
      </div>
      <!-- Apply Job -->
      <div>
        <label for="applyJob">지원 직무</label>
        <input type="text" id="applyJob" v-model="formData.apply_job">
      </div>
      <!-- Step -->
      <div>
        <label for="step">단계</label>
        <select id="step" v-model="formData.step">
          <option disabled value="">면접 단계를 선택해주세요</option>
          <option value="1">1단계</option>
          <option value="2">2단계</option>
          <option value="3">3단계</option>
          <option value="3">4단계</option>
          <option value="3">5단계</option>
          <option value="3">최종단계</option>
          <!-- Add more options as needed -->
        </select>
      </div>

      <!-- Status -->
      <div class="status radio-box">
        <label class="status-label">상태</label>
        <div class="radio-group">
          <input type="radio" id="status1" value="1" v-model="formData.status">
          <label for="status1">응시전</label>

          <input type="radio" id="status2" value="2" v-model="formData.status">
          <label for="status2">결과 대기 중</label>

          <input type="radio" id="status3" value="3" v-model="formData.status">
          <label for="status3">합격</label>

          <input type="radio" id="status4" value="4" v-model="formData.status">
          <label for="status4">불합격</label>
        </div>
      </div>

      <!-- Date -->
      <div>
        <label for="date">날짜</label>
        <input type="date" id="date" v-model="formData.date">
      </div>
      <button>전형 저장하기</button>
      <button @click="toggleReview" v-if="!openReview">리뷰 작성하기</button>
      <button @click="toggleReview" v-if="openReview">취소</button>
      <div v-if="openReview" class="actual-review">

        <!-- Type -->
        <div class="type radio-box">
          <label for="type">유형</label>
          <div class="radio-group">
            <input type="radio" id="type1" value="1" v-model="formData.type">
            <label for="type1">일대일</label>

            <input type="radio" id="type2" value="2" v-model="formData.type">
            <label for="type2">일대다</label>

            <input type="radio" id="type3" value="3" v-model="formData.type">
            <label for="type3">다대다</label>

            <input type="radio" id="type4" value="4" v-model="formData.type">
            <label for="type4">코딩테스트</label>

            <input type="radio" id="type5" value="5" v-model="formData.type">
            <label for="type5">과제면접</label>
            
            <input type="radio" id="type6" value="6" v-model="formData.type">
            <label for="type6">기타</label>
          </div>
        </div>

        <!-- Total Question -->
        <div class="total-question radio-box">
          <label for="totalQuestion">총 문항수</label>
          <div class="radio-group">
            <input type="radio" id="totalQuestion1" value="1" v-model="formData.total_question">
            <label for="totalQuestion1">1~2</label>

            <input type="radio" id="totalQuestion2" value="2" v-model="formData.total_question">
            <label for="totalQuestion2">3~4</label>

            <input type="radio" id="totalQuestion3" value="3" v-model="formData.total_question">
            <label for="totalQuestion3">5~6</label>

            <input type="radio" id="totalQuestion4" value="4" v-model="formData.total_question">
            <label for="totalQuestion4">7+</label>

            <input type="radio" id="totalQuestion5" value="5" v-model="formData.total_question">
            <label for="totalQuestion5">해당 없음</label>
          </div>
        </div>

        <!-- Time Required -->
        <div class="time-required radio-box">
          <label for="timeRequired">소요시간</label>
          <div class="radio-group">
            <input type="radio" id="timeRequired1" value="1" v-model="formData.time_required">
            <label for="timeRequired1">10~20분</label>

            <input type="radio" id="timeRequired2" value="2" v-model="formData.time_required">
            <label for="timeRequired2">20~30분</label>

            <input type="radio" id="timeRequired3" value="3" v-model="formData.time_required">
            <label for="timeRequired3">30~40분</label>

            <input type="radio" id="timeRequired4" value="4" v-model="formData.time_required">
            <label for="timeRequired4">40~50</label>

            <input type="radio" id="timeRequired5" value="5" v-model="formData.time_required">
            <label for="timeRequired5">50+</label>
          </div>
        </div>



        <!-- Difficulty -->
        <div class="difficulty radio-box">
          <label for="difficulty">난이도</label>
          <div class="radio-group">
            <input type="radio" id="difficulty1" value="1" v-model="formData.difficulty">
            <label for="difficulty1">매우 쉬움</label>

            <input type="radio" id="difficulty2" value="2" v-model="formData.difficulty">
            <label for="difficulty2">쉬움</label>

            <input type="radio" id="difficulty3" value="3" v-model="formData.difficulty">
            <label for="difficulty3">보통</label>

            <input type="radio" id="difficulty4" value="4" v-model="formData.difficulty">
            <label for="difficulty4">어려움</label>

            <input type="radio" id="difficulty5" value="5" v-model="formData.difficulty">
            <label for="difficulty5">매우 어려움</label>
          </div>
        </div>

        <!-- Atmosphere -->
        <div class="atmosphere radio-box">
          <label for="atmosphere">분위기</label>
          <div class="radio-group">
            <input type="radio" id="atmosphere1" value="1" v-model="formData.atmosphere">
            <label for="atmosphere1">매우 편안함</label>

            <input type="radio" id="atmosphere2" value="2" v-model="formData.atmosphere">
            <label for="atmosphere2">편안함</label>

            <input type="radio" id="atmosphere3" value="3" v-model="formData.atmosphere">
            <label for="atmosphere3">보통</label>

            <input type="radio" id="atmosphere4" value="4" v-model="formData.atmosphere">
            <label for="atmosphere4">엄격함</label>

            <input type="radio" id="atmosphere5" value="5" v-model="formData.atmosphere">
            <label for="atmosphere5">매우 엄격함</label>
          </div>
        </div>

        <!-- Comment -->
        <div>
          <label for="comment">코멘트</label>
          <textarea id="comment" v-model="formData.comment"></textarea>
        </div>

        <!-- Review -->
        <div>
          <label for="review">리뷰</label>
          <textarea id="review" v-model="formData.review"></textarea>
        </div>
        <button type="submit">저장하기</button>
      </div>
      
      <!-- Buttons -->
      <div>

        <button type="button" @click="closeModal">닫기</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  data: Object
})
const autoStep = String((props.data.reviews?.length ?? 0) + 1);

const formData = ref({
  company_name: props.data.company_name,
  company_code: '',
  step: '',
  status: '1',
  date: '',
  review_or_not: false,
  type: '',
  total_question: '',
  time_required: '',
  apply_job: '',
  difficulty: '',
  atmosphere: '',
  comment: '',
  review: '',
  // ... add other fields as needed
});

const submitForm = () => {
  // Handle form submission, e.g., send data to your backend server
  console.log(formData.value);
};

// Define the emits option
const emit = defineEmits(['close']);

// Define the method to emit the close event
const closeModal = () => {
  emit('close');
};

const openReview = ref(false)

const toggleReview = function() {
  openReview.value = !openReview.value
}
</script>

<style scoped>

  .actual-review {
    margin-top: 10px;
  }
  /* Styles for the form container */
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