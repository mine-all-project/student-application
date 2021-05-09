import Vue from 'vue'
import VueRouter from 'vue-router'
import PortalIndex from '@/views/portal/Index.vue'
import ManagerIndex from '@/views/manage/Index.vue'
import Receive from '@/components/Receive.vue'
import RedPackage from '@/components/RedPackcge.vue'
import Demo from '@/components/Demo.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/demo',
        name: 'demo',
        component: Demo
    },
    {
        path: '/hello',
        name: 'hello',
        component: () => import('@/views/Hello.vue')
    },
    {
        path: '/',
        name: 'portal-index',
        component: PortalIndex
    },
    {
        path: '/manageIndex',
        name: 'manage-index',
        component: ManagerIndex
    },
    {
        path: '/manageLogin',
        name: 'manage-login',
        component: () => import('@/views/manage/Login.vue')
    },
    {
        path: '/red_package',
        name: 'red-package',
        component: RedPackage
    },
    {
        path: '/receive',
        name: 'receive',
        component: Receive
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
