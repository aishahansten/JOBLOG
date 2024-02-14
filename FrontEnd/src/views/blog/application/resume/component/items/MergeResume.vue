<template>
  <div class="resume-container">
    <h1>이력서</h1>

    <section class="personal">
      <!-- <input
        type="file"
        ref="fileInput"
        @change="handleFileUpload"
        style="display: none"
      /> -->

      <div class="personal-info">
        <div class="line">
          <div>
            <p class="info-title">이름</p>
            <input type="text" v-model="userData.realName" />
          </div>
          <div>
            <p class="info-title">영문</p>
            <input type="text" v-model="userData.englishName" />
          </div>
        </div>
        <div class="line">
          <div>
            <p class="info-title">주민등록번호</p>
            <input type="text" v-model="userData.birthDate" />
          </div>
          <div>
            <p class="info-title">H.P</p>
            <input type="text" v-model="userData.phoneNumber" />
          </div>
        </div>
        <div class="line">
          <div class="info-title">지원직무</div>
          <input type="text" v-model="resumeResponse.job" />
        </div>
        <div class="line">
          <p class="info-title">이메일</p>
          <input type="text" v-model="userData.userEmail" />
        </div>
        <div class="line">
          <p class="info-title">주소</p>
          <input type="text" v-model="userData.address" />
        </div>
      </div>

      <div class="resume-pic" @click="triggerFileInput">
        <img
          v-if="imageUrl"
          :src="imageUrl"
          alt="Profile Picture Preview"
          class="profileImage"
        />
      </div>
    </section>

    <section class="resume" style="padding-bottom: 100px">
      <div class="info-container" id="educate">
        <div class="tag">
          <p class="info-title">학교명</p>
          <p class="info-title">전공 및 학위</p>
          <p class="info-title">입학 일자</p>
          <p class="info-title">졸업 일자</p>
          <p class="info-title">졸업여부</p>
          <p class="info-title">편입 여부</p>
          <p class="info-title">주간/야간</p>
        </div>
        <div class="info-item" v-for="info in education" :key="info.id">
          <input
            type="text"
            v-model="info.institutionName"
            placeholder="Institution Name"
          />
          <input type="text" v-model="info.title" placeholder="Major" />
          <input
            type="date"
            v-model="info.startDate"
            placeholder="Start Date"
          />
          <input type="date" v-model="info.endDate" placeholder="End Date" />
          <select v-model="info.graduationStatus">
            <option value="0">재학 중</option>
            <option value="1">졸업 예정</option>
            <option value="2">졸업</option>
            <!-- Add more options as needed, incrementing the value -->
          </select>
          <select v-model="info.yesOrNot">
            <option value="0">X</option>
            <option value="1">O</option>
          </select>
          <select v-model="info.dayOrNight">
            <option value="0">주간</option>
            <option value="1">야간</option>
          </select>
        </div>
      </div>

      <div class="info-container">
        <div class="tag">
          <p class="info-title">회사명</p>
          <p class="info-title">직무</p>
          <p class="info-title">시작 일자</p>
          <p class="info-title">종료 일자</p>
        </div>
        <div class="info-item" v-for="info in career" :key="info.id">
          <input type="text" v-model="info.institutionName" />

          <input type="text" placeholder="Title" v-model="info.title" />

          <input
            type="date"
            placeholder="Start Date"
            v-model="info.startDate"
          />

          <input type="date" placeholder="End Date" v-model="info.endDate" />
        </div>
      </div>

      <div class="info-container">
        <div class="tag">
          <p class="info-title">활동/교육명</p>
          <p class="info-title">활동/교육 주관 기관명</p>
          <p class="info-title">활동/교육 시작 일자</p>
          <p class="info-title">활동/교육 종료 일자</p>
          <p class="info-title">활동/교육 상세 내용</p>
        </div>
        <div class="info-item" v-for="info in activity" :key="info.id">
          <input type="text" placeholder="Title" v-model="info.title" />

          <input type="text" v-model="info.institutionName" />

          <input type="text" v-model="info.description" />

          <input
            type="date"
            placeholder="Start Date"
            v-model="info.startDate"
          />

          <input type="date" placeholder="End Date" v-model="info.endDate" />
        </div>
      </div>

      <div class="info-container">
        <div class="tag">
          <p class="info-title">자격증/어학성적명</p>
          <p class="info-title">기관명</p>
          <p class="info-title">응시 일자/발급 일자</p>
          <p class="info-title">만료 일자</p>
          <p class="info-title">자격번호/언어 종류</p>
          <p class="info-title">자격 등급/어학 등급</p>
        </div>
        <div class="info-item" v-for="info in certificate" :key="info.id">
          <input type="text" placeholder="Title" v-model="info.title" />

          <input type="text" v-model="info.institutionName" />

          <input type="text" v-model="info.description" />

          <input
            type="date"
            placeholder="Start Date"
            v-model="info.startDate"
          />

          <input type="date" placeholder="End Date" v-model="info.endDate" />

          <input type="date" placeholder="End Date" v-model="info.level" />
        </div>
      </div>

      <div class="info-container">
        <div class="tag">
          <p class="info-title">수상명</p>
          <p class="info-title">기관명</p>
          <p class="info-title">수상 일자</p>
          <p class="info-title">수상 상세 내용</p>
        </div>
        <div class="info-item" v-for="info in award" :key="info.id">
          <input type="text" placeholder="Title" v-model="info.title" />

          <input type="text" v-model="info.institutionName" />

          <input
            type="date"
            placeholder="Start Date"
            v-model="info.startDate"
          />

          <input type="text" v-model="info.description" />
        </div>
      </div>

      <div class="info-container">
        <div class="tag">
          <p class="info-title">기술명</p>
          <p class="info-title">분야</p>
          <p class="info-title">상세 설명</p>
          <p class="info-title">숙련도</p>
        </div>
        <div class="info-item" v-for="info in skill" :key="info.id">
          <input type="text" placeholder="Title" v-model="info.title" />

          <input type="text" v-model="info.institutionName" />

          <input type="text" v-model="info.description" />

          <select v-model="info.skillLevel">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import { useRoute } from "vue-router";
