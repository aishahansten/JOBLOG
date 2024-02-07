<template>
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
            <!-- Bind title (skill name) using v-model with required attribute -->
            <input type="text" v-model="skill.title" placeholder="기술명을 입력해주세요 (예: 파이썬)" required />
          </td>
          <td>
            <!-- Bind institutionName (field of application) using v-model with required attribute -->
            <input type="text" v-model="skill.institutionName" placeholder="분야를 입력해주세요 (예: 개발)" required/>
          </td>
          <td>
            <!-- Bind description using v-model with required attribute -->
            <textarea v-model="skill.description" placeholder="상세 설명을 입력해주세요" required></textarea>
          </td>
          <td>
            <!-- Bind skillLevel using v-model with required attribute -->
            <!-- This input assumes skillLevel is a number from 1 to 10 -->
            <select v-model="skill.skillLevel" required>
              <option disabled value="">선택해주세요</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <!-- Add options up to 10 as needed -->
            </select>
          </td>
        </tr>
      </table>

      <div>
        <button @click="createInfo(skill)">추가하기</button>
      </div>
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

  skill.value = {
    userId: "",
    infoCategory: 'SKILL',
    title: "",
    institutionName: "",
    description: "",
    skillLevel: 1,
  }
  emit('refresh') 
  }


const skill = ref ({
  userId: "",
  infoCategory: 'SKILL',
  title: "",
  institutionName: "",
  description: "",
  skillLevel: 1,
})
</script>

<style lang="scss" scoped>

</style>