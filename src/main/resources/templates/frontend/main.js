const app = new Vue({
    el: '#app',
    components: {},
    data() {
        return {
            labelCol: {span: 5},
            wrapperCol: {span: 16},
            image: {
                schoolLogo: '/images/xiaohui.png',
                schoolName: '/images/blackimg.png',
                moduleIcon: '/images/icon.svg',
                xiaoxun: '/images/xiaohui.png',
            },
            page: {
                schoolName: '学校名字',
                schoolNameEn: '学校英文名',
                xiaoxun: '校训',
                sysName: '系统名字',
            },
            pageUrl: '/frontend/demo.html',
            headers: headers,
            axios: axios,
            form: {
                updatePassword: {
                    oldPassword: '',
                    newPassword: '',
                    againPassword: '',
                },
                personSettings: {}
            },
            show: {
                updatePassword: false,
                personSettings: false,
                previewImg: false,
            },
            userInfo: {},
            temp: {
                previewSrc: '',
                fileList: []
            },
            activeIndex: 0,
            timer: null,
            bannerData: [
                {src: '/images/banner01.png'},
                {src: '/images/banner02.png'},
                {src: '/images/banner03.png'},
            ],
            interval: 2000,

            title:'二手交易',
            menus: [
                {key: '', name: '二手交易'},
                {key: '', name: '二手交易'},
                {key: '', name: '租赁'},
            ],
            dataList: [],
            titleText: '校园问答',
        }
    },
    mounted() {
        this.getUserInfo()
    },
    methods: {
        changeSecondType(e) {
            console.log(e.name)
        },
        changeOtherType(e) {
            console.log(e)
        },

        changeTab(e) {
            console.log(e)
            this.pageUrl = e
            window.location.href = e
        },
        onOver() {
            clearInterval(this.timer)
        },
        onOut() {
            this.startSlider()
        },
        onEnter(index) {
            this.activeIndex = index
            clearInterval(this.timer)
        },
        onLeave() {
            this.startSlider()
        },
        startSlider() {
            clearInterval(this.timer)
            if (this.bannerData.length > 1) {
                this.timer = setInterval(this.onMove, this.interval)
            }
        },
        onMove() {
            if (this.activeIndex === this.bannerData.length - 1) {
                this.activeIndex = 0
            } else {
                this.activeIndex++
            }
        },
        beforeDestroy() {
            clearInterval(this.timer)
            this.timer = null
        },
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
        getUserInfo() {
            this.axios.get('/api/user/getUserInfo').then(response => {
                const result = response.data
                if (result.status !== 200) {
                    this.$message.error(result.message);
                    return;
                }
                if (result.data !== null) {
                    this.userInfo = result.data;
                }
            }).catch(function (error) {
                console.error('出现错误:', error);
            });
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
