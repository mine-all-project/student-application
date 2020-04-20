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
  {path: '/demo', component: httpVueLoader('/demo.vue')},
  {path: '/home', component: httpVueLoader('/vue/portal/home.vue')},

  {path: '/about', component: httpVueLoader('/vue/portal/about.vue')},
  {path: '/culture', component: httpVueLoader('/vue/portal/culture.vue')},
  {path: '/honor', component: httpVueLoader('/vue/portal/honor.vue')},

  {path: '/notice', component: httpVueLoader('/vue/portal/notice.vue')},
  {path: '/notice-detail', component: httpVueLoader('/vue/portal/notice-detail.vue')},
  {path: '/news', component: httpVueLoader('/vue/portal/news.vue')},
  {path: '/news-detail', component: httpVueLoader('/vue/portal/news-detail.vue')},

  {path: '/products', component: httpVueLoader('/vue/portal/products.vue')},
  {path: '/products-detail', component: httpVueLoader('/vue/portal/products-detail.vue')},
  {path: '/friends', component: httpVueLoader('/vue/portal/friends.vue')},
  {path: '/contact', component: httpVueLoader('/vue/portal/contact.vue')},

];

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  routes // (缩写) 相当于 routes: routes
});


// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能

const app = new Vue({
  el: '#app',
  router,
  components: {},
  data() {
    const item = {
      date: '2020-04-01',
      name: '全',
      address: '贵州省六盘水市钟山区'
    };
    return {
      userInfo: {},
      tableData: Array(20).fill(item),
      menus: [
        {
          id: '1',
          name: '网站管理',
          icon: 'el-icon-message',
          children: [
            {
              id: '1-1',
              name: '公告管理',
              icon: 'el-icon-message',
              url: '/manage/notice'
            },
            {
              id: '1-2',
              name: '新闻管理',
              icon: '',
              url: '/manage/news'
            },
            {
              id: '1-3',
              name: '企业文化',
              icon: '',
              url: '/manage/culture'
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
            },
            {
              id: '2-2',
              name: '旅游资讯',
              icon: '',
              url: '/manage/tourism'
            },
            {
              id: '2-4',
              name: '通知公告',
              icon: '',
              url: '/manage/notice'
            },
          ]
        },
        {
          id: '3',
          name: '订单管理',
          icon: 'el-icon-message',
          children: [
            {
              id: '3-1',
              name: '订单列表',
              icon: 'el-icon-message',
              url: '/manage/tickets'
            },
          ]
        },
        {
          id: '4',
          name: '评论',
          icon: 'el-icon-message',
          children: [
            {
              id: '4-1',
              name: '主题1',
              icon: 'el-icon-message',
              url: '/manage/area1'
            },
            {
              id: '4-2',
              name: '主题2',
              icon: '',
              url: '/manage/area2'
            },
          ]
        },
      ],
      welcome: true,
    }
  },
  mounted() {
    this.getUserInfo()
    // router.push({path: '/home', params: {userId: 123}})
  },
  methods: {
    clickMenu(url) {
      this.welcome = false;
      router.push({path: url, params: {userId: 123}});
      console.log(url);
    },
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
  }
});
