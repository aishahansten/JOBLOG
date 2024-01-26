<template>
  <!-- 로그인 시 화면 전환되는 메인 페이지를 구현하기 위한 버튼 -->
  <button @click="toggleLogin">로그인 상태 전환인 척하는 버튼</button>

  <div v-if="!isLoggedIn">
    <div class="main-img-container">
      <img
        src="../../assets/img/home/main-img-1.png"
        alt="main img"
        class="main-img"
      />
    </div>

    <RouterLink :to="{ name: 'Login' }">
      <img
        src="../../assets/img/home/main-login-img.png"
        alt="loginbutton"
        class="login-button-img"
      />
    </RouterLink>
  </div>

  <div v-else class="logged-in-content">
      <p>로그인 시 표시할 화면</p>
      <Calendar/>
      <ResumeEssay/>
      <ResumeEssay2/>
      <Chatter/>
  </div>

  <!-- Toggle Button -->
</template>

<script setup>
import { ref } from 'vue'
import Calendar from './components/Calendar.vue';
import ResumeEssay from './components/ResumeEssay.vue';
// import ResumeEssay2 from './components/ResumeEssay2.vue';
import Chatter from './components/Chatter.vue'
// 로그인/로그아웃 상태를 대체하는 버튼 및 화면 전환용 로직
// 나중에는 isAuthenticated나, token값 등을 사용하여 버튼 없이 자동 전환
const isLoggedIn = ref(false);

function toggleLogin() {
  isLoggedIn.value = !isLoggedIn.value;
}
// ----------------------------------------------------
</script>

<style scoped>
button {
  margin: 20px;
}
.main-img-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: -1;
  padding: 0px;
  margin: 0px;
}

.main-img {
  margin: 0px;
  padding: 0px;
}
.login-button-img {
  position: relative;
  top: -400px;
  left: 73%;
  transform: translate(-50%, -50%);
  border-radius: 8px;
}
.logged-in-content {
  width: 1344px;
}
</style>
