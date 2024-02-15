import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "./auth";

export const useJournalStore = defineStore("journal", {
  state: () => ({
    journals: [],
    API_URL: import.meta.env.VITE_API_BASE_URL,
  }),
  actions: {
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },

    async getJournals(router) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/diary/${authStore.userInfo.sub}`, {
          headers: {
            Authorization: `${this.getCookie("accessToken")}`,
          },
        });
        this.journals = response.data;
        console.log(response.data);
      } catch (err) {
        if (err.response && err.response.status === 500) {
          router.push("/login");
        } else {
          console.log("token", token);
        }
        this.journals = [];
      }
    },
  },
});
