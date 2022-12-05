<template>
  <h1>Lobby</h1>
  <button class="participant" v-for="(participant, index) in participants" key="index" @click="kickParticipant(participant.studentId)">{{ participant.studentFullName }}</button>
  <button @click="startChallenge">Start</button>
</template>

<script>
import Api from "@/services/Api";
import {onUnmounted, ref} from "vue";
import {useRoute} from "vue-router"
import router from "@/router";

export default {
  setup() {
    const route = useRoute();

    const participants = ref([]);

    function refreshParticipant() {
      Api().get("/api/organizer/participant", {params: {challengeId: route.params.challengeId}}).then(resp => participants.value = resp.data.list)
    }

    refreshParticipant();

    const interval = setInterval(() => {
      refreshParticipant()
    }, 5000)

    onUnmounted(() => {
      clearInterval(interval)
    })


    function kickParticipant(participantId){
      console.log("Kick participant: ", participantId)
    }

    function startChallenge() {
      router.push({name: "organizer.start", params: route.params});
    }

    return {
      participants,
      kickParticipant,
      startChallenge
    }
  }
}

</script>

<style scoped>
button.participant {
  margin: 2px;
}

button.participant:hover {
  text-decoration: line-through
}

</style>