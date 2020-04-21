<template>
  <el-row>
    <el-col>
      <el-dialog title="外层 Dialog" :visible.sync="outerVisible">
        <el-dialog
            width="30%"
            title="内层 Dialog"
            :visible.sync="innerVisible"
            append-to-body>
        </el-dialog>
        <div slot="footer" class="dialog-footer">
          <el-button @click="outerVisible = false">取 消</el-button>
          <el-button type="primary" @click="innerVisible = true">打开内层 Dialog</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>

</template>
<script>
  module.exports = {
    data() {
      return {
        outerVisible: false,
        innerVisible: false
      };
    },
    mounted() {
      let id = this.$route.query.id;
      this.getAnimalById(id)
    },
    methods: {
      getAnimalById(id) {
        const _this = this;
        axios.get(`/api/getAnimalById?id=${id}`).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          _this.animal = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      },
      getSum(newValue, oldValue) {
        this.form.sum = parseFloat(this.form.counts) * parseFloat(this.animal.price);
      },
      createOrder() {
        const _this = this;
        _this.form.goodsId = _this.animal.id;
        console.log(_this.form);
        axios.post(`/api/createOrder`,_this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error('数据加载失败');
            return
          }
          // _this.animal = result.data;
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      }
    }
  }
</script>
<style scoped>
  .goods-info {
    width: 50%;
    display: inline-block;
    float: left;
  }

  .img-list {
    margin: 3px;
    width: 30%;
  }

</style>