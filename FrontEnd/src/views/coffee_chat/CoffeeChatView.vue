<template>
  <div class="main-container">
    <RouterLink :to="{ name: 'ChatterReg' }">
      <img src="@/assets/img/coffee-chat/chatterbanner.png" alt="" class="banner-img">
    </RouterLink>
    <div class="text">
        <h2 class="subtitle">커피챗 채터 찾기</h2>
        <p>고민에 대한 간단한 이야기를 나눌 채터 탐색</p>
    </div>
    <div>
      <ChatterRecommend/>
    </div>
  </div>
</template>

<script setup>
import ChatterRecommend from './components/ChatterRecommend.vue';
</script>

<style scoped>

  .main-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  

  .banner-img {
    height: 300px;
    width: 1288px;
    margin: 10px;
  }

  .text {
    margin-left: 10px;
    margin-bottom: 0;
    padding-bottom: 0;
    gap: 0;
    width: 1344px;
  }

  .subtitle {
    margin-bottom: 0;
    padding-bottom: 0;
  }

  p {
    margin-top: 0;
    padding-top: 0;
  }
</style>