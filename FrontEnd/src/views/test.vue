<template>
  <ul>
    <li v-for="category in jobCategories" :key="category.jobCode">
      {{ category.jobName }}
      <ul v-if="category.child && category.child.length">
        <li v-for="childCategory in category.child" :key="childCategory.jobCode">
          {{ childCategory.jobName }}
        </li>
      </ul>
    </li>
  </ul>
</template>

<script setup>
import jobCategories from '@/assets/data/jobcategory.json';


</script>

<style scoped>

.main-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
}
.button-container {
  display: flex;
  flex-direction: row;
}

button {
  width: 200px;
  margin: 50px;
}
</style>