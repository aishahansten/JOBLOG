<template>
  <div class="form-input">
    <label :for="id">{{ label }}</label>
    <input :id="id" :type="type" v-model="inputValue" />
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  label: String,
  id: String,
  type: {
    type: String,
    default: 'text'
  }
});

const inputValue = ref('');
</script>

<style scoped>
.form-input {
  margin-bottom: 16px;
}
label {
  display: block;
  margin-bottom: 8px;
}
input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
