import React from 'react';
import { HashRouter, Switch, Route } from 'dva/router';
import LoginPage from '../pages/LoginPage';
import PrimaryLayout from './PrimaryLayout';

export default function AppRouter() {
    return (
        <HashRouter>
            <Switch>
                <Route path="/login" component={LoginPage} />
                <Route paht="/" component={PrimaryLayout} />
            </Switch>
        </HashRouter>
    )
}