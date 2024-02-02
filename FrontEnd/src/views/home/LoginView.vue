<script setup></script>

<template>
  <div class="container">
    <div class="login-pharse">
      <h1>가입과 로그인 모두<br />SNS 계정으로</h1>
      <p>간편하게 시작하세요.</p>
    </div>
    <div class="login-btn-space">
      <div id="google-login-btn" class="login-btn">
        <img
          src="@/assets/img/login/icon/logo-google.png"
          class="login-icon"
          alt=""
        />
        <span>구글 계정으로 시작하기</span>
      </div>
      <div id="kakao-login-btn" class="login-btn">
        <img
          src="@/assets/img/login/icon/logo-kakao.svg"
          class="login-icon"
          alt=""
        />
        <span>카카오 계정으로 시작하기</span>
      </div>
      <div id="naver-login-btn" class="login-btn">
        <img
          src="@/assets/img/login/icon/logo-naver.svg"
          class="login-icon"
          alt=""
        />
        <span>네이버 계정으로 시작하기</span>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
