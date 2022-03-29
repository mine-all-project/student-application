<template>
	<div>
		<van-nav-bar left-text=" " left-arrow @click-left="back()"> </van-nav-bar>
		<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
			<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
				<van-swipe-cell v-for="item in dataList" :key="item.id">
					<van-cell :title="item.title" @click="toPaperInfo(item.id)">
						<van-rate :value="item.favorite" icon="like" void-icon="like-o" :count="1"></van-rate>
					</van-cell>
					<template #right>
						<van-button square type="danger" text="取消收藏" @click="delFavorite(item)"></van-button>
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
				axios.get(`${api.baseApi}/api/favorite/paper/get`).then(response => {
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
		delFavorite(item) {
				axios.post(`${api.baseApi}/api/favorite/paper/delete/${item.id}`).then(response => {
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
			toPaperInfo(id) {
				this.$router.push({
					path: `/paper/info/${id}`,
					query: {
						id: id
					}
				})
			},
			back() {
				this.$router.back()
			},
			addPaper() {
				window.getUserInfo()
				this.$router.push(`/paper/add/`)
			},
			onLoad() {
				axios.get(`${api.baseApi}/api/paper/list/${this.$route.query.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
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
