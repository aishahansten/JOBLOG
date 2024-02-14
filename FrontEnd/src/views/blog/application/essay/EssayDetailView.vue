<template>
  <div v-if="!isLoaded"></div>
  <div v-else>
    <div class="container">
      <div class="header">
        <a class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          목록으로 돌아가기
        </a>
        <h3>문항 수정하기</h3>
        <a
          @click="submitEssay()"
          class="btn lined-bg f-color-c h-lined-c a-solid-c"
        >
          수정 완료
        </a>
      </div>
      <div class="content">
        <p class="f-weight-b f-color-g">카테고리 | {{ essay.categoryName }}</p>
        <input class="question input" type="text" v-model="essay.question" />
        <textarea class="answer input" type="text" v-model="essay.answer" />
        <a @click="submitEssay()" class="btn solid-c h-bright a-dark"
          >저장하기</a
        >
        <a @click="deleteEssay()" class="btn h-solid-g a-dark">삭제하기</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import { useRoute, useRouter } from "vue-router";

const essayResumeStore = useEssayResumeStore();
const route = useRoute();
const router = useRouter();

const essay = ref({});

const isLoaded = ref(false);

const submitEssay = async () => {
  delete essay.value.categoryName;
  delete essay.value.recruitId;
  console.log("수정", essay.value);
  await essayResumeStore.updateEssay(essay);
  alert("수정되었습니다.");
};

const deleteEssay = async () => {
  await essayResumeStore.deleteEssay(route.params.id);
  alert("삭제되었습니다.");
  router.push({ name: "BlogApplication" });
};

onMounted(async () => {
  await essayResumeStore.getEssayDetail(route.params.id);
  essay.value = essayResumeStore.currentEssay;
  console.log(essayResumeStore.currentEssay);
  isLoaded.value = true;
});
</script>

<style scoped>
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
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 50px 0;
  font-size: 16px;
}
.content input,
.content textarea {
  width: 100%;
  font-size: 16px;
}
.content textarea {
  height: 500px;
  resize: none;
}

.footer {
  display: flex;
  justify-content: start;
  padding: 30px 0;
}
</style>
