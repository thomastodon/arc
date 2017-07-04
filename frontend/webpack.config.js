const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

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
    new ExtractTextPlugin({filename: 'styles.css', allChunks: true}),
    new HtmlWebpackPlugin({template: 'src/index.html'})
  ],
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: ['css-loader', 'sass-loader']
        })
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        include: path.join(__dirname, 'src'),
        use: {
          loader: 'babel-loader',
          options: {
            presets: ["react", "es2015", "stage-0"]
          }
        }
      }
    ]
  },
  devServer: {
    stats: 'errors-only'
  },
}