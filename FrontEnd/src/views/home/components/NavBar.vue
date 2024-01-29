<template>
  <header>
    <RouterLink class="logo-link" :to="{ name: 'Home' }">
      <img src="@/assets/img/icon/main-logo.svg" alt="" />
    </RouterLink>
    <nav
      class="nav-container"
      @mouseover="showDropdown = true"
      @mouseleave="showDropdown = false"
    >
      <div class="nav-links">
        <RouterLink class="link" :to="{ name: 'Jobs' }">채용공고</RouterLink>
        <RouterLink class="link" :to="{ name: 'BlogSchedule' }"
          >취준플랜</RouterLink
        >
        <RouterLink class="link" :to="{ name: 'QnABoard' }"
          >커뮤니티</RouterLink
        >
        <RouterLink class="link" :to="{ name: 'Coffee' }">취업Talk</RouterLink>
        <RouterLink class="link" :to="{ name: 'ProfileSetting' }"
          >임시세팅</RouterLink
        >
      </div>
      <!-- <transition name="dropdown">
          <div class="dropdown-content" v-show="showDropdown">
            <p>테스트1</p>
            <p>테스트1</p>
            <p>테스트1</p>
            <p>테스트1</p>
          </div>
        </transition> -->
    </nav>
    <RouterLink :to="{ name: 'Login' }">
      <button class="login-button">로그인</button>
    </RouterLink>
  </header>
</template>

<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";

const showDropdown = ref(false);
</script>

<style scoped>
header {
  display: flex;
  width: 100%;
  height: 100px;
  padding: 0 250px;
  justify-content: space-between;
  align-items: center;
  flex-shrink: 0;
}

.link {
  color: #130000;
  text-decoration: none;
  padding: 10px 20px;
  margin: 0 10px;
  transition: background-color 0.1s ease;
  text-align: center;
  font-family: Pretendard Variable;
  font-size: 26px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.login-button {
  color: #6880ff;
  text-align: center;
  font-family: Pretendard Variable;
  font-size: 24px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  background-color: white;
  border: #6880ff 2px solid;
  padding: 10px 20px;
  border-radius: 8px;
}
.nav-item {
  position: relative;
  display: inline-block;
}

.nav-container {
  display: flex;
  align-items: center;
  position: relative; /* Position relative for absolute positioning of children */
  gap: 120px;
}

.nav-links {
  display: flex;
  gap: 20px;
}
/* Styles for dropdown-content when it is visible */
.dropdown-content {
  display: block;
  position: absolute;
  left: 0;
  right: 0;
  top: 100%; /* Position right below the nav-container */
  background-color: #f9f9f9;
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
