<script setup>
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/withdraw-setting.css";
import { useAuthStore } from "@/stores/auth";
const authStore = useAuthStore();
const router = useRouter();

onMounted(async () => {
  await authStore.deleteUser();
  await authStore.logout();
  setTimeout(() => {
    router.push({ name: "Home" });
  }, 4000);
});
</script>

<template>
  <div class="content-container" id="withdraw-complete-view">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">회원 탈퇴 완료</p>
        <p>회원 정보가 정상적으로 삭제되었습니다.</p>
      </div>
    </div>

    <div class="withdraw-confirm-content">
      <h2>탈퇴 완료</h2>
      <p>3초 후 메인 페이지로 이동합니다.</p>
      <RouterLink :to="{ name: 'Home' }">
        <a class="basic-btn clickable-txt">직접 이동</a>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.withdraw-content {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  padding: 70px 50px;
  gap: 100px;
}

.withdraw-content section {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.section-title {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.dont-btn {
  width: fit-content;
  padding: 10px 30px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.withdraw-confirm-content {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 70px 50px;
  gap: 30px;
}

.precautions {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 50px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.warning {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

#agreement {
  width: 545px;
  font-size: 16px;
}

.withdraw-confirm-content button {
  background-color: var(--border-gray);
  font-size: 20px;
  color: white;
}
.btn-inactive {
  background-color: var(--border-gray);
}
.btn-active:hover {
  background-color: var(--gray);
  font-size: 20px;
  color: white;
}

#withdraw-complete-view {
  border-left: 1px solid var(--border-gray);
  border-right: 1px solid var(--border-gray);
  height: 1000px;
}
</style>
