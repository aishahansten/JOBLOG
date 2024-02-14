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
  padding: 50px 0;
}

input[type="datetime-local"]:focus {
  font-size: 20px; /* Adjust this value as needed */
  padding: 10px; /* Larger padding for a larger click area */
}
</style>
