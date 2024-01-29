<template>
    <div class="QnAItem-container">
        <div class="container-left">
          <div class="title">
            <RouterLink :to="{ name:'QnADetail' }"> {{ dummyData.title }} </RouterLink>
          </div>
          <div>{{ dummyData.content }}</div>
          <div class="icon-info">
            <img src="@/assets/img/community/eye.svg" class="icons">
            {{ dummyData.hit }}

            <img src="@/assets/img/community/like.svg" class="icons">
            {{ dummyData.totalLikes }}
          </div>
        </div>
        <div class="container-right">
          <div>{{ dummyData.createdAt }}</div>
          <div>답변 개수: {{ dummyData.totalComments }}</div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const dummyData = ref ({
  postID: 1,
  userId: 1,
  title: '예제',
  content: '예제 내용',
  hit: 200,
  totalLikes: 6,
  totalComments: 2,
  createdAt: '2024-01-29'
})


</script>

<style scoped>

  * {
    font-size: 20px;
  }
  .QnAItem-container {
    display: grid;
    grid-template-columns: 1fr 0.2fr;
    width: 1344px;
    padding: 20px;
    box-sizing: border-box;
    height: 170px;
  }

  .container-left {
    display: grid;
    grid-template-rows: 1fr 1fr 1fr;
  }

  .icon-info {
    display: flex;
    gap: 5px;
    align-items: center;

  }

  .icons {
    width: 20px;
    height: 20px;
  }

  .title { 
    font-size: 24px;
    font-weight: bold;
  }
  .container-right {
    margin-left: auto;
    display: grid;
    grid-template-rows: 1fr 1fr;
    padding: 20px;
    box-sizing: border-box;
  }
</style>