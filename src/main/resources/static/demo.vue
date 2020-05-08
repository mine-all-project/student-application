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
												<a v-if="!userInfo.username" href="/login" class="btn_1">登录</a>
												<template v-else>
													<a href="/loginOut" class="btn_1">退出登录</a>
													<ul>
														<li>
															<a @click="editInfo"><i class="ti-user"></i>用户资料管理</a>
														</li>
														<li>
															<a @click="publish"><i class="ti-package"></i>发布房源</a>
														</li>
														<li>
															<a @click="myHouse"><i class="ti-package"></i>我的房源</a>
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
					<div v-if="type === 1" class="row justify-content-center">
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
					<div v-else-if="type === 2" class="row justify-content-center">
						<div class="col-xl-6 col-lg-6 col-md-8">
							<div class="box_account">
								<h3 class="new_client">{{houseForm.title?'修改':'发布'}}房源</h3>
								<div class="form_container">
									<div class="form-group">
										<input autocomplete="off" placeholder="标题" type="text" class="form-control"
										       v-model="houseForm.title">
									</div>
									<div class="form-group">
										<input autocomplete="off" placeholder="备注" type="text" class="form-control"
										       v-model="houseForm.note">
									</div>
									<div class="form-group">
										<input autocomplete="off" placeholder="地址" type="text" class="form-control"
										       v-model="houseForm.address">
									</div>
									<div class="form-group">
										<input autocomplete="off" placeholder="价格" type="number" class="form-control"
										       v-model="houseForm.price">
									</div>
									<div class="form-group img-box">
										<div class="row">
											<template v-if="houseForm.img_src">
												<div class="col-md-3 item"
												     v-for="(img, index) in houseForm.img_src.split(',')">
													<img :src="img" alt="img">
													<i @click="removeImg(index)">X</i>
												</div>
											</template>
											<div class="col-md-3 item">
												<div class="btn-upload">点击上传图片</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div id="editor" style="width: 100%;"></div>
										<!--										<input autocomplete="off" placeholder="合同" type="text" class="form-control"-->
										<!--										       v-model="houseForm.contract">-->
									</div>
									<div class="text-center">
										<input @click="submitHouseForm" type="submit" value="发布" class="btn_1 full-width">
									</div>
								</div>
								<!-- /form_container -->
							</div>
							<!-- /box_account -->
						</div>
					</div>
					<div v-else class="row small-gutters">
						<div class="col-6 col-md-4 col-xl-3" v-for="houses in housesList">
							<router-link :to="'house?id='+houses.id">
								<div class="grid_item house-item">
									<figure>
										<template v-if="houses.img_src">
											<img class="img-fluid lazy" v-for="src in houses.img_src.split(',').slice(0, 2)" :src="src"
												 :alt="houses.title">
										</template>
										<img v-else class="img-fluid lazy"
											 src="/portal/img/products/product_placeholder_square_medium.jpg" alt="img">
									</figure>
									<h3>{{houses.title}}</h3>
									<div class="price_box">
										<span class="new_price">￥{{houses.price}}</span>
									</div>
									<ul v-show="showMyHouse">
										<li><a @click.prevent="removeHouses(houses.id)"><i class="ti-trash"></i><span>删除</span></a></li>
										<li><a @click.prevent="editHouses(houses)"><i class="ti-pencil"></i><span>修改</span></a></li>
									</ul>
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
        data() {
            return {
                wangEditorOptions: [
                    'head',  // 标题
                    'bold',  // 粗体
                    'fontSize',  // 字号
                    'fontName',  // 字体
                    'italic',  // 斜体
                    'underline',  // 下划线
                    'strikeThrough',  // 删除线
                    'justify',  // 对齐方式
                    'image',  // 插入图片
                ],
				showMyHouse: false,
                editor: null,
				uploadInst: null,
                tableData: [],
                type: 0, //0信息展示 1用户资料管理 2发布房源
                editPwd: false,
                keywords: '',
                form: {
                    username: '',
                    name: '',
                    phone: '',
                    mail: ''
                },
                houseForm: {
                    address: '',
                    contract: '',
                    img_src: '',
                    note: '',
                    price: '',
                    title: '',
                    user_id: ''
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
        mounted() {
            // this.getUserList()
            this.getUserInfo();
            this.getHousesList();
        },
        methods: {
			editHouses (house) {
				this.houseForm = house;
				this.type = 2;

				if (!this.uploadInst) {
					this.$nextTick(this.initUpload)
				}

				if (!this.editor) {
					this.$nextTick(this.initEditor)
				}
			},
			removeHouses (id) {
				layer.confirm('确定删除该房源吗？', {icon: 3, title:'删除确认'}, index => {
					layer.close(index);
					axios.delete(`/api/removeHousesById/${id}`).then(({data: res}) => {
						if (res.success) {
							layer.msg(res.message, {icon: 6});
							this.getMyHousesList();
						} else {
							layer.msg(res.message, {icon: 5});
						}
					})
				});
			},
			myHouse () {
				this.getMyHousesList()
			},
			initUpload () {
				this.uploadInst = layui.upload.render({
					elem: '.btn-upload' //绑定元素
					, url: '/api/uploadFile' //上传接口
					, acceptMime: 'image/*'
					, done: res => {
						//上传完毕回调
						if (res.success) {
							let src = this.houseForm.img_src;
							this.houseForm.img_src = src ? `${src},${res.data.url}` : res.data.url;
						}
					}
					, error: function () {
						//请求异常回调
						console.error('上传失败');
					}
				});
			},
            initEditor() {
                this.editor = new window.wangEditor('#editor');
                this.editor.customConfig.uploadImgShowBase64 = true;
                this.editor.customConfig.showLinkImg = false;
                this.editor.customConfig.pasteIgnoreImg = true;
                this.editor.customConfig.menus = this.wangEditorOptions;
                this.editor.create();
                this.editor.txt.html(this.houseForm.contract);
            },
            removeImg(index) {
                let arr = this.houseForm.img_src.split(',');
                arr.splice(index, 1);
                this.houseForm.img_src = arr.join(',');
            },
            publish() {
                if (!this.userInfo.id) {
                    layer.alert('请先登录再操作', {icon: 0}, function (index) {
                        location = '/login';
                        layer.close(index);
                    });
                } else {
                    let house = {
                        address: '',
                        contract: '',
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
                this.type = 1;
            },
            search() {
                if (this.keywords) {
					this.type = 0;
					this.showMyHouse = false;
                    axios.get('/api/getHousesListByTitle?title=' + this.keywords).then(({data: res}) => {
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
                for (let v of Object.values(form)) {
                    if (v.trim() === '') {
                        alert('请完整填写信息');
                        return false;
                    }
                }
                return true;
            },
            submitHouseForm() {
                this.houseForm.contract = this.editor.txt.html();
                if (this.checkForm(this.houseForm)) {
                    axios.post('/api/saveHousesInfo', this.houseForm).then(({data: res}) => {
                        if (res.success) {
                            this.getHousesList();
                            layer.msg(res.message, {icon: 6});
                            setTimeout(() => {
                                this.type = 0;
                            }, 1000);
                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    });
                }
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
                                this.type = 0;
                            }, 1000);
                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    });
                }
            },
			getMyHousesList() {
				this.type = 0;
				this.showMyHouse = true;
				axios.get('/api/getHousesByUser').then(({data: res}) => {
					if (res.success) {
						this.housesList = res.data;
					} else {
						this.housesList = [];
					}
				});
			},
            getHousesList() {
				this.type = 0;
				this.showMyHouse = false;
                axios.get('/api/getHousesList').then(({data: res}) => {
                    if (res.success) {
                        this.housesList = res.data;
                    } else {
                        this.housesList = [];
                    }
                });
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
                            return;
                        }
                        _this.$message.success('操作成功');
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
                            return;
                        }
                        _this.$message.success('操作成功');
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
            }
        }
    };
</script>
<style>
	.img-box .item {
		position: relative;
		margin-bottom: 15px;
		min-height: 100px;
	}

	.img-box .item .layui-upload-file {
		display: none !important;
		opacity: .01;
		filter: Alpha(opacity=1);
	}

	.img-box .item .btn-upload {
		height: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background: lightgrey;
		border-radius: 8px;
	}

	.img-box .item .btn-upload:hover {
		font-weight: bold;
		font-size: larger;
	}

	.img-box .item img {
		width: 100%;
		border-radius: 8px;
	}

	.img-box .item i {
		position: absolute;
		top: 0;
		right: 15px;
		width: 18px;
		height: 18px;
		border-radius: 100px;
		border: 1px solid black;
		background: white;
		text-align: center;
		cursor: pointer;
	}

	.img-box .item i:hover {
		color: white;
		background: #004dda;
	}

</style>
