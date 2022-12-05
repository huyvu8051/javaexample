<template>
  <h1>Result</h1>
  <table id="participants">
    <tr>
      <th>Rank</th>
      <th>Username</th>
      <th>FullName</th>
      <th>Total point</th>
    </tr>
    <tr v-for="(participant, index) in participantsRank" key="index">
      <td>{{ index + 1 }}</td>
      <td>{{ participant.username }}</td>
      <td>{{ participant.fullName }}</td>
      <td>{{ participant.totalPoint }}</td>
    </tr>
  </table>
  <button @click="next">Next</button>
</template>

<script>
import {useOrganizerStore} from "@/stores/Organizer";
import router from "@/router";
import {useRoute} from "vue-router";

export default {
  name: "Result",
  setup() {

    const route = useRoute();

    const participantsRank = useOrganizerStore().participantsRank;

    function next(){
      router.push({name:"organizer.request", params: route.params})
    }

    return {
      participantsRank,
      next
    }

  }
}
</script>

<style scoped>

#participants {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#participants td, #participants th {
  border: 1px solid #ddd;
  padding: 8px;
}

#participants tr:nth-child(even) {
  background-color: #f2f2f2;
}

#participants tr:hover {
  background-color: #ddd;
}

#participants th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>