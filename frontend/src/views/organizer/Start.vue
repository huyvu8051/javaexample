<template>
  <h1>Start Challenge</h1>
</template>

<script>
import Api from "@/services/Api";
import {useRoute} from "vue-router";
import router from "@/router";
import {onUnmounted} from "vue";

export default {
  name: "Start",
  setup(){
    const route = useRoute();

    Api().get("/api/organizer/startChallenge",{params:route.params})

    const timeout = setTimeout(()=>{
      router.push({name:"organizer.request", params:route.params})
    }, 1000);

    onUnmounted(()=>{
      clearTimeout(timeout)
    })
  }
}
</script>

<style scoped>

</style>