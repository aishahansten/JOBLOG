
<template>
  <div v-if="!loading"></div>
  <div v-else>
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
          <a class="btn lined-bg f-color-c h-lined-c a-solid-c"> 작성 완료 </a>
        </div>

        <a @click="toBack" class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          수정 취소
        </a>
        <h3>질문 수정하기</h3>
        <a
          @click="saveJournal"
          class="btn lined-bg f-color-c h-lined-c a-solid-c"
        >
          수정 완료
        </a>

      </div>

      <div class="content">
        <div class="title" v-if="journal">
          <i class="fa-solid fa-q fa-2xl"></i>
          <input
            type="text"
            class="input-simple focus-lined-c"
            v-model="journal.content"
            required
          />
        </div>
        <textarea
          class="input focus-lined-c"
          placeholder="일기 내용을 입력해주세요"
          v-model="journal.content"
          required
        ></textarea>
      </div>
    </div>
  </div>
</template>

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
const journal = ref(null);
console.log(journal);

async function fetchJournal() {
  try {
    await authStore.updateUserInfoFromToken();
    const response = await axios.get(
      `${authStore.API_URL}/diary/detail/${route.params.id}`
    );

    return {
      post: response.data
    };
  } catch (error) {
    console.error("불러오기 실패: ", error);
    return { journal: null }; // Return nulls or defaults on error
  }
}

const saveJournal = async () => {
  try {
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };
    await authStore.updateUserInfoFromToken();
    const editContent = {
      diaryId: journal.value.diaryId,
      userId: authStore.userInfo.sub,
      content: journal.value.content,
    };
    console.log(journal.value.diaryId);
    const res = await axios.patch(
      `${authStore.API_URL}/diary/update`,
      editContent,
      config
    );
    console.log(res.data);
    router.push({ name: "JournalDetail", params: { id: journal.value.diaryId } });
  } catch (error) {
    console.error("업데이트 실패: ", error);
  }
};

onMounted(async () => {
  const { post: fetchedPost } = await fetchJournal();
  journal.value = fetchedPost;
  loading.value = true;
  authStore.updateUserInfoFromToken();
});

function toBack() {
  router.back();
}
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
