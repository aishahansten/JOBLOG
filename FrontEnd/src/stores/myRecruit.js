import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "./auth";

export const useMyRecruitStore = defineStore("myRecruit", {
  state: () => ({
    newMyRecruit: {},
  }),
  actions: {
    async createMyRecruit(newMyRecruit) {
      const authStore = useAuthStore();
      authStore.updateUserInfoFromToken();
      newMyRecruit.value.userId = authStore.userInfo.sub;
      const config = {
        headers: { Authoriation: `${authStore.accessToken}` },
      };
      try {
        const response = await axios.post(
          `${import.meta.env.VITE_API_BASE_URL}/myRecruit/register`,
          newMyRecruit.value,
          config
        );
        console.log("만들기", response.data);
      } catch (error) {
        console.error(error);
      }
    },
    async deleteMyRecruit(id) {
      const isConfirmed = confirm("삭제하시겠습니까?");
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      if (isConfirmed) {
        try {
          const config = {
            headers: {
              Authorization: `${authStore.accessToken}`,
            },
          };
          const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/myRecruit/delete/${id}`, config);
          console.log(res.data);
        } catch (error) {
          console.error("삭제 실패: ", error);
        }
      } else {
        console.log("삭제하지 않음.");
      }
    },
  },
});
