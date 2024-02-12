<template>
  <div class="chatter pointer h-solid-lg a-transparent-c" @click="toggleModal">
    <div class="chatter-img">
      <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
    </div>
    <div class="chatter-info">
      <h3>{{ item.user_id }}</h3>
      <span>{{ item.job }}</span>
      <span>{{ item.career }}차</span>
    </div>
  </div>

  <div v-if="showModal" class="modal">
    <div class="profile">
      <a @click="toggleModal" class="exit-btn"
        ><i class="fa-solid fa-xmark fa-xl"></i
      ></a>
      <div class="info">
        <h1 class="title">채터 정보</h1>
        <div class="info-img">
          <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
        </div>
        <div class="info-txt">
          <h1>{{ item.user_id }}</h1>
          <div>
            <i class="fa-solid fa-briefcase"></i>
            <span>{{ item.job }}</span>
          </div>
          <div>
            <i class="fa-solid fa-building-user"></i>
            <span>경력 {{ item.career }}차</span>
          </div>
          <div>
            <i class="fa-solid fa-bullhorn"></i>
            <div>{{ item.description }}</div>
          </div>
        </div>
      </div>
      <div class="book" v-if="!showBook">
        <a @click="toggleBook" class="btn lined-c f-color-c h-solid-c a-bright"
          >커피챗 신청하기</a
        >
      </div>
      <div class="book" v-else>
        <a @click="toggleBook" class="btn lined-bg h-solid-g a-bright">
          돌아가기</a
        >

        <div>예약 신청 폼</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";

defineProps({
  item: Object,
});

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

onMounted(() => {
  window.addEventListener("keydown", handleEscapeKeyPress);
});

onUnmounted(() => {
  window.removeEventListener("keydown", handleEscapeKeyPress);
});
// -----------------------------------------------------------
</script>

<style scoped>
.chatter {
  display: flex;
  justify-content: baseline;
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
}
.chatter-info span {
  color: var(--gray);
}

/* modal */
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
  display: grid;
  grid-template-columns: 1fr 20fr;
  align-items: center;
  font-size: 20px;
}

.book {
  display: flex;
  flex-direction: column;
  gap: 30px;

  width: 100%;
}
</style>
