jest.unmock('../apiService')
jest.mock('axios')

import * as service from "../apiService";
import axios from 'axios'
import { getHost } from '../../actions/locationFacade'

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

    describe('transaction-hub-gateway host resolution', () => {

        it('defaults to local', () => {
            getHost.mockReturnValue('localhost:3000')

            expect(service.resolveEnvApiHost()).toBe('http://localhost:8181')
        })

        it('resolves acceptance env', () => {
            getHost.mockReturnValue('transaction-hub-ui-acceptance.apps.system.npc.pcf.ntrs.com')

            expect(service.resolveEnvApiHost()).toBe('https://transaction-hub-gateway-acceptance.apps.system.npc.pcf.ntrs.com')
        })

    })

    describe('apiGet', () => {

        it('gets from transaction-hub-gateway api', () => {
            const promise = createPromise();
            axios.get.mockReturnValue(promise)
            const transaction = {some: 'data'}

            const response = service.apiGet('/transaction/id');

            expect(axios.get).toBeCalledWith('/transaction/id')
            expect(response).toBe(promise)
        })
    })
})
