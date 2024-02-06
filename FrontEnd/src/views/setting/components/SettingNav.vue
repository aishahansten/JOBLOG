<script setup>
import "@/assets/css/setting/setting-nav.css";
</script>

<template>
  <aside class="setting-menu">
    <div class="menu-profile">
      <p>프로필 정보</p>
      <div>
        <RouterLink :to="{ name: 'AccountSetting' }"
          ><a id="account-setting" class="clickable-txt"
            >계정 정보 설정</a
          ></RouterLink
        >
        <RouterLink :to="{ name: 'ResumeSetting' }">
          <a id="resume-setting" href="" class="clickable-txt"
            >이력 정보 설정</a
          ></RouterLink
        >
      </div>
    </div>
    <div class="auth-menu">
      <p>계정 인증</p>
      <div>
        <RouterLink :to="{ name: 'AuthStatus' }"
          ><a id="auth-status" class="clickable-txt">현직자 인증</a></RouterLink
        >
      </div>
    </div>
    <div class="menu-other">
      <p>기타 설정</p>
      <div>
        <RouterLink :to="{ name: 'NotificationSetting' }"
          ><a id="notification-setting" class="clickable-txt"
            >알림 설정</a
          ></RouterLink
        >
        <RouterLink :to="{ name: 'WithdrawSetting' }"
          ><a id="withdraw-setting" class="clickable-txt"
            >회원 기능</a
          ></RouterLink
        >
      </div>
    </div>
    <div class="menu-logout">
      <a id="logout" href="" class="clickable-txt">로그아웃</a>
    </div>
  </aside>
</template>