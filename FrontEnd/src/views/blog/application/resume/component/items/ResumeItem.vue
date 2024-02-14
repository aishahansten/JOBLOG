<script setup>
import { ref, onMounted } from "vue";

const props = defineProps({
  resu: Object,
});
</script>

<template>
  <div class="resume">
    <div class="resume-title">
      <h3>{{ resu.title }}</h3>
      <p>
        <b class="f-color-g">{{ resu.job }}</b> 직무
      </p>
    </div>
    <div class="resume-go">
      <RouterLink
        :to="{ name: 'ResumeDetail', params: { id: resu.id } }"
        class="btn solid-g h-solid-c a-bright"
      >
        입사지원서 작성　<i class="fa-solid fa-pen-nib"></i>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.resume {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
}
.resume-title {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
