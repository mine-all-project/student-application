<template>
  <el-row>
    <el-col>
      <div class="pg-main clearfix">
        <div class="partner">
          <ul class="clearfix">
            <li><img data-th-src="@{/portal/images/partner/pa1.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa2.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa3.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa4.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa5.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa6.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa7.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa8.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa9.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa10.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa11.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa12.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa13.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa14.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa15.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa16.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa17.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa18.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa19.jpg}"></li>
            <li><img data-th-src="@{/portal/images/partner/pa20.jpg}"></li>
          </ul>
          <div class="pager">
            <ul class="clearfix">
              <li><a href="">&lt;</a></li>
              <li class="active"><a href="">1</a></li>
              <li><a href="">2</a></li>
              <li><a href="">&gt;</a></li>
            </ul>
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