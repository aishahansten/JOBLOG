<template>
  <div class="resume-container">
    <h1>이력서</h1>
    <section class="personal">
      <div class="personal-space">
        <input
          type="file"
          ref="fileInput"
          @change="handleFileUpload"
          style="display: none"
        />
        <div class="personal-info">
          <div class="name">
            <div class="info-title">이름</div>
            <div>
              <input
                type="text"
                v-model="userData.realName"
                :readonly="isReadOnly"
              />
            </div>
            <div class="info-title">영문</div>
            <div>
              <input
                type="text"
                v-model="userData.englishName"
                :readonly="isReadOnly"
              />
            </div>
          </div>
          <div class="reg-number">
            <div class="info-title">주민등록번호</div>
            <div>
              <!-- Assuming 'reg_number' is the field for 주민등록번호 -->
              <input type="text" v-model="userData.birthDate" />
            </div>
          </div>
          <div class="numbers">
            <div class="info-title">H.P</div>
            <div>
              <!-- Assuming 'mobile_phone' is the field for H.P -->
              <input
                type="text"
                v-model="userData.phoneNumber"
                :readonly="isReadOnly"
              />
            </div>
            <div class="info-title">지원직무</div>
            <div>
              <!-- Assuming 'telephone' is the field for Tel. -->
              <input type="text" v-model="resumeResponse.job" />
            </div>
          </div>
          <div class="email">
            <div class="info-title">이메일</div>
            <div>
              <input
                type="text"
                v-model="userData.userEmail"
                :readonly="isReadOnly"
              />
            </div>
          </div>
          <div class="address">
            <div class="info-title">주소</div>
            <div>
              <input
                type="text"
                v-model="userData.address"
                :readonly="isReadOnly"
              />
            </div>
          </div>
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

    <div class="resume">
      <div>
        <table class="info-container">
          <tr class="info-title">
            <th>학교명</th>
            <th>전공 및 학위</th>
            <th>입학 일자</th>
            <th>졸업 일자</th>
            <th>졸업여부</th>
            <th>편입 여부</th>
            <th>주간/야간</th>
          </tr>
          <tr class="info-item" v-for="info in education" :key="info.id">
            <td>
              <input
                type="text"
                v-model="info.institutionName"
                placeholder="Institution Name"
              />
            </td>
            <td>
              <input type="text" v-model="info.title" placeholder="Major" />
            </td>
            <td>
              <input
                type="date"
                v-model="info.startDate"
                placeholder="Start Date"
              />
            </td>
            <td>
              <input
                type="date"
                v-model="info.endDate"
                placeholder="End Date"
              />
            </td>
            <td>
              <select v-model="info.graduationStatus">
                <option value="0">Select Graduation Status</option>
                <option value="1">Graduated</option>
                <option value="2">Studying</option>
                <!-- Add more options as needed, incrementing the value -->
              </select>
            </td>
            <td>
              <select v-model="info.yesOrNot">
                <option value="0">Choose Yes or No</option>
                <option value="1">Yes</option>
                <option value="2">No</option>
              </select>
            </td>
            <td>
              <select v-model="info.dayOrNight">
                <option value="0">Select Day or Night</option>
                <option value="1">Day</option>
                <option value="2">Night</option>
              </select>
            </td>
          </tr>
        </table>

        <table class="info-container">
          <tr class="info-title">
            <th>회사명</th>
            <th>직무</th>
            <th>시작 일자</th>
            <th>종료 일자</th>
          </tr>
          <tr class="info-item" v-for="info in career" :key="info.id">
            <td>
              <input type="text" v-model="info.institutionName" />
            </td>
            <td>
              <input type="text" placeholder="Title" v-model="info.title" />
            </td>
            <td>
              <input
                type="date"
                placeholder="Start Date"
                v-model="info.startDate"
              />
            </td>
            <td>
              <input
                type="date"
                placeholder="End Date"
                v-model="info.endDate"
              />
            </td>
          </tr>
        </table>

        <table class="info-container">
          <tr class="info-title">
            <th>활동/교육명</th>
            <th>활동/교육 주관 기관명</th>
            <th>활동/교육 시작 일자</th>
            <th>활동/교육 종료 일자</th>
            <th>활동/교육 상세 내용</th>
          </tr>
          <tr class="info-item" v-for="info in activity" :key="info.id">
            <td>
              <input type="text" placeholder="Title" v-model="info.title" />
            </td>
            <td>
              <input type="text" v-model="info.institutionName" />
            </td>
            <td>
              <input type="text" v-model="info.description" />
            </td>
            <td>
              <input
                type="date"
                placeholder="Start Date"
                v-model="info.startDate"
              />
            </td>
            <td>
              <input
                type="date"
                placeholder="End Date"
                v-model="info.endDate"
              />
            </td>
          </tr>
        </table>

        <table class="info-container">
          <tr class="info-title">
            <th>자격증/어학성적명</th>
            <th>기관명</th>
            <th>응시 일자/발급 일자</th>
            <th>만료 일자</th>
            <th>자격번호/언어 종류</th>
            <th>자격 등급/어학 등급</th>
          </tr>
          <tr class="info-item" v-for="info in certificate" :key="info.id">
            <td>
              <input type="text" placeholder="Title" v-model="info.title" />
            </td>
            <td>
              <input type="text" v-model="info.institutionName" />
            </td>
            <td>
              <input type="text" v-model="info.description" />
            </td>
            <td>
              <input
                type="date"
                placeholder="Start Date"
                v-model="info.startDate"
              />
            </td>
            <td>
              <input
                type="date"
                placeholder="End Date"
                v-model="info.endDate"
              />
            </td>
            <td>
              <input type="date" placeholder="End Date" v-model="info.level" />
            </td>
          </tr>
        </table>

        <table class="info-container">
          <tr class="info-title">
            <th>수상명</th>
            <th>기관명</th>
            <th>수상 일자</th>
            <th>수상 상세 내용</th>
          </tr>
          <tr class="info-item" v-for="info in award" :key="info.id">
            <td>
              <input type="text" placeholder="Title" v-model="info.title" />
            </td>
            <td>
              <input type="text" v-model="info.institutionName" />
            </td>
            <td>
              <input
                type="date"
                placeholder="Start Date"
                v-model="info.startDate"
              />
            </td>
            <td>
              <input type="text" v-model="info.description" />
            </td>
          </tr>
        </table>

        <table class="info-container">
          <tr class="info-title">
            <th>기술명</th>
            <th>분야</th>
            <th>상세 설명</th>
            <th>숙련도</th>
          </tr>
          <tr class="info-item" v-for="info in skill" :key="info.id">
            <td>
              <input type="text" placeholder="Title" v-model="info.title" />
            </td>
            <td>
              <input type="text" v-model="info.institutionName" />
            </td>
            <td>
              <input type="text" v-model="info.description" />
            </td>
            <td>
              <select v-model="info.skillLevel">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>
            </td>
          </tr>
        </table>
      </div>
    </div>
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
  height: 240mm;
  gap: 20px;
  background-color: aqua;
}
.resume-container h1 {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 28px;
}

.personal {
  display: grid;
  grid-template-columns: 4fr 1fr;
  height: 150px;
  grid-gap: 20px;
}
.personal-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
  padding: 10px 0;
}
.resume-pic {
  display: flex;
  width: 30mm;
  height: 40mm;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.section {
  border-bottom: 3px solid black;
}

.section-title {
  border-bottom: 1px solid rgb(118, 118, 118);
  padding-bottom: 5px;
  margin-bottom: 5px;
}

table {
  width: 100%; /* Table takes the full width of its container */
  border-collapse: collapse; /* Optional: for a cleaner look */
}

.thick {
  border: 2px;
}

.reg-number,
.email,
.address {
  display: grid;
  grid-template-columns: 1fr 3fr;
}
.name,
.numbers {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr 3fr;
}

input {
  width: 95%;
  height: 95%;
  border: 0px;
  background-color: rgb(240, 240, 240);
  outline: none;
}

.profileImage {
  display: flex;
  width: 170px;
  height: 210px;
  justify-content: center;
  align-items: center;
}
</style>
