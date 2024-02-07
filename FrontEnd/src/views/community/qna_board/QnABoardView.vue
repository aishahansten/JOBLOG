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
import { useRouter } from 'vue-router';
const communityStore = useCommunityStore();
const router = useRouter()
onMounted(async () => {
  // Use an immediately-invoked async function
  await communityStore.getPosts(router);
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