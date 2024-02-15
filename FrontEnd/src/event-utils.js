let eventGuid = 0;
let todayStr = new Date().toISOString().replace(/T.*$/, "");
let today = new Date();

function getOffsetDate(offset) {
  const date = new Date(today);
  date.setDate(date.getDate() + offset);
  return date.toISOString().replace(/T.*$/, "");
}

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: "신한은행 서류지원",
    start: todayStr,
    end: todayStr + "T13:00:00",
  },
  {
    id: createEventId(),
    title: "국민은행 면접",
    start: todayStr + "T12:00:00",
    end: todayStr + "T13:00:00",
  },
  {
    id: createEventId(),
    title: "삼성SDS 코테",
    start: getOffsetDate(-2),
    end: todayStr + "T13:00:00",
  },
  {
    id: createEventId(),
    title: "소프티어 코테",
    start: getOffsetDate(2),
    end: todayStr + "T13:00:00",
  },
];

export function createEventId() {
  return String(eventGuid++);
}
