import {defineStore} from 'pinia'

export const useAuthStore = defineStore('auth', {
    state: () => ({
        username: "",
        jwt: "",
        roles: [],
        fullName: ""
    }),
    actions: {
        update(authResp) {
            let state = this;
            state = Object.assign(state, authResp)
        }
    },
    persist: {
        enabled: true
    }
})