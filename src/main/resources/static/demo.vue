<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column prop="name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="number" label="产品批号" width="120"></el-table-column>
			<el-table-column prop="address" label="生产地址" width="130">
				<template slot-scope="scope">
					<el-popover trigger="hover" placement="top">
						<p>{{ scope.row.address }}</p>
						<div slot="reference" class="name-wrapper">
							<el-tag size="medium">查看详细地址</el-tag>
						</div>
					</el-popover>
				</template>
			</el-table-column>
			<el-table-column prop="supply" label="供应商" width="130"></el-table-column>
			<el-table-column prop="counts" label="数量" width="130"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="primary" @click="edit(scope)" size="mini">编辑</el-button>
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: []
            };
        },
        activated() {
            this.getTableDataList()
        },
        mounted() {

        },
        methods: {
            edit(row) {
                let id = row.row.id
                this.$router.push({path: '/purchases-add', query: {id: id, isEdit: true}})
            },
            filterByName(value, row, column) {
                console.log('筛选-->', value, row, column)
                return true
            },
            getTableDataList() {
                const _this = this;
                axios.get('/api/getPurchasesList').then(response => {
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
        }
    }
</script>

<style scoped>
</style>
