<template>
	<div class="parent-body">
		<el-table :data="tableData" style="width: 100%" height="600">
			<el-table-column fixed prop="goods.name" label="产品名称" width="150" sortable
			                 :filters="[{text: '2016-05-04', value: '2016-05-04'}]"
			                 :filter-method="filterByName"></el-table-column>
			<el-table-column prop="counts" label="数量" width="120"></el-table-column>
			<el-table-column prop="goods.counts" label="剩余库存" width="120"></el-table-column>
			<el-table-column prop="goods.producedTime" label="生产日期" width="120"></el-table-column>
			<el-table-column prop="goods.shelLife" label="有效期至" width="120"></el-table-column>
			<el-table-column label="状态" width="120">
				<template slot-scope="scope">
					<el-tag type="success" effect="plain" v-if="scope.row.status === 0">正常</el-tag>
					<el-tag type="danger" effect="plain" v-else>已退</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="180">
				<template slot-scope="scope" v-if="scope.row.status === 0">
					<el-button type="danger" @click="returnSalse(scope)" size="mini">申请退货</el-button>
				</template>
			</el-table-column>
		</el-table>

		<el-drawer :before-cldemo-drawer__contentose="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false"
		           ref="drawer" size="70%">
			<div class="drawer-inner">
				<el-row class="form-line">
					<el-col :span="12">
						<el-col :span="2">
							<span>药品</span>
						</el-col>
						<el-col :span="21" :offset="1">
							<el-select v-model="form.goodsId" size="small" style="width: 80%" @change="selectChange">
								<el-option v-for="item in selectData" :key="item.id" :label="item.name" :value="item.id" size="small">
								</el-option>
							</el-select>
						</el-col>
					</el-col>
					<el-col :span="12">
						<el-col :span="2"><span>数量</span></el-col>
						<el-col :span="21" :offset="1">
							<el-input-number v-model="form.counts" :min="1" :max="drawer.data.counts" size="small"
							                 style="width: 80%"></el-input-number>
						</el-col>
					</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">生产日期</el-col>
					<el-col :span="20">{{drawer.data.producedTime}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">有效期至</el-col>
					<el-col :span="20">{{drawer.data.shelLife}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">性状</el-col>
					<el-col :span="20">{{drawer.data.characte}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">功能主治</el-col>
					<el-col :span="20">{{drawer.data.majorFunction}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">规格</el-col>
					<el-col :span="20">{{drawer.data.specification}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">用法用量</el-col>
					<el-col :span="20">{{drawer.data.usages}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">不良反应</el-col>
					<el-col :span="20">{{drawer.data.adverseReactions}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">注意事项</el-col>
					<el-col :span="20">{{drawer.data.mattersNeeding}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">禁忌</el-col>
					<el-col :span="20">{{drawer.data.taboo}}</el-col>
				</el-row>
				<el-row class="form-line">
					<el-col :span="4">储藏环境</el-col>
					<el-col :span="20">{{drawer.data.storageEnvironment}}</el-col>
				</el-row>

				<el-row class="form-line">
					<div class="drawer-footer" :label-width="formLabelWidth">
						<el-button type="primary" @click="saveDrawer">确定</el-button>
						<el-button @click="drawerClose">取 消</el-button>
					</div>
				</el-row>
			</div>
		</el-drawer>
		<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                formLabelWidth: '80px',
                drawer: {
                    show: false,
                    data: {
                        id: '',
                        counts: 1,
                        producedTime: '',
                        shelLife: '',
                        characte: '',
                        majorFunction: '',
                        specification: '',
                        usages: '',
                        adverseReactions: '',
                        mattersNeeding: '',
                        taboo: '',
                        storageEnvironment: '',
                        status: 0,
                    },
                },
                tableData: [],
                selectData: [],
                form: {
                    goodsId: '',
                    counts: '',
                    type: 0,
                }
            };
        },
        activated() {
            this.getTableDataList()
        },
        mounted() {
        },
        methods: {
            returnSalse(item) {
                this.$confirm(`确定要发起退货吗?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    axios.get('/api/reduceSalesInfo?id=' + item.row.id).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            this.$message.error('数据加载失败');
                            return;
                        }
                        _this.$message.success(result.message);
                        _this.getTableDataList();
                    }).catch(function (error) {
                        console.log('请求出现错误:', error);
                    });
                })
            },
            selectChange(id) {
                this.drawer.data = this.selectData.filter(e => {
                    return e.id === id
                })[0]
            },
            drawerOpen() {
                this.drawer.show = true;
                this.getSelectData();
            },
            drawerClose() {
                this.drawer.show = false;
                this.drawer.data = {};
                this.form = {};
                this.getTableDataList();
            },
            saveDrawer() {
                const _this = this;
                console.log(_this.form)
                _this.form.type = 0
                axios.post(`/api/addSalesInfo`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success(result.message);
                    _this.drawer.show = false;
                    _this.getTableDataList();
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            filterByName(value, row, column) {
                console.log('筛选-->', value, row, column)
                return true
            },
            getTableDataList() {
                const _this = this;
                axios.get('/api/getSalesList').then(response => {
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
            getSelectData() {
                const _this = this;
                axios.get('/api/getGoodsListByFlag').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.selectData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            sendMessages(item, type) {
                console.log(item.row, type)
                this.$confirm(`确定要发送${type === 0 ? '缺货' : '报损'}消息吗?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    _this.messageForm.objectId = item.row.id
                    _this.messageForm.type = type
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
	.drawer-inner {
		padding: 16px;
	}

	.form-line {
		margin-top: 16px;
	}
</style>
