<template>
  <NavBar />
  <main>
    <RouterView />
  </main>
  <footer>
    <img src="" alt="" />
    ⓒ 2024. B204. All Rights Reserved.
  </footer>
</template>

<script setup>
import { RouterView, RouterLink } from "vue-router";
import NavBar from "./views/home/components/NavBar.vue";
</script>

<style></style>
