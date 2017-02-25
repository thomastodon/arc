var reducer = require('../js/reducers/reducer.js');

describe('reducer', function() {
	describe('default', function() {
		it('returns the initial state', function() {
			expect(reducer()).toEqual({items: []});
		});
	});

	describe('on ADD_ITEM action', function() {
		var state;

		beforeEach(function() {
			state = {
				items: ['one']
			}
		});

		it('returns the state with the new item added', function() {
			expect(reducer(state, {type: 'ADD_ITEM', data: 'two'})).toEqual({items: ['one', 'two']});
		});
	});
});