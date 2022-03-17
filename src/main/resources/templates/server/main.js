let instance = axios.create({timeout: 3000})
instance.interceptors.request.use(
    config => {
        config.headers['crabapples-token'] = sessionStorage.getItem('token');
        if (/get/i.test(config.method)) {
            config.params = config.params || {}
            config.params.temp = Date.parse(new Date()) / 1000
        }
        return config;
    },
    error => Promise.error(error)
);
instance.interceptors.response.use(
    response => {
        console.debug('api返回的数据-->', response)
        if (response.status === 200) {
            if (response.data.status === 401) {
                setTimeout(() => {
                    // window.location.href = '/server/login'
                }, 3000)

            }
        }
        return response
    },
    error => {
        return Promise.reject(error)
    }
)
const app = new Vue({
    el: '#app',
    components: {},
    data() {
        return {
            labelCol: {span: 5},
            wrapperCol: {span: 16},
            $http: instance,
            axios: instance,
            form: {
                paper: {title: '', fileInfo: {}, content: ''},
                reader: {paper: {}, pitchRate: 100, speechRate: 100, voice: ''},
                voices: [],
                oldPassword: '',
                password: '',
                rePassword: '',
                tagsList: [],
            },
            pagination: {total: 0, pageSize: 10},
            title: "",
            rules: {
                oldPassword: [
                    {required: true, message: '请输入原密码', trigger: 'change'},
                    {min: 8, max: 16, message: '长度为8-16位', trigger: 'change'},
                ],
                password: [
                    {required: true, message: '请输入新密码', trigger: 'change'},
                    {min: 8, max: 16, message: '长度为8-16位', trigger: 'change'},
                ],
                rePassword: [
                    {required: true, message: '请重复新密码', trigger: 'change'},
                    {min: 8, max: 16, message: '长度为8-16位', trigger: 'change'},
                ],
            },

            voiceOptions: [
                {
                    label: '通用', children: [
                        {value: 'aiqi', label: '温柔女声', name: '艾琪'},
                        {value: 'aicheng', label: '标准男声', name: '艾城'},
                        {value: 'aijia', label: '标准女声', name: '艾佳'},
                        {value: 'siqi', label: '温柔女声', name: '思琪'},
                        {value: 'sijia', label: '标准女声', name: '思佳'},
                        {value: 'mashu', label: '儿童剧男声', name: '马树'},
                        {value: 'yuer', label: '儿童剧女声', name: '悦儿'},
                        {value: 'ruoxi', label: '温柔女声', name: '若兮'},
                        {value: 'aida', label: '标准男声', name: '艾达'},
                        {value: 'sicheng', label: '标准男声', name: '思诚'},
                        {value: 'ninger', label: '标准女声', name: '宁儿'},
                        {value: 'xiaoyun', label: '标准女声', name: '小云'},
                        {value: 'xiaogang', label: '标准男声', name: '小刚'},
                        {value: 'ruilin', label: '标准女声', name: '瑞琳'},
                    ]
                },
                {
                    label: '客服', children: [
                        {value: 'aixia', label: '亲和女声', name: '艾夏'},
                        {value: 'aiyue', label: '温柔女声', name: '艾悦'},
                        {value: 'aiya', label: '严厉女声', name: '艾雅'},
                        {value: 'aijing', label: '严厉女声', name: '艾婧'},
                        {value: 'aimei', label: '甜美女声', name: '艾美'},
                        {value: 'siyue', label: '温柔女声', name: '思悦'},
                        {value: 'Aina', label: '浙普女声', name: '艾娜'},
                        {value: 'aishuo', label: '自然男声', name: '艾硕'},
                        {value: 'aiyu', label: '自然女声', name: '艾雨'},
                        {value: 'xiaomei', label: '甜美女声', name: '小美'},
                        {value: 'yina', label: '浙普女声', name: '伊娜'},
                        {value: 'sijing', label: '严厉女声', name: '思婧'},
                    ]
                },
                {
                    label: '超高清', children: [
                        {value: 'zhitian', label: '甜美女声', name: '知甜'},
                        {value: 'zhiqing', label: '台湾话女声', name: '知青'},
                    ]
                },
                {
                    label: '直播', children: [
                        {value: 'laomei', label: '吆喝女声', name: '老妹'},
                        {value: 'laotie', label: '东北老铁', name: '老铁'},
                        {value: 'xiaoxian', label: '亲切女声', name: '小仙'},
                        {value: 'guijie', label: '亲切女声', name: '柜姐'},
                        {value: 'stella', label: '知性女声', name: 'Stella'},
                        {value: 'maoxiaomei', label: '活力女声', name: '猫小美'},
                        {value: 'qiaowei', label: '卖场广播', name: '巧薇'},
                        {value: 'ailun', label: '悬疑解说', name: '艾伦'},
                        {value: 'aifei', label: '激昂解说', name: '艾飞'},
                        {value: 'yaqun', label: '卖场广播', name: '亚群'},
                        {value: 'stanley', label: '沉稳男声', name: 'Stanley'},
                        {value: 'kenny', label: '温暖男声', name: 'Kenny'},
                        {value: 'rosa', label: '自然女声', name: 'Rosa'},
                    ]
                },
                {
                    label: '童声', children: [
                        {value: 'aitong', label: '儿童音', name: '艾彤'},
                        {value: 'aiwei', label: '萝莉女声', name: '艾薇'},
                        {value: 'jielidou', label: '治愈童声', name: '杰力豆'},
                        {value: 'xiaobei', label: '萝莉女声', name: '小北'},
                        {value: 'sitong', label: '儿童音', name: '思彤'},
                        {value: 'aibao', label: '萝莉女声', name: '艾宝'},
                    ]
                },
                {
                    label: '英文', children: [
                        {value: 'ava', label: '美式女声', name: 'ava'},
                        {value: 'Luca', label: '英音男声', name: 'Luca'},
                        {value: 'Luna', label: '英音女声', name: 'Luna'},
                        {value: 'Emily', label: '英音女声', name: 'Emily'},
                        {value: 'Eric', label: '英音男声', name: 'Eric'},
                        {value: 'annie', label: '美语女声', name: 'Annie'},
                        {value: 'Andy', label: '美音男声', name: 'Andy'},
                        {value: 'William', label: '英音男声', name: 'William'},
                        {value: 'Abby', label: '美音女声', name: 'Abby'},
                        {value: 'Lydia', label: '英中双语', name: 'Lydia'},
                        {value: 'Olivia', label: '英音女声', name: 'Olivia'},
                        {value: 'Wendy', label: '英音女声', name: 'Wendy'},
                        {value: 'Harry', label: '英音男声', name: 'Harry'},
                    ]
                },
                {
                    label: '方言', children: [
                        {value: 'jiajia', label: '粤语女声', name: '佳佳'},
                        {value: 'dahu', label: '东北话男声', name: '大虎'},
                        {value: 'aikan', label: '天津话男声', name: '艾侃'},
                        {value: 'taozi', label: '粤语女声', name: '桃子'},
                        {value: 'qingqing', label: '台湾话女声', name: '青青'},
                        {value: 'cuijie', label: '东北话女声', name: '翠姐'},
                        {value: 'xiaoze', label: '湖南重口音', name: '小泽'},
                        {value: 'shanshan', label: '粤语女声', name: '姗姗'},
                    ]
                },
                {
                    label: '多语种', children: [
                        {value: 'tomoka', label: '日语女声', name: '智香'},
                        {value: 'tomoya', label: '日语男声', name: '智也'},
                        {value: 'indah', label: '印尼女声', name: 'Indah'},
                        {value: 'farah', label: '马来语女声', name: 'Farah'},
                        {value: 'tala', label: '菲律宾语女声', name: 'Tala'},
                    ]
                },
            ],
            show: {changePassword: false, loading: false, read: false, play: false, voicing: false, parseText: false},
            paperList: [{id: '1', title: '标题1', content: '内容1'}],
            paperListAll: [],
            readerSrc: '',
            formLabelWidth: '80px',
            timer: null,
            menus: [],
            allMenus: [
                {
                    key: '1',
                    name: '科研管理',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '11',
                            name: '项目申报',
                            icon: 'appstore',
                            url: '/manage-index/sub-apply',
                        },
                        {
                            key: '12',
                            name: '项目审核',
                            icon: 'appstore',
                            url: '/manage-index/audit-list',
                        },
                        {
                            key: '13',
                            name: '标签管理',
                            icon: 'appstore',
                            url: '/manage-index/tags',
                        },
                    ]
                },
                {
                    key: '2',
                    name: '数据共享',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '21',
                            name: '我的项目',
                            icon: 'appstore',
                            url: '/manage-index/mine-subject',
                        },
                        {
                            key: '22',
                            name: '我参与的',
                            icon: 'appstore',
                            url: '/manage-index/mine-join',
                        },
                        {
                            key: '23',
                            name: '共享项目',
                            icon: 'appstore',
                            url: '/manage-index/mine-pull',
                        },
                        {
                            key: '24',
                            name: '共享审核',
                            icon: 'appstore',
                            url: '/manage-index/request-pull-list',
                        },
                    ]
                },
                {
                    key: '3',
                    name: '科研分析',
                    icon: 'appstore',
                    children: [
                        {
                            key: '31',
                            name: '数据统计',
                            icon: 'appstore',
                            url: '/manage-index/data-statistical',
                        },
                        {
                            key: '32',
                            name: '工作评价',
                            icon: 'appstore',
                            url: '/manage-index/subject-discuss',
                        },
                    ]
                },
                {
                    key: '4',
                    name: '数据对接',
                    icon: 'appstore',
                    url: '/school-org/list',
                    children: [
                        // {
                        //   key: '41',
                        //   name: '信息发布',
                        //   icon: 'appstore',
                        //   url: '/manage-index/paper-list',
                        // },
                        {
                            key: '41-1',
                            name: '信息发布',
                            icon: 'appstore',
                            url: '/manage-index/paper-list1',
                        },
                        // {
                        //   key: '42',
                        //   name: '工作评价',
                        //   icon: 'appstore',
                        //   url: '/manage-index/subject-discuss',
                        // },
                    ]
                },
                {
                    key: '99',
                    name: '用户管理',
                    icon: 'appstore',
                    url: '/manage-index/user-list',
                },
            ],
            sysMenus: [
                {
                    key: '1',
                    name: '科研管理',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '12',
                            name: '项目审核',
                            icon: 'appstore',
                            url: '/manage-index/audit-list',
                        },
                        {
                            key: '13',
                            name: '标签管理',
                            icon: 'appstore',
                            url: '/manage-index/tags',
                        },
                    ]
                },
                {
                    key: '3',
                    name: '科研分析',
                    icon: 'appstore',
                    children: [
                        {
                            key: '31',
                            name: '数据统计',
                            icon: 'appstore',
                            url: '/manage-index/data-statistical',
                        },
                        {
                            key: '32',
                            name: '工作评价',
                            icon: 'appstore',
                            url: '/manage-index/subject-discuss',
                        },
                    ]
                },
                {
                    key: '4',
                    name: '数据对接',
                    icon: 'appstore',
                    url: '/school-org/list',
                    children: [
                        // {
                        //   key: '41',
                        //   name: '信息发布',
                        //   icon: 'appstore',
                        //   url: '/manage-index/paper-list',
                        // },
                        {
                            key: '41-1',
                            name: '信息发布',
                            icon: 'appstore',
                            url: '/manage-index/paper-list1',
                        },
                        // {
                        //   key: '42',
                        //   name: '工作评价',
                        //   icon: 'appstore',
                        //   url: '/manage-index/subject-discuss',
                        // },
                    ]
                },
                {
                    key: '99',
                    name: '用户管理',
                    icon: 'appstore',
                    url: '/manage-index/user-list',
                },
            ],
            userMenus: [
                {
                    key: '1',
                    name: '科研管理',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '11',
                            name: '项目申报',
                            icon: 'appstore',
                            url: '/manage-index/sub-apply',
                        },
                    ]
                },
                {
                    key: '2',
                    name: '数据共享',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '21',
                            name: '我的项目',
                            icon: 'appstore',
                            url: '/manage-index/mine-subject',
                        },
                        {
                            key: '22',
                            name: '我参与的',
                            icon: 'appstore',
                            url: '/manage-index/mine-join',
                        },
                        {
                            key: '23',
                            name: '共享项目',
                            icon: 'appstore',
                            url: '/manage-index/mine-pull',
                        },
                    ]
                },
                {
                    key: '3',
                    name: '科研分析',
                    icon: 'appstore',
                    children: [
                        {
                            key: '31',
                            name: '数据统计',
                            icon: 'appstore',
                            url: '/manage-index/data-statistical',
                        },
                        {
                            key: '32',
                            name: '工作评价',
                            icon: 'appstore',
                            url: '/manage-index/subject-discuss',
                        },
                    ]
                },
                {
                    key: '4',
                    name: '数据对接',
                    icon: 'appstore',
                    url: '/school-org/list',
                    children: [
                        {
                            key: '41-1',
                            name: '信息发布',
                            icon: 'appstore',
                            url: '/manage-index/paper-list1',
                        },
                    ]
                },
            ],
            managerMenus: [
                {
                    key: '1',
                    name: '科研管理',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '12',
                            name: '标签管理',
                            icon: 'appstore',
                            url: '/manage-index/tags',
                        },
                    ]
                },
                {
                    key: '2',
                    name: '数据共享',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '24',
                            name: '共享审核',
                            icon: 'appstore',
                            url: '/manage-index/request-pull-list',
                        },
                    ]
                },
                {
                    key: '3',
                    name: '科研分析',
                    icon: 'appstore',
                    children: [
                        {
                            key: '31',
                            name: '数据统计',
                            icon: 'appstore',
                            url: '/manage-index/data-statistical',
                        },
                        {
                            key: '32',
                            name: '工作评价',
                            icon: 'appstore',
                            url: '/manage-index/subject-discuss',
                        },
                    ]
                },
                {
                    key: '4',
                    name: '数据对接',
                    icon: 'appstore',
                    url: '/school-org/list',
                    children: [
                        {
                            key: '41-1',
                            name: '信息发布',
                            icon: 'appstore',
                            url: '/manage-index/paper-list1',
                        },
                    ]
                },
            ],
            welcome: true,
            userInfo: {},
            tagsOptions: [],

        }
    },
    mounted() {
        this.menus = this.allMenus
        this.getUserInfo()
    },
    methods: {
        showChangePassword() {
            this.show.changePassword = true
        },
        closeChangePassword() {
            this.show.changePassword = false
            // this.$refs.ruleForm.resetFields();
        },
        clickTitle(e) {
            console.log('clickTitle', e);
        },
        clickMenu(url) {
            console.log('clickMenu', url);
            // window.location.href = url
        },
        getUserInfo() {
            this.axios.get('/api/user/getUserInfo').then(result => {
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    return;
                }
                if (result.data !== null) {
                    this.userInfo = result.data;
                    this.changeMenus()
                }
            }).catch(function (error) {
                console.error('出现错误:', error);
            });
        },

        submitForm() {
            // this.$refs.ruleForm.validate(valid => {
            //     if (valid) {
            //         console.info('success', this.form);
            //         if (this.form.password !== this.form.rePassword) {
            //             this.$message.error('两次密码不一致');
            //             return
            //         }
            //         this.form.id = this.userInfo.id
            //         this.$http.post('/api/user/updatePassword', this.form).then(result => {
            //             if (result.status !== 200) {
            //                 this.$message.error(result.message);
            //                 return;
            //             }
            //             this.$message.success(result.message);
            //             this.closeModal()
            //         }).catch(function (error) {
            //             console.error('出现错误:', error);
            //         });
            //     } else {
            //         console.log('验证失败');
            //         return false;
            //     }
            // })
        },
        changeMenus() {
            // let rule = this.userInfo.role
            // if (rule === 0) {
            //     this.menus = this.sysMenus
            // } else if (rule === 1) {
            //     this.menus = this.managerMenus
            // } else if (rule === 2) {
            //     this.menus = this.userMenus
            // }
            // this.menus = this.allMenus
        },
        changeTags() {
            // this.getTagsList()
            // this.show.changeTags = true
            // console.log(this.userInfo)
            // this.form.tagsList = this.userInfo.tags.map(e => {
            //     return e.id
            // })
        },

        downloadFile(scope) {
            window.open(scope.row.fileInfo.virtualPath)
        },
        changePage(e) {
            this.paperList = this.paperListAll.slice((e - 1) * 10, this.pagination.pageSize * e);
        },
        openReadWindow(scope) {
            this.form.reader.paper = scope.row
            this.show.read = true
        },
        closeReadWindow() {
            this.form.reader.paper = {}
            document.getElementById('player').src = '';
            this.form.voices = []
            this.show.read = false
        },
        replay() {
            document.getElementById('player').play()
        },
        play(src) {
            this.show.voicing = false
            this.$nextTick(() => {
                let audio = document.getElementById('player');
                audio.src = src
                audio.play()
            })
        },
        getReadInfo(readInfo) {
            this.timer = setInterval(() => {
                this.axios.post(`/api/paper/reader/id/${readInfo.id}`,).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error(result.message);
                        return
                    }
                    if (result.data.status === 0) {
                        clearInterval(this.timer)
                        this.readerSrc = result.data.virtualPath
                        this.play(result.data.virtualPath)
                    }
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }, 3000)
        },
        createVoiceFile() {
            this.show.voicing = true
            this.form.reader.voice = this.form.voices[1]
            this.axios.post(`/api/paper/reader/create`, this.form.reader).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    this.show.voicing = false
                    return
                }
                this.getReadInfo(result.data)
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
        },
        remove(scope) {
            const _this = this;
            const id = scope.row.id;
            _this.$confirm('确认删除？').then(e => {
                _this.show.loading = true;
                _this.axios.delete(`/api/paper/del/${id}`).then(response => {
                    _this.getPaperList();
                    const result = response.data;
                    if (result.status !== 200) {
                        _this.show.loading = false;
                        _this.$message.error(e.message);
                        return
                    }
                    _this.show.loading = false;
                    _this.$message.success('操作成功')
                }).catch(function (error) {
                    _this.getPaperList();
                    console.log('请求出现错误:', error);
                });
            });
        },
        getPaperList() {
            const _this = this;
            this.axios.get('/api/paper/list').then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    _this.$message.error(result.message);
                    return;
                }
                _this.paperListAll = result.data
                _this.pagination.total = _this.paperListAll.length
                _this.paperList = _this.paperListAll.slice(0, _this.pagination.pageSize);
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
        },
        openPaperEdit(scope) {
            this.show.drawer = true;
            this.$nextTick(() => {
                this.getPaperById(scope ? scope.row.id : undefined);
            })
        },
        getPaperById(id) {
            const _this = this;
            if (id !== undefined) {
                _this.axios.get(`/api/paper/id/${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error(result.message);
                        return
                    }
                    _this.form.paper = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }
        },
        closeEdit() {
            this.$refs.uploadFile.clearFiles()
            this.show.loading = false;
            this.show.drawer = false;
            this.form.paper = {title: '', fileInfo: {}, content: ''}
        },
        savePaper() {
            const _this = this;
            _this.show.loading = true;
            _this.axios.post(`/api/paper/save`, _this.form.paper).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    _this.$message.error(result.message);
                    return
                }
                _this.show.loading = false;
                _this.$message.success('操作成功');
                _this.closeEdit()
                _this.getPaperList()
            }).catch(function (error) {
                window.location.reload();
                console.log('请求出现错误:', error);
            });
        },
        uploadSuccess(result) {
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
                this.$message.error(result.message);
                return
            }
            this.show.parseText = true
            this.axios.get(`/api/paper/content/${result.data.id}`).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    this.show.parseText = false
                    return
                }
                this.form.paper.content = result.data
                this.show.parseText = false
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
            this.form.paper.fileInfo = result.data;
            this.$message.success('上传成功');
        },
        logout() {
            window.location.href = '/system/logout'
        },
    }
})
