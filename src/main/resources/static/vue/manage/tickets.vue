<template>
  <el-row>
    <el-col :span="24">
      <el-row type="flex" class="row">
        <el-col :span="2">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-light">门票图片:</div>
        </el-col>
        <el-col :span="10">
          <el-upload class="avatar-uploader" :action="`/api/uploadShopFile/tickets`"
                     :show-file-list="false" ref="uploadFile" :on-success="successUpload">
            <img v-if="form.url" :src=`/file/${form.url}` class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-col>
      </el-row>

      <el-row type="flex" class="row">
        <el-col :span="2">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-light">门票价格:</div>
        </el-col>
        <el-col :span="10">
          <el-input size="small" v-model="form.sale" type="number"></el-input>
        </el-col>
      </el-row>

      <el-row type="flex" class="row">
        <el-col :span="2">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple-light"></div>
        </el-col>
        <el-col :span="10">
          <el-button type="danger" @click="submitShop">提交</el-button>
        </el-col>
      </el-row>
    </el-col>
  </el-row>

</template>

<script>
  module.exports = {
    data() {
      return {
        form: {
          sale: '',
          url: '',
          keyWord: 'tickets',
        },
      };
    },
    mounted() {
      this.getShop()
    },
    methods: {
      getShop() {
        const _this = this;
        axios.get(`/api/getShopInfo/tickets`).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            _this.$message.error('数据加载失败');
            return
          }
          if (result.data !== null) {
            _this.form = result.data;
          }
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
      successUpload(res, file) {
        this.form.url = res.data.path;
      },
      submitShop() {
        const _this = this;
        console.log(_this.form);
        axios.post(`/api/saveShopInfo`, _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            _this.$message.error('数据加载失败');
            return
          }
          if (result.data !== null) {
            _this.form = result.data
          }
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      }
    }
  }
</script>

<style scoped>
  .row {
    margin-top: 20px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
