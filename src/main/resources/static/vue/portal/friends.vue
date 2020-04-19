<template>
  <el-row>
    <el-col>
      <div class="pg-main clearfix">
        <ul class="">
          <li class="img-list">
            <img :src="item.url" v-for="item in fileList" style="width: 100%;height: 100%"/>
          </li>
        </ul>
      </div>
      <div class="space"></div>
    </el-col>
  </el-row>

</template>
<script>
  module.exports = {
    data() {
      return {
        fileList: [],
        keyWord: 'friends'
      };
    },
    mounted() {
      this.getFileList(this.keyWord)
    },
    methods: {
      getFileList(keyWord) {
        const _this = this;
        axios.get(`/api/getFileListByKeyWord/${keyWord}`).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return;
          }
          _this.fileList = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
    }
  }
</script>
<style>

  .img-list {
    width: 300px;
    height: 200px;
    float: left;
    display: inline-block;
    margin: 5px;
  }
</style>