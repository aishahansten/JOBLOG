<template>
  <a class="btn lined-c h-solid-c a-bright" @click="updateResume">이력서 수정하기</a>
  <div class="create-container">
    <div class="title">
      <div>
        제목:
        <input type="text" class="input" v-model="resume.title" required />
      </div>
      <div>직무: <input type="text" class="input" v-model="resume.job" required /></div>
    </div>

    <div class="resume">
      사용 정보:
      <div class="info-container">
        <!-- EDUCATION Section -->
        <div class="info-title">
          <p>학교명</p>
          <p>전공 및 학위</p>
          <p>입학 일자</p>
          <p>졸업 일자</p>
          <p>졸업여부</p>
          <p>편입 여부</p>
          <p>주간/야간</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('EDUCATION')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.title }}</p>
          <p>{{ info.institutionName }}</p>
          <p>{{ info.startDate }}</p>
          <p>{{ info.endDate }}</p>
          <p>
            {{ info.graduationStatus === 0 ? "재학 중" : info.graduationStatus === 1 ? "졸업 예정" : "졸업" }}
          </p>
          <p>{{ info.yesOrNot === 0 ? "X" : "O" }}</p>
          <p>{{ info.dayOrNight === 0 ? "X" : "O" }}</p>
        </div>
      </div>
      <!-- CAREER Section -->
      <div class="info-container">
        <div class="info-title">
          <p>회사명</p>
          <p>직무</p>
          <p>시작 일자</p>
          <p>종료 일자</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('CAREER')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.institutionName }}</p>
          <p>{{ info.title }}</p>
          <p>{{ info.startDate }}</p>
          <p>{{ info.endDate }}</p>
        </div>
      </div>
      <!-- ACTIVITY Section -->
      <div class="info-container">
        <div class="info-title">
          <p>활동/교육명</p>
          <p>활동/교육 주관 기관명</p>
          <p>활동/교육 시작 일자</p>
          <p>활동/교육 종료 일자</p>
          <p>활동/교육 상세 내용</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('ACTIVITY')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.title }}</p>
          <p>{{ info.institutionName }}</p>
          <p>{{ info.description }}</p>
          <p>{{ info.startDate }}</p>
          <p>{{ info.endDate }}</p>
        </div>
      </div>
      <!-- CERTIFICATE Section -->
      <div class="info-container">
        <div class="info-title">
          <p>자격증/어학성적명</p>
          <p>기관명</p>
          <p>응시 일자/발급 일자</p>
          <p>만료 일자</p>
          <p>자격번호/언어 종류</p>
          <p>자격 등급/어학 등급</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('CERTIFICATE')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.title }}</p>
          <p>{{ info.institutionName }}</p>
          <p>{{ info.description }}</p>
          <p>{{ info.startDate }}</p>
          <p>{{ info.endDate }}</p>
          <p>{{ info.level }}</p>
        </div>
      </div>
      <!-- AWARD Section -->
      <div class="info-container">
        <div class="info-title">
          <p>수상명</p>
          <p>기관명</p>
          <p>수상 일자</p>
          <p>수상 상세 내용</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('AWARD')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.title }}</p>
          <p>{{ info.institutionName }}</p>
          <p>{{ info.startDate }}</p>
          <p>{{ info.description }}</p>
        </div>
      </div>
      <!-- SKILL Section -->
      <div class="info-container">
        <div class="info-title">
          <p>기술명</p>
          <p>분야</p>
          <p>상세 설명</p>
          <p>숙련도</p>
        </div>
        <div
          class="info-item"
          v-for="info in filteredInfo('SKILL')"
          :key="info.id"
          @click="addToInfoList(info.id)"
          :class="{ selected: isInInfoList(info.id) }"
        >
          <p>{{ info.title }}</p>
          <p>{{ info.institutionName }}</p>
          <p>{{ info.description }}</p>
          <p>{{ info.skillLevel }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useSettingResumeStore } from "@/stores/settingResume";
import { useEssayResumeStore } from "@/stores/essayResume";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const settingResumeStore = useSettingResumeStore();
const essayResumeStore = useEssayResumeStore();
const resume = ref({
  userId: "",
  resumeId: route.params.id,
  title: "",
  job: "",
  infoList: [],
});

const infoListAll = ref([]);

const filteredInfo = (category) => {
  return infoListAll.value.filter((info) => info.infoCategory === category);
};

const addToInfoList = (id) => {
  const index = resume.value.infoList.indexOf(id);
  if (index > -1) {
    resume.value.infoList.splice(index, 1);
  } else {
    resume.value.infoList.push(id);
  }
};

const isInInfoList = (id) => {
  return resume.value.infoList.includes(id);
};

onMounted(async () => {
  await settingResumeStore.getInfo();
  await essayResumeStore.getResumeDetail(route.params.id);
  infoListAll.value = settingResumeStore.informations;
  resume.value.infoList = infoListAll.value.map((info) => info.id);
  resume.value.title = essayResumeStore.currentResume.resumeResponseDto.title;
  resume.value.job = essayResumeStore.currentResume.resumeResponseDto.job;
  resume.value.userId = essayResumeStore.currentResume.resumeResponseDto.userId;
  console.log(essayResumeStore.currentResume);
});

console.log(resume);

const updateResume = async () => {
  const authStore = useAuthStore();
  await authStore.updateUserInfoFromToken();
  resume.value.userId = authStore.userInfo.sub;
  if (resume.value.title.trim() === "") {
    alert("제목을 작성해주세요");
    return;
  }
  if (resume.value.job.trim() === "") {
    alert("직업을 작성해주세요");
    return;
  }
  const config = {
    headers: {
      Authorization: `${authStore.accessToken}`,
    },
  };
  try {
    const res = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/resume/update`, resume.value, config);
    console.log(res.data);
    router.push("/blog-application");
  } catch (err) {
    console.error(err);
  }
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 20px 20px 20px;
  border-radius: 0 0 10px 10px;
  border: 1px solid var(--border-gray);
  border-top: none;
}
.header a {
  display: flex;
  gap: 5px;
}

/* title */
.title {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  padding: 30px 0;
}
.title input {
  width: 300px;
}

/* content */

.resume {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding-bottom: 100px;
}
.info-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  padding: 20px;
}

.info-title {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 0.4fr 0.4fr;
  grid-gap: 10px;
  padding: 10px;
}
.info-title p {
  font-weight: bold;
  color: var(--gray);
  margin-bottom: 5px;
}

.info-item {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 0.4fr 0.4fr;
  grid-gap: 10px;

  border: 1px solid var(--border-gray);
  border-radius: 10px;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.info-item:hover {
  background-color: var(--border-gray);
}

.selected {
  background-color: rgb(104, 128, 255, 0.2);
}
</style>
