<script setup>
import "@/assets/css/home/login.css";

const goToHome = () => {
  // Vue Router를 사용하는 경우: this.$router.push('/');
  window.location.href = "/";
};
</script>

<template>
  <div class="login-container">
    <div class="login-pharse">
      <h1>경력자라면, 인증해주세요!</h1>
      <p>재직했던 회사 이메일로 인증해주세요.</p>
    </div>
    <div class="select-form">
      <h2>회사 이메일로 인증해주세요.</h2>
      <div class="auth">
        <div class="lines" id="login-auth">
          <input
            type="email"
            placeholder="회사 이메일을 입력하세요"
            id="email-input"
          />
          <a class="edit-btn clickable-btn">인증메일 전송</a>
        </div>
        <div class="lines" id="login-auth">
          <input type="text" placeholder="인증번호를 입력하세요" id="long" />
        </div>
      </div>
      <a @click="goToHome" class="update-btn clickable" id="login-auth-btn">
        인증 완료
      </a>
      <a @click="goToHome" class="clickable-txt">나중에 할게요</a>
    </div>
  </div>
</template>

<style scoped></style>
