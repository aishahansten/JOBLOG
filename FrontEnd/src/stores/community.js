import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useCommunityStore = defineStore('community', () => {
  const API_URL = import.meta.env.VITE_API_BASE_URL
  const posts = ref([])

  const getCookie = function(name) {
    const value = `; ${document.cookie}`
    const parts = value.split(`; ${name}=`)
    if (parts.length === 2) return parts.pop().split(';').shift()
  } 
  
  const token = getCookie('accessToken')
  // 목록 가져오기
  const getPosts = function() {
    axios({
      method: 'get',
      url: `${API_URL}/community`,
      headers: {
        Authorization: `${token}`
      }
    })
    .then(res => {
      if (Array.isArray(res.data) && res.data.length > 0) {
        console.log('남은 게시글.', posts)
        posts.value = res.data;
      } else {
        console.log('게시글이 없습니다.', posts)
        posts.value = []
      }
    })
    .catch((err) => {
      console.error(err)
    })
  }
  
  return {
    API_URL,
    getPosts,
    posts
  }
}, {persist: true})
