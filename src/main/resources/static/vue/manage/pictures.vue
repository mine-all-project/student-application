<template>
  <el-row :gutter="20">
    <el-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8" v-for="item in pictures" :key="item.id">
      <div class="grid-content bg-purple picture">
        <img :src="`/file/${item.url}`" alt="" class="picture-img">
        <el-upload :show-file-list="false" :action="`/api/uploadFile/${item.id}`"
                   :on-success="uploadSuccess" :on-error="uploadError">
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  module.exports = {
    data() {
      return {
        pictures: [],
      }
    },
    mounted() {
      this.getPictures()
    },
    methods: {
      getPictures() {
        const _this = this
        axios.get('/api/getPictures').then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            this.$message.error('图片加载失败');
            return
          }
          _this.pictures = result.data
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
