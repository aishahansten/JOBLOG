<script setup>
import { ref } from "vue";
import EmployeeAuth from "./EmployeeAuth.vue";
import "@/assets/css/home/login.css";

const isEdit = ref(false);

const goToHome = () => {
  // Vue Router를 사용하는 경우: this.$router.push('/');
  window.location.href = '/';
};

const showEmployeeAuth = () => {
  isEdit.value = true;
};
</script>

<template>
  <div class="login-container" v-if="!isEdit">
    <div class="login-pharse">
      <h1>간단 기본정보 입력</h1>
      <p>이것만 입력하면 가입 끝!</p>
    </div>
    <div class="select-form">
      <h2>현재 신입인가요, 경력자인가요?</h2>
      <div class="select-status">
        <button :class="['select-btn', 'basic-btn', 'clickable-btn']" @click="goToHome">
          <h2>신입</h2>
          <p>취업준비생</p>
        </button>
        <button :class="['select-btn', 'basic-btn', 'clickable-btn']" @click="showEmployeeAuth">
          <h2>경력</h2>
          <p>현직자 및 이직준비생</p>
        </button>
      </div>
    </div>
  </div>
  <EmployeeAuth v-else />
</template>

<style scoped></style>
