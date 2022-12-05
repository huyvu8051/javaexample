import {defineStore} from 'pinia'

export const useOrganizerStore = defineStore('organizer', {
    state: () => ({
        questionToken: "",
        question: {
            "id": 0,
            "ordinalNumber": 0,
            "questionContent": "",
            "questionImage": "",
            "answerTimeLimit": 0,
            "askDate": 0,
            "timeout": 0,
            "point": "",
            "answerOption": "",
            "challengeId": 0,
            "totalQuestion": 0,
            "questionOrder": 0,
            "theLastQuestion": false
        },
        answers: [{
            "id": 0,
            "ordinalNumber": 0,
            "content": "",
            style: {}
        }],
        corrects: [0],
        selected: [0],
        encryptKey: "",
        timeout: 0,
        totalStudent: 0,
        totalStudentCorrect: 0,
        totalStudentWrong: 0,
        participantsRank: [{
            fullName: "",
            id: 0,
            totalPoint: 0,
            username: "",
        }]
    }),
    actions: {
        update(input) {
            console.log(input)
            let state = this;
            state = Object.assign(state, input)

            if (input.corrects) {
                input.corrects.forEach(correct => {
                    const answer = state.answers.find(answer => answer.id = correct);
                    answer.style = {
                        background: "green",
                        color: "white",
                        border: '3px solid gold'
                    }
                })
            }

        }
    }
    ,
    persist: {
        enabled: true
    }
})