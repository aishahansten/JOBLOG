import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useCommunityStore = defineStore('community', () => {
  const API_URL = import.meta.env.VITE_API_BASE_URL
  const posts = ref([])

  // 목록 가져오기
  const getPosts = function() {
    axios({
      method: 'get',
      url: `${API_URL}/community/posts/`
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
    getPosts
  }
}, {persist: true})