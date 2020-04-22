<template>
  <el-row>
    <el-col>
      <div class="pg-con prodetail">
        <h1> {{animal.name}} </h1>
        <el-row>
          <ul v-if="animal.audioFiles">
            <li v-for="item in animal.audioFiles" class="img-list">
              <img :src="item.url" style="width: 100%">
            </li>
          </ul>
          <h3>商品详情：</h3>
          <p>商品名称&nbsp;:&nbsp;{{animal.name}}</p>
          <p class="goods-info">价格&nbsp;:&nbsp;{{animal.price}}</p>
          <p class="goods-info">库存&nbsp;:&nbsp;{{animal.remaining}}</p>
          <p>简介&nbsp;:&nbsp;{{animal.content}}</p>
        </el-row>
        <el-col :span="8" :offset="16">
          <div class="procon">
            <el-col :span="12">
              <label>数量</label>
              <el-input-number v-model="form.counts" :min="0" :max="animal.remaining" label="数量" :step="1"
                               step-strictly :precision="0" @change="getSum"></el-input-number>

            </el-col>
            <el-col :span="12">
              <label>金额</label>
              <el-input-number v-model="form.sum" label="总价" :controls="false"
                               :precision="2" :disabled="true"></el-input-number>
            </el-col>
          </div>
        </el-col>
        <br>
        <hr>
        <br>
        <el-row>
          <el-col :span="4" :offset="20">
            <el-button type="primary" @click="createOrder">立即购买</el-button>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </el-row>

</template>
<script>
  module.exports = {
    data() {
      return {
        animal: {
          id: '',
          content: '',
          name: '',
          sale: 0,
          remaining: 0,
          type: '',
          price: 0,
        },
        form: {
          goodsId: '',
          counts: 1,
          sum: 0
        },
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
        if (_this.form.counts <= 0) {
          this.$message.error('数量最低为1');
          return;
        }
        _this.form.goodsId = _this.animal.id;
        console.log(_this.form);
        axios.post(`/api/createOrder`, _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            this.$message.error(result.message);
            if (result.status !== 401) {
              setTimeout(() => {
                window.location.href = "/login"
              }, 5000)
            }
          }
          this.$message.success(result.message);
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