<template>
  <div class="chatter pointer h-solid-lg a-transparent-c" @click="toggleModal">
    <div class="chatter-img">
      <img src="@/assets/img/profile/default-user-pic.jpg" alt="" v-if="!item.amazonS3FileUrl" />
      <img :src="item.amazonS3FileUrl" alt="" v-else />
    </div>
    <div class="chatter-info">
      <h3>직군: {{ item.job }}</h3>
      <span>경력: {{ item.career }}</span>
      <span>{{ item.description }}</span>
    </div>
  </div>

  <div v-if="showModal" class="modal">
    <div class="profile">
      <a @click="toggleModal" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
      <div class="info">
        <h1 class="title">채터 정보</h1>
        <div class="info-division">
          <div class="info-img">
            <img src="@/assets/img/profile/default-user-pic.jpg" alt="" v-if="!item.amazonS3FileUrl" />
            <img :src="item.amazonS3FileUrl" alt="" v-else />
          </div>
          <div class="info-txt">
            <h1>{{ item.job }}</h1>
            <div>
              <i class="fa-solid fa-building-user"></i>
              <span>경력: {{ item.career }}</span>
            </div>
            <div>
              <i class="fa-solid fa-bullhorn"></i>
              <div class="info-description">
                {{ item.description }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="book" v-if="!showBook">
        <a @click="toggleBook" class="btn lined-c f-color-c h-solid-c a-bright">신청서 작성하기</a>
      </div>
      <div class="book" v-else>
        <a @click="toggleBook" class="btn lined-bg h-solid-g a-bright"> 돌아가기</a>

        <div class="chatCall-item">
          <div>상담 시간 신청</div>
          <div>
            <input type="datetime-local" class="datetime-input" v-model="chatCall.startDate" />
          </div>
          <div>상담 받고 싶은 내용/분야</div>
          <div>
            <textarea v-model="chatCall.consultField" maxlength="250" class="textarea"></textarea>
          </div>
        </div>
        <div>{{ chatCall.consultField.length }}자 / 250자</div>
        <a @click="callChat()" class="btn lined-c f-color-c h-solid-c a-bright">커피챗 신청하기</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { useCoffeeStore } from "@/stores/coffeeChat";
const coffeeStore = useCoffeeStore();
import { useAuthStore } from "@/stores/auth";
const authStore = useAuthStore();

const props = defineProps({
  item: Object,
});

const chatCall = ref({
  chatterId: props.item.userId,
  chatteeId: authStore.userInfo.sub,
  consultField: "",
  startDate: "",
});

const callChat = async () => {
  try {
    await coffeeStore.createChat(chatCall.value);
  } catch (error) {
    console.error("Failed to create chat:", error);
  }
};

// 모달을 보이는 함수
const showModal = ref(false);
const toggleModal = () => {
  showModal.value = !showModal.value;
};

const showBook = ref(false);
const toggleBook = () => {
  showBook.value = !showBook.value;
};
//-----------------------------------

// 모달 esc 클릭을 위한 함수
const handleEscapeKeyPress = (event) => {
  if (event.key === "Escape") {
    showModal.value = false;
  }
};

onMounted(async () => {
  await authStore.updateUserInfoFromToken();

  window.addEventListener("keydown", handleEscapeKeyPress);
});

onUnmounted(() => {
  window.removeEventListener("keydown", handleEscapeKeyPress);
});
// -----------------------------------------------------------
</script>

<style scoped>
.chatter {
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  gap: 20px;
  padding: 20px;
  border-radius: 20px;
}
.chatter-img {
  display: flex;
  align-items: center;
  justify-content: center;

  width: 150px;
  height: 150px;
  overflow: hidden;

  border: 1px solid var(--border-gray);
  border-radius: 70%;
}
.chatter-img img {
  width: 100%;
  object-fit: cover;
}
.chatter-info {
  display: flex;
  flex-direction: column;
  gap: 5px;
  max-height: 190px;
  overflow-y: hidden;
  text-overflow: ellipsis;
}
.chatter-info span {
  color: var(--gray);
}

.modal {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: center;

  width: 1000px;
  height: 1000px;
  padding: 50px;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 3;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
  background-color: white;
}

.profile {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: center;

  width: 100%;
  height: 100%;
  position: relative;
}
.exit-btn {
  position: absolute;
  top: 40px;
  right: 40px;
}

.info {
  display: flex;
  flex-direction: column;
  gap: 20px;

  width: 100%;
  padding: 30px 0;
}

.info-division {
  display: grid;
  grid-template-columns: 1fr 3fr;
}

.info .title {
  text-align: center;
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
}
.info-txt div {
  display: flex;
  align-items: center;
  font-size: 20px;
  gap: 20px;
}

.book {
  display: flex;
  flex-direction: column;
  gap: 30px;

  width: 100%;
}

.textarea {
  height: 200px;
  width: 898px;
  resize: none;
  border: 1px solid rgba(0, 0, 0, 0.207);
  border-radius: 8px;
  padding: 10px;
  box-sizing: border-box;
  font-size: 18pt;
}

.chatCall-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.datetime-input {
  width: 898px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 20px;
  color: #333;
  background-color: #fff;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  outline: none;
}

.datetime-input:focus {
  border-color: #4a90e2;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(74, 144, 226, 0.6);
}
</style>
