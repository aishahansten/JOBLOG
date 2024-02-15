<template>
  <div class="container">
    <div class="header">
      <RouterView :to="{ name: 'Coffee' }">
        <a class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          이전으로
        </a>
      </RouterView>
      <h3>커피챗 프로필 설정</h3>
      <a class="btn lined-bg f-color-c h-lined-c a-solid-c"> 편집 </a>
    </div>

    <div class="profile">
      <div class="info-img">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
      </div>
      <div class="info-txt">
        <h1>닉네임</h1>
        <div class="info">
          <i class="fa-solid fa-briefcase"></i>
          <span>직군</span>
        </div>
        <div class="info">
          <i class="fa-solid fa-building-user"></i>
          <span>경력 n년차</span>
        </div>
        <div class="info">
          <i class="fa-solid fa-bullhorn"></i>
          <div class="description">
            평일 오후 8시부터 프리합니다! 개발직군 FE쪽 종사하고 있어요, 관련 분야에 관심있다면 언제든 이야기 나눠요~
          </div>
        </div>
      </div>
    </div>

    <div class="requests">
      <div class="title">
        <h2>받은 신청 목록</h2>
      </div>
      <div class="list">
        <div class="request">
          <div class="left">
            <div class="img">
              <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
            </div>
            <div class="info">
              <h3>닉네임</h3>
              <p>
                신청 메시지 예를 들면 안녕하세요! FE 분야 취준 시 포폴 유의점에 대해 알고싶어서 신청합니다..! 부디ㅠ
              </p>
            </div>
          </div>
          <div class="right">
            <div class="time">
              <i class="fa-regular fa-clock"></i>
              <span>00:00</span>
            </div>
            <div class="select">
              <a class="btn-s lined-c h-solid-c a-bright">수락</a>
              <a class="btn-s lined-bg h-solid-g a-dark">거절</a>
            </div>
          </div>
        </div>
        <div class="request">
          <div class="left">
            <div class="img">
              <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
            </div>
            <div class="info">
              <h3>닉네임</h3>
              <p>
                신청 메시지 예를 들면 안녕하세요! FE 분야 취준 시 포폴 유의점에 대해 알고싶어서 신청합니다..! 부디ㅠ
              </p>
            </div>
          </div>
          <div class="right">
            <div class="time">
              <i class="fa-regular fa-clock"></i>
              <span>00:00</span>
            </div>
            <div class="select">
              <a class="btn-s lined-c h-solid-c a-bright">수락</a>
              <a class="btn-s lined-bg h-solid-g a-dark">거절</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 20px 20px 20px;
  border-radius: 0 0 10px 10px;
  border: 1px solid var(--border-gray);
  border-top: none;
}
.header a {
  display: flex;
  gap: 5px;
}

.profile {
  display: flex;
  justify-content: center;
  gap: 50px;

  width: 100%;
  padding: 50px 0;
}

.info-img {
  width: 200px;
  height: 200px;
  overflow: hidden;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
}
.info-img img {
  width: 100%;
  object-fit: cover;
}

.info-txt {
  display: flex;
  flex-direction: column;
  gap: 20px;
  width: 600px;
}
.info-txt .info {
  display: grid;
  grid-template-columns: 1fr 10fr;
  align-items: start;
  font-size: 20px;
}
.info-txt .description {
  line-height: 140%;
}

.requests {
  min-height: 400px;
  padding: 40px 50px;

  border: 1px solid var(--border-gray);
  border-radius: 10px 10px 0 0;
  border-bottom: none;
}
.requests .title {
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}

.list {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 30px 0;
}
.request {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.left {
  display: flex;
  align-items: center;
  gap: 20px;
}
.left .img {
  width: 70px;
  height: 70px;
  overflow: hidden;
  border-radius: 70%;
}
.left .img img {
  width: 100%;
  object-fit: cover;
}
.left .info {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.right {
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 30px;
  width: 300px;
}
.right .time {
  display: flex;
  align-items: center;
  gap: 5px;
}
.right .select {
  display: flex;
  gap: 5px;
}
</style>
