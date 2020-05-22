Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', name: 'demo', component: httpVueLoader('/demo.vue')},
    {path: '/manage-index', name: 'manageIndex', component: httpVueLoader('/vue/manage/index.vue')},
    {path: '/welcome', name: 'welcome', component: httpVueLoader('/vue/manage/welcome.vue')},

    {path: '/lineups-list', name: 'lineUpsList', component: httpVueLoader('/vue/manage/lineups-list.vue')},
    {path: '/lineups-add', name: 'lineUpsAdd', component: httpVueLoader('/vue/manage/lineups-add.vue')},
    {path: '/lineups-info', name: 'lineUpsInfo', component: httpVueLoader('/vue/manage/lineups-info.vue')},

    {path: '/rooms-list', name: 'roomsList', component: httpVueLoader('/vue/manage/rooms-list.vue')},
    {path: '/machines-list', name: 'machinesList', component: httpVueLoader('/vue/manage/machines-list.vue')},

    {path: '/message-list', name: 'messageList', component: httpVueLoader('/vue/manage/message-list.vue')},

    {path: '/goods-list', name: 'goodsList', component: httpVueLoader('/vue/manage/goods-list.vue')},
    {path: '/goods-input', name: 'goodsInput', component: httpVueLoader('/vue/manage/goods-input.vue')},
    {path: '/goods-output', name: 'goodsOutput', component: httpVueLoader('/vue/manage/goods-output.vue')},

    {path: '/sales-list', name: 'salesList', component: httpVueLoader('/vue/manage/sales-list.vue')},

    {path: '/user-list', name: 'userList', component: httpVueLoader('/vue/manage/user-list.vue')},
    {path: '/database-list', name: 'databaseList', component: httpVueLoader('/vue/manage/database-list.vue')},
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
