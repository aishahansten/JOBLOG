<template>
  <div class="item-container">
    <div class="alarm-item">
      <div v-if="alarm.alarmType == 1">
        <div>오늘 {{ alarm.companyName }}의 {{ alarm.title }}이 마감됩니다.</div>
        <button>채용 공고로 이동</button>
      </div>
      <div v-if="alarm.alarmType == 2">
        <div>{{ alarm.companyName }}의 {{ alarm.title }}에 지원하셨나요? 리뷰를 남겨보세요.</div>
        <button>리뷰 남기러 가기</button>
        <button>다음 전형 일정 추가</button>
      </div>
      <div v-if="alarm.alarmType == 3">
        <div>오늘 {{ alarm.title }} 일정이 예정되어 있습니다.</div>
        <button>일정으로 이동</button>
      </div>
      <div v-if="alarm.alarmType == 4">
        <div>{{ alarm.chatteeName }}님이 커피챗을 신청하였습니다.</div>
        <button>커피챗으로 이동</button>
      </div>
      <div v-if="alarm.alarmType == 5">
        <div>{{ alarm.chatterName }}님이 커피챗을 수락하였습니다.</div>
        <button>커피챗으로 이동</button>
      </div>
      <div v-if="alarm.alarmType == 6">
        <div>{{ alarm.chatterName }}님이 커피챗을 거절하였습니다.</div>
      </div>
      <div v-if="alarm.alarmType == 7">
        <div>오늘 {{ alarm.companyName }}의 {{ alarm.title }}이 마감됩니다.</div>
        <button>전형으로 이동</button>
      </div>
      <div v-if="alarm.alarmType == 8">
        <div>{{ alarm.companyName }}의 {{ alarm.title }}에 지원하셨나요? 리뷰를 남겨보세요.</div>
        <button>리뷰 남기러 가기</button>
        <button>다음 전형 일정 추가</button>
      </div>
    </div>
    <div>예정 시각 : {{ alarm.alarmDate }}</div>
    <div>알림 읽음</div>
    <div @click="deleteAlarm(alarm.alarmId)">알림 삭제</div>
  </div><br><br>
  
  <!-- <RouterLink :to="{ name: 'BlogReview' }">
    <div>채용번호: {{ alarm.userRecruitId }}</div>
      </RouterLink> -->
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
