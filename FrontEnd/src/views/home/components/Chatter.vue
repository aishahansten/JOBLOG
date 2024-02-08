<template>
  <div class="header">
    <div>
      <h1 class="f-weight-t">추천 커피챗 채터</h1>
      <i class="fa-solid fa-angle-right fa-xl"></i>
    </div>
  </div>
  <div class="chatters">
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
    <div class="chatter">
      <div class="profile-pic">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info">
        <h2>닉네임</h2>
        <span>직군·직무</span>
        <span>경력</span>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.header div {
  display: flex;
  align-items: center;
  gap: 10px;
}

.chatters {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-gap: 20px;
}

.chatter {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  padding: 30px 40px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
.info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
}

.profile-pic {
  width: 100px;
  height: 100px;
  overflow: hidden;
  border-radius: 70%;
}
.profile-pic img {
  width: 100px;
  height: 100px;
}
</style>
