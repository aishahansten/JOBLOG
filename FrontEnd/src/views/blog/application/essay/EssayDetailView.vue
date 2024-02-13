<template>
  <div v-if="!isLoaded"></div>
  <div v-else>
    <div class="essay-container">
      <div class="essay-header">
        <p>카테고리: {{ essay.categoryName }}</p>
      </div>
      <div class="essay-main">
        <div>
          <input class="question" type="text" v-model="essay.question">
        </div>
        <div>
          <input class="answer" type="text" v-model="essay.answer">
        </div>
      </div>
      <div class="essay-footer">
        <button @click="submitEssay()">저장하기</button>
        <button @click="deleteEssay()">삭제하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useEssayResumeStore } from '@/stores/essayResume';
import { useRoute, useRouter } from 'vue-router';

const essayResumeStore = useEssayResumeStore()
const route = useRoute()
const router = useRouter()

const essay = ref({})

const isLoaded = ref(false)

const submitEssay = async() => {
  delete essay.value.categoryName
  delete essay.value.recruitId
  console.log('수정', essay.value)
  await essayResumeStore.updateEssay(essay)
  alert('수정되었습니다.')
}

const deleteEssay = async() => {
  await essayResumeStore.deleteEssay(route.params.id)
  alert('삭제되었습니다.')
  router.push({ name: 'BlogApplication'})
}


onMounted(async() => {
  await essayResumeStore.getEssayDetail(route.params.id)
  essay.value = essayResumeStore.currentEssay
  console.log(essayResumeStore.currentEssay)
  isLoaded.value = true
})
</script>

<style scoped>

</style>