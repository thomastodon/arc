jest.unmock('../Temperature')

import React from "react";
import {shallow} from "enzyme";
import Temperature, {mapStateToProps} from "../Temperature";

describe('Temperature', () => {

  describe('connecting to redux', () => {
    it('maps values on component props', () => {
      const state = {temperature: "34.10"}
      const mappedProps = mapStateToProps(state)
      expect(mappedProps.temperature).toBe('34.10')
    })
  })

  describe('component', () => {
    const createPage = props => {
      return <Temperature.WrappedComponent temperature={""} {...props}/>
    }

    describe('render', () => {
      it('renders temperature when temperature is set', () => {
        const dom = shallow(createPage({temperature: '23.49'}))
        expect(dom.find('.temperature').text()).toContain('23.49')
      })
    })
  })
})