<script setup>
  import MainPage from "./components/views/MainPage.vue";
  import Toast from "primevue/toast";
</script>

<template>
  <div id="main-container">
    <MainPage></MainPage>
    <Toast group="clipboardCopy" position="bottom-center" class="w-18rem sm:w-25rem">
    </Toast>
  </div>
</template>

<style scoped>
</style>
