<template>
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
              <input type="text" v-model="certificate.title" placeholder="자격증 또는 어학성적명을 입력해주세요" required />
            </td>
            <td>
              <input type="text" v-model="certificate.institutionName" placeholder="기관명을 입력해주세요" required/>
            </td>
            <td>
              <input type="date" v-model="certificate.startDate" placeholder="YYYY-MM-DD" required/>
            </td>
            <td>
              <input type="date" v-model="certificate.endDate" placeholder="YYYY-MM-DD" required/>
            </td>
            <td>
              <input type="text" v-model="certificate.description" placeholder="자격번호 또는 언어 종류를 입력해주세요" required/>
            </td>
            <td>
              <input type="text" v-model="certificate.level" placeholder="자격 등급 또는 어학 등급을 입력해주세요" required/>
            </td>
          </tr>
        </table>
      </div>
    <div>
      <button @click="createInfo(certificate)">추가하기</button>
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

    certificate.value = {
      userId: "",
      infoCategory: 'CERTIFICATE',
      title: "",
      institutionName: "",
      startDate: "",
      endDate: "",
      description: "",
      level: "",
    }
    emit('refresh') 
  }

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
</script>

<style lang="scss" scoped>

</style>