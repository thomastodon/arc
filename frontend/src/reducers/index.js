var _ = require('lodash');

var initialState = {
	items: []
};

function reducer(state, action) {
	if (typeof state === 'undefined') {
		return initialState;
	}
	
	switch(action.type) {
		case 'STREAM_TEMPERATURE':
			return _.assign({}, state, {
				items: _.concat(state.items, action.data)
			}
		default:
			return state
	}
}

module.exports = reducer;