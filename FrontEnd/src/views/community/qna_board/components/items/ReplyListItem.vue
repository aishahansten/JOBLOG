<script setup>
import axios from "axios";
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const router = useRouter();

const props = defineProps({
  comment: Object,
  index: Number,
});

const isEditing = ref(false);
const editableComment = ref({
  commentId: props.comment.commentId,
  content: "",
});

onMounted(() => {
  // Set the initial content of the editableComment
  editableComment.value.content = props.comment.content;
  authStore.updateUserInfoFromToken();
});

const toggleEdit = () => {
  if (isEditing.value) {
    console.log(editableComment);
    saveComment();
  }
  // Toggle the edit state
  isEditing.value = !isEditing.value;
};

const saveComment = async () => {
  try {
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`,
      },
    };

    const res = await axios.patch(
      `${authStore.API_URL}/comment/update`,
      editableComment.value,
      config
    );
    props.comment.modifiedDate = res.data.modifiedDate;
    console.log(res.data);
  } catch (error) {
    console.error("업뎃 실패: ", error);
  }
};

const editted = computed(() => {
  return sameDates(props.comment.createdDate, props.comment.modifiedDate);
});
function sameDates(date1, date2) {
  return date1 === date2;
}

const deleteComment = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(
        `${authStore.API_URL}/comment/delete/${props.comment.commentId}`,
        config
      );
      console.log(props.comment.commentId, "번글이 삭제되었습니다.");
      console.log(res.data);
      location.reload();

      // Optionally, emit an event to inform the parent component to update the comment list
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
    console.log("삭제가 취소되었습니다.");
  }
};
</script>

<template>
  <div class="answer">
    <div class="title">
      <div class="left">
        <div>
          <i class="fa-solid fa-a fa-2xl"></i>
          <p class="f-size-24 f-weight-b">
            <b>{{ comment.userId }}</b
            >님의 답변
          </p>
        </div>
        <div>
          <span class="f-size-14 f-color-g">{{ comment.createdDate }}</span>
          <span v-if="!editted">(수정됨)</span>
        </div>
      </div>
      <div class="right" v-if="comment.userId == authStore.userInfo.sub">
        <a
          @click="toggleEdit"
          class="btn-s lined-bg h-solid-bg a-dark f-size-14"
        >
          {{ isEditing ? "저장" : "수정" }}
        </a>
        <a
          @click="deleteComment"
          class="btn-s lined-bg h-solid-bg a-dark f-size-14"
          >삭제</a
        >
      </div>
    </div>

    <div class="content" v-if="!isEditing">
      {{ editableComment.content }}
    </div>
    <div class="content" v-else>
      <textarea
        v-model="editableComment.content"
        cols="30"
        rows="10"
        class="comment-textarea"
      ></textarea>
    </div>
  </div>
</template>

<style scoped>
.answer {
  border: 1px solid var(--border-gray);
  border-radius: 10px;
  padding: 30px 50px;
}
.title {
  display: grid;
  grid-template-columns: 5fr 1fr;
  padding: 15px 0;
}
.left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 10px;
}
.left div {
  display: flex;
  align-items: center;
  gap: 15px;
}

.right {
  display: flex;
  justify-content: end;
  align-items: start;
  gap: 5px;
}

.content {
  padding: 15px 0;
}

textarea {
  width: 100%;
  padding: 20px;
  resize: none;

  font-size: 16px;
  border: none;
  border-radius: 10px;
  background-color: var(--light-gray);
}
</style>
