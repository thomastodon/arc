var path = require('path')
var webpack = require('webpack')

module.exports = {
  devtool: 'cheap-module-source-map',
  entry: [
    'webpack-dev-server/client?http://localhost:3000',
    'webpack/hot/only-dev-server',
    './src/App'
  ],
  output: {
    pathinfo: true,
    path: path.join(__dirname, 'build'),
    filename: 'bundle.js'
  },
  module: {
    loaders: [{
      test: /\.jsx?$/,
      loaders: [ 'babel' ],
      exclude: /node_modules/,
      include: path.join(__dirname, 'src')
    }]
  },
  devServer: {
    stats: 'errors-only',
    contentBase: "./src"
  },
}