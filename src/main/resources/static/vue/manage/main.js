Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', component: httpVueLoader('/demo-manage.vue')},
    {path: '/manage/lines', component: httpVueLoader('/vue/manage/lines.vue')},
    {path: '/manage/stands', component: httpVueLoader('/vue/manage/stands.vue')},

    {path: '/manage/dynamic', component: httpVueLoader('/vue/manage/dynamic.vue')},

    {path: '/manage/notice', component: httpVueLoader('/vue/manage/notice.vue')},
    {path: '/manage/friends', component: httpVueLoader('/vue/manage/friends.vue')},
    {path: '/manage/products', component: httpVueLoader('/vue/manage/products.vue')},
    {path: '/manage/contact', component: httpVueLoader('/vue/manage/contact.vue')},
    {path: '/manage/user-list', component: httpVueLoader('/vue/manage/user-list.vue')},
    {path: '/manage/orders-list', component: httpVueLoader('/vue/manage/orders-list.vue')},


    {path: '/demo/demo1', component: httpVueLoader('/vue/demo/demo1.vue')},
    {path: '/demo/demo2', component: httpVueLoader('/vue/demo/demo2.vue')},
    {path: '/demo/demo3', component: httpVueLoader('/vue/demo/demo3.vue')},

]

const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})
const app = new Vue({
    el: '#app',
    router,
    components: {},
    data() {
        return {
            menus: [
                {
                    id: '1',
                    name: '公交管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '1-1',
                            name: '公交线路管理',
                            icon: '',
                            url: '/manage/lines'
                        },
                        {
                            id: '1-2',
                            name: '公交站点管理',
                            icon: 'el-icon-message',
                            url: '/manage/stands'
                        },
                    ]
                },
                {
                    id: '2',
                    name: '信息管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '2-1',
                            name: '公交动态管理',
                            icon: '',
                            url: '/manage/dynamic'
                        },
                        {
                            id: '2-2',
                            name: '公告管理',
                            icon: 'el-icon-message',
                            url: '/manage/notice'
                        },
                        {
                            id: '2-3',
                            name: '合作伙伴',
                            icon: '',
                            url: '/manage/friends'
                        },
                        {
                            id: '2-4',
                            name: '产品管理',
                            icon: '',
                            url: '/manage/products'
                        },
                        {
                            id: '2-5',
                            name: '联系我们',
                            icon: '',
                            url: '/manage/contact'
                        },
                    ]
                },
                {
                    id: '3',
                    name: '用户管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '3-1',
                            name: '用户列表',
                            icon: 'el-icon-message',
                            url: '/manage/user-list'
                        }
                    ]
                },
                {
                    id: 'demo',
                    name: '测试菜单',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: 'demo-1',
                            name: '表格列表(富文本)',
                            icon: 'el-icon-message',
                            url: '/demo/demo1'
                        },
                        {
                            id: 'demo-2',
                            name: '表格列表(文本域)',
                            icon: 'el-icon-message',
                            url: '/demo/demo2'
                        },
                        {
                            id: 'demo-3',
                            name: '照片墙',
                            icon: 'el-icon-message',
                            url: '/demo/demo3'
                        },
                        {
                            id: 'demo-4',
                            name: '用户列表',
                            icon: 'el-icon-message',
                            url: '/demo/user-list'
                        },
                        {
                            id: 'demo-5',
                            name: '用户列表',
                            icon: 'el-icon-message',
                            url: '/demo/user-list'
                        }
                    ]
                },
            ],
            welcome: true
        }
    },
    methods: {
        clickMenu(url) {
            this.welcome = false
            router.push({path: url, params: {userId: 123}})
            console.log(url)
        }
    }
})
