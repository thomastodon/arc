import * as actions from '../temperatureAction'

describe('streamTemperature', () => {
	it('returns an object with the type of STREAM_TEMPERATURE', function() {
		expect(actions.streamTemperature()).toEqual({
			type: 'STREAM_TEMPERATURE'
		});
	});
});

describe('setTemperature', () => {
	it('returns an object with the type of SET_TEMPERATURE', function() {
		expect(actions.setTemperature(31.94)).toEqual({
			type: 'SET_TEMPERATURE',
			degrees: 31.94
		});
	});
});