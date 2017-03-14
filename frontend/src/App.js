import "babel-polyfill"

import Temperature from './components/Temperature'
import React, { Component } from 'react';

export default class App extends Component {
  render() {
    return (
      <Temperature></Temperature>
    );
  }
}