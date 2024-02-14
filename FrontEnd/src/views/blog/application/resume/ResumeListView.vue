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
  <div class="resume-list">
    <ResumeItem class="resume" v-for="resu in resume" :key="resu.id" :resu="resu" />
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
</style>
