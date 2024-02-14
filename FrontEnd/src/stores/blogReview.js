import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "./auth";
import { useRouter } from "vue-router";

export const useBlogReviewStore = defineStore("blogReview", {
  state: () => ({
    myJobs: [],
  }),
  actions: {
    async getMyJobs() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const router = useRouter();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`, config);
        this.myJobs = res.data;
      } catch (err) {
        if (err.response && err.response.status === 500) {
          router.push("/login2");
        }
        console.error(err);
      }
    },

    async createStep(recruitStep) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      // recruitStep.value.step = 1
      console.log("js", recruitStep.value);
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.post(
          `${import.meta.env.VITE_API_BASE_URL}/selection/register`,
          recruitStep.value,
          config
        );
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
          config
        );
        this.myJobs = response.data;
        console.log(res.data);
      } catch (err) {
        console.error(err);
      }
    },

    async deleteStep(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/selection/delete/${id}`, config);
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
          config
        );
        this.myJobs = response.data;
        console.log(res.data);
      } catch (err) {
        console.error(err);
      }
    },

    async createStepReview(stepReview) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.post(
          `${import.meta.env.VITE_API_BASE_URL}/selectionReview/register`,
          stepReview.value,
          config
        );
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
          config
        );
        this.myJobs = response.data;
        console.log(res.data);
      } catch (err) {
        console.error("createStepErr", err);
      }
    },
    async deleteSelection(id) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      try {
        const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/myRecruit/delete/${id}`, config);
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
          config
        );
        this.myJobs = response.data;
        console.log(res.data);
      } catch (err) {
        console.error(err);
      }
    },
  },
});
