<template>
  <el-row>
    <el-col>
      <div class="bread">
        <ul class="clearfix">
          <li>
            <router-link to="/home">首页</router-link>&nbsp;>&nbsp;
          </li>
          <li>
            <router-link to="/about">关于我们</router-link>
          </li>
        </ul>
      </div>
      <div class="pg-main clearfix">
        <div class="pg-nav">
          <ul>
            <li>
              <a href="/page/introduction.html">公司简介</a>
            </li>
            <li>
              <a href="ab-culture.html">企业文化</a>
            </li>
            <li>
              <a href="ab-honor.html">企业荣誉</a>
            </li>
          </ul>
        </div>
        <div class="pg-con">
          <h2>
            苏州天之信化工有限公司
          </h2>
          <p>
            经销批发的丙二醇、乙二醇、甘油、油酸、胺类、硬脂酸畅销消费者市场，在消费者当中享有较高的地位。
          </p>
          <p>
            公司与多家零售商和代理商建立了长期稳定的合作关系。苏州天之信化工有限公司经销的丙二醇、乙二醇、甘油、油酸、胺类品种齐全、价格合理。苏州天之信化工有限公司实力雄厚，重信用、守合同、保证产品质量，以多品种经营特色和薄利多销的原则，赢得了广大客户的信任。
          </p>
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