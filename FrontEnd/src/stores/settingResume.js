import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from '@/stores/auth'; // Adjust the path to your auth store

export const useSettingResumeStore = defineStore('settingResume', {
  state: () => ({
    userInfo: {},
  }),
  actions: {
    nullToEmptyString(obj) {
      Object.keys(obj).forEach(key => {
        if (obj[key] === null) {
          obj[key] = '';
        }
      });
      return obj;
    },
    async getUserInfo() {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/users/${authStore.userInfo.sub}`);
        this.userInfo = this.nullToEmptyString(res.data); // Apply transformation here
        console.log('유저 정보', res.data);
        console.log('스토어에서',this.userInfo)

      } catch (err) {
        console.error(err);
      }
    }
  },
});
