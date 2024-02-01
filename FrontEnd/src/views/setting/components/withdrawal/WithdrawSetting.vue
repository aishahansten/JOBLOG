<script setup>
import { ref } from "vue";
import WithdrawConfirm from "./WithdrawConfirm.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/withdraw-setting.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="content-container" v-if="!isEdit">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">회원 기능</p>
        <p>회원 관련 기능을 설정합니다.</p>
      </div>
    </div>

    <div class="withdraw-content">
      <section>
        <div class="section-title">
          <h2>회원 탈퇴</h2>
          <p>JOBLOG 사이트 내 본인의 계정 관련 정보를 모두 삭제합니다.</p>
        </div>
        <a class="dont-btn clickable-txt" @click="toggleEdit">회원 탈퇴하기</a>
      </section>
    </div>
  </div>
    <WithdrawConfirm v-else/>
</template>

<style scoped>
#account-setting {
  font-weight: 700;
}

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

.profile-pic {
  width: 300px;
  height: 300px;

  border: 1px solid var(--border-gray);
  border-radius: 70%;
  overflow: hidden;
}
.profile-pic img {
  width: 300px;
  height: 300px;
  object-fit: cover;
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

.edit-btn {
  color: var(--main-blue);
  text-align: center;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
  background-color: white;
  border: var(--main-blue) 2px solid;
  padding: 10px 30px;
  border-radius: 8px;
}
</style>
