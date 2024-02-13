import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from '@/stores/auth'; // Adjust the path to your auth store
import { useAttrs } from 'vue';

export const useEssayResumeStore = defineStore('essayResume', {
  state: () => ({
    currentResume: [],
    resume: [],
    currentEssay: [],
    essayList: [],
    categoryList: [],
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
        this.essayList = res.data
      } catch(err) {
        console.error(err)
      }
    },

    async getEssayDetail(id) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/essay/detail/${id}`, config)
        this.currentEssay = res.data
      } catch(err) {
        console.error(err)
      }
    },

    async deleteEssay(id) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/essay/delete/${id}`, config)
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/essay/${authStore.userInfo.sub}`, config)
        this.essayList = response.data
      } catch(err) {
        console.error(err)
      }
    },

    async updateEssay(essay) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      // delete essay.categoryName
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/essay/update`, essay.value, config)
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/essay/${authStore.userInfo.sub}`, config)
        this.essayList = response.data
      } catch(err) {
        console.error(err)
      }
    },

    async getCategory() {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/category/${authStore.userInfo.sub}`, config)
        this.categoryList = res.data
      } catch(err) {
        console.error(err)
      }
    },

    async createCategory(category) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      category.value.userId = authStore.userInfo.sub
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/category/register`, category.value, config)
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/category/${authStore.userInfo.sub}`, config)
        this.categoryList = response.data
      } catch(err) {
        console.error(err)
      }
    },
    async deleteCategory(id) {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/category/delete/${id}`, config)
        this.categoryList = this.categoryList.filter(category => category.categoryId !== id);
      } catch(err) {
        console.error(err)
      }
    },
  },
});
