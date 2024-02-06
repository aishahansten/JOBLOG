<template>
<div class="main">
  <div class="pdf-container" id="pdf-download" ref="document">
    <div class="resume-container" @dragover.prevent @drop="handleDrop">
      <MergeResume/>

    </div>
    <div class="essay-container">
      <div v-if="showEssay.length > 0">
        <div v-for="essay in showEssay" :key="essay.id" class="essay-list">
            <div class="essay-question">
              <h3>{{ essay.question }}</h3>
              <hr>
            </div>
            <div class="essay-answer">
              <p>{{ essay.answer }}</p>
            </div>
        </div>
      </div>
    </div>
  </div>
  <div class="selection-container">
    <div v-if="essayList.length > 0">
      <div v-for="essay in essayList" :key="essay.id" class="essay-item" draggable="true" @dragstart="handleDragStart(essay)">
        <div>
        <h3>{{ essay.question }}</h3>
        <p>{{ essay.answer }}</p>
        </div>
        <div>
          <input type="checkbox" @change="toggleShowEssay(essay)">
        </div>
      </div>
    </div>
  </div>
  <button @click="exportToPdf">해주세요</button>

</div>

</template>

<script setup>
import html2pdf from 'html2pdf.js'
import MergeResume from "../components/items/MergeResume.vue"

import { ref } from 'vue'

  const exportToPdf = function () {
    const pdfArea = document.getElementById('pdf-download')
    const pdfOptions = {
      filename: 'testing.pdf',
      image: { type: 'jpeg', quality: '0.98' },
      html2canvas: { scale: 2 },
      margin: 0.2
    }
    html2pdf(pdfArea, pdfOptions)
    console.log('됨?')
  }


  //자소서 리스트를 위함
  const essayList = ref([
    {
      essayId: 1,
      categoryId: 1,
      userId: 1,
      recruitId: 1,
      question: `Dummy essay Title 1`,
      answer: `Dummy essay content for item 1`
    },
    {
      essayId: 2,
      categoryId: 2,
      userId: 2,
      recruitId: 2,
      question: `Dummy essay Title 2`,
      answer: `Dummy essay content for item 2`
    },
    {
      essayId: 3,
      categoryId: 3,
      userId: 3,
      recruitId: 3,
      question: `Dummy essay Title 3`,
      answer: `Dummy essay content for item 3`
    },
  ])


  // 여기에 기존 에세이 정보를 담기
  const showEssay = ref([])

  const toggleShowEssay = (selectedEssay) => {
    const index = showEssay.value.findIndex((essay) => essay.essayId === selectedEssay.essayId);
    if (index === -1) {
      // Essay not in showEssay, add it
      showEssay.value.push(selectedEssay);
    } else {
      // Essay already in showEssay, remove it
      showEssay.value.splice(index, 1);
    }
  };

  // drag&drop
  const currentDrag = ref(null)
  const handleDragStart = (essay) => {
    // Use Vue's reactive ref to store the currently dragged essay
    currentDrag.value = essay;
  };

  const handleDrop = () => {
    if (currentDrag.value) {
      const index = showEssay.value.findIndex((essay) => essay.essayId === currentDrag.value.essayId);
      if (index === -1) {
        showEssay.value.push(currentDrag.value);
      }
    // Reset currentDrag after dropping
      currentDrag.value = null;
  }
  };
// 나중에 할 방법
// 해당 merge 페이지에 이력서 1개의 데이터와 자소서 문항들을 array 형태로 prop
// -> 어디에서 데이터들을 선택해서 받아오는게 맞을까?
// 위의 데이터들을 원했던 형식에 맞게 표현 / v-for 로 자소서 표기
// 가로만 맞추면 세로는 알아서 pdf에서 나뉘어서 들어가기 때문에 신경 쓸 필요 없어짐.
</script>

<style scoped>
  .main {
    margin: 0%;
    padding: 0%;
    display: grid;
    grid-template-columns: 210mm auto;
  }
  .pdf-container {
    width: 210mm;
    border: 1px solid black;
    padding: 0%;
  }

  .resume-container {
    margin: 0%;
    padding: 0%;
  }

  .essay-item {
    display: flex;
    gap: 50px;
    border: 1px solid black;
    background-color: rgb(237, 237, 237);
  }
  
  .essay-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 10px;
    box-sizing: border-box;
    
  }

  .essay-container {
    margin: 0%;
    padding: 0%;
  }

  .essay-question {
    font-size: 20px;
  }

  .essay-answer {
    font-size: 18px;
  }

  .selection-container {
    border:  1px solid black;
    padding: 20px;
    box-sizing: border-box;
  }
</style>