export function formatISODate(dateString) {
  return new Date(dateString).toLocaleDateString(undefined, {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
}

export function calculateDaysLeft(expirationDateString) {
  const today = new Date();
  const expirationDate = new Date(expirationDateString);
  const timeDiff = expirationDate - today;
  const daysDiff = timeDiff / (1000 * 60 * 60 * 24);
  return Math.ceil(daysDiff);
}

export function expirationMessage(expirationDateString) {
  const daysLeft = calculateDaysLeft(expirationDateString);
  if (daysLeft > 0) {
    return `마감 ${daysLeft}일 전`;
  } else if (daysLeft === 0) {
    return `오늘 마감`;
  } else {
    return `마감된 공고`;
  }
}
