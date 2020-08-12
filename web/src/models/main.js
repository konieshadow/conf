import { logout } from '../services/loginService';
import { getCurrentUserInfo } from '../services/userService';
import { selectAllNamespaceNames } from '../services/namespaceService';
import { routerRedux } from 'dva/router';
import { effects } from 'dva/saga';

const { all } = effects;

export default {
    namespace: 'main',

    state: {
        loading: true,
        userInfo: null,
        allNamespaces: null,
        selectedNamespace: null,
    },

    reducers: {
        updateLoading(state, { payload: loading }) {
            return {
                ...state,
                loading
            };
        },
        updateUserInfo(state, { payload: userInfo }) {
            return {
                ...state,
                userInfo
            };
        },
        updateAllNamespaces(state, { payload: allNamespaces }) {
            return {
                ...state,
                allNamespaces
            }
        },
        updateSelectedNamespace(state, { payload: selectedNamespace }) {
            return {
                ...state,
                selectedNamespace
            }
        }
    },

    effects: {
        *fetchInitData(_action, { put, call }) {
            try {
                yield put({ type: 'updateLoading', payload: true });
                const [userInfo, namespaces] = yield all([call(getCurrentUserInfo), call(selectAllNamespaceNames)]);
                yield put({ type: 'updateUserInfo', payload: userInfo });
                yield put({ type: 'updateAllNamespaces', payload: namespaces});
                yield put({ type: 'updateSelectedNamespace', payload: namespaces[0]});
            } catch (err) {
                console.error(err);
            } finally {
                yield put({ type: 'updateLoading', payload: false });
            }
        },
        *requestLogout(_action, { put, call }) {
            try {
                yield call(logout)
            } catch (err) {
                console.error(err);
            } finally {
                yield put({ type: 'updateUserInfo', payload: null });
                yield put(routerRedux.push('/login'));
            }
        }
    },

}