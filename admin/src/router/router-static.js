import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'

Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import forum from '@/views/modules/forum/list'
import news from '@/views/modules/news/list'
import huodongshenqing from '@/views/modules/huodongshenqing/list'
import discussgongsixinxi from '@/views/modules/discussgongsixinxi/list'
import gongsixinxi from '@/views/modules/gongsixinxi/list'
import chat from '@/views/modules/chat/list'
import huiyuan from '@/views/modules/huiyuan/list'
import huodongxinxi from '@/views/modules/huodongxinxi/list'
import discusshuodongxinxi from '@/views/modules/discusshuodongxinxi/list'
import storeup from '@/views/modules/storeup/list'
import config from '@/views/modules/config/list'
import baoxiu from '@/views/modules/baoxiu/list'
import shijia from '@/views/modules/shijia/list'
import jiuyuan from '@/views/modules/jiuyuan/list'
import jingxiaodian from '@/views/modules/jingxiaodian/list'


//2.配置路由   注意：名字
const routes = [
    {
        path: '/index',
        name: '首页',
        component: Index,
        children: [
            {
                // 这里不设置值，是把main作为默认页面
                path: '/',
                name: '首页',
                component: Home,
                meta: {icon: '', title: 'center'}
            },
            {
                path: '/updatePassword',
                name: '修改密码',
                component: UpdatePassword,
                meta: {icon: '', title: 'updatePassword'}
            },
            {
                path: '/pay',
                name: '支付',
                component: pay,
                meta: {icon: '', title: 'pay'}
            },
            {
                path: '/center',
                name: '个人信息',
                component: center,
                meta: {icon: '', title: 'center'}
            },
            {
                path: '/forum',
                name: '论坛管理',
                component: forum
            },
            {
                path: '/news',
                name: '新闻资讯',
                component: news
            },
            {
                path: '/huodongshenqing',
                name: '活动申请',
                component: huodongshenqing
            },
            {
                path: '/discussgongsixinxi',
                name: '公司信息评论',
                component: discussgongsixinxi
            },
            {
                path: '/gongsixinxi',
                name: '公司信息',
                component: gongsixinxi
            },
            {
                path: '/chat',
                name: '在线客服',
                component: chat
            },
            {
                path: '/huiyuan',
                name: '会员',
                component: huiyuan
            },
            {
                path: '/huodongxinxi',
                name: '活动信息',
                component: huodongxinxi
            },
            {
                path: '/discusshuodongxinxi',
                name: '活动信息评论',
                component: discusshuodongxinxi
            },
            {
                path: '/storeup',
                name: '我的收藏管理',
                component: storeup
            },
            {
                path: '/config',
                name: '轮播图管理',
                component: config
            },
            {
                path: '/baoxiu',
                name: '保修',
                component: baoxiu
            },
            {
                path: '/shijia',
                name: '试驾',
                component: shijia
            },
            {
                path: '/jiuyuan',
                name: '救援',
                component: jiuyuan
            },
            {
                path: '/jingxiaodian',
                name: '经销店管理',
                component: jingxiaodian
            },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {icon: '', title: 'login'}
    },
    {
        path: '/register',
        name: 'register',
        component: register,
        meta: {icon: '', title: 'register'}
    },
    {
        path: '/',
        name: '首页',
        redirect: '/index'
    }, /*默认跳转路由*/
    {
        path: '*',
        component: NotFound
    }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
    mode: 'hash',
    /*hash模式改为history*/
    routes // （缩写）相当于 routes: routes
})

export default router;
