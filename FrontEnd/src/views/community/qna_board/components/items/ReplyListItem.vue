<template>
  <div class="comment">
    <div class="header">
      <div><img src="@/assets/img/community/A.svg" class="comment-icon"></div>
      <div class="title">{{ index + 1 }}번 답변</div>
      <div v-if="!editted"> (수정됨)</div>
    </div>
    <div class="content">
      <textarea 
        v-model="editableComment.content" 
        :readonly="!isEditing" 
        class="comment-textarea"
      ></textarea>
    </div>
  </div>
  <div class="buttons" v-if="comment.userId == authStore.userInfo.sub">
    <button @click="toggleEdit">{{ isEditing ? '저장하기' : '수정하기' }}</button>
    <button @click="deleteComment">삭제하기</button>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useAuthStore } from '@/stores/auth';
import axios from 'axios';

const authStore = useAuthStore();

const props = defineProps({
  comment: Object,
  index: Number
});

const isEditing = ref(false);
const editableComment = ref({
  commentId: props.comment.commentId,
  content: '',
});

onMounted(() => {
  // Set the initial content of the editableComment
  editableComment.value.content = props.comment.content;
  authStore.updateUserInfoFromToken();
});

const toggleEdit = () => {
  if (isEditing.value) {
    console.log(editableComment)
    saveComment();
  }
  // Toggle the edit state
  isEditing.value = !isEditing.value;
};

const saveComment = async () => {
  try { 
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    }

    const res = await axios.patch(`${authStore.API_URL}/comment/update`, editableComment.value, config)
    props.comment.modifiedDate = res.data.modifiedDate
    console.log(res.data)
  } catch (error) {
    console.error('업뎃 실패: ', error);
  }
};

const editted = computed(() => {
  return sameDates(props.comment.createdDate, props.comment.modifiedDate)
})
function sameDates(date1, date2) {
  return date1 === date2
}

const deleteComment = async () => {
  try {
    const config = {
      headers: {
        'Authorization': `${authStore.accessToken}`
      }
    };
    const res = await axios.delete(`${authStore.API_URL}/comment/delete/${props.comment.commentId}`, config);
    console.log(props.comment.commentId,'번글이 삭제되었습니다.');
    console.log(res.data);
    // Optionally, emit an event to inform the parent component to update the comment list
  } catch (error) {
    console.error('삭제 실패: ', error);
  }
};
</script>

<style scoped>


  .comment {
    min-width: 1200px;
    border: 1px black solid;
    border-radius: 8px;
    min-height: 600px;
    padding: 30px;
    box-sizing: border-box;
  }
  .content {
    padding: 30px;
    box-sizing: border-box;
  }
  .title {
    font-size: 40px;
    font-weight: bold;
    width: 987px;
  }

  .comment-icon {
    height: 100px;
    width: 100px;
  }
  .comment-textarea {
    width: 100%;
    height: 100%;
    resize: none;
  }
</style>