var path = require('path')
var webpack = require('webpack')
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  devtool: 'cheap-module-source-map',
  entry: [
    'webpack-dev-server/client?http://localhost:3000',
    'webpack/hot/only-dev-server',
    './src/index'
  ],
  output: {
    pathinfo: true,
    path: path.join(__dirname, 'build'),
    filename: 'bundle.js'
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'src/index.html'
    })
  ],
  module: {
    loaders: [{
      test: /\.jsx?$/,
      loaders: [ 'babel' ],
      exclude: /node_modules/,
      include: path.join(__dirname, 'src')
    }]
  },
  devServer: {
    stats: 'errors-only'
  },
}