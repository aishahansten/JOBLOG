<template>
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
            <input type="text" v-model="activity.title" placeholder="활동/교육명을 입력해주세요" required />
          </td>
          <td>
            <input type="text" v-model="activity.institutionName" placeholder="활동/교육 주관 기관명을 입력해주세요" required/>
          </td>
          <td>
            <input type="date" v-model="activity.startDate" required />
          </td>
          <td>
            <input type="date" v-model="activity.endDate" required />
          </td>
          <td>
            <textarea v-model="activity.description" placeholder="활동/교육 상세 내용을 입력해주세요" required></textarea>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <button @click="createInfo(activity)">추가하기</button>
    </div>
  </div>
</template>


<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from 'vue'
const emit = defineEmits(['refresh'])

const settingResumeStore = useSettingResumeStore()
const createInfo = async(info) => {
    await settingResumeStore.createInfo(info)
    activity.value = {
      userId: "",
      infoCategory: 'ACTIVITY',
      title: "",
      institutionName: "",
      startDate: "",
      endDate: "",
      description: "",
    }
    emit('refresh') 
  }

const activity = ref ({
  userId: "",
  infoCategory: 'ACTIVITY',
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  description: "",
})
</script>

<style lang="scss" scoped>

</style>