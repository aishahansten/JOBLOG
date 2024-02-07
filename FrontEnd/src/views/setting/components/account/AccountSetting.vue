<script setup>
import { ref } from "vue";
import AccountSettingEdit from "./AccountSettingEdit.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/account-setting.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="content-container" v-if="!isEdit">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">계정 정보 설정</p>
        <p>JOBLOG 계정에 관한 설정을 변경합니다.</p>
      </div>
      <div class="content-function">
        <a @click="toggleEdit" class="edit-btn clickable-solid">설정 변경하기</a>
      </div>
    </div>

    <div class="account-content">
      <div class="profile-pic">
        <img src="@/assets/img/profile/임시.jpg" alt="" />
      </div>
      <div class="account-info">
        <div>
          <span>닉네임</span>
          <p>크림빵23</p>
        </div>
        <div>
          <span>연결된 계정</span>
          <p>cream23@gmail.com</p>
        </div>
      </div>
    </div>
  </div>
  <div class="content-container" v-else>
    <AccountSettingEdit />
  </div>
</template>

<style scoped>
/* 공통 */
.account-content {
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
.nickname-input {
  text-align: center;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

/* 프로필 사진 */

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

/* 프로필 정보 */

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
</style>
