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
											<a class="access_link"><span>Account</span></a>
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
					<div class="row small-gutters">
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
										<li>
											<router-link :to="{path:'/edit-house',query:{id:houses.id}}">
												<i class="ti-pencil"></i>
												<span>修改</span>
											</router-link>
										</li>
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
                showMyHouse: true,
                editor: null,
                uploadInst: null,
                tableData: [],
                keywords: '',
                housesList: [],
                userInfo: {}
            };
        },
        mounted() {
            // this.getUserList()
            // this.getUserInfo();
            // this.getHousesList();
            this.getMyHousesList()
        },
        methods: {
            removeHouses(id) {
                layer.confirm('确定删除该房源吗？', {icon: 3, title: '删除确认'}, index => {
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
            getMyHousesList() {
                this.showMyHouse = true;
                axios.get('/api/getHousesByUser').then(({data: res}) => {
                    if (res.success) {
                        this.housesList = res.data;
                    } else {
                        this.housesList = [];
                    }
                });
            },
        }
    };
</script>
<style>

</style>
