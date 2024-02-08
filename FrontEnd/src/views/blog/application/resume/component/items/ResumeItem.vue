<template>
  <div class="resume-container">
    <div class="resume-title">
      <RouterLink :to="{ name: 'ResumeDetail', params: { id: resu.id } }">
      제목:
      {{ resu.title }}
      </RouterLink>
    </div>
    <div class="resume-job">
      직무:
      {{ resu.job }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const props = defineProps({
    resu: Object
})

</script>

<style  scoped>
  .resume-container {
    border: 1px solid rgba(0, 0, 0, 0.475);
    padding: 10px;
    box-sizing: border-box;

  }

  .resume-title {
    display: flex;
    gap: 10px;
    font-size: 18px;
    font-weight: bold;
  }
</style>