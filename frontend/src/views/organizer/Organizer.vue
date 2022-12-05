<template>
  <div class="organizer">
    <router-view></router-view>
<!--    <button class="control-button" @click="click">click</button>-->
  </div>
</template>

<script>
import io from "socket.io-client"
import {useRoute} from "vue-router"

import {useAuthStore} from "@/stores/Auth";
import router from "@/router";
import {useOrganizerStore} from "@/stores/Organizer";

export default {
  setup() {

    const authStore = useAuthStore();
    const organizerStore = useOrganizerStore();
    const route = useRoute()

    const socket = io("http://localhost:8082", {
      query: {
        challengeId: route.params.challengeId
      },
      transportOptions: {
        polling: {
          extraHeaders: {
            'Authorization': 'Bearer ' + authStore.jwt
          }
        }
      }
    });
    socket.on("connected", (e) => {
      console.log("socket connected: ", e);
    });
   socket.on("showCorrectAnswer", organizerStore.update);


    socket.on("publishQuestion", async msg => {
      await useOrganizerStore().update(msg);
      await router.push({name: "organizer.preview", params: route.params})
    })


    function click() {
        alert("chung ta cua hien tai")
    }

    function result(){
      router.push({name:"organizer.result"})
    }

    return {
      click,
      result
    }

  }
}

</script>

<style scoped>
.organizer{
  height: 97vh;
  /*background-color: rgba(44, 62, 80, 0.38);*/
}
.control-button {
  height: 5%;
  width: calc(50% - 1vmin);
  /*background-color: rgba(62, 148, 37, 0.76);*/
  border: 1px solid black;
  border-radius: 5px;
  margin: 0.5vmin;
}
</style>