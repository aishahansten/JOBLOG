<template>
  <div class="container">
    <div class="header">
      <h1>입사지원서 관리</h1>
      <p>
        직무에 따라 자유롭게 제작 가능한 이력서와<br />
        문항별로 작성한 자기소개서를 조합하여<br />
        입사지원서를 간편하게 제작합니다.
      </p>
    </div>
    <div class="content">
      <div class="left">
        <div class="title">
          <h1>이력서</h1>
          <RouterLink :to="{ name: 'ResumeCreate' }" class="pointer"
            ><i class="fa-solid fa-square-plus fa-xl"></i
          ></RouterLink>
        </div>
        <div class="list">
          <ResumeListView />
        </div>
      </div>
      <div class="right">
        <div class="title">
          <h1>자기소개서</h1>
          <a @click="toggleModal" class="pointer">
            <i class="fa-solid fa-square-plus fa-xl"></i>
          </a>
        </div>
        <div class="list">
          <EssayList :essayList="essayList" />
        </div>
      </div>
    </div>
  </div>

  <div v-if="showModal" class="modal">
    <a @click="toggleModal" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
    <h1 class="title">자기소개서 문항 작성</h1>
    <div class="content">
      <div v-if="categoryLoaded"></div>
      <div class="dropdown" v-else>
        <a
          class="dropdown-button btn lined-c h-solid-c a-bright"
          @click="toggleDropdown"
          v-if="essays.categoryId === null"
        >
          카테고리를 선택해주세요!　
          <i class="fa-solid fa-square-caret-down"></i>
        </a>
        <div class="dropdown-button" v-else>
          <div class="btn lined-c h-solid-c a-bright" @click="toggleDropdown">{{ selectedCategoryName }}</div>
          <button @click="categoryEmpty" class="dropdown-item dropdown-button2">선택 취소</button>
        </div>

        <div class="dropdown-content" v-if="showDropdown">
          <div v-for="category in categoryList" :key="category.categoryId" class="dropdown-item">
            <div @click="checkCategory(category.categoryId)" class="category">
              {{ category.categoryName }}
            </div>
            <button @click="deleteCategory(category.categoryId)">삭제</button>
          </div>
        </div>
      </div>
      <div class="category-add">
        <div class="toggle">
          <a v-if="!showAdd" @click="toggleShowAdd" class="btn lined-bg f-color-g h-solid-g a-bright" type="button">
            새 카테고리 추가　<i class="fa-solid fa-square-plus"></i>
          </a>
          <a v-if="showAdd" @click="toggleShowAdd" class="btn lined-bg f-color-g h-solid-g a-bright" type="button">
            취소
          </a>
        </div>
        <div v-if="showAdd" class="add-category">
          <input type="text" class="input" v-model="category.categoryName" placeholder="카테고리를 추가하세요" />
          <a @click="addCategory()" class="btn solid-c h-bright a-dark">카테고리 추가하기</a>
        </div>
      </div>

      <div class="write">
        <input type="text" class="input" id="question" v-model="essays.question" />
        <textarea class="input" id="answer" v-model="essays.answer"></textarea>
      </div>
      <div>{{ essays.answer.length }}자</div>
      <a @click="submitEssay" class="btn lined-c h-solid-c a-bright" type="button"> 제출 </a>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref, onMounted, watch, computed } from "vue";

import EssayList from "@/views/blog/application/components/EssayList.vue";
import ResumeListView from "./resume/ResumeListView.vue";
import { useEssayResumeStore } from "@/stores/essayResume";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const essayResumeStore = useEssayResumeStore();

const essays = ref({
  userId: "",
  categoryId: null,
  question: "문항 제목을 입력해주세요",
  answer: "내용을 입력해주세요",
});

const categoryEmpty = function () {
  essays.value.categoryId = null;
};

