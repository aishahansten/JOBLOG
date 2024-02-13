<template>
  <div class="add">
    <div class="info-input" id="award-input">
      <input
        type="text"
        v-model="award.title"
        placeholder="예) 한국해커톤 11회 은상"
        required
        class="input focus-lined-c"
      />
      <input
        type="text"
        v-model="award.institutionName"
        placeholder="예) 한국기업"
        required
        class="input focus-lined-c"
      />
      <input
        type="date"
        class="input focus-lined-c"
        v-model="award.startDate"
        required
      />
      <textarea
        class="input focus-lined-c"
        v-model="award.description"
        placeholder="수상 내용을 입력해주세요"
        required
        rows="1"
      ></textarea>

      <a @click="createInfo(award)" class="btn-s solid-c h-bright a-dark"
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
  award.value = {
    userId: "",
    infoCategory: "AWARD",
    title: "",
    institutionName: "",
    startDate: "",
    description: "",
  };
  emit("refresh");
};

const award = ref({
  userId: "",
  infoCategory: "AWARD",
  title: "",
  institutionName: "",
  startDate: "",
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

#award-input {
  display: grid;
  grid-template-columns: 1fr 1fr 0.8fr 1.5fr 0.3fr;
  grid-gap: 5px;
  width: 100%;
}

#award-input input,
#award-input select {
  width: 100%;
  text-align: baseline;
  padding: 10px;
}

textarea {
  resize: none;
}
</style>
