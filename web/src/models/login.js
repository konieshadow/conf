import { login } from '../services/loginService';
import { routerRedux } from 'dva/router';

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
                yield put(routerRedux.push('/'));
            } catch (err) {
                console.error(err);
            } finally {
                yield put({ type: 'updateSubmiting', payload: false });
            }
        }
    },
}