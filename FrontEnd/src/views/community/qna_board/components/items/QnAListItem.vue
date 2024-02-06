<template>
    <div class="QnAItem-container">
        <div class="container-left">
          <div class="title">
            <RouterLink :to="{ name:'QnADetail', params: { id: post.postId } }"> {{ post.title }} </RouterLink>
          </div>
          <div>{{ post.content }}</div>
          <div class="icon-info">
            <img src="@/assets/img/community/eye.svg" class="icons">
            {{ post.hit }}

            <img src="@/assets/img/community/like.svg" class="icons">
            {{ post.totalLike }}
          </div>
        </div>
        <div class="container-right">
          <div>{{ post.createdDate }}</div>
          <div>답변 개수: {{ post.totalComment }}</div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, computed } from 'vue';

const props = defineProps({
  post: Object
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