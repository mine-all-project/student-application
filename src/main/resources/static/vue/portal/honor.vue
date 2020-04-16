<template>
  <el-row>
    <el-col>
      <div class="bread">
        <ul class="clearfix">
          <li>
          <li>
            <router-link to="/home">首页</router-link>&nbsp;>&nbsp;
          </li>
          <li>
            <router-link to="/culture">企业文化</router-link>
          </li>
        </ul>
      </div>
      <div class="pg-main clearfix">
        <div class="pg-nav">
          <ul>
            <li>
              <router-link to="/introduction">公司简介</router-link>
            </li>
            <li>
              <router-link to="/culture">企业文化</router-link>
            </li>
            <li>
              <router-link to="/honor">公司荣誉</router-link>
            </li>
          </ul>
        </div>
        <div class="pg-con">
          <p><span>2015年</span>获得国家某某奖项</p>
          <p><span>2016年</span>获得国家某某奖项</p>
          <p><span>2017年</span>获得国家某某奖项</p>
        </div>
      </div>
      <div class="space"></div>
    </el-col>
  </el-row>
</template>
<script>
  module.exports = {
    data() {
      return {
        dataList: [],
      };
    },
    mounted() {
      this.getPaperList()
    },
    methods: {
      getPaperList() {
        const _this = this;
        axios.get('/api/getPapersByKeyWord/notice').then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return;
          }
          _this.dataList = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
    }
  }
</script>
<style>


</style>