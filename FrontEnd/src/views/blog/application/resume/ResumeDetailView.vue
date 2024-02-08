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
    <RouterLink :to="{ name: 'ResumeUpdate', params: { id:route.params.id} }"><button>수정하기</button></RouterLink>
  </div>
  <div>

  </div>
</template>
  
<script setup>
  import html2pdf from 'html2pdf.js'
  import MergeResume from "@/views/blog/application/resume/component/items/MergeResume.vue"
  import { useEssayResumeStore } from '@/stores/essayResume';
  import { useSettingResumeStore } from '@/stores/settingResume';
  const essayResumeStore = useEssayResumeStore()
  const settingResumeStore = useSettingResumeStore()

  import { ref, onMounted } from 'vue'
  import { useRoute } from 'vue-router';
  const route = useRoute()

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
        showEssay.value.push(selectedEssay);
      } else {
        showEssay.value.splice(index, 1);
      }
    };
  
    const currentDrag = ref(null)
    const handleDragStart = (essay) => {
      currentDrag.value = essay;
    };
  
    const handleDrop = () => {
      if (currentDrag.value) {
        const index = showEssay.value.findIndex((essay) => essay.essayId === currentDrag.value.essayId);
        if (index === -1) {
          showEssay.value.push(currentDrag.value);
        }
        currentDrag.value = null;
    }
    };

onMounted(async() => {
  await settingResumeStore.getInfo()
  await essayResumeStore.getResume(route.params.id)
  })
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