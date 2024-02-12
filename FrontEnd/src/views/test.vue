<template>
  <div>
    <button @click="deleteSelection()">테스트 버튼</button>
  </div>
</template>

<script setup>
import axios from "axios";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();

const deleteSelection = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(
        `${import.meta.env.VITE_API_BASE_URL}/selection/delete/1`,
        config
      );
      console.log(res.data);
      // router.push();

      // Optionally, emit an event to inform the parent component to update the comment list
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
    console.log("삭제하지 않음.");
  }
};
</script>

<style scoped>

</style>