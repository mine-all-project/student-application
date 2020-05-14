<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%" row-key="id">
			<el-table-column prop="createTime" label="日期" width="250"></el-table-column>
			<el-table-column prop="userName" label="作者" width="180"></el-table-column>
			<el-table-column prop="houses" label="来源" width="180"></el-table-column>
			<el-table-column prop="content" label="内容" :show-overflow-tooltip="true"></el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
					<el-button type="primary" @click="edit(scope.row)" size="mini"
					           v-if="!scope.row.parent_id && !scope.row.children.length>0">回复
					</el-button>
				</template>
			</el-table-column>
		</el-table>
	</el-row>

</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: [],
                form: {
                    parent_id: '',
                    houses_id: '',
                    content: '',
                },
            };
        },
        mounted() {
            this.getTableDataList()
        },
        methods: {
            edit(scope) {
                const _this = this
                _this.$prompt('请输入内容', '回复', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({value}) => {
                    this.form = {
                        parent_id: scope.id,
                        houses_id: scope.houses_id,
                        content: value,
                    }
                    axios.post(`/api/saveLeaveMessage`, _this.form).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功');
                        _this.getTableDataList()
                    }).catch(function (error) {
                        window.location.reload();
                        console.log('请求出现错误:', error);
                    });
                })
            },
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    axios.delete(`/api/removeLeaveMessage/${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getTableDataList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getTableDataList() {
                const _this = this;
                axios.get(`/api/getLeaveMessage`).then(response => {
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
                this.form = {
                    parent_id: scope.id,
                    houses_id: scope.houses_id,
                    content: '',
                }
            },
            savePaper() {
                if (!(this.form.content)) {
                    this.$message.warning('请完整填写信息');
                    return
                }
                const _this = this;
                axios.post(`/api/saveLeaveMessage`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.content = result.data;
                    _this.$message.success('操作成功');
                    _this.getTableDataList();
                }).catch(function (error) {
                    // window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>

<style scoped>
	.drawer-footer {
		margin-left: 10px;
	}
</style>