const submitEssay = async () => {
  await authStore.updateUserInfoFromToken();
  essays.value.userId = authStore.userInfo.sub;

  const dataToSubmit = {
    ...essays.value,
    userId: authStore.userInfo.sub,
  };

  if (dataToSubmit.categoryId === null) {
    delete dataToSubmit.categoryId;
  }

  const config = {
    headers: {
      Authorization: `${authStore.accessToken}`,
    },
  };
  try {
    const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/essay/register`, dataToSubmit, config);
    essayList.value = essayResumeStore.essayList;
  } catch (err) {
    console.error(err);
  }

  essays.value = {
    userId: "",
    categoryId: null,
    question: "문항을 입력해주세요",
    answer: "답변을 입력해주세요",
  };

  showModal.value = !showModal.value;
  essayList.value = essayResumeStore.essayList;
};

const essayList = ref([]);
const categoryList = ref([]);

const category = ref({
  userId: "",
  categoryName: "",
});

const categoryLoaded = ref(false);

const addCategory = async () => {
  categoryLoaded.value = true;
  await essayResumeStore.createCategory(category);
  category.value = {
    userId: "",
    categoryName: "",
  };
  showAdd.value = !showAdd.value;
  categoryList.value = essayResumeStore.categoryList;
  categoryLoaded.value = false;
};

const deleteCategory = async (id) => {
  categoryLoaded.value = true;
  await essayResumeStore.deleteCategory(id);
  categoryList.value = essayResumeStore.categoryList;
  categoryLoaded.value = false;
};

const checkCategory = function (id) {
  essays.value.categoryId = id;
  showDropdown.value = !showDropdown.value;
};

const selectedCategoryName = computed(() => {
  const category = categoryList.value.find((c) => c.categoryId === essays.value.categoryId);
  return category ? category.categoryName : "카테고리를 선택해주세요!";
});

watch(
  () => essayResumeStore.essayList,
  (newVal) => {
    essayList.value = newVal;
  },
  { deep: true }
);

watch(
  () => essayResumeStore.categoryList,
  (newVal) => {
    categoryList.value = newVal;
  },
  { deep: true }
);

const showModal = ref(false);
const toggleModal = function () {
  showModal.value = !showModal.value;
};

const showAdd = ref(false);
const toggleShowAdd = function () {
  showAdd.value = !showAdd.value;
};

const showDropdown = ref(false);
const toggleDropdown = function () {
  showDropdown.value = !showDropdown.value;
};

onMounted(async () => {
  await essayResumeStore.getEssay();
  essayList.value = essayResumeStore.essayList;
  await essayResumeStore.getCategory();
  categoryList.value = essayResumeStore.categoryList;
});
</script>

<style scoped>
/* 기본 화면 */
.container {
  padding: 30px 0;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 20px 0;
}
.header p {
  text-align: end;
  line-height: 130%;
}
.content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  padding: 20px 0;
}

.left,
.right {
  display: grid;
  grid-template-rows: 1fr 9fr;
  max-height: 900px;
  padding: 40px;

  border: 1px solid var(--border-gray);
  border-radius: 10px;
  background-color: var(--light-gray);
}

.title {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.list {
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  background-color: white;
}

/* modal */
.modal {
  display: flex;
  flex-direction: column;

  width: 1000px;
  height: 1000px;
  padding: 50px;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 3;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
  background-color: white;
}
.exit-btn {
  position: absolute;
  top: 60px;
  right: 60px;
}

.modal .title {
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}
.modal .content {
  display: flex;
  flex-direction: column;
  justify-content: baseline;

  padding: 40px 0;
}

.write {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.write input {
  width: 100%;
  font-size: 20px;
}
.write textarea {
  height: 400px;
  resize: none;
  font-size: 16px;
}

.add-category {
  display: flex;
  justify-content: space-between;
}
.add-category input {
  width: 70%;
}

.modal-buttons {
  margin-left: auto;
}
.modal-content h2 {
  margin-top: 0;
}

p {
  margin: 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: fit-content;
}

label {
  font-size: 20px;
  margin-right: 10px;
}

input {
  font-size: 24px;
  margin-right: 30px;
}

.dropdown-button {
  cursor: pointer;
}

.dropdown-button2 {
  margin-left: auto;
}
.dropdown-content {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
}
.dropdown-item {
  display: flex;
  justify-content: space-between;
  border: 1px solid rgba(0, 0, 0, 0.089);
  border-radius: 10px;
  padding: 5px;
  font-size: 14pt;
}

.category {
  cursor: pointer;
  width: 100px;
}
</style>
