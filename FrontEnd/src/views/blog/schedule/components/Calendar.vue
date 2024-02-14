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

////////////////////////////////////// onMounted ////////////////
onMounted(async () => {
  await Promise.all([getSchedules(), getSelections(), getMyRecruits()]);
  // 이제 schedules, selections, myRecruits가 각각의 API 호출을 통해 채워졌다고 가정합니다.

  // 변환된 이벤트 데이터를 currentEvents에 추가
  const convertedEvents = [
    ...convertToCalendarEvents(schedules),
    ...convertToCalendarEvents(selections),
    ...convertToCalendarEvents(myRecruits),
  ];

  // FullCalendar 이벤트로 설정
  currentEvents.value = [...convertedEvents];

  // FullCalendar 갱신을 위해 eventsSet 함수 호출 (필요한 경우)
  // 주의: FullCalendar Vue 컴포넌트가 이 방식으로 동적 업데이트를 지원하지 않을 수 있습니다.
  // eventsSet 메서드 대신, initialEvents prop을 업데이트하는 방식을 고려해야 할 수도 있습니다.
});

////////////////////////////////////// calendarOptions ////////////////
const calendarOptions = ref({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  headerToolbar: {
    left: "prev,next today",
    center: "title",
    right: "dayGridMonth,timeGridWeek,timeGridDay",
  },
  initialView: "dayGridMonth",
  initialEvents: currentEvents.value,
  // initialEvents: INITIAL_EVENTS,
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

////////////////////////////////////// 날짜별 필터링 /////////////
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

////////////////////////////////////// schedule 전체 조회 /////
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
      console.log("token", token);
    }
  }
};
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
      `${import.meta.env.VITE_API_BASE_URL}/selection/${
        authStore.userInfo.sub
      }`,
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
      `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${
        authStore.userInfo.sub
      }`,
      config
    );
    myRecruits = response.data;
    console.log(myRecruits);
  } catch (err) {
    if (err.response && err.response.status === 500) {
      router.push("/login2");
    } else {
      // Handle other errors or a case where the error does not have a response object
      console.log("token", token); // Logging the token for debugging purposes
    }
  }
};

////////////////////////////////////// fullCal 형식으로 변환 /////////////
function convertToCalendarEvents(data) {
  return data.map((item) => ({
    id: item.id, // 고유 ID
    title: item.title, // 이벤트 제목
    start: item.startDate, // 시작 날짜
    end: item.endDate, // 종료 날짜 (선택 사항)
    // 다른 필요한 속성들을 여기에 추가할 수 있습니다
  }));
}

////////////////////////////////////// schedule 생성 /////////
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
    console.log(response.data);
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

