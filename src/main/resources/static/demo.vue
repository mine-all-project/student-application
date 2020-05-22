<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%" :height="400">
			<el-table-column prop="name" label="设备名" width="220"></el-table-column>
			<el-table-column prop="title" label="预约次数" width="180"></el-table-column>
			<el-table-column prop="content" label="使用次数" :show-overflow-tooltip="true"></el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
					<el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-drawer :visible.sync="drawer.show" :wrapperClosable="false" size="50%">
			<div class="demo-drawer__content">
				<el-form v-model="form">
					<el-form-item label="设备名称" :label-width="formLabelWidth">
						<el-input v-model="form.name" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item label="运行时长" :label-width="formLabelWidth">
						<el-input v-model="form.time" autocomplete="off" placeholder="单位(分钟)"></el-input>
					</el-form-item>
					<el-form-item label="注意事项" :label-width="formLabelWidth">
						<el-input type="textarea" :rows="4" v-model="form.content"></el-input>
					</el-form-item>
					<el-form-item :label-width="formLabelWidth">
						<el-button size="mini" @click="saveForm" type="primary">确定</el-button>
						<el-button size="mini" @click="drawerClose">取 消</el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-drawer>

		<div style="margin-top: 10px">
			<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
		</div>
	</el-row>

</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: [],
                form: {
                    id: '',
                    keyWords: '',
                    title: '',
                    content: '',
                },
                drawer: {
                    show: false,
                },
                formLabelWidth: '80px',
                keyWords: 'demo2',
            };
        },
        mounted() {
            this.getTableDataList(this.keyWord)
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    axios.delete(`/api/removePapersById/${id}`).then(response => {
                        _this.getTableDataList(this.keyWords);
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getTableDataList(_this.keyWords);
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getTableDataList(keyWord) {
                const _this = this;
                axios.get(`/api/getPapersByKeyWords/${keyWord}`).then(response => {
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
            drawerOpen(scope) {
                this.$nextTick(() => {
                    this.getDataById(scope ? scope.row.id : ' ');
                    this.drawer.show = true;
                })
            },
            getDataById(id) {
                const _this = this;
                axios.get(`/api/getMachinesById?id=${id}`).then(response => {
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
            },
            saveForm() {
                const _this = this;
                axios.post(`/api/savePapers`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    _this.drawer.show = false;
                    _this.getTableDataList()
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            drawerClose() {
                this.drawer.show = false;
                this.getTableDataList();
            },
        }
    }
</script>

<style scoped>
</style>
