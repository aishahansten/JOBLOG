<script setup>
import axios from "axios";
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import { useCommunityStore } from "@/stores/community";
import ReplyList from "./components/ReplyList.vue";
import QnACommentCreate from "@/views/community/qna_board/components/QnACommentCreate.vue";

const authStore = useAuthStore();
const communityStore = useCommunityStore();
const route = useRoute();
const router = useRouter();

const loading = ref(false);
const post = ref(null);
const comments = ref([]);

const handleRefresh = async () => {
  loading.value = false;
  await communityStore.getPost(route.params.id);
  post.value = communityStore.post.postResponseDto;
  comments.value = communityStore.post.comments;
  showModal.value = false;
  loading.value = true;
};

onMounted(async () => {
  await communityStore.getPost(route.params.id);
  post.value = communityStore.post.postResponseDto;
  comments.value = communityStore.post.comments;
  authStore.updateUserInfoFromToken();
  loading.value = true;
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
      const res = await axios.delete(`${authStore.API_URL}/community/delete/${post.value.postId}`, config);
      router.push({ name: "QnABoard" });
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  }
};

const isWriter = computed(() => {
  if (!post.value || !authStore.userInfo) {
    return false;
  }
  return post.value.userId == authStore.userInfo.sub;
});

const isLoggedIn = computed(() => {
  if (!authStore.userInfo) {
    return false;
  }
  return authStore.userInfo != null;
});
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
              <span>{{ post.hit }} views</span>
            </div>
          </div>
          <div class="right">
            <div class="like pointer h-txt">
              <i class="fa-regular fa-thumbs-up"></i>
              <span class="f-size-14">{{ post.totalLike }}</span>
            </div>
            <div v-if="isWriter">
              <RouterLink class="f-size-14 h-txt f-color-g" :to="{ name: 'QnAUpdate', params: { id: post.postId } }">
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

      <div class="write" v-if="isLoggedIn && authStore.userInfo.role == 'ROLE_JUNIOR'">
        <div class="intro">
          <h2>답변을 작성하고 질문자의 궁금증을 해결해주세요!</h2>
          <a @click="toggleModal" class="btn lined-c f-color-c h-solid-c a-bright"> 답변 작성하기 </a>
        </div>
        <transition name="slide">
          <div v-if="showModal">
            <QnACommentCreate :postId="post.postId" @refresh="handleRefresh" />
          </div>
        </transition>
      </div>
      <div class="write" v-else>
        <div class="intro">
          <h2>답변을 작성하기 위해선 현직자 인증이 필요합니다!</h2>
        </div>
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
