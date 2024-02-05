import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from './auth';

export const useCommunityStore = defineStore('community', {
  state: () => ({
    posts: [],
  }),
  actions: {

    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(';').shift();
      return null;
    },

    async getPosts() {
      const authStore = useAuthStore();
      const token = authStore.accessToken

      try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/community`, {
          headers: {
            Authorization: `${this.getCookie('accessToken')}`, // Use the token directly
          },
        });
        this.posts = response.data;
        console.log(response.data)
      } catch (err) {
        console.error('Error fetching posts:', err);
        console.log('token', token)
        this.posts = [];
      }
    },
  }
});
