<template>
    <div>
        <div id="page">
            <header class="version_1">
                <div class="layer"></div><!-- Mobile menu overlay mask -->
                <div class="main_header">
                    <div class="container">
                        <div class="row small-gutters">
                            <div class="col-xl-3 col-lg-3 d-lg-flex align-items-center">
                                <div id="logo">
                                    <a href="/index">
                                        <img src="/portal/img/logo.svg" alt="" width="100" height="35">
                                    </a>
                                </div>
                            </div>
                            <nav class="col-xl-6 col-lg-7">
                                <a class="open_close" href="javascript:void(0);">
                                    <div class="hamburger hamburger--spin">
                                        <div class="hamburger-box">
                                            <div class="hamburger-inner"></div>
                                        </div>
                                    </div>
                                </a>
                                <!-- Mobile menu button -->
                                <div class="main-menu">
                                    <div id="header_menu">
                                        <a href="/index">
                                            <img src="/portal/img/logo_black.svg" alt="" width="100" height="35">
                                        </a>
                                        <a href="#" class="open_close" id="close_in">
                                            <i class="ti-close"></i>
                                        </a>
                                    </div>
                                    <ul>
                                        <li>
                                            <a>房屋出租平台</a>
                                        </li>
                                    </ul>
                                </div>
                                <!--/main-menu -->
                            </nav>
                            <div class="col-xl-3 col-lg-2 d-lg-flex align-items-center justify-content-end text-right">
                                <a class="phone_top" href="tel://13111111111">
                                    <strong><span>联系电话</span>13111111111</strong>
                                </a>
                            </div>
                        </div>
                        <!-- /row -->
                    </div>
                </div>
                <!-- /main_header -->

                <div class="main_nav Sticky">
                    <div class="container">
                        <div class="row small-gutters">
                            <div class="col-xl-3 col-lg-3 col-md-3"></div>
                            <div class="col-xl-6 col-lg-7 col-md-6 d-none d-md-block">
                                <div class="custom-search-input" @keydown.enter="search">
                                    <input type="text" placeholder="请输入搜索内容" v-model.trim="keywords">
                                    <button type="submit" @click="search"><i class="header-icon_search_custom"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-2 col-md-3">
                                <ul class="top_tools">
                                    <li>
                                        <div class="dropdown dropdown-access">
                                            <a href="#" class="access_link"><span>Account</span></a>
                                            <div class="dropdown-menu">
                                                <a href="/login" class="btn_1">登录</a>
                                                <ul>
                                                    <li>
                                                        <a @click="edit = true"><i class="ti-user"></i>修改信息</a>
                                                    </li>
                                                    <li>
                                                        <a href="account.html"><i class="ti-package"></i>My Orders</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- /header -->
            <main>
                <div class="container margin_60_35">
                    <div class="main_title">
                    </div>
                    <div v-if="edit" class="row justify-content-center">
                        <div class="col-xl-6 col-lg-6 col-md-8">
                            <div class="box_account">
                                <h3 class="new_client">修改信息</h3>
                                <div class="form_container">
                                    <div class="form-group">
                                        <input autocomplete="off" placeholder="用户名" readonly="readonly" type="text" class="form-control"
                                               v-model="form.username">
                                    </div>
                                    <div class="form-group">
                                        <input autocomplete="off" placeholder="姓名" type="text" class="form-control"
                                               v-model="form.name">
                                    </div>
                                    <div class="form-group">
                                        <input autocomplete="off" placeholder="手机号" type="text" class="form-control"
                                               v-model="form.phone">
                                    </div>
                                    <div class="form-group">
                                        <input autocomplete="off" placeholder="邮箱" type="text" class="form-control"
                                               v-model="form.mail">
                                    </div>
                                    <div class="form-group">
                                        <label class="container_check">修改密码
                                            <input type="checkbox" v-model="editPwd">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                    <template v-if="editPwd">
                                        <div class="form-group">
                                            <input type="password" class="form-control" placeholder="旧密码"
                                                   v-model="pwd.password">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" placeholder="新密码"
                                                   v-model="pwd.newPassword">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" placeholder="确认新密码"
                                                   v-model="pwd.confirm">
                                        </div>
                                    </template>
                                    <div class="text-center"><input @click="submit" type="submit" value="保存"
                                                                    class="btn_1 full-width"></div>
                                </div>
                                <!-- /form_container -->
                            </div>
                            <!-- /box_account -->
                        </div>
                    </div>
                    <div v-else class="row small-gutters">
                        <div class="col-6 col-md-4 col-xl-3" v-for="houses in housesList">
                            <router-link class="grid_item" tag="div" :to="'house?id='+houses.id">
                                <figure>
                                    <template v-if="houses.img_src">
                                        <img class="img-fluid lazy" v-for="src in houses.img_src.split(',')" :src="src"
                                             :alt="houses.title">
                                    </template>
                                    <img v-else class="img-fluid lazy"
                                         src="/portal/img/products/product_placeholder_square_medium.jpg" alt="img">
                                </figure>
                                <h3>{{houses.title}}</h3>
                                <div class="price_box">
                                    <span class="new_price">￥{{houses.price}}</span>
                                </div>
                            </router-link>
                            <!-- /grid_item -->
                        </div>
                    </div>
                </div>
            </main>
            <footer class="revealed">
                <div class="container">
                    <div class="row add_bottom_25">
                        <div class="col-lg-6">
                            <ul class="footer-selector clearfix">
                                <li>
                                    123
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="additional_links">
                                <li><a href="#0">Terms and conditions</a></li>
                                <li><a href="#0">Privacy</a></li>
                                <li><span>© 2020 Allaia</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
            <!--/footer-->
        </div>
        <div id="toTop"></div><!-- Back to top button -->
    </div>
