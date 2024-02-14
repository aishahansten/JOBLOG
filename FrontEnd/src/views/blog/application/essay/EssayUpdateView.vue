<template>
  <div></div>
</template>

<script setup>
import { ref, onMounted } from "vue";

// hanspell-example.js
const hanspell = require("hanspell");

const sentence = "리랜드는 얼굴 골격이 굵은게,";
const end = function () {
  console.log("// check ends");
};
const error = function (err) {
  console.error("// error: " + err);
};

const spellcheck = function () {
  hanspell.spellCheckByPNU(sentence, 6000, console.log, end, error);
  hanspell.spellCheckByDAUM(sentence, 6000, console.log, end, error);
};
</script>

<style scoped></style>
