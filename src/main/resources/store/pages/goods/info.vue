<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()" @click-right="toStoreCar()">
			<template #right>
				<van-icon name="cart-o" color="#1989fa" size="1.5rem"></van-icon>
			</template>
		</van-nav-bar>
		<van-cell title="名称" :value="goodsInfo.name"></van-cell>
		<van-cell title="售价" :value="goodsInfo.price"></van-cell>
		<van-cell title="简介" :value="goodsInfo.description"></van-cell>
		<div v-html="goodsInfo.content" class="goodsInfo container"> </div>
		<van-submit-bar label=" " button-text="加入购物车" @submit="saveStoreCar" class="submitOrder">
		</van-submit-bar>
	</div>
</template>
<script>
	;
	module.exports = {
		data() {
			return {
				goodsInfo: {
					title: '',
					price: '999',
					type_name: '',
					content: '',
					description: '',
				},
				message: '',
			}
		},
		activated() {
			this.loadGoods()
		},
		mounted() {

		},
		methods: {
			loadGoods() {
				axios.get(`${api.baseApi}/api/goods/id/${this.$route.query.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					if (result.data !== null) {
						this.goodsInfo = result.data
					}
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			saveStoreCar() {
				const url = `${api.baseApi}/api/storeCar/add/${this.$route.query.id}`
				axios.post(url).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					window.vant.Toast(result.message);
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			toStoreCar() {
				this.$router.push({
					path: '/goods/storeCar'
				})
			},
			checkLogin() {

			},
			back() {
				this.$router.back()
				window.vant.Toast('back()');
			},
		}
	}
</script>
<style scoped>
	.container {
		max-width: 98% !important;
	}
	img {
		max-width: 90% !important;
	}

	.goodsInfo {
		width: 100%;
		border: none;
		overflow: hidden;
	}

	.submitOrder {
		position: absolute;
		bottom: 7vh;
		border: 1px solid #f1f1f1;
		max-width: 99% !important;
	}
</style>
