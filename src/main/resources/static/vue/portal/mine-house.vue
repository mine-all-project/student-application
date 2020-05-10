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
			</header>
			<!-- /header -->

			<main class="bg_gray">
				<div class="container margin_30">
					<div class="page_header">
						<h1>{{houseInfo.title}}</h1>
					</div>
					<!-- /page_header -->
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="owl-carousel owl-theme prod_pics magnific-gallery owl-loaded owl-drag">
								<div class="owl-stage-outer">
									<div class="owl-stage">
										<div class="owl-item" v-for="src in houseInfo.img_src.split(',')">
											<div class="item">
												<a title="Photo title" data-effect="mfp-zoom-in">
													<img :src="src" alt="">
												</a>
											</div>
										</div>
									</div>
								</div>
								<!-- /item -->
							</div>
							<!-- /carousel -->
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->

				<div class="bg_white">
					<div class="container margin_60_35">
						<div class="row justify-content-between">
							<div class="col-lg-6">
								<div class="prod_info version_2">
									<p><small>{{houseInfo.note}}</small><br></p>
								</div>
							</div>
							<div class="col-lg-5">
								<div class="prod_options version_2">
									<div class="row">
										<label class="col-xl-7 col-lg-5  col-md-6 col-6 pt-0"><strong>地址</strong></label>
										<div class="col-xl-5 col-lg-5 col-md-6 col-6 colors">
											<p>{{houseInfo.address}}</p>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-lg-7 col-md-6">
											<div class="price_main">
												<span class="new_price">¥{{houseInfo.price.toFixed(2)}}</span>
											</div>
										</div>
										<div class="col-lg-5 col-md-6">
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /row -->
					</div>
				</div>
				<!-- /bg_white -->

				<div class="tabs_product bg_white version_2">
					<div class="container">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item">
								<a id="tab-B" href="#pane-B" data-toggle="tab" role="tab" class="nav-link active" aria-selected="true">留言</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- /tabs_product -->

				<div class="tab_content_wrapper">
					<div class="container">
						<div class="tab-content" role="tablist">
							<div id="pane-B" class="card tab-pane fade active show" role="tabpanel" aria-labelledby="tab-B">
								<div class="card-header" role="tab" id="heading-B">
									<h5 class="mb-0">
										<a class="collapsed" data-toggle="collapse" href="#collapse-B" aria-expanded="false"
										   aria-controls="collapse-B">
											Reviews
										</a>
									</h5>
								</div>
								<div id="collapse-B" class="collapse" role="tabpanel" aria-labelledby="heading-B">
									<div class="card-body">
										<div class="row justify-content-between">
											<div class="col-lg-5" v-for="message in houseInfo.messages">
												<div class="review_content">
													<div class="clearfix add_bottom_10">
														<span class="rating">{{message.user.name}}</span>
														<em>{{message.createTime}}</em>
													</div>
													<p>{{message.content}}</p>
												</div>
											</div>
										</div>
										<!-- /row -->
										<p class="text-right"><a href="leave-review.html" class="btn_1">留言</a></p>
									</div>
									<!-- /card-body -->
								</div>

							</div>
							<!-- /tab B -->
						</div>
						<!-- /tab-content -->
					</div>
					<!-- /container -->
				</div>
				<!-- /tab_content_wrapper -->

			</main>
			<!-- /main -->
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
                houseInfo: {}
            };
        },
        mounted() {
            this.getHouseInfo();
        },
        methods: {
            getHouseInfo() {
                let msgIndex = layer.msg('加载中', {
                    icon: 16
                    , shade: 0.8
                    , time: 0
                });
                axios.get(`/api/getHousesById?id=${this.$route.query.id}`).then(({data: res}) => {
                    layer.close(msgIndex)
                    if (res.success) {
                        this.houseInfo = res.data;
                    } else {
                        this.housesList = {};
                    }
                });
            }
        }
    };
</script>
<style>
</style>
