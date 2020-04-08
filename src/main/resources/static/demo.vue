<template>
  <el-row>
    <el-table :data="messages" style="width: 100%;margin-bottom: 20px;" row-key="id" border default-expand-all
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column prop="user" label="姓名" sortable width="180"></el-table-column>
      <el-table-column prop="createTime" label="日期" sortable width="220"></el-table-column>
      <el-table-column prop="content" label="内容"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="addMessage(scope.row)">回复</el-button>
          <el-button size="mini" type="danger" @click="remove(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
  </el-row>

</template>

<script>
  module.exports = {
    data() {
      return {
        messages: [
          {
            id: 1,
            date: '2016-05-02',
            user: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            id: 2,
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          },
          {
            id: 3,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄',
            children: [
              {
                id: 31,
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
              },
              {
                id: 32,
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
              }
            ]
          },
          {
            id: 4,
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }
        ],
        form: {
          area: '0',
          content: '',
        },
      };
    },
    mounted() {
      this.getMessages()
    },
    methods: {
      addMessage(row) {
        const _this = this;
        this.$prompt('请输入内容', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({value}) => {
          _this.form.content = value;
          axios.post(`/api/addMessage/${row.id}`, _this.form).then(response => {
            const result = response.data;
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              this.$message.error('数据加载失败');
              return
            }
            _this.$message.success('操作成功');
            window.location.reload();
          }).catch(function (error) {
            window.location.reload();
            console.log('请求出现错误:', error);
          });
        }).catch(() => {
          console.log('取消输入')
        });
      },

      remove(scope) {
        const _this = this;
        const id = scope.row.id;
        _this.$confirm('确认删除？').then(e => {
          _this.drawer.loading = true;
          axios.delete(`/api/removeMessageById/${id}`).then(response => {
            _this.getAudioFileList();
            const result = response.data;
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              _this.$message.error('数据加载失败');
              return
            }
            _this.$message.success('操作成功')
          }).catch(function (error) {
            _this.getAudioFileList();
            console.log('请求出现错误:', error);
          });
        });
      },

      getMessages() {
        const _this = this
        axios.get('/api/getMessages/1').then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            layer.msg(`${result.message}`);
            return
          }
          _this.messages = result.data
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
