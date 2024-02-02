<template>
    <div class="diary-create-container">
        <div class="diary-header"></div>
        <div class="diary-title">
            <div class="title-left">
                <h1>다이어리 쓰기</h1>
            </div>
            <div class="title-right">
                <button>저장</button>
            </div>
        </div>
        <div class="diary-content">
            <textarea name="내용 작성" class="content-textarea"></textarea>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
  .diary-create-container {
    display: grid;
    grid-template-rows: 1fr 1fr 3fr;
    width: 800px;
    
  }

  .diary-header {
    background-color: rgb(103, 103, 103);
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
  }

  .diary-title {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }

  .content-textarea {
    width: 800px;
  }
</style>