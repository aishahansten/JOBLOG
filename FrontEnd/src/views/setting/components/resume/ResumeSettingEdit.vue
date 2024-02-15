<script setup>
import Education from "./item/Education.vue";
import Career from "./item/Career.vue";
import Activity from "./item/Activity.vue";
import Award from "./item/Award.vue";
import Skill from "./item/Skill.vue";
import Certificate from "./item/Certificate.vue";

import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/resume-setting.css";
import { ref, onMounted } from "vue";
import { useSettingResumeStore } from "@/stores/settingResume";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";

import CategoryData from "@/assets/data/jobcategory.json";

const category = ref([]);
category.value = CategoryData.map(({ jobCode, jobName }) => ({
  jobCode,
  jobName,
}));

console.log(category);
const authStore = useAuthStore();
const settingResumeStore = useSettingResumeStore();

const loading = ref(true);

const informations = ref([]);
const userInfo = ref(null);

const handleRefresh = async () => {
  try {
    loading.value = true;
    await settingResumeStore.getInfo();
  } catch (error) {
    console.error("Error refreshing data:", error);
  } finally {
    informations.value = settingResumeStore.informations;
    loading.value = false;
  }
};

onMounted(async () => {
  await settingResumeStore.getInfo();
  await settingResumeStore.getUserInfo();
  userInfo.value = settingResumeStore.userInfo;
  informations.value = settingResumeStore.informations;
  authStore.updateUserInfoFromToken();
  loading.value = false;
});

console.log(userInfo);

const filteredInfo = function (category) {
  return this.informations.filter((info) => info.infoCategory === category);
};

const submitUserInfo = function (userInfo) {
  console.log(userInfo);
  settingResumeStore.updateUserInfo(userInfo);
};

const deleteInfo = async (id) => {
  await settingResumeStore.deleteInfo(id);
  await handleRefresh();
};

const uploadFile = async (event) => {
  const file = event.target.files[0];
  if (!file) {
    return;
  }

  const formData = new FormData();
  formData.append("file", file);

  try {
    const res = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/users/profile/${authStore.userInfo.sub}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    await settingResumeStore.getUserInfo();
    userInfo.value = settingResumeStore.userInfo;
    console.log("업로드성공", res.data);
  } catch (err) {
    console.log(err);
  }
};
</script>

