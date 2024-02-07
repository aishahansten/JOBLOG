<template>
  <div v-if="!loading">
  </div>
  <div v-else>
    <div class="qnaDetailContainer">
      <div class="question">
        <div class="header">
          <div>
            <img src="@/assets/img/community/Q.svg" class="question-icon">
          </div>
          <div class="title" v-if="post">
            {{ post.title }}
          </div>
          <div class="buttons" v-if="post.userId == authStore.userInfo.sub">
            <RouterLink :to="{ name: 'QnAUpdate', params: { id: post.postId } }">
              <button>수정하기</button>
            </RouterLink>
          </div>
        </div>
        <div class="content">
          {{ post.postId }}
          {{ post.content }}
        </div>
      </div>
      <button @click="toggleModal">버튼 키기</button>
      <div v-if="showModal">
        <QnACommentCreate 
          :postId="post.postId"
          @refresh="handleRefresh"
        />
      </div>

      <ReplyList 
        :comments="comments"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router';
import axios from 'axios';
import ReplyList from './components/ReplyList.vue';
import { useAuthStore } from '@/stores/auth';
import QnACommentCreate from '@/views/community/qna_board/components/QnACommentCreate.vue'

const authStore = useAuthStore()
const route = useRoute()

const loading = ref(false)
const post = ref(null)
const comments = ref([])


async function fetchPostAndComments() {
  try {
    await authStore.updateUserInfoFromToken()
    const response = await axios.get(`${authStore.API_URL}/community/detail/${route.params.id}`)
    return {
      post: response.data.postResponseDto,
      comments: response.data.comments,
    };
    
  } catch (error) {
    console.error('불러오기 실패: ', error);
    return { post: null, comments: null }; // Return nulls or defaults on error
  }
}

const handleRefresh = async () => {
  loading.value = false
  const { post: fetchedPost, comments: fetchedComments } = await fetchPostAndComments()
  post.value = fetchedPost
  comments.value = fetchedComments
  showModal.value = false
  loading.value = true
}

onMounted(async () => {
  const { post: fetchedPost, comments: fetchedComments } = await fetchPostAndComments();
  post.value = fetchedPost;
  comments.value = fetchedComments;
  loading.value = true;
  authStore.updateUserInfoFromToken()
});

//-----------
const showModal = ref(false)
const toggleModal = function() {
  showModal.value = !showModal.value
}

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