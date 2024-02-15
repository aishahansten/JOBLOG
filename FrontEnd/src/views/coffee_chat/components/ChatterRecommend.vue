<template>
  <div ref="chatterList" class="list">
    <ChatterListItem v-for="item in chatterList" :key="item.id" :item="item" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import ChatterListItem from "./items/ChatterListItem.vue";

const props = defineProps({
  chatterList: Array,
});
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
