<template>
  <div class="add">
    <div class="info-input" id="cert-input">
      <input
        type="text"
        v-model="certificate.title"
        placeholder="예) TOEIC Speaking"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="certificate.institutionName"
        placeholder="ETS"
        required
        class="input focus-lined-c"
      />
      <input
        class="input focus-lined-c"
        type="date"
        v-model="certificate.startDate"
        placeholder="YYYY-MM-DD"
        required
      />
      <input
        class="input focus-lined-c"
        type="date"
        v-model="certificate.endDate"
        placeholder="YYYY-MM-DD"
        required
      />
      <input
        type="text"
        v-model="certificate.description"
        placeholder="예) 영어"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="certificate.level"
        placeholder="예) Intermediate High"
        required
        class="input focus-lined-c"
      />

      <a @click="createInfo(certificate)" class="btn-s solid-c h-bright a-dark"
        >저장</a
      >
    </div>
  </div>
</template>

<script setup>
import { useSettingResumeStore } from "@/stores/settingResume";
import { ref, onMounted } from "vue";
const emit = defineEmits(["refresh"]);

const settingResumeStore = useSettingResumeStore();
const createInfo = async (info) => {
  await settingResumeStore.createInfo(info);

  certificate.value = {
    userId: "",
    infoCategory: "CERTIFICATE",
    title: "",
    institutionName: "",
    startDate: "",
    endDate: "",
    description: "",
    level: "",
  };
  emit("refresh");
};

const certificate = ref({
  userId: "",
  infoCategory: "CERTIFICATE",
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  description: "",
  level: "",
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

#cert-input {
  display: grid;
  grid-template-columns: 1fr 0.7fr 1fr 1fr 0.7fr 0.7fr 0.4fr;
  grid-gap: 5px;
  width: 100%;
}

#cert-input input,
#cert-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}
</style>
