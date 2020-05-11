<template>
	<div>
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
									<li><a>房屋出租平台</a></li>
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
							<div class="custom-search-input"
							     @keydown.enter="()=>{this.$emit('search',this.keywords)}"
							     v-if="show">
								<input type="text" placeholder="请输入搜索内容" v-model.trim="keywords">
								<button type="submit" @click="()=>{this.$emit('search',this.keywords)}"><i
										class="header-icon_search_custom"></i>
								</button>
							</div>
						</div>

						<div class="col-xl-3 col-lg-2 col-md-3">
							<ul class="top_tools">
								<li>
									<div class="dropdown dropdown-access">
										<a class="access_link" @click="clickUser"><span>Account</span></a>
										<div class="dropdown-menu" :style="{display:style.display}">
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
	</div>
</template>
<script>
    module.exports = {
        name: 'page-header',
        props: {
            search: {
                type: Function
            },
            show: {
                type: Boolean,
                default: true
            },
        },
        data() {
            return {
                keywords: '',
                userInfo: {},
                style: {
                    display: 'none',
                    show: false
                }
            };
        },
        mounted() {
            this.getUserInfo();
        },
        methods: {
            clickUser() {
                this.style.show = !this.style.show
                this.style.display = this.style.show ? 'block' : 'none'
            },
            getUserInfo() {
                axios.get('/getUserInfo').then(({data: res}) => {
                    if (res.success && res.data) {
                        this.userInfo = res.data;
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

