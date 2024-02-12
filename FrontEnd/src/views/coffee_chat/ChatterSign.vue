<template>
  <div>커피챗 채터 신청하기</div>
  <div class="register-container">
    <div class="register-header">
      <div class="profile-header">
        <div class="profile-title">커피챗 프로필 설정</div>
        <div>
          채터와 채티에게 보이는 나의 소개 및 공지를 설정합니다.
          <hr />
        </div>
      </div>
      <div class="register-buttons">편집</div>
    </div>
    <div class="profile">
      <div class="chatter-img-2"></div>
      <div class="chatter-info">
        <div class="info-bold"></div>
        <div class="info-normal">직군:</div>
        <div class="info-normal">경력:</div>
        <div class="info-normal">나의 한마디:</div>
      </div>
    </div>
  </div>
  <div class="">신청 받은 목록</div>
</template>

<script setup></script>

<style scoped>
.chatter-container {
  display: grid;
  grid-template-columns: 1.7fr 1fr;
  border: 1px solid black;
  border-radius: 8px;
  width: 270px;
  height: 198px;
  padding: 10px;
  box-sizing: border-box;
  margin-left: 25px;
}
.chatter-img {
  border: 1px solid black;
  background-color: #e0e0e0; /* Placeholder color, replace with actual image */
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
}

.chatter-img-2 {
  border: 1px solid black;
  height: 200px;
  width: 170px;
}
.chatter-profile div {
  margin-bottom: 5px;
}
.modal {
  position: fixed;
  top: 10%;
  left: 21.5%;
  width: 1100px;
  height: 700px;
  background-color: rgba(0, 0, 0, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: 600px;
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.3);
}

.modal-content textarea {
  resize: none;
  width: 994px;
  height: 200px;
}

.x-button {
  margin-left: auto;
}

.profile {
  display: flex;
  gap: 40px;
  padding: 10px 30px;
  box-sizing: border-box;
}

.chatter-info {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.info-bold {
  font-size: 28px;
  font-weight: bold;
}

.info-normal {
  font-size: 26px;
}

.register-container {
  width: 1344px;
  border: 1px solid black;
}

.register-header {
  display: grid;
  grid-template-columns: 1fr 1fr;
  padding: 40px;
  box-sizing: border-box;
}

.register-buttons {
  margin-left: auto;
}

.profile-title {
  font-size: 26px;
  font-weight: bold;
}
</style>
