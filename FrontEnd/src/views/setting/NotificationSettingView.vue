<template>
    <div class="setting-container">
      <div>
        <ProfileSidebar/>
      </div>
      <div class="setting-content">
        <div>
          <p>나의 일정관리</p>
        </div>
        <div>
          <p>커뮤니티</p>
        </div>
        <div>
          <p>커피챗</p>
        </div>
        <div>
          저장
        </div>
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
      grid-template-rows: 3fr 3fr 3fr 1fr;
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