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
  { name: '홍길동', jobTitle: '개발자', experience: '5년' },
  { name: '김철수', jobTitle: '디자이너', experience: '3년' },
  { name: '이영희', jobTitle: '마케터', experience: '2년' },
  { name: '홍길동', jobTitle: '개발자', experience: '5년' },
  { name: '김철수', jobTitle: '디자이너', experience: '3년' },
  { name: '이영희', jobTitle: '마케터', experience: '2년' },
  { name: '홍길동', jobTitle: '개발자', experience: '5년' },
  { name: '김철수', jobTitle: '디자이너', experience: '3년' },
  { name: '이영희', jobTitle: '마케터', experience: '2년' },
  { name: '홍길동', jobTitle: '개발자', experience: '5년' },
  { name: '김철수', jobTitle: '디자이너', experience: '3년' },
  { name: '이영희', jobTitle: '마케터', experience: '2년' },
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
