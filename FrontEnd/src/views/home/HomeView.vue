<script setup>
import { computed } from "vue";
import Calendar from "./components/Calendar.vue";
import ResumeEssay from "./components/ResumeEssay.vue";
import Chatter from "./components/Chatter.vue";
import "@/assets/css/home/welcome.css";
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore();

const loggedIn = computed(() => !!authStore.userInfo);

authStore.updateUserInfoFromToken();

</script>

<template>
  <div class="container-visitor" v-if="!loggedIn">
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

    <div class="welcome-btn-space">
      <RouterLink :to="{ name: 'Login' }">
        <a class="login-btn-solid clickable"> SNS 계정으로 시작하기 -> </a>
      </RouterLink>
    </div>
  </div>

  <div class="container-member" v-else>
    <Calendar />
    <ResumeEssay />
    <ResumeEssay2 />
    <Chatter />
  </div>
</template>
