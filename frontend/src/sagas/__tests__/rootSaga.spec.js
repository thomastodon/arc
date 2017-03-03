import "babel-polyfill";

jest.unmock('../rootSaga')
jest.unmock('../../actions/temperature')

import {takeEvery} from "redux-saga";
import {put} from "redux-saga/effects";
import * as sagas from "../rootSaga";
import * as actions from "../../actions/temperature"

describe('rootSaga', () => {
    describe('navigateHome', () => {
        it('calls router push', () => {
            push.mockReturnValue('routing action')

            const iterator = sagas.navigateHome()

            let actualSaga = iterator.next().value;
            expect(actualSaga).toEqual(put(push('/')))
        })
    })
})