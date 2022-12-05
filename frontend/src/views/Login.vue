<template>
  <h1>Login</h1>
  <form @submit.prevent="submit">
    <label for="username"> Username </label>
    <input v-model="authReq.username" type="text" name="username" id="username">
    <label for="password">Password</label>
    <input v-model="authReq.password" type="text" name="password" id="password">
    <input type="submit" value="login">
  </form>
</template>

<script setup>
import Api from "@/services/Api";
import {reactive} from "vue";
import {useAuthStore} from "@/stores/Auth";
import router from "@/router";

const authStore = useAuthStore();


const authReq = reactive({
  username: "",
  password: ""
})

function submit() {
  Api().post("/api/authentication", authReq)
      .then(async ({data}) => {
        await authStore.update(data)
        await router.push({name: "customer.home"})
      })
      .catch()
}


</script>