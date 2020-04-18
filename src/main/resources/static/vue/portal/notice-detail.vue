<template>
  <el-row>
    <el-col>
      <div class="bread">
        <ul class="clearfix">
          <li>
            <router-link to="/home">首页</router-link>&nbsp;>&nbsp;
          </li>
          <li>
            <router-link to="/notice">通知公告</router-link>
          </li>
        </ul>
      </div>
      <div class="pg-main clearfix">
        <div class="pg-nav">
          <ul>
            <li>
              <router-link to="/notice">通知公告</router-link>
            </li>
          </ul>
        </div>
        <div class="pg-con newdetail" id="data">
          <h2>{{data.title}}</h2>
          <p class="ntime">发布时间：{{data.createTime}}</p>
          <div class="newdetailcon">
            <p v-html="data.content"></p>
          </div>
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
        data: {
          id: '',
          keyWord: '',
          title: '',
          content: '',
        },
      };
    },
    mounted() {
      let id = this.$route.query.id;
      this.getPaperById(id)
    },
    methods: {
      getPaperById(id) {
        const _this = this;
        axios.get(`/api/getPaperById?id=${id}`).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.data = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
    }
  }
</script>
<style>


</style>