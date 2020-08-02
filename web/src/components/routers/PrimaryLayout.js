import React, { useEffect } from 'react';
import { Layout, Menu, Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import { connect } from 'dva';
import { Link, Switch, Route, Redirect } from 'dva/router';
import MENU_DEFINE from '../../utils/menus';
import LayoutHeader from '../LayoutHeader';
import ConfigPage from '../pages/ConfigPage';
import NamespacePage from '../pages/NamespacePage';
import UserPage from '../pages/UserPage';

import './PrimaryLayout.css';

const { SubMenu } = Menu;
const { Content, Sider } = Layout;

const loadingIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
Spin.setDefaultIndicator(loadingIcon);

function PrimaryLayout(props) {
    const { dispatch, match, loading, userInfo } = props;

    useEffect(() => {
        dispatch({ type: 'main/fetchUserInfo' });
    }, [dispatch]);

    return (
        <Layout className="BasicLayout">
            <LayoutHeader />
            <Layout>
                <Sider className="BasicLayout_sider">
                    <Menu
                        mode="inline"
                        defaultOpenKeys={getMenuOpenKeys(MENU_DEFINE)}
                        selectedKeys={getMenuSelectedKeys(MENU_DEFINE, match.path)}
                        style={{ height: '100%', borderRight: 0 }}
                    >
                        { renderMenus(MENU_DEFINE) }
                    </Menu>
                </Sider>
                <Content className="BasicLayout_cotent">
                    {
                        loading ? <div className="fullscreen"><Spin size="large" delay={1000} /></div> : (
                            !userInfo ? <Redirect to="/login" /> : (
                                <Switch>
                                    <Redirect from="/" exact  to="configs" />
                                    <Route path="/configs" exact component={ConfigPage} />
                                    <Route path="/namespaces" exact component={NamespacePage} />
                                    <Route path="/users" exact component={UserPage} />
                                </Switch>
                            )
                        )
                    }
                </Content>
            </Layout>
        </Layout>
    )
}

function getMenuOpenKeys(menus) {
    return menus.filter(item => item.children).map(item => item.key);
}

function getMenuSelectedKeys(menus, pathname) {
    for (let item of menus) {
        if (item.children) {
            for (let childItem of item.children) {
                if (childItem.path === pathname) {
                    return childItem.key;
                }
            }
        } else {
            if (item.path === pathname) {
                return item.key;
            }
        }
    }
    return null;
}

function renderMenus(menus) {
    return menus.map(item => {
        if (item.children) {
            return <SubMenu key={item.key} title={item.title}>
                {
                    item.children.map(childItem => {
                        return <Menu.Item key={childItem.key}>
                            <Link to={childItem.path}>{childItem.title}</Link>
                        </Menu.Item>
                    })
                }
            </SubMenu>;
        }

        return <Menu.Item key={item.key}>
            <Link to={item.path}>{item.title}</Link>
        </Menu.Item>
    });
}

function mapStateToProps(state) {
    return {
        loading: state.main.loading,
        userInfo: state.main.userInfo,
    }
}

export default connect(mapStateToProps)(PrimaryLayout);