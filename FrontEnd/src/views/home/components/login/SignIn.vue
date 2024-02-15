<script setup>
import { onMounted } from "vue";
import { useRouter } from "vue-router";

onMounted(() => {
  const params = new URLSearchParams(window.location.search);
  const accessToken = params.get("accessToken");
  const router = useRouter();

  if (accessToken) {
    document.cookie = `accessToken=${accessToken}; path=/; Secure;`;

    history.pushState(null, "", window.location.pathname);
  }

  router.replace("/");
});
</script>

<template>
  <div></div>
</template>

<style scoped></style>
