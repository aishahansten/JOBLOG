<template>
  <div>
    <div>
        답변하기
        <textarea 
          name="comment" 
          id="comment"
          cols="30"
          rows="10"
          v-model="comment.content"
        >
      </textarea>
      <button @click="submitAnswer">작성하기</button>
    </div>    
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios';
import { useCommunityStore } from '@/stores/community';
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore()
const communityStore = useCommunityStore()

const props = defineProps({
    postId: Number
})

const emit = defineEmits(['refresh'])

const comment = ref({
  postId: props.postId,
  userId: '',
  content: ''
})

const submitAnswer = async () => {
  try {
    authStore.updateUserInfoFromToken()
    comment.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    }
    const response = await axios.post(`${communityStore.API_URL}/comment/register`, comment.value, config)
    emit('refresh') 
    comment.value.content = ''
  } catch (error) {
    console.error(error);
  }
};


</script>

<style scoped>

</style>