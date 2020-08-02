import { logout } from '../services/loginService';
import { getCurrentUserInfo } from '../services/userService';
import { routerRedux } from 'dva/router';

export default {
    namespace: 'main',

    state: {
        loading: true,
        userInfo: null,
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
        }
    },

    effects: {
        *fetchUserInfo(_action, { put, call }) {
            try {
                yield put({ type: 'updateLoading', payload: true });
                const userInfo = yield call(getCurrentUserInfo);
                yield put({ type: 'updateUserInfo', payload: userInfo });
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
                yield put(routerRedux.push('/login'));
            }
        }
    },

}