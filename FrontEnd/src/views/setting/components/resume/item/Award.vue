<template>
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
            <input type="text" v-model="award.title" placeholder="수상명을 입력해주세요" required />
          </td>
          <td>
            <input type="text" v-model="award.institutionName" placeholder="기관명을 입력해주세요" required/>
          </td>
          <td>
            <input type="date" v-model="award.startDate" placeholder="YYYY-MM-DD" required/>
          </td>
          <td>
            <textarea v-model="award.description" placeholder="수상 상세 내용을 입력해주세요" required></textarea>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <button @click="createInfo(award)">추가하기</button>
    </div>
  </div>
</template>


<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from 'vue'

const settingResumeStore = useSettingResumeStore()
const createInfo = function(info) {
    settingResumeStore.createInfo(info)
    award.value = {
      userId: "",
      infoCategory: 'AWARD',
      title: "",
      institutionName: "",
      startDate: "",
      description: "",
    }
  }

const award = ref ({
  userId: "",
  infoCategory: 'AWARD',
  title: "",
  institutionName: "",
  startDate: "",
  description: "",
})
</script>

<style scoped>

</style>