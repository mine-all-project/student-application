<template>
	<div>
		<van-pull-refresh v-model="refreshing" @refresh="onRefresh">
			<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
				<van-cell v-for="item in dataList" :key="item.id" :title="item.name" @click="toPaperList(item.id)">
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
		activated() {},
		mounted() {

		},
		methods: {
			toPaperList(id) {
				this.$router.push({
					path: `/paper/list/${id}`,
					query: {
						id: id
					}
				})
			},
			back() {
				this.$router.back()
			},
			checkLogin() {

			},
			onLoad() {
				axios.get(`${api.baseApi}/api/paperType/list`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					if (result.data !== null) {
						this.dataList = result.data
					}
					this.loading = false
					this.finished = true
					this.refreshing = false
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			onRefresh() {
				this.finished = false
				this.loading = true
				this.onLoad();
			},
		}
	}
</script>
<style scoped>
</style>
