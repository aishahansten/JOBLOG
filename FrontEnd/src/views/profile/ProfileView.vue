<template>
  <div class="setting-container">
    <div><ProfileSidebar/></div>
    <div class="setting-content">
      <div class="profile-picture">사진 들어갈 자리</div>
      <div class="personal-information">
        <div>이름</div>
        <div><input type="text" placeholder="홍길동"></div>
        <div>성별</div>
        <div><input type="checkbox"></div>
        <div>생년월일</div>
        <div><input type="text" placeholder="1900.01.01"></div>
      </div>
      <div class="education">학력</div>
      <div class="career">경력</div>
      <div class="certification">자격증</div>
    </div>
  </div>
</template>

<script setup>
import ProfileSidebar from '@/views/profile/components/ProfileSidebar.vue'
</script>

<style scoped>
  .setting-container {
    display: grid;
    grid-template-columns: 1fr 3.5fr;
    width: 1344px;
    align-items: stretch;
  }
  
  .setting-content {
    display: grid;
    grid-template-rows: 4fr 1fr 1fr 1fr 1fr 1fr;
    border: 1px solid black;
    padding: 50px;
    box-sizing: border-box;
    justify-content: center;
  }

  .personal-information {
    display: grid;
    grid-template-columns: 1fr 2fr 1fr 2fr 1fr 2fr;
  }
</style>