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
    start: formatDate(item.openingDate),
    end: formatDate(item.expirationDate),
    company: item.companyName,
    job: item.job,
    type: "recruit",
  }));
}

onMounted(async () => {
  await Promise.all([getSchedules(), getSelections(), getMyRecruits()]);

  currentEvents.value = [...convertSchedule(schedules), ...convertSelection(selections), ...convertRecruit(myRecruits)];

  filterTodayEvents();
  getSchedules();
});

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
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");
  const hours = date.getHours().toString().padStart(2, "0");
  const minutes = date.getMinutes().toString().padStart(2, "0");

  return `${month}/${day} ${hours}:${minutes}`;
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
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
    }
  }
};

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
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login");
    } else {
    }
  }
};

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
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
    }
  }
};

const createSchedule = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newSchedule.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/schedule/register`,
      newSchedule.value,
      config
    );
    newSchedule.value = {
      userId: authStore.userInfo.sub,
      title: "일정 제목을 입력하세요",
      content: "일정 설명",
      startDate: new Date("2024-02-08T00:00:00"),
      endDate: new Date("2024-02-08T00:00:00"),
    };
  } catch (error) {
    console.error(error);
  }
};

const newSchedule = ref({
  userId: authStore.userInfo.sub,
  title: "일정 제목을 입력하세요",
  content: "일정 설명",
  startDate: new Date("2024-02-08T00:00:00"),
  endDate: new Date("2024-02-08T00:00:00"),
});

const updateSchedule = async () => {
  try {
    authStore.updateUserInfoFromToken();
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };

    const newSchedule = {
      scheduleId: 2,
      userId: authStore.userInfo.sub,
      title: "test title2222222",
      content: "test content22222222",
      startDate: new Date("2024-02-08T00:00:00"),
      endDate: new Date("2024-02-08T00:00:00"),
    };
    const response = await axios.patch(`${import.meta.env.VITE_API_BASE_URL}/schedule/update`, newSchedule, config);
  } catch (error) {
    console.error(error);
  }
};

const deleteSchedule = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/schedule/delete/3`, config);
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
  }
};

const selectedEvent = ref("");
function selectEvent(event) {
  selectedEvent.value = event;

  if (event.type === "schedule") {
    toggleSchedule();
  } else if (event.type === "selection") {
    toggleSelected();
  } else if (event.type === "recruit") {
    toggleRecruit();
  }
}

const isCreateOpen = ref(false);
function toggleCreate() {
  isCreateOpen.value = !isCreateOpen.value;
}

const isScheduleOpen = ref(false);
function toggleSchedule() {
  isScheduleOpen.value = !isScheduleOpen.value;
  if (!isScheduleOpen.value) {
    selectedEvent.value = null;
  }
}
const isSelectedOpen = ref(false);
function toggleSelected() {
  isSelectedOpen.value = !isSelectedOpen.value;
}
const isRecruitOpen = ref(false);
function toggleRecruit() {
  isRecruitOpen.value = !isRecruitOpen.value;
}

const isUpdateMode = ref(false);
function showUpdateForm() {
  isUpdateMode.value = true;
}
function cancelUpdate() {
  isUpdateMode.value = false;
}
</script>

