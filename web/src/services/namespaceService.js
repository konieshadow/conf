import { get } from '../utils/request';

export function selectAllNamespaceNames() {
    return get('/api/namespace/all');
}