<template>
  <table>
    <tbody>
      <tr v-for="(value, key) in filteredData" :key="key">
        <td>{{ key }}</td>
        <td>{{ value }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref, computed } from 'vue';

const data = ref({
  dayOrNight: 0,
  description: null,
  endDate: "2023-02-05",
  graduationStatus: 0,
  id: 2,
  infoCategory: "EDUCATION",
  institutionName: "살려줘",
  level: null,
  link: null,
  resumeId: 0,
  role: null,
  skillLevel: null,
  startDate: "2023-02-05",
  techStack: null,
  title: "살려줘",
  totalMember: null,
  userId: 1,
  yesOrNot: 0
});

const filteredData = computed(() => {
  const result = {};
  Object.entries(data.value).forEach(([key, value]) => {
    if (value !== null) {
      result[key] = value;
    }
  });
  return result;
});
</script>
