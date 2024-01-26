<template>
  <button :type="type" class="form-button">{{ text }}</button>
</template>

<script setup>
const props = defineProps({
  text: String,
  type: {
    type: String,
    default: 'button'
  }
});
</script>

<style scoped>
.form-button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}
.form-button:hover {
  background-color: #0056b3;
}
</style>
