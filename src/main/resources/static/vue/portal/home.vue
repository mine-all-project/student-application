<template>
	<div>
		<div id="page">
			<page-header @search="search"></page-header>
			<main>
				<div class="container margin_60_35">
					<div class="main_title">
					</div>
					<div class="row small-gutters">
						<div class="col-6 col-md-4 col-xl-3" v-for="houses in housesList">
							<router-link :to="'house-info?id='+houses.id">
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
								</div>
							</router-link>
							<!-- /grid_item -->
						</div>
					</div>
				</div>
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
                housesList: [],
            };
        },
        mounted() {
            this.getHousesList();
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
            getHousesList() {
                axios.get('/api/getHousesList').then(({data: res}) => {
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
