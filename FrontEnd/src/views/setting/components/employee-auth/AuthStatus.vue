<script setup>
import { ref } from "vue";
import AuthProceed from "./AuthProceed.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/employee-auth.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="content-container" v-if="!isEdit">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">현직자 인증</p>
        <p>회사 이메일 인증을 통해 현직자 전용 서비스를 사용할 수 있습니다.</p>
      </div>
    </div>

    <div class="auth-content">
      <div class="status">
        <p><b>{000}</b>님의 현재 현직자 인증 상태는<br /></p>
        <p><b>{미완료}</b>입니다.</p>
      </div>
      <div class="content-function">
        <a @click="toggleEdit" class="edit-btn clickable-btn" id="go-auth-btn"
          >인증하러 가기</a
        >
      </div>
    </div>
  </div>

  <AuthProceed v-else />
</template>

<style scoped></style>
