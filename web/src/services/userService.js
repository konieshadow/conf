import { get } from '../utils/request';

export function getCurrentUserInfo() {
    return get('/api/user/current');
}