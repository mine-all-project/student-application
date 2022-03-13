const app = new Vue({
    el: '#app',
    // router,
    components: {},
    data() {
        return {
            form: {title: '', fileInfo: {}, content: ''},
            show: {drawer: false, loading: false},
            paperList: [{id: '1', title: '标题1', content: '内容1'}],
            audioFile: {},
            formLabelWidth: '80px',
            timer: null,
            menus: [
                {
                    id: '0',
                    name: '文章列表',
                    icon: '',
                    url: '/manage/list.html'
                },
            ],
            welcome: true
        }
    },
    mounted() {
        this.getPaperList()
    },
    methods: {
        readText() {

        },
        openReadWindow() {
        },
        remove(scope) {
            const _this = this;
            const id = scope.row.id;
            _this.$confirm('确认删除？').then(e => {
                _this.show.loading = true;
                axios.delete(`/api/paper/del/${id}`).then(response => {
                    _this.getPaperList();
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        _this.show.loading = false;
                        _this.$message.error('数据加载失败');
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
            axios.get('/api/paper/list').then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
                    return;
                }
                _this.paperList = result.data;
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
                axios.get(`/api/paper/id/${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.form = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }
        },
        closeEdit() {
            this.show.loading = false;
            this.show.drawer = false;
        },
        savePaper() {
            const _this = this;
            _this.show.loading = true;
            axios.post(`/api/paper/save`, _this.form).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
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
                this.$message.error('上传失败');
                return
            }
            axios.get(`/api/paper/content/${result.data.id}`).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
                    return
                }
                this.form.content = result.data
            }).catch(function (error) {
                console.log('请求出现错误:', error);
            });
            this.form.fileInfo = result.data;
            this.$message.success('上传成功');
        },
        logout() {
            window.location.href = '/logOut'
        },
        clickMenu(url) {
            window.location.href = url
        }
    }
})
