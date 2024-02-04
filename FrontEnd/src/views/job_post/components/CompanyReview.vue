<template>
  <div class="company-container">
    <div class="company-container-left">
      <div class="company-logo">
        <img src="" alt="not rdy">
      </div>
      <div class="company-name">
        not rdy  
      </div>
    </div>
    <div class="company-container-right">
      <div>
        복지 및 급여
      </div>
      <div>
        워라밸
      </div>
      <div>
        사내 문화
      </div>
      <div>
        기회 및 가능성
      </div>
      <div>
        경영진
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
  .company-container {
    display: grid;
    grid-template-columns: 1fr 2fr;
    justify-content: center;
    align-items: center;
  }

  .company-container-left, .company-container-right {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
</style>