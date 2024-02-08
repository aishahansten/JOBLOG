<template>
  <div>
    제목: <input type="text" v-model="resumeResponse.title">
    직무: <input type="text" v-model="resumeResponse.job">
  </div>
    <div class="resume-container">
      <div class="resume-upper">
        <div class="resume-picture" @click="triggerFileInput">
          <img v-if="imageUrl" :src="imageUrl" alt="Profile Picture Preview" class="profileImage">
        </div>
        <input type="file" ref="fileInput" @change="handleFileUpload" style="display:none;">
        <div class="personal-info">
          <div class="name">
            <div class="info-title">
              이름
            </div>
            <div>
              <input type="text" v-model="userData.realName" :readonly="isReadOnly">
            </div>
            <div class="info-title">
              영문
            </div>
            <div>
              <input type="text" v-model="userData.englishName" :readonly="isReadOnly">
            </div>
          </div>
          <div class="reg-number">
            <div class="info-title">
              주민등록번호
            </div>
            <div>
              <!-- Assuming 'reg_number' is the field for 주민등록번호 -->
              <input type="text" v-model="userData.birthDate">
            </div>
          </div>
          <div class="numbers">
            <div class="info-title">
              H.P
            </div>
            <div>
              <!-- Assuming 'mobile_phone' is the field for H.P -->
              <input type="text" v-model="userData.phoneNumber" :readonly="isReadOnly">
            </div>
            <div class="info-title">
              지원직무
            </div>
            <div>
              <!-- Assuming 'telephone' is the field for Tel. -->
              <input type="text" v-model="resumeResponse.job">
            </div>
          </div>
          <div class="email">
            <div class="info-title">
              이메일
            </div>
            <div>
              <input type="text" v-model="userData.userEmail" :readonly="isReadOnly">
            </div>
          </div>
          <div class="address">
            <div class="info-title">
              주소
            </div>
            <div>
              <input type="text" v-model="userData.address" :readonly="isReadOnly">
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="create-container">
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
          <div class="info-item" v-for="info in education" :key="info.id" >
            <div class="info-container">
              <input type="text" v-model="info.title" placeholder="Title">
              <input type="text" v-model="info.institutionName" placeholder="Institution Name">
              <input type="date" v-model="info.startDate" placeholder="Start Date">
              <input type="date" v-model="info.endDate" placeholder="End Date">
              <select v-model="info.graduationStatus">
                <option value="0">Select Graduation Status</option>
                <option value="1">Graduated</option>
                <option value="2">Studying</option>
                <!-- Add more options as needed, incrementing the value -->
              </select>
              <select v-model="info.yesOrNot">
                <option value="0">Choose Yes or No</option>
                <option value="1">Yes</option>
                <option value="2">No</option>
              </select>
              <select v-model="info.dayOrNight">
                <option value="0">Select Day or Night</option>
                <option value="1">Day</option>
                <option value="2">Night</option>
              </select>
            </div>
          </div>
        </div>
        <div class="info-container">
          <div class="info-title">
            <th>회사명</th>
            <th>직무</th>
            <th>시작 일자</th>
            <th>종료 일자</th>
          </div>
          <div class="info-item" v-for="info in career" :key="info.id" >
            <input type="text" v-model="info.institutionName">
            <input type="text" placeholder="Title" v-model="info.title">
            <input type="date" placeholder="Start Date" v-model="info.startDate">
            <input type="date" placeholder="End Date" v-model="info.endDate">
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
          <div class="info-item" v-for="info in activity" :key="info.id" >
            <input type="text" placeholder="Title" v-model="info.title">
            <input type="text" v-model="info.institutionName">
            <input type="text" v-model="info.description">
            <input type="date" placeholder="Start Date" v-model="info.startDate">
            <input type="date" placeholder="End Date" v-model="info.endDate">
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
          <div class="info-item" v-for="info in certificate" :key="info.id" >
            <input type="text" placeholder="Title" v-model="info.title">
            <input type="text" v-model="info.institutionName">
            <input type="text" v-model="info.description">
            <input type="date" placeholder="Start Date" v-model="info.startDate">
            <input type="date" placeholder="End Date" v-model="info.endDate">
            <input type="date" placeholder="End Date" v-model="info.level">
          </div>
        </div>
        <div class="info-container">
          <div class="info-title">
            <th>수상명</th>
            <th>기관명</th>
            <th>수상 일자</th>
            <th>수상 상세 내용</th>
          </div>
          <div class="info-item" v-for="info in award" :key="info.id" >
            <input type="text" placeholder="Title" v-model="info.title">
            <input type="text" v-model="info.institutionName">
            <input type="date" placeholder="Start Date" v-model="info.startDate">
            <input type="text" v-model="info.description">
          </div>    
        </div>
        <div class="info-container"> 
          <div class="info-title">
            <th>기술명</th>
            <th>분야</th>
            <th>상세 설명</th>
            <th>숙련도</th>
          </div>
          <div class="info-item" v-for="info in skill" :key="info.id" >
            <input type="text" placeholder="Title" v-model="info.title">
            <input type="text" v-model="info.institutionName">
            <input type="text" v-model="info.description">
            <select v-model="info.skillLevel">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useEssayResumeStore } from '@/stores/essayResume';
  import { useRoute } from 'vue-router';
  import ResumeDetailInfo from './ResumeDetailInfo.vue';
  const essayResumeStore = useEssayResumeStore()
  const route = useRoute()
  
  
  //read-only 로 만들기
  const isReadOnly = ref(true)
  
  const fileInput = ref(null);
  const imageUrl = ref(null);
  
  const triggerFileInput = () => {
    fileInput.value.click();
  };
  
  const handleFileUpload = event => {
    const file = event.target.files[0];
    if (file && file.type.startsWith('image/')) {
      const reader = new FileReader();
      reader.onload = e => {
        imageUrl.value = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  };


const userData = ref({})
const info = ref([])
const resumeResponse = ref([])
const career = ref([])
const education = ref([])
const skill = ref([])
const award = ref([])
const activity = ref([])
const certificate = ref([])

onMounted(async() => {
  await essayResumeStore.getResumeDetail(route.params.id)
  userData.value = essayResumeStore.currentResume
  info.value = essayResumeStore.currentResume.infoResponseDtos
  resumeResponse.value = essayResumeStore.currentResume.resumeResponseDto
  const filteredInfo = (category) => {
    return info.value.filter(info => info.infoCategory === category);
  };  
  try {
    career.value = await filteredInfo('CAREER')
    education.value = await filteredInfo('EDUCATION')
    skill.value = await filteredInfo('SKILL')
    award.value = await filteredInfo('AWARD')
    activity.value = await filteredInfo('ACTIVITY')
    certificate.value = await filteredInfo('CERTIFICATE')
  } catch(err) {
    console.log(err)
  }
})


</script>
<style scoped>
  @import "@/views/blog/application/css/ResumeCreateView.css" 

</style>
