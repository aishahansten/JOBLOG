<template>
  <div class="carousel-container">
    <div ref="chatterList" class="chatter-list">
      <ChatterListItem 
        v-for="(item, index) in chatterItems" 
        :key="index"
        :item="item"/>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import ChatterListItem from './items/ChatterListItem.vue';

const chatterList = ref(null);
const chatterItems = [
  { user_id: '홍길동', job: '개발자', career: '5년' },
  { user_id: '김철수', job: '디자이너', career: '3년' },
  { user_id: '이영희', job: '마케터', career: '2년' },
  { user_id: '홍길동', job: '개발자', career: '5년' },
  { user_id: '김철수', job: '디자이너', career: '3년' },
  { user_id: '이영희', job: '마케터', career: '2년' },
  { user_id: '홍길동', job: '개발자', career: '5년' },
  { user_id: '김철수', job: '디자이너', career: '3년' },
  { user_id: '이영희', job: '마케터', career: '2년' },
  { user_id: '홍길동', job: '개발자', career: '5년' },
  { user_id: '김철수', job: '디자이너', career: '3년' },
  { user_id: '이영희', job: '마케터', career: '2년' },
  // Add more items as needed
];

</script>

<style scoped>
  .carousel-container {
    display: flex;
    align-items: center;
  }
  .chatter-list {
    border: 1px solid black;
    margin: 10px;
    width: 1344px;
    padding: 0 20px;
    box-sizing: border-box;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 10px; /* Optional: gap between items */
    justify-content: center;
    align-items: center;
  }

</style>
