<template>
    <div class="container">
        <div class="left h-transparent-c" @click="prevPage">
            <div
             class="diary pointer h-bright" 
             v-for="(diary, index) in paginatedDiaries.left" 
             :key="index"
            >
              <!-- <div class="img">
                <img src="@/assets/img/blog/diary-img.jpg" alt="" />
              </div> -->
              <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate"/>
            </div>
        </div>
        <div class="right h-transparent-c" @click="nextPage">
            <div
             class="diary pointer h-bright" 
             v-for="(diary, index) in paginatedDiaries.right" 
             :key="index"
            >
                <!-- <div class="img">
                  <img src="@/assets/img/blog/diary-img.jpg" alt="" />
                </div> -->
                <JournalListItem :diaryId="diary.diaryId" :content="diary.content" :createdDate="diary.createdDate"/>
            </div>
        </div>
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
  if (currentPage.value < Math.ceil(diaries.value.length / diariesPerPage)) {
    currentPage.value += 1;
  }
};

const prevPage = () => {
  if (currentPage.value > 1) currentPage.value -= 1;
};
</script>

<style scoped>
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .left,
  .right {
    display: grid;
    grid-template-columns: 1fr 1fr;
    height: 700px;
    padding: 30px 40px;

    border: 1px solid var(--border-gray);
    border-bottom: 20px solid var(--border-gray);
    background-color: var(--light-gray);
  }
  .left {
    border-radius: 0 50px 0 0;
  }
  .right {
    border-radius: 50px 0 0 0;
  }

  .diary {
    display: grid;
    grid-template-rows: 3.5fr 1fr;
    grid-gap: 20px;
    margin: 20px 10px;
  }
  .diary .img {
    height: 200px;
    overflow: hidden;
    border-radius: 20px;
  }
  .diary p {
    font-size: 20px;
    font-weight: 600;
  }
</style>
