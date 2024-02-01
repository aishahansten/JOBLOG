<script setup>
import { ref } from "vue";
import AuthStatus from "./AuthStatus.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/employee-auth.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div id="edit-content" v-if="!isEdit">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">현직자 인증</p>
        <p>회사 이메일 인증을 통해 현직자 전용 서비스를 사용할 수 있습니다.</p>
      </div>
    </div>

    <div class="auth-content">
      <h2>회사 이메일 인증</h2>
      <div class="auth">
        <div class="lines">
          <span>회사 이메일 주소</span>
          <div>
            <input type="text" placeholder="회사 이메일을 입력하세요" id="email-input"/>
            <a class="edit-btn clickable-btn">인증메일 전송</a>
          </div>
        </div>
        <div class="lines">
          <span>인증번호</span>
          <div>
            <input type="text" placeholder="인증번호를 입력하세요" id="long" />
          </div>
        </div>
      </div>
      <a @click="toggleEdit" class="update-btn clickable" id="go-auth-btn">
        인증 완료
      </a>
    </div>
  </div>

  <AuthStatus v-else />
</template>

<style scoped></style>
