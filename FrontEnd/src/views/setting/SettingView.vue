<script setup>
import { ref } from "vue";
import { RouterView, RouterLink } from "vue-router";
import ProfileSidebar from "@/views/setting/components/ProfileSidebar.vue";

const isEdit = ref(false);
function toggleEdit() {
  isEdit.value = !isEdit.value;
}
</script>

<template>
  <div class="container">
    <ProfileSidebar />

    <RouterView />
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
