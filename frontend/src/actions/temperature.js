export const STREAM_TEMPERATURE = 'STREAM_TEMPERATURE'
export const SET_TEMPERATURE = 'SET_TEMPERATURE'

export function streamTemperature() {
	return {
		type: 'STREAM_TEMPERATURE'
	}
}

export function setTemperature(degrees) {
    return {
        type: SET_TEMPERATURE,
        degrees
    }
}