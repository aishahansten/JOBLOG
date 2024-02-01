<script setup>
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/account-setting.css";
</script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">계정 정보 설정</p>
        <p>JOBLOG 계정에 관한 설정을 변경합니다.</p>
      </div>
      <div class="content-function">
        <a href="" class="update-btn clickable">변경 완료</a>
      </div>
    </div>

    <div class="account-content">
      <form action="">
        <div class="profile-pic">
          <img src="@/assets/img/profile/임시.jpg" alt="" />
          <img
            src="@/assets/img/profile/user-pic-edit.svg"
            class="hover-edit-img"
            alt=""
          />
        </div>
        <div class="account-info">
          <div>
            <span>닉네임</span>
            <input type="text" placeholder="닉네임이 없으면 유저번호" id="nickname-input"/>
          </div>
          <div>
            <span>연결된 계정</span>
            <p>cream23@gmail.com</p>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>