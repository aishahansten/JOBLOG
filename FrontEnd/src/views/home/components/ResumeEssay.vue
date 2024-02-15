<template>
  <div class="container">
    <div class="header">
      <div>
        <RouterLink :to="{ name: 'BlogApplication' }"> <h1 class="f-weight-t">나의 이력서·자소서 관리</h1> </RouterLink>
        <i class="fa-solid fa-angle-right fa-xl"></i>
      </div>
    </div>

    <div class="content" v-if="resume.length > 0 || essay.length > 0">
      <div class="applications">
        <div v-for="resume in resume" :key="resume.id" class="application">
          <RouterLink :to="{ name: 'ResumeDetail', params: { id: resume.id } }">
            <div class="info">
              <h2>{{ resume.title }}</h2>
              <p>{{ formatISODate(resume.createDate) }} 작성</p>
            </div>
          </RouterLink>
          <img src="@/assets/img/icon/resume.svg" alt="" />
        </div>
      </div>
      <div class="applications">
        <div v-for="essay in essay" :key="essay.essayId" class="application">
          <RouterLink :to="{ name: 'EssayDetail', params: { id: essay.essayId } }">
            <div class="info">
              <h2>{{ essay.question }}</h2>
              <p>{{ essay.answer }}</p>
            </div>
          </RouterLink>
          <img src="@/assets/img/icon/essay.svg" alt="" />
        </div>
      </div>
    </div>
    <div class="resume-essay" v-else>
      <div>
        <RouterLink :to="{ name: 'BlogApplication' }">
          <div class="empty-essay">
            <i class="fa-regular fa-folder-open fa-2xl"></i>
            <p>아직 작성하신 이력서나 자소서가 없는 것 같아요!</p>
            <br />
            <p>이력서를 하나 작성해볼까요?</p>
            <br />
            <p style="font-size: 30pt">+</p>
          </div>
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import { formatISODate } from "@/utils/utility";

const essayResumeStore = useEssayResumeStore();
const resume = ref([]);
const essay = ref([]);

onMounted(async () => {
  await essayResumeStore.getResume();
  resume.value = essayResumeStore.resume.slice(0, 3);
  await essayResumeStore.getEssay();
  essay.value = essayResumeStore.essayList.slice(0, 3);
  console.log(resume, " ", essay);
});
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
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-width: 500px;
  display: block;
}
.resume-essay {
  display: flex;
  justify-content: center;
  align-items: center;
}
.empty-essay {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 500px;
  font-size: 24px;
  gap: 30px;
}
</style>
