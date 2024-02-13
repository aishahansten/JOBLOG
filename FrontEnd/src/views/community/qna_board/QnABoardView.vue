<script setup>
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import { useCommunityStore } from "@/stores/community";
import QnAList from "./components/QnAList.vue";

const communityStore = useCommunityStore();
const router = useRouter();

onMounted(async () => {
  // Use an immediately-invoked async function
  await communityStore.getPosts(router);
});
</script> 

<template>
  <div class="container">
    <div class="header">
      <div class="title">
        <h1>Q&A 게시판</h1>
        <p class="f-color-g">취준생과 현직자 간의 궁금한 이야기</p>
      </div>
      <div class="create">
        <RouterLink :to="{ name: 'QnACreate' }">
          <a class="btn lined-c f-weight-b f-color-c h-solid-c a-bright"
            >질문 등록하기</a
          >
        </RouterLink>
      </div>
    </div>
    <div class="content">
      <QnAList :posts="communityStore.posts" />
    </div>
  </div>
</template>

<style scoped>
.container {
  padding: 20px 0;
}
.container .header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 0;
}
.container .header .title {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  gap: 5px;
}
</style>
