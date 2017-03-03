import {put} from "redux-saga/effects";
import {push} from "../actions/temperature";
import * as actions from "../actions/transactionActions"

export default function* rootSaga() {
    yield [
        navigateHome()
    ];
}

export function* navigateHome(){
    yield* takeEvery(actions.UPDATE_TRANSACTION, updateTransaction)
}
