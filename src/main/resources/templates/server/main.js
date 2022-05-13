const app = new Vue({
    el: '#app',
    components: {},
    data() {
        return {
            isDebug: false,
            pageUrl: '/server/welcome.html',
            headers: headers,
            labelCol: {span: 5},
            wrapperCol: {span: 16},
            axios: axios,
            form: {
                updatePassword: {
                    oldPassword: '',
                    newPassword: '',
                    againPassword: '',
                },
                personSettings: {}
            },
            pagination: {total: 0, pageSize: 10},
            title: "毕业设计过程管理系统",
            rules: {
                name: [
                    {required: true, message: '请输入姓名', trigger: 'change'},
                ],
                age: [
                    {required: true, message: '请输入年龄', trigger: 'change'},
                ],
                gender: [
                    {required: true, message: '请选择性别', trigger: 'change'},
                ],
                mail: [
                    {required: true, message: '请输入邮箱', trigger: 'change'},
                ],
                oldPassword: [
                    {required: true, message: '请输入原密码', trigger: 'change'},
                ],
                newPassword: [
                    {required: true, message: '请输入新密码', trigger: 'change'},
                ],
                againPassword: [
                    {required: true, message: '请重复新密码', trigger: 'change'},
                ],
            },
            show: {
                updatePassword: false,
                personSettings: false,
                previewImg: false,
            },
            timer: null,
            menus: [],
            allMenus: [
                {
                    key: '1',
                    name: '课题管理',
                    icon: 'appstore',
                    url: '/server/subject-list.html',
                },
                {
                    key: '2',
                    name: '课题审核',
                    icon: 'appstore',
                    url: '/server/subject-check.html',
                },
                {
                    key: '3',
                    name: '选择课题',
                    icon: 'appstore',
                    url: '/server/subject-select.html',
                },
                {
                    key: '4',
                    name: '撰写论文',
                    icon: 'appstore',
                    url: '/server/subject-write.html',
                },
                {
                    key: '5',
                    name: '用户管理',
                    icon: 'appstore',
                    url: '/server/user-list.html',
                },
            ],
            managerMenus: [
                {
                    key: '5',
                    name: '用户管理',
                    icon: 'appstore',
                    url: '/server/user-list.html',
                },
            ],
            directorMenus: [
                {
                    key: '2',
                    name: '课题审核',
                    icon: 'appstore',
                    url: '/server/subject-check.html',
                },
            ],
            teacherMenus: [
                {
                    key: '1',
                    name: '课题管理',
                    icon: 'appstore',
                    url: '/server/subject-list.html',
                },
            ],
            studentMenus: [
                {
                    key: '3',
                    name: '选择课题',
                    icon: 'appstore',
                    url: '/server/subject-select.html',
                },
                {
                    key: '4',
                    name: '撰写论文',
                    icon: 'appstore',
                    url: '/server/subject-write.html',
                },
            ],
            welcome: true,
            userInfo: {},
            temp: {
                previewSrc: '',
                fileList: []
            }
        }

    },
    mounted() {
        this.menus = this.allMenus
        this.getUserInfo()
    },
    methods: {
        showPersonSettings() {
            this.show.personSettings = true
        },
        closePersonSettings() {
            this.show.personSettings = false
        },
        savePersonSettings() {
            const _this = this
            let data = {
                id: _this.userInfo.id,
                age: _this.form.personSettings.age,
                name: _this.form.personSettings.name,
                gender: _this.form.personSettings.gender,
                mail: _this.form.personSettings.mail,
                headImg: _this.temp.fileList.length > 0 ? _this.temp.fileList[0].response.data : _this.userInfo.headImg,
            }
            this.$refs.personSettingsForm.validate(valid => {
                if (valid) {
                    console.info('valid success-->', data, _this.temp.fileList);
                    axios.post('/api/user/updateUserInfo', data).then(response => {
                        const result = response.data
                        if (result.status !== 200) {
                            this.$message.error(result.message);
                            return;
                        }
                        _this.$message.success(result.message);
                        _this.closePersonSettings()
                    }).catch(function (error) {
                        console.error('出现错误:', error);
                    });
                } else {
                    console.log('验证失败');
                    return false;
                }
            })
        },

        showUpdatePassword() {
            this.show.updatePassword = true
        },
        closeUpdatePassword() {
            this.show.updatePassword = false
            this.$refs.updatePasswordForm.resetFields();
        },
        saveUpdatePassword() {
            const _this = this
            _this.$refs.updatePasswordForm.validate(valid => {
                let data = {
                    id: _this.userInfo.id,
                    oldPassword: _this.form.updatePassword.oldPassword,
                    newPassword: _this.form.updatePassword.newPassword,
                    againPassword: _this.form.updatePassword.againPassword,
                }
                if (valid) {
                    console.info('valid success-->', data);
                    if (data.newPassword !== data.againPassword) {
                        _this.$message.error('两次密码不一致');
                        return
                    }
                    axios.post('/api/user/updatePassword', data).then(response => {
                        const result = response.data
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
                            return;
                        }
                        _this.$message.success(result.message);
                        _this.closeUpdatePassword()
                    }).catch(function (error) {
                        console.error('出现错误:', error);
                    });
                } else {
                    console.log('验证失败');
                    return false;
                }
            })
        },

        clickMenusTitle(e) {
            console.log('clickMenusTitle', e);
        },
        clickMenusItem(url) {
            console.log('clickMenusItem', url);
            this.pageUrl = url
            // window.location.href = url
        },
        getUserInfo() {
            this.axios.get('/api/getUserInfo').then(response => {
                const result = response.data
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    return;
                }
                if (result.data !== null) {
                    this.userInfo = result.data;
                    this.initUserMenus()
                }
            }).catch(function (error) {
                console.error('出现错误:', error);
            });
        },
        initUserMenus() {
            let rule = this.userInfo.role
            if (rule === 0) {
                this.menus = this.managerMenus
            } else if (rule === 1) {
                this.menus = this.directorMenus
            } else if (rule === 2) {
                this.menus = this.teacherMenus
            } else if (rule === 3) {
                this.menus = this.studentMenus
            }
            if (this.isDebug) {
                this.menus = this.allMenus
            }
        },

        logout() {
            sessionStorage.clear()
            window.location.href = '/server/login'
        },
        closePreview() {
            this.show.previewImg = false
        },
        async showPreview(file) {
            if (file.response) {
                this.temp.previewSrc = file.response.data.virtualPath
            } else {
                this.temp.previewSrc = file.url
            }
            this.show.previewImg = true
        },
        changeUploadFile(info) {
            this.temp.fileList = info.fileList
            if (info.file.status === 'done') {
                this.$message.success(`${info.file.name} 上传成功`);
            } else if (info.file.status === 'error') {
                this.$message.error(`${info.file.name} 上传失败`);
            }
        },
        removeUploadFile(file) {
            this.temp.fileList = []
        },
    }
})
