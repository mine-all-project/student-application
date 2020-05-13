<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column fixed prop="name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="number" label="数量" width="120"></el-table-column>
			<el-table-column prop="address" label="生产地址" width="130" :show-overflow-tooltip="true"></el-table-column>
			<el-table-column prop="counts" label="库存" width="120"></el-table-column>
			<el-table-column prop="producedTime" label="生产日期" width="120"></el-table-column>
			<el-table-column prop="shelLife" label="有效期至" width="120"></el-table-column>
			<el-table-column label="操作" width="180">
				<template slot-scope="scope">
					<el-button type="warning" @click="sendMessages(scope,0)" size="mini">缺货</el-button>
					<el-button type="danger" @click="sendMessages(scope,1)" size="mini">报损</el-button>
				</template>
			</el-table-column>
		</el-table>

		<el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer" size="70%">
			<div class="demo-drawer__content">
				<el-form v-model="form">
					<el-form-item label="药品" :label-width="formLabelWidth">
						<el-select v-model="form.purchasesId" size="small" style="width: 50%">
							<el-option v-for="item in selectData" :key="item.id" :label="item.name" :value="item.id" size="small">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="数量" :label-width="formLabelWidth">
						<el-input v-model="form.counts" type="number" size="small" style="width: 50%"></el-input>
					</el-form-item>
					<el-form-item label="正文" :label-width="formLabelWidth">
						<div id="editor"></div>
					</el-form-item>
				</el-form>
				<div class="drawer-footer" :label-width="formLabelWidth">
					<el-button type="primary" @click="savePaper" :loading="drawer.loading">
						{{ drawer.loading ? '提交中 ...' : '确定'}}
					</el-button>
					<el-button @click="drawerClose">取 消</el-button>
				</div>
			</div>
		</el-drawer>
		<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                formLabelWidth: '80px',
                drawer: {
                    show: false,
                    loading: false,
                },
                tableData: [],
                selectData: [],
                form: {
                    id: '',
                    keyWords: '',
                    title: '',
                    content: '',
                }
            };
        },
        activated() {
            this.getTableDataList()
        },
        mounted() {
        },
        methods: {
            drawerOpen(scope) {
                this.drawer.show = true;
                this.$nextTick(() => {
                    this.editor = new window.wangEditor('#editor');
                    this.editor.customConfig.uploadImgShowBase64 = true;
                    this.editor.customConfig.showLinkImg = false;
                    this.editor.customConfig.pasteIgnoreImg = true;
                    this.editor.customConfig.menus = this.wangEditorOptions;
                    this.editor.create();
                    // this.getSelectData(scope ? scope.row.id : '');
                    this.getSelectData();
                })
            },
            drawerClose() {
                this.drawer.loading = false;
                this.drawer.show = false;
                this.getTableDataList(this.keyWords);
            },
            savePaper() {
                const _this = this;
                _this.form.content = _this.editor.txt.html();
                _this.loading = true;
                _this.form.keyWords = this.keyWords;
                axios.post(`/api/savePapers`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.content = result.data;
                    _this.$message.success('操作成功');
                    _this.drawer.loading = false;
                    _this.drawer.show = false;
                    _this.getTableDataList(this.keyWords);
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            filterByName(value, row, column) {
                console.log('筛选-->', value, row, column)
                return true
            },
            getTableDataList() {
                const _this = this;
                axios.get('/api/getSalesList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.tableData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            getSelectData() {
                const _this = this;
                axios.get('/api/getGoodsListByFlag').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.selectData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            sendMessages(item, type) {
                console.log(item.row, type)
                this.$confirm(`确定要发送${type === 0 ? '缺货' : '报损'}消息吗?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    _this.messageForm.objectId = item.row.id
                    _this.messageForm.type = type
                    axios.post('/api/saveMessages', _this.messageForm).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            this.$message.error('数据加载失败');
                            return;
                        }
                        _this.$message.success(result.message);
                    }).catch(function (error) {
                        console.log('请求出现错误:', error);
                    });
                })
            }
        }
    }
</script>

<style scoped>
</style>
