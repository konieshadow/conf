import fetch from 'dva/fetch';
import qs from  'querystring';
import { FetchError } from './errorrs';

const defaultHeaders = {
    'content-type': 'application/json'
};

export function request(method, api, body, param=null, headers=null) {
    return fetch(applyQueryParameters(api, param), createFetchConfig(method, body, headers)).then(responseInterceptor, responseErrorIntercepter);
}

export function get(api, param=null, headers=null) {
    return request('GET', api, null, param, headers);
}

export function post(api, body, param=null, headers=null) {
    return request('POST', api, body, param, headers);
}

export function put(api, body, param=null, headers=null) {
    return request('PUT', api, body, param, headers);
}

export function del(api, body, param=null, headers=null) {
    return request('DELETE', api, body, param, headers);
}

function applyQueryParameters(api, param) {
    if (param == null) {
        return api;
    }

    const encoded = qs.stringify(param);
    if (api.indexOf('?') <= 0) {
        return api + '?' + encoded;
    } else {
        return api + '&' + encoded;
    }
}

function createFetchConfig(method, body, headers) {
    const config = {
        method,
        body: body == null ? null : JSON.stringify(body),
        headers: {
            ...defaultHeaders,
            ...headers
        }
    }
    return config;
}

function responseInterceptor(res) {
    if (res.ok) {
        return res.json();
    }
    return res.text().then(body => {
        throw new FetchError(res.status,  body);
    });
}

function responseErrorIntercepter(error) {
    if (error instanceof FetchError) {
        throw FetchError();
    }
    return new FetchError(0, error.message);
}