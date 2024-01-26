<template>
  <div class="sidebar">
    <div>
        <p>프로필 정보</p>
        <div><RouterLink :to="{ name: 'ProfileSetting' }">기본 정보 설정</RouterLink></div>
        <div>로그인 정보 설정</div>
    </div>
    <div>
        <p>계정 인증</p>
        <div><RouterLink :to="{ name: 'EmployeeRegister' }">현직자 인증</RouterLink></div>
    </div>
    <div>
        <p>기타 설정</p>
        <div><RouterLink :to="{ name: 'NotificationSetting' }">알림 설정</RouterLink></div>
        <div><RouterLink :to="{ name: 'DeleteMember' }">회원 기능</RouterLink></div>
    </div>
    <div>
        <p>로그아웃</p>
    </div>
    </div>
    <div class="setting-content">
    <div></div>
  </div>
</template>

<script setup>

</script>

<style scoped>
  .sidebar {
    display: grid;
    grid-template-rows: 1fr 1fr 1fr 1fr;
    border: 1px solid black;
    align-items: center;
    justify-content: center;
    min-height: 100%;
  }
</style>