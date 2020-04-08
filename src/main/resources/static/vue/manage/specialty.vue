<template>
  <el-row>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="name" label="特产名称" width="220"></el-table-column>
      <el-table-column label="特产图片" width="180">
        <template slot-scope="scope">
          <img :src=`/file/${scope.row.url}` alt="" style="width: 100%;height: 100px;">
        </template>
      </el-table-column>
      <el-table-column prop="sale" label="价格" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="danger" @click="remove(scope)" size="mini">删除</el-button>
          <el-button type="primary" @click="drawerOpen(scope)" size="mini">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
    <el-drawer :before-close="drawerClose" :visible.sync="drawer.show" :wrapperClosable="false" ref="drawer" size="60%">
      <div class="demo-drawer__content">
        <el-form v-model="drawer.form">
          <el-form-item label="特产名称" :label-width="formLabelWidth">
            <el-input v-model="drawer.form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格" :label-width="formLabelWidth">
            <el-input v-model="drawer.form.sale" autocomplete="off" type="number"></el-input>
          </el-form-item>
          <el-form-item label="预览图">
            <el-upload class="avatar-uploader" :action="`/api/uploadShopFile/tickets`"
                       :show-file-list="false" ref="uploadFile" :on-success="uploadSuccess">
              <img v-if="drawer.form.url" :src=`/file/${drawer.form.url}` class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
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
        tableData: [],
        audioFile: {},
        drawer: {
          show: false,
          loading: false,
          form: {
            keyword: '',
            name: '',
            sale: '',
            url: '',
          },
        },
        editor: null,
        formLabelWidth: '80px',
        timer: null,

      };
    },
    mounted() {
      this.getGoodsList()
    },
    methods: {
      remove(scope) {
        const _this = this;
        const id = scope.row.id;
        _this.$confirm('确认删除？').then(e => {
          _this.drawer.loading = true;
          axios.delete(`/api/removeAudioFileById/${id}`).then(response => {
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

      getGoodsList() {
        const _this = this;
        axios.get('/api/getGoodsList/specialty').then(response => {
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
        this.$nextTick(() => {
          this.getGoodsById(scope ? scope.row.id : undefined);
        })
      },

      getGoodsById(id) {
        const _this = this;
        if (id !== undefined) {
          axios.get(`/api/getGoodsById/${id}`).then(response => {
            const result = response.data;
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              this.$message.error('数据加载失败');
              return
            }
            _this.drawer.form = result.data;
            _this.editor.txt.html(_this.drawer.form.url)
          }).catch(function (error) {
            console.log('请求出现错误:', error);
          });
        }
      },

      drawerClose() {
        this.drawer.loading = false;
        this.drawer.show = false;
        this.$refs.drawer.closeDrawer();
        this.getAudioFileList();
      },

      saveDrawer() {
        const _this = this;
        _this.drawer.loading = true;
        _this.drawer.form.keyword = 'specialty';
        axios.post(`/api/saveGoodsInfo`, _this.drawer.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.content = result.data;
          _this.drawer.loading = false;
          _this.$message.success('操作成功');
          window.location.reload();
        }).catch(function (error) {
          // window.location.reload();
          console.log('请求出现错误:', error);
        });
      },

      uploadSuccess(result) {
        console.log('通过api获取到的数据:', result);
        if (result.status !== 200) {
          this.$message.error('上传失败');
          return
        }
        this.drawer.form.url = result.data.path;
        this.$message.success('上传成功');
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
