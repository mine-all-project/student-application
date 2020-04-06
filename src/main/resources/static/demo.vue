<template>
  <el-row>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="createTime" label="日期" width="180"></el-table-column>
      <el-table-column prop="title" label="标题" width="180"></el-table-column>
      <el-table-column prop="url" label="正文" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="danger" @click="remove" size="mini">删除</el-button>
          <el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer" size="70%">
      <div class="demo-drawer__content">
        <el-form v-model="drawer.form">
          <el-form-item label="文章标题" :label-width="formLabelWidth">
            <el-input v-model="drawer.form.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="活动区域" :label-width="formLabelWidth">
            <div id="editor"></div>
          </el-form-item>
        </el-form>
        <div class="drawer-footer">
          <el-button type="primary" @click="$refs.drawer.closeDrawer()" :loading="drawer.loading">
            {{ drawer.loading ? '提交中 ...' : '确定'}}
          </el-button>
          <el-button @click="cancelForm">取 消</el-button>
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
        audioFile: {},
        drawer: {
          show: false,
          loading: false,
          form: {},
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
        timer: null,

      };
    },
    mounted() {
      this.getAudioFileList()
    },
    methods: {
      remove() {


      },
      getAudioFileList() {
        const _this = this
        axios.get('/api/getAudioFileList/dynamic').then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.tableData = result.data
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
      drawerOpen(scope) {
        console.log('row-->', scope.row)
        // this.drawer.show = true;
        // this.$nextTick(() => {
        //   this.editor = new window.wangEditor('#editor');
        //   this.editor.customConfig.uploadImgShowBase64 = true;
        //   this.editor.customConfig.showLinkImg = false;
        //   this.editor.customConfig.pasteIgnoreImg = true;
        //   this.editor.customConfig.menus = this.wangEditorOptions;
        //   this.editor.create();
        //   // this.getAudioFileById(id)
        // })
      },
      getAudioFileById(row, scope) {
        // const id = row.id
        console.log(row, scope)
        // const _this = this
        // axios.get(`/api/getAudioFileById/${123}`).then(response => {
        //   const result = response.data
        //   console.log('通过api获取到的数据:', result)
        //   if (result.status !== 200) {
        //     this.$message.error('数据加载失败');
        //     return
        //   }
        //   console.log('获取到的数据--->', result.data)
        //   _this.drawer.form = result.data
        //   // _this.editor.txt.html(_this.paper.url)
        // }).catch(function (error) {
        //   console.log('请求出现错误:', error);
        // });
      },

      drawerClose(done) {
        // if (this.loading) {
        //   return;
        // }
      },
      cancelForm() {
        this.drawer.loading = false;
        this.drawer.show = false;
      }
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