<template>
    <div v-if="!isLoaded">로딩중</div>
    <div class="container" v-else>
      <div class="content">
        <div class="job-detail">
          <div class="job-title">
            <h2>{{ currentCompany.value.companyName }}</h2>
          </div>
          <div>
          </div>
          <div>
            <CompanyJobList :companyRecruit="companyRecruit"/>  
          </div>
        </div>
        <div class="job-interaction">
          <div class="job-check">
          </div>
          <div class="job-apply">

          </div>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useJobPostStore } from '@/stores/jobPosts';
import { useRoute } from 'vue-router';
import CompanyJobList from '@/views/job_post/components/CompanyJobList.vue'
const route = useRoute()
const jobPostStore = useJobPostStore()

const currentCompany = ref({})
const companyRecruit = ref([])
const isLoaded = ref(false)

onMounted(async() => {
  await jobPostStore.getCompanyDetail(route.params.id)
  await jobPostStore.getCompanyRecruit(route.params.id)
  try {
    currentCompany.value = jobPostStore.companyDetail
    companyRecruit.value = jobPostStore.companyRecruit
  } catch {}
  finally { 
    isLoaded.value = true
    console.log(jobPostStore.companyRecruit)
  }
})

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
