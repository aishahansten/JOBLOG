<script setup>
import { ref } from "vue";
import ResumeSettingEdit from "./ResumeSettingEdit.vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/resume-setting.css";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="content-container">
    <ResumeSettingEdit />
  </div>
</template>

<style scoped>
/* 공통 */

.resume-content {
  display: flex;
  flex-direction: column;
  align-items: baseline;
  padding: 70px 50px;
  gap: 100px;
}
.component {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 30px;
}

.description {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.description .title {
  font-size: 24px;
  font-weight: 600;
}
.description p {
  font-size: 16px;
  font-weight: 200;
}

.tag {
  font-size: 16px;
  font-weight: 600;
  color: var(--gray);
}
.data {
  font-size: 20px;
  font-weight: 500;
}

/* 취업사진 등록 */

#half {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-between;
}

.identify-pic-space {
  display: flex;
  align-items: end;
  gap: 10px;
}
.input-pic-guide {
  display: flex;
  flex-direction: column;
  align-items: end;
  gap: 10px;
}

.identify-pic {
  width: 150px;
  height: 200px;

  border: 1px solid var(--border-gray);
  border-radius: 10px;
  overflow: hidden;
}
.identify-pic img {
  width: 150px;
  height: 200px;
  object-fit: cover;
}

/* 인적사항 등록 */

.info-space {
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 10px;
}
.info-space div {
  display: flex;
  align-items: center;
  gap: 70px;
  align-self: stretch;
}
.user-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  flex: 1 0 0;
}

.user-info input {
  text-align: baseline;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
input#long {
  width: 500px;
}
input#user-radio {
  font-size: 20px;
}

/* 기타 등록 */

table {
  width: 100%;
  table-layout: fixed;
  border-spacing: 0 10px;
}
th {
  text-align: left;
  padding: 10px 0;
}

td div {
  display: flex;
  gap: 5px;
}
tr input {
  width: 95%;
  padding: 10px;
  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.add-btn {
  display: flex;
  width: 98.5%;
  justify-content: center;
  padding: 10px;
  background-color: var(--border-gray);
  border-radius: 10px;
  color: white;
  font-size: 20px;
  font-weight: 800;
}

.click:hover {
  filter: brightness(107%);
  transition: 0.1s;
}
</style>
