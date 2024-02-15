<script setup>
import { ref } from "vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/withdraw-setting.css";

const inputment = ref("");
const agreement = "위 내용을 모두 숙지하였으며, 모든 사항에 동의합니다.";
function checkAgreement() {
  isBtnActive.value = inputment.value === agreement;
}

const isBtnActive = ref(false);
</script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">회원 기능</p>
        <p>회원 관련 기능을 설정합니다.</p>
      </div>
    </div>

    <div class="withdraw-confirm-content">
      <h2>회원 탈퇴 시 주의사항</h2>

      <ul class="precautions">
        <li>일주일 후</li>
        <li>사이트 내에 저장한 모든 개인 일정 정보가 삭제됩니다.</li>
        <li>사이트 내에 저장한 모든 개인 계정 및 이력 정보가 삭제됩니다.</li>
        <li>사이트 내에 사용자가 생성한 모든 이력서 및 자소서 정보가 삭제됩니다.</li>
      </ul>
      <div class="warning">
        <p>탈퇴하시려면 아래에 보이는 문구를 입력하세요.</p>
        <p style="font-weight: bold;">위 내용을 모두 숙지하였으며, 모든 사항에 동의합니다.</p>
        <input
          type="text"
          v-model="inputment"
          @input="checkAgreement"
          placeholder="위 내용을 모두 숙지하였으며, 모든 사항에 동의합니다."
          id="agreement"
        />
      </div>
      <RouterLink
        v-if="isBtnActive"
        :to="{ name: 'WithdrawCompleteView' }"
        custom
        v-slot="{ navigate }"
      >
        <button 
          class="basic-btn btn-active" 
          @click="navigate"
        >
          회원 탈퇴하기
        </button>
      </RouterLink>
      <button v-else class="basic-btn btn-inactive" disabled>
        회원 탈퇴하기
      </button>
    </div>
  </div>
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
