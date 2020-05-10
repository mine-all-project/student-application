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
										<img src="/portal/img/logo.svg" width="100" height="35">
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
							<div class="col-xl-6 col-lg-7 col-md-6 d-none d-md-block"></div>
							<div class="col-xl-3 col-lg-2 col-md-3">
								<ul class="top_tools">
									<li>
										<div class="dropdown dropdown-access">
											<a href="#" class="access_link"><span>Account</span></a>
											<div class="dropdown-menu">
												<a v-if="!userInfo.username" href="/login" class="btn_1">登录</a>
												<template v-else>
													<a href="/loginOut" class="btn_1">退出登录</a>
													<ul>
														<li>
															<router-link to="/user-info"><i class="ti-package"></i>个人信息</router-link>
														</li>
														<li>
															<router-link to="/publish-house"><i class="ti-package"></i>发布房源</router-link>
														</li>
														<li>
															<router-link to="/mine-house"><i class="ti-package"></i>我的房源</router-link>
														</li>
													</ul>
												</template>
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
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-6 col-md-8">
							<div class="box_account">
								<h3 class="new_client">用户资料管理</h3>
								<div class="form_container">
									<div class="form-group">
										<input autocomplete="off" placeholder="用户名" readonly="readonly" type="text"
										       class="form-control"
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
        data() {
            return {
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
                userInfo: {}
            };
        },
        mounted() {
            this.getUserInfo();
        },
        methods: {
            publish() {
                if (!this.userInfo.id) {
                    layer.alert('请先登录再操作', {icon: 0}, function (index) {
                        location = '/login';
                        layer.close(index);
                    });
                } else {
                    let house = {
                        address: '',
                        contracts: '',
                        img_src: '',
                        note: '',
                        price: '',
                        title: '',
                        user_id: this.userInfo.id
                    };
                    this.editHouses(house);
                }
            },
            editInfo() {
                this.pwd = {
                    password: '',
                    newPassword: '',
                    confirm: ''
                };
            },
            checkForm(form) {
                for (let value of Object.values(form)) {
                    if (value === '') {
                        alert('请完整填写信息');
                        return false;
                    }
                }
                return true;
            },
            submit() {
                if (this.checkForm(this.form)) {
                    let data = {
                        ...this.form
                    };
                    if (this.editPwd && this.checkForm(this.pwd)) {
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
                                this.editPwd = false
                            }, 1000);
                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    });
                }
            },
            getUserInfo() {
                axios.get('/getUserInfo').then(({data: res}) => {
                    if (res.success && res.data) {
                        this.userInfo = res.data;
                        for (let k of Object.keys(this.form)) {
                            this.form[k] = res.data[k];
                        }
                    } else {
                        this.userInfo = {};
                    }
                });
            },
        }
    };
</script>
<style>
</style>
