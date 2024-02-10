<template>
  <div class="scrap-container">
    <ReviewItem
      v-for="data in processedData"
      :key="data.my_recruit_id"
      :data="data"
      :formatDate="formatDate"
      :calculateStatus="calculateStatus"
      :getStatusClass="getStatusClass"
    />
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import dummyData from "./data/dummy_data.json";
import reviewDummy from "./data/application_status_dummy_data.json";
import ReviewItem from "./component/ReviewItem.vue";
//날짜 계산용 함수 -> 마감일까지의 정보

function getReviewsForRecruitment(userRecruitId) {
  return reviewDummy.filter(
    (review) => review.user_recruit_id === userRecruitId
  );
}

const processedData = computed(() => {
  return dummyData.map((item) => {
    const reviews = getReviewsForRecruitment(item.user_recruit_id);
    return {
      ...item,
      opening_date: new Date(item.opening_date),
      expiration_date: new Date(item.expiration_date),
      reviews: reviews,
      showReviews: false, // Initially, reviews are not shown
    };
  });
});

console.log(processedData);

function formatDate(date) {
  const options = { year: "numeric", month: "long", day: "numeric" };
  return date.toLocaleDateString(undefined, options);
}

function calculateStatus(openingDate, expirationDate) {
  const today = new Date();
  if (today < openingDate) {
    return `공고시작일까지 ${daysBetween(today, openingDate)}일`;
  } else if (today > expirationDate) {
    return "마감";
  } else {
    return `마감일까지 ${daysBetween(today, expirationDate)}일`;
  }
}

function daysBetween(date1, date2) {
  const oneDay = 24 * 60 * 60 * 1000;
  return Math.round(Math.abs((date2 - date1) / oneDay));
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
//--------------------------------------------------------------
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

.review {
  height: 80px;
  border: 1px solid black;
}
</style>
