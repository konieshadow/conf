import { get } from '../utils/request';

export function selectConfigs(param) {
    return get('/api/config', param);
}