<template>
  <div class="item-container">
    <div>알림 시각 : {{ alarm.alarmDate }}</div>
    <div class="alarm-item">
      <RouterLink :to="{ name: 'BlogReview' }">
        <div>채용번호: {{ alarm.userRecruitId }}</div>
      </RouterLink>
      <div>스케줄 번호: {{ alarm.scheduleId }}</div>
    </div>
    <div @click="deleteAlarm(alarm.alarmId)">알람 삭제</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAlarmStore } from "@/stores/alarm";
const alarmStore = useAlarmStore();
const props = defineProps({
  alarm: Object,
});

const deleteAlarm = async (id) => {
  await alarmStore.deleteAlarm(id);
};
</script>

<style scoped>
.item-container {
  display: flex;
  flex-direction: column;
}

.alarm-item {
}
</style>
