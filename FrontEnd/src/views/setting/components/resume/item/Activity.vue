<template>
  <div class="add">
    <div class="info-input" id="activity-input">
      <input
        type="text"
        v-model="activity.title"
        placeholder="예) 삼성 청년 SW 교육 아카데미"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="activity.institutionName"
        placeholder="예) 삼성 멀티캠퍼스"
        required
        class="input focus-lined-c"
      />
      <input
        type="date"
        class="input focus-lined-c"
        v-model="activity.startDate"
        required
      />
      <input
        type="date"
        class="input focus-lined-c"
        v-model="activity.endDate"
        required
      />
      <textarea
        class="input focus-lined-c"
        v-model="activity.description"
        placeholder="활동/교육 내용을 입력해주세요"
        required
        rows="1"
      ></textarea>

      <a @click="createInfo(activity)" class="btn-s solid-c h-bright a-dark"
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
  activity.value = {
    userId: "",
    infoCategory: "ACTIVITY",
    title: "",
    institutionName: "",
    startDate: "",
    endDate: "",
    description: "",
  };
  emit("refresh");
};

const activity = ref({
  userId: "",
  infoCategory: "ACTIVITY",
  title: "",
  institutionName: "",
  startDate: "",
  endDate: "",
  description: "",
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

#activity-input {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1.5fr 0.4fr;
  grid-gap: 5px;
  width: 100%;
}

#activity-input input,
#activity-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}

textarea {
  resize: none;
}
</style>
