const app = new Vue({
    el: '#app',
    // router,
    components: {},
    data() {
        return {
            paperList: [{id:'1',title:'标题1',content:'内容1'}],
            audioFile: {},
            drawer: {
                show: false,
                loading: false,
                form: {
                    title: '',
                    filePath: '',
                },
            },
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
    methods: {
        readText(){

        },
        remove(scope) {
            const _this = this;
            const id = scope.row.id;
            _this.$confirm('确认删除？').then(e => {
                _this.drawer.loading = true;
                axios.delete(`/api/removeAudioFileById/${id}`).then(response => {
                    _this.getAudioFileList();
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        _this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功')
                }).catch(function (error) {
                    _this.getAudioFileList();
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
        drawerOpen(scope) {
            this.drawer.show = true;
            this.$nextTick(() => {
                this.getGoodsById(scope ? scope.row.id : undefined);
            })
        },
        getGoodsById(id) {
            const _this = this;
            if (id !== undefined) {
                axios.get(`/api/getGoodsById/${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.drawer.form = result.data;
                    _this.editor.txt.html(_this.drawer.form.url)
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }
        },
        drawerClose() {
            this.drawer.loading = false;
            this.drawer.show = false;
            this.$refs.drawer.closeDrawer();
            this.getAudioFileList();
        },
        saveDrawer() {
            const _this = this;
            _this.drawer.loading = true;
            _this.drawer.form.keyword = 'food';
            axios.post(`/api/saveGoodsInfo`, _this.drawer.form).then(response => {
                const result = response.data;
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('数据加载失败');
                    return
                }
                _this.content = result.data;
                _this.drawer.loading = false;
                _this.$message.success('操作成功');
                window.location.reload();
            }).catch(function (error) {
                // window.location.reload();
                console.log('请求出现错误:', error);
            });
        },
        uploadSuccess(result) {
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
                this.$message.error('上传失败');
                return
            }
            this.drawer.form.url = result.data.path;
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
