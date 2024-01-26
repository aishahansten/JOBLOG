<template>
  <div class="form-textarea">
    <label :for="id">{{ label }}</label>
    <textarea :id="id" v-model="textareaValue" />
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  label: String,
  id: String
});

const textareaValue = ref('');
</script>

<style scoped>
.form-textarea {
  margin-bottom: 16px;
}
label {
  display: block;
  margin-bottom: 8px;
}
textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}
</style>
