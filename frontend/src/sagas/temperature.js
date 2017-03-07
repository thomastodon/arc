import {takeEvery} from "redux-saga";
import {call, put} from "redux-saga/effects";
import {apiGet} from "../api/apiService";
import * as actions from "../actions/temperature";

export function* watchTemperature() {
    yield* takeEvery(actions.STREAM_TEMPERATURE, streamTemperature)
}

export function* streamTemperature(action) {
    const temperature = yield call(apiGet, `/temperature`)
    yield put(actions.setTemperature(temperature.degrees))
}