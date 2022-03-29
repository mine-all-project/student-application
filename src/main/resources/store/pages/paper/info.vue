<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()" right-text="评论" @click-right="saveMessage()">
		</van-nav-bar>
		<van-cell title="标题" :value="goodsInfo.title"></van-cell>
		<van-cell title="分类" :value="goodsInfo.type_name"></van-cell>
		<div v-html="goodsInfo.content" class="goodsInfo container"> </div>
		<van-field v-model="message" center placeholder="请输入评论" class="message"></van-field>
		<hr/>
		<van-cell title="评论"></van-cell>
		<van-cell :title="item.message" v-for="item in messageList"></van-cell>
	</div>
</template>
<script>
	;
	module.exports = {
		data() {
			return {
				goodsInfo: {
					title: '',
					type_name: '',
					content: '',
				},
				message: '',
				messageList: []
			}
		},
		activated() {
			this.getPaperInfo()
			this.getMessageList()
		},
		mounted() {

		},
		methods: {
			getMessageList() {
				axios.get(`${api.baseApi}/api/message/get/paperId/${this.$route.query.id}`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					if (result.data !== null) {
						this.messageList = result.data
					}
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			getPaperInfo() {
				axios.get(`${api.baseApi}/api/paper/id/${this.$route.query.id}`).then(response => {
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
			saveMessage() {
				let data = {
					message: this.message,
					paperId: this.$route.query.id
				}
				axios.post(`${api.baseApi}/api/message/add`, data).then(response => {
					const result = response.data
					if (result.status !== 200) {
						return;
					}
					window.vant.Toast(result.message)
					this.getMessageList()
					this.message = ''
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
		border: 1px solid red;
	}

	.message {
		position: absolute;
		bottom: 7vh;
		border: 1px solid #f1f1f1;
	}
</style>
