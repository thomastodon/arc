var path = require('path')
var webpack = require('webpack')
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  devtool: 'eval',
  entry: [
    path.join(__dirname, 'src/index')
  ],
  output: {
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