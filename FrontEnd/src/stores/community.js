import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useCommunityStore = defineStore('community', () => {
  const questions = ref([])
  

  return {

  }
}, {persist: true})