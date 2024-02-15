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

//
//
//
////////////////////////////////////// 달력용 id increase //////////////
const eventGuid = 0;
function createEventId() {
  return String(eventGuid++);
}

//
//
//
////////////////////////////////////// fullCal 형식으로 변환 /////////////
function convertSchedule(data) {
  return data.map((item) => ({
    id: item.scheduleId, // 고유 ID
    title: item.title, // 이벤트 제목
    start: item.startDate, // 시작 날짜
    end: item.endDate, // 종료 날짜 (선택 사항)
    content: item.content,
    type: "schedule", // 이벤트 유형 추가
    // 다른 필요한 속성들을 여기에 추가할 수 있습니다
  }));
}
function convertSelection(data) {
  return data.map((item) => ({
    id: item.applyStatusId, // 고유 ID
    title: `${item.title} ${item.type}`, // item.title과 item.type을 이어붙임
    start: item.date, // 시작 날짜
    step: item.step,
    type: "selection",
    // 다른 필요한 속성들을 여기에 추가할 수 있습니다
  }));
}
function convertRecruit(data) {
  return data.map((item) => ({
    id: item.myRecruitId, // 고유 ID
    title: item.title, // 이벤트 제목
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
  currentEvents.value = [
    ...convertSchedule(schedules),
    ...convertSelection(selections),
    ...convertRecruit(myRecruits),
  ];

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

//
//
//
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

//
//
//
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

//
//
//
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

//
//
//
////////////////////////////////////// schedule 수정 ////////
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
    const response = await axios.patch(
      `${import.meta.env.VITE_API_BASE_URL}/schedule/update`,
      newSchedule,
      config
    );
    console.log(response.data);
    // router.push();
  } catch (error) {
    console.error(error);
  }
};

//
//
//
////////////////////////////////////// schedule 삭제 ////////
const deleteSchedule = async () => {
  const isConfirmed = confirm("삭제하시겠습니까?");

  if (isConfirmed) {
    try {
      const config = {
        headers: {
          Authorization: `${authStore.accessToken}`,
        },
      };
      const res = await axios.delete(
        `${import.meta.env.VITE_API_BASE_URL}/schedule/delete/3`,
        config
      );
      console.log(res.data);
      // router.push();

      // Optionally, emit an event to inform the parent component to update the comment list
    } catch (error) {
      console.error("삭제 실패: ", error);
    }
  } else {
    console.log("삭제하지 않음.");
  }
};

//
//
//
////////////////////////////////////// schedule 선택 ////////
const selectedEvent = ref("");
function selectEvent(event) {
  selectedEvent.value = event;
  console.log("selectedEvent : ", selectedEvent.value);

  if (event.type === "schedule") {
    toggleSchedule();
  } else if (event.type === "selection") {
    toggleSelected();
  } else if (event.type === "recruit") {
    toggleRecruit();
  }
}

//
//
//
////////////////////////////////////// modal ////////////////
const isCreateOpen = ref(false); // 모달 상태를 관리하는 변수
function toggleCreate() {
  isCreateOpen.value = !isCreateOpen.value;
}

const isScheduleOpen = ref(false); // 모달 상태를 관리하는 변수
function toggleSchedule() {
  isScheduleOpen.value = !isScheduleOpen.value;
  if (!isScheduleOpen.value) {
    selectedEvent.value = null; // 모달을 열 때가 아닌 닫을 때 상태를 초기화
  }
}
const isSelectedOpen = ref(false); // 모달 상태를 관리하는 변수
function toggleSelected() {
  isSelectedOpen.value = !isSelectedOpen.value;
}
const isRecruitOpen = ref(false); // 모달 상태를 관리하는 변수
function toggleRecruit() {
  isRecruitOpen.value = !isRecruitOpen.value;
}
//
//
//
////////////////////////////////////// 수정 ////////////////
const isUpdateMode = ref(false); // 수정 모드의 활성화 여부를 추적
function showUpdateForm() {
  isUpdateMode.value = true;
}
function cancelUpdate() {
  isUpdateMode.value = false;
}

//
//
//
////////////////////////////////////// log ///////////////
function check(item) {
  console.log(item.event.title);
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
            <a class="btn solid-c h-bright a-dark" @click="toggleCreate"
              >일정 등록</a
            >
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
                <span class="f-color-g f-size-14"
                  >시작일　|　{{ formatTime(event.start) }}</span
                >
                <span class="f-color-g f-size-14"
                  >마감일　|　{{ formatTime(event.end) }}</span
                >
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
    <a @click="toggleCreate" class="exit-btn"
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

  <div v-if="isScheduleOpen" class="modal">
    <a @click="toggleSchedule" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
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
        <a class="btn solid-c h-bright a-dark" @click="showUpdateForm"
          >수정하기</a
        >
        <a class="btn lined-bg h-solid-g a-dark" @click="deleteSchedule"
          >삭제하기</a
        >
      </div>
    </div>
    <div class="content" v-if="isUpdateMode">
      <div class="detail">
        <input type="text" v-model="newSchedule.title" class="input" />
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
      <div>
        <a class="btn solid-c h-bright a-dark" @click="updateSchedule"
          >저장하기</a
        >
        <a class="btn lined-bg h-solid-g a-dark" @click="cancelUpdate">취소</a>
      </div>
    </div>
  </div>

  <div v-if="isSelectedOpen" class="modal">
    <a @click="toggleSelected" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
    <h1 class="title">{{ selectedEvent.title }}</h1>
    <div class="content" v-if="!isUpdateMode">
      <div class="detail">
        <p>일정　|　{{ selectedEvent.start }}</p>
        <br />
        <p>단계　|　{{ selectedEvent.step }}</p>
      </div>
      <RouterLink
        class="btn lined-c h-solid-c a-bright"
        :to="{ name: 'BlogReview' }"
        >전형 리뷰 바로가기</RouterLink
      >
    </div>
  </div>

  <div v-if="isRecruitOpen" class="modal">
    <a @click="toggleRecruit" class="exit-btn"
      ><i class="fa-solid fa-xmark fa-xl"></i
    ></a>
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
