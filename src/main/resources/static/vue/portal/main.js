Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', component: httpVueLoader('/demo.vue')},
    {path: '/home', component: httpVueLoader('/vue/portal/home.vue')},
    {path: '/login', component: httpVueLoader('/vue/portal/login.vue')},
    {path: '/registry', component: httpVueLoader('/vue/portal/registry.vue')},
];
const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
});

const app = new Vue({
    el: '#app',
    router,
    components: {},
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
        // this.getUserInfo()
        router.push({path: '/demo'})
        console.log(123)
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
