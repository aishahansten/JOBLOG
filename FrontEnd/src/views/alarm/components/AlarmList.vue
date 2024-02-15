<template>
  <div>
    <AlarmItem v-for="alarm in alarmList" :key="alarm.alarmId" :alarm="alarm" />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAlarmStore } from "@/stores/alarm";
import AlarmItem from "./AlarmItem.vue";

const props = defineProps({
  alarmList: Array,
});
</script>

<style lang="scss" scoped></style>
