<template>
  <el-row>
    <el-col>
      <div class="pg-main clearfix">
        <div class="pg-con newspg">
          <ul class="clearfix">
            <li v-for="item in dataList">
              <h3>
                <router-link :to="{path:'/notice-detail',query:{id:item.id}}">{{item.title}}</router-link>
              </h3>
              <p class="neptime">{{item.createTime}}</p>
            </li>
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
.pg-con{
  margin: 0;
}

</style>