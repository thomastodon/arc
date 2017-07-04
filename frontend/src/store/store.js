import "babel-polyfill";
import React from "react";
import {applyMiddleware, createStore} from "redux";
import createSagaMiddleware from "redux-saga";
import reducer from "./../reducers";
import rootSaga from "./../sagas/rootSaga";

const sagaMiddleware = createSagaMiddleware();
export default createStore(
  reducer,
  applyMiddleware(sagaMiddleware)
)
sagaMiddleware.run(rootSaga);