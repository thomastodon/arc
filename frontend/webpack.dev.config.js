var path = require('path')
var webpack = require('webpack')

module.exports = {
  devtool: 'eval',
  entry: [
    'webpack-dev-server/client?http://localhost:3000',
    'webpack/hot/only-dev-server',
    path.join(__dirname, 'src/App')
  ],
  output: {
    path: path.join(__dirname, 'build'),
    filename: 'bundle.js'
  },
  module: {
    loaders: [{
      test: /\.js$/,
      loaders: [ 'babel' ],
      exclude: /node_modules/,
      include: path.join(__dirname, 'src')
    }]
  },
  devServer: {
    stats: 'errors-only',
  }
}