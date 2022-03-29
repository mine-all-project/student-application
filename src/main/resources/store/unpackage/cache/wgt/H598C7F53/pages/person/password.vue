<template>
	<div>
		<van-nav-bar left-arrow left-text="" @click-left="back()"> </van-nav-bar>
		<br>
		<van-field label="旧密码" placeholder="请输入旧密码" v-model="form.oldPassword"></van-field>
		<van-field label="新密码" placeholder="请输入新密码" v-model="form.newPassword"></van-field>
		<van-field label="重复密码" placeholder="请输入重复密码" v-model="form.againPassword"></van-field>
		<br>
		<div style="display: flex;justify-content: center;">
			<van-button style="width: 80%;" color="rgb(25,137,250)" @click="saveUpdatePassword">确定</van-button>
		</div>
		
		<div style="display: flex;justify-content: center;">
			<van-button style="width: 80%;" color="rgb(25,137,250)" @click="register">注册</van-button>
		</div>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				userInfo: {
					username: '',
				},
				form: {
					oldPassword: '',
					newPassword: '',
					againPassword: '',
				}
			}
		},
		activated() {
			window.getUserInfo().then(result => {
				console.log(result)
				this.userInfo = result
				this.form = {
					oldPassword: '',
					newPassword: '',
					againPassword: '',
				}
			})
		},
		mounted() {

		},
		methods: {
			saveUpdatePassword() {
				const _this = this
				let data = {
					id: _this.userInfo.id,
					oldPassword: _this.form.oldPassword,
					newPassword: _this.form.newPassword,
					againPassword: _this.form.againPassword,
				}
				if (data.newPassword !== data.againPassword) {
					window.vant.Toast('两次密码不一致');
					return
				}
				axios.post(`${api.baseApi}/api/user/updatePassword`, data).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message);
						return;
					}
					window.vant.Toast(result.message);
					_this.back()
				}).catch(function(error) {
					console.error('出现错误:', error);
				});


			},
			back() {
				this.$router.back()
			},
		}
	}
</script>
<style scoped>
</style>
