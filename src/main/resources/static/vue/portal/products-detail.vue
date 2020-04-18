<template>
  <el-row>
    <el-col>
      <div class="pg-con prodetail">
        <h1>
          甘油
        </h1>
        <div>
          <h3>产品介绍：</h3>
          <p>中文品名&nbsp;:&nbsp;甘油</p>
          <p>中文别名&nbsp;:&nbsp;丙三醇；1，2，3-丙三醇；三羟基丙烷</p>
          <p>英文品名&nbsp;:&nbsp;Glycerin</p>
          <p>CAS号&nbsp;:&nbsp;56-81-5</p>
          <p>主要规格型号&nbsp;:&nbsp;95%/99.5%</p>
          <p>包装&nbsp;:&nbsp;250KG桶</p>
          <p>产地&nbsp;:&nbsp;国产/进口</p>
          <p>应用&nbsp;:&nbsp;用作基本有机化工原料，广泛用于医药、食品、日用化学、纺织、造纸、油漆等行业</p>
        </div>
        <!--<div class="procon">
          <h3>详细信息</h3>
          <ul>
            <li><span>型号：</span>6501</li>
            <li><span>品牌：</span>进口</li>
            <li><span>主要用途：</span>乳化剂</li>
            <li><span>有效物质质量：</span>90%</li>
            <li><span>执行保准：</span>国际</li>
          </ul>
        </div>-->
        <p class="cona">如有疑问，<a href="contact.html">→联系我们</a></p>
      </div>
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
<style scoped>
  .products-list {
    width: 20%;
    float: left;
    margin: 10px;
  }

  .products-list-img {
    width: 100%;
  }

  .products-list-name {
    width: 100%;
  }

  .products-list-text {
    width: 100%;
    line-height: 20px;
  }

</style>