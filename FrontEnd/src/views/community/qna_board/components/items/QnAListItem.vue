<script setup>
const props = defineProps({
  post: Object,
});
</script>

<template>
  <div class="item">
    <RouterLink :to="{ name: 'QnADetail', params: { id: post.postId } }">
      <div class="left">
        <h2>{{ post.title }}</h2>
        <p class="f-size-20 f-weight-l">{{ post.content }}</p>
      </div>
    </RouterLink>

    <div class="right">
      <span class="f-color-g">{{ post.createdDate }}</span>
      <div class="icon-info">
        <div>
          <img src="@/assets/img/icon/view.svg" />
          {{ post.hit }}
        </div>
        <div>
          <img src="@/assets/img/icon/like.svg" />
          {{ post.totalLike }}
        </div>
        <div>
          <img src="@/assets/img/icon/answer.svg" />
          {{ post.totalComment }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.item {
  display: grid;
  grid-template-columns: 5fr 1fr;
  padding: 40px 0;
  height: 160px;
  border-bottom: 1px solid var(--border-gray);
}
.left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
  height: 100%;
}
.left p {
  width: 1200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: end;
}

.icon-info {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}
.icon-info div {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  font-size: 16px;
  font-weight: 600;
  color: var(--gray);
}
.icon-info div img {
  width: 20px;
}
</style>
