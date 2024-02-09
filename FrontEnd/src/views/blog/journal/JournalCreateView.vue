<template>
    <div class="diary-create-container">
        <div class="diary-header"></div>
        <div class="diary-title">
            <div class="title-left">
                <h1>다이어리 쓰기</h1>
            </div>
            <div class="title-right">
                <button @click="createJournal">저장</button>
            </div>
        </div>
        <div class="diary-content">
            <textarea v-model="newJournal.content" name="내용 작성" placeholder="다이어리 내용을 입력하세요" class="content-textarea" required></textarea>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import axios from 'axios';
import {useJournalStore} from '@/stores/journal';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const router = useRouter()
const authStore = useAuthStore()
const journalStore = useJournalStore()

const createJournal = async () => {
  try {
    authStore.updateUserInfoFromToken()
    newJournal.value.userId = authStore.userInfo.sub;

    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    };

    const response = await axios.post(`${journalStore.API_URL}/diary/register`, newJournal.value, config);
    console.log(response.data);

    newJournal.value = {
      userId: authStore.userInfo.sub,
      content: ''
    };
    router.push('/blog-journal')
  } catch (error) {
    console.log(error);
  }
}

const newJournal = ref({
  userId: '',
  content: '',
})


</script>

<style scoped>
  .diary-create-container {
    display: grid;
    grid-template-rows: 1fr 1fr 3fr;
    width: 800px;
    
  }

  .diary-header {
    background-color: rgb(103, 103, 103);
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
  }

  .diary-title {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .content-textarea { 
    width: 800px;
  }
</style>