<template>
  <aside class="setting-menu">
    <div class="menu-profile">
      <p>프로필 정보</p>
      <div>
        <RouterLink :to="{ name: 'AccountSetting' }"
          ><a id="account-setting" class="clickable-txt"
            >계정 정보 설정</a
          ></RouterLink
        >
        <a id="resume-info-setting" href="" class="clickable-txt"
          >이력 정보 설정</a
        >
      </div>
    </div>
    <div class="auth-menu">
      <p>계정 인증</p>
      <div>
        <RouterLink :to="{ name: 'EmployeeRegister' }"
          ><a id="employee-register" class="clickable-txt"
            >현직자 인증</a
          ></RouterLink
        >
      </div>
    </div>
    <div class="menu-other">
      <p>기타 설정</p>
      <div>
        <RouterLink :to="{ name: 'NotificationSetting' }"
          ><a id="notification-setting" class="clickable-txt"
            >알림 설정</a
          ></RouterLink
        >
        <RouterLink :to="{ name: 'DeleteMember' }"
          ><a id="delete-member" class="clickable-txt">회원 기능</a></RouterLink
        >
      </div>
    </div>
    <div class="menu-logout">
      <a id="logout" href="" class="clickable-txt">로그아웃</a>
    </div>
  </aside>
</template>

<script setup></script>

<style scoped>
aside {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  width: 300px;
  min-height: 100%;
  gap: 100px;

  box-sizing: border-box;
  padding: 70px 50px;

  border-right: 1px solid var(--border-gray);
}
aside * {
  font-size: 20px;
}

.setting-menu {
}
.setting-menu div {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.setting-menu div p {
  margin-bottom: 10px;
  color: var(--gray);
  font-weight: 600;
}

.clickable-txt:hover {
  font-weight: 600;
  transition: 0.1s;
}
</style>
