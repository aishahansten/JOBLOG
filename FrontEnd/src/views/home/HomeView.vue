<script setup>
import { ref, computed, onMounted } from "vue";
import Calendar from "./components/Calendar.vue";
import ResumeEssay from "./components/ResumeEssay.vue";
import Chatter from "./components/Chatter.vue";

import "@/assets/css/home/welcome.css";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const loggedIn = computed(() => !!authStore.userInfo);

authStore.updateUserInfoFromToken();

const retryCount = ref(0);
const hasAlarm = ref(false);
let sse;

const startSSE = async () => {
  sse = new EventSource(`${import.meta.env.VITE_API_BASE_URL}/connect/${authStore.userInfo.sub}`);

  sse.onmessage = (event) => {
    if (event.data !== "connected") {
      hasAlarm.value = true;
    }
  };

  sse.onerror = (event) => {
    if (event.target.readyState === EventSource.CLOSED) {
      retryCount.value++;
      setTimeout(startSSE, 5000);
    }
  };
};

onMounted(async () => {
  if (loggedIn) {
    startSSE();
  } else {
  }
});
</script>

<template>
  <div class="container-visitor" v-if="!loggedIn">
    <div class="welcome-pharse">
      <div class="title">
        <p class="f-weight-b">나의 첫 취업준비,</p>
        <p class="f-weight-t">JOBLOG.</p>
      </div>
      <div class="introduce f-weight-l">
        JOBLOG에서 <br />
        나의 <b>취준이력</b>과 <b>스케줄 관리</b>, <br />
        <b>현직자와의 커피챗</b>까지 <br />
        경험해보세요.
      </div>
    </div>

    <div class="welcome-btn-space">
      <RouterLink :to="{ name: 'Login' }">
        <a id="to-login" class="btn solid-c h-bright a-dark"> SNS 계정으로 시작하기 -> </a>
      </RouterLink>
    </div>
  </div>

  <div class="container-member" v-else>
    <RouterLink :to="{ name: 'Alarm' }">
      <div class="btn-s lined-bg f-color-c h-solid-c h-lined-c a-bright logout-btn" v-if="hasAlarm"></div>
    </RouterLink>

    <Calendar />
    <ResumeEssay />
    <Chatter />
  </div>
</template>

<style scoped>
.container-visitor {
  background-image: url("@/assets/img/home/background-img.png");
  background-position: center;
  background-repeat: no-repeat;
  width: 100%;
  height: 880px;
  text-align: center;
}

.welcome-pharse {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  text-align: left;
  padding-top: 150px;
  gap: 15px;
}
.title {
  font-size: 60px;
}
.introduce {
  font-size: 32px;
  line-height: 130%;
}
.introduce b {
  font-weight: 500;
}

.welcome-btn-space {
  display: flex;
  justify-content: end;
}
#to-login {
  text-align: center;
  font-size: 32px;
  padding: 30px 50px;
}

.container-member {
  display: flex;
  flex-direction: column;
  gap: 100px;
  padding: 50px 0;
}

.logout-btn {
  position: absolute;
  left: 90%;
}
</style>
