<script setup>
import { ref, onMounted } from "vue";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import "@/assets/css/home/todo.css";

import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";

const authStore = useAuthStore();
const currentEvents = ref([]);

const eventGuid = 0;
function createEventId() {
  return String(eventGuid++);
}

function convertSchedule(data) {
  return data.map((item) => ({
    id: item.scheduleId,
    title: item.title,
    start: item.startDate,
    end: item.endDate,
    content: item.content,
    type: "schedule",
  }));
}
function convertSelection(data) {
  return data.map((item) => ({
    id: item.applyStatusId,
    title: `${item.title} ${item.type}`,
    start: item.date,
    step: item.step,
    type: "selection",
  }));
}
function convertRecruit(data) {
  return data.map((item) => ({
    id: item.myRecruitId,
    title: item.title,
    start: formatDate(item.openingDate), // 시작 날짜
    end: formatDate(item.expirationDate), // 종료 날짜 (선택 사항)
    company: item.companyName,
    job: item.job,
    type: "recruit", // 이벤트 유형 추가
    // 다른 필요한 속성들을 여기에 추가할 수 있습니다
  }));
}

//
//
//
////////////////////////////////////// onMounted ////////////////
onMounted(async () => {
  await Promise.all([getSchedules(), getSelections(), getMyRecruits()]);
  // 이제 schedules, selections, myRecruits가 각각의 API 호출을 통해 채워졌다고 가정합니다.

  // FullCalendar 이벤트로 설정
  currentEvents.value = [...convertSchedule(schedules), ...convertSelection(selections), ...convertRecruit(myRecruits)];

  // FullCalendar 갱신을 위해 eventsSet 함수 호출 (필요한 경우)
  // 주의: FullCalendar Vue 컴포넌트가 이 방식으로 동적 업데이트를 지원하지 않을 수 있습니다.
  // eventsSet 메서드 대신, initialEvents prop을 업데이트하는 방식을 고려해야 할 수도 있습니다.

  filterTodayEvents(); // 초기 로딩 시 오늘의 이벤트를 필터링하여 selectedDateEvents에 할당
  getSchedules();
});

//
//
//
////////////////////////////////////// calendarOptions ////////////////
const calendarOptions = ref({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  headerToolbar: {
    left: "prev,next today",
    center: "title",
    right: "dayGridMonth,timeGridWeek,timeGridDay",
  },
  initialView: "dayGridMonth",
  initialEvents: currentEvents,
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  weekends: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  eventsSet: handleEvents,
});

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
  if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
    clickInfo.event.remove();
  }
}

function handleEvents(events) {
  currentEvents.value = events;
}

function formatTime(isoString) {
  const date = new Date(isoString);
  const month = (date.getMonth() + 1).toString().padStart(2, "0"); // 월을 2자리 숫자로 변환
  const day = date.getDate().toString().padStart(2, "0"); // 일을 2자리 숫자로 변환
  const hours = date.getHours().toString().padStart(2, "0"); // 시를 2자리 숫자로 변환
  const minutes = date.getMinutes().toString().padStart(2, "0"); // 분을 2자리 숫자로 변환

  return `${month}/${day} ${hours}:${minutes}`; // 원하는 형식으로 문자열을 조합하여 반환
}
function formatDate(isoString) {
  const date = new Date(isoString);
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");
  const hours = date.getHours().toString().padStart(2, "0");
  const minutes = date.getMinutes().toString().padStart(2, "0");

  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

// 여기서부터 편집된 코드

const selectedDateEvents = ref([]);

function filterTodayEvents() {
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  selectedDateEvents.value = currentEvents.value.filter((event) => {
    const eventStart = new Date(event.start);
    eventStart.setHours(0, 0, 0, 0);
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
    const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/schedule/${authStore.userInfo.sub}`, config);
    schedules = response.data;
    console.log(schedules);
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
      console.log("token", token);
    }
  }
};

//
//
//
////////////////////////////////////// selected 전체 조회 /////
let selections = [];

const getSelections = async () => {
  try {
    authStore.updateUserInfoFromToken();
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };
    const response = await axios.get(
      `${import.meta.env.VITE_API_BASE_URL}/selection/${authStore.userInfo.sub}`,
      config
    );
    selections = response.data;
    console.log(selections);
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
      // Handle other errors or a case where the error does not have a response object
      // console.log("token", token); // Logging the token for debugging purposes
    }
  }
};

//
//
//
////////////////////////////////////// myRecruit 전체 조회 /////
let myRecruits = [];

const getMyRecruits = async () => {
  try {
    authStore.updateUserInfoFromToken();
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };
    const response = await axios.get(
      `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
      config
    );
    myRecruits = response.data;
    console.log(myRecruits);
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login");
    } else {
      console.log("token", token);
    }
  }
};

// test
function check(a) {
  console.log("arg : ", a);
  console.log("arg.event : ", a.event);
}
</script>

<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<template>
  <div class="container">
    <div class="title">
      <h1 class="f-weight-t">이달의 일정</h1>
      <i class="fa-solid fa-angle-right fa-xl"></i>
    </div>

    <div class="full-cal">
      <div class="calendar">
        <FullCalendar class="demo-app-calendar" :options="calendarOptions">
          <template v-slot:eventContent="arg">
            <b>{{ arg.event.title }}</b>
          </template>
        </FullCalendar>
      </div>

      <div class="task-space">
        <div class="header">
          <h2 class="f-weight-t">나의 일정 목록</h2>
        </div>
        <div class="tasks" v-if="selectedDateEvents.length > 0">
          <div v-for="event in selectedDateEvents" :key="event.id" class="task" @click="selectEvent(event)">
            <div class="coloring"></div>
            <div class="task-info">
              <p class="f-size-20 f-weight-b">{{ event.title }}</p>
              <div style="display: flex; flex-direction: column; gap: 5px">
                <span class="f-color-g f-size-14">시작일　|　{{ formatTime(event.start) }}</span>
                <span class="f-color-g f-size-14">마감일　|　{{ formatTime(event.end) }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="tasks" v-else>
          <h3 class="f-color-g">일정을 등록해보세요!</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  padding: 30px 0;
}
.title {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 30px 0;
}

.header {
  display: flex;
  justify-content: space-between;
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

.calendar {
  margin-right: 20px;
}

.fc .fc-daygrid-day {
  height: 50px;
  width: 100px;
}

.task-space {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-left: 20px;
}
.task-space h2 {
  padding: 8px 0;
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
