<template>
    <div class="chatter-container">
      <div class="chatter-img">
        <!-- Image or placeholder here -->
      </div>
      <div class="chatter-profile">
        <div>{{ item.name }}</div>
        <div>{{ item.jobTitle }}</div>
        <div>{{ item.experience }}</div>
        <button @click="toggleModal">예약하기</button>
      </div>
    </div>
    <div v-if="showModal" class="modal">
    <div class="modal-content">
      <div class="x-button"> <button @click="toggleModal">X</button></div>
      <div class="profile"> 
        <div class="chatter-img"></div>
      </div>
    </div>
  </div>
  </template>
  
<script setup>
  import { ref, onMounted, onUnmounted } from 'vue';
  
  defineProps({
    item: Object
  });
  
  const showModal = ref(false);
  
  const toggleModal = () => {
    showModal.value = !showModal.value;
  };
  
  const handleEscapeKeyPress = event => {
    if (event.key === 'Escape') {
      showModal.value = false;
    }
  };
  
  onMounted(() => {
    window.addEventListener('keydown', handleEscapeKeyPress);
  });
  
  onUnmounted(() => {
    window.removeEventListener('keydown', handleEscapeKeyPress);
  });
</script>
  
  
<style scoped>
.chatter-container {
    display: grid;
    grid-template-columns: 1.7fr 1fr;
    border: 1px solid black;
    border-radius: 8px;
    width: 270px;
    height: 198px;
    padding: 10px;
    box-sizing: border-box;
    margin-left: 25px;
}
  .chatter-img {
    border: 1px solid black;
    background-color: #e0e0e0; /* Placeholder color, replace with actual image */
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .chatter-profile div {
    margin-bottom: 5px;
  }
  .modal {
    position: fixed;
    top: 10%;
    left: 21.5%;
    width: 1100px;
    height: 700px;
    background-color: rgba(0, 0, 0, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1;
  }

  .modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    width: 1000px;
    height: 600px;
  }

  .modal-content textarea {
    resize: none;
    width: 994px;
    height: 200px
  }

  .x-button {
    margin-left: auto;
  }
</style>
  