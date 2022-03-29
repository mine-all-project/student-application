<template>
	<div>
		<br>
			<van-field v-model="form.username" label="用户名" placeholder="请输入用户名"></van-field>
			<van-field v-model="form.password" label="密码" placeholder="请输入密码"></van-field>
			<van-field v-model="form.repassword" label="重复密码" placeholder="请输入重复密码"></van-field>
			<br>
			<div style="display: flex;justify-content: center;">
				<van-button style="width: 80%;" color="rgb(25,137,250)" @click="register">注册</van-button>
			</div>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				form: {
					username: '',
					password: '',
					repassword: '',
				}
			}
		},
		activated() {
			// window.getUserInfo().then(result => {
			// 	console.log(result)
			// 	this.userInfo = result
			// })
		},
		mounted() {

		},
		methods: {
			register() {
				axios.post(`${api.baseApi}/api/registry`, this.form).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					sessionStorage.setItem('token', result.data)
					this.$router.back()
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
		}
	}
</script>
<style scoped>
</style>
