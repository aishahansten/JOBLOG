import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "./auth";

export const useCommunityStore = defineStore("community", {
  state: () => ({
    posts: [],
    post: [],
    API_URL: import.meta.env.VITE_API_BASE_URL,
  }),
  actions: {
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },

    async getPosts(router) {
      try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/community`, {});

        this.posts = response.data;
      } catch (err) {
        if (err.response && err.response.status === 500) {
          router.push("/login");
        } else {
        }
        this.posts = [];
      }
    },

    async getPost(id) {
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/community/detail/${id}`);
        this.post = res.data;
      } catch (err) {
        console.error(err);
      }
    },
  },
});
