import "babel-polyfill"

import React from 'react'
import ReactDOM from 'react-dom'
import { createStore, applyMiddleware } from 'redux'
import createSagaMiddleware from 'redux-saga'

import Temperature from './components/Temperature'
import reducer from './reducers'
import rootSaga from './sagas/rootSaga'

const sagaMiddleware = createSagaMiddleware()
const store = createStore(
  reducer,
  applyMiddleware(sagaMiddleware)
)
sagaMiddleware.run(rootSaga)

const action = type => store.dispatch({type})

console.log("HERE");
console.log(document.getElementById('root'));

function render() {
  ReactDOM.render(
//    <Temperature temperature={store.getState()}/>,
    <div id='hello'>hello</div>,
    document.getElementById('root')
  )
}

render()
store.subscribe(render)