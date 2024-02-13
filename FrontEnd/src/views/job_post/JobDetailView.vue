<template>
  <div v-if="!isLoaded">로딩중</div>
  <div class="container" v-else>
    <div id="job-post" class="content">
      <div class="job-detail">
        <div class="job-title">
          <h1>{{ currentJob.value.title }}</h1>
          <h3>{{ currentJob.value.companyName}}</h3>
        </div>
        <div class="job-info">
          <div>
            <div>
              <span>업종</span>
              <p>{{ currentJob.value.jobCategoryDesc }}</p>
            </div>
            <div>
              <span>지역</span>
              <p>{{ currentJob.value.locationDesc}}</p>
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
              <p>{{ currentJob.value.openingDate }} ~ {{ currentJob.value.expirationDate }}</p>
            </div>
            <div>
              <span>학력</span>
              <p>{{ currentJob.value.requiredEducationLevel }}</p>
            </div>
            <div>
              <span>경력</span>
              <p>{{ currentJob.value.experienceLevel}}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="job-interaction">
        <div class="job-check">
          <h3>마감 00일 전</h3>
          <!-- <span>마감</span> -->
          <!-- <span>접수 00일 전</span> -->
          <div class="job-scrap-info" @click="scrapJobPost">
            <i class="fa-regular fa-star fa-xl"></i>
            <span>178</span>
          </div>
        </div>
        <div class="job-apply">
          <div class="btn solid-c h-bright a-dark f-size-20">이력서 쓰기</div>
          <div class="btn solid-c h-bright a-dark f-size-20">자소서 쓰기</div>
        </div>
      </div>
    </div>
    <div>
      <ScreenReview 
      :currentJob="currentJob"
      />
    </div>
    <div>
      <CompanyReview 
      />
    </div>
  </div>
</template>

<script setup>
import ScreenReview from "./components/detail/ScreenReview.vue";
import CompanyReview from "./components/detail/CompanyReview.vue";
import { ref, onMounted } from 'vue';
import { useRoute } from "vue-router";
import { useJobPostStore } from '@/stores/jobPosts';
import { useAuthStore } from "@/stores/auth";
import axios from "axios";

const jobPostStore = useJobPostStore();
const route = useRoute();

const isLoaded = ref(false);
const currentJob = ref(null);

onMounted(async () => {
  try {
    await jobPostStore.getJobPostDetail(route.params.id)
    currentJob.value = jobPostStore.currentJob
    console.log(currentJob.value)
  } catch (err) {
    console.error(err);
  } finally {
    isLoaded.value = true
  }
})



const scrapJobPost = async() => {
      const authStore = useAuthStore()
      await authStore.updateUserInfoFromToken()
      const scrap = {
        userId: authStore.userInfo.sub,
        recruitId: route.params.id
      }
      const config = {
        headers: {
          'Authorization': `${authStore.accessToken}`,
        },
      }
      try { 
        const res = await axios.post(`${import.meta.env.VITE_API_BASE_URL}/recruit/scrap`, scrap, config)
        console.log('스크랩')
      } catch (err) {
        console.error(err)
      }
    }

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
  grid-template-columns: 1fr 1fr;
  padding: 20px 0;
}
.job-info div div {
  display: flex;
  justify-content: space-between;
  align-items: center;
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
.job-check h3 {
  border-bottom: 2px double var(--main-blue);
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
