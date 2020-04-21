// 0. 如果使用模块化机制编程，导入Vue和VueRouter，要调用 Vue.use(VueRouter)

// 1. 定义 (路由) 组件。
// 可以从其他文件 import 进来
Vue.use(httpVueLoader);

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  {path: '/demo', component: httpVueLoader('/demo-manage.vue')},
  {path: '/manage/about', component: httpVueLoader('/vue/manage/about.vue')},
  {path: '/manage/culture', component: httpVueLoader('/vue/manage/culture.vue')},
  {path: '/manage/honor', component: httpVueLoader('/vue/manage/honor.vue')},

  {path: '/manage/notice', component: httpVueLoader('/vue/manage/notice.vue')},
  {path: '/manage/news', component: httpVueLoader('/vue/manage/news.vue')},
  {path: '/manage/friends', component: httpVueLoader('/vue/manage/friends.vue')},
  {path: '/manage/products', component: httpVueLoader('/vue/manage/products.vue')},
  {path: '/manage/contact', component: httpVueLoader('/vue/manage/contact.vue')},
  {path: '/manage/user-list', component: httpVueLoader('/vue/manage/user-list.vue')},

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
  components: {},
  data() {
    return {
      menus: [
        {
          id: '1',
          name: '公司简介',
          icon: 'el-icon-message',
          children: [
            {
              id: '1-1',
              name: '关于我们',
              icon: '',
              url: '/manage/about'
            },
            {
              id: '1-2',
              name: '企业文化',
              icon: 'el-icon-message',
              url: '/manage/culture'
            },
            {
              id: '1-3',
              name: '企业荣誉',
              icon: '',
              url: '/manage/honor'
            },
          ]
        },
        {
          id: '2',
          name: '网站管理',
          icon: 'el-icon-message',
          children: [
            {
              id: '2-1',
              name: '通知管理',
              icon: '',
              url: '/manage/notice'
            },
            {
              id: '2-2',
              name: '新闻管理',
              icon: 'el-icon-message',
              url: '/manage/news'
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
          id: '4',
          name: '订单管理',
          icon: 'el-icon-message',
          children: [
            {
              id: '4-1',
              name: '订单列表',
              icon: 'el-icon-message',
              url: '/manage/tickets'
            },
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
