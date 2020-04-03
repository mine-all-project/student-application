// 0. 如果使用模块化机制编程，导入Vue和VueRouter，要调用 Vue.use(VueRouter)

// 1. 定义 (路由) 组件。
// 可以从其他文件 import 进来
Vue.use(httpVueLoader);
const Foo = {template: '<div>foo</div>'}
const Bar = {template: '<div>bar</div>'}
// const Demo = httpVueLoader('/demo.vue')

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
    {path: '/foo', component: Foo},
    {path: '/bar', component: Bar},
    {path: '/demo', component: httpVueLoader('/demo.vue')},
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})


// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能

const app = new Vue({
    el: '#app',
    router,
    components: {
        // Demo
    },
    data() {
        const item = {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
        };
        return {
            tableData: Array(20).fill(item),
            menus: [
                {
                    id: '',
                    name: '导航',
                    icon: 'el-icon-message',
                    children: [
                        {
                            id: '',
                            name: '选项1',
                            icon: 'el-icon-message',
                            url: '/manage/pictures'
                        },
                        {
                            id: '',
                            name: '选项2',
                            icon: '',
                            url: '/manage/memberEdit'
                        },
                        {
                            id: '',
                            name: '选项3',
                            icon: '',
                            url: '/foo'
                        },
                    ]
                },
            ]
        }
    },
    methods: {
        clickMenu(url) {
            router.push({path: url, params: {userId: 123}})
            console.log(url)
        }
    }
})


// 现在，应用已经启动了！
