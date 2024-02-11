<template>
  <div>
    <ResumeItem
      v-for="resu in resume"
      :key="resu.id"
      :resu="resu"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useEssayResumeStore } from '@/stores/essayResume' 
import ResumeItem from '@/views/blog/application/resume/component/items/ResumeItem.vue'

const essayResumeStore = useEssayResumeStore()

const resume = ref([])

onMounted(async() => {
  await essayResumeStore.getResume()  
  resume.value = essayResumeStore.resume
  console.log(resume.value)
})
</script>

<style scoped>

</style>