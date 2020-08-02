const MENU_DEFINE = [
    { key: 'config-manager', title: '配置管理', children: [
        { path: '/configs', key: 'configs', title: '配置列表'},
        { path: '/namespaces', key: 'namespaces', title: '命名空间'}
    ]},
    { path: '/users', key: 'users', title: '用户管理'}
];

export default MENU_DEFINE;