<template>
  <div v-if="loading"></div>

  <div class="content-container" v-else>
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">이력 정보 설정</p>
        <p>이력서 작성에 사용될 개인 이력 정보를 변경합니다.</p>
      </div>
      <div class="content-function">
        <a @click="submitUserInfo(userInfo)" class="btn lined-c f-color-c h-solid-c a-bright">저장</a>
      </div>
    </div>

    <div class="resume-content">
      <div class="component" id="half">
        <div class="title">
          <h2>취업사진 등록</h2>
          <p class="f-weight-l">이력서에 사용될 증명사진을 등록해주세요.</p>
        </div>
        <div class="identify-pic-space">
          <div class="input-pic-guide">
            <ul>
              <li>얼굴이 뚜렷하게 드러난 사진 파일</li>
              <li>배경이 없는 png 등이 아닌 사진 파일</li>
            </ul>
            <input type="file" class="file-input" accept="image/jpg, image/jpeg" @change="uploadFile" />
          </div>
          <div class="identify-pic">
            <img src="@/assets/img/profile/default-user-pic.jpg" v-if="!userInfo.amazonS3FileUrl" />
            <img :src="userInfo.amazonS3FileUrl" v-else />
          </div>
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>인적사항 등록</h2>
          <p class="f-weight-l">성명, 주소, 연락처 등의 인적사항을 등록해주세요.</p>
        </div>
        <div class="info-space">
          <div>
            <div class="user-info">
              <span class="f-color-g f-weight-b">성명(한글)</span>
              <input type="text" v-model="userInfo.realName" required />
            </div>
            <div class="user-info">
              <span class="f-color-g f-weight-b">성명(영문)</span>
              <input type="text" v-model="userInfo.englishName" required />
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="f-color-g f-weight-b">생년월일</span>
              <input type="date" v-model="userInfo.birthDate" required />
            </div>
            <div class="user-info">
              <span class="f-color-g f-weight-b">희망 직군/직무</span>
              <select v-model="userInfo.objective">
                <option v-for="item in category" :key="item.jobCode" :value="item.jobCode">
                  {{ item.jobName }}
                </option>
              </select>
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="tag">전화번호</span>
              <input type="tel" v-model="userInfo.phoneNumber" />
            </div>
            <div class="user-info">
              <span class="tag">이메일 주소</span>
              <input type="email" v-model="userInfo.userEmail" />
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="tag">현주소(실거주지)</span>
              <input type="text" id="long" v-model="userInfo.address" />
            </div>
          </div>
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>학적사항 등록</h2>
          <p class="f-weight-l">고등, 대학, 대학원 등의 학적을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="educate-info">
            <p class="f-color-g f-weight-b">학교명</p>
            <p class="f-color-g f-weight-b">전공 및 학위</p>
            <p class="f-color-g f-weight-b">입학 일자</p>
            <p class="f-color-g f-weight-b">졸업 일자</p>
            <p class="f-color-g f-weight-b">졸업 여부</p>
            <p class="f-color-g f-weight-b">편입 여부</p>
            <p class="f-color-g f-weight-b">야간 여부</p>
            <p class="f-color-g f-weight-b">　</p>
          </div>
          <div class="classfy info" v-for="info in filteredInfo('EDUCATION')" :key="info.id">
            <p>{{ info.institutionName }}</p>
            <p>{{ info.title }}</p>
            <p>{{ info.startDate }}</p>
            <p>{{ info.endDate }}</p>
            <p>
              {{ info.graduationStatus === 0 ? "재학 중" : info.graduationStatus === 1 ? "졸업 예정" : "졸업" }}
            </p>
            <p>{{ info.yesOrNot === 1 ? "O" : "X" }}</p>
            <p>{{ info.dayOrNight === 1 ? "O" : "X" }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Education @refresh="handleRefresh" />
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>경력사항 등록</h2>
          <p class="f-weight-l">회사 재직 등의 경력사항을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="career-info">
            <p class="f-color-g f-weight-b">회사명</p>
            <p class="f-color-g f-weight-b">직무</p>
            <p class="f-color-g f-weight-b">시작 일자</p>
            <p class="f-color-g f-weight-b">종료 일자</p>
          </div>
          <div class="classfy info" id="career-info" v-for="info in filteredInfo('CAREER')" :key="info.id">
            <p>{{ info.institutionName }}</p>
            <p>{{ info.title }}</p>
            <p>{{ info.startDate }}</p>
            <p>{{ info.endDate }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Career @refresh="handleRefresh" />
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>교육 및 활동사항 등록</h2>
          <p class="f-weight-l">외부교육, 대외활동 등의 사항을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="activity-info">
            <p class="f-color-g f-weight-b">활동/교육명</p>
            <p class="f-color-g f-weight-b">기관명</p>
            <p class="f-color-g f-weight-b">시작 일자</p>
            <p class="f-color-g f-weight-b">종료 일자</p>
            <p class="f-color-g f-weight-b">상세 내용</p>
          </div>
          <div class="classfy info" id="activity-info" v-for="info in filteredInfo('ACTIVITY')" :key="info.id">
            <p>{{ info.title }}</p>
            <p>{{ info.institutionName }}</p>
            <p>{{ info.startDate }}</p>
            <p>{{ info.endDate }}</p>
            <p>{{ info.description }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Activity @refresh="handleRefresh" />
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>어학 및 자격사항 등록</h2>
          <p class="f-weight-l">어학성적, 공인자격 등의 사항을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="cert-info">
            <p class="f-color-g f-weight-b">자격명</p>
            <p class="f-color-g f-weight-b">기관명</p>
            <p class="f-color-g f-weight-b">응시/발급 일자</p>
            <p class="f-color-g f-weight-b">만료 일자</p>
            <p class="f-color-g f-weight-b">자격/언어 종류</p>
            <p class="f-color-g f-weight-b">자격/어학 등급</p>
          </div>
          <div class="classfy info" id="cert-info" v-for="info in filteredInfo('CERTIFICATE')" :key="info.id">
            <p>{{ info.title }}</p>
            <p>{{ info.institutionName }}</p>
            <p>{{ info.startDate }}</p>
            <p>{{ info.endDate }}</p>
            <p>{{ info.description }}</p>
            <p>{{ info.level }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Certificate @refresh="handleRefresh" />
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>수상 이력 등록</h2>
          <p class="f-weight-l">공모전, 대회 등의 수상 이력을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="award-info">
            <p class="f-color-g f-weight-b">수상명</p>
            <p class="f-color-g f-weight-b">기관명</p>
            <p class="f-color-g f-weight-b">수상 일자</p>
            <p class="f-color-g f-weight-b">상세 내용</p>
          </div>
          <div class="classfy info" id="award-info" v-for="info in filteredInfo('AWARD')" :key="info.id">
            <p>{{ info.title }}</p>
            <p>{{ info.institutionName }}</p>
            <p>{{ info.startDate }}</p>
            <p>{{ info.description }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Award @refresh="handleRefresh" />
        </div>
      </div>

      <div class="component">
        <div class="title">
          <h2>사용 기술 등록</h2>
          <p class="f-weight-l">직무에 도움이 되는 각종 기술에 대한 사용 경험을 등록해주세요.</p>
        </div>
        <div class="infos">
          <div class="classfy" id="skill-info">
            <p class="f-color-g f-weight-b">기술명</p>
            <p class="f-color-g f-weight-b">분야</p>
            <p class="f-color-g f-weight-b">상세 내용</p>
            <p class="f-color-g f-weight-b">숙련도</p>
          </div>
          <div class="classfy info" id="skill-info" v-for="info in filteredInfo('SKILL')" :key="info.id">
            <p>{{ info.title }}</p>
            <p>{{ info.institutionName }}</p>
            <p>{{ info.description }}</p>
            <p>{{ info.skillLevel }}</p>
            <a @click="deleteInfo(info.id)" class="btn-s solid-g h-bright a-dark">삭제</a>
          </div>
          <Skill @refresh="handleRefresh" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 공통 */
.resume-content {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  gap: 100px;

  width: 100%;
  padding: 70px 50px;
}
.component {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 30px;
}
.title {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

/* 취업사진 등록 */
#half {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-between;
}

.identify-pic-space {
  display: flex;
  align-items: end;
  gap: 10px;
}
.input-pic-guide {
  display: flex;
  flex-direction: column;
  align-items: end;
  gap: 10px;
}

.identify-pic {
  width: 150px;
  height: 200px;

  border: 1px solid var(--border-gray);
  border-radius: 10px;
  overflow: hidden;
}
.identify-pic img {
  width: 150px;
  height: 200px;
  object-fit: cover;
}

/* 인적사항 등록 */
.info-space {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 10px;
}
.info-space div {
  display: flex;
  align-items: center;
  gap: 70px;
  align-self: stretch;
}
.user-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  flex: 1 0 0;
}

.user-info input {
  text-align: baseline;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
input#long {
  width: 500px;
}
input#user-radio {
  font-size: 20px;
}

.user-info select {
  text-align: baseline;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

/* 학력 등록 */
.infos {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.classfy {
  display: grid;
  grid-template-columns: 1fr 1.2fr 1fr 1fr 0.8fr 0.5fr 0.5fr 0.4fr;
  grid-gap: 5px;
  align-items: center;
  width: 100%;
}
.classfy p {
  width: 100%;
  padding: 0 10px;
}
.info p {
  font-size: 18px;
}

#educate-info {
  grid-template-columns: 1fr 1.2fr 1fr 1fr 0.8fr 0.5fr 0.5fr 0.4fr;
}
#activity-info {
  grid-template-columns: 1fr 1fr 1fr 1fr 1.5fr 0.4fr;
}
#career-info {
  grid-template-columns: 1fr 1fr 1fr 1fr 0.3fr;
}
#cert-info {
  grid-template-columns: 1fr 0.7fr 1fr 1fr 0.7fr 0.7fr 0.4fr;
}
#award-info {
  grid-template-columns: 1fr 1fr 0.8fr 1.5fr 0.3fr;
}
#skill-info {
  grid-template-columns: 1fr 0.5fr 1.5fr 0.5fr 0.3fr;
}
</style>
