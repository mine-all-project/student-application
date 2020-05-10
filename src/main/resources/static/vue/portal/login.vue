<template>
	<div class="login">
		<div class="welcome">
			<img src="/portal/images/welcome.png"></div>
		<div class="login-form">
			<div class="login-inp">
				<label>登录</label>
				<input type="text" placeholder="请输入用户名" v-model="form.username">
			</div>
			<div class="login-inp">
				<label>密码</label>
				<input type="password" placeholder="请输入密码" v-model="form.password">
			</div>
			<div class="login-inp">
				<a @click="loginCheck">立即登录</a>
			</div>
		</div>
		<div class="login-txt">
			<a href="#">立即注册</a>|<a href="#">忘记密码？</a>
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
                },
            };
        },
        mounted() {
        },
        methods: {
            loginCheck() {
                let data = {
                    username: this.form.username,
                    password: this.form.password
                };
                axios.post('/loginCheck', data).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result)
                    if (result.status !== 200) {
                        layer.msg(result.message);
                        return
                    }
                    layer.msg(result.message);
                    this.$router.back(-1)
                }).catch(exception => {
                    layer.msg('系统错误');
                    console.error('系统错误', exception)
                })
            },
            select(item) {
                this.standsList = item.data.standsList
            },
        }
    }
</script>
<style>
	html, body, div, p, form, label, ul, li, dl, dt, dd, ol, img, button, b, em, strong, small, h1, h2, h3, h4, h5, h6 {
		margin: 0;
		padding: 0;
		border: 0;
		list-style: none;
		font-style: normal;
	}

	body {
		font-family: SimHei, 'Helvetica Neue', Arial, 'Droid Sans', sans-serif;
		font-size: 14px;
		color: #333;
		background: #f2f2f2;
	}

	a, a.link {
		color: #666;
		text-decoration: none;
		font-weight: 500;
	}

	a, a.link:hover {
		color: #666;
	}

	h1, h2, h3, h4, h5, h6 {
		font-weight: normal;
	}

	.login {
		width: 100%;
		height: 100%;
		background: url(/portal/images/login-bg.png) no-repeat;
		background-size: cover;
		position: fixed;
		z-index: -10;
	}

	.welcome {
		width: 100%;
		margin: 25% 0;
	}

	.welcome img {
		width: 100%;
	}

	.login-inp {
		margin: 0 30px 15px 30px;
		border: 1px solid #fff;
		border-radius: 25px;
	}

	.login-inp label {
		width: 4em;
		text-align: center;
		display: inline-block;
		color: #fff;
	}

	.login-inp input {
		line-height: 40px;
		color: #fff;
		background-color: transparent;
		border: none;
		outline: none;
	}

	.login-inp a {
		display: block;
		width: 100%;
		text-align: center;
		line-height: 40px;
		color: #fff;
		font-size: 16px;
		letter-spacing: 5px;
	}

	.login-txt {
		text-align: center;
		color: #fff;
	}

	.login-txt a {
		color: #fff;
		padding: 0 5px;
	}
</style>
