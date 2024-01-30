<template>
    <div class="setting-container">
      <div>
        <ProfileSidebar/>
      </div>
      <div class="setting-content">
        <div class="delete-information">삭제되는 정보 설명</div>
        <div><input type="checkbox">동의함</div>
        <div><RouterLink :to="{ name:'DeleteMember' }">취소</RouterLink></div>
      </div>
    </div>
  </template>
  
  <script setup>
  import ProfileSidebar from '@/views/setting/components/ProfileSidebar.vue'
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
      grid-template-rows: 1fr 1fr 1fr;
      border: 1px solid black;
 
    }
  
    .personal-information {
      display: grid;
      grid-template-columns: 1fr 2fr 1fr 2fr 1fr 2fr;
    }
  </style>