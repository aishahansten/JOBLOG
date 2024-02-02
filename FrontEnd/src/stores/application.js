import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useApplicationStore = defineStore('application', () => {
  const API_URL = ref('')
  
  
  // 이력서 관련
  const resumeList = ref([])
  const getResumeList = function () {
    axios({
      method: 'get',
      url: `${API_URL}/resume/${user_Id}`,
      headers: {
        Authorization: `Token ${token}`
      }
    })
    .then(res => {
      if (Array.isArray(res.data) && res.data.length > 0) {
        console.log('이력서들', resumeList)
        resumeList.value = res.data;
      } else {
        console.log('이력서가 없습니다.', resumeList)
        resumeList.value = []
      }
    })
    .catch((err) => {
        console.error(err)
        console.log(token)
    })
  }

  // const getResume = function () {
  //   axios({
  //     method: 'get',
  //     url: `${API_URL}/resume/${user_Id}/${resumeId}`,
  //     headers: {
  //       Authorization: `Token ${token}`
  //     }
  //   })
  //   .then((res) => {
  //     resumeDetail.value = res.data
  //     console.log('데이터들어옴', res.data)
  //   })
  //   .catch((err) => {
  //     console.error(err)
  //   })
  // }
  
  return {
    getResumeList,
    // getResume,
    resumeList,

  }
}, {persist: true})