<script setup>
import { ref, onMounted } from "vue";
import AuthProceed from "./AuthProceed.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/employee-auth.css";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const userInfo = ref({});
const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}

onMounted(async () => {
  await authStore.updateUserInfoFromToken();
  userInfo.value = authStore.userInfo;
});
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
        <p><b>회원</b>님의 현재 현직자 인증 상태는<br /></p>
        <p>
          <b v-if="userInfo.role == 'ROLE_APPLICANT'">{미완료}</b>
          <b v-else>완료</b>
          입니다.
        </p>
      </div>
      <div class="content-function" v-if="userInfo.role == 'ROLE_APPLICANT'">
        <a @click="toggleEdit" class="edit-btn clickable-btn" id="go-auth-btn">인증하러 가기</a>
      </div>
    </div>
  </div>

  <AuthProceed v-else />
</template>

<style scoped>
.auth-content {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  padding: 100px 0;
  gap: 50px;
}
.status {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  font-size: 24px;
}

#edit-content {
  width: 100%;
  height: 820px;
}

.auth-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.auth {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.lines {
  display: flex;
  width: 700px;
  justify-content: space-between;
  align-items: center;
}
.lines div {
  display: flex;
  flex-direction: row;
  gap: 10px;
}

input {
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

#email-input {
  width: 320px;
}

#go-auth-btn {
  padding: 10px 50px;
  font-size: 20px;
}
</style>
