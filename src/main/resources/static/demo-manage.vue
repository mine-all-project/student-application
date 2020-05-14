<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%">
			<el-table-column prop="title" label="标题" width=""></el-table-column>
			<el-table-column prop="address" label="地址" width=""></el-table-column>
			<el-table-column prop="user.name" label="发布人" width=""></el-table-column>
			<el-table-column prop="price" label="金额" width=""></el-table-column>
			<el-table-column prop="note" label="备注" width=""></el-table-column>
			<el-table-column prop="sysUser.name" label="图片" width="">
				<template slot-scope="scope">
					<img :src="item" v-for="item in scope.row.imgs" style="height: 50px">
				</template>
			</el-table-column>
			<el-table-column prop="contracts.title" label="合同" width=""></el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="primary" @click="remove(scope)" size="mini">删除</el-button>
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
            this.getTableData()
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('删除后不可恢复,确认删除？').then(e => {
                    axios.delete(`/api/removeHousesById/${id}`).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
                            return
                        }
                        _this.$message.success('操作成功')
                        _this.getTableData();
                    }).catch(function (error) {
                        _this.getTableData();
                        console.error('请求出现错误:', error);
                    });
                });
            },
            getTableData() {
                const _this = this;
                axios.get('/api/getHousesList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error(result.message);
                        return;
                    }
                    _this.tableData = result.data;
                    _this.tableData.forEach(e => {
                        e.imgs = e.img_src.split(',')
                    })
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
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
