const app = new Vue({
    el: '#app',
    components: {},
    data() {
        return {
            pageUrl:'/server/demo.html',
            labelCol: {span: 5},
            wrapperCol: {span: 16},
            $http: axios,
            axios: axios,
            form: {
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
            show: {changePassword: false, loading: false, read: false, play: false, voicing: false, parseText: false},
            timer: null,
            menus: [],
            allMenus: [
                {
                    key: '1',
                    name: '用户管理',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '11',
                            name: '用户列表',
                            icon: 'appstore',
                            url: '/server/user-list',
                        },
                    ]
                },
                {
                    key: '2',
                    name: '校园咨询',
                    icon: 'appstore',
                    url: '',
                },
                {
                    key: '3',
                    name: '校园动态',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '31',
                            name: '二手交易平台',
                            icon: 'appstore',
                            url: '/server/trading.html',
                        },
                        {
                            key: '32',
                            name: '失物招领',
                            icon: 'appstore',
                            url: '/server/lost-items.html',
                        },
                        {
                            key: '33',
                            name: '拼车',
                            icon: 'appstore',
                            url: '/server/car-pooling.html',
                        },
                        {
                            key: '34',
                            name: '就业速递',
                            icon: 'appstore',
                            url: '/server/employment.html',
                        },
                        {
                            key: '35',
                            name: '其它',
                            icon: 'appstore',
                            url: '/server/other.html',
                        },
                    ]
                },
                {
                    key: '4',
                    name: '数据审核',
                    icon: 'appstore',
                    url: '',
                    children: [
                        {
                            key: '41',
                            name: '二手交易',
                            icon: 'appstore',
                            url: '/server/check-trading.html',
                        },
                        {
                            key: '42',
                            name: '失物招领',
                            icon: 'appstore',
                            url: '/server/check-lost-items.html',
                        },
                        {
                            key: '43',
                            name: '拼车',
                            icon: 'appstore',
                            url: '/server/check-car-pooling.html',
                        },
                        {
                            key: '44',
                            name: '就业速递',
                            icon: 'appstore',
                            url: '/server/check-employment.html',
                        },
                        {
                            key: '45',
                            name: '其它',
                            icon: 'appstore',
                            url: '/server/check-other.html',
                        },
                    ]
                },
            ],
            sysMenus: [],
            userMenus: [ ],
            managerMenus: [ ],
            welcome: true,
            userInfo: {},

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
            this.pageUrl = url
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
