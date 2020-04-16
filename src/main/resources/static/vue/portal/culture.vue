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
          <div class="ab-culture">
            <img src="/portal/images/cul.jpg"/>
            <p>追求高品质、高工艺水平，使用户享受卫生、健康、舒适的生活是我们一贯追求的目标。</p>
            <p>“合抱之木，生于毫末；九层之台，起于垒土；千里之行，始于足下。”我们锐意进取，努力打造优质、高效的产品供应链体系，秉承“科技引领市场、品质铸就品牌”的经营理念，为客户提供优质、周到的技术支持与服务。</p>
            <p>“穷则独善其身，达则兼济天下。”我们务实，但从未停止追求卓越，我们尊重合作伙伴的感受，诚邀各界业内人士长期沟通，共谋发展、共创辉煌！</p>
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