import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from './auth';


export const useCommunityStore = defineStore('community', {
  state: () => ({
    posts: [],
    API_URL: import.meta.env.VITE_API_BASE_URL
  }),
  actions: {

    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(';').shift();
      return null;
    },

    async getPosts(router) {


      try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/community`, {
          headers: {
            Authorization: `${this.getCookie('accessToken')}`,
          },
        });
        this.posts = response.data;
      } catch (err) {
        if (err.response && err.response.status === 500) {
          router.push('/login2');
        } else {
          // Handle other errors or a case where the error does not have a response object
          console.log('token', token); // Logging the token for debugging purposes
        }
        this.posts = [];
      }
    },
  }
});
