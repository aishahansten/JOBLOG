<template>
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
            <input type="text" v-model="education.institutionName" placeholder="예제) 짱짱대학교" required />
          </td>
          <td>
            <input type="text" v-model="education.title" placeholder="예제) 경영학과 / 학사" required/>
          </td>
          <td>
            <input type="date" v-model="education.startDate" required />
          </td>
          <td>
            <input type="date" v-model="education.endDate" required />
          </td>
          <td>
            <select v-model="education.graduationStatus" required>
              <option disabled value="">선택해주세요</option>
              <option value="0">재학 중</option>
              <option value="1">졸업 예정</option>
              <option value="2">졸업</option>
            </select>
          </td>
          <td>
            <!-- Assuming you want to make this required, consider how you handle the "required" logic for checkboxes -->
            <input type="checkbox" v-model="education.yesOrNot" true-value="1" false-value="0"/>
          </td>             
          <td>
            <!-- Similarly, for day/night, consider how "required" applies -->
            <input type="checkbox" v-model="education.dayOrNot" true-value="1" false-value="0"/>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <button @click="createInfo(education)">추가하기</button>
    </div>
  </div>
</template>


<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from 'vue'

const settingResumeStore = useSettingResumeStore()
const createInfo = function(info) {
    settingResumeStore.createInfo(info)
    
    education.value = {
      userId: "",
      infoCategory: 'EDUCATION',
      title: "",
      institutionName:"",
      startDate: "",
      endDate: "",
      graduationStatus: 0,
      yesOrNot: 0,
      dayOrNight: 0,
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
</script>

<style scoped>

</style>