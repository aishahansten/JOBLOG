<script setup>
import "@/assets/css/home/todo.css";

import SubNav from "../_component/SubNav.vue";
import Calendar from "./componnents/Calendar.vue";
</script>

<template>
  <div class="container">
    <SubNav />
    <Calendar />
  </div>
</template>

<style scoped>
.container {
  min-height: 1000px;
}
.blog-schedule {
  display: grid;
  grid-template-rows: 1fr 9fr;
  width: 100%;
  height: 600px;
  gap: 10px;
}

.calendar-container {
  display: grid;
  grid-template-columns: 7fr 3fr;
  gap: 20px;
}

.calendar-space {
  border: 1px solid black;
  border-radius: 8px;
}
</style>
