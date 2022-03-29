<template>
	<div>
		<br>
		<van-cell title="用户名" :value="userInfo.username"></van-cell>
		<van-cell is-link title="我收藏的文章" to="/person/paper"></van-cell>
		<van-cell is-link title="我收藏的商品" to="/person/goods"></van-cell>
		<van-cell is-link title="我的订单" to="/person/order"></van-cell>
		<van-cell is-link title="修改密码" to="/person/password"></van-cell>
		<div style="display: flex;justify-content: center;">
			<van-button style="width: 80%;" color="rgb(25,137,250)" @click="logout">退出登录</van-button>
		</div>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				userInfo: {
					username: ''
				}
			}
		},
		activated() {
			axios.get(`${api.baseApi}/api/user/getUserInfo`).then(response => {
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
					this.userInfo = result.data
				}
			}).catch(function(error) {
				console.error('出现错误:', error);
			})
		},
		mounted() {

		},
		methods: {
			logout() {
				sessionStorage.setItem('token','')
				this.$router.push({path:'/person/login'})
			},
		}
	}
</script>
<style scoped>
</style>
