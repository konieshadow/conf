import { post } from '../utils/request';

export function login(username, password) {
    return post('/api/login', {
        username,
        password
    });
}

export function logout() {
    return post('/api/logout', null);
}