////////////////////////////////////// selection 생성 /////////
const createSelection = async () => {
  try {
    authStore.updateUserInfoFromToken();
    newSelection.value.userId = authStore.userInfo.sub;
    const config = {
      headers: {
        Authoriation: `${authStore.accessToken}`,
      },
    };

    const response = await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/selection/register`,
      newSelection.value,
      config
    );
    console.log(response.data);
    newSelection.value = {
      userId: authStore.userInfo.sub,
      myRecruitId: 1,
      companyName: "test title",
      companyCode: "test content",
      step: 1,
      state: "test state",
      date: new Date("2024-02-08T00:00:00"),
    };
    // router.push();
  } catch (error) {
    console.error(error);
  }
};

const newSelection = ref({
  userId: authStore.userInfo.sub,
  myRecruitId: 1,
  companyName: "test title",
  companyCode: "test content",
  step: 1,
  state: "test state",
  date: new Date("2024-02-08T00:00:00"),
});

////////////////////////////////////// schedule 선택 ////////
const selectedEvent = ref(null);
function selectEvent(event) {
  selectedEvent.value = event;
  toggleModal3();
}

////////////////////////////////////// modal ////////////////
const isModalOpen = ref(false); // 모달 상태를 관리하는 변수
function toggleModal() {
  isModalOpen.value = !isModalOpen.value;
}

const isModalOpen2 = ref(false); // 모달 상태를 관리하는 변수
function toggleModal2() {
  isModalOpen2.value = !isModalOpen2.value;
}

const isModalOpen3 = ref(false); // 모달 상태를 관리하는 변수
function toggleModal3() {
  if (!isModalOpen3.value) {
    selectedEvent.value = null; // 모달을 열 때가 아닌 닫을 때 상태를 초기화
  }
  isModalOpen3.value = !isModalOpen3.value;
}
</script>

<template>
  <div class="container">
    <div class="full-cal">
      <div class="calendar">
        <FullCalendar class="app-calendar" :options="calendarOptions">
          <template v-slot:eventContent="arg">
            <b>{{ arg.event.title }}</b>
          </template>
        </FullCalendar>
      </div>

      <div class="task-space">
        <div class="header">
          <h2 class="f-weight-t">나의 일정 목록</h2>
          <div class="btns">
            <!-- <RouterLink
              :to="{ name: 'CustomJobCreate' }"
              class="btn-s solid-c h-bright a-dark"
              >외부공고 등록</RouterLink
            > -->
            <a class="btn-s solid-c h-bright a-dark" @click="toggleModal2"
              >전형일정 등록</a
            >
            <a class="btn-s solid-c h-bright a-dark" @click="toggleModal"
              >기타일정 등록</a
            >
          </div>
        </div>
        <div class="tasks" v-if="selectedDateEvents.length > 0">
          <div
            v-for="event in selectedDateEvents"
            :key="event.id"
            class="task"
            @click="
              () => {
                selectEvent(event);
                toggleModal3();
              }
            "
          >
            <div class="coloring"></div>
            <div class="task-info">
              <b class="f-size-20">{{ event.title }}</b>
              {{ formatTime(event.startStr) }} ~ {{ formatTime(event.endStr) }}
            </div>
          </div>
        </div>
        <div class="tasks" v-else>
          <h3 class="f-color-g">일정을 등록해보세요!</h3>
        </div>
      </div>
    </div>
  </div>

  <div v-if="isModalOpen" class="modal">
    <a @click="toggleModal" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
    <h1 class="title">일정 추가하기</h1>
    <div class="content">
      <div class="detail">
        <input
          type="text"
          v-model="newSchedule.title"
          class="input"
          placeholder="일정 제목"
        />
        <input
          type="datetime-local"
          v-model="newSchedule.startDate"
          class="input"
        />
        ~
        <input
          type="datetime-local"
          v-model="newSchedule.endDate"
          class="input"
        />
        <textarea v-model="newSchedule.content" class="input" />
      </div>
      <a class="btn solid-c h-bright a-dark" @click="createSchedule"
        >저장하기</a
      >
    </div>
  </div>

  <div v-if="isModalOpen2" class="modal">
    <a @click="toggleModal2" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
    <h1 class="title">전형일정 추가하기</h1>
    <div class="content">
      <div class="detail">
        <input
          type="text"
          v-model="newSchedule.title"
          class="input"
          placeholder="일정 제목"
        />
        <input
          type="datetime-local"
          v-model="newSchedule.startDate"
          class="input"
        />
        ~
        <input
          type="datetime-local"
          v-model="newSchedule.endDate"
          class="input"
        />
        <textarea v-model="newSchedule.content" class="input" />
      </div>
      <a class="btn solid-c h-bright a-dark" @click="createSelection"
        >저장하기</a
      >
    </div>
  </div>

  <div v-if="isModalOpen3" class="modal">
    <a @click="toggleModal3" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
    <h1 class="title">일정 조회하기</h1>
    <div class="content">
      <div class="detail">
        <p>일정 제목 | {{ selectedEvent.value?.title }}</p>
        <p>일정 시작 | {{ selectedEvent.value?.start }}</p>
        <p>일정 마감 | {{ selectedEvent.value?.end }}</p>
        <p>일정 내용 | {{ selectedEvent.value?.content }}</p>
        <input
          type="text"
          v-model="newSchedule.title"
          class="input"
          placeholder="일정 제목"
        />
        <input
          type="datetime-local"
          v-model="newSchedule.startDate"
          class="input"
        />
        ~
        <input
          type="datetime-local"
          v-model="newSchedule.endDate"
          class="input"
        />
        <textarea v-model="newSchedule.content" class="input" />
      </div>
      <a class="btn solid-c h-bright a-dark" @click="createSelection"
        >저장하기</a
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

/* modal */
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
  top: 60px;
  right: 60px;
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
}
</style>
