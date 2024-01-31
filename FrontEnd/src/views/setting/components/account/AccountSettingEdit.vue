<script setup></script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">계정 정보 설정</p>
        <p>JOBLOG 계정에 관한 설정을 변경합니다.</p>
      </div>
      <div class="content-function">
        <a href="" class="update-btn clickable-btn">변경 완료</a>
      </div>
    </div>

    <div class="content">
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
            <input type="text" placeholder="닉네임이 없으면 유저번호" />
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

<style scoped>
.container {
  display: flex;
  width: 100%;
  min-height: 980px;
  align-items: stretch;

  border-left: 1px solid var(--border-gray);
  border-right: 1px solid var(--border-gray);
}

.content-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: baseline;
  align-items: baseline;
  box-sizing: border-box;
}

.content-info-space {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding: 70px 50px 20px 50px;
  gap: 10px;

  border-bottom: 1px solid var(--border-gray);
}
.content-info {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.content-info-space .content-title {
  font-size: 32px;
  font-weight: 700;
}
.content-info-space p {
  font-size: 20px;
  font-weight: 200;
}

.content {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: baseline;
  align-items: center;

  padding: 100px 0;
  gap: 50px;
}
form {
  display: flex;
  flex-direction: column;
  gap: 50px;
}
input {
  text-align: center;
  padding: 10px 5px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.profile-pic {
  width: 300px;
  height: 300px;

  border: 1px solid var(--border-gray);
  border-radius: 70%;
  overflow: hidden;
  position: relative;
}
.profile-pic img {
  width: 300px;
  height: 300px;
  object-fit: cover;
  transition: opacity 0.2s ease;
}
.profile-pic .hover-edit-img { 
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
}
.profile-pic:hover .hover-edit-img {
  opacity: 1;
}

.account-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 30px;
}
.account-info div {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;

  font-size: 20px;
}
.account-info div span {
  color: var(--gray);
}
.account-info div p {
  font-weight: 700;
}

.update-btn {
  color: white;
  text-align: center;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  background-color: var(--main-blue);
  border: var(--main-blue) 2px solid;
  padding: 10px 30px;
  border-radius: 8px;
}
</style>