</template>
<script>
  module.exports = {
    data () {
      return {
        tableData: [],
        edit: false,
        editPwd: false,
        keywords: '',
        form: {
          username: '',
          name: '',
          phone: '',
          mail: ''
        },
        pwd: {
          password: '',
          newPassword: '',
          confirm: ''
        },
        housesList: [],
        userInfo: {}
      };
    },
    mounted () {
      // this.getUserList()
      this.getUserInfo();
      this.getHousesList();
    },
    methods: {
      search () {
        if (this.keywords) {
          axios.get('/api/getHousesListByTitle?title=' + this.keywords).then(({data: res}) => {
            if (res.success) {
              this.housesList = res.data;
            } else {
              this.housesList = [];
            }
          });
        }
      },
      submit () {
        for (let v of Object.values(this.form)) {
          if (v.trim() === '') {
            alert('请完整填写信息');
            return;
          }
        }
        let data = {
          ...this.form
        };
        if (this.editPwd) {
          for (let v of Object.values(this.pwd)) {
            if (v.trim() === '') {
              alert('请完整填写信息');
              return;
            }
          }
          if (this.pwd.confirm !== this.pwd.newPassword) {
            alert('两次密码不一致');
            return;
          }
          Object.assign(data, this.pwd);
        }
        axios.post('/saveUserInfo', data).then(({data: res}) => {
          if (res.success) {
            layer.msg(res.message, {icon: 6});
            setTimeout(() => {
              this.edit = false;
            }, 1000);
          } else {
            layer.msg(res.message, {icon: 5});
          }
        });
      },
      getHousesList () {
        axios.get('/api/getHousesList').then(({data: res}) => {
          if (res.success) {
            this.housesList = res.data;
          } else {
            this.housesList = [];
          }
        });
      },
      getUserInfo () {
        axios.get('/getUserInfo').then(({data: res}) => {
          if (res.success && res.data) {
            this.userInfo = res.data;
            for (let k of Object.keys(this.form)) {
              this.form[k] = res.data[k]
            }
          } else {
            this.userInfo = {};
          }
        });
      },
      remove (scope) {
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
              return;
            }
            _this.$message.success('操作成功');
          }).catch(function (error) {
            _this.getPaperList();
            console.log('请求出现错误:', error);
          });
        });
      },
      changeStatus (scope) {
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
              return;
            }
            _this.$message.success('操作成功');
          }).catch(function (error) {
            _this.getPaperList();
            console.log('请求出现错误:', error);
          });
        });
      },
      getUserList () {
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
      }
    }
  };
</script>
<style>


</style>
