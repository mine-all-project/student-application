<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()"> </van-nav-bar>
		<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
			<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
				<van-swipe-cell v-for="item in dataList" :key="item.id" v-if="false">
					<van-cell center :title=`订单编号:${item.id.substring(0,8)}`
					 @click="toGoodsInfo(item.id)" :value="`订单金额￥${item.price}`">
					</van-cell>
					<template #right>
						<van-button square type="danger" text="取消收藏" @click="delFavorite(item)"></van-button>
						<van-button square type="primary" text="收藏" @click="addFavorite(item)"></van-button>
					</template>
				</van-swipe-cell>
					<van-cell center :title=`订单编号:${item.id.substring(0,8)}`
					 v-for="item in dataList"  :key="item.id"
					 @click="toOrderInfo(item.id)" :value="`订单金额￥${item.price}`">
					</van-cell>
			</van-list>
		</van-pull-refresh>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				dataList: [],
				loading: false,
				finished: false,
				refreshing: false,
			}
		},
		activated() {
			this.onLoad()
		},
		mounted() {

		},
		methods: {
			getOrders(dataList) {
				axios.get(`${api.baseApi}/api/order/get`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					this.dataList = result.data
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			toOrderInfo(id) {
				this.$router.push({
					path: `/person/order/info/${id}`,
					query: {
						id: id
					}
				})
			},
			back() {
				this.$router.back()
			},
			onLoad() {
				axios.get(`${api.baseApi}/api/goods/list`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					if (result.data !== null) {
						this.getOrders(result.data)
					}
					this.loading = false
					this.finished = true
					this.refreshing = false
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			onRefresh() {
				this.finished = false;
				this.loading = true;
				this.onLoad();
			},
		}
	}
</script>
<style scoped>
</style>
