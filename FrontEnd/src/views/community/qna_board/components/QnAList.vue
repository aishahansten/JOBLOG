<template>
  <div class="list-content">
    <div class="item-body">
    <QnAListItem/>
    </div>
    <div class="create-button">
      <RouterLink :to="{ name:'QnACreate' }"> <button>작성하기</button></RouterLink>
    </div>
  </div>
</template>

<script setup>
import QnAListItem from './items/QnAListItem.vue';

</script>

<style scoped>
  .list-content {
    display: flex;
    flex-direction: column;
    max-height: 1300px;
    border: 1px solid black;
  }
  
  .item-body {
    margin-left: 40px;
  }

  .create-button {
    margin-left: auto;
  }

</style>