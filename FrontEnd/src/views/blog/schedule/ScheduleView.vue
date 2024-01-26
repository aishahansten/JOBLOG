<script setup>
import { ref, onMounted } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { INITIAL_EVENTS, createEventId } from '@/event-utils';
import '@/assets/css/home/todo.css';

import { RouterLink } from 'vue-router';
import SubNav from '../_component/SubNav.vue';

const calendarOptions = ref({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  headerToolbar: {
    left: 'prev,next today',
    center: 'title',
    right: 'dayGridMonth,timeGridWeek,timeGridDay',
  },
  initialView: 'dayGridMonth',
  initialEvents: INITIAL_EVENTS,
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  weekends: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  eventsSet: handleEvents
});

const currentEvents = ref([]);

function handleWeekendsToggle() {
  calendarOptions.value.weekends = !calendarOptions.value.weekends;
}

function handleDateSelect(selectInfo) {
  let title = prompt('Please enter a new title for your event');
  let calendarApi = selectInfo.view.calendar;

  calendarApi.unselect();

  if (title) {
    calendarApi.addEvent({
      id: createEventId(),
      title,
      start: selectInfo.startStr,
      end: selectInfo.endStr,
      allDay: selectInfo.allDay
    });
  }
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
  return date.toLocaleTimeString('ko-KR', {
    hour: '2-digit',
    minute: '2-digit',
    timeZone: 'Asia/Seoul'
  });
}
</script>

<template>
  <main class="full-cal">
    <section class="calendar">
      <FullCalendar class="demo-app-calendar" :options="calendarOptions">
        <template v-slot:eventContent="arg">
          <b>{{ arg.event.title }}</b>
          {{ arg.timeText }}
        </template>
      </FullCalendar>
    </section>
    <section class="tasks">
      <h2>오늘의 일정</h2>
      <div v-for="event in currentEvents" :key="event.id" class="task">
        <b>{{ event.title }}</b>
        {{ formatTime(event.startStr) }}
      </div>
    </section>
  </main>
  <SubNav/>

  <div class="blog-schedule">
      <div>
          <h1>취준로그</h1>
      </div>
      <div class="calendar-container">
        <div class="calendar">
            달력 들어갈 자리
        </div>
        <div class="schedule">
            일정 들어갈 자리
        </div>
      </div>
  </div>
</template>

<style scoped>
  .blog-schedule {
    display: grid;
    grid-template-rows: 1fr 9fr;
    width: 1344px;
    height: 600px;
    gap: 10px;
  }

  .calendar-container {
    display: grid;
    grid-template-columns: 7fr 3fr;
    gap: 20px;
  }

  .calendar {
    border: 1px solid black;
    border-radius: 8px;
  }

  .schedule {
    border: 1px solid black;
    border-radius: 8px;
  }

</style>
