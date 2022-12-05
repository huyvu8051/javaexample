<template>
  <h1>
    Organizer home
  </h1>
  <table>
    <tr>
      <th>id</th>
      <th>title</th>
      <th>actions</th>
    </tr>
    <tr v-for="(challenge, index) in challenges" key="index">
      <td>{{ challenge.id }}</td>
      <td>{{ challenge.title }}</td>
      <td>
        <button @click="enterLobby(challenge.id)">start</button>
      </td>
    </tr>
  </table>
</template>

<script setup lang="ts">
import Api from "@/services/Api"
import {ref} from "vue";
import router from "@/router";

interface VDataTablePagingRequest {
  groupBy: string[] | [],
  groupDesc: string[] | [],
  itemsPerPage: number | 12,
  multiSort: boolean | false,
  mustSort: boolean | false,
  page: number | 1,
  sortBy: string[] | [],
  sortDesc: boolean[] | []
}

const challenges = ref([])

Api().get("/api/organizer/challenge").then(resp => {
  challenges.value = resp.data.list
})

function enterLobby(challengeId: number) {
  router.push({name: "organizer.lobby", params: {challengeId: challengeId}})
}


</script>

<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>