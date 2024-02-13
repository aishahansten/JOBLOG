<template>
  <div class="add">
    <div class="info-input" id="educate-input">
      <input
        type="text"
        v-model="education.institutionName"
        placeholder="예) 한국대학교"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="education.title"
        placeholder="예) 경영학과 학사"
        required
        class="input focus-lined-c"
      />
      <input
        class="input focus-lined-c"
        type="date"
        v-model="education.startDate"
        required
      />
      <input
        class="input focus-lined-c"
        type="date"
        v-model="education.endDate"
        required
      />
      <select
        class="input focus-lined-c"
        v-model="education.graduationStatus"
        required
      >
        <option disabled value="">선택해주세요</option>
        <option value="0">재학 중</option>
        <option value="1">졸업 예정</option>
        <option value="2">졸업</option>
      </select>
      <select class="input focus-lined-c" v-model="education.yesOrNot" required>
        <option disabled value="">선택</option>
        <option value="0">X</option>
        <option value="1">O</option>
      </select>
      <select class="input focus-lined-c" v-model="education.dayOrNot" required>
        <option disabled value="">선택</option>
        <option value="0">X</option>
        <option value="1">O</option>
      </select>
      <a @click="createInfo(education)" class="btn-s solid-c h-bright a-dark"
        >저장</a
      >
    </div>
  </div>
</template>

<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from "vue";

const settingResumeStore = useSettingResumeStore();
const createInfo = async (info) => {
  await settingResumeStore.createInfo(info);

  education.value = {
    userId: "",
    infoCategory: "EDUCATION",
    title: "",
    institutionName: "",
    startDate: "",
    endDate: "",
    graduationStatus: 0,
    yesOrNot: 0,
    dayOrNight: 0,
  };
  emit("refresh");
};

const emit = defineEmits(["refresh"]);

const education = ref({
  userId: "",
  infoCategory: "EDUCATION",
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  graduationStatus: 0,
  yesOrNot: 0,
  dayOrNight: 0,
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

#educate-input {
  display: grid;
  grid-template-columns: 1fr 1.2fr 1fr 1fr 0.8fr 0.5fr 0.5fr 0.4fr;
  grid-gap: 5px;
  width: 100%;
}

#educate-input input,
#educate-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}
</style>
