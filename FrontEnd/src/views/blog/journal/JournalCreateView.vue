<template>
  <div class="container">
    <div class="header">
      <div class="img">
        <img src="@/assets/img/blog/diary-img.jpg" alt="" />
      </div>
      <div class="title">
        <a class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          목록으로 돌아가기
        </a>
        <h3>00월 00일의 일기</h3>
        <a
          @click="createJournal"
          class="btn lined-bg f-color-c h-lined-c a-solid-c"
        >
          작성 완료
        </a>
      </div>
    </div>

    <div class="content">
      <textarea
        v-model="newJournal.content"
        class="input focus-lined-c"
        placeholder="일기 내용을 입력해주세요"
        required
      ></textarea>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useJournalStore } from "@/stores/journal";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

const router = useRouter();
const authStore = useAuthStore();
const journalStore = useJournalStore();

const createJournal = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newJournal.value.userId = authStore.userInfo.sub;

    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(
      `${journalStore.API_URL}/diary/register`,
      newJournal.value,
      config
    );
    console.log(response.data);

    newJournal.value = {
      userId: authStore.userInfo.sub,
      content: "",
    };
    router.push("/blog-journal");
  } catch (error) {
    console.log(error);
  }
};

const newJournal = ref({
  userId: "",
  content: "",
});
</script>

<style scoped>
.container {
  padding: 50px 0;
}

.header .img {
  width: 100%;
  height: 200px;
  overflow: hidden;
  border-radius: 10px 10px 0 0;
}
.img img {
  width: 100%;
}

.title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 20px 20px 20px;
  border-radius: 0 0 10px 10px;
  border: 1px solid var(--border-gray);
  border-top: none;
}
.title a {
  display: flex;
  gap: 5px;
}

.content {
  padding: 30px 0;
}

.content textarea {
  width: 100%;
  height: 500px;
  padding: 20px;
  resize: none; /* Prevents resizing */
  font-size: 16px;
}
</style>
