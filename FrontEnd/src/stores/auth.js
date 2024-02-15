import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    userInfo: null,
    accessToken: null,
    API_URL: import.meta.env.VITE_API_BASE_URL,
    chatterList: [],
    chatter: {},
  }),
  actions: {
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },
    decodeJWT(token) {
      if (!token) return null;
      try {
        const base64Url = token.split(".")[1];
        const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        const jsonPayload = decodeURIComponent(
          atob(base64)
            .split("")
            .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
            .join("")
        );
        return JSON.parse(jsonPayload);
      } catch (e) {
        console.error("Error decoding token", e);
        return null;
      }
    },
    updateUserInfoFromToken() {
      const token = this.getCookie("accessToken");
      if (token) {
        this.accessToken = token;
        const decoded = this.decodeJWT(token);
        if (decoded) {
          this.userInfo = { sub: decoded.sub, role: decoded.role };
        }
      } else {
        this.userInfo = null;
        this.accessToken = null;
      }
    },
    isLoggedIn() {
      return !!this.userInfo;
    },

    logout() {
      this.userInfo = null;
      this.accessToken = null;
      document.cookie = "accessToken=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    },

    async deleteUser() {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        const res = await axios.delete(
          `${import.meta.env.VITE_API_BASE_URL}/users/delete/${this.userInfo.sub}`,
          config
        );
      } catch {}
    },

    async deleteUserPerm() {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        const res = await axios.delete(
          `${import.meta.env.VITE_API_BASE_URL}/users/permanent/delete/${this.userInfo.sub}`,
          config
        );
        console.log("영삭.js", res.data);
      } catch (err) {
        console.error(err);
      }
    },

    async changeUserRole() {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      const chatterProfile = {
        userId: this.userInfo.sub,
        job: "",
        career: "",
        description: "",
      };
      try {
        const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/users/role/${this.userInfo.sub}`, config);
        const chatter = await axios.post(
          `${import.meta.env.VITE_API_BASE_URL}/chat/profile/register`,
          chatterProfile,
          config
        );
        console.log(chatter.data);
        alert("다시 로그인 해주세요");
        await this.logout2();
      } catch (err) {
        console.error(err);
      } finally {
        router.push("/login");
      }
    },

    async getChatterList() {
      await this.updateUserInfoFromToken();
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/chat`);
        console.log(res.data, "js");
        this.chatterList = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async getChatter(id) {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        const res = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/chat/profile/${id}`, config);
        this.chatter = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    async updateChatterProfile(currChat) {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/chat/profile/update`, currChat.value, config);
      } catch (err) {
        console.error(err);
      }
    },
    async chatterStatus() {
      await this.updateUserInfoFromToken();
      const config = {
        headers: {
          Authorization: `${this.accessToken}`,
        },
      };
      try {
        await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/chat/profile/delete/${this.userInfo.sub}`, config);
        console.log("변경됨");
      } catch (err) {
        console.error(err);
      }
    },
  },
});
