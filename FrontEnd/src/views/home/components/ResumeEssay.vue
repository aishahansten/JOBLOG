<template>
  <div class="container">
    <div class="header">
      <div>
        <h1 class="f-weight-t">나의 이력서·자소서 관리</h1>
        <i class="fa-solid fa-angle-right fa-xl"></i>
      </div>
    </div>

    <div class="content">
      <div class="applications">
        <div v-for="resume in resume" :key="resume.id" class="application">
          <div class="info">
            <h2>{{ resume.title }}</h2>
            <p>{{ resume.createDate }}</p>
          </div>
          <img src="@/assets/img/icon/resume.svg" alt="" />
        </div>
      </div>
      <div class="applications">
        <div v-for="essay in essay" :key="essay.essayId" class="application">
          <div class="info">
            <h2>{{ essay.question }}</h2>
            <p>{{ essay.answer }}</p>
          </div>
          <img src="@/assets/img/icon/essay.svg" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useEssayResumeStore } from '@/stores/essayResume'

const essayResumeStore = useEssayResumeStore()
const resume = ref([])
const essay = ref([])

onMounted(async() => {
  await essayResumeStore.getResume()
  resume.value = essayResumeStore.resume.slice(0, 3)
  await essayResumeStore.getEssay()
  essay.value = essayResumeStore.essayList.slice(0, 3)
})
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.header div {
  display: flex;
  align-items: center;
  gap: 10px;
}

.content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 30px;
}

.applications {
  display: flex;
  flex-direction: column;
  gap: 30px;
}
.application {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 30px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
.application img {
  padding: 0 10px;
}

.info {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
</style>
