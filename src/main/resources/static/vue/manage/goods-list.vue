<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column fixed prop="name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="number" label="产品批号" width="120"></el-table-column>
			<el-table-column prop="address" label="生产地址" width="130" :show-overflow-tooltip="true"></el-table-column>
			<el-table-column prop="counts" label="库存" width="120"></el-table-column>
			<el-table-column prop="producedTime" label="生产日期" width="120"></el-table-column>
			<el-table-column prop="shelLife" label="有效期至" width="120"></el-table-column>
			<el-table-column label="操作" width="180">
				<template slot-scope="scope">
					<el-button type="danger" @click="sendMessages(scope,0)" size="mini">报损</el-button>
					<el-button type="primary" @click="sendMessages(scope,1)" size="mini">缺货</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: [],
                messageForm: {
                    type: 0,
                    objectId: '',
                    toAs: '1',
                }
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
            getTableDataList() {
                const _this = this;
                axios.get('/api/getGoodsListByFlag').then(response => {
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
            // saveMessages
            sendMessages(item, type) {
                console.log(item.row, type)
                this.$confirm(`确定要发送${type === 0 ? '报损' : '缺货'}消息吗?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    _this.messageForm.objectId = item.row.id
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
