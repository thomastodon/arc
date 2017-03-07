import "babel-polyfill";

jest.unmock('../rootSaga')
jest.unmock('../../actions/temperature')

import {takeEvery} from "redux-saga";
import {call, put} from "redux-saga/effects";
import {apiGet} from "../../api/apiService";
import * as sagas from "../temperature";
import * as actions from "../../actions/temperature"

describe('temperature Saga', () => {
    describe('streamTemperature', () => {

        let responseData;
        beforeEach(() => {
            responseData = {degrees: 20.13};
        })

        it('calls to api GET', () => {
            const iterator = sagas.streamTemperature()
            const actualSaga = iterator.next(responseData).value;
            expect(actualSaga).toEqual(call(apiGet, '/temperature'))
        })

        it('dispatches setTemperature with degrees on PUT', () => {
            const iterator = sagas.streamTemperature()
            iterator.next(responseData)
            const actualSaga = iterator.next(responseData).value;
            expect(actualSaga).toEqual(put(actions.setTemperature(20.13)))
        })
    })

    describe('watchTemperature', () => {
        it('takes every STREAM_TEMPERATURE action', () => {
            const iterator = sagas.watchTemperature()
            const actualSaga = iterator.next().value
            expect(actualSaga.TAKE).toEqual({"pattern": 'STREAM_TEMPERATURE'})
        })
    })
})