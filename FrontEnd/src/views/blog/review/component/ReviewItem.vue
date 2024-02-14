<template>
  <div class="item-container">
    <div class="scrap-item">
      <div class="title">
        <span v-if="data.companyCode" class="company-exists">
          <RouterLink :to="{ name: 'CompanyDetail', params: { id: data.companyCode } }">
            {{ data.companyName }}
          </RouterLink>
        </span>

        <span v-else>{{ data.companyName }}</span>
      </div>
      <div class="main">
        <div class="name">
          {{ data.title }}
        </div>
        <div class="date-time">
          <div class="date">
            공고 시작일:
            <span v-if="data.openingDate">{{ formatDate(data.openingDate) }}</span>
            <span v-else>시작일 미정</span>
          </div>
          |
          <div class="date">
            공고 마감일:
            <span v-if="data.expirationDate">{{ formatDate(data.expirationDate) }}</span>
            <span v-else>마감일 미정</span>
          </div>
        </div>
      </div>
      <div>
        <button @click="toggleModal">전형 등록</button>
        <button @click="toggleDropDown">▼</button>
      </div>
      <div :class="getStatusClass(data.openingDate, data.expirationDate)"></div>
    </div>
    <div v-if="showModal" class="create-modal">
      <div class="create-content">
        <ReviewCreate :data="data" @close="showModal = false" />
      </div>
    </div>
    <div v-if="showDropDown">
      <div v-for="step in data.selectionListResponseDto" :key="step.id" class="step">
        <div>
          <div>{{ step.title }}</div>
          <div>{{ step.step }}단계</div>
          <div>{{ step.status }}</div>
        </div>
        <button @click="toggleModalState(step.id)">리뷰 작성하기</button>

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
const props = defineProps({
  data: Object,
});

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
.item-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 5px;
}
.scrap-item {
  display: grid;
  grid-template-columns: 1fr 5fr 1fr 0.1fr;
  font-size: 20px;
  border: 1px solid black;
  height: 80px;
}

.title {
  font-weight: bold;
}

.main {
  display: grid;
  grid-template-rows: 1fr 1fr;
}

.date-time {
  display: flex;
  gap: 10px;
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

.step {
  border: 1px solid black;
}

.create-modal {
  display: flex;
  flex-direction: column;
  position: relative;
  width: 1420px;
  background-color: rgba(0, 0, 0, 0.23);
  padding: 20px;
  box-sizing: border-box;
}

.create-content {
  background-color: white;
}

.company-exists {
  background-color: rgba(245, 245, 245, 0.371);
}
</style>
