import axios from "axios";
import {getHost} from "../actions/locationFacade";

export const resolveEnvApiHost = () => {
  switch (getHost()) {
    case 'localhost:3000':
      return 'http://localhost:8080'
  }
}

axios.defaults.baseURL = resolveEnvApiHost();

export function apiGet(url) {
  return axios.get(url).then(r => r.data)
}