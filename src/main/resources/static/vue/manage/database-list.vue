<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%">
			<el-table-column prop="createTime" label="备份时间"></el-table-column>
			<el-table-column prop="filePath" label="备份文件路径"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="danger" @click="rollback(scope)" size="mini">还原</el-button>
					<el-button type="primary" @click="remove(scope)" size="mini" v-if="!scope.row.admin">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-button type="primary" @click="add()" size="mini">添加</el-button>
	</el-row>
</template>

<script>
    module.exports = {
        data() {
            return {
                formLabelWidth: '80px',
                tableData: [],
                form: {
                    id: '',
                    username: '',
                    name: '',
                    mail: '',
                    phone: '',
                },
                drawer: {
                    show: false,
                },
            };
        },
        mounted() {
            this.getTableDataList()
        },
        methods: {
            rollback(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认要还原这个备份吗，此时间点后的数据数据将会被清空且无法恢复？', '警告').then(e => {
                    axios.delete(`/manage/rollbackDatabaseBakById?id=${id}`).then(response => {
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
            add() {
                const _this = this
                _this.$confirm('确定要执行数据库备份吗', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(() => {
                    axios.post(`/manage/addDatabaseBak`).then(response => {
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
                    axios.delete(`/manage/delDatabaseBakById?id=${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
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
                axios.get('/manage/getDatabaseBakList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.tableData = result.data;
                }).catch(function (error) {
                    console.error('请求出现错误:', error);
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
