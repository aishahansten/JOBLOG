<template>
  <div v-if="!loading">
  </div>
  <div v-else>
    <div class="qnaDetailContainer">
      <div class="question">
        <div class="header">
          <div>
            <button @click="savePost">수정창</button>
          </div>
          <div class="title" v-if="post">
             <input type="text" v-model="post.title" required>
          </div>
        </div>
        <div class="content">
            <textarea v-model="post.content" required></textarea>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute } from 'vue-router';
  import axios from 'axios';
  import { useAuthStore } from '@/stores/auth';
  import { useRouter } from 'vue-router';

  const authStore = useAuthStore()
  const route = useRoute()
  const router = useRouter()
  
  const loading = ref(false)    
  const post = ref(null)
  console.log(post)
  
  async function fetchPostAndComments() {
    try {
        
      await authStore.updateUserInfoFromToken()
      const response = await axios.get(`${authStore.API_URL}/community/detail/${route.params.id}`)
  
      return {
        post: response.data.postResponseDto,
      };
      
    } catch (error) {
      console.error('불러오기 실패: ', error);
      return { post: null, comments: null }; // Return nulls or defaults on error
    }
  }
  
  const savePost = async () => {
  try {
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    };
    await authStore.updateUserInfoFromToken();
    const editContent = {
      postId: post.value.postId,
      userId: authStore.userInfo.sub,
      title: post.value.title,
      content: post.value.content,
      category: 'QNA',
    };
    console.log(post.value.postId)
    const res = await axios.patch(`${authStore.API_URL}/community/update`, editContent, config)
    console.log(res.data)
    router.push({ name: 'QnADetail', params: { id: post.value.postId } })
  } catch (error) {
    console.error('업데이트 실패: ', error);
  }
};

  onMounted(async () => {
    const { post: fetchedPost } = await fetchPostAndComments();
    post.value = fetchedPost;
    loading.value = true;
    authStore.updateUserInfoFromToken()
  });
  
  
</script>
  
  <style scoped>
  
    * {
      font-size: 24px;
    }
    .qnaDetailContainer {
      display: flex;
      flex-direction: column;
      width: 1344px;
      border: 1px black solid;
      justify-content: center;
      gap: 20px;
    }
  
    .question {
      min-width: 1200px;
      border: 1px black solid;
      border-radius: 8px;
      min-height: 600px;
      padding: 30px;
      box-sizing: border-box;
    }
  
    .question-icon {
      height: 100px;
      width: 100px;
    }
  
    .header { 
      display: grid;
      grid-template-columns: 150px auto 100px;
    }
  
    .content {
      padding: 30px;
      box-sizing: border-box;
    }
    .title {
      font-size: 40px;
      font-weight: bold;
      width: 987px;
    }
  
  
  </style>