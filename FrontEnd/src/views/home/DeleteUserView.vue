<template>
  <div>
    탈퇴한 회원 보여줄 페이지
    <button @click="deletePerm()">영구 삭제하기</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

onMounted(() => {
  const params = new URLSearchParams(window.location.search);
  const accessToken = params.get("accessToken");

  if (accessToken) {
    document.cookie = `accessToken=${accessToken}; path=/; Secure;`;

    history.pushState(null, "", window.location.pathname);
  }
});

const router = useRouter();
const authStore = useAuthStore();

const deletePerm = async () => {
  await authStore.deleteUserPerm();
  alert("회원님의 정보가 영구 삭제 되었습니다");
  setTimeout(() => {
    router.push({ name: "Home" });
  }, 2000);
};
</script>

<style scoped></style>
