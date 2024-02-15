import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";

export const useCoffeeStore = defineStore("coffee", {
  state: () => ({
    chats: [],
  }),
  actions: {
    async getChat() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/chat/${authStore.userInfo.sub}`, config);
        this.chats = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async createChat(applyChat) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        await axios.post(`${import.meta.env.VITE_API_BASE_URL}/chat/apply`, applyChat, config);
      } catch (err) {
        console.error(err);
      }
    },

    async declineChat(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/chat/delete/${id}`, config);
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/chat/${authStore.userInfo.sub}`, config);
        this.alarms = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async acceptChat(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/chat/accept/${id}`, config);
        // const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/chat/${authStore.userInfo.sub}`, config);
        // this.alarms = res.data;
      } catch (err) {
        console.error(err);
      }
    },
  },
});
