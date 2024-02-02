<template>
  <SubNav/>
  
  <div class="journal-container">
    <div class="journal-header">
      <div><h1>나의 다이어리</h1></div>
      <RouterLink :to="{ name:'JournalCreate' }" class="journal-button-container"><button class="journal-button">+ 다이어리 쓰기</button></RouterLink>
    </div>
    <div class="journal-list">
      <JournalList/>
    </div>
  </div>

</template>

<script setup>
import { RouterLink } from 'vue-router';
import JournalList from './components/JournalList.vue';
import SubNav from '../_component/SubNav.vue';
</script>

<style scoped>

  .journal-container {
    display: grid;
    grid-template-rows: 1fr 9fr 1fr;
  }

  .journal-header {
    display: grid;
    grid-template-columns: 1fr 1fr;
    width: 1344px;
    margin-bottom: 30px;
  }

  .journal-button {
    color: #6880FF;
    text-align: center;
    font-family: Pretendard Variable;
    font-size: 12px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    background-color: white;
    border: #6880FF 2px solid;
    padding: 10px 20px;
    border-radius: 8px;
  }
  .journal-button-container {
    margin-left: auto;
    margin-top: auto;
  }
</style>