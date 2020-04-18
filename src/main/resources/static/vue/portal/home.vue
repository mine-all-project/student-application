<template>
  <el-row>
    <el-col>
      <div class="products">
        <section class="title">
          <h2>产品系列</h2>
        </section>
        <ul class="clearfix">
          <li>
            <a href="products.html">
              <img src="/portal/images/products/pro1.jpg"/>
              <h3>有机化工</h3>
              <p>醇醚类、羧酸类、酯类、含氮类、含卤类、含硫类、酚类、烃类、醛酮类、杂环类、元素类</p>
            </a>
          </li>
          <li>
            <a href="products.html">
              <img src="/portal/images/products/pro2.jpg"/>
              <h3>无机化工</h3>
              <p>硫化合物及硫酸盐、磷化合物及磷酸盐、氯化物和氯酸盐、氯化物和氯酸盐、氟化合物、
                碳酸盐、硅化合物及硅酸盐、硼化合物、硝酸盐、钡化合物、铬化合物、氧化物和过氧化物、氢氧化物、其他无机化合物</p>
            </a>
          </li>
          <li>
            <a href="products.html">
              <img src="/portal/images/products/pro4.jpg}"/>
              <h3>表面活性剂</h3>
              <p>阴离子表面活性剂、非离子表面活性剂、阳离子表面活性剂、两性离子表面活性剂、复配表面活性剂</p>
            </a>
          </li>
          <li>
            <a href="products.html">
              <img src="/portal/images/products/pro6.jpg}"/>
              <h3>医药中间体</h3>
              <p>胺类、咪唑类、吡唑类、吡啶类、嘧啶类、羧酸类、催化剂、苯衍生物、硼酸衍生物、五元杂环类、异吲哚啉酮类、其他</p>
            </a>
          </li>
        </ul>
      </div>
      <div class="newsbg">
        <div class="news">
          <section class="title">
            <h2>新闻中心</h2>
          </section>
          <div class="news-list clearfix">
            <div>
              <div>
                <img src="/portal/images/news/news1.jpg"/>
                <div class=""></div>
              </div>
            </div>
            <div>
              <ul>
                <li>
                  <div class="news-time">
                    <span>20</span>
                    <p>2017-6</p>
                  </div>
                  <h3><a href="news-detail.html">澳大利亚反倾销理事会自7月1日起正式运作</a></h3>
                </li>
                <li>
                  <div class="news-time">
                    <span>20</span>
                    <p>2017-6</p>
                  </div>
                  <h3><a href="news-detail.html">全球化工并购“战略”味道渐浓</a></h3>
                </li>
                <li>
                  <div class="news-time">
                    <span>20</span>
                    <p>2017-6</p>
                  </div>
                  <h3><a href="news-detail.html">专家支招化工行业绿色发展</a></h3>
                </li>
                <li>
                  <div class="news-time">
                    <span>20</span>
                    <p>2017-6</p>
                  </div>
                  <h3><a href="news-detail.html">公司被评为第二季度省级优秀企业</a></h3>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="about">
        <section class="title">
          <h2>关于我们</h2>
        </section>
        <div class="aboutli">
          <img src="/portal/images/fg.jpg"/>
          <div>
            <div>
              <h3>苏州天之信化工有限公司</h3>
              <p>
                经销批发的丙二醇、乙二醇、甘油、油酸、胺类、硬脂酸畅销消费者市场，在消费者当中享有较高的地位。
              </p>
              <p>
                天之信与多家零售商和代理商建立了长期稳定的合作关系。苏州天之信化工有限公司经销的丙二醇、乙二醇、甘油、油酸、胺类品种齐全、价格合理。苏州天之信化工有限公司实力雄厚，重信用、守合同、保证产品质量，以多品种经营特色和薄利多销的原则，赢得了广大客户的信任。
              </p>
            </div>
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
                tableData: [],
            };
        },
        mounted() {
            this.getUserList()
        },
        methods: {
            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.delete(`/api/removePaperById/${id}`).then(response => {
                        _this.getPaperList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getPaperList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            changeStatus(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.put(`/manage/changeStatus/${id}`).then(response => {
                        _this.getPaperList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getPaperList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getUserList() {
                const _this = this;
                axios.get('/manage/getUserList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.tableData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>
<style>


</style>