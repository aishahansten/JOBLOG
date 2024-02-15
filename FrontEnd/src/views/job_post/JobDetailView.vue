<template>
  <div v-if="!isLoaded">로딩중</div>
  <div class="container" v-else>
    <div id="job-post" class="content">
      <div class="job-detail">
        <div class="job-title">
          <h1>{{ currentJob.value.title }}</h1>
          <RouterLink :to="{ name: 'CompanyDetail', params: { id: currentJob.value.companyCode } }">
            <h3>{{ currentJob.value.companyName }}</h3>
          </RouterLink>
        </div>
        <div class="job-info-header">
          <div>
            <p>{{ currentJob.value.jobCategoryDesc }}</p>
          </div>
          <div class="job-info">
            <div class="job-info-item">
              <div>
                <span>지역</span>
                <p>{{ currentJob.value.locationDesc }}</p>
              </div>
              <div>
                <span>근무 형태</span>
                <p>{{ currentJob.value.jotTypeDesc }}</p>
              </div>
              <div>
                <span>급여</span>
                <p>{{ currentJob.value.salary }}</p>
              </div>
            </div>
            <div>
              <div>
                <span>접수 기간</span>
                <p>
                  {{ formatISODate(currentJob.value.openingDate) }} ~
                  {{ formatISODate(currentJob.value.expirationDate) }}
                </p>
              </div>
              <div>
                <span>학력</span>
                <p>{{ currentJob.value.requiredEducationLevel }}</p>
              </div>
              <div>
                <span>경력</span>
                <p>{{ currentJob.value.experienceLevel }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="job-interaction">
        <div class="job-check">
          <h3>{{ expirationMessage(currentJob.value.expirationDate) }}</h3>
          <a class="job-scrap-info" @click="scrapJobPost">
            <i class="fa-regular fa-star fa-xl"></i>
            <span>스크랩</span>
          </a>
        </div>
        <div class="job-apply">
          <RouterLink
            :to="{ name: 'ResumeCreate', params: { title: currentJob.value.companyName, job: currentJob.value.title } }"
          >
            <div class="btn solid-c h-bright a-dark f-size-20">이력서 쓰기</div>
          </RouterLink>
          <div>
            <RouterLink :to="{ name: 'CompanyDetail', params: { id: currentJob.value.companyCode } }">
              <div class="btn solid-c h-bright a-dark">회사의 다른 공고 보기</div>
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
    <div>
      <ScreenReview :currentJob="currentJob" />
    </div>
  </div>
</template>

<script setup>
import ScreenReview from "./components/detail/ScreenReview.vue";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useJobPostStore } from "@/stores/jobPosts";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";
import { formatISODate, expirationMessage } from "@/utils/utility";

const jobPostStore = useJobPostStore();
const route = useRoute();

const isLoaded = ref(false);
const currentJob = ref(null);

onMounted(async () => {
  try {
    await jobPostStore.getJobPostDetail(route.params.id);
    currentJob.value = jobPostStore.currentJob;
  } catch (err) {
    console.error(err);
  } finally {
    isLoaded.value = true;
  }
});

const scrapJobPost = async () => {
  const authStore = useAuthStore();
  await authStore.updateUserInfoFromToken();
  const scrap = {
    userId: authStore.userInfo.sub,
    recruitId: route.params.id,
  };
  const config = {
    headers: {
      Authorization: `${authStore.accessToken}`,
    },
  };
  try {
    const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/recruit/scrap`, scrap, config);
    alert("스크랩 되었습니다.");
  } catch (err) {
    console.error(err);
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  min-height: 1000px;
  padding: 30px 0;
}

.content {
  display: flex;
  justify-content: space-between;
  padding: 30px 40px;
  border: 1px solid var(--border-gray);
  border-radius: 10px;
}

.company-job {
  display: flex;
  border: 1px solid rgba(0, 0, 0, 0.321);
  border-radius: 8px;
  padding: 5px;
  width: 200px;
  align-items: center;
  justify-content: center;
}
.content#job-post {
  gap: 40px;
  background-color: rgb(104, 128, 255, 0.2);
}

.job-detail {
  width: 100%;
}

.job-title {
  padding: 20px 0;
  border-bottom: 1px solid white;
}
.job-info {
  display: grid;
  grid-template-columns: 2fr 1fr;
  padding: 20px 0;
  font-size: 13pt;
}
.job-info div div {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
  align-items: center;
}

.job-info div div span {
  font-weight: bold;
}

.job-interaction {
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  align-items: end;
  gap: 20px;

  width: 200px;
  padding: 20px 0;
}

.job-check {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  width: 100%;
}
.job-scrap-info {
  display: flex;
  align-items: center;
  gap: 5px;
}

.job-apply {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.job-apply .btn {
  width: 200px;
}
</style>
