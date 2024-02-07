<script setup>
import Education from "./item/Education.vue"
import Career from "./item/Career.vue"
import Activity from "./item/Activity.vue"
import Award from "./item/Award.vue"
import Skill from "./item/Skill.vue"
import Certificate from "./item/Certificate.vue"

import "@/assets/css/setting/setting-header.css"
import "@/assets/css/setting/menu/resume-setting.css"
import { ref, onMounted } from 'vue'
import { useSettingResumeStore } from "@/stores/settingResume"
import { useAuthStore } from "@/stores/auth"
import axios from "axios"

const authStore = useAuthStore()
const settingResumeStore = useSettingResumeStore()

const userInfo = ref(null)
const informations = ref([])
onMounted(async() => {
  await settingResumeStore.getInfo()
  informations.value = settingResumeStore.informations
  console.log(informations)
  authStore.updateUserInfoFromToken()
})

const filteredInfo = function(category) {
  return this.informations.filter(info => info.infoCategory === category)
}

</script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">이력 정보 설정</p>
        <p>이력서 작성에 사용될 개인 이력 정보를 변경합니다.</p>
      </div>
      <div class="content-function">
        <a href="" class="update-btn clickable">변경 완료</a>
      </div>
    </div>
    <div class="resume-content">
      <div class="component" id="half">
        <div class="description">
          <p class="title">취업사진 등록</p>
          <p>이력서에 사용될 증명사진입니다.</p>
        </div>
        <div class="identify-pic-space">
          <div class="input-pic-guide">
            <ul>
              <li>얼굴이 뚜렷하게 드러난 사진 파일</li>
              <li>배경이 없는 png 등이 아닌 사진 파일</li>
            </ul>
            <input
              type="file"
              class="file-input"
              accept="image/jpg, image/jpeg"
            />
          </div>
          <div class="identify-pic">
            <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">인적사항 등록</p>
          <p>이력서에 사용될 성명, 주소, 연락처입니다.</p>
        </div>
        <div class="info-space">
          <div>
            <div class="user-info">
              <span class="tag">성명(한글)</span>
              <input type="text" placeholder="박시연" required />
            </div>
            <div class="user-info">
              <span class="tag">성명(영문)</span>
              <input type="text" placeholder="Park Si Yeon" required />
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="tag">생년월일</span>
              <input type="date" required />
            </div>
            <div class="user-info">
              <span class="tag">성별</span>
              <div>
                <div>
                  <input type="radio" id="" />
                  <label for=""></label>
                </div>
                <div>
                  <input type="radio" class="user-radio" />
                </div>
              </div>
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="tag">전화번호</span>
              <input type="tel" placeholder="010-0101-0101" />
            </div>
            <div class="user-info">
              <span class="tag">이메일 주소</span>
              <input type="email" placeholder="xion2664@gmail.com" />
            </div>
          </div>
          <div>
            <div class="user-info">
              <span class="tag">현주소(실거주지)</span>
              <input
                type="text"
                placeholder="대전광역시 유성구 유성동 유성빌라 010호"
                id="long"
              />
            </div>
          </div>
        </div>
      </div>
    <div>
      <div v-for="info in filteredInfo('EDUCATION')" :key="info.id">
        {{ info.title }}
        {{ info.institutionName }}
        {{ info.startDate }}
        {{ info.endDate }}
        {{ info.graduationStatus }}
        {{ info.yesOrNot }}
        {{ info.dayOrNight }}
      </div>
      <Education/>
    </div>
    <div>
      <div v-for="info in filteredInfo('CAREER')" :key="info.id">
        {{ info.institutionName }}
        {{ info.title }}
        {{ info.startDate }}
        {{ info.endDate }}
      </div>
      <Career/>
    </div>
    <div>
      <div v-for="info in filteredInfo('ACTIVITY')" :key="info.id">
        {{ info.title }}
        {{ info.institutionName }}
        {{ info.description }}
        {{ info.startDate }}
        {{ info.endDate }}
      </div>
      <Activity/>
    </div>
    <div>
      <div v-for="info in filteredInfo('CERTIFICATE')" :key="info.id">
        {{ info.title }}
        {{ info.institutionName }}
        {{ info.description }}
        {{ info.startDate }}
        {{ info.endDate }}
        {{ info.level }}
      </div>
      <Certificate/>
    </div>
      <div v-for="info in filteredInfo('AWARD')" :key="info.id">
        {{ info.title }}
        {{ info.institutionName }}
        {{ info.startDate }}
        {{ info.description }}
      </div>    
      <Award/>
    </div>
    <div>
      <div v-for="info in filteredInfo('SKILL')" :key="info.id">
        {{ info.title }}
        {{ info.institutionName }}
        {{ info.description }}
        {{ info.skillLevel }}
      </div>
      <Skill/>
    </div>
  </div>
</template>

<style scoped>
/* 공통 */

.resume-content {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  padding: 70px 50px;
  gap: 100px;
}
.component {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 30px;
}

.description {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.description .title {
  font-size: 24px;
  font-weight: 600;
}
.description p {
  font-size: 16px;
  font-weight: 200;
}

.tag {
  font-size: 16px;
  font-weight: 600;
  color: var(--gray);
}
.data {
  font-size: 20px;
  font-weight: 500;
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

/* 기타 등록 */

table {
  width: 100%;
  table-layout: fixed;
  border-spacing: 0 10px;
}
th {
  text-align: left;
  padding: 10px 0;
}

td div {
  display: flex;
  gap: 5px;
}
tr input {
  width: 95%;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.add-btn {
  display: flex;
  width: 98.5%;
  justify-content: center;
  padding: 10px;
  background-color: var(--border-gray);
  border-radius: 10px;
  color: white;
  font-size: 20px;
  font-weight: 800;
}

.click:hover {
  filter: brightness(107%);
  transition: 0.1s;
}
</style>
