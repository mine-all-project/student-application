import Index from '@/views/manage/Index'
import subApply from '@/views/manage/sub-apply'
import tags from '@/views/manage/tags'

const manageRouter = {
    path: '/manage-index',
    component: Index,
    name: 'manage-index',
    meta: {title: '首页', icon: 'clipboard'},
    children: [
        {
            path: 'sub-apply',
            components: {innerView: subApply},
            name: 'sub-apply',
        },
        {
            path: 'tags',
            components: {innerView: tags},
            name: 'tags',
        },
    ]
}

export default manageRouter
