<script setup>
import { ref } from "vue";
import axios from "axios";
import { useCommunityStore } from "@/stores/community";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const communityStore = useCommunityStore();

const props = defineProps({
  postId: Number,
});

const emit = defineEmits(["refresh"]);

const comment = ref({
  postId: props.postId,
  userId: "",
  content: "",
});

const submitAnswer = async () => {
  try {
    authStore.updateUserInfoFromToken();
    comment.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };
    const response = await axios.post(
      `${communityStore.API_URL}/comment/register`,
      comment.value,
      config
    );
    emit("refresh");
    comment.value.content = "";
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div id="write-answer">
    <textarea
      name="comment"
      id="comment"
      cols="30"
      rows="20"
      v-model="comment.content"
      placeholder="질문자를 위한 답변을 작성해주세요!"
    >
    </textarea>
    <div>
      <a class="btn solid-c h-bright a-dark" @click="submitAnswer"
        >답변 등록하기</a
      >
    </div>
  </div>
</template>

<style scoped>
#write-answer {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

textarea {
  width: 100%;
  padding: 20px;
  resize: none;

  font-size: 16px;
  border: none;
  border-radius: 10px;
}
</style>
