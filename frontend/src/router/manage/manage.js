import Index from '@/views/manage/Index'
import SubApply from '@/views/manage/sub-apply'
import Tags from '@/views/manage/tags'
import MineSubject from '@/views/manage/mine-subject'
import MinePull from '@/views/manage/mine-pull'
import RequestPullList from '@/views/manage/request-pull-list'

const manageRouter = {
    path: '/manage-index',
    component: Index,
    name: 'manage-index',
    meta: {title: '首页', icon: 'clipboard'},
    children: [
        {
            path: 'sub-apply',
            components: {innerView: SubApply},
            name: 'sub-apply',
        },
        {
            path: 'tags',
            components: {innerView: Tags},
            name: 'tags',
        },
        {
            path: 'mine-subject',
            components: {innerView: MineSubject},
            name: 'mine-subject',
        },
        {
            path: 'mine-pull',
            components: {innerView: MinePull},
            name: 'mine-pull',
        },
        {
            path: 'request-pull-list',
            components: {innerView: RequestPullList},
            name: 'request-pull-list',
        },
    ]
}

export default manageRouter
