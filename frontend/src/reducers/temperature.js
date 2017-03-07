import {combineReducers} from "redux";
import * as temperatureActions from "../actions/temperature";

export function temperature(previousState = '', action) {
    switch (action.type) {
        case temperatureActions.SET_TEMPERATURE:
            return String(action.degrees)
        case temperatureActions.CLEAR_TEMPERATURE:
            return ''
    }

    return previousState
}

export default combineReducers({
    temperature
})
