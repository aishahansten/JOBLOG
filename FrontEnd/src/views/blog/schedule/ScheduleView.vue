<script setup>
import "@/assets/css/home/todo.css";
import Calendar from "./components/Calendar.vue";
</script>

<template>
  <div class="container">
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

input[type="datetime-local"]:focus {
  font-size: 20px; /* Adjust this value as needed */
  padding: 10px; /* Larger padding for a larger click area */
}
</style>
