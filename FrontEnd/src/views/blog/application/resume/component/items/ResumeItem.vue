<script setup>
const props = defineProps({
  resu: Object,
});
</script>

<template>
  <div class="resume">
    <div class="resume-title">
      <div class="ellipsis">
        <h3>{{ resu.title }}</h3>
        <p>
          <b class="f-color-g">{{ resu.job }}</b>
        </p>
      </div>
    </div>
    <div class="resume-go">
      <RouterLink :to="{ name: 'ResumeDetail', params: { id: resu.id } }" class="btn solid-g h-solid-c a-bright">
        입사지원서 작성　<i class="fa-solid fa-pen-nib"></i>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.resume {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
}
.resume-title {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-width: 350px;
  display: block;
}
</style>
