<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import ReplyList from "./components/ReplyList.vue";
import QnACommentCreate from "@/views/community/qna_board/components/QnACommentCreate.vue";

const authStore = useAuthStore();
const route = useRoute();
const router = useRouter();

const loading = ref(false);
const post = ref(null);
const comments = ref([]);

async function fetchPostAndComments() {
  try {
    await authStore.updateUserInfoFromToken();
    const response = await axios.get(
      `${authStore.API_URL}/community/detail/${route.params.id}`
    );

    return {
      post: response.data.postResponseDto,
      comments: response.data.comments,
    };
  } catch (error) {
    console.error("불러오기 실패: ", error);
    return { post: null, comments: null }; // Return nulls or defaults on error
  }
}

const handleRefresh = async () => {
  loading.value = false;
  const { post: fetchedPost, comments: fetchedComments } =
    await fetchPostAndComments();
  post.value = fetchedPost;
  comments.value = fetchedComments;
  showModal.value = false;
  loading.value = true;
};

onMounted(async () => {
  const { post: fetchedPost, comments: fetchedComments } =
    await fetchPostAndComments();
  post.value = fetchedPost;
  comments.value = fetchedComments;
  loading.value = true;
  authStore.updateUserInfoFromToken();
});

//-----------
const showModal = ref(false);
const toggleModal = function () {
  showModal.value = !showModal.value;
};

const deletePost = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(
        `${authStore.API_URL}/community/delete/${post.value.postId}`,
        config
      );
      console.log(post.value.postId, "번글이 삭제되었습니다.");
      console.log(res.data);
      router.push({ name: "QnABoard" });

      // Optionally, emit an event to inform the parent component to update the comment list
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
    console.log("삭제하지 않음.");
  }
};
</script>

<template>
  <div v-if="!loading"></div>
  <div v-else>
    <div class="container">
      <div class="header">
        <RouterLink :to="{ name: 'QnABoard' }">
          <span class="f-weight-b">Q&A 게시판</span>
        </RouterLink>
        <i class="fa-solid fa-chevron-right fa-xs" style="margin-top: 4px"></i>
        <span>{{ post.title }}</span>
      </div>

      <div class="question">
        <div class="title">
          <div class="left">
            <div>
              <i class="fa-solid fa-q fa-2xl"></i>
              <p class="f-size-24 f-weight-b" v-if="post">
                {{ post.title }}
              </p>
            </div>
            <div>
              <span>{{ post.createdDate }}</span>
              ·
              <span>50 views</span>
            </div>
          </div>
          <div class="right">
            <div class="like pointer h-txt">
              <i class="fa-regular fa-thumbs-up"></i>
              <span class="f-size-14">18</span>
            </div>
            <div>
              <RouterLink
                class="f-size-14 h-txt f-color-g"
                :to="{ name: 'QnAUpdate', params: { id: post.postId } }"
              >
                수정
              </RouterLink>
              ·
              <a class="f-size-14 h-txt f-color-g" @click="deletePost">삭제</a>
            </div>
          </div>
        </div>

        <div class="content">
          {{ post.content }}
        </div>
      </div>

      <div class="write">
        <div class="intro">
          <h2>답변을 작성하고 질문자의 궁금증을 해결해주세요!</h2>
          <a
            @click="toggleModal"
            class="btn lined-c f-color-c h-solid-c a-bright"
          >
            답변 작성하기
          </a>
        </div>
        <transition name="slide">
          <div v-if="showModal">
            <QnACommentCreate :postId="post.postId" @refresh="handleRefresh" />
          </div>
        </transition>
      </div>

      <ReplyList :comments="comments" />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px 0 50px 0;
}
.header {
  display: flex;
  align-content: center;
  gap: 10px;
  padding: 10px 0;
}

.question {
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  padding: 30px 50px;
}
.title {
  display: grid;
  grid-template-columns: 5fr 1fr;
  padding: 10px 0;
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
.content {
  padding: 15px 0;
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
