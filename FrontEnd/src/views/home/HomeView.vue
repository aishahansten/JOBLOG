<template>
  <!-- 로그인 시 화면 전환되는 메인 페이지를 구현하기 위한 버튼 -->
  <button @click="toggleLogin">로그인 상태 전환인 척하는 버튼</button>

  <div class="container-visitor" v-if="!isLoggedIn">
    <div class="welcome-pharse">
      <div class="welcome-title">
        <p class="bold">나의 첫 취업준비,</p>
        <p class="black">JOBLOG.</p>
      </div>
      <div class="welcome-txt">
        JOBLOG에서 <br />
        나의 <b>취준이력</b>과 <b>스케줄 관리</b>, <br />
        <b>현직자와의 커피챗</b>까지 <br />
        경험해보세요.
      </div>
    </div>

    <RouterLink :to="{ name: 'Login' }">
      <button class="login-btn-solid clickable">
        SNS 계정으로 시작하기 ->
      </button>
    </RouterLink>
  </div>

  <div class="container-member" v-else>
    <p>로그인 시 표시할 화면</p>
    <Calendar />
    <ResumeEssay />
    <ResumeEssay2 />
    <Chatter />
  </div>
</template>

<script setup>
import { ref } from "vue";
import Calendar from "./components/Calendar.vue";
import ResumeEssay from "./components/ResumeEssay.vue";
import Chatter from "./components/Chatter.vue";
// 로그인/로그아웃 상태를 대체하는 버튼 및 화면 전환용 로직
// 나중에는 isAuthenticated나, token값 등을 사용하여 버튼 없이 자동 전환
const isLoggedIn = ref(false);

function toggleLogin() {
  isLoggedIn.value = !isLoggedIn.value;
}
// ----------------------------------------------------
</script>

<style scoped>
.container {
  background-image: url("@/assets/img/home/background-img.png");
  background-size: cover;
  background-position: center;
  position: relative;
  width: 100%;
  height: 880px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
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
.login-btn-solid {
  color: white;
  text-align: center;
  font-size: 32px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  background-color: var(--main-blue);
  border: var(--main-blue) 2px solid;
  padding: 30px 50px;
  border-radius: 8px;
}
.container-members {
  width: 1344px;
}
</style>
