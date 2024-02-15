<template>
  <div class="add">
    <div class="info-input" id="skill-input">
      <input
        type="text"
        v-model="skill.title"
        placeholder="예) 파이썬"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="skill.institutionName"
        placeholder="예) 개발"
        required
        class="input focus-lined-c"
      />
      <textarea
        v-model="skill.description"
        placeholder="상세 설명을 입력해주세요"
        required
        class="input focus-lined-c"
        rows="1"
      ></textarea>
      <select class="input focus-lined-c" v-model="skill.skillLevel" required>
        <option disabled value="">선택해주세요</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select>

      <a @click="createInfo(skill)" class="btn-s solid-c h-bright a-dark"
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

  skill.value = {
    userId: "",
    infoCategory: "SKILL",
    title: "",
    institutionName: "",
    description: "",
    skillLevel: 1,
  };
  emit("refresh");
};

const skill = ref({
  userId: "",
  infoCategory: "SKILL",
  title: "",
  institutionName: "",
  description: "",
  skillLevel: 1,
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

#skill-input {
  display: grid;
  grid-template-columns: 1fr 0.5fr 1.5fr 0.5fr 0.3fr;
  grid-gap: 5px;
  width: 100%;
}

#skill-input input,
#skill-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}

textarea {
  resize: none;
}
</style>
