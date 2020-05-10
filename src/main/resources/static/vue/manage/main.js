Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', component: httpVueLoader('/demo-manage.vue')},
    {path: '/manage/house-list', component: httpVueLoader('/vue/manage/house-list.vue')},
    {path: '/manage/user-list', component: httpVueLoader('/vue/manage/user-list.vue')},
    {path: '/manage/contracts', component: httpVueLoader('/vue/manage/contracts.vue')},
    {path: '/manage/leave-message', component: httpVueLoader('/vue/manage/leave-message.vue')},

    {path: '/manage/culture', component: httpVueLoader('/vue/manage/culture.vue')},
    {path: '/manage/honor', component: httpVueLoader('/vue/manage/honor.vue')},
    {path: '/manage/friends', component: httpVueLoader('/vue/manage/friends.vue')},
    {path: '/manage/products', component: httpVueLoader('/vue/manage/products.vue')},
    {path: '/manage/contact', component: httpVueLoader('/vue/manage/contact.vue')},
    {path: '/manage/orders-list', component: httpVueLoader('/vue/manage/house-list.vue')},

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
                    name: '房源管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '1-1',
                            name: '房源列表',
                            icon: '',
                            url: '/manage/house-list'
                        },
                    ]
                },
                {
                    id: '2',
                    name: '用户管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '2-1',
                            name: '用户列表',
                            icon: 'el-icon-message',
                            url: '/manage/user-list'
                        }
                    ]
                },
                {
                    id: '3',
                    name: '合同管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '3-1',
                            name: '合同列表',
                            icon: '',
                            url: '/manage/contracts'
                        },
                    ]
                },
                {
                    id: '4',
                    name: '留言管理',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '4-1',
                            name: '留言列表',
                            icon: 'el-icon-message',
                            url: '/manage/leave-message'
                        }
                    ]
                },
            ],
            activeUrl: '',
            welcome: true
        }
    },
    methods: {
        clickMenu(url) {
            if (url !== this.activeUrl) {
                this.welcome = false
                router.push({path: url, params: {userId: 123}})
                this.activeUrl = url
            }
            console.log(url)
        }
    }
})
