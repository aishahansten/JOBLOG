<template>
  <div class="essay-list">
    <div v-if="essayList.length > 0" class="essay-list">
      <div v-for="essay in essayList" :key="essay.id">
        <RouterLink
          :to="{ name: 'EssayDetail', params: { id: essay.essayId } }"
          class="essay-item h-transparent-c a-dark"
        >
          <h3>{{ essay.question }}</h3>
          <p>{{ essay.answer }}</p>
        </RouterLink>
      </div>
    </div>
    <div v-else class="empty-essay">
      <i class="fa-regular fa-folder-open fa-2xl"></i>
      <p>
        아직 자기소개서를 작성하지 않으신 것 같아요!<br />자기소개서 문항을 하나
        작성해볼까요?
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from "vue";

const props = defineProps({
  essayList: Array,
});

const showModal = ref(false);

const toggleModal = () => {
  showModal.value = !showModal.value;
};
</script>

<style scoped>
.empty-essay {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 500px;
  font-size: 24px;
}

.essay-list {
  display: flex;
  flex-direction: column;
  gap: 10px;

  height: 700px;
  padding: 5px;
  overflow: auto;
  overflow-y: scroll;
}

/* Style the scrollbar itself (background) */
.essay-list::-webkit-scrollbar {
  width: 10px; /* width of the entire scrollbar */
}

/* Style the scrollbar thumb (the part you drag) */
.essay-list::-webkit-scrollbar-thumb {
  background: #ffe2a5; /* thumb color */
  border-radius: 10px;
}

/* Handle the hover effect */
.essay-list::-webkit-scrollbar-thumb:hover {
  background: #ffb565; /* color when hovering over the thumb */
}

.essay-item {
  display: flex;
  flex-direction: column;
  gap: 10px;

  padding: 20px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}
.essay-item p {
  margin: 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: fit-content;
  color: var(--gray);
}
</style>
