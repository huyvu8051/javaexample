import {useAuthStore} from "@/stores/Auth";
import axios from "axios";

const {jwt} = useAuthStore();

let instance = axios.create({
    headers: {
        Authorization: "Bearer " + `${jwt}`,
    }
});

instance.interceptors.request.use(req => {
    // console.log(req);
    return req;
}, error => Promise.reject(error));

instance.interceptors.response.use(resp => {

    let customBodyRespDTO = resp.data;

    switch (customBodyRespDTO.status) {
        case 200:
        case 201:
            return customBodyRespDTO;
            break;
        case 400:
        case 401:
        case 403:

            return Promise.reject(customBodyRespDTO);
            break;

        default :
            console.warn("resp without wrapped body: ", resp.data);
            break;
    }
}, err => Promise.reject(err))

export default () => {
    return instance;
}
