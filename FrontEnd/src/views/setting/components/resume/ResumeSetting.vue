<script setup>
import { ref } from "vue";
import ResumeSettingEdit from "./ResumeSettingEdit.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/resume-setting.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="content-container" v-if="!isEdit">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">이력 정보 설정</p>
        <p>이력서 작성에 사용될 개인 이력 정보를 변경합니다.</p>
      </div>
      <div class="content-function">
        <a @click="toggleEdit" class="edit-btn clickable-btn">설정 변경하기</a>
      </div>
    </div>

    <div class="resume-content">
      <div class="component" id="half">
        <div class="description">
          <p class="title">취업사진 등록</p>
          <p>이력서에 사용될 증명사진입니다.</p>
        </div>
        <div class="identify-pic-space">
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
            <div class="info">
              <span class="tag">성명(한글)</span>
              <p class="data">박시연</p>
            </div>
            <div class="info">
              <span class="tag">성명(영문)</span>
              <p class="data">박시연</p>
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">생년월일</span>
              <p class="data">1999.05.16</p>
            </div>
            <div class="info">
              <span class="tag">성별</span>
              <p class="data">여</p>
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">전화번호</span>
              <p class="data">010-0101-0101</p>
            </div>
            <div class="info">
              <span class="tag">이메일 주소</span>
              <p class="data">xion2664@gmail.com</p>
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">현주소(실거주지)</span>
              <p class="data">대전광역시 유성구 유성동 유성빌라 010호</p>
            </div>
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">학적사항 등록</p>
          <p>고등학교, 대학원, 대학원 등의 학적입니다.</p>
        </div>
        <table>
          <tr>
            <th class="tag">학교명</th>
            <th class="tag">전공 및 학위</th>
            <th class="tag">입학 및 졸업 연/월</th>
          </tr>
          <tr class="data">
            <td>순천향대학교</td>
            <td>컴퓨터소프트웨어공학과 학사</td>
            <td>2018년 3월 ~ 2023년 8월</td>
          </tr>
          <tr class="data">
            <td>개짱짱대학교</td>
            <td>소프트웨어공학과 석사</td>
            <td>2023년 9월 ~ 2026년 8월</td>
          </tr>
        </table>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">경력사항 등록</p>
          <p>이력서에 등록할 수 있는 경력사항입니다.</p>
        </div>
        <table>
          <tr>
            <th class="tag">기관명</th>
            <th class="tag">담당 직무</th>
            <th class="tag">재직 기간</th>
          </tr>
          <tr class="data">
            <td>가상테크</td>
            <td>UI/UX 디자인</td>
            <td>2024년 5월 ~ 2025년 6월</td>
          </tr>
          <tr class="data">
            <td>가상컴퍼니</td>
            <td>UX 디자인</td>
            <td>2025년 7월 ~</td>
          </tr>
        </table>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">자격 및 교육사항 등록</p>
          <p>이력서에 등록할 수 있는 자격·면허사항 및 교육사항입니다.</p>
        </div>
        <table>
          <tr>
            <th class="tag">자격명</th>
            <th class="tag">발급일</th>
            <th class="tag">발급기관</th>
          </tr>
          <tr class="data">
            <td>정보처리기사</td>
            <td>2024년 05월 16일</td>
            <td>큐넷</td>
          </tr>
          <tr class="data">
            <td>빅데이터처리기사</td>
            <td>2024년 03월 23일</td>
            <td>언젠간따고싶다</td>
          </tr>
        </table>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">수상이력 등록</p>
          <p>이력서에 등록할 수 있는 공모전, 대회 등의 수상이력입니다.</p>
        </div>
        <table>
          <tr>
            <th class="tag">대회 및 수상명</th>
            <th class="tag">수상일</th>
            <th class="tag">주최기관</th>
          </tr>
          <tr class="data">
            <td>2023 SW 창업경진대회 장려상</td>
            <td>2022년 06월 05일</td>
            <td>순천향대학교 SW융합대학</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
  <div class="content-container" v-else>
    <ResumeSettingEdit />
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
