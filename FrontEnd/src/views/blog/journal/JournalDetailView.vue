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
          
        <RouterLink :to="{ name: 'BlogJournal' }">
          <span class="f-weight-b">나의 다이어리</span>
        </RouterLink>
        <i class="fa-solid fa-chevron-right fa-xs" style="margin-top: 4px"></i>
        <span>{{ journal.content }}</span>

          <h3>00월 00일의 일기</h3>
          <a class="btn lined-bg f-color-c h-lined-c a-solid-c"> 작성 완료 </a>
        </div>
      </div>

      <div class="content">
        <textarea
          class="input focus-lined-c"
          placeholder="일기 내용을 입력해주세요"
          required
        ></textarea>
      </div>
      
      <div class="question">
        <div class="title">
          <div class="left">
            <div>
              <i class="fa-solid fa-q fa-2xl"></i>
              <p class="f-size-24 f-weight-b" v-if="journal">
                {{ journal.content }}
              </p>
            </div>
            <div>
              <span>{{ journal.createdDate }}</span>
            </div>
          </div>
          <div class="right">
            <div>
              <RouterLink
                class="f-size-14 h-txt f-color-g"
                :to="{ name: 'JournalUpdate', params: { id: journal.diaryId } }"
              >
                수정
              </RouterLink>
              ·
              <a class="f-size-14 h-txt f-color-g" @click="deleteJournal">삭제</a>
            </div>
          </div>
        </div>

        <div class="content">
          {{ journal.content }}
        </div>
      </div>
      
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import {ref, onMounted} from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useAuthStore } from '@/stores/auth';


const router = useRouter();
const route = useRoute();
const authStore = useAuthStore();

const journal = ref(null);
const loading = ref(false);

async function fetchJournal() {
  try {
    await authStore.updateUserInfoFromToken();
    const response = await axios.get(
      `${authStore.API_URL}/diary/detail/${route.params.id}`
    );
    console.log(response.data)

    return {
      journal: response.data
    };
    
  } catch (error) {
    console.error("불러오기 실패: ", error);
    return { journal: null};
  }
}

const handleRefresh = async () => {
  loading.value = false;
  const { journal: fetchedJournal } =
    await fetchJournal();
  journal.value = fetchedJournal;
  showModal.value = false;
  loading.value = true;
};

onMounted(async () => {
  const { journal: fetchedJournal } =
    await fetchJournal();
  journal.value = fetchedJournal;
  loading.value = true;
  authStore.updateUserInfoFromToken();
});

const showModal = ref(false);
const toggleModal = function () {
  showModal.value = !showModal.value;
};

const deleteJournal = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(
        `${authStore.API_URL}/diary/delete/${journal.value.diaryId}`,
        config
      );
      console.log(journal.value.diaryId, "번글이 삭제되었습니다.");
      console.log(res.data);
      router.push({ name: "BlogJournal" });

      // Optionally, emit an event to inform the parent component to update the comment list
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
    console.log("삭제하지 않음.");
  }
};
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


.question {
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  padding: 30px 50px;
}

.left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.left div {
  display: flex;
  align-items: center;
  gap: 15px;
}
.left div span {
  font-size: 14px;
  color: var(--gray);
}
.right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: end;
  gap: 15px;
}
.right .like {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
}

.write {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 30px;
  background-color: var(--light-gray);
  border-radius: 10px;
}
.intro {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Slide Transition for QnACommentCreate Component */
.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}
.slide-enter-from,
.slide-leave-to {
  transform: translateY(10px);
  opacity: 0;
}
.slide-enter-to,
.slide-leave-from {
  transform: translateY(0);
  opacity: 1;
}
</style>
