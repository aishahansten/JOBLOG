<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";

const showDropdown = ref(false);
</script>

<template>
  <header>
    <RouterLink id="main-logo-link" :to="{ name: 'Home' }">
      <img src="@/assets/img/icon/main-logo.svg" alt="" />
    </RouterLink>

    <nav @mouseover="showDropdown = true" @mouseleave="showDropdown = false">
      <div id="nav-menu">
        <RouterLink class="link h-txt" :to="{ name: 'Jobs' }"
          >채용공고</RouterLink
        >
        <RouterLink class="link h-txt" :to="{ name: 'BlogSchedule' }"
          >취준플랜</RouterLink
        >
        <RouterLink class="link h-txt" :to="{ name: 'QnABoard' }"
          >커뮤니티</RouterLink
        >
        <RouterLink class="link h-txt" :to="{ name: 'Coffee' }"
          >커피챗</RouterLink
        >
      </div>
      <transition name="dropdown">
        <div class="dropdown-content" v-show="showDropdown">
          <p>테스트1</p>
          <p>테스트1</p>
          <p>테스트1</p>
          <p>테스트1</p>
        </div>
      </transition>

      <!-- <div class="right">
        <RouterLink :to="{ name: 'Login' }">
          <a
            id="header-login-btn"
            class="btn-s lined-bg f-color-c h-solid-c a-dark"
            >로그인 · 회원가입</a
          >
        </RouterLink>
      </div> -->
      <div class="right">
        <div id="alert" class="pointer h-bright a-dark">
          <img src="@/assets/img/icon/non-alert-icon.svg" alt="" />
        </div>
        <RouterLink class="h-bright a-dark" :to="{ name: 'ProfileSetting' }">
          <div id="to-profile">
            <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
          </div>
        </RouterLink>
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

  border-bottom: 1px solid var(--border-gray);
}

/* 메인 로고 링크 */
#main-logo-link img {
  width: 120px;
}

/* 네비게이션 메뉴 */
nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#nav-menu {
  display: flex;
  gap: 50px;
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
  margin-left: 50px;
}

#alert {
  display: flex;
  justify-content: end;
  align-items: end;
}
#alert img {
  height: 25px;
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
  top: 100%; /* Position right below the nav-container */
  background-color: white;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  opacity: 1;
}
.dropdown-enter-active,
.dropdown-leave-active {
  transition: opacity 0.1s;
}

.dropdown-enter-from,
.dropdown-leave-to {
  opacity: 0;
}

/* Show dropdown content when showDropdown is true */
.nav-container:hover .dropdown-content {
  display: flex;
  flex-direction: column;
}

.nav-container:hover .dropdown-content {
  display: flex;
  flex-direction: column; /* Stack the links vertically */
}

.dropdown-content p {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: center;
}

.dropdown-content p:hover {
  background-color: #ddd;
}
</style>
