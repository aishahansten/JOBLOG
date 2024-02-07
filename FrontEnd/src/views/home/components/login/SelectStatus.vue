<script setup>
import { ref } from "vue";
import EmployeeAuth from "./EmployeeAuth.vue";

const isEdit = ref(false);

const goToHome = () => {
  window.location.href = "/";
};

const showEmployeeAuth = () => {
  isEdit.value = true;
};
</script>

<template>
  <div class="container" v-if="!isEdit">
    <div class="title">
      <h1>간단 기본정보 입력</h1>
      <p class="f-weight-l">이것만 입력하면 가입 끝!</p>
    </div>
    <div class="form">
      <h2>현재 신입인가요, 경력자인가요?</h2>
      <div class="select-status">
        <a class="select-btn btn lined-c h-solid-c a-dark" @click="goToHome">
          <h2>신입</h2>
          <p>취업준비생</p>
        </a>
        <a
          class="select-btn btn lined-c h-solid-c a-dark"
          @click="showEmployeeAuth"
        >
          <h2>경력</h2>
          <p>현직자 및 이직준비생</p>
        </a>
      </div>
    </div>
  </div>
  <EmployeeAuth v-else />
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 50px;
  min-height: 800px;

  background-image: url("@/assets/img/login/background-img.png");
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
  text-align: center;
}
.title {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

/* 현직자 여부 질문 페이지 */
.form {
  display: flex;
  width: 700px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
  padding: 60px;

  background-color: white;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.select-status {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  padding: 10px;
}

.select-btn {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 5px;

  width: 250px;
  padding: 15px;
}
.select-btn p {
  font-size: 20px;
}
</style>
