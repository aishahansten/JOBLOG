<script setup>
import "@/assets/css/home/todo.css";
import Calendar from "./components/Calendar.vue";
</script>

<template>
  <div class="container">
    <div class="header">
      <h1>나의 일정 관리</h1>
      <p>
        스크랩 및 등록한 공고의 전형 일정을 살펴보고,<br />
        합격 후 2, 3차 전형의 일정을 등록해 관리할 수 있습니다.
      </p>
    </div>
    <Calendar />
  </div>
</template>

<style scoped>
.container {
  min-height: 1000px;
  padding: 20px 0;
}
.header {
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}
.header p {
  text-align: end;
  line-height: 130%;
  font-size: 14px;
}

input[type="datetime-local"]:focus {
  font-size: 20px; /* Adjust this value as needed */
  padding: 10px; /* Larger padding for a larger click area */
}
</style>
