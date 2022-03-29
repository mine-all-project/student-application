<template>
	<div>
		<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
			<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
				<van-swipe-cell v-for="item in dataList" :key="item.id">
					<van-cell center :title="item.name" @click="toGoodsInfo(item.id)" :label="`￥${item.price}`">
						<template #right-icon>
							<van-rate :value="item.favorite" icon="like" void-icon="like-o" :count="1"></van-rate>
						</template>
					</van-cell>
					<template #right>
						<van-button square type="danger" text="取消收藏" @click="delFavorite(item)"></van-button>
						<van-button square type="primary" text="收藏" @click="addFavorite(item)"></van-button>
					</template>
				</van-swipe-cell>
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
			getFavorite(dataList) {
				axios.get(`${api.baseApi}/api/favorite/get`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						if (response.data.status === 401) {
							this.$router.push({
								path: '/person/login',
								query: {}
							})
						}
						return;
					}
					if(result.data.goodsIds){
						const goodsIds = result.data.goodsIds.split(',')
						dataList.forEach(e => {
							goodsIds.forEach(r => {
								if (e.id === r)
									e.favorite = 1
							})
						})
					}
					this.dataList = dataList
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			addFavorite(item) {
				axios.post(`${api.baseApi}/api/favorite/goods/add/${item.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
					}
					this.onLoad()
					window.vant.Toast(result.message)
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			delFavorite(item) {
				axios.post(`${api.baseApi}/api/favorite/goods/delete/${item.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
					}
					this.onLoad()
					window.vant.Toast(result.message)
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			toGoodsInfo(id) {
				this.$router.push({
					path: `/goods/info/${id}`,
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
						if (response.data.status === 401) {
							this.$router.push({
								path: '/person/login',
								query: {}
							})
						}
						return;
					}
					if (result.data !== null) {
						this.getFavorite(result.data)
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
