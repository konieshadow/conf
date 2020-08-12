import { selectConfigs } from '../services/configService';

export default {
    namespace: 'config',

    state: {
        loading: false,
        searchParam: {},
        pageParam: {
            current: 1,
            size: 20,
        },
        configs: []
    },

    reducers: {
        updateLoading(state, { payload: loading }) {
            return {
                ...state,
                loading
            };
        },
        updateSearchParam(state, { payload: searchParam }) {
            console.log(searchParam);
            return {
                ...state,
                searchParam
            };
        },
        updatePageParam(state, { payload: pageParam }) {
            return {
                ...state,
                pageParam
            };
        },
        updateConfigs(state, { payload: configs }) {
            return {
                ...state,
                configs
            };
        }
    },

    effects: {
        *fetchConfigs(_action, { put, call, select }) {
            try {
                yield put({ type: 'updateLoading', payload: true });
                const { searchParam, pageParam } = yield select(state => state.config);
                const configs = yield call(selectConfigs, {
                    ...pageParam,
                    ...searchParam
                });
                yield put({ type: 'updateConfigs', payload: configs });
            } catch (err) {
                console.error(err);
            } finally {
                yield put({ type: 'updateLoading', payload: false });
            }
        }
    }
}