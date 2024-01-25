<template>
  <div class="form-container">
    <FormInput label="전형 유형" id="petition-type" />
    <FormDropdown
      label="전형 유형"
      id="petition-category"
      :options="['면접', '코테']"
    />
    <!-- Other form fields go here -->
    <FormTextarea label="내용을 입력하세요" id="content" />
    <FormButton text="전송" type="submit" />
  </div>
</template>

<script setup>
import FormInput from "./components/FormInput.vue";
import FormDropdown from "./components/FormDropdown.vue";
import FormTextarea from "./components/FormTextarea.vue";
import FormButton from "./components/FormButton.vue";
</script>

<style scoped>
.form-container {
  max-width: 600px;
  margin: auto;
}
</style>
