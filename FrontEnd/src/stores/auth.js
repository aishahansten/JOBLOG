import { defineStore } from 'pinia';
import router from '@/router';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    userInfo: null,
    accessToken: null,
    API_URL: import.meta.env.VITE_API_BASE_URL
  }),
  actions: {
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(';').shift();
      return null;
    },
    decodeJWT(token) {
      if (!token) return null;
      try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(c => 
          '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join(''));
        return JSON.parse(jsonPayload);
      } catch (e) {
        console.error("Error decoding token", e)
        return null;
      }
    },
    updateUserInfoFromToken() {
      const token = this.getCookie('accessToken')
      if (token) {
        this.accessToken = token
        const decoded = this.decodeJWT(token)
        if (decoded) {
          this.userInfo = { sub: decoded.sub, role: decoded.role }
        }
      } else {
        this.userInfo = null
        this.accessToken = null
      }
    },
    isLoggedIn() {
      return !!this.userInfo
    },

    logout() {
        this.userInfo = null
        this.accessToken = null
        document.cookie = 'accessToken=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;'
        router.push('/')
      },
  },
});
