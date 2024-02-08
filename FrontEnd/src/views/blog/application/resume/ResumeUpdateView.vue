<template>
    <button @click="updateResume">이력서 생성하기</button>
    <div class="create-container">
      <div class="resume">
        <div>
          제목: <input type="text" v-model="resume.title" required>
        </div>
        <div>
          직무: <input type="text" v-model="resume.job" required>
        </div>
        <div>
          사용 정보:
          <div class="info-container">
            <!-- EDUCATION Section -->
            <div class="info-title">
              <th>학교명</th>
              <th>전공 및 학위</th>
              <th>입학 일자</th>
              <th>졸업 일자</th>
              <th>졸업여부</th>
              <th>편입 여부</th>
              <th>주간/야간</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('EDUCATION')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.startDate }}
              {{ info.endDate }}
              {{ info.graduationStatus }}
              {{ info.yesOrNot }}
              {{ info.dayOrNight }}
            </div>
          </div>
          <!-- CAREER Section -->
          <div class="info-container">
            <div class="info-title">
              <th>회사명</th>
              <th>직무</th>
              <th>시작 일자</th>
              <th>종료 일자</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('CAREER')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
              {{ info.institutionName }}
              {{ info.title }}
              {{ info.startDate }}
              {{ info.endDate }}
            </div>
          </div>
          <!-- ACTIVITY Section -->
          <div class="info-container">
            <div class="info-title">
              <th>활동/교육명</th>
              <th>활동/교육 주관 기관명</th>
              <th>활동/교육 시작 일자</th>
              <th>활동/교육 종료 일자</th>
              <th>활동/교육 상세 내용</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('ACTIVITY')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.description }}
              {{ info.startDate }}
              {{ info.endDate }}
            </div>
          </div>
          <!-- CERTIFICATE Section -->
          <div class="info-container">
            <div class="info-title">
              <th>자격증/어학성적명</th>
              <th>기관명</th>
              <th>응시 일자/발급 일자</th>
              <th>만료 일자</th>
              <th>자격번호/언어 종류</th>
              <th>자격 등급/어학 등급</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('CERTIFICATE')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.description }}
              {{ info.startDate }}
              {{ info.endDate }}
              {{ info.level }}
            </div>
          </div>
          <!-- AWARD Section -->
          <div class="info-container">
            <div class="info-title">
              <th>수상명</th>
              <th>기관명</th>
              <th>수상 일자</th>
              <th>수상 상세 내용</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('AWARD')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
              {{ info.title }}
              {{ info.institutionName }}
              {{ info.startDate }}
              {{ info.description }}
            </div>    
          </div>
          <!-- SKILL Section -->
          <div class="info-container">
            <div class="info-title">
              <th>기술명</th>
              <th>분야</th>
              <th>상세 설명</th>
              <th>숙련도</th>
            </div>
            <div class="info-item" v-for="info in filteredInfo('SKILL')" :key="info.id" @click="addToInfoList(info.id)" :class="{'selected': isInInfoList(info.id)}">
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
  import { ref, onMounted } from 'vue';
  import { useSettingResumeStore } from '@/stores/settingResume';
  import { useEssayResumeStore } from '@/stores/essayResume';
  import { useAuthStore } from '@/stores/auth';
  import axios from 'axios';
  import { useRoute, useRouter } from 'vue-router';
  
  const route = useRoute()
  const router = useRouter()
  const settingResumeStore = useSettingResumeStore()
  const essayResumeStore = useEssayResumeStore()
  const resume = ref({
    userId: "",
    resumeId: route.params.id,
    title: "",
    job: "",
    infoList: [] 
  });
  
  const infoListAll = ref([])
  
  const filteredInfo = (category) => {
    return infoListAll.value.filter(info => info.infoCategory === category);
  }
  
  const addToInfoList = (id) => {
    const index = resume.value.infoList.indexOf(id)
    if (index > -1) {
      resume.value.infoList.splice(index, 1)
    } else {
      resume.value.infoList.push(id)
    }
  };
  
  const isInInfoList = (id) => {
    return resume.value.infoList.includes(id)
  };
  
  onMounted(async () => {
    await settingResumeStore.getInfo()
    await essayResumeStore.getResumeDetail(route.params.id)
    infoListAll.value = settingResumeStore.informations
    resume.value.infoList = infoListAll.value.map(info => info.id)
    resume.value.title = essayResumeStore.currentResume.resumeResponseDto.title
    resume.value.job = essayResumeStore.currentResume.resumeResponseDto.job
    resume.value.userId = essayResumeStore.currentResume.resumeResponseDto.userId
    console.log(essayResumeStore.currentResume)
  })
  
  console.log(resume)

  const updateResume = async() => {
    const authStore = useAuthStore()
    await authStore.updateUserInfoFromToken()
    resume.value.userId = authStore.userInfo.sub
    if (resume.value.title.trim() === ''){
      alert('제목을 작성해주세요');
      return
    }
    if (resume.value.job.trim() === '') {
      alert('직업을 작성해주세요');
      return;
    }
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`,
      },
    };
    try {
      const res = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/resume/update`, resume.value, config);
      console.log(res.data)
      router.push('/blog-application')
    } catch (err) {
      console.error(err);
    }
  }
  
  </script>
  
  
  <style scoped>
  .info-container {
    border: 1px solid #ccc;
    margin: 20px 0;
    padding: 10px;
  }
  
   .info-title th {
    font-weight: bold;
    margin-bottom: 5px;
    gap: 10px;
  }
  
  .info-item {
    border: 1px solid #eee;
    margin: 10px 0;
    padding: 10px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .info-item:hover {
    background-color: #f9f9f9;
  }
  
  .selected {
    background-color: #e2e2e2; /* Light grey background for selected items */
    border-color: #d1d1d1; /* Slightly darker border for better contrast */
  }
  
  /* Optional: Add more specific styles below if needed */
  </style>
  