<template>
	<div>
		<div id="box">
			<div class="item">
				<div class="login-box">
					<div class="zh">
						<label>账&nbsp;号:</label>
						<input type="text" class="text" v-model="form.username" placeholder="请输入账号"/>
					</div>
					<div class="password">
						<label>密&nbsp;码:</label>
						<input type="password" class="pwd" v-model="form.password" placeholder="请输入密码"/>
					</div>
					<a class="login" @click="login">登录</a>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
    module.exports = {
        components: {},
        data() {
            return {
                form: {
                    username: '',
                    password: ''
                },
                showForm: false,
                userInfo: {},
                houseInfo: {}
            };
        },
        mounted() {


        },
        methods: {
            login() {
                axios.post('/loginCheck', this.form).then(response => {
                    let result = response.data
                    if (result.status !== 200) {
                        this.$message.error(result.message)
                        return
                    }
                    this.$router.push({path: 'home'})
                });
            },
        }
    };
</script>
<style scoped>
	body, h3 {
		margin: 0;
		padding: 0;
		overflow: hidden;
	}

	#box {
		display: flex;
		display: -webkit-flex;
		height: 100vh;
		width: 100%;
		align-items: center;
		justify-content: center;
		background: url(/portal/img/bg.jpg) no-repeat;
		/*background-size: 100%;*/
	}

	.item {
		width: 380px;
		height: 230px;
		background-color: rgba(255, 192, 159, .8);
		transition: .5s ease;
		border-radius: 8px;
		padding: 50px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.item .login-box {
		margin-top: 50px;
	}

	.item .title p {
		color: #333;

	}

	.item h3 {
		color: white;
		text-align: center;
		height: 60px;
		line-height: 60px;
		letter-spacing: 5px;
		background-color: rgb(255, 87, 34);
		border-radius: 8px;
	}

	.zh, .password {
		height: 30px;
		width: 265px;
		margin: 20px auto;
		color: #333;
	}

	.item .text, .item .pwd {
		display: inline-block;
		height: 35px;
		width: 200px;
		border: none;
		padding-left: 10px;
		border-radius: 4px;

	}

	.item .login {
		display: block;
		width: 150px;
		height: 40px;
		margin: 10px auto;
		text-decoration: none;
		text-align: center;
		color: white;
		line-height: 40px;
		border-radius: 20px;
		background-image: linear-gradient(to right, rgb(132, 202, 252), rgb(152, 152, 253));
	}

	.item .login:hover {
		background-image: linear-gradient(to right, rgb(152, 152, 253), rgb(132, 202, 252));
	}

	#warning {
		opacity: 0;
		text-align: center;
		color: red;
		font-size: 14px;
	}

</style>
