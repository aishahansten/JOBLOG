<script setup>
import { ref } from "vue";
import SelectStatus from "./components/login/SelectStatus.vue";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}

function callGoogle() {
  window.location.href = "https://joblog.pro/api/oauth2/authorization/google";
}
function callKakao() {
  window.location.href = "https://joblog.pro/api/oauth2/authorization/kakao";
}
function callNaver() {
  window.location.href = "https://joblog.pro/api/oauth2/authorization/naver";
}
</script>

<template>
  <div class="container" v-if="!isEdit">
    <div class="title">
      <h1>가입과 로그인 모두</h1>
      <h1>SNS 계정으로</h1>
      <h1 class="f-weight-l">간편하게 시작하세요.</h1>
    </div>
    <div class="login-btn-space">
      <a
        @click="callGoogle"
        id="google-login-btn"
        class="social-login-btn btn lined-bg h-solid-c a-dark"
      >
        <img
          src="@/assets/img/login/icon/logo-google.svg"
          id="google-login-icon"
          class="login-icon"
          alt=""
        />
        <span>Google 로그인</span>
      </a>
      <a
        @click="callKakao"
        id="kakao-login-btn"
        class="social-login-btn btn h-bright a-dark"
      >
        <img
          src="@/assets/img/login/icon/logo-kakao.png"
          class="login-icon"
          alt=""
        />
        <span>Kakao 로그인</span>
      </a>
      <a
        @click="callNaver"
        id="naver-login-btn"
        class="social-login-btn btn h-bright a-dark"
      >
        <img
          src="@/assets/img/login/icon/logo-naver.png"
          class="login-icon"
          alt=""
        />
        <span>Naver 로그인</span>
      </a>
    </div>
    <button @click="toggleEdit">첫 로그인이라면..!</button>
  </div>
  <SelectStatus v-else />
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

.login-btn-space {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.social-login-btn {
  display: flex;
  width: 400px;
  height: 70px;
  justify-content: center;
  align-items: center;
  padding-right: 20px;
}
.social-login-btn span {
  font-family: "Roboto", sans-serif;
  font-size: 24px;
  font-weight: 400;
}

#google-login-btn:hover {
  border: none;
}
#kakao-login-btn {
  background-color: #fee500;
}
#naver-login-btn {
  background-color: #03c75a;
}
#naver-login-btn span {
  color: white;
}

.login-icon {
  width: 55px;
  height: 55px;
}
#google-login-icon {
  width: 25px;
  height: 25px;
  margin: 15px;
}
</style>
