<template>
  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="text-align: center">
      <div id="editor"></div>
      <el-button type="primary" @click="saveContent">立即发表</el-button>
    </el-col>
  </el-row>
</template>

<script>
  module.exports = {
    data() {
      return {
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
        paper: {},
      }
    },
    mounted() {
      this.editor = new window.wangEditor('#editor')
      this.editor.customConfig.uploadImgShowBase64 = true
      this.editor.customConfig.showLinkImg = false
      this.editor.customConfig.pasteIgnoreImg = true
      this.editor.customConfig.menus = this.wangEditorOptions
      this.editor.create()
      this.getPaper()
    },
    methods: {
      getPaper() {
        const _this = this
        axios.get('/api/getAudioFile/dynamic').then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.paper = result.data
          _this.editor.txt.html(_this.paper.url)
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
      uploadSuccess(result) {
        console.log('通过api获取到的数据:', result);
        if (result.status !== 200) {
          this.$message.error('上传失败');
          return
        }
        this.$message.success('上传成功');
        this.getPictures()
      },
      saveContent() {
        const _this = this
        _this.paper.url = _this.editor.txt.html()
        console.log(_this.paper)
        // console.log(data)
        axios.post(`/api/saveAudioFile/${this.paper.id}`, _this.paper).then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.content = result.data
        }).catch(function (error) {
          // 请求失败处理
          console.log('请求出现错误:', error);
        });
      },

    }
  }
</script>

<style scoped>
  .picture-img {
    width: 100%;
    height: 85%;
    border-radius: 5px;
  }

  .picture {
    height: 250px;
    margin-top: 10px;
  }

  .el-row {
    margin-bottom: 20px;

  &
  :last-child {
    margin-bottom: 0;
  }

  }
  .el-col {
    border-radius: 4px;
  }

  .bg-purple-dark {
    background: #99a9bf;
  }

  .bg-purple {
    background: #d3dce6;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>