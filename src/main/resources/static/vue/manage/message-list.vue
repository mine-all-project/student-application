<template>
	<el-row>
		<el-table :data="tableData" stripe style="width: 100%">
			<el-table-column prop="createTime" label="时间"></el-table-column>
			<el-table-column prop="formAs.name" label="发送人"></el-table-column>
			<el-table-column prop="type" label="类型">
				<template slot-scope="scope">
					<el-tag type="warning" v-if="scope.row.type === 0">缺货</el-tag>
					<el-tag type="danger" v-else>报损</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag type="primary" v-if="scope.row.status === 0">未读</el-tag>
					<el-tag type="success" v-else>已读</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button type="primary" @click="showMessageInfo(scope)" size="mini">查看详情</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-dialog title="消息" :visible.sync="dialogVisible" width="30%">
			<span>{{messageInfo.content}}</span>
			<span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
		</el-dialog>
	</el-row>

</template>

<script>
    module.exports = {
        data() {
            return {
                dialogVisible: false,
                tableData: [],
                messageInfo: {}
            };
        },
        mounted() {
            this.getTableDataList()
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.delete(`/api/removePapersById/${id}`).then(response => {
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
                axios.get(`/api/getMessageList`).then(response => {
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
            showMessageInfo(item) {
                axios.get(`/api/getMessagesById?id=${item.row.id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    this.getTableDataList()
                    this.messageInfo = result.data
                    this.dialogVisible = true
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
