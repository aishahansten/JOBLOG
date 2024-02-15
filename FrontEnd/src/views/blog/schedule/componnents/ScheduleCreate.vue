<template>
  <div>
    <button @click="createSchedule()">테스트 버튼</button>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, computed } from "vue";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";

const authStore = useAuthStore();

const createSchedule = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newSchedule.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/schedule/register`,
      newSchedule.value,
      config
    );
    newSchedule.value = {
      userId: authStore.userInfo.sub,
      title: "test title",
      content: "test content",
      startDate: new Date("2024-02-08T00:00:00"),
      endDate: new Date("2024-02-08T00:00:00"),
    };
    // router.push();
  } catch (error) {
    console.error(error);
  }
};

const newSchedule = ref({
  userId: authStore.userInfo.sub,
  title: "test title",
  content: "test content",
  startDate: new Date("2024-02-08T00:00:00"),
  endDate: new Date("2024-02-08T00:00:00"),
});
</script>

<style scoped></style>
