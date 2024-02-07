import { defineStore } from 'pinia';
import axios from 'axios';
import { useAuthStore } from '@/stores/auth'; // Adjust the path to your auth store

export const useSettingResumeStore = defineStore('settingResume', {
  state: () => ({
    userInfo: {},
    informations: [],
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
    },
    async createInfo (info) {
      const authStore = useAuthStore();
      await authStore.updateUserInfoFromToken();
      info.userId = authStore.userInfo.sub
      console.log('함수 내에 들어가는 education 테스트', info)
      const config = {
          headers: {
            'Authorization': `${authStore.accessToken}`,
          },
        };
      try {
        const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/info/register`, info, config);
        console.log('이력서 정보 추가', res.data);
      } catch (err) {
        console.error(err);
        console.log(info.value)
      }
    },

    async getInfo() {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/info/${authStore.userInfo.sub}`, config)
        this.informations = res.data
      } catch(err) {
        console.error(err)
      }
    }
  },
});
