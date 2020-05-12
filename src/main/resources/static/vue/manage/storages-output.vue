<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column fixed prop="name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="phone" label="电话号码" width="120"></el-table-column>
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
			<el-table-column prop="barCode" label="产品条码" width="120"></el-table-column>
			<el-table-column prop="amount" label="入库数量" width="120"></el-table-column>
			<el-table-column prop="producedTime" label="生产日期" width="120"></el-table-column>
			<el-table-column prop="shelLife" label="有效期至" width="120"></el-table-column>
			<el-table-column prop="zip" label="操作" width="300" fixed="right">
				<template slot-scope="scope">
					<el-button type="success" @click="remove(scope)" size="mini">查看详情</el-button>
					<el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: [
                    {
                        date: '2016-05-03',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-02',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-04',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-01',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-08',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-06',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-03',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-02',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-04',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-01',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-08',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-08',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                    {
                        date: '2016-05-08',
                        name: '王小虎',
                        province: '上海',
                        city: '普陀区',
                        address: '上海市普陀区金沙江路 1518 弄',
                        zip: 200333
                    },
                ]
            };
        },
        activated() {
            let id = this.$router.query.id
            if (!!id) {
                this.findDataById(id)
            }
        },
        mounted() {
        },
        methods: {
            filterByName(value, row, column) {
                console.log('筛选-->', value, row, column)
                return true
            },
            findDataById(id) {
                const _this = this;
                axios.get(`/manage/findDataById?id=${id}`).then(response => {
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
                axios.post(`/manage/saveData`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>

<style scoped>
</style>
