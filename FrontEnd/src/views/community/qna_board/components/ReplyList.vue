<script setup>
import ReplyListItem from "./items/ReplyListItem.vue";

const props = defineProps({
  comments: Array,
});
</script>

<template>
  <ReplyListItem
    v-for="(comment, index) in comments"
    :key="comment.commentId"
    :comment="comment"
    :index="index"
  />
</template>

<style scoped></style>
