<template>
    <h2>작성 중인 자소서/이력서</h2>

    <div class="container">
      <div class="subcontainer">
        <div class="doc-container">
            <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
            <p>SSAFY 지원 이력서</p>
        </div>
        <div class="doc-container">
            <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
            <p>SSAFY 지원 이력서</p>
        </div>
        <div class="doc-container">
            <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
            <p>SSAFY 지원 이력서</p>
        </div>
        <div class="doc-container">
            <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
            <p>SSAFY 지원 이력서</p>
        </div>
      </div>
      <div class="subcontainer">
        <div class="essay-container">
          <img src="@/assets/img/home/icon/doc-mini.svg" alt="doc" class="icon">
          <p>SSAFY 지원 자소서</p>
        </div>
      </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    margin-bottom: 10px;
    height: 170px;
    border: solid 1px black;
  }

  .subcontainer {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    border: 1px black solid;
    border-radius: 8px;
    padding: 10px 30px;
    gap: 30px;
  }
  .essay-container, .doc-container {
    height: 120px;
    width: 100px;
    background-color: rgba(192, 181, 255, 0.701);
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 10px; /* Added padding for better spacing */
  }

  .essay-container {
    background-color: rgb(176, 180, 255);
  }
  .icon {
    height: 80px;
    width: 80px;
    margin-bottom: 10px;
  }

  p {
    margin: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    width: 90%; /* Adjust as needed */
  }
</style>
