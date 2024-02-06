<template>
    <h2>작성 중인 자소서/이력서</h2>
    <div class="container">
      <div class="subcontainer">
        <button @click="moveCarousel('left')">Left</button>
        <div class="carousel-container">
          <div class="carousel" :style="carouselStyle">
            <div class="doc-container" v-for="n in 5" :key="n">
                <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
                <p>SSAFY 지원 이력서</p>
            </div>
          </div>
        </div>
        <button @click="moveCarousel('right')">Right</button>
      </div>
      <div class="subcontainer">
        <div class="essay-container">
          <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
          <p>SSAFY 지원 자소서</p>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';

// Define itemWidth outside of moveCarousel for global access within this script
const itemWidth = 100; // Adjust as needed based on your item width
const maxItems = 4;
const totalItems = 5; // Update this to match your total number of items

const carouselPosition = ref(0);

const moveCarousel = (direction) => {
  if (direction === 'left' && carouselPosition.value > 0) {
    carouselPosition.value -= 1;
  } else if (direction === 'right' && carouselPosition.value < totalItems - maxItems) {
    carouselPosition.value += 1;
  }
};

const carouselStyle = computed(() => ({
  transform: `translateX(${-carouselPosition.value * itemWidth}px)`
}));
</script>


<style scoped>
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    margin-bottom: 10px;
    height: 170px;
    border: solid 1px black;
  }

  .subcontainer {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .carousel-container {
    overflow: hidden;
    flex-grow: 1;
    position: relative;
  }

  .carousel {
    display: flex;
    transition: transform 0.3s ease;
  }

  .doc-container, .essay-container {
    height: 120px;
    min-width: 100px; /* Adjust width based on your needs */
    background-color: rgba(192, 181, 255, 0.701);
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 10px;
    margin-right: 10px; /* Spacing between items */
  }

  .essay-container {
    background-color: rgb(176, 180, 255);
  }

  .icon {
    height: 80px;
    width: 80px;
    margin-bottom: 10px;
  }

  p {
    margin: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    width: 90%;
  }
</style>
