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
        <a @click="toggleEdit" class="edit-btn clickable-btn">설정 변경하기</a>
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




</style>
