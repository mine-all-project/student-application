<template>
  <el-row :gutter="20">
    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="text-align: center">
      <video width="80%" height="100%" controls v-if="video.url">
        <source :src=`/file/${video.url}` type="video/mp4">
      </video>
      <el-upload :show-file-list="false" :action="`/api/uploadFile/${video.id}`"
                 :on-success="uploadSuccess" :on-error="uploadError" style="text-align: right">
        <el-button size="medium" type="primary">点击上传</el-button>
      </el-upload>
    </el-col>
  </el-row>
</template>

<script>
  module.exports = {
    data() {
      return {
        video: {},
      }
    },
    mounted() {
      this.getVideo()
    },
    methods: {
      getVideo() {
        const _this = this
        axios.get('/api/getAudioFile/video').then(response => {
          const result = response.data
          console.log('通过api获取到的数据:', result)
          if (result.status !== 200) {
            this.$message.error('视频缓冲失败');
            return
          }
          _this.video = result.data
        }).catch(function (error) {
          // 请求失败处理
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
      uploadError(file) {
        console.log(file);
        this.$message.error('上传失败');
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