import ResumeDetailInfo from "./ResumeDetailInfo.vue";
const essayResumeStore = useEssayResumeStore();
const route = useRoute();

//read-only 로 만들기
const isReadOnly = ref(true);

const fileInput = ref(null);
const imageUrl = ref(null);

const triggerFileInput = () => {
  fileInput.value.click();
};

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  if (file && file.type.startsWith("image/")) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imageUrl.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const userData = ref({});
const info = ref([]);
const resumeResponse = ref([]);
const career = ref([]);
const education = ref([]);
const skill = ref([]);
const award = ref([]);
const activity = ref([]);
const certificate = ref([]);

onMounted(async () => {
  await essayResumeStore.getResumeDetail(route.params.id);
  userData.value = essayResumeStore.currentResume;
  info.value = essayResumeStore.currentResume.infoResponseDtos;
  resumeResponse.value = essayResumeStore.currentResume.resumeResponseDto;
  const filteredInfo = (category) => {
    return info.value.filter((info) => info.infoCategory === category);
  };
  try {
    career.value = await filteredInfo("CAREER");
    education.value = await filteredInfo("EDUCATION");
    skill.value = await filteredInfo("SKILL");
    award.value = await filteredInfo("AWARD");
    activity.value = await filteredInfo("ACTIVITY");
    certificate.value = await filteredInfo("CERTIFICATE");
  } catch (err) {
    console.log(err);
  }
});
</script>

<style scoped>
* {
  font-size: 12px;
}
.resume-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 300mm;
  gap: 30px;
}
.resume-container h1 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 28px;
}

/* 개인정보 */
.personal {
  display: grid;
  grid-template-columns: 5fr 1fr;
  grid-gap: 20px;
  justify-content: center;
  align-items: center;

  padding: 10px 0;
  border-top: 2px solid var(--gray);
  border-bottom: 2px solid var(--gray);
}
.personal-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.line {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid var(--border-gray);
  padding: 10px;
}
.line:last-child {
  border: none;
}
.line div {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.line input {
  background: none;
  text-align: end;
  font-weight: 500;
}
.info-title {
  width: 100px;
  font-weight: 700;
  color: var(--gray);
}
.resume-pic {
  display: flex;
  width: 30mm;
  height: 40mm;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

/* resume */

.resume {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.info-container {
  display: flex;
  flex-direction: column;
  gap: 10px;

  padding: 10px 0;
  border-top: 2px solid var(--gray);
  border-bottom: 2px solid var(--gray);
}
.tag {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 0.3fr 0.3fr;
  grid-gap: 10px;

  width: 100%;
  padding: 10px 0;
  border-bottom: 1px solid var(--gray);
}
.info-item {
  display: grid;
  grid-template-columns: 0.7fr 0.7fr 1fr 1fr 1fr 0.5fr 0.3fr;
  grid-gap: 10px;

  padding-bottom: 10px;
  border-bottom: 1px solid var(--border-gray);
}
.info-item:last-child {
  border: none;
}

/* 공통 */
input {
  border: 0px;
  background-color: rgb(240, 240, 240);
}
input:focus {
  outline: auto;
}
</style>
