<template>
    <div class="create-container">
      <div v-for="(essay, index) in essays" :key="index" class="essay-container">
        <input v-model="essay.question" type="text" placeholder="문항을 입력하세요" class="input-title"/>
        <textarea v-model="essay.answer" placeholder="내용을 입력하세요" class="input-content"></textarea>
        <button @click="deleteEssay(index)">Delete</button>
      </div>
      <button @click="addEssay">Add Essay</button>
    </div>
</template>

<script setup>  
import { ref } from 'vue';
import { useEssayResumeStore } from '@/stores/essayResume';

const essayResumeStore = useEssayResumeStore()

const essays = ref({
    userId: '',
    recruitId: null,
    categoryId: 1,
    question: '',
    answer: '',
  });

const addEssay = async() => {
  await essayResumeStore.createEssay(essays.value)
  essays.value = {
    userId: '',
    recruitId: null,
    categoryId: 1,
    question: '',
    answer: '',
  }
}

const deleteEssay = (index) => {
  essays.value.splice(index, 1);
};
</script>

<style scoped>
  .create-container {
    display: flex;
    flex-direction: column;
    width: 1344px;
  }

  .essay-container {
    margin-bottom: 10px;
  }

  button {
    margin-top: 5px;
  }
  
  .input-title, .input-content {
    width: 1344px;
  }
</style>
