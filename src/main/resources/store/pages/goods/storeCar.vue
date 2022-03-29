<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()" @click-right="toStoreCar">
			<template #right>
				<van-icon name="cart-o" color="#1989fa" size="1.5rem"></van-icon>
			</template>
		</van-nav-bar>
		<van-card v-for="(item,index) in dataList" :price="item.price" :title="item.name" :desc="item.description"
			:thumb="fileList[index]" :key="item.id">
			<template #footer>
				<van-button size="mini" @click="deleteStoreCar(item)">移除</van-button>
			</template>
		</van-card>
		<van-submit-bar :price="prices" label="" button-text="立即购买" @submit="saveOrder" class="submitOrder">
		</van-submit-bar>
	</div>
</template>
<script>
	
	module.exports = {
		data() {
			return {
				dataList: [],
				fileList: [],
				prices: 0
			}
		},
		activated() {
			this.getDataList()
		},
		mounted() {

		},
		methods: {
			getDataList() {
				const url = `${api.baseApi}/api/storeCar/get`
				axios.get(url).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
					}
					let prices = 0
					this.dataList = result.data
					this.dataList.forEach((e, index) => {
						let price = parseFloat(e.price)
						prices += price * 100
						this.getFileById(e.fileInfoId)
					})
					this.prices = parseFloat(prices)
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
			getFileById(id) {
				axios.get(`${api.baseApi}/api/getFileById/${id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
						window.vant.Toast(result.message);
					}
					if (result.data !== null) {
						this.fileList.push(`${api.baseApi}${result.data.virtualPath}`)
					}
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			saveOrder() {
				if (this.dataList.length <= 0) {
					window.vant.Toast('购物车是空的哦');
					return;
				}
				axios.post(`${api.baseApi}/api/order/add`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message);
						return;
					}
					window.vant.Toast(result.message);
					this.getDataList()
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			deleteStoreCar(item) {
				axios.post(`${api.baseApi}/api/storeCar/delete/${item.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
						window.vant.Toast(result.message);
					}
					this.getDataList()
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			back() {
				this.$router.back()
				window.vant.Toast('back()');
			},
		}
	}
</script>
<style scoped>
	.goodsInfo {
		border: none
	}

	.submitOrder {
		position: absolute;
		bottom: 7vh;
		border: 1px solid #f1f1f1;
	}
</style>
