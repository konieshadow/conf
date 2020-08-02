import React from 'react';
import { Layout, Form, Input, Button } from 'antd';
import { connect } from 'dva';
import LayoutHeader from '../LayoutHeader';

import './styles/LoginPage.css';

const { Content } = Layout;

function LoginPage(props) {
    const { dispatch, submiting } = props;

    const handleFormFinish = (values) => {
        dispatch({ type: 'login/requestLogin', payload: values });
    }

    return (
        <Layout>
            <LayoutHeader />
            {
                <Content className="BasicLayout_login">
                    <div className="Login_form_container">
                        <Form layout="vertical" onFinish={handleFormFinish}>
                            <Form.Item name="username" label="用户名" rules={[{ required: true, message: "请输入用户名" }]}>
                                <Input />
                            </Form.Item>
                            <Form.Item name="password" label="密码" rules={[{ required: true, message: "请输入密码" }]}>
                                <Input type="password" />
                            </Form.Item>
                            <Form.Item>
                                <Button className="Login_submit" type="primary" htmlType="submit" loading={submiting}>提交</Button>
                            </Form.Item>
                        </Form>
                    </div>
                </Content>
            }
        </Layout>
    )
}

function mapStateToProps(state) {
    return {
        submiting: state.login.submiting,
    }
}

export default connect(mapStateToProps)(LoginPage);