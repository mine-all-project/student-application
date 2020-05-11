<template>
	<div>
		<div id="page">
			<page-header @search="search" :show="false"></page-header>
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
			<page-footer></page-footer>
			<!--/footer-->
		</div>
		<div id="toTop"></div><!-- Back to top button -->
	</div>
</template>
<script>
    module.exports = {
        components: {
            'page-header': httpVueLoader('/vue/portal/page-header.vue'),
            'page-footer': httpVueLoader('/vue/portal/page-footer.vue'),
        },
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
            search(keywords) {
                if (keywords) {
                    axios.get('/api/getHousesListByTitle?title=' + keywords).then(({data: res}) => {
                        if (res.success) {
                            this.housesList = res.data;
                        } else {
                            this.housesList = [];
                        }
                    });
                } else {
                    this.getHousesList()
                }
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
