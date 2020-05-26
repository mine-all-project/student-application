<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%" :height="500">
			<el-table-column prop="createTime" label="预约时间"></el-table-column>
			<el-table-column prop="startTime" label="开始时间"></el-table-column>
			<el-table-column prop="endTime" label="结束时间"></el-table-column>
			<el-table-column prop="reallyStartTime" label="实际开始时间"></el-table-column>
			<el-table-column prop="reallyEndTime" label="预计结束时间"></el-table-column>
			<el-table-column prop="user.name" label="预约人"></el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag size="small" v-if="scope.row.status === 0">未开始</el-tag>
					<el-tag size="small" v-if="scope.row.status === 1" type="warning">使用中</el-tag>
					<el-tag size="small" v-if="scope.row.status === 2" type="success">已结束</el-tag>
					<el-tag size="small" v-if="scope.row.status === 3" type="info">已取消</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="primary" @click="startUse(scope)" size="mini" v-if="scope.row.status === 0">开始</el-button>
					<el-button type="primary" @click="endUse(scope)" size="mini" v-if="scope.row.status === 1">结束</el-button>
					<el-button type="danger" @click="closeUse(scope)" size="mini" v-if="scope.row.status === 0">撤销</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-dialog title="上传实验照片" :visible.sync="show.dialog" width="22%" style="text-align: center">
			<el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/"
			           :show-file-list="false" :on-success="handleAvatarSuccess">
				<img v-if="imageUrl" :src="imageUrl" class="avatar">
				<i v-else class="el-icon-plus avatar-uploader-icon"></i>
			</el-upload>
			<el-button size="mini" type="primary">确认提交</el-button>
		</el-dialog>
	</el-row>
</template>

<script>
    module.exports = {
        data() {
            return {
                tableData: [],
                imageUrl: '',
                show: {
                    dialog: false
                }
            };
        },
        activated() {
            this.getTableDataList()
        },
        mounted() {
        },
        methods: {
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
            },
            startUse(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认要开始使用吗？').then(e => {
                    axios.get(`/api/startLineUpsById?id=${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
                            return
                        }
                        _this.$message.success(result.message)
                    }).catch(function (error) {
                        _this.getTableDataList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            endUse(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认要结束吗？').then(() => {
                    _this.show.dialog = true
                    return
                    axios.get(`/api/endLineUpsById?id=${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
                            return
                        }
                        _this.$message.success(result.message)
                    }).catch(function (error) {
                        _this.getTableDataList();
                        console.log('请求出现错误:', error);
                    });
                })
            },
            closeUse(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认取消？').then(e => {
                    axios.delete(`/api/closeLineUpsById?id=${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error(result.message);
                            return
                        }
                        _this.$message.success(result.message)
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
                    // result.data.forEach(e => {
                    //     if (new moment() > new moment(e.reallyEndTime) && e.status === 1) {
                    //         this.endUse(e.id)
                    //     }
                    // })
                }).catch(function (error) {
                    console.error('请求出现错误:', error);
                });
            },
        },

    }
</script>

<style scoped>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
