<script setup>
import SettingUser from "@/views/setting/components/resume/item/SettingUser.vue"

import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/resume-setting.css";
import { ref, onMounted } from 'vue'
import { useSettingResumeStore } from "@/stores/settingResume";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";

const authStore = useAuthStore()
const settingResumeStore = useSettingResumeStore()

const userInfo = ref(null)



async function getUserInfo() {
  try {
    function transformNulls(obj) {
      return Object.entries(obj).reduce((acc, [key, value]) => {
      acc[key] = value === null ? '' : value;
      return acc;
      }, {});
    }   
  
    await authStore.updateUserInfoFromToken()
    const response = await axios.get(`${authStore.API_URL}/users/${authStore.userInfo.sub}`)
    console.log('어디서?',response.data)
    return {
      userInfo: transformNulls(response.data)
    };
    
  } catch (error) {
    console.error('불러오기 실패: ', error);
    return { userInfo: null }; // Return nulls or defaults on error
  }
}

// onMounted(async() => {
//   await settingResumeStore.getUserInfo()
//   console.log('뭐라도', settingResumeStore.userInfo)
//   userInfo.value = { ...settingResumeStore.userInfo }
// })

onMounted(async() => {
  const { userInfo: fectedInfo } = await getUserInfo()
  userInfo.value = fectedInfo
  authStore.updateUserInfoFromToken()
})


// 정보 생성 관련 함수/객체
const createInfo = async(info) =>{
  const authStore = useAuthStore();
  await authStore.updateUserInfoFromToken();
  info.userId = authStore.userInfo.sub
  console.log('함수 내에 들어가는 education 테스트', info)
  const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`,
      },
    };
  try {
    const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/info/register`, info, config);
    console.log('이력서 정보 추가', res.data);
  } catch (err) {
    console.error(err);
    console.log(info.value)
  }
}
    
const education = ref({
  userId: "",
  infoCategory: 'EDUCATION',
  title: "",
  institutionName:"",
  startDate: "",
  endDate: "",
  graduationStatus: 0,
  yesOrNot: 0,
  dayOrNight: 0,
})

const career = ref({
  userId: "",
  infoCategory: 'CAREER',
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
})

const activity = ref ({
  userId: "",
  infoCategory: 'ACTIVITY',
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  description: "",
})

const certificate = ref ({
  userId: "",
  infoCategory: 'CERTIFICATE',
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  description: "",
  level: "",
})

const award = ref ({
  userId: "",
  infoCategory: 'AWARD',
  title: "",
  institutionName: "",
  startDate: "",
  description: "",
})

const skill = ref ({
  userId: "",
  infoCategory: 'SKILL',
  title: "",
  institutionName: "",
  description: "",
  skillLevel: 1,
})
//-------------------------------------
</script>

