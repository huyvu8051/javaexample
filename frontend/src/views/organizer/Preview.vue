<template>
  <h1>Preview</h1>
  <h2>Content: {{ question.questionContent }}</h2>
  <h3>Point: {{ question.point }}</h3>
  <h3>Answer option: {{ question.answerOption }}</h3>
  <h3>Order: {{ question.questionOrder }}</h3>
</template>

<script>
import {useOrganizerStore} from "@/stores/Organizer";
import {useRoute} from "vue-router";
import router from "@/router";
import {onUnmounted} from "vue";

export default {
  name: "Preview",
  setup() {

    const route = useRoute();

    const question = useOrganizerStore().question;

    if (question.timeout - question.askDate < 1500) {
      router.push({name: "organizer.ask", params: route.params})
    }

    const timeout = setTimeout(() => {
      router.push({name: "organizer.ask", params: route.params})
    }, 2000);

    onUnmounted(() => {
      clearTimeout(timeout)
    })

    return {
      question
    }
  }
}
</script>

<style scoped>

</style>