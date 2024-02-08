import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from '@/stores/auth'; // Adjust the path to your auth store

export const useEssayResumeStore = defineStore('essayResume', {
  state: () => ({
    currentResume: [],
    resume: [],
    essay: [],
  }),
  actions: {
    async getResume() {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/resume/${authStore.userInfo.sub}`, config)
        this.resume = res.data
      } catch(err) {
        console.error(err)
      }
    },

    async getResumeDetail(id) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/resume/detail/${id}`, config)
        this.currentResume = res.data
      } catch(err) {
        console.error(err)
      }
    },


    async getEssay() {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/essay/${authStore.userInfo.sub}`, config)
        this.essay = res.data
      } catch(err) {
        console.error(err)
      }
    },
  },
});
