<template>
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
            <input type="text" v-model="career.institutionName" placeholder="회사명을 입력해주세요" required />
          </td>
          <td>
            <input type="text" v-model="career.title" placeholder="직무를 입력해주세요" required/>
          </td>
          <td>
            <input type="date" v-model="career.startDate" required />
          </td>
          <td>
            <input type="date" v-model="career.endDate" required />
          </td>
        </tr>
        </table>
    </div>
    <div>
      <button @click="createInfo(career)">추가하기</button>
    </div>
  </div>
</template>

<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from 'vue'

const settingResumeStore = useSettingResumeStore()
const createInfo = function(info) {
  settingResumeStore.createInfo(info);

  career.value = {
    userId: "",
    infoCategory: 'CAREER',
    title: "",
    institutionName: "",
    startDate: "",
    endDate: "",
  };
}

const career = ref({
  userId: "",
  infoCategory: 'CAREER',
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
})
</script>

<style scoped>

</style>