<template>
  <div class="question-create-container">
    <div class="title">
      <input type="text" v-model="newQuestion.title" placeholder="제목을 입력하세요" required>
    </div>
    <div class="content">
      <textarea v-model="newQuestion.content" placeholder="본문 내용을 입력하세요" required></textarea>
    </div>
    <div class="submit">
      <button @click="createPost">작성하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios';
import { useCommunityStore } from '@/stores/community';
import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
const communityStore = useCommunityStore()

console.log(authStore.userInfo.sub)

const createPost = async () => {
  try {
    // Set the Authorization header for this specific request
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    };

    const response = await axios.post(`${communityStore.API_URL}/community/register`, newQuestion.value, config);
    console.log(response.data);
    // Resetting the newQuestion value after successful request
    newQuestion.value = {
      userId: authStore.userInfo.sub,
      category: 'QNA',
      title: '',
      content: ''
    };
  } catch (error) {
    console.error(error);
  }
}


const newQuestion = ref({
  userId: authStore.userInfo.sub,
  category: 'QNA',
  title: '',
  content: '',
})

</script>

<style scoped>
  * {
    font-size: 18px;
  }
  .question-create-container {
    display: flex;
    flex-direction: column;
    width: 1344px;
    min-height: 600px;
    border: 1px solid black;
    padding: 20px; /* Add padding for better spacing */
    box-sizing: border-box;
  }

  .title input {
    width: 100%; /* Full width of the container */
    height: 40px; /* Fixed height */
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-bottom: 20px; /* Spacing between title and content */
  }

  .content textarea {
    width: 100%; /* Full width of the container */
    height: 400px; /* Fixed height */
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: none; /* Prevents resizing */
  }

  .submit {
    margin-left: auto;
  }
</style>
