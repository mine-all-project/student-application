<template>
  <el-row>
    <el-col>
      <div class="bread">
        <ul class="clearfix">
          <li>
            <a href="index.html">首页</a>&nbsp;>&nbsp;
          </li>
          <li>
            <a href="introduction.html">联系我们</a>
          </li>
        </ul>
      </div>
      <div class="pg-main clearfix">
        <div class="pg-nav">
          <ul>
            <li>
              <a href="contact.html">联系我们</a>
            </li>
          </ul>
        </div>
        <div class="pg-con contpg">
          <iframe src="/portal/map/map.html"></iframe>
          <ul class="address clearfix">
            <li>公司地址：江苏省昆山开发区伟业路8号255室</li>
            <li>联系电话：18112651385</li>
          </ul>
          <div class="message">
            <h3>在线留言：</h3>
            <form>
              <input placeholder="姓名" type="text"/>
              <input placeholder="电话" type="tel"/>
              <textarea placeholder="消息"></textarea>
            </form>
          </div>
          <a href="" class="btn">立即发送</a>
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