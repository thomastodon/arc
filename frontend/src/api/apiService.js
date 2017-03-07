import axios from "axios";
import {getHost} from "../actions/locationFacade";

export const resolveEnvApiHost = () => {
    const host = getHost()
    return 'http://localhost:8080'
}

axios.defaults.baseURL = resolveEnvApiHost()

export function apiGet(url){
    return axios.get(url)
        .then(r => r.data)
}