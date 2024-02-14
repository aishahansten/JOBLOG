import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth"; // Adjust the path to your auth store

export const useSettingResumeStore = defineStore("settingResume", {
  state: () => ({
    userInfo: {},
    informations: [],
  }),
  actions: {
    async getUserInfo() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/users/${authStore.userInfo.sub}`);
        this.userInfo = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async updateUserInfo(info) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/users/update`, info, config);
        console.log("업데이트 완료");
      } catch (err) {
        console.error(err);
      }
    },

    async createInfo(info) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      info.userId = authStore.userInfo.sub;
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/info/register`, info, config);
      } catch (err) {
        console.error(err);
      } finally {
        this.getInfo();
      }
    },

    async getInfo() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/info/${authStore.userInfo.sub}`, config);
        this.informations = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async deleteInfo(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/info/delete/${id}`, config);
      } catch (err) {
        console.error(err);
      }
    },
  },
});
