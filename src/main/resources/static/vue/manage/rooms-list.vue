<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%" :height="400">
			<el-table-column prop="name" label="名称"></el-table-column>
			<el-table-column prop="filePath" label="设备信息">
				<template slot-scope="scope">
					<el-button type="primary" @click="remove(scope)" size="mini">查看详情</el-button>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="primary" @click="edit(scope)" size="mini">编辑</el-button>
					<el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<el-drawer :visible.sync="drawer.show" :wrapperClosable="false" size="70%">
			<div class="demo-drawer__content">
				<el-form v-model="form">
					<el-form-item label="名称" :label-width="formLabelWidth">
						<el-input v-model="form.name" autocomplete="off" style="width: 30%" size="mini"></el-input>
					</el-form-item>
					<!--					<el-form-item label="站点" :label-width="formLabelWidth">-->
					<!--						<el-transfer v-model="form.standsList" :data="stands" target-order="push"-->
					<!--						             :titles="['所有站点', '途经站点']"-->
					<!--						             :button-texts="['移除', '添加']">-->
					<!--						</el-transfer>-->
					<!--					</el-form-item>-->
					<el-form-item :label-width="formLabelWidth">
						<el-button type="primary" @click="saveForm" size="mini">确定</el-button>
						<el-button @click="formClose" size="mini">取 消</el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-drawer>
		<div style="margin-top: 10px">
			<el-button type="primary" @click="addRooms(undefined)" size="mini">添加实训室</el-button>
		</div>
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
            edit() {
                this.drawer.show = true
            },
            saveForm() {
                const _this = this;
                let url = this.isEdit ? '/manage/saveUserInfo' : 'addUser'
                axios.post(url, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    _this.drawer.show = false;
                    _this.getTableDataList()
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            formClose() {
                this.drawer.show = false;
                this.getTableDataList();
            },
            addRooms(scope) {
                const _this = this
                _this.$nextTick(() => {
                    _this.getDataById(scope ? scope.row.id : ' ');
                    _this.$prompt('请输入名称', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                    }).then(({value}) => {
                        _this.form.name = value
                        axios.post(`/api/saveRoomsInfo`, _this.form).then(response => {
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
                })
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
                axios.get('/api/getRoomsList').then(response => {
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
            getDataById() {

            },

        }
    }
</script>

<style scoped>
	.drawer-footer {
		margin-left: 10px;
	}
</style>
