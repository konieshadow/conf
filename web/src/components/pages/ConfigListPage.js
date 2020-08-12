import React, { useEffect } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Form, Input, Button, Table } from 'antd';
import { PlusOutlined } from '@ant-design/icons';

function ConfigListPage(props) {
    const { dispatch, configs, pageParam } = props;
    const [ form ] = Form.useForm();

    const columns = [
        {
            title: 'Data ID',
            dataIndex: 'dataId',
        },
        {
            title: 'Group ID',
            dataIndex: 'groupId',
        }, {
            title: '操作',
            render: (_, rowData) => (<div></div>)
        }
    ]

    const handleSearch = (values) => {
        dispatch({ type: 'config/updateSearchParam', payload: values });
        dispatch({ type: 'config/fetchConfigs' });
    }

    const handleTableChange = (pagination, filters, sorter) => {
        console.log(pagination, filters, sorter);
    }

    useEffect(() => {
        dispatch({ type: 'config/fetchConfigs' });
    }, [dispatch]);

    return (
        <div>
            <div className="search-pane">
                <Form layout="inline" form={form} onFinish={handleSearch}>
                    <Form.Item label="Data ID" name="dataId">
                        <Input />
                    </Form.Item>
                    <Form.Item label="Group ID" name="groupId">
                        <Input />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">搜索</Button>
                    </Form.Item>
                    <Form.Item style={{marginLeft: 30}}>
                        <Button type="primary">导出结果</Button>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary">导入配置</Button>
                    </Form.Item>
                    <Form.Item>
                        <Link to="configs/create"><Button icon={<PlusOutlined />} /></Link>
                    </Form.Item>
                </Form>
            </div>
            <div className="data-pane">
                <Table
                    rowKey="id"
                    dataSource={configs.records}
                    columns={columns}
                    onChange={handleTableChange}
                    pagination={{ current: pageParam.current, pageSize: pageParam.size }} />
            </div>
        </div>
    );
}

function mapStateToProps({ config }) {
    return {
        configs: config.configs,
        pageParam: config.pageParam,
    };
}

export default connect(mapStateToProps)(ConfigListPage);