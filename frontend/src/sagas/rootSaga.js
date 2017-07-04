import * as temperatureSagas from "./temperatureSaga";

export default function* rootSaga() {
  yield [
    temperatureSagas.watchTemperature()
  ];
}