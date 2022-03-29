<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()"> </van-nav-bar>
		<van-cell title="下单时间">{{orderInfo.createTime}}</van-cell>
		<van-cell title="订单金额">￥{{orderInfo.price}}</van-cell>
		<van-card v-for="(item,index) in goodsList" :price="item.price" :title="item.name" :desc="item.description"
			:thumb="fileList[index]" :key="item.id">
		</van-card>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				goodsList: [],
				orderInfo: {},
				fileList: [],
				prices: 0
			}
		},
		activated() {
			this.getOrderInfo()
		},
		mounted() {

		},
		methods: {
			getOrderInfo() {
				let id = this.$route.query.id
				console.log(id)
				const url = `${api.baseApi}/api/order/get/id/${id}`
				axios.get(url).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message);
						return;
					}
					this.orderInfo = result.data
					this.getGoodsList(result.data.goodsIds)
					console.log(result.data)
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			getGoodsList(ids) {
				console.log(ids)
				const url = `${api.baseApi}/api/goods/ids/${ids}`
				axios.get(url).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message);
						return;
					}
					this.goodsList = result.data
					this.goodsList.forEach((e, index) => {
						this.getFileById(e.fileInfoId)
					})
					console.log(result.data)
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
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
