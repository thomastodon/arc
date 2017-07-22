import {END, eventChannel, takeEvery} from "redux-saga";
import {call, take, put} from "redux-saga/effects";
import {apiGet} from "../api/apiService";
import * as actions from "../actions/temperatureAction";

export function* watchTemperature() {
  yield* takeEvery(actions.STREAM_TEMPERATURE, streamTemperature)
}

export function* streamTemperature() {
  const eventSource = new EventSource('http://localhost:8080/temperatures');
  const channel = yield call(subscribeToSse, eventSource);
  while (true) {
    const temperature = yield take(channel);
    yield put(actions.setTemperature(temperature.degrees));
  }
}

function subscribeToSse(eventSrc) {
  const subs = emitter => {
    eventSrc.onmessage = (message) => {
      emitter(JSON.parse(message.data));
    };
    return () => {
      eventSrc.close();
    }
  };
  return eventChannel(subs);
}