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

</style>
