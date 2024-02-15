<template>
  <div ref="chatterList" class="list">
    <ChatterListItem v-for="(item, index) in chatterList2" :key="index" :item="item" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import ChatterListItem from "./items/ChatterListItem.vue";

const props = defineProps({
  chatterList: Array,
});
const chatterList2 = ref([
  { user_id: "홍길동", job: "개발자", career: "5년" },
  { user_id: "김철수", job: "디자이너", career: "3년" },
  { user_id: "이영희", job: "마케터", career: "2년" },
  { user_id: "홍길동", job: "개발자", career: "5년" },
  { user_id: "김철수", job: "디자이너", career: "3년" },
  { user_id: "이영희", job: "마케터", career: "2년" },
  { user_id: "홍길동", job: "개발자", career: "5년" },
  { user_id: "김철수", job: "디자이너", career: "3년" },
  { user_id: "이영희", job: "마케터", career: "2년" },
  { user_id: "홍길동", job: "개발자", career: "5년" },
  { user_id: "김철수", job: "디자이너", career: "3년" },
  { user_id: "이영희", job: "마케터", career: "2년" },
]);
</script>

<style scoped>
.list {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 10px;

  width: 100%;
  padding: 10px 0;
}
</style>
