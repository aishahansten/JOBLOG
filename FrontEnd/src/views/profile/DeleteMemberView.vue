<template>
    <div class="setting-container">
      <div>
        <ProfileSidebar/>
      </div>
      <div class="setting-content">
        <p>진짜 정말 탈퇴 해?</p>
        <RouterLink :to="{ name: 'DeleteCheck' }"><button>really?</button></RouterLink>
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
      display: flex;
      border: 1px solid black;
      justify-content: center;
      align-items: center;
      flex-direction: column;
    }
  
    .personal-information {
      display: grid;
      grid-template-columns: 1fr 2fr 1fr 2fr 1fr 2fr;
    }
  </style>