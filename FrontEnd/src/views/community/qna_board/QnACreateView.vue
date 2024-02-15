<script setup>
import axios from "axios";
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useCommunityStore } from "@/stores/community";
import { useAuthStore } from "@/stores/auth";

const router = useRouter();
const authStore = useAuthStore();
const communityStore = useCommunityStore();

const createPost = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newQuestion.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(`${communityStore.API_URL}/community/register`, newQuestion.value, config);
    newQuestion.value = {
      userId: authStore.userInfo.sub,
      category: "QNA",
      title: "",
      content: "",
    };
    router.push("/qna-board");
  } catch (error) {
    console.error(error);
  }
};

const newQuestion = ref({
  userId: "",
  category: "QNA",
  title: "",
  content: "",
});

const isTitleValid = computed(() => newQuestion.value.title.length >= 4);

function toBoard() {
  router.push("/qna-board");
}
</script>

<template>
  <div class="container">
    <div class="header">
      <a @click="toBoard" class="btn f-color-w h-solid-g">
        <i class="fa-solid fa-caret-left"></i>
        게시판으로 돌아가기
      </a>
      <h3>질문 작성하기</h3>
      <button @click="createPost" :disabled="!isTitleValid" class="btn lined-bg f-color-c h-lined-c a-solid-c">
        작성 완료
      </button>
    </div>

    <div class="content">
      <div class="title">
        <i class="fa-solid fa-q fa-2xl"></i>
        <input
          type="text"
          class="input-simple focus-lined-c"
          v-model="newQuestion.title"
          placeholder="무엇이 궁금하신가요?"
          required
        />
      </div>
      <textarea
        class="input focus-lined-c"
        v-model="newQuestion.content"
        placeholder="질문 내용을 입력해주세요"
        required
      ></textarea>
    </div>
  </div>
</template>

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

.content {
  padding: 30px 0;
}

.title {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 20px 0;
}
.title input {
  width: 100%;
  font-size: 28px;
}

.content textarea {
  width: 100%;
  height: 500px;
  padding: 20px;
  resize: none;
  font-size: 16px;
}

.submit {
  margin-left: auto;
}
</style>
