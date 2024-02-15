<template>
  <div class="container">
    <div class="header">
      <h1>맞춤 입사지원서 작성</h1>
      <div class="info">
        <div class="left">
          <div class="title">
            <span>
              <h3>{{ resumeResponse.title }}</h3>
            </span>
            <span>
              직무:
              <b>{{ resumeResponse.job }}</b>
            </span>
          </div>
          <RouterLink
            :to="{ name: 'ResumeUpdate', params: { id: route.params.id } }"
            class="btn lined-c h-solid-c a-bright"
            >수정하기</RouterLink
          >
        </div>
        <div class="right">
          <h3>자기소개서 문항</h3>
        </div>
      </div>
    </div>

    <div class="content">
      <div class="pdf-space" id="pdf-download" ref="document">
        <div class="resume-space" @dragover.prevent @drop="handleDrop">
          <MergeResume />
        </div>
        <div class="essay-space">
          <div v-if="showEssay.length > 0">
            <div v-for="essay in showEssay" :key="essay.id" class="essay-list">
              <div class="essay-question">
                <h3>{{ essay.question }}</h3>
                <hr />
              </div>
              <div class="essay-answer">
                <p>{{ essay.answer }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="selection-container">
        <div v-if="essayList.length > 0" class="list">
          <div
            v-for="essay in essayList"
            :key="essay.id"
            class="essay-item"
            draggable="true"
            @dragstart="handleDragStart(essay)"
            :class="{ selected: isEssaySelected(essay) }"
          >
            <div @click="toggleShowEssay(essay)" class="select-essay">
              <h3>{{ essay.question }}</h3>
              <p>{{ essay.answer }}</p>
            </div>
          </div>
        </div>
        <div v-else>작성한 자기소개서 문항이 없습니다!</div>
      </div>
    </div>

    <a @click="exportToPdf" class="btn solid-c h-bright a-dark" id="export-btn"
      >입사지원서 제작하기 !</a
    >
  </div>
</template>

<script setup>
import html2pdf from "html2pdf.js";
import MergeResume from "@/views/blog/application/resume/component/items/MergeResume.vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import { useSettingResumeStore } from "@/stores/settingResume";
const essayResumeStore = useEssayResumeStore();
const settingResumeStore = useSettingResumeStore();

import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();

// here
const resumeResponse = ref([]);

const exportToPdf = function () {
  const pdfArea = document.getElementById("pdf-download");
  const pdfOptions = {
    filename: "testing.pdf",
    image: { type: "jpeg", quality: "0.98" },
    html2canvas: { scale: 2 },
    margin: 0.2,
  };
  html2pdf(pdfArea, pdfOptions);
};

const isEssaySelected = (essay) => {
  for (let i = 0; i < showEssay.value.length; i++) {
    if (showEssay.value[i].essayId === essay.essayId) {
      return true;
    }
  }
  return false;
};

const essayList = ref([]);
const showEssay = ref([]);

const toggleShowEssay = (selectedEssay) => {
  const index = showEssay.value.findIndex(
    (essay) => essay.essayId === selectedEssay.essayId
  );
  if (index === -1) {
    showEssay.value.push(selectedEssay);
  } else {
    showEssay.value.splice(index, 1);
  }
};

const currentDrag = ref(null);
const handleDragStart = (essay) => {
  currentDrag.value = essay;
};

const handleDrop = () => {
  if (currentDrag.value) {
    const index = showEssay.value.findIndex(
      (essay) => essay.essayId === currentDrag.value.essayId
    );
    if (index === -1) {
      showEssay.value.push(currentDrag.value);
    }
    currentDrag.value = null;
  }
};

onMounted(async () => {
  await settingResumeStore.getInfo();
  // await essayResumeStore.getResume(route.params.id);
  await essayResumeStore.getEssay();
  essayList.value = essayResumeStore.essayList;
  await essayResumeStore.getResumeDetail(route.params.id);
  resumeResponse.value = essayResumeStore.currentResume.resumeResponseDto;
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  padding: 30px 0;
}
.header {
}
.header h1 {
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}
.info {
  display: grid;
  grid-template-columns: 210mm auto;
  grid-gap: 20px;
  padding: 30px 0 10px 0;
}
.left {
  display: flex;
  justify-content: space-between;
}
.right {
  display: flex;
  justify-content: center;
  align-items: center;
}

.content {
  display: grid;
  grid-template-columns: 210mm auto;
  grid-gap: 20px;
}
.pdf-space {
  width: 210mm;
  border: 1px solid var(--border-gray);
  padding: 30px;
}

.resume-space {
  width: 100%;
}

.essay-item {
  display: flex;
  gap: 50px;
  border: 1px solid rgba(0, 0, 0, 0.419);
  border-radius: 8px;
  padding: 10px;
  cursor: pointer; /* Indicates the item is clickable */
}

.selected {
  background-color: #e5e5e559; /* Different background for selected items */
}

.essay-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px;
  box-sizing: border-box;
}

.essay-space {
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
  border: 1px solid black;
  padding: 20px;
  box-sizing: border-box;
}

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

  border: 1px solid var(--border-gray);
  background-color: var(--light-gray);
  padding: 20px;
  cursor: pointer; /* Indicates the item is clickable */
}

.selected {
  background-color: var(--light-main);
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
  white-space: pre-wrap;
}

.select-essay {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: pre-wrap;
  height: 110px;
}
.selection-container {
  border: 1px solid var(--border-gray);
  padding: 20px;
  box-sizing: border-box;
}
.selection-container .list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

#export-btn {
  padding: 20px;
  font-size: 20px;
  margin: 30px 0;
}

.title {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-width: 650px; /* or a specific px value */
  display: block; /* ensures the behavior applies correctly */
}
</style>
