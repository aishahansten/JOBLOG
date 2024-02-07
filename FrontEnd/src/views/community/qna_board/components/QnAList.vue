<script setup>
import QnAListItem from "./items/QnAListItem.vue";
import { useCommunityStore } from "@/stores/community";

const props = defineProps({
  posts: Array,
});
</script>

<template>
  <div class="list">
    <QnAListItem v-for="post in posts" :key="post.postId" :post="post" />
  </div>
</template>

<style scoped>
.list {
  display: flex;
  flex-direction: column;
  max-height: 1300px;
}
</style>
