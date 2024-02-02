<template>
  <div class="title">
    QnA 게시판
  </div>
  <QnAList/>
</template>

<script setup>
import { onMounted } from 'vue';
import QnAList from './components/QnAList.vue';
import { useCommunityStore } from '@/stores/community';


onMounted (() => {
  
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