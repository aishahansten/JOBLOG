<template>
  <div class="title">
    QnA 게시판
  </div>
  <QnAList :posts="communityStore.posts"/>
</template>

<script setup>
import { onMounted } from 'vue';
import QnAList from './components/QnAList.vue';
import { useCommunityStore } from '@/stores/community';

const communityStore = useCommunityStore();

onMounted(async () => {
  // Use an immediately-invoked async function
  await communityStore.getPosts();
  
  // Now, this log will reflect the updated state
  console.log('커뮤니티 리스트에서 찾는 로그', communityStore.posts);
});
</script>


<style scoped>
  .title {
    display: flex;
    justify-content: center;
    font-size: 40px;
    font-weight: bold;
  }
</style>