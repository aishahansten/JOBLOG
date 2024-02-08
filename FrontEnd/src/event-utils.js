let eventGuid = 0;
let todayStr = new Date().toISOString().replace(/T.*$/, ""); // YYYY-MM-DD of today
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
  },
  {
    id: createEventId(),
    title: "국민은행 면접",
    start: todayStr + "T12:00:00",
  },
  {
    // todayStr보다 이틀 전 일정 추가
    id: createEventId(),
    title: "삼성SDS 코테",
    start: getOffsetDate(-2), // 이틀 전
  },
  {
    // todayStr보다 이틀 후 일정 추가
    id: createEventId(),
    title: "소프티어 코테",
    start: getOffsetDate(2), // 이틀 후
  },
];

export function createEventId() {
  return String(eventGuid++);
}
