import React, {PropTypes, Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import setTemperature from "../../actions/temperature"
import _ from "lodash";

export function mapStateToProps(state) {
    return {temperature: state.temperature}
}

@connect(mapStateToProps, {
    setTemperature,
})
export default class Temperature extends Component {

    static propTypes = {
        temperature: PropTypes.string,
    }

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.setTemperature()
    }

    renderTemperature() {
        if (!_.isEmpty(this.props.temperature)) {
            return <div className="temperature">{this.props.temperature}</div>
        }
    }

    render() {
        return (
            <div className="temperature">{this.props.temperature}</div>
        )
    }
}
