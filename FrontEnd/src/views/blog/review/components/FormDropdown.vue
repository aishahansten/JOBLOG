<template>
  <div class="form-dropdown">
    <label :for="id">{{ label }}</label>
    <select :id="id" v-model="selectedOption">
      <option v-for="option in options" :key="option" :value="option">{{ option }}</option>
    </select>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  label: String,
  id: String,
  options: Array
});

const selectedOption = ref(props.options[0]);
</script>

<style scoped>
.form-dropdown {
  margin-bottom: 16px;
}
label {
  display: block;
  margin-bottom: 8px;
}
select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
