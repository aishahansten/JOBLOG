<template>
    <div v-if="!isLoaded">
    </div>
    <div class="create-container" v-else>
      <div class="resume">
        <div>
          사용 정보:
          <div class="info-container">
            <div class="info-title">
              <th>학교명</th>
              <th>전공 및 학위</th>
              <th>입학 일자</th>
              <th>졸업 일자</th>
              <th>졸업여부</th>
              <th>편입 여부</th>
              <th>주간/야간</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('EDUCATION')" :key="info.id" >
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.startDate }}
              {{ info.endDate }}
              {{ info.graduationStatus }}
              {{ info.yesOrNot }}
              {{ info.dayOrNight }}
            </div>
          </div>
          <div class="info-container">
            <div class="info-title">
              <th>회사명</th>
              <th>직무</th>
              <th>시작 일자</th>
              <th>종료 일자</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('CAREER')" :key="info.id" >
              {{ info.institutionName }}
              {{ info.title }}
              {{ info.startDate }}
              {{ info.endDate }}
            </div>
          </div>
          <div class="info-container">
            <div class="info-title">
              <th>활동/교육명</th>
              <th>활동/교육 주관 기관명</th>
              <th>활동/교육 시작 일자</th>
              <th>활동/교육 종료 일자</th>
              <th>활동/교육 상세 내용</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('ACTIVITY')" :key="info.id" >
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.description }}
              {{ info.startDate }}
              {{ info.endDate }}
            </div>
          </div>
          <div class="info-container">
            <div class="info-title">
              <th>자격증/어학성적명</th>
              <th>기관명</th>
              <th>응시 일자/발급 일자</th>
              <th>만료 일자</th>
              <th>자격번호/언어 종류</th>
              <th>자격 등급/어학 등급</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('CERTIFICATE')" :key="info.id">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.description }}
              {{ info.startDate }}
              {{ info.endDate }}
              {{ info.level }}
            </div>
          </div>
          <div class="info-container">
            <div class="info-title">
              <th>수상명</th>
              <th>기관명</th>
              <th>수상 일자</th>
              <th>수상 상세 내용</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('AWARD')" :key="info.id">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.startDate }}
              {{ info.description }}
            </div>    
          </div>
          <div class="info-container">
            <div class="info-title">
              <th>기술명</th>
              <th>분야</th>
              <th>상세 설명</th>
              <th>숙련도</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('SKILL')" :key="info.id">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.description }}
              {{ info.skillLevel }}
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'

const props = defineProps({
    info: Array
})

const isLoaded = ref(false)


// Watch for changes to the `info` prop
watch(() => props.value, (newValue, oldValue) => {

  if (newValue && newValue.length > 0) {
    isLoaded.value = true;
  } else {
    isLoaded.value = false;
  }
}, {
  immediate: true // This ensures the watcher runs immediately with the current value
});


const filteredInfo = (category) => {
  return props.info.filter(info => info.infoCategory === category);
};  
console.log('c', props.info)

</script>

<style scoped>

</style>