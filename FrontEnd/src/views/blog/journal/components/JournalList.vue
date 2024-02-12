<template>
    <div class="journal-items-container">
        <div class="journal-left">
            <div class="diary" v-for="(diary, index) in paginatedDiaries.left" :key="index">
                <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate" :modifiedDate="diary.modifiedDate"/>
                <!-- <h3>{{diary.diaryId}}</h3>
                <p>{{ diary.createdDate }}</p>
                <p>{{diary.content}}</p> -->
            </div>
        </div>
        <div class="journal-right">
            <div class="diary" v-for="(diary, index) in paginatedDiaries.right" :key="index">
                <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate" :modifiedDate="diary.modifiedDate"/>
    
                <!-- <h3>{{diary.diaryId}}</h3>
                <p>{{ diary.content }}</p> -->
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
// const diaries = ref([
//   { title: 'Diary 1' }, { title: 'Diary 2' }, { title: 'Diary 3' }, { title: 'Diary 4' },
//   { title: 'Diary 5' }, { title: 'Diary 6' }, { title: 'Diary 7' }, { title: 'Diary 8' },
//   { title: 'Diary 9' }, { title: 'Diary 10' }, { title: 'Diary 11' }, { title: 'Diary 12' },
//   { title: 'Diary 13' }, { title: 'Diary 14' }, { title: 'Diary 15' }, { title: 'Diary 16' }
// ]);

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