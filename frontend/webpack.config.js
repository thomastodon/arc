var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var autoprefixer = require('autoprefixer');
var ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
    devtool: 'cheap-module-source-map',
    entry: './src/App',
    output: {
        path: path.join(__dirname, 'build'),
        filename: 'js/bundle-[hash].js'
    },
    plugins: [
        new HtmlWebpackPlugin({template: 'src/index.html'}),
        new ExtractTextPlugin("css/thub-ui-[hash].css")
    ],
    module: {
        loaders: [{
            test: /\.jsx?$/,
            loaders: ['babel'],
            include: path.join(__dirname, 'src')
        }, {
            test: /\.scss$/,
            loader: ExtractTextPlugin.extract('style', ['css!postcss!sass'])
        }, {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract('style', ['css!postcss'])
        }, {
            test: /\.json$/,
            loader: 'json'
        }]
    },
    postcss: function () {
        return [autoprefixer];
    }
};