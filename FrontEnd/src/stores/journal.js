import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from './auth';

export const useJournalStore = defineStore('journal', {
  state: () => ({
    journals: [],
    API_URL: import.meta.env.VITE_API_BASE_URL
  }),
  actions: {
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(';').shift();
      return null;
    },

    async getJournals(router) {
      try {
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/diary`, {
          headers: {
            Authorization: `${this.getCookie('accessToken')}`,
          },
        });
        this.journals = response.data;
      } catch(err) {
        if (err.response && err.response.status === 500) {
          router.push('/login2');
        } else {
          console.log('token', token);
        }
        this.journals = [];
      }
    },
  }

})