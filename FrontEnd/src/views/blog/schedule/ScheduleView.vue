<script setup>
import "@/assets/css/home/todo.css";

import SubNav from "../_component/SubNav.vue";

import Calendar from "./componnents/Calendar.vue";
import Task from "./componnents/Task.vue";
</script>

<template>
  <main class="full-cal">
    <div class="blog-schedule">
      <div>
        <h1>취준로그</h1>
      </div>
      <div class="calendar-container">
        <div class="calendar"><Calendar /></div>
        <div class="schedule"><Task /></div>
      </div>
    </div>
  </main>
  <SubNav />
</template>

<style scoped>
.blog-schedule {
  display: grid;
  grid-template-rows: 1fr 9fr;
  width: 1344px;
  height: 600px;
  gap: 10px;
}

.calendar-container {
  display: grid;
  grid-template-columns: 7fr 3fr;
  gap: 20px;
}

.calendar {
  border: 1px solid black;
  border-radius: 8px;
}

.schedule {
  border: 1px solid black;
  border-radius: 8px;
}
</style>
