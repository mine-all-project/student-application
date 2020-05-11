<template>
	<div>
		<div id="page">
			<page-header @search="search" :show="false"></page-header>
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
										<template v-if="houseInfo.img_src">
											<div class="owl-item" v-for="src in houseInfo.img_src.split(',')">
												<div class="item">
													<a title="Photo title" data-effect="mfp-zoom-in">
														<img :src="src" alt="">
													</a>
												</div>
											</div>
										</template>
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
												<span class="new_price" v-if="houseInfo.price">¥{{houseInfo.price.toFixed(2)}}</span>
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
								<a id="tab-B" href="#pane-B" data-toggle="tab" role="tab" class="nav-link active" aria-selected="true">留言区</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- /tabs_product -->

				<div class="tab_content_wrapper">
					<div v-if="showForm" class="container">
						<div class="row justify-content-center">
							<div class="col-xl-6 col-lg-6 col-md-8 add_bottom_25">
								<h4 class="pb-3">填写留言</h4>
								<div class="form-group">
									<textarea v-model.trim="form.content" class="form-control" style="height: 250px;" placeholder="请填写留言"></textarea>
								</div>
								<div class="form-group">
									<input @click="submit" class="btn_1 full-width" type="submit" value="提交">
								</div>
							</div>
						</div>
						<!-- /row -->
					</div>
					<div v-else class="container">
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
											<div class="col-lg-5" style="margin-bottom: 20px;" v-for="message in houseInfo.messages">
												<div class="review_content">
													<div class="clearfix add_bottom_10">
														<span class="rating">{{message.user.name}}</span>
														<em>{{message.createTime}}</em>
													</div>
													<p>{{message.content}}</p>
													<div style="text-align: right; font-size: 0.7rem; color: #aaa;" v-if="message.children.length>0">
														<template v-for="msg in message.children">
															<span>管理员回复({{msg.createTime}})</span>
															<p style="margin-bottom: 0;">{{msg.content}}</p>
														</template>
													</div>
												</div>
											</div>
										</div>
										<!-- /row -->
										<p class="text-right"><a @click="leaveMessage" class="btn_1">留言</a></p>
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
			<page-footer></page-footer>
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
            	form: {
					houses_id: '',
					content: ''
				},
				showForm: false,
				userInfo: {},
                houseInfo: {}
            };
        },
        mounted() {
        	this.getUserInfo();

			let msgIndex = layer.msg('加载中', {
				icon: 16
				, shade: 0.8
				, time: 0
			});
            this.getHouseInfo(msgIndex);
        },
        methods: {
        	submit () {
				if (!this.form.content) {
					layer.msg('请填写留言！', {icon: 3});
					return;
				}
				axios.post('/api/saveLeaveMessage', this.form).then(({data: res}) => {
					if (res.success) {
						this.getHouseInfo();
						layer.msg(res.message, {icon: 6});
						setTimeout(() => {
							this.showForm = false;
						}, 1000);
					} else {
						layer.msg(res.message, {icon: 5});
					}
				});
			},
			leaveMessage() {
				if (!this.userInfo.id) {
					layer.alert('请先登录再操作', {icon: 0}, function (index) {
						location = '/login';
						layer.close(index);
					});
				} else {
					this.form = {
						houses_id: this.$route.query.id,
						content: ''
					};
					this.showForm = true;
				}
			},
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
			getUserInfo() {
				axios.get('/getUserInfo').then(({data: res}) => {
					if (res.success && res.data) {
						this.userInfo = res.data;
					} else {
						this.userInfo = {};
					}
				});
			},
            getHouseInfo(msgIndex) {
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
