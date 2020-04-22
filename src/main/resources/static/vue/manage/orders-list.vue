<template>
  <el-row>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="username" label="订单号" width=""></el-table-column>
      <el-table-column prop="name" label="商品" width=""></el-table-column>
      <el-table-column prop="age" label="数量" width=""></el-table-column>
      <el-table-column prop="mail" label="金额" width=""></el-table-column>
      <el-table-column prop="phone" label="联系方式" width=""></el-table-column>
      <el-table-column prop="phone" label="姓名" width=""></el-table-column>
      <el-table-column prop="phone" label="订单时间" width=""></el-table-column>
      <el-table-column label="状态" width="">
        <template slot-scope="scope">
          <el-tag :key="scope.row.id" type="success" effect="plain" v-if="scope.row.status === 0">正常</el-tag>
          <el-tag :key="scope.row.id" type="danger" effect="plain" v-else>禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="danger" @click="changeStatus(scope)" size="mini" v-if="scope.row.status === 0">禁用</el-button>
          <el-button type="success" @click="changeStatus(scope)" size="mini" v-else>启用</el-button>
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
            this.getOrdersList()
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.delete(`/api/removePaperById/${id}`).then(response => {
                        _this.getPaperList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getPaperList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            changeStatus(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.put(`/manage/changeStatus/${id}`).then(response => {
                        _this.getPaperList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getPaperList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getOrdersList() {
                const _this = this;
                axios.get('/api/getOrdersList').then(response => {
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
  .table-row-hidden {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .drawer-footer {
    margin-left: 10px;
  }
</style>