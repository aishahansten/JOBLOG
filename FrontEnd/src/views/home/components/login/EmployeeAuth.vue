<script setup>
const goToHome = () => {
  // Vue Router를 사용하는 경우: this.$router.push('/');
  window.location.href = "/";
};
</script>

<template>
  <div class="container">
    <div class="title">
      <h1>경력자라면, 인증해주세요!</h1>
      <p class="f-weight-l">재직했던 회사 이메일로 인증해주세요.</p>
    </div>
    <div class="form">
      <h2>회사 이메일로 인증해주세요.</h2>
      <div id="auth">
        <div id="email">
          <input
            type="email"
            placeholder="회사 이메일을 입력하세요"
            class="input focus-lg f-size-20"
          />
          <a class="btn lined-c f-color-c f-size-20 h-solid-c a-bright"
            >인증메일 전송</a
          >
        </div>
        <input
          type="text"
          class="input focus-lg f-size-20"
          placeholder="인증번호를 입력하세요"
        />
      </div>
      <a
        @click="goToHome"
        class="btn solid-c h-bright a-dark f-size-20"
        id="auth-proceed"
      >
        인증 완료
      </a>
      <a @click="goToHome" class="h-txt f-color-g">나중에 할게요</a>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 50px;
  min-height: 800px;

  background-image: url("@/assets/img/login/background-img.png");
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
  text-align: center;
}
.title {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.form {
  display: flex;
  width: 700px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
  padding: 60px;

  background-color: white;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

/* 현직자 인증 페이지 */
#auth {
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: 100%;
}

#email {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
}
#email input {
  width: 100%;
}
#email a {
  width: 250px;
}

#auth-proceed {
  width: 100%;
}
</style>
