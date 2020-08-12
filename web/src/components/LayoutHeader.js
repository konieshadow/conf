import React from 'react';
import { Layout, Button, Dropdown, Menu, Select } from 'antd';
import { connect } from 'dva';
import { Link } from 'dva/router';

const { Header } = Layout;

function LayoutHeader(props) {
    const { dispatch, userInfo, allNamespaces, selectedNamespace } = props;

    const handleLogout = () => {
        dispatch({ type: 'main/requestLogout' });
    }

    const dropdownOverlay = <Menu>
        <Menu.Item><Link to="/password">修改密码</Link></Menu.Item>
        <Menu.Item onClick={handleLogout}>退出</Menu.Item>
    </Menu>

    return (
        <Header className="BasicLayout_header">
            <h1 className="BasicLaout_logo">Conf</h1>
            <div className="BasicLayout_toolbar">
                {
                    allNamespaces && <Select bordered={false} style={{width: 120}} value={selectedNamespace}>
                    {
                        allNamespaces.map(item => <Select.Option key={item}>{item}</Select.Option>)
                    }
                    </Select>
                }
            </div>
            <div className="BasicLayout_navmenu">
                <Button type="link">
                    <a href="https://github.com/konieshadow/conf" target="_blank" rel="noopener noreferrer">Github</a>
                </Button>
                {
                    userInfo && <Dropdown overlay={dropdownOverlay}>
                        <Button type="link">{userInfo.name}</Button>
                    </Dropdown>
                }
            </div>
        </Header>
    )
}

function mapStateToProps({main}) {
    return {
        userInfo: main.userInfo,
        allNamespaces: main.allNamespaces,
        selectedNamespace: main.selectedNamespace,
    };
}

export default connect(mapStateToProps)(LayoutHeader);