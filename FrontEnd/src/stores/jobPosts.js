import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth"; // Adjust the path to your auth store

export const useJobPostStore = defineStore("jobPost", {
  state: () => ({
    jobPosts: [],
    currentJob: {},
    companyReview: [],
    companyDetail: {},
    companyRecruit: [],
  }),
  actions: {
    async getJobPost(search) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/recruit/search`, {
          params: search,
        });
        this.jobPosts = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async getJobPostDetail(id) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/recruit/${id}`);
        this.currentJob.value = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async getCompanyReveiw(id) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/company/${id}/selectionReview`);
        this.companyReview.value = res.data;
      } catch (err) {
        console.error(err);
      }
    },

    async getCompanyDetail(id) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/company/${id}`);
        this.companyDetail.value = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async getCompanyRecruit(id) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/company/${id}/recruit`);
        this.companyRecruit.value = res.data;
      } catch (err) {
        console.error(err);
      }
    },
  },
});