<template>
  <div class="container">
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
          <div class="btns">
            <a class="btn solid-c h-bright a-dark" @click="toggleCreate">일정 등록</a>
          </div>
        </div>
        <div class="tasks" v-if="selectedDateEvents.length > 0">
          <div
            v-for="event in selectedDateEvents"
            :key="event.id"
            class="task pointer h-transparent-c"
            @click="selectEvent(event)"
          >
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

  <div v-if="isCreateOpen" class="modal">
    <a @click="toggleCreate" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
    <h1 class="title">일정 추가하기</h1>
    <div class="content">
      <div class="detail">
        <input type="text" v-model="newSchedule.title" class="input" placeholder="일정 제목" />
        <input type="datetime-local" v-model="newSchedule.startDate" class="input" />
        <input type="datetime-local" v-model="newSchedule.endDate" class="input" />
        <textarea v-model="newSchedule.content" class="input" />
      </div>
      <a class="btn solid-c h-bright a-dark" @click="createSchedule">저장하기</a>
    </div>
  </div>

  <div v-if="isScheduleOpen" class="modal">
    <a @click="toggleSchedule" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
    <h1 class="title">{{ selectedEvent.title }}</h1>
    <div class="content" v-if="!isUpdateMode">
      <div class="detail">
        <p>일정 시작　|　{{ selectedEvent.start }}</p>
        <br />
        <p>일정 마감　|　{{ selectedEvent.end }}</p>
        <br />
        <p>일정 상세　|　{{ selectedEvent.content }}</p>
      </div>
      <div>
        <a class="btn solid-c h-bright a-dark" @click="showUpdateForm">수정하기</a>
        <a class="btn lined-bg h-solid-g a-dark" @click="deleteSchedule">삭제하기</a>
      </div>
    </div>
    <div class="content" v-if="isUpdateMode">
      <div class="detail">
        <input type="text" v-model="newSchedule.title" class="input" />
        <input type="datetime-local" v-model="newSchedule.startDate" class="input" />
        ~
        <input type="datetime-local" v-model="newSchedule.endDate" class="input" />
        <textarea v-model="newSchedule.content" class="input" />
      </div>
      <div>
        <a class="btn solid-c h-bright a-dark" @click="updateSchedule">저장하기</a>
        <a class="btn lined-bg h-solid-g a-dark" @click="cancelUpdate">취소</a>
      </div>
    </div>
  </div>

  <div v-if="isSelectedOpen" class="modal">
    <a @click="toggleSelected" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
    <h1 class="title">{{ selectedEvent.title }}</h1>
    <div class="content" v-if="!isUpdateMode">
      <div class="detail">
        <p>일정　|　{{ selectedEvent.start }}</p>
        <br />
        <p>단계　|　{{ selectedEvent.step }}</p>
      </div>
      <RouterLink class="btn lined-c h-solid-c a-bright" :to="{ name: 'BlogReview' }">전형 리뷰 바로가기</RouterLink>
    </div>
  </div>

  <div v-if="isRecruitOpen" class="modal">
    <a @click="toggleRecruit" class="exit-btn"><i class="fa-solid fa-xmark fa-xl"></i></a>
    <h1 class="title">{{ selectedEvent.title }}</h1>
    <div class="content" v-if="!isUpdateMode">
      <div class="detail">
        <p>회사명　|　{{ selectedEvent.company }}</p>
        <br />
        <p>일정 시작　|　{{ selectedEvent.start }}</p>
        <br />
        <p>일정 마감　|　{{ selectedEvent.end }}</p>
      </div>
      <RouterLink
        v-if="selectedEvent && selectedEvent.id"
        class="btn lined-c h-solid-c a-bright"
        :to="{ name: 'JobDetail', params: { id: selectedEvent.id } }"
      >
        {{ selectedEvent.id }}
        공고 상세 바로가기</RouterLink
      >
    </div>
  </div>
</template>

<style scoped>
.container {
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

.modal {
  display: flex;
  flex-direction: column;

  width: 600px;
  height: 600px;
  padding: 50px;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 3;

  border: 1px solid var(--border-gray);
  border-radius: 20px;
  background-color: white;
}
.exit-btn {
  position: absolute;
  top: 30px;
  right: 30px;
}

.modal .title {
  padding: 20px 0;
  border-bottom: 1px solid var(--border-gray);
}
.modal .content {
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  height: 100%;
  padding: 40px 0;
}

.modal .detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
}
.modal .detail * {
  width: 100%;
}
.modal .detail textarea {
  height: 100px;
  resize: none;
}
</style>
