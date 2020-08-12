import { login } from '../services/loginService';
import { getCurrentUserInfo } from '../services/userService';
import { selectAllNamespaceNames } from '../services/namespaceService';
import { routerRedux } from 'dva/router';
import { effects } from 'dva/saga';

const { all } = effects;

export default {
    namespace: 'login',

    state: {
        submiting: false,
    },

    reducers: {
        updateSubmiting(state, { payload: submiting }) {
            return {
                ...state,
                submiting
            };
        }
    },

    effects: {
        *requestLogin({ payload }, { put, call }) {
            try {
                yield put({ type: 'updateSubmiting', payload: true });
                const { username, password } = payload;
                yield call(login, username, password);
                const [userInfo, namespaces] = yield all([call(getCurrentUserInfo), call(selectAllNamespaceNames)]);
                yield put({ type: 'main/updateLoading', payload: true });
                yield put({ type: 'main/updateUserInfo', payload: userInfo });
                yield put({ type: 'main/updateAllNamespaces', payload: namespaces });
                yield put({ type: 'main/updateSelectedNamespace', payload: namespaces[0]});
                yield put({ type: 'main/updateLoading', payload: false });
                yield put(routerRedux.push('/'));
            } catch (err) {
                console.error(err);
            } finally {
                yield put({ type: 'updateSubmiting', payload: false });
            }
        }
    },
}