<template>
  <div>
    <SettingUser :userInfo="userInfo"/>
  </div>
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
      <div class="component">
        <div class="description">
          <p class="title">학적사항 등록</p>
          <p>고등학교, 대학원, 대학원 등의 학적입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>학교명</th>
              <th>전공 및 학위</th>
              <th>입학 일자</th>
              <th>졸업 일자</th>
              <th>졸업여부</th>
              <th>편입 여부</th>
              <th>주간/야간</th>
            </tr>
            <tr>
              <td>
                <!-- Add placeholder to institutionName input -->
                <input type="text" v-model="education.institutionName" placeholder="예제) 짱짱대학교" />
              </td>
              <td>
                <!-- Add placeholder to title input -->
                <input type="text" v-model="education.title" placeholder="예제) 경영학과 / 학사"/>
              </td>
              <td>
                <!-- Add placeholder to startDate input -->
                <input type="month" v-model="education.startDate" placeholder="YYYY-MM"/>
              </td>
              <td>
                <!-- Add placeholder to endDate input -->
                <input type="month" v-model="education.endDate" placeholder="YYYY-MM"/>
              </td>
              <td>
                <!-- Add placeholder for graduationStatus select -->
                <select v-model="education.graduationStatus">
                  <option disabled value="">선택해주세요</option>
                  <option value="0">재학 중</option>
                  <option value="1">졸업 예정</option>
                  <option value="2">졸업</option>
                </select>
              </td>
              <td>
                <!-- Checkbox for transfer status doesn't typically need a placeholder -->
                <input type="checkbox" v-model="education.yesOrNot" true-value="1" false-value="0"/>
              </td>             
              <td>
                <!-- Checkbox for day/night classes doesn't typically need a placeholder -->
                <input type="checkbox" v-model="education.dayOrNot" true-value="1" false-value="0"/>
              </td>
            </tr>
          </table>
        </div>
        <div>
          <button @click="createInfo(education)">추가하기</button>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">경력사항 등록</p>
          <p>이력서에 등록할 수 있는 경력사항입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>회사명</th>
              <th>직무</th>
              <th>시작 일자</th>
              <th>종료 일자</th>
            </tr>
            <tr>
              <td>
                <!-- Bind institutionName using v-model -->
                <input type="text" v-model="career.institutionName" placeholder="회사명을 입력해주세요" />
              </td>
              <td>
                <!-- Bind title using v-model -->
                <input type="text" v-model="career.title" placeholder="직무를 입력해주세요"/>
              </td>
              <td>
                <!-- Bind startDate using v-model and change type to month -->
                <input type="month" v-model="career.startDate" placeholder="YYYY-MM"/>
              </td>
              <td>
                <!-- Bind endDate using v-model and change type to month -->
                <input type="month" v-model="career.endDate" placeholder="YYYY-MM"/>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">대외활동 및 교육사항 등록</p>
          <p>이력서에 등록할 수 있는 자격·면허사항 및 교육사항입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>활동/교육명</th>
              <th>활동/교육 주관 기관명</th>
              <th>활동/교육 시작 일자</th>
              <th>활동/교육 종료 일자</th>
              <th>활동/교육 상세 내용</th>
            </tr>
            <tr>
              <td>
                <!-- Bind title using v-model -->
                <input type="text" v-model="activity.title" placeholder="활동/교육명을 입력해주세요" />
              </td>
              <td>
                <!-- Bind institutionName using v-model -->
                <input type="text" v-model="activity.institutionName" placeholder="활동/교육 주관 기관명을 입력해주세요"/>
              </td>
              <td>
                <!-- Bind startDate using v-model -->
                <input type="date" v-model="activity.startDate" placeholder="YYYY-MM-DD"/>
              </td>
              <td>
                <!-- Bind endDate using v-model -->
                <input type="date" v-model="activity.endDate" placeholder="YYYY-MM-DD"/>
              </td>
              <td>
                <!-- Bind description using v-model -->
                <textarea v-model="activity.description" placeholder="활동/교육 상세 내용을 입력해주세요"></textarea>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">자격증/어학성적 등록</p>
          <p>이력서에 등록할 수 있는 자격·면허사항 및 교육사항입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>자격증/어학성적명</th>
              <th>기관명</th>
              <th>응시 일자/발급 일자</th>
              <th>만료 일자</th>
              <th>자격번호/언어 종류</th>
              <th>자격 등급/어학 등급</th>
            </tr>
            <tr>
              <td>
                <!-- Bind title using v-model -->
                <input type="text" v-model="certificate.title" placeholder="자격증 또는 어학성적명을 입력해주세요" />
              </td>
              <td>
                <!-- Bind institutionName using v-model -->
                <input type="text" v-model="certificate.institutionName" placeholder="기관명을 입력해주세요"/>
              </td>
              <td>
                <!-- Bind startDate using v-model -->
                <input type="date" v-model="certificate.startDate" placeholder="YYYY-MM-DD"/>
              </td>
              <td>
                <!-- Bind endDate using v-model -->
                <input type="date" v-model="certificate.endDate" placeholder="YYYY-MM-DD"/>
              </td>
              <td>
                <!-- Bind description using v-model -->
                <input type="text" v-model="certificate.description" placeholder="자격번호 또는 언어 종류를 입력해주세요"/>
              </td>
              <td>
                <!-- Bind level using v-model -->
                <input type="text" v-model="certificate.level" placeholder="자격 등급 또는 어학 등급을 입력해주세요"/>
              </td>
            </tr>
          </table>
        </div>
      </div>
      </div>
        
      <div class="component">
        <div class="description">
          <p class="title">수상이력 등록</p>
          <p>이력서에 등록할 수 있는 공모전, 대회 등의 수상이력입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>수상명</th>
              <th>기관명</th>
              <th>수상 일자</th>
              <th>수상 상세 내용</th>
            </tr>
            <tr>
              <td>
                <!-- Bind title using v-model -->
                <input type="text" v-model="award.title" placeholder="수상명을 입력해주세요" />
              </td>
              <td>
                <!-- Bind institutionName using v-model -->
                <input type="text" v-model="award.institutionName" placeholder="기관명을 입력해주세요"/>
              </td>
              <td>
                <!-- Bind startDate using v-model -->
                <input type="date" v-model="award.startDate" placeholder="YYYY-MM-DD"/>
              </td>
              <td>
                <!-- Bind description using v-model -->
                <textarea v-model="award.description" placeholder="수상 상세 내용을 입력해주세요"></textarea>
              </td>
            </tr>
          </table>

          <div class="component">
        <div class="description">
          <p class="title">사용 기술 등록</p>
          <p>이력서에 등록할 수 있는 공모전, 대회 등의 수상이력입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th>기술명</th>
              <th>분야</th>
              <th>상세 설명</th>
              <th>숙련도</th>
            </tr>
            <tr>
              <td>
                <!-- Bind title (skill name) using v-model -->
                <input type="text" v-model="skill.title" placeholder="기술명을 입력해주세요 (예: 파이썬)" />
              </td>
              <td>
                <!-- Bind institutionName (field of application) using v-model -->
                <input type="text" v-model="skill.institutionName" placeholder="분야를 입력해주세요 (예: 개발)"/>
              </td>
              <td>
                <!-- Bind description using v-model -->
                <textarea v-model="skill.description" placeholder="상세 설명을 입력해주세요"></textarea>
              </td>
              <td>
                <!-- Bind skillLevel using v-model -->
                <!-- This input assumes skillLevel is a number from 1 to 10 -->
                <select v-model="skill.skillLevel">
                  <option disabled value="">선택해주세요</option>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </select>
              </td>
            </tr>
          </table>


          <div>
            <a class="add-btn click">+</a>
          </div>
        </div>
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
