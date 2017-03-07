jest.unmock('../temperature')

import reducer from "../temperature";
import * as reducers from "../temperature";
import * as actions from "../../actions/temperature";

describe('temperature', () => {
    describe('temperature reducer', () => {

        it('starts with empty string default state', () => {
            const newState = reducer(undefined, {})
            expect(newState.temperature).toEqual('')
        })

        it('updates temperature on SET_TEMPERATURE', () => {
            const action = {
                type: actions.SET_TEMPERATURE,
                degrees: 13.46
            }

            const newState = reducer(undefined, action)
            expect(newState.temperature).toEqual('13.46')
        })

        it('clears temperature on CLEAR_TEMPERATURE', () => {
            const action = {
                type: actions.CLEAR_TEMPERATURE
            }

            const newState = reducer({temperature: "25.94"}, action)
            expect(newState.temperature).toEqual('')
        })
    })
})
