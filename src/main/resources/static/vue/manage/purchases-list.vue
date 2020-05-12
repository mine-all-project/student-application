<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column prop="name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="goods.number" label="产品批号" width="120"></el-table-column>
			<el-table-column prop="address" label="生产地址" width="130">
				<template slot-scope="scope">
					<el-popover trigger="hover" placement="top">
						<p>{{ scope.row.goods.address }}</p>
						<div slot="reference" class="name-wrapper">
							<el-tag size="medium">查看详细地址</el-tag>
						</div>
					</el-popover>
				</template>
			</el-table-column>
			<el-table-column prop="supply" label="供应商" width="130"></el-table-column>
			<el-table-column prop="counts" label="数量" width="100"></el-table-column>
			<el-table-column prop="status" label="状态" width="100">
				<template slot-scope="scope">
					<el-tag size="medium" type="success" v-if="scope.row.delFlag === 0">正常</el-tag>
					<el-tag size="medium" type="danger" v-else>已退货</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="danger" @click="statusRemove(scope)" size="mini" v-if="scope.row.delFlag === 0">退货</el-button>
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
            statusRemove(item) {
                this.$confirm('确定要退货吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    let id = item.row.id
                    axios.delete('/api/flagDelPurchasesById?id=' + id).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            this.$message.error('数据加载失败');
                            return;
                        }
                        this.$message.success(result.message);
                        _this.getTableDataList()
                    }).catch(function (error) {
                        console.log('请求出现错误:', error);
                    });
                })
            },
        }
    }
</script>

<style scoped>
</style>
