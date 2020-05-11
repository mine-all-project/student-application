<template>
	<div>
		<div id="page">
			<page-header @search="search" :show="false"></page-header>
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
                housesList: []
            };
        },
        mounted() {
            this.getMyHousesList();
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
