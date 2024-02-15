<template>
  <div class="add">
    <div class="info-input" id="career-input">
      <input
        type="text"
        v-model="career.institutionName"
        placeholder="예) 삼성 멀티캠퍼스"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="career.title"
        placeholder="관리/감독"
        required
        class="input focus-lined-c"
      />
      <input
        type="date"
        class="input focus-lined-c"
        v-model="career.startDate"
        required
      />
      <input
        type="date"
        class="input focus-lined-c"
        v-model="career.endDate"
        required
      />

      <a @click="createInfo(career)" class="btn-s solid-c h-bright a-dark"
        >저장</a
      >
    </div>
  </div>
</template>

<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref } from "vue";
const emit = defineEmits(["refresh"]);

const settingResumeStore = useSettingResumeStore();
const createInfo = async (info) => {
  await settingResumeStore.createInfo(info);

  career.value = {
    userId: "",
    infoCategory: "CAREER",
    title: "",
    institutionName: "",
    startDate: "",
    endDate: "",
  };
  emit("refresh");
};

const career = ref({
  userId: "",
  infoCategory: "CAREER",
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
});
</script>

<style scoped>
.add {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.add * {
  font-size: 16px;
}

#career-input {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 0.3fr;
  grid-gap: 5px;
  width: 100%;
}

#career-input input,
#career-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}
</style>
