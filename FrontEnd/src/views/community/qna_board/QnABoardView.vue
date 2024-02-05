<template>
  <div class="title">
    QnA 게시판
  </div>
  <QnAList/>
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import QnAList from './components/QnAList.vue';
  import { useCommunityStore } from '@/stores/community';

  const communityStore = useCommunityStore()
  // const posts = ref([])
  // console.log(posts.value)

  onMounted (() => {
    communityStore.getPosts()
    // posts.value = communityStore.posts1
  })

</script>

<style scoped>
  .title {
    display: flex;
    justify-content: center;
    font-size: 40px;
    font-weight: bold;
  }
</style>