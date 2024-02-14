<template>
  <div class="item">
    <div class="post">
      <div class="left">
        <div :class="getStatusClass(data.openingDate, data.expirationDate)">
          <div v-if="getStatusClass(data.openingDate, data.expirationDate) === 'status-yet-opened'">
            <i class="fa-solid fa-hourglass-start fa-2xl"></i>
            <span>예정 공고</span>
          </div>
          <div v-if="getStatusClass(data.openingDate, data.expirationDate) === 'status-ongoing'">
            <i class="fa-solid fa-hourglass-half fa-2xl"></i>
            <span>진행중 공고</span>
          </div>
          <div v-if="getStatusClass(data.openingDate, data.expirationDate) === 'status-expired'">
            <i class="fa-solid fa-hourglass-end fa-2xl"></i>
            <span>마감된 공고</span>
          </div>
        </div>
        <div class="info">
          <h3>{{ data.title }} <span v-if="!data.scrapOrNot" class="customJob">(직접 작성한 공고입니다)</span></h3>
          <p v-if="data.companyCode">
            <RouterLink :to="{ name: 'CompanyDetail', params: { id: data.companyCode } }" class="hyperLink">
              {{ data.companyName }}
            </RouterLink>
          </p>
          <p v-else>
            {{ data.companyName }}
          </p>
          <div class="date">
            <div>
              <b class="f-color-g">공고 시작일</b> |
              <span v-if="data.openingDate">{{ formatDate(data.openingDate) }}</span>
              <span v-else>시작일 미정</span>
            </div>
            <div class="date">
              <b class="f-color-g">공고 마감일</b> |
              <span v-if="data.expirationDate">{{ formatDate(data.expirationDate) }}</span>
              <span v-else>마감일 미정</span>
            </div>
          </div>
        </div>
      </div>
      <div class="right-container">
        <div class="right">
          <a class="btn h-bright a-dark" @click="deleteSelection(data.id)"
            >공고 삭제　<i class="fa-solid fa-square-minus"></i
          ></a>
          <RouterLink :to="{ name: 'ResumeCreate', params: { title: data.companyName, job: data.title } }">
            <div class="btn h-bright a-dark">이력서 쓰기</div>
          </RouterLink>
        </div>
        <div class="right">
          <a class="btn solid-c h-bright a-dark" @click="toggleModal"
            >전형 등록　<i class="fa-solid fa-square-plus"></i
          ></a>
          <a class="btn solid-g h-bright a-dark" @click="toggleDropDown"
            >전형 목록　<i class="fa-solid fa-square-caret-down"></i
          ></a>
        </div>
      </div>
    </div>

    <div v-if="showModal" class="create-modal">
      <div class="create-content">
        <ReviewCreate :data="data" @close="closeModal()" />
      </div>
    </div>

    <div v-if="showDropDown">
      <div v-for="step in data.selectionListResponseDto" :key="step.id" class="step">
        <div class="header">
          <div class="left-info">
            <h3 class="f-color-g">{{ step.step }}단계</h3>
            <div>
              <h3>{{ step.title }}</h3>
              <p class="f-color-g f-weight-b">{{ step.status }}</p>
            </div>
          </div>
          <div>
            <a @click="deleteStep(step.id)" class="btn solid-a h-bright a-dark"
              >전형 삭제하기　<i class="fa-solid fa-square-pen"></i
            ></a>
            <a @click="toggleModalState(step.id)" class="btn solid-c h-bright a-dark"
              >리뷰 작성하기　<i class="fa-solid fa-square-pen"></i
            ></a>
          </div>
        </div>
        <div v-if="modalState[step.id]">
          <RegisterReview :step="step" @close="() => (modalState[step.id] = false)" />
          리뷰 작성하기
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import ReviewCreate from "../ReviewCreateView.vue";
import RegisterReview from "../component/item/RegisterReview.vue";
import { useBlogReviewStore } from "@/stores/blogReview";

const blogReviewStore = useBlogReviewStore();

const deleteStep = async (id) => {
  const isConfirmed = confirm("삭제하시겠습니까?");
  if (isConfirmed) {
    await blogReviewStore.deleteStep(id);
    alert("삭제되었습니다.");
    emit("close");
  } else {
  }
};

const deleteSelection = async (id) => {
  const isConfirmed = confirm("삭제하시겠습니까?");
  if (isConfirmed) {
    await blogReviewStore.deleteSelection(id);
    alert("삭제되었습니다.");
    emit("close");
  }
};
const props = defineProps({
  data: Object,
});

const emit = defineEmits(["close"]);

const closeModal = () => {
  showModal.value = false;
  emit("close");
};

console.log("item", props.data);

// 드랍다운
const showDropDown = ref(false);

const toggleDropDown = function () {
  showDropDown.value = !showDropDown.value;
};

// 리뷰 작성
const showModal = ref(false);

const toggleModal = function () {
  showModal.value = !showModal.value;
};

const modalState = reactive({});

const toggleModalState = (itemId) => {
  if (modalState[itemId] === undefined) {
    modalState[itemId] = true;
  } else {
    modalState[itemId] = !modalState[itemId];
  }
};

function formatDate(dateString) {
  return dateString.slice(0, 10);
}

// 현재 날짜와 마감 시작일/종료일 사이의 관계 설정
function getStatusClass(openingDate, expirationDate) {
  const today = new Date();
  if (today < openingDate) {
    return "status-yet-opened";
  } else if (today > expirationDate) {
    return "status-expired";
  } else {
    return "status-ongoing";
  }
}
</script>

<style scoped>
.item {
  display: flex;
  flex-direction: column;
}
.post {
  display: flex;
  justify-content: space-between;

  padding: 20px;

  font-size: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 20px;
}

.left {
  display: flex;
  gap: 20px;
}

.right-container {
  display: flex;
  gap: 10px;
}
.right {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 5px;
}

.info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 5px;
}

.date-time {
  display: flex;
}

.status-yet-opened,
.status-ongoing,
.status-expired {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px 40px;
  border-radius: 10px;
}

.status-yet-opened div,
.status-ongoing div,
.status-expired div {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.status-yet-opened span,
.status-ongoing span,
.status-expired span {
  font-size: 16px;
  font-weight: 700;
  color: var(--dark-gray);
}

.status-yet-opened {
  background-color: rgba(0, 0, 255, 0.159); /* Color for yet opened */
}
.status-ongoing {
  background-color: rgba(0, 128, 0, 0.175); /* Color for ongoing */
}
.status-expired {
  background-color: rgba(255, 0, 0, 0.148); /* Color for expired */
}

/* 전형 등록/목록 */
.create-modal {
  display: flex;
  flex-direction: column;

  position: relative;
  margin: 10px;
  padding: 20px;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
}

.create-content {
  background-color: white;
}

.step {
  display: flex;
  flex-direction: column;
  gap: 30px;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
  margin: 10px;
  padding: 20px;
}
.header {
  display: flex;
  justify-content: space-between;
}
.left-info {
  display: flex;
  align-items: center;
  gap: 20px;
}
.left-info div {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.hyperLink {
  color: rgba(3, 110, 171, 0.57);
}

.customJob {
  font-size: 12px;
  font-style: italic;
}
</style>
