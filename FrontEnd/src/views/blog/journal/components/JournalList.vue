<template>
  <div class="container">
    <div class="left h-transparent-c" @click="prevPage">
      <div
        class="diary pointer h-bright"
        v-for="(diary, index) in paginatedDiaries.left"
        :key="index"
      >
        <div class="img">
          <img src="@/assets/img/blog/diary-img.jpg" alt="" />
        </div>
        <p>{{ diary.title }}</p>
      </div>
    </div>
    <div class="right h-transparent-c" @click="nextPage">
      <div
        class="diary pointer h-bright"
        v-for="(diary, index) in paginatedDiaries.right"
        :key="index"
      >
        <div class="img">
          <img src="@/assets/img/blog/diary-img.jpg" alt="" />
        </div>
        <p>{{ diary.title }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";

const diaries = ref([
  { title: "Diary 1" },
  { title: "Diary 2" },
  { title: "Diary 3" },
  { title: "Diary 4" },
  { title: "Diary 5" },
  { title: "Diary 6" },
  { title: "Diary 7" },
  { title: "Diary 8" },
  { title: "Diary 9" },
  { title: "Diary 10" },
  { title: "Diary 11" },
  { title: "Diary 12" },
  { title: "Diary 13" },
  { title: "Diary 14" },
  { title: "Diary 15" },
  { title: "Diary 16" },
]);

const currentPage = ref(1);
const diariesPerPage = 8;

const paginatedDiaries = computed(() => {
  const startIndex = (currentPage.value - 1) * diariesPerPage;
  const endIndex = startIndex + diariesPerPage;
  const currentDiaries = diaries.value.slice(startIndex, endIndex);

  return {
    left: currentDiaries.slice(0, 4),
    right: currentDiaries.slice(4, 8),
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
