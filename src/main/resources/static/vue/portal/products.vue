<template>
  <el-row>
    <el-col>
      <div class="pg-main clearfix">
        <div class="pg-con productspg clearfix">

          <ul class="clearfix">
            <li class="products-list">
              <div>
                <router-link to="/products-detail">
                  <img src="/portal/images/products/pro1.jpg" class="products-list-img"/>
                  <h3 class="products-list-name"> 6501 </h3>
                  <p class="products-list-text">
                    主要用途：乳化剂，产品为淡黄色或珀色透明液体，具有较强的起泡力、浸透力、去污力、防锈力
                  </p>
                </router-link>

              </div>
            </li>
            <li class="products-list">
              <div>
                <a href="products-detail.html">
                  <img src="/portal/images/products/pro1.jpg" class="products-list-img"/>
                  <h3 class="products-list-name"> 6501 </h3>
                  <p class="products-list-text">
                    主要用途：乳化剂，产品为淡黄色或珀色透明液体，具有较强的起泡力、浸透力、去污力、防锈力
                  </p>
                </a>
              </div>
            </li>
          </ul>
        </div>
        <div class="pager">
          <ul class="clearfix">
            <li><a href="">&lt;</a></li>
            <li class="active"><a href="">1</a></li>
            <li><a href="">2</a></li>
            <li><a href="">&gt;</a></li>
          </ul>
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