<script setup>
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/withdraw-setting.css";

onMounted(() => {
  setTimeout(() => {
    const router = useRouter();
    router.push("/");
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
        <a @click="toggleEdit" class="basic-btn clickable-txt">직접 이동</a>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped></style>
