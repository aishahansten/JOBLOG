<template>
  <div class="container">
    <div class="title">알람창</div>
    <div class="alarm-list">
      <AlarmList :alarmList="alarmList" />
    </div>
  </div>
</template>

<script setup>
import AlarmList from "./components/AlarmList.vue";
import { ref, onMounted } from "vue";
import { useAlarmStore } from "@/stores/alarm";

const alarmStore = useAlarmStore();
const alarmList = ref([]);

onMounted(async () => {
  await alarmStore.getAlarm();
  alarmList.value = alarmStore.alarms;
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid rgba(0, 0, 0, 0.184);
  border-radius: 10px;
}

.title {
  font-size: 20pt;
  font-weight: bold;
  margin: 20px;
}

.alarm-list {
  width: 1100px;
  border: 1px solid rgba(0, 0, 0, 0.184);
  border-radius: 10px;
  height: 750px;
}
</style>
