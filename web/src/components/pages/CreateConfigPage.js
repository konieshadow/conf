import React from 'react';
import { connect } from 'dva';
import { Form, Button, Input } from 'antd';
import MonacoEditor from 'react-monaco-editor';
import 'monaco-editor/esm/vs/basic-languages/ini/ini.contribution';

function CreateConfigPage(props) {
    const { dispatch } = props;
    const [ form ] = Form.useForm();

    const formLayout = {
        labelCol: { span: 3 },
        wrapperCol: { span: 21 },
    };

    const tailLayout = {
        wrapperCol: { offset: 3, span: 21 },
    };

    return (
        <div>
            <h3 className="page-title">新建配置</h3>
            <div className="edit-pane">
                <Form {...formLayout} form={form}>
                    <Form.Item label="Data ID">
                        <Input />
                    </Form.Item>
                    <Form.Item label="Groupd ID">
                        <Input />
                    </Form.Item>
                    <Form.Item label="描述">
                        <Input.TextArea rows={2} />
                    </Form.Item>
                    <Form.Item label="内容">
                    <MonacoEditor
                        width="100%"
                        height="300"
                        language="ini"
                        theme="vs-dark"
                    />
                    </Form.Item>
                    <Form.Item {...tailLayout}>
                        <Button type="primary" style={{marginRight: 16}}>发布</Button>
                        <Button>返回</Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    );
}

function mapStateToProps({ config }) {
    return {
    };
}

export default connect(mapStateToProps)(CreateConfigPage);