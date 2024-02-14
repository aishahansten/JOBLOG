<script setup>
import { ref, onMounted } from "vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import ResumeItem from "@/views/blog/application/resume/component/items/ResumeItem.vue";

const essayResumeStore = useEssayResumeStore();

const resume = ref([]);

onMounted(async () => {
  await essayResumeStore.getResume();
  resume.value = essayResumeStore.resume;
  console.log(resume.value);
});
</script>

<template>
  <div class="resume-list" v-if="resume.length > 0">
    <ResumeItem class="resume" v-for="resu in resume" :key="resu.id" :resu="resu" />
  </div>
  <div v-else class="empty-essay">
    <i class="fa-regular fa-folder-open fa-2xl"></i>
    <p>아직 이력서를 작성하지 않으신 것 같아요!<br />이력서를 하나 작성해볼까요?</p>
  </div>
</template>

<style scoped>
.resume-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 10px;
}
.resume {
  padding: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  background-color: var(--light-gray);
}

.empty-essay {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 500px;
  font-size: 24px;
}
</style>
