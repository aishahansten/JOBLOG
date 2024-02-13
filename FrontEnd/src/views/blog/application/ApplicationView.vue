<template>
  <div class="application-container">
    <div class="application-left">
      <div class="application-header">
        <div>
          <h1>이력서</h1>
        </div>
        <div class="header-right">
          <RouterLink :to="{ name: 'ResumeCreate' }"> <h1>+</h1></RouterLink>
        </div>
      </div>
      <div class="application-list">
        <ResumeListView />
      </div>
    </div>
    <div class="application-right">
      <div class="application-header">
        <h1>자기소개서</h1>
        <div class="header-right">
          <button @click="toggleModal" class="w-btn w-btn-indigo">
            + 추가하기
          </button>
        </div>
      </div>
      <EssayList :essayList="essayList" />
    </div>
  </div>
  <div v-if="showModal" class="modal">
    <div class="modal-content">
      <h2>새 항목 작성</h2>
      <div>
        <label for="job-post">채용공고:</label>
        <input type="text" class="job-post-dropdown" />
        <div v-if="categoryLoaded"></div>
        <div class="dropdown" v-else>
          <div
            class="dropdown-button"
            @click="toggleDropdown"
            v-if="essays.categoryId === null"
          >
            카테고리를 선택해주세요!
          </div>
          <div class="dropdown-button" @click="toggleDropdown" v-else>
            {{ selectedCategoryName }}
          </div>
          <div class="dropdown-content" v-if="showDropdown">
            <div
              v-for="category in categoryList"
              :key="category.categoryId"
              class="dropdown-item"
            >
              <div @click="checkCategory(category.categoryId)" class="category">
                {{ category.categoryName }}
              </div>
              <button @click="deleteCategory(category.categoryId)">
                Delete
              </button>
            </div>
          </div>
        </div>
        <button
          v-if="!showAdd"
          @click="toggleShowAdd"
          class="w-btn w-btn-indigo"
          type="button"
        >
          추가
        </button>
        <button
          v-if="showAdd"
          @click="toggleShowAdd"
          class="w-btn w-btn-indigo"
          type="button"
        >
          취소
        </button>

        <div v-if="showAdd" class="add-category">
          <input
            type="text"
            v-model="category.categoryName"
            placeholder="카테고리를 추가하세요"
          />
          <button @click="addCategory()">카테고리 추가하기</button>
        </div>
      </div>
      <label for="question">질문:</label>
      <input type="text" id="question" v-model="essays.question" />

      <label for="answer">답변:</label>
      <textarea id="answer" v-model="essays.answer"></textarea>
      <div>{{ essays.answer.length }}자</div>
      <div class="modal-buttons">
        <button @click="submitEssay" class="w-btn w-btn-indigo" type="button">
          제출
        </button>

        <button @click="toggleModal" class="w-btn w-btn-indigo" type="button">
          닫기
        </button>
      </div>
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
  question: "답변을 입력해주세요",
  answer: "문항을 입력해주세요",
});

// const essayCategory

const submitEssay = async () => {
  await authStore.updateUserInfoFromToken();
  essays.value.userId = authStore.userInfo.sub;
  const config = {
    headers: {
      Authorization: `${authStore.accessToken}`,
    },
  };
  try {
    const res = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/essay/register`,
      essays.value,
      config
    );
  } catch (err) {
    console.error(err);
  }
  essays.value = {
    userId: "",
    question: "",
    answer: "",
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
  const category = categoryList.value.find(
    (c) => c.categoryId === essays.value.categoryId
  );
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
.application-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  width: 1344px;
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid black;
  border-radius: 8px;
  margin-top: 20px;
}

.application-left,
.application-right {
  display: grid;
  grid-template-rows: 1fr 9fr;
  border: 1px solid black;
  border-radius: 8px;
  box-sizing: border-box;
  padding: 40px;
  max-height: 900px;
}

.application-header {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.header-right {
  display: flex;
  margin-left: auto;
  justify-content: center;
}

.application-list,
.essay-list {
  border: 1px solid black;
  border-radius: 8px;
}

.empty-essay {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 500px;
  font-size: 24px;
}

.modal {
  position: fixed;
  top: 10%;
  width: 1100px;
  height: 700px;
  background-color: rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: 600px;
}

.modal-content textarea {
  resize: none;
  width: 994px;
  height: 200px;
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

.category {
  cursor: pointer;
}
</style>
