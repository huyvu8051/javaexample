<template>
  <h1>Request</h1>
</template>

<script>
import Api from "@/services/Api";
import {useRoute} from "vue-router";

export default {
  name: "Request",
  setup() {
    const route = useRoute();
    Api().get("/api/organizer/request", {params: route.params})
  }
}
</script>

<style scoped>

</style>