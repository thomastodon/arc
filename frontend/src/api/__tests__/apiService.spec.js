jest.unmock("../apiService");

import * as service from "../apiService";
import axios from 'axios'
import {getHost} from '../../actions/locationFacade'

function createPromise(resolvedData) {
  return {
    resolvedData,
    resolved: false,
    then: function (callback) {
      this.callback = callback
      if (this.resolved) this.callback(this.resolvedData)
      return this
    },
    resolve: function () {
      this.resolved = true
      if (this.callback) this.callback(this.resolvedData)
    }
  }
}

describe('apiService', () => {
  describe('host resolution', () => {
    it('resolves correctly', () => {
      getHost.mockReturnValue('localhost:3000')
      expect(service.resolveEnvApiHost()).toBe('http://localhost:8080')
    })
  })

  describe('apiGet', () => {
    it('gets from temperature api', () => {
      const promise = createPromise();
      axios.get.mockReturnValue(promise)
      const temperature = {degrees: 32.94}

      const response = service.apiGet('/temperature');

      expect(axios.get).toBeCalledWith('/temperature')
      expect(response).toBe(promise)
    })
  })
})
