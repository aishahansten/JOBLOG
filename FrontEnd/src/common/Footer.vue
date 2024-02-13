<script setup></script>

<template>
  <footer>
    <div class="left">
      <div class="logo">
        <img
          class="footer-logo"
          src="@/assets/img/icon/sub-icon-logo.svg"
          alt=""
        />
        <div>
          <img src="@/assets/img/icon/sub-logo.svg" alt="" />
          <span class="f-size-12 f-weight-t">JOB-1-CHEORIBAN</span>
        </div>
      </div>
      <div class="copyright">
        <span class="f-size-12 f-weight-b"
          >ⓒ 2024. B204. All Rights Reserved.</span
        >
      </div>
    </div>

    <div class="right">
      <div class="developer">
        <span class="name">S0Y0UNG</span>
        <span class="role">BACK</span>
        <span class="role">INFRA</span>
        <span class="role">WEBRTC</span>
      </div>
      <div class="developer">
        <span class="name">22BOTTLE</span>
        <span class="role">BACK</span>
        <span class="role">KAFKA</span>
        <span class="role">CERT</span>
      </div>
      <div class="developer">
        <span class="name">LISA1072</span>
        <span class="role">BACK</span>
        <span class="role">ENTITY</span>
        <span class="role">BATCH</span>
      </div>
      <div class="developer">
        <span class="name">SPARROW</span>
        <span class="role">BACK</span>
        <span class="role">CRUD</span>
        <span class="role">FUNCTION</span>
      </div>
      <div class="developer">
        <span class="name">WONNYBOI</span>
        <span class="role">FRONT</span>
        <span class="role">DATABINDING</span>
        <span class="role">FUNCTION</span>
      </div>
      <div class="developer">
        <span class="name">XION</span>
        <span class="role">FRONT</span>
        <span class="role">DESIGN</span>
        <span class="role">UCC</span>
      </div>
    </div>
  </footer>
</template>

<style scoped>
* {
  color: white;
}
footer {
  display: flex;
  justify-content: space-between;

  width: 100%;
  height: 270px;
  padding: 70px 250px;

  border-top: 1px solid var(--border-gray);
  background-color: var(--main-black);
}

.left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.logo {
  display: flex;
  gap: 20px;
}
.logo .footer-logo {
  height: 110%;
}
.logo div {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: baseline;
  gap: 10px;
  margin-top: 2px;
}
.logo div img {
  height: 18px;
}

.right {
  display: flex;
  justify-content: space-between;
  gap: 70px;
}
.right div {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.right div span {
  font-size: 14px;
  font-weight: 600;
}
.right div .name {
  font-size: 16px;
  font-weight: 900;
}
</style>
