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
.withdraw-content {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  padding: 70px 50px;
  gap: 100px;
}

.withdraw-content section {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.section-title {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.dont-btn {
  width: fit-content;
  padding: 10px 30px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.withdraw-confirm-content {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 70px 50px;
  gap: 30px;
}

.precautions {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 50px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.warning {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

#agreement {
  width: 545px;
  font-size: 16px;
}

.withdraw-confirm-content button {
  background-color: var(--border-gray);
  font-size: 20px;
  color: white;
}
.btn-inactive {
  background-color: var(--border-gray);
}
.btn-active:hover {
  background-color: var(--gray);
  font-size: 20px;
  color: white;
}

#withdraw-complete-view {
  border-left: 1px solid var(--border-gray);
  border-right: 1px solid var(--border-gray);
  height: 1000px;
}
</style>
