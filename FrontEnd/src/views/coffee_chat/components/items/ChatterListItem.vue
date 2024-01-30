<template>
    <div class="chatter-container">
      <div class="chatter-img">
        <!-- Image or placeholder here -->
      </div>
      <div class="chatter-profile">
        <div>{{ item.user_id }}</div>
        <div>{{ item.job }}</div>
        <div>{{ item.career }}</div>
        <button @click="toggleModal">예약하기</button>
      </div>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <div class="x-button"> <button @click="toggleModal">X</button></div>
        <div class="profile"> 
          <div class="chatter-img-2"></div>
          <div class="chatter-info">
            <div class="info-bold">{{ item.user_id }}</div>
            <div class="info-normal">직군: {{ item.job }}</div>
            <div class="info-normal">경력: {{ item.career }}</div>
            <div class="info-normal">나의 한마디: {{ item.description }}</div>
          </div>
        </div>
        <div v-if="!showModal2">
          <button @click="toggleModal2">예약하기</button>
          schedule 또는 개인 선호 시간
        </div>
        <div v-else>
          <button @click="toggleModal2">돌아가기</button>

          예약 신청하는 페이지
        </div>
      </div>
    </div>
  </template>
  
<script setup>
  import { ref, onMounted, onUnmounted } from 'vue';
  
  defineProps({
    item: Object
  });
  
  // 모달을 보이는 함수
  const showModal = ref(false);
  
  const toggleModal = () => {
    showModal.value = !showModal.value;

  };

  const showModal2 = ref(false)

  const toggleModal2 = () => {
    showModal2.value = !showModal2.value
  }
  //-----------------------------------

  // 모달 esc 클릭을 위한 함수
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
  // -----------------------------------------------------------
</script>
  
  
<style scoped>
@import '@/views/coffee_chat/css/ChatterProfile.css'

</style>
  