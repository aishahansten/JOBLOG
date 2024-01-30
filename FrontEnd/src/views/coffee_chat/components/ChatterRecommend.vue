<template>
  <div class="carousel-container">
    <button @click="scrollLeft">Left</button>
    <div ref="chatterList" class="chatter-list">
      <ChatterListItem 
        v-for="(item, index) in chatterItems" 
        :key="index"
        :item="item"/>
    </div>
    <button @click="scrollRight">Right</button>
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

const scrollAmount = 1000; // Adjust this value as needed

const scrollLeft = () => {
  if (chatterList.value) {
    chatterList.value.scrollTo({
      left: chatterList.value.scrollLeft - scrollAmount,
      behavior: 'smooth'
    });
  }
};

const scrollRight = () => {
  if (chatterList.value) {
    chatterList.value.scrollTo({
      left: chatterList.value.scrollLeft + scrollAmount,
      behavior: 'smooth'
    });
  }
};

</script>

<style scoped>
  .carousel-container {
    display: flex;
    align-items: center;
  }
  .chatter-list {
    border: 1px solid black;
    margin: 10px;
    height: 400px;
    width: 1344px;
    padding: 0 20px;
    box-sizing: border-box;
    display: grid;
    grid-auto-flow: column; /* Flow items in a column direction */
    grid-template-rows: 1fr 1fr; /* Two rows */
    overflow-x: auto; /* Enable horizontal scrolling */
    gap: 10px; /* Optional: gap between items */
  }

</style>
