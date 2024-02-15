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
      <div>
        <a class="btn lined-bg f-color-c h-lined-c a-solid-c" @click="updateChatterProfile"> 편집 </a>
        <a class="btn lined-bg f-color-c h-lined-c a-solid-c" @click="chatterStatus"> 활성화하기 </a>
      </div>
    </div>

    <div class="profile">
      <div class="info-img">
        <img src="@/assets/img/profile/default-user-pic.jpg" alt="" v-if="!userInfo.amazonS3FileUrl" />
        <img :src="userInfo.amazonS3FileUrl" v-else />
      </div>
      <div class="info-txt">
        <h1>닉네임</h1>
        <div class="info">
          <i class="fa-solid fa-briefcase"></i>
          <span
            ><input class="input" type="text" v-model="currChat.job" placeholder="현재 직군을 입력해주세요" />
          </span>
        </div>
        <div class="info">
          <i class="fa-solid fa-building-user"></i>
          <span><input class="input" type="text" v-model="currChat.career" placeholder="경력을 입력해주세요" /></span>
        </div>
        <div class="info">
          <i class="fa-solid fa-bullhorn"></i>
          <div class="description">
            <textarea
              class="textarea"
              v-model="currChat.description"
              placeholder="간단한 설명, 가능한 시간등을 입력해주세요"
            ></textarea>
          </div>
        </div>
      </div>
    </div>

    <div class="requests">
      <div class="title">
        <h2>받은 신청 목록</h2>
      </div>
      <div class="list" v-for="chat in chatCalls" :key="chat.chatId">
        <div class="request">
          <div class="left">
            <div class="img">
              <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
            </div>
            <div class="info">
              <p>
                {{ chat.consultField }}
              </p>
            </div>
          </div>
          <div class="right">
            <div class="time">
              <i class="fa-regular fa-clock"></i>
              <span>{{ chat.startDate }}</span>
            </div>
            <div class="select" v-if="chat.acceptOrNot == false">
              <a class="btn-s lined-c h-solid-c a-bright" @click="acceptChat(chat.chatId)">수락</a>
              <a class="btn-s lined-bg h-solid-g a-dark" @click="declineChat(chat.chatId)">거절</a>
            </div>
            <div v-else>수락되었습니다</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "@/stores/auth";
const authStore = useAuthStore();
import { useCoffeeStore } from "@/stores/coffeeChat";
const coffeeStore = useCoffeeStore();
import { useSettingResumeStore } from "@/stores/settingResume";
const settingResumeStore = useSettingResumeStore();

const userInfo = ref({});

const currChat = ref({
  id: "",
  userId: "",
  job: "",
  career: "",
  description: "",
});

const chatCalls = ref([]);

const updateChatterProfile = async () => {
  await authStore.updateChatterProfile(currChat);
  alert("변경되었습니다.");
};

const chatterStatus = async () => {
  await authStore.chatterStatus();
  alert("변경되었습니다.");
};

const acceptChat = async (id) => {
  const isConfirm = confirm("수락하시겠습니까?");
  if (isConfirm) {
    await coffeeStore.acceptChat(id);
    alert("수락되었습니다.");
  }
  return;
};
const declineChat = async (id) => {
  const isConfirm = confirm("거절하시겠습니까?");
  if (isConfirm) {
    await coffeeStore.declineChat(id);
    alert("거절되었습니다..");
  }
  return;
};

onMounted(async () => {
  await authStore.updateUserInfoFromToken();
  await authStore.getChatter(authStore.userInfo.sub);
  currChat.value = authStore.chatter;
  await settingResumeStore.getUserInfo();
  userInfo.value = settingResumeStore.userInfo;
  await coffeeStore.getChat();
  const allChatCalls = coffeeStore.chats.filter((chat) => chat.chatterId == authStore.userInfo.sub);
  chatCalls.value = allChatCalls;
});
</script>

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

.input {
  height: 30px;
  width: 540px;
}

.textarea {
  height: 200px;
  width: 540px;
  resize: none;
  border: 1px solid rgba(0, 0, 0, 0.207);
  border-radius: 8px;
  padding: 10px;
  box-sizing: border-box;
}
</style>
