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
  {path: '/manage/pictures', component: httpVueLoader('/vue/manage/pictures.vue')},
  {path: '/manage/video', component: httpVueLoader('/vue/manage/video.vue')},
  {path: '/manage/strategy', component: httpVueLoader('/vue/manage/strategy.vue')},
  {path: '/manage/civilization', component: httpVueLoader('/vue/manage/civilization.vue')},

  {path: '/manage/dynamic', component: httpVueLoader('/vue/manage/dynamic.vue')},
  {path: '/manage/tourism', component: httpVueLoader('/vue/manage/tourism.vue')},
  {path: '/manage/active', component: httpVueLoader('/vue/manage/active.vue')},
  {path: '/manage/notice', component: httpVueLoader('/vue/manage/notice.vue')},

  {path: '/manage/tickets', component: httpVueLoader('/vue/manage/tickets.vue')},
  {path: '/manage/hotel', component: httpVueLoader('/vue/manage/hotel.vue')},
  {path: '/manage/food', component: httpVueLoader('/vue/manage/food.vue')},
  {path: '/manage/specialty', component: httpVueLoader('/vue/manage/specialty.vue')},

  {path: '/manage/area1', component: httpVueLoader('/vue/manage/area1.vue')},
  {path: '/manage/area2', component: httpVueLoader('/vue/manage/area2.vue')},

  {path: '/manage/traffic', component: httpVueLoader('/vue/manage/traffic.vue')},
  {path: '/manage/common', component: httpVueLoader('/vue/manage/common.vue')},
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
    const item = {
      date: '2020-04-01',
      name: '全',
      address: '贵州省六盘水市钟山区'
    };
    return {
      tableData: Array(20).fill(item),
      menus: [
        {
          id: '1',
          name: '感知妥乐',
          icon: 'el-icon-message',
          children: [
            {
              id: '1-1',
              name: '美景图库',
              icon: 'el-icon-message',
              url: '/manage/pictures'
            },
            {
              id: '1-2',
              name: '精彩视频',
              icon: '',
              url: '/manage/video'
            },
            {
              id: '1-3',
              name: '旅游攻略',
              icon: '',
              url: '/manage/strategy'
            },
            {
              id: '1-4',
              name: '文明旅游',
              icon: '',
              url: '/manage/civilization'
            },
          ]
        },
        {
          id: '2',
          name: '妥乐资讯',
          icon: 'el-icon-message',
          children: [
            {
              id: '2-1',
              name: '最新动态',
              icon: 'el-icon-message',
              url: '/manage/dynamic'
            },
            {
              id: '2-2',
              name: '旅游资讯',
              icon: '',
              url: '/manage/tourism'
            },
            {
              id: '2-3',
              name: '活动资讯',
              icon: '',
              url: '/manage/active'
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
          name: '在线预约',
          icon: 'el-icon-message',
          children: [
            {
              id: '3-1',
              name: '门票预定',
              icon: 'el-icon-message',
              url: '/manage/tickets'
            },
            {
              id: '3-2',
              name: '住宿',
              icon: '',
              url: '/manage/hotel'
            },
            {
              id: '3-3',
              name: '餐饮',
              icon: '',
              url: '/manage/food'
            },
            {
              id: '3-4',
              name: '特产',
              icon: '',
              url: '/manage/specialty'
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
        // {
        //   id: '5',
        //   name: '联系我们',
        //   icon: 'el-icon-message',
        //   children: [
        //     {
        //       id: '5-1',
        //       name: '景区直通车',
        //       icon: 'el-icon-message',
        //       url: '/manage/traffic'
        //     },
        //     {
        //       id: '5-2',
        //       name: '旅游常识',
        //       icon: '',
        //       url: '/manage/common'
        //     },
        //   ]
        // },
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
