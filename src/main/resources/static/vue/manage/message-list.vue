<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%">
			<el-table-column prop="createTime" label="序号"></el-table-column>
			<el-table-column prop="title" label="名称"></el-table-column>
			<el-table-column prop="content" label="联系电话" :show-overflow-tooltip="true"></el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
					<el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
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
            };
        },
        mounted() {
            this.getTableDataList(this.keyWords)
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
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
            getTableDataList(keyWords) {
                const _this = this;
                axios.get(`/api/getPapersByKeyWords/${keyWords}`).then(response => {
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
                    this.getPaperById(scope ? scope.row.id : ' ');
                    this.drawer.show = true;
                })
            },
            getPaperById(id) {
                const _this = this;
                axios.get(`/api/getPapersById?id=${id}`).then(response => {
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
            savePaper() {
                const _this = this;
                _this.drawer.loading = true;
                _this.form.keyWords = this.keyWords;
                axios.post(`/api/savePapers`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    _this.drawer.loading = false;
                    _this.drawer.show = false;
                    _this.getTableDataList(this.keyWords)
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            drawerClose() {
                this.drawer.loading = false;
                this.drawer.show = false;
                this.getTableDataList(this.keyWords);
            },
        }
    }
</script>

<style scoped>
	.table-row-hidden {
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.drawer-footer {
		margin-left: 10px;
	}
</style>
