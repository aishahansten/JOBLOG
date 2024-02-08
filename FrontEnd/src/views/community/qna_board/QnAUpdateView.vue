<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const route = useRoute();
const router = useRouter();

const loading = ref(false);
const post = ref(null);
console.log(post);

async function fetchPostAndComments() {
  try {
    await authStore.updateUserInfoFromToken();
    const response = await axios.get(
      `${authStore.API_URL}/community/detail/${route.params.id}`
    );

    return {
      post: response.data.postResponseDto,
    };
  } catch (error) {
    console.error("불러오기 실패: ", error);
    return { post: null, comments: null }; // Return nulls or defaults on error
  }
}

const savePost = async () => {
  try {
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };
    await authStore.updateUserInfoFromToken();
    const editContent = {
      postId: post.value.postId,
      userId: authStore.userInfo.sub,
      title: post.value.title,
      content: post.value.content,
      category: "QNA",
    };
    console.log(post.value.postId);
    const res = await axios.patch(
      `${authStore.API_URL}/community/update`,
      editContent,
      config
    );
    console.log(res.data);
    router.push({ name: "QnADetail", params: { id: post.value.postId } });
  } catch (error) {
    console.error("업데이트 실패: ", error);
  }
};

onMounted(async () => {
  const { post: fetchedPost } = await fetchPostAndComments();
  post.value = fetchedPost;
  loading.value = true;
  authStore.updateUserInfoFromToken();
});

function toBack() {
  router.back();
}
</script>

<template>
  <div v-if="!loading"></div>
  <div v-else>
    <div class="container">
      <div class="header">
        <a @click="toBack" class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          수정 취소
        </a>
        <h3>질문 수정하기</h3>
        <a
          @click="savePost"
          :disabled="!isTitleValid"
          class="btn lined-bg f-color-c h-lined-c a-solid-c"
        >
          수정 완료
        </a>
      </div>

      <div class="content">
        <div class="title" v-if="post">
          <i class="fa-solid fa-q fa-2xl"></i>
          <input
            type="text"
            class="input-simple focus-lined-c"
            v-model="post.title"
            required
          />
        </div>
        <textarea
          class="input focus-lined-c"
          v-model="post.content"
          required
        ></textarea>
      </div>
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
  resize: none; /* Prevents resizing */
  font-size: 16px;
}

.submit {
  margin-left: auto;
}
</style>
