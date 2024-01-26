<template>
      <div class="essay-list">
        <div v-if="resumeList.length > 0">
          <div v-for="resume in resumeList" :key="resume.id" class="resume-item">
            <h3>{{ resume.question }}</h3>
            <p>{{ resume.answer }}</p>
          </div>
        </div>
        <div v-else class="empty-essay"> 
          <p>아직 자기소개서를 작성하지 않으신 것 같아요!</p>
          <p>자기소개서 문항을 하나 작성해볼까요?</p>
        </div>
      </div>
</template>
  
<script setup>
  import { ref, defineProps } from 'vue';
  
  // Define the props
  const props = defineProps({
    resumeList: Array,
    // Define other props as needed
  });
  
  const showModal = ref(false);
  
  const toggleModal = () => {
    showModal.value = !showModal.value;
  };
  
  const addDummyEssay = () => {
    // Add dummy essay logic here
    // ...
  };
  
  // Include other reactive properties or methods as needed
  </script>
  
  <style scoped>

  .empty-essay {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 500px;
    font-size: 24px;
  }

  .essay-list {
    height: 700px;
    overflow: auto;
    overflow-y: scroll;
  }

  /* Style the scrollbar itself (background) */
.essay-list::-webkit-scrollbar {
    width: 10px; /* width of the entire scrollbar */
  }

  /* Style the scrollbar thumb (the part you drag) */
  .essay-list::-webkit-scrollbar-thumb {
    background: #ffe2a5; /* thumb color */
    border-radius: 10px;
  }

  /* Handle the hover effect */
  .essay-list::-webkit-scrollbar-thumb:hover {
    background: #ffb565; /* color when hovering over the thumb */
  }

  .resume-item {
    border: 1px solid rgb(255, 207, 173);
    border-radius: 8px;
    background-color: rgb(245, 233, 220);
    box-sizing: border-box;
    margin: 2px;
    width: 550px;
    height: 80px;
  }
  p {
    margin: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: fit-content;
  }
</style>
  