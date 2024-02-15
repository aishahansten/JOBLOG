<script setup>
import { ref, computed, onMounted } from "vue";
import { RouterLink } from "vue-router";
import { useAuthStore } from "@/stores/auth";
const logout = function () {
  authStore.logout();
};

const showDropdown = ref(false);
const authStore = useAuthStore();
const loggedIn = computed(() => !!authStore.userInfo);
const userInfo = ref({});

onMounted(async () => {
  await authStore.updateUserInfoFromToken();
  userInfo.value = authStore.userInfo;
});
</script>

<template>
  <header>
    <RouterLink class="main-logo-link" :to="{ name: 'Home' }">
      <img src="@/assets/img/icon/main-logo.svg" alt="" />
    </RouterLink>

    <nav>
      <div id="nav-menu" @mouseover="showDropdown = true" @mouseleave="showDropdown = false">
        <RouterLink class="link h-txt" :to="{ name: 'Jobs' }">채용공고</RouterLink>
        <RouterLink class="link h-txt" :to="{ name: 'BlogSchedule' }">취준플랜</RouterLink>
        <RouterLink class="link h-txt" :to="{ name: 'QnABoard' }">커뮤니티</RouterLink>
        <RouterLink class="link h-txt" :to="{ name: 'Coffee' }">커피챗</RouterLink>
      </div>
      <transition name="dropdown">
        <div
          class="dropdown-content"
          v-show="showDropdown"
          @mouseover="showDropdown = true"
          @mouseleave="showDropdown = false"
        >
          <div class="width">
            <div class="width550">
              <div class="sub-nav">
                <RouterLink class="menu h-txt" :to="{ name: 'Jobs' }">공고 검색</RouterLink>
                <RouterLink class="menu h-txt" :to="{ name: 'BlogReview' }">전형 리뷰</RouterLink>
              </div>
              <div class="sub-nav">
                <RouterLink class="menu h-txt" :to="{ name: 'BlogSchedule' }">나의 일정 관리</RouterLink>
                <RouterLink class="menu h-txt" :to="{ name: 'BlogApplication' }">이력서·자소서</RouterLink>
                <RouterLink class="menu h-txt" :to="{ name: 'BlogJournal' }">다이어리</RouterLink>
              </div>
              <div class="sub-nav">
                <RouterLink class="menu h-txt" :to="{ name: 'QnABoard' }">Q&A 게시판</RouterLink>
              </div>
              <div class="sub-nav">
                <RouterLink class="menu h-txt" :to="{ name: 'Coffee' }">채터 탐색하기</RouterLink>
                <RouterLink class="menu h-txt" :to="{ name: 'ChatterReg' }"
                  ><div v-if="authStore.userInfo && userInfo.role == 'ROLE_JUNIOR'">마이프로필</div></RouterLink
                >
              </div>
            </div>
            <div class="width170">　</div>
          </div>
        </div>
      </transition>

      <div class="right" v-if="!loggedIn">
        <RouterLink :to="{ name: 'Login' }">
          <a id="header-login-btn" class="btn-s lined-bg f-color-c h-solid-c h-lined-c a-bright">로그인 · 회원가입</a>
        </RouterLink>
      </div>
      <div class="right" v-else>
        <RouterLink :to="{ name: 'Alarm' }">
          <div id="alert" class="pointer h-bright a-dark" :class="{ 'alert-highlighted': hasAlarm }">
            <img src="@/assets/img/icon/non-alert-icon.svg" alt="" />
          </div>
        </RouterLink>
        <RouterLink class="h-bright a-dark" :to="{ name: 'ProfileSetting' }">
          <div id="to-profile">
            <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
          </div>
        </RouterLink>
        <button class="btn-s lined-bg f-color-c h-solid-c h-lined-c a-bright logout-btn" @click="logout">
          로그아웃
        </button>
      </div>
    </nav>
  </header>
</template>

<style scoped>
/* 전체 컨테이너 */
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-shrink: 0;

  width: 100%;
  height: 80px;
  padding: 0 250px;
  box-sizing: border-box;
  position: relative;

  border-bottom: 1px solid var(--border-gray);
}

/* 메인 로고 링크 */
.main-logo-link img {
  width: 120px;
}

/* 네비게이션 메뉴 */
nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}

#nav-menu {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  align-items: center;
  width: 550px;
  height: 100%;
  /* background-color: aquamarine; */
}
.link {
  justify-content: space-between;
  padding: 10px;
  text-align: center;
  font-size: 20px;
  font-weight: 400;
}

.right {
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 50px;
  width: 150px;
  margin-left: 20px;
  /* background-color: violet; */
}

#alert {
  display: flex;
  justify-content: end;
  align-items: end;
}
#alert img {
  height: 25px;
}
.alert-highlighted {
  background-color: rgba(255, 122, 122, 0.295);
}

#to-profile {
  width: 30px;
  height: 30px;
  border-radius: 70%;
  overflow: hidden;
}
#to-profile img {
  width: 100%;
  object-fit: cover;
}

/* Styles for dropdown-content when it is visible */
.dropdown-content {
  display: block;
  position: absolute;
  left: 0;
  right: 0;
  top: 100%;
  background-color: white;
  border-bottom: 1px solid var(--border-gray);
  z-index: 2;
  opacity: 1;
  background-image: url("@/assets/img/home/nav-background-img.png");
  background-position: center;
  background-repeat: no-repeat;
}
.dropdown-enter-active {
  transition: opacity 0.2s ease;
}
.dropdown-leave-active {
  transition: opacity 0.1s ease;
}

.dropdown-enter-from,
.dropdown-leave-to {
  opacity: 0;
}

.width {
  display: flex;
  justify-content: end;
  width: 100%;
  padding: 20px 250px 50px 250px;
}
.width550 {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  width: 550px;
}
.width170 {
  width: 170px;
}

.sub-nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 30px;

  font-size: 18px;
}

.logout-btn {
  position: absolute;
  left: 90%;
}
</style>
