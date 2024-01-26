<template>
  <div class="review">
    <div class="image-placeholder"></div>
    <h3>{{ title }}</h3>
    <p>{{ dateCreated }}</p>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  title: String,
  dateCreated: String
})
</script>

<style>
.review {
  /* styles for the review component */
  border: 1px solid #ccc;
  padding: 16px;
  text-align: center;
}
.image-placeholder {
  /* styles for the image placeholder */
  background-color: #eaeaea;
  width: 100%;
  height: 150px;
  margin-bottom: 8px;
}
</style>
