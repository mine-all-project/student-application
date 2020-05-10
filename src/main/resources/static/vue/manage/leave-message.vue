<template>
  <el-row>
    <el-table :data="tableData" stripe style="width: 100%" row-key="id">
      <el-table-column prop="createTime" label="日期" width="250"></el-table-column>
      <el-table-column prop="userName" label="作者" width="180"></el-table-column>
      <el-table-column prop="houses" label="来源" width="180"></el-table-column>
      <el-table-column prop="content" label="内容" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
          <el-button type="primary" v-if="!scope.row.parent_id" :disabled="scope.row.children.length>0" @click="drawerOpen(scope.row)" size="mini">回复</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer" size="70%">
      <div class="demo-drawer__content">
        <el-form v-model="form">
          <el-form-item label="回复内容" :label-width="formLabelWidth">
            <el-input type="textarea" :rows="4" placeholder="请输入回复内容" v-model="form.content"></el-input>
          </el-form-item>
        </el-form>
        <div class="drawer-footer">
          <el-button type="primary" @click="savePaper" :loading="drawer.loading">
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
        tableData: [],
        form: {
          parent_id: '',
          houses_id: '',
          content: '',
        },
        drawer: {
          show: false,
          loading: false,
        },
        wangEditorOptions: [
          'head',  // 标题
          'bold',  // 粗体
          'fontSize',  // 字号
          'fontName',  // 字体
          'italic',  // 斜体
          'underline',  // 下划线
          'strikeThrough',  // 删除线
          'justify',  // 对齐方式
          'image',  // 插入图片
        ],
        editor: null,
        formLabelWidth: '80px',
        keyWord: 'news',
      };
    },
    mounted() {
      this.getPaperList()
    },
    methods: {
      remove(scope) {
        const _this = this;
        const id = scope.row.id;
        _this.$confirm('确认删除？').then(e => {
          _this.drawer.loading = true;
          axios.delete(`/api/removeLeaveMessage/${id}`).then(response => {
            _this.getPaperList(_this.keyWord);
            const result = response.data;
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              _this.$message.error('数据加载失败');
              return
            }
            _this.$message.success('操作成功')
          }).catch(function (error) {
            _this.getPaperList(_this.keyWord);
            console.log('请求出现错误:', error);
          });
        });
      },
      getPaperList(keyWord) {
        const _this = this;
        axios.get(`/api/getLeaveMessage`).then(response => {
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
      drawerOpen(scope) {
        this.drawer.show = true;
        this.form = {
          parent_id: scope.id,
          houses_id: scope.houses_id,
          content: '',
        }
      },
      savePaper() {
        if (!(this.form.content)) {
          this.$message.warning('请完整填写信息');
          return
        }
        const _this = this;
        _this.loading = true;
        axios.post(`/api/saveLeaveMessage`, _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.content = result.data;
          _this.$message.success('操作成功');
          _this.drawer.loading = false;
          _this.drawer.show = false;
          _this.getPaperList(this.keyWord);
        }).catch(function (error) {
          // window.location.reload();
          console.log('请求出现错误:', error);
        });
      },
      drawerClose() {
        this.drawer.loading = false;
        this.drawer.show = false;
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