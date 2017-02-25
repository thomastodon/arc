import * as actions from '../../src/actions/index'

describe('streamTemperature', () => {
	it('returns an object with the type of STREAM_TEMPERATURE', function() {
		expect(actions.streamTemperature()).toEqual({
			type: 'STREAM_TEMPERATURE'
		});
	});
});