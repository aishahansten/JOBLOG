<template>
  <div class="container">
    <div class="header">
      <h1>나의 다이어리</h1>
      <RouterLink :to="{ name: 'JournalCreate' }" class="btn lined-c h-solid-c a-bright f-color-c"
        >+ 다이어리 쓰기</RouterLink
      >
    </div>

    <div class="content">
      <JournalList />

    </div>
  </div>
</template>

<script setup>

import { useRouter, RouterLink } from 'vue-router';
import JournalList from './components/JournalList.vue';
import SubNav from '../_component/SubNav.vue';
import { useJournalStore } from '@/stores/journal';
import { onMounted, ref } from "vue";

const journalStore = useJournalStore();
const journals = ref([]);
const router = useRouter();

onMounted(async () => {
  await journalStore.getJournals(router)
  journals.value = journalStore.journals
  console.log(journals.value)
});
</script>

<style scoped>
.container {
  padding: 20px 0;
}
.header {
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
}

.content {
  height: 100%;
  padding: 10px 0;
}
</style>
