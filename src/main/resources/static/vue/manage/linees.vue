<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%">
			<el-table-column prop="number" label="线路"></el-table-column>
			<el-table-column prop="startTime" label="首班时间"></el-table-column>
			<el-table-column prop="endTime" label="末班时间"></el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
					<el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
		<el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer" size="70%">
			<div class="demo-drawer__content">
				<el-form v-model="form">
					<el-form-item label="车次" :label-width="formLabelWidth">
						<el-input v-model="form.number" autocomplete="off" style="width: 30%"></el-input>
					</el-form-item>
					<el-form-item label="首班时间" :label-width="formLabelWidth">
						<el-time-picker v-model="form.startTime" placeholder="首班时间"></el-time-picker>
					</el-form-item>
					<el-form-item label="末班时间" :label-width="formLabelWidth">
						<el-time-picker v-model="form.endTime" placeholder="首班时间"></el-time-picker>
					</el-form-item>
					<el-form-item label="站点" :label-width="formLabelWidth">
						<el-transfer v-model="form.stands" :data="stands"></el-transfer>
					</el-form-item>
				</el-form>
				<div class="drawer-footer">
					<el-button type="primary" @click="saveDrawer" :loading="drawer.loading">
						{{ drawer.loading ? '提交中 ...' : '确定'}}
					</el-button>
					<el-button @click="drawerClose">取 消</el-button>
				</div>
			</div>
		</el-drawer>
	</el-row>

</template>

<script>
    module.exports = {
        data() {
            return {
                stands: [],
                active: 0,
                tableData: [],
                form: {
                    id: '',
                    number: '',
                    startTime: '',
                    endTime: '',
                    stands: [],
                },
                drawer: {
                    show: false,
                    loading: false,
                },
                formLabelWidth: '80px',
            };
        },
        mounted() {
            this.getTableDataList()
            this.getStandsList()
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.delete(`/api/removePaperById/${id}`).then(response => {
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
                axios.get(`/api/getLineesList`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    result.data.forEach(e => {
                        e.startTime = moment(e.startTime).format('HH:mm:ss')
                        e.endTime = moment(e.endTime).format('HH:mm:ss')
                    })
                    _this.tableData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            getStandsList() {
                const _this = this;
                axios.get(`/api/getStandsList`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.stands = result.data.map(e => {
                        return {
                            key: e.id,
                            label: e.name,
                        }
                    });
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            drawerOpen(scope) {
                this.$nextTick(() => {
                    this.getDataById(scope ? scope.row.id : ' ');
                    this.drawer.show = true;
                })
            },
            getDataById(id) {
                const _this = this;
                axios.get(`/api/getLineesById?id=${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.form.startTime = new Date(result.data.startTime)
                    _this.form.endTime = new Date(result.data.endTime)
                    _this.form = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            saveDrawer() {
                const _this = this;

                console.log(_this.form)
                // _this.drawer.loading = true;
                // axios.post(`/api/saveLinees`, _this.form).then(response => {
                //     const result = response.data;
                //     console.log('通过api获取到的数据:', result);
                //     if (result.status !== 200) {
                //         this.$message.error('数据加载失败');
                //         return
                //     }
                //     _this.$message.success('操作成功');
                //     _this.drawer.loading = false;
                //     _this.drawer.show = false;
                //     _this.getTableDataList()
                // }).catch(function (error) {
                //     window.location.reload();
                //     console.log('请求出现错误:', error);
                // });
            },
            drawerClose() {
                this.drawer.loading = false;
                this.drawer.show = false;
                this.getLineesList();
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
