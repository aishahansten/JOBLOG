<template>
  <div class="item-container">
    <div class="scrap-item">
      <div class="title">{{ data.company_name }}</div>
      <div class="main">
        <div class="name">
          {{ data.title }}
        </div>
        <div class="date-time">
          <div class="date">공고 시작일: {{ formatDate(data.opening_date) }}</div> | 
          <div class="date">공고 마감일: {{ formatDate(data.expiration_date) }}</div> |
          <div class="status">
            {{ calculateStatus(data.opening_date, data.expiration_date) }}
          </div>
        </div>
      </div>
      <div>
        <button @click="toggleModal">리뷰 쓰기</button>
        <button @click="toggleDropDown">▼</button>
      </div>
      <div :class="getStatusClass(data.opening_date, data.expiration_date)"></div>
    </div>
    <div v-if="showModal" class="create-modal">
      <div class="create-content">
        <ReviewCreate
        :data="data"
        @close="showModal = false"/>
      </div>
    </div>
    <div v-if="showDropDown">
      <div v-for="review in data.reviews" :key="review.id" class="review">
        {{ review.step }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref }  from 'vue'
import ReviewCreate from '../ReviewCreateView.vue';

const props = defineProps({
  data: Object,
  showModal: Boolean,
  toggleModal: Function,
  formatDate: Function,
  calculateStatus: Function,
  getStatusClass: Function
});

// 드랍다운
const showDropDown = ref(false)

const toggleDropDown = function() {
  showDropDown.value = !showDropDown.value
  console.log(showDropDown.value)
}

// 리뷰 작성
const showModal = ref(false)

const toggleModal = function() {
  showModal.value = !showModal.value
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
</style>