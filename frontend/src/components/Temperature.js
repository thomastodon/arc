import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import store from "../store/store";
import _ from "lodash";
import {STREAM_TEMPERATURE} from "../actions/temperatureAction";

export function mapStateToProps(state) {
  return {temperature: state.temperature}
}

export class Temperature extends Component {

  static propTypes = {
    temperature: PropTypes.string
  };

  constructor(props) {
    super(props)
  }

  componentWillMount() {
    store.dispatch({type: STREAM_TEMPERATURE});
  }

  renderTemperature() {
    if (!_.isEmpty(this.props.temperature)) {
      return <div className="temperature">{this.props.temperature}</div>
    }
  }

  render() {
    console.log(this.props.temperature);
    return (
      <div className="temperature">{this.props.temperature}</div>
    )
  }
}

export default connect(state => mapStateToProps(state))(Temperature);
