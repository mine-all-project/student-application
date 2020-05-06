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
								<div class="custom-search-input">
									<input type="text" placeholder="请输入搜索内容">
									<button type="submit"><i class="header-icon_search_custom"></i></button>
								</div>
							</div>
							<div class="col-xl-3 col-lg-2 col-md-3">
								<ul class="top_tools">
									<li>
										<div class="dropdown dropdown-access">
											<a href="account.html" class="access_link"><span>Account</span></a>
										</div></li>
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
						<div class="col-6 col-md-4 col-xl-3">
							<div class="grid_item">
								<figure>
									<a href="product-detail-1.html">
										<img class="img-fluid lazy" src="/portal/img/products/product_placeholder_square_medium.jpg"
										     data-src="/portal/img/products/shoes/1.jpg" alt="">
										<img class="img-fluid lazy" src="/portal/img/products/product_placeholder_square_medium.jpg"
										     data-src="/portal/img/products/shoes/1_b.jpg" alt="">
									</a>
								</figure>
								<a href="product-detail-1.html">
									<h3>这是一个房屋</h3>
								</a>
								<div class="price_box">
									<span class="new_price">￥48.00</span>
								</div>
							</div>
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
                tableData: [],
            };
        },
        mounted() {
            // this.getUserList()
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
