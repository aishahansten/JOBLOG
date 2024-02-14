<template>
  <div class="container">
    <div v-if="userInfo == 'ROLE_APPLICANT'">
      <div class="banner">
        <div class="banner-cover">
          <h1>커피챗 채팅</h1>
          <p>
            <b>취준생·현직자</b>와<br />
            언제 어디서나<br />
            빠르고 간편하게, <b>커피챗</b>.
          </p>
          <RouterLink :to="{ name: 'AuthStatus' }">
            <a class="btn solid-g h-solid-c a-bright">채터로 등록하기 위해선 현직자 인증을 해야합니다</a>
          </RouterLink>
        </div>
        <img src="@/assets/img/coffee-chat/banner.jpg" alt="" class="banner-img" />
      </div>
    </div>
    <div v-else>
      <RouterLink :to="{ name: 'ChatterReg' }">
        <div class="banner">
          <div class="banner-cover">
            <h1>커피챗 채팅</h1>
            <p>
              <b>취준생·현직자</b>와<br />
              언제 어디서나<br />
              빠르고 간편하게, <b>커피챗</b>.
            </p>
            <a class="btn solid-g h-solid-c a-bright">채터 등록하기</a>
          </div>
          <img src="@/assets/img/coffee-chat/banner.jpg" alt="" class="banner-img" />
        </div>
      </RouterLink>
    </div>
    <div class="title">
      <h2>커피챗 채터 찾기</h2>
      <p>고민에 대한 간단한 이야기를 나눌 채터 탐색</p>
    </div>
    <div class="content">
      <ChatterRecommend />
    </div>
  </div>
</template>

<script setup>
import ChatterRecommend from "./components/ChatterRecommend.vue";
import { useAuthStore } from "@/stores/auth";
import { ref, onMounted } from "vue";

const authStore = useAuthStore();

const userInfo = ref({});

onMounted(async () => {
  await authStore.updateUserInfoFromToken;
  userInfo.value = authStore.userInfo.role;
  console.log(userInfo.value);
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 50px 0;
}

.banner,
.banner-cover,
.banner-img {
  width: 100%;
  height: 400px;
  border-radius: 10px;
}
.banner {
  position: relative;
}
.banner-cover {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: end;
  gap: 20px;

  width: 1420px;
  padding: 100px;
  position: absolute;

  background: linear-gradient(0.25turn, rgba(0, 0, 0, 0) 25%, rgba(0, 0, 0, 0.8));
}
.banner-cover * {
  color: white;
  text-align: end;
}
.banner-cover:hover {
  background: linear-gradient(0.25turn, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 1));
  transition: 0.3s ease;
}

.title {
  padding: 30px 0 0 0;
}
</style>
