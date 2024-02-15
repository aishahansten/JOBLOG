<template>
  <div class="form-container">
    <form @submit.prevent="submitForm">
      <div>
        <label for="companyName">회사 이름</label>
        <input type="text" id="companyName" v-model="data.companyName" :readonly="true" />
      </div>
      <div>
        <label for="applyJob">지원 단계(면접, 코딩테스트...)</label>
        <input type="text" id="applyJob" v-model="recruitStep.title" required />
      </div>
      <div>
        <label for="step">단계</label>
        <select id="step" v-model="recruitStep.step">
          <option disabled :value="0">면접 단계를 선택해주세요</option>
          <option :value="1">1단계</option>
          <option :value="2">2단계</option>
          <option :value="3">3단계</option>
          <option :value="4">4단계</option>
          <option :value="5">5단계</option>
          <option :value="6">최종단계</option>
        </select>
      </div>
      <div class="status radio-box">
        <label class="status-label">상태</label>
        <div class="radio-group">
          <input type="radio" id="status1" value="응시전" v-model="recruitStep.status" />
          <label for="status1">응시전</label>

          <input type="radio" id="status2" value="결과 대기 중" v-model="recruitStep.status" />
          <label for="status2">결과 대기 중</label>

          <input type="radio" id="status3" value="합격" v-model="recruitStep.status" />
          <label for="status3">합격</label>

          <input type="radio" id="status4" value="불합격" v-model="recruitStep.status" />
          <label for="status4">불합격</label>
        </div>
      </div>
      <div>
        <label for="progressDate">날짜</label>
        <input type="datetime-local" id="progressDate" v-model="recruitStep.progressDate" />
      </div>
      <button>전형 저장하기</button>
      <div>
        <button type="button" @click="closeModal">닫기</button>
      </div>
    </form>
  </div>
  <a href="https://joblog.pro/172.0.0.8"></a>
</template>

<script setup>
import { ref } from "vue";
import { useBlogReviewStore } from "@/stores/blogReview";

const blogReviewStore = useBlogReviewStore();

const props = defineProps({
  data: Object,
});

const emit = defineEmits(["close"]);

const closeModal = () => {
  emit("close");
};

const submitForm = async () => {
  await blogReviewStore.createStep(recruitStep);
  emit("close");
};

const recruitStep = ref({
  myRecruitId: props.data.id,
  userId: props.data.userId,
  progressDate: "",
  title: "",
  status: "응시전",
  step: 1,
});
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
