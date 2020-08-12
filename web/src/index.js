import dva from 'dva';
import './index.css';

// 1. Initialize
const app = dva();

// 2. Plugins
// app.use({});

// 3. Model
app.model(require('./models/main').default);
app.model(require('./models/login').default);
app.model(require('./models/config').default);

// 4. Router
app.router(require('./components/routers/AppRouter').default);

// 5. Start
app.start('#root');