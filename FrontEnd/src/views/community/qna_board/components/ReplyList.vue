<template>
  <div>
    <ReplyListItem
      v-for="(comment, index) in comments"
      :key="comment.commentId"
      :comment="comment"
      :index="index"
    />
  </div>
</template>
  
<script setup>
import ReplyListItem from './items/ReplyListItem.vue';

const props = defineProps ({
  comments: Array
})
</script>

<style scoped>
</style>
  