Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', name: 'demo', component: httpVueLoader('/demo.vue')},
    {path: '/user-list', name: 'userList', component: httpVueLoader('/vue/manage/user-list.vue')},
    {path: '/goods-add', name: 'goodsAdd', component: httpVueLoader('/vue/manage/goods-add.vue')},
    {path: '/goods-query', name: 'goodsQuery', component: httpVueLoader('/vue/manage/goods-query.vue')},


    {path: '/manage-index', name: 'manageIndex', component: httpVueLoader('/vue/manage/index.vue')},


    {path: '/manage-welcome', name: 'manageWelcome', component: httpVueLoader('/vue/manage/welcome.vue')},
    {path: '/portal-home', name: 'portalHome', component: httpVueLoader('/vue/portal/home.vue')},
    {path: '/mine-info', name: 'mineInfo', component: httpVueLoader('/vue/portal/mine-info.vue')},
    {path: '/change-password', name: '/changePassword', component: httpVueLoader('/vue/portal/change-password.vue')},
    {path: '/notices-list', name: '/noticesList', component: httpVueLoader('/vue/portal/notices-list.vue')},
    {path: '/mine-notices', name: '/mineNotices', component: httpVueLoader('/vue/portal/mine-notices.vue')},
    {path: '/add-notices', name: '/addNotices', component: httpVueLoader('/vue/portal/add-notices.vue')},
    {path: '/edit-notices', name: '/editNotices', component: httpVueLoader('/vue/portal/edit-notices.vue')},
    {path: '/login', name: '/login', component: httpVueLoader('/vue/portal/login.vue')},
    {path: '/registry', name: 'registry', component: httpVueLoader('/vue/portal/registry.vue')},
];
const router = new VueRouter({
    routes, // (缩写) 相当于 routes: routes
    // mode: "history"
});

const app = new Vue({
    el: '#app',
    router,
    components: {
        'manage-index': httpVueLoader('/vue/manage/index.vue')
    },
    data() {
        return {
            ordersList: [],
            userInfo: {
                username: '',
            },
            welcome: true,
            outerVisible: true,
            innerVisible: false,
            show: {
                mineInfo: false,
                changePassword: false,
                ordersList: false,
            },
            form: {
                password: '',
                newPassword: '',
                rePassword: '',
            }
        }
    },
    mounted() {
        console.log('main.js加载完成')
    },
    methods: {
        getUserInfo() {
            axios.get(`/getUserInfo`).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
                    return
                }
                this.userInfo = result.data ? result.data : null;
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
        },
        saveUserInfo() {
            axios.post(`/manage/saveUserInfo`, this.userInfo).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
                    return
                }
                this.$message.success(result.message);
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
        },
        savePassword() {
            axios.post(`/manage/savePassword`, this.form).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    return
                }
                this.$message.success(result.message);
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
        }
    }
});
