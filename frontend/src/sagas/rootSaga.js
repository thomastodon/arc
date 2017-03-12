import * as temperatureSagas from "./temperature";

export default function* rootSaga() {
    yield [
        temperatureSagas.watchTemperature()
    ];
}