<template>
  <div class="modal-content">
    <h2>새 항목 작성</h2>
    <div>
      <label for="category">카테고리:</label>
      <input type="text" id="category" v-model="newEssay.category">
      <label for="jobpost">채용공고:</label>
      <input type="text">
      <button v-if="!showAdd" @click="toggleShowAdd">추가</button>
      <button v-if="showAdd" @click="toggleShowAdd">취소</button>

      <div v-if="showAdd" class="add-category">
        <input type="text" placeholder="카테고리를 추가하세요">
      </div>
    </div>
      <label for="question">질문:</label>
      <input type="text" id="question" v-model="newEssay.question">

      <label for="answer">답변:</label>
      <textarea id="answer" v-model="newEssay.answer"></textarea>
      <div>{{ newEssay.answer.length }}자</div>
      <div class="modal-buttons">
        <button @click="submitEssay">제출</button>
        <button @click="toggleModal">닫기</button>
      </div>
  </div>
</template>

<script setup>

</script>

<style scoped>

</style>