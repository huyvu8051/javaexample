<template>
  <h2 class="question-content">{{ question.questionContent }}</h2>
  <img class="question-image" :src="questionImage"/>
  <div class="time-left">Time left: {{ diff }}</div>
  <div class="answer-container">
    <div class="answer-item" :style="answer.style" v-for="(answer, index) in answers" key="index">
      {{ answer.content }}
    </div>
  </div>
  <button @click="result">Result</button>
</template>

<script>
import {useOrganizerStore} from "@/stores/Organizer";
import questionImage from "@/assets/question-imagejpg.jpg"
import {onUnmounted, ref} from "vue";
import Api from "@/services/Api";
import {useRoute} from "vue-router";
import router from "@/router";

export default {
  name: "Ask",
  setup() {

    const route = useRoute();

    const organizerStore = useOrganizerStore();

    const question = useOrganizerStore().question;
    const answers = useOrganizerStore().answers;

    const diff = ref(0)

    const interval = setInterval(() => {
      diff.value = (question.timeout - Date.now()) / 1000;

      if (diff.value < 0) {
        diff.value = 0;
        Api().get("/api/organizer/showCorrectAnswer", {params: route.params}).then(resp=>organizerStore.update(resp.data))
        clearInterval(interval)
      }

    }, 200);

    onUnmounted(() => {
      clearInterval(interval)
    })


    function result() {
      router.push({name: "organizer.result", params: route.params})
    }

    return {
      question,
      answers,
      questionImage,
      diff,
      result
    }

  }
}
</script>

<style scoped>

.question-content {
  max-height: 20vh;
  text-align: center;
  font-size: calc(0.9rem + 1.6%);
  /*background-color: #2ad72a;*/
}

.question-image {
  height: 40%;
  width: 100%;
  text-align: center;
  object-fit: contain;
  /*background-color: #006666;*/
}

.answer-container {
  height: 40%;
  display: flex;
  align-content: stretch;
  flex-flow: row wrap;
}

.answer-item {
  border: 1px solid black;
  border-radius: 5px;
  margin: 0.5vmin;
  padding: 1vmin;
  flex: 1 auto;
  font-size: calc(0.4rem + 1.6vmin);
}

.time-left {
  height: 3%;
  font-size: calc(0.9rem + 1.6%);
}
</style>