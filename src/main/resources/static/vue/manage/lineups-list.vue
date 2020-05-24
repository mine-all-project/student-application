<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%" :height="400">
			<el-table-column prop="createTime" label="预约时间"></el-table-column>
			<el-table-column prop="startTime" label="开始时间"></el-table-column>
			<el-table-column prop="endTime" label="结束时间"></el-table-column>
			<el-table-column prop="user.name" label="预约人"></el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag size="small" v-if="scope.row.status === 0">未开始</el-tag>
					<el-tag size="small" v-if="scope.row.status === 1">使用中</el-tag>
					<el-tag size="small" v-if="scope.row.status === 2">已结束</el-tag>
					<el-tag size="small" v-if="scope.row.status === 4">已取消</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="primary" @click="startUse(scope)" size="mini" v-if="scope.row.status === 0">开始</el-button>
					<el-button type="danger" @click="remove(scope)" size="mini" v-if="scope.row.status === 0">撤销</el-button>
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
            this.getTableDataList()
        },
        methods: {
            startUse(scope) {

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
                axios.get('/api/getLineUpsListByUser').then(response => {
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
