<template>
  <div class="review">
    <RouterLink :to="{ name: 'JournalDetail', params: { id: diaryId } }">
      <img
        :src="catImageUrl"
        alt="Cat Profile Image"
        class="profile-image"
        :style="{ width: size + 'px', height: size + 'px' }"
      />
      <h3 class="preview">{{ content }}</h3>
      <p>{{ createdDate }}</p>
    </RouterLink>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps } from "vue";
import axios from "axios";

const props = defineProps({
  diaryId: Number,
  content: String,
  createdDate: String,
  size: {
    type: Number,
    default: 200, // 기본 크기는 200px
  },
});

const catImageUrl = ref("");

onMounted(() => {
  fetchCatImage();
});

const fetchCatImage = async () => {
  try {
    const response = await axios.get(
      "https://api.thecatapi.com/v1/images/search"
    );
    catImageUrl.value = response.data[0].url;
  } catch (error) {
    console.error("Error fetching cat image:", error);
  }
};
</script>

<style>
.review {
  border: 1px solid #ccc;
  padding: 16px;
  text-align: center;
}
.image-placeholder {
  background-color: #eaeaea;
  width: 100%;
  height: 150px;
  margin-bottom: 8px;
}

.preview {
  display: -webkit-box;
  height: 50px;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  word-wrap: break-word;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 14px;
}
</style>
