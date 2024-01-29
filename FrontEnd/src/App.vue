<template>
  <NavBar />
  <main>
    <RouterView />
  </main>
  <footer>
    <img src="" alt="" />
  </footer>
</template>

<script setup>
import { RouterView, RouterLink } from "vue-router";
import NavBar from "./views/home/components/NavBar.vue";
</script>

<style>
/* header,
.main-content {
  width: 1920px;
  margin: 0 auto;
  padding: 0px 288px;
  box-sizing: border-box;
  border-bottom: 1px solid #d9d9d9;
  display: flex;
  flex-direction: column;
  align-items: center;
} */
</style>
