<template>
    <div class="journal-items-container">
        <div class="journal-left">
            <div class="diary" v-for="(diary, index) in paginatedDiaries.left" :key="index">
                <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate"/>
            </div>
        </div>
        <div class="journal-right">
            <div class="diary" v-for="(diary, index) in paginatedDiaries.right" :key="index">
                <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate"/>
            </div>
        </div>
        <button @click="prevPage">이전 페이지</button>
        <button @click="nextPage">다음 페이지</button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import JournalListItem from './items/JournalListItem.vue'
import { useJournalStore } from '@/stores/journal';
import { onMounted, ref, computed } from "vue";
const journalStore = useJournalStore();
const journals = ref([]);
const router = useRouter();

onMounted(async () => {
  await journalStore.getJournals(router)
  journals.value = journalStore.journals
});
const currentPage = ref(1);
const diariesPerPage = 8;

const paginatedDiaries = computed(() => {
    const startIndex = (currentPage.value - 1) * diariesPerPage;
    const endIndex = startIndex + diariesPerPage;
    const currentDiaries = journals.value.slice(startIndex, endIndex);

    return {
        left: currentDiaries.slice(0, 4),
        right: currentDiaries.slice(4, 8)
    };
});

const nextPage = () => {
    if (currentPage.value < Math.ceil(journals.value.length / diariesPerPage)) {
        currentPage.value += 1;
    }
};

const prevPage = () => {
    if (currentPage.value > 1) currentPage.value -= 1;
};
</script>


<style scoped>
  .journal-items-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 50px;
  }

  .journal-left, .journal-right {
    border: 1px black solid;
    border-radius: 8px;
    height: 700px;
    background-color: aliceblue;
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .diary {
    margin: 10px;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid black;
    border-radius: 8px;
    background-color: rgb(245, 238, 220);
  }
</style>