<script setup>
import { ref, onMounted } from "vue";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { INITIAL_EVENTS, createEventId } from "@/event-utils";
import "@/assets/css/home/todo.css";

import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";

const authStore = useAuthStore();

const calendarOptions = ref({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  headerToolbar: {
    left: "prev,next today",
    center: "title",
    right: "dayGridMonth,timeGridWeek,timeGridDay",
  },
  initialView: "dayGridMonth",
  initialEvents: INITIAL_EVENTS,
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  weekends: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  eventsSet: handleEvents,
});

const currentEvents = ref([]);

function handleWeekendsToggle() {
  calendarOptions.value.weekends = !calendarOptions.value.weekends;
}

function handleDateSelect(selectInfo) {
  const selectedDate = new Date(selectInfo.startStr).setHours(0, 0, 0, 0);

  selectedDateEvents.value = currentEvents.value.filter((event) => {
    const eventStart = new Date(event.start).setHours(0, 0, 0, 0);
    return eventStart === selectedDate;
  });
}

function handleEventClick(clickInfo) {
  if (
    confirm(
      `Are you sure you want to delete the event '${clickInfo.event.title}'`
    )
  ) {
    clickInfo.event.remove();
  }
}

function handleEvents(events) {
  currentEvents.value = events;
}

function formatTime(isoString) {
  const date = new Date(isoString);
  return date.toLocaleTimeString("ko-KR", {
    hour: "2-digit",
    minute: "2-digit",
    timeZone: "Asia/Seoul",
  });
}

// 여기서부터 편집된 코드

const selectedDateEvents = ref([]);

// 오늘의 이벤트를 필터링하여 selectedDateEvents에 설정하는 함수
function filterTodayEvents() {
  const today = new Date();
  today.setHours(0, 0, 0, 0); // 시간을 00:00:00으로 설정하여 오늘 날짜만 비교

  selectedDateEvents.value = currentEvents.value.filter((event) => {
    const eventStart = new Date(event.start);
    eventStart.setHours(0, 0, 0, 0); // 이벤트 시작 날짜의 시간도 00:00:00으로 설정
    return eventStart.getTime() === today.getTime();
  });
}

// 컴포넌트가 마운트될 때 오늘의 이벤트를 필터링하여 표시
onMounted(() => {
  filterTodayEvents(); // 초기 로딩 시 오늘의 이벤트를 필터링하여 selectedDateEvents에 할당
  getSchedules();
});

// schedule 전체 조회

let schedules = [];

const getSchedules = async () => {
  try {
    authStore.updateUserInfoFromToken();
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };
    const response = await axios.get(
      `${import.meta.env.VITE_API_BASE_URL}/schedule/${authStore.userInfo.sub}`,
      config
    );
    schedules = response.data;
    console.log(schedules);
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
      // Handle other errors or a case where the error does not have a response object
      console.log("token", token); // Logging the token for debugging purposes
    }
  }
};

// schedule 생성
const createSchedule = async () => {
  try {
    authStore.updateUserInfoFromToken();
    const config = {
      headers: {
        Authorization: `${authStore.accessToken}`, // 오타 수정: 'Authoriation' -> 'Authorization'
      },
    };

    const response = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/schedule/register`,
      newSchedule.value,
      config
    );

    // 캘린더에 스케줄 추가
    const createdSchedule = response.data;
    calendarOptions.value.getApi().addEvent({
      id: createdSchedule.id, // 또는 createEventId() 사용
      title: createdSchedule.title,
      start: createdSchedule.startDate,
      end: createdSchedule.endDate,
      allDay: true,
    });

    console.log("스케줄 생성:", response.data);
  } catch (error) {
    console.error("스케줄 생성 실패:", error);
  }
};

// myRecruit 생성

const createMyRecruit = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newMyRecruit.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/myRecruit/register`,
      newMyRecruit.value,
      config
    );
    console.log(response.data);
    newMyRecruit.value = {
      userId: authStore.userInfo.sub,
      companyName: "test company",
      companyCODE: "test code",
      job: "test job",
      openingDate: new Date("2024-02-08T00:00:00"),
      expirationDate: new Date("2024-02-08T00:00:00"),
      description: "test description",
    };
    // router.push();
  } catch (error) {
    console.error(error);
  }
};

const newMyRecruit = ref({
  userId: authStore.userInfo.sub,
  companyName: "test company",
  companyCODE: "test code",
  job: "test job",
  openingDate: new Date("2024-02-08T00:00:00"),
  expirationDate: new Date("2024-02-08T00:00:00"),
  description: "test description",
});
</script>

<template>
  <div class="container">
    <div class="full-cal">
      <div class="calendar">
        <FullCalendar class="demo-app-calendar" :options="calendarOptions">
          <template v-slot:eventContent="arg">
            <b>{{ arg.event.title }}</b>
            {{ arg.timeText }}
          </template>
        </FullCalendar>
      </div>

      <div class="task-space">
        <div class="header">
          <h2 class="f-weight-t">나의 일정 목록</h2>
        </div>
        <div class="tasks">
          <div v-for="event in selectedDateEvents" :key="event.id" class="task">
            <div class="coloring"></div>
            <div class="task-info">
              <b class="f-size-20">{{ event.title }}</b>
              {{ formatTime(event.startStr) }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.header {
  padding: 10px 0;
  border-bottom: 1px solid var(--border-gray);
}
.header div {
  display: flex;
  align-items: center;
  gap: 10px;
}
.btns {
  display: flex;
  justify-content: end;
}

/* calendar */
.calendar {
  margin-right: 20px;
}

.fc .fc-daygrid-day {
  height: 50px;
  width: 100px;
}

/* tasks */
.task-space {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.task-space h2 {
  padding: 10px 0;
  text-align: end;
}
.tasks {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.task {
  display: flex;
  flex-direction: row;
  align-content: center;
  gap: 10px;
  height: 100px;
  border: none;
}
.coloring {
  width: 10px;
  border-radius: 10px;
  background-color: var(--main-blue);
}
.task-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 10px;
  height: 100%;
}
</style>
