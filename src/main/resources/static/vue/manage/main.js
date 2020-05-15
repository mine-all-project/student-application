Vue.use(httpVueLoader);
const routes = [
    {path: '/demo', component: httpVueLoader('/demo-manage.vue')},
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
            menus: [],
            welcome: true
        }
    },
    methods: {
        clickMenu(url) {
            this.welcome = false
            router.push({path: url, query: {userId: 123}})
            console.log(url)
        }
    }
})
