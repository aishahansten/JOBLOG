<template>
  <NavBar />
  <div class="main-content">
    <RouterView />
  </div>
  <footer>
    <img src="" alt="" />
  </footer>
</template>

<script setup>
import { RouterView, RouterLink } from "vue-router";
import NavBar from "./views/home/components/NavBar.vue";
</script>

<style>
body {
  min-width: 1920px;
}

header,
.main-content {
  width: 1920px;
  margin: 0 auto;
  padding: 0px 288px;
  box-sizing: border-box;
  border-bottom: 1px solid #d9d9d9;
}

header {
  display: flex;
  height: 100px;
  align-items: center;
  justify-content: center;
}

.main-content {
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
