<template>
  <div class="resume-container">
    <div class="resume-upper">
      <div class="resume-picture" @click="triggerFileInput">
        <img v-if="imageUrl" :src="imageUrl" alt="Profile Picture Preview" class="profileImage">
      </div>
      <input type="file" ref="fileInput" @change="handleFileUpload" style="display:none;">
      <div class="personal-info">
        <div class="name">
          <div class="info-title">
            이름
          </div>
          <div>
            <input type="text">
          </div>
          <div class="info-title">
            영문
          </div>
          <div>
            <input type="text">
          </div>
        </div>
        <div class="reg-number">
          <div class="info-title">
            주민등록번호
          </div>
          <div>
            <input type="text">
          </div>
        </div>
        <div class="numbers">
          <div class="info-title">
            H.P
          </div>
          <div><input type="text"></div>
          <div class="info-title">
            Tel.
          </div>
          <div><input type="text"></div>
        </div>
        <div class="email">
          <div class="info-title">
            이메일
          </div>
          <div>
            <input type="text">
          </div>
        </div>
        <div class="address">
          <div class="info-title">
            주소
          </div>
          <div>
            <input type="text">
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="section-title">학력사항</div>
        <div>
          <table>
            <colgroup>
              <col style="width: 25%;">
              <col style="width: 30%;">
              <col style="width: 30%;">
              <col style="width: 15%;">
            </colgroup>
            <tbody>
              <tr>
                <td>재학기간</td>
                <td>학교</td>
                <td>전공</td>
                <td>구분</td>
              </tr>
            </tbody>
          </table>
        <hr>
      </div>
    </div>
    <div class="section">
      <div class="section-title">경력사항</div>
      <div>
        <table>
          <colgroup>
            <col style="width: 25%;">
            <col style="width: 40%;">
            <col style="width: 20%;">
            <col style="width: 15%;">
          </colgroup>
          <tbody>
            <tr>
              <td>근무기간</td>
              <td>회사/부서</td>
              <td>직위</td>
              <td>담당업무</td>
            </tr>
          </tbody>
        </table>
        <hr>
      </div>
    </div>  
    <div class="section">
      <div class="section-title">교육</div>
      <hr class="thick">
    </div>
    <div class="section">
      <div class="section-title">대외활동</div>
      <hr class="thick">
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const fileInput = ref(null);
const imageUrl = ref(null);

const triggerFileInput = () => {
  fileInput.value.click();
};

const handleFileUpload = event => {
  const file = event.target.files[0];
  if (file && file.type.startsWith('image/')) {
    const reader = new FileReader();
    reader.onload = e => {
      imageUrl.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};
</script>
<style scoped>
@import "@/views/blog/application/css/ResumeCreateView.css" 

</style>
