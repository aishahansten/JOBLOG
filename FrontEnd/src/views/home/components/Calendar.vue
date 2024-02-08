<template>
  <div class="container">
    <div class="header">
      <div>
        <h1 class="f-weight-t">나의 일정 관리</h1>
        <i class="fa-solid fa-angle-right fa-xl"></i>
      </div>
    </div>

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
        <h2 class="f-weight-t">나의 일정 목록</h2>
        <div class="tasks">
          <div v-for="event in currentEvents" :key="event.id" class="task">
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

.header div {
  display: flex;
  align-items: center;
  gap: 10px;
}

/* calendar */
.calendar {
  margin-right: 20px;
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
  border-bottom: 1px solid var(--border-gray);
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

<script setup>
import { ref, onMounted } from "vue";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { INITIAL_EVENTS, createEventId } from "@/event-utils";
import "@/assets/css/home/todo.css";

// Google API 클라이언트 ID 및 API 키
const CLIENT_ID = import.meta.env.VITE_GOOGLE_CLIENT_ID;
const API_KEY = import.meta.env.VITE_GOOGLE_API_KEY;

// 필요한 변수 정의
const content = ref("");
const isAuthorized = ref(false);

// Google API 및 GSI 초기화
let tokenClient;
let gapiInited = false;
let gisInited = false;

// Google API 및 GSI 로딩 후 실행될 함수
function gapiLoaded() {
  gapi.load("client", initializeGapiClient);
}

function gisLoaded() {
  tokenClient = google.accounts.oauth2.initTokenClient({
    client_id: CLIENT_ID,
    scope: "https://www.googleapis.com/auth/calendar",
    callback: "", // defined later
  });
  gisInited = true;
  maybeEnableButtons();
}

async function initializeGapiClient() {
  await gapi.client.init({
    apiKey: API_KEY,
    discoveryDocs: [
      "https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest",
    ],
  });
  gapiInited = true;
  maybeEnableButtons();
}

// 버튼 활성화 여부 결정
function maybeEnableButtons() {
  if (gapiInited && gisInited) {
    const token = gapi.client.getToken();
    isAuthorized.value = token !== null;
  }
}

// 인증, 로그아웃, 이벤트 추가 등의 메소드 정의
function handleAuthClick() {
  tokenClient.callback = async (resp) => {
    if (resp.error !== undefined) {
      throw resp;
    }
    isAuthorized.value = true;
    await listUpcomingEvents();
  };

  if (gapi.client.getToken() === null) {
    tokenClient.requestAccessToken({ prompt: "consent" });
  } else {
    tokenClient.requestAccessToken({ prompt: "" });
  }
}

function handleSignoutClick() {
  const token = gapi.client.getToken();
  if (token !== null) {
    google.accounts.oauth2.revoke(token.access_token);
    gapi.client.setToken("");
    content.value = "";
    isAuthorized.value = false;
  }
}

// Google Calendar 이벤트 목록 조회
async function listUpcomingEvents() {
  let response;
  try {
    const request = {
      calendarId: "primary",
      timeMin: new Date().toISOString(),
      showDeleted: false,
      singleEvents: true,
      maxResults: 10,
      orderBy: "startTime",
    };
    response = await gapi.client.calendar.events.list(request);
  } catch (err) {
    content.value = err.message;
    return;
  }

  const events = response.result.items;
  if (!events || events.length === 0) {
    content.value = "No events found.";
    return;
  }

  // Flatten to string to display
  content.value = events.reduce(
    (str, event) =>
      `${str}${event.summary} (${event.start.dateTime || event.start.date})\n`,
    "Events:\n"
  );
}

// Google Calendar 이벤트 추가
function handleInsertClick() {
  const insertEvent = {
    summary: "Google I/O 2025",
    location: "800 Howard St., San Francisco, CA 94103",
    description: "A chance to hear more about Google's developer products.",
    start: {
      dateTime: "2024-01-26T09:00:00-07:00",
      timeZone: "America/Los_Angeles",
    },
    end: {
      dateTime: "2024-01-26T17:00:00-07:00",
      timeZone: "America/Los_Angeles",
    },
    recurrence: ["RRULE:FREQ=DAILY;COUNT=2"],
    attendees: [{ email: "lpage@example.com" }, { email: "sbrin@example.com" }],
    reminders: {
      useDefault: false,
      overrides: [
        { method: "email", minutes: 24 * 60 },
        { method: "popup", minutes: 10 },
      ],
    },
  };

  const request = gapi.client.calendar.events.insert({
    calendarId: "primary",
    resource: insertEvent,
  });

  request.execute((insertEvent) => {
    content.value += "Event created: " + insertEvent.htmlLink + "\n";
  });
}

// 스크립트 로딩
onMounted(() => {
  const gapiScript = document.createElement("script");
  gapiScript.async = true;
  gapiScript.defer = true;
  gapiScript.src = "https://apis.google.com/js/api.js";
  gapiScript.onload = gapiLoaded;
  document.body.appendChild(gapiScript);

  const gsiScript = document.createElement("script");
  gsiScript.async = true;
  gsiScript.defer = true;
  gsiScript.src = "https://accounts.google.com/gsi/client";
  gsiScript.onload = gisLoaded;
  document.body.appendChild(gsiScript);
});

// fullcal
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
  let title = prompt("Please enter a new title for your event");
  let calendarApi = selectInfo.view.calendar;

  calendarApi.unselect();

  if (title) {
    calendarApi.addEvent({
      id: createEventId(),
      title,
      start: selectInfo.startStr,
      end: selectInfo.endStr,
      allDay: selectInfo.allDay,
    });
  }
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
</script>

<!-- <script>
import { defineComponent } from "vue";

import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { INITIAL_EVENTS, createEventId } from "@/event-utils";

import "@/assets/css/home/todo.css";

export default defineComponent({
  components: {
    FullCalendar,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin, // needed for dateClick
        ],
        headerToolbar: {
          left: "dayGridMonth,timeGridWeek,timeGridDay",
          center: "",
          right: "title",
        },
        initialView: "dayGridMonth",
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: false,
        selectable: true,
        selectMirror: false,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventsSet: this.handleEvents,
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
    };
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },
    handleDateSelect(selectInfo) {
      let title = prompt("Please enter a new title for your event");
      let calendarApi = selectInfo.view.calendar;

      calendarApi.unselect(); // clear date selection

      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay,
        });
      }
    },
    handleEventClick(clickInfo) {
      if (
        confirm(
          `Are you sure you want to delete the event '${clickInfo.event.title}'`
        )
      ) {
        clickInfo.event.remove();
      }
    },
    handleEvents(events) {
      this.currentEvents = events;
    },
    formatTime(isoString) {
      const date = new Date(isoString);
      return date.toLocaleTimeString("ko-KR", {
        hour: "2-digit",
        minute: "2-digit",
        stimeZone: "Asia/Seoul",
      });
    },
  },
});
</script> -->
