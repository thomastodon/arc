import * as temperatureActions from "../actions/temperatureAction";

export function temperature(previousState = '', action) {
  switch (action.type) {
    case temperatureActions.SET_TEMPERATURE:
      return String(action.degrees);
    case temperatureActions.CLEAR_TEMPERATURE:
      return ''
  }
  return previousState
}

export default temperature