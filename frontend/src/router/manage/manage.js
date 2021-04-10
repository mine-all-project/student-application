import Index from '@/views/manage/Index'
import SubApply from '@/views/manage/sub-apply'
import Tags from '@/views/manage/tags'
import MineSubject from '@/views/manage/mine-subject'
import MineJoin from '@/views/manage/mine-join'
import MinePull from '@/views/manage/mine-pull'
import RequestPullList from '@/views/manage/request-pull-list'
import DataStatistical from '@/views/manage/data-statistical'
import SubjectDiscuss from '@/views/manage/subject-discuss'
import PaperList from '@/views/manage/paper-list'
import PaperList1 from '@/views/manage/paper-list1'

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
            path: 'mine-join',
            components: {innerView: MineJoin},
            name: 'mine-join',
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
        {
            path: 'data-statistical',
            components: {innerView: DataStatistical},
            name: 'data-statistical',
        },
        {
            path: 'subject-discuss',
            components: {innerView: SubjectDiscuss},
            name: 'subject-discuss',
        },
        {
            path: 'paper-list',
            components: {innerView: PaperList},
            name: 'paper-list',
        },
        {
            path: 'paper-list1',
            components: {innerView: PaperList1},
            name: 'paper-list1',
        },
    ]
}

export default manageRouter
