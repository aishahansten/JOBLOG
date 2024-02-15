import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth"; // Adjust the path to your auth store

export const useAlarmStore = defineStore("alarm", {
  state: () => ({
    alarms: [],
    currAlarm: {},
  }),
  actions: {
    async getAlarm() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/alarm/${authStore.userInfo.sub}`, config);
        this.alarms = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async getAlarmDetail(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/alarm/detail/${id}`, config);
        this.currAlarm = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async deleteAlarm(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/alarm/${id}`, config);
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/alarm/${authStore.userInfo.sub}`, config);
        this.alarms = res.data;
      } catch (err) {
        console.error(err);
      }
    },
  },
});
