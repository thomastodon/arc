var babelJest = require('babel-jest');

module.exports = {
    process: function (src, filename) {
        if (!filename.match(/\.jsx?$/)) {
            return '';
        } else {
            return babelJest.process(src, filename);
        }
    }
};