<script setup>
import { ref } from "vue";
import SelectStatus from "./components/login/SelectStatus.vue";
import "@/assets/css/home/login.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="login-container" v-if="!isEdit">
    <div class="login-pharse">
      <h1>가입과 로그인 모두<br />SNS 계정으로</h1>
      <span>간편하게 시작하세요.</span>
    </div>
    <RouterLink :to="{ name: 'Home' }">
      <div class="login-btn-space">
        <div id="google-login-btn" class="social-login-btn clickable">
          <img
            src="@/assets/img/login/icon/logo-google.svg"
            id="google-login-icon"
            class="login-icon"
            alt=""
          />
          <span>Google 로그인</span>
        </div>
        <div id="kakao-login-btn" class="social-login-btn clickable">
          <img
            src="@/assets/img/login/icon/logo-kakao.png"
            class="login-icon"
            alt=""
          />
          <span>Kakao 로그인</span>
        </div>
        <div id="naver-login-btn" class="social-login-btn clickable">
          <img
            src="@/assets/img/login/icon/logo-naver.png"
            class="login-icon"
            alt=""
          />
          <span>Naver 로그인</span>
        </div>
      </div>
    </RouterLink>
    <button @click="toggleEdit">첫 로그인이라면..!</button>
  </div>
  <SelectStatus v-else />
</template>

<style scoped></style>
