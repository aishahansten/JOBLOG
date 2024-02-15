<template>
  <div v-if="!loading"></div>
  <div v-else>
    <div class="container">
      <div class="header">
        <div class="img">
          <img src="@/assets/img/blog/diary-img.jpg" alt="" />
        </div>

        <div class="title">
          <a @click="toDiary" class="btn f-color-w h-solid-g">
            <i class="fa-solid fa-caret-left"></i>
            목록으로 돌아가기
          </a>
          <h3>{{ journal.createdDate }}의 일기</h3>
          <a class="btn lined-bg f-color-c h-lined-c a-solid-c">
            <RouterLink
              class="f-size-23 h-txt f-color-g"
              :to="{ name: 'JournalUpdate', params: { id: journal.diaryId } }"
            >
              <i class="fa fa-edit"></i>
              수정
            </RouterLink>
          </a>
        </div>
      </div>

      <div class="body">
        <div class="left">
          <div>
            <i class="fas fa fa-align-left fa-2xl" aria-hidden="true"></i>
          </div>
          <br />
          <div>
            <p class="f-size-24 f-weight-b" v-if="journal">
              {{ journal.content }}
            </p>
          </div>
          <br />
          <div>
            <span>{{ journal.createdDate }} 작성된 일기</span>
          </div>
        </div>

        <div class="right">
          <a class="f-size-23 h-txt f-color-g" @click="deleteJournal">
            <i class="fa fa-close"></i>
            삭제
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const router = useRouter();
const route = useRoute();
const authStore = useAuthStore();

const journal = ref(null);
const loading = ref(false);

async function fetchJournal() {
  try {
    await authStore.updateUserInfoFromToken();
    const response = await axios.get(`${authStore.API_URL}/diary/detail/${route.params.id}`);

    return {
      journal: response.data,
    };
  } catch (error) {
    console.error("불러오기 실패: ", error);
    return { journal: null };
  }
}

onMounted(async () => {
  const { journal: fetchedJournal } = await fetchJournal();
  journal.value = fetchedJournal;
  loading.value = true;
  authStore.updateUserInfoFromToken();
});

const deleteJournal = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(`${authStore.API_URL}/diary/delete/${journal.value.diaryId}`, config);
      router.push({ name: "BlogJournal" });
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
  }
};

function toDiary() {
  router.push("/blog-journal");
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

.body {
  margin-top: 40px;
  padding: 50px 20px 20px 20px;
  border-radius: 10px 10px 10px 10px;
  border: 1px solid var(--border-gray);
  resize: none;
}

.left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: end;
  gap: 15px;
}
</style>
