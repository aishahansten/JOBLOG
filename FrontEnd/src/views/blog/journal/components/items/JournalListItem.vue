<template>
  <div class="review">
    <p>{{ diaryId }}번째 나의 복기</p>
    <h3>복기 내용</h3>
    
    <div class="image-placeholder"><h3>{{ content }}</h3></div>
    
    <p>작성일자 : {{ createdDate }}</p>
    <p>수정일자 : {{ modifiedDate }}</p>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  diaryId: Number,
  content: String,
  createdDate : String,
  modifiedDate: String,
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
