<template>
    <el-row>
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="createTime" label="日期" width="220"></el-table-column>
            <el-table-column prop="title" label="标题" width="180"></el-table-column>
            <el-table-column prop="content" label="正文" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column label="操作" width="160">
                <template slot-scope="scope">
                    <el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
                    <el-button type="primary" @click="drawerOpen(scope.row)" size="mini">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="drawerOpen()" size="mini">添加</el-button>
        <el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer"
                   size="70%">
            <div class="demo-drawer__content">
                <el-form v-model="form">
                    <el-form-item label="标题" :label-width="formLabelWidth">
                        <el-input v-model="form.title" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="内容" :label-width="formLabelWidth">
                        <div id="editor"></div>
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
    data () {
      return {
        tableData: [],
        form: {
          id: '',
          title: '',
          content: ''
        },
        drawer: {
          show: false,
          loading: false
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
          'image'  // 插入图片
        ],
        editor: null,
        formLabelWidth: '80px',
        keyWord: 'notice'
      };
    },
    mounted () {
      this.getTableData();
    },
    methods: {
      getTableData () {
        const _this = this;
        axios.get('/api/getContractsList').then(response => {
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
      remove (scope) {
        const _this = this;
        const id = scope.row.id;
        _this.$confirm('确认删除？').then(e => {
          _this.drawer.loading = true;
          axios.delete(`/api/removeContractsById/${id}`).then(response => {
            _this.getTableData(this.keyWord);
            const result = response.data;
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              _this.$message.error('数据加载失败');
              return;
            }
            _this.$message.success('操作成功');
          }).catch(function (error) {
            _this.getTableData(this.keyWord);
            console.log('请求出现错误:', error);
          });
        });
      },
      getPaperList (keyWord) {
        const _this = this;
        axios.get(`/api/getPapersByKeyWord/${keyWord}`).then(response => {
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
      drawerOpen (scope) {
        if (scope) {
          this.form = {
            ...scope
          }
        } else {
          this.form = {
            title: '',
            content: ''
          }
        }
        this.drawer.show = true;
        if (this.editor) {
          this.editor.txt.html(this.form.content);
        } else {
          this.$nextTick(this.initEditor)
        }
      },
      initEditor () {
        this.editor = new window.wangEditor('#editor');
        this.editor.customConfig.uploadImgShowBase64 = true;
        this.editor.customConfig.showLinkImg = false;
        this.editor.customConfig.pasteIgnoreImg = true;
        this.editor.customConfig.menus = this.wangEditorOptions;
        this.editor.customConfig.onchange = html => this.form.content = html;
        this.editor.create();
        this.editor.txt.html(this.form.content);
      },
      getPaperById (id) {
        const _this = this;
        axios.get(`/api/getPaperById?id=${id}`).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return;
          }
          _this.form = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
      savePaper () {
        if (!(this.form.title && this.form.content)) {
          this.$message.warning('请完整填写信息');
          return
        }
        const _this = this;
        _this.drawer.loading = true;
        axios.post(`/api/saveContractInfo`, _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return;
          }
          _this.$message.success('操作成功');
          _this.drawer.loading = false;
          _this.drawer.show = false;
          _this.getTableData();
        }).catch(function (error) {
          console.log('请求出现错误:', error);
          window.location.reload();
        });
      },
      drawerClose () {
        this.drawer.loading = false;
        this.drawer.show = false;
      }
    }
  };
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