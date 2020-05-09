<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<el-col :span="12">用户名:</el-col>
			<el-col :span="12">{{userInfo.username}}</el-col>
			<el-col :span="12" v-if="this.isEdit">
				<el-input v-model="userInfo.username"></el-input>
			</el-col>

		</el-row>
	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                isEdit: false,
                userInfo: {
                    username: '',
                },
                form: {
                    password: '',
                    newPassword: '',
                    rePassword: '',
                }
            };
        },
        mounted() {
            this.getUserInfo()
        },
        methods: {
            getUserInfo() {
                axios.get(`/getUserInfo`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    if(result.data === null){
                        router.push({path: '/login'})
                    }
                    this.userInfo = result.data ? result.data : null;
                    console.log(this.userInfo)
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            saveUserInfo() {
                axios.post(`/manage/saveUserInfo`, this.userInfo).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    this.$message.success(result.message);
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            savePassword() {
                axios.post(`/manage/savePassword`, this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error(result.message);
                        return
                    }
                    this.$message.success(result.message);
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }
        }
    }
</script>
<style>
	.body-parent {
		padding: 16px;
	}

	.col-line {
		margin-top: 16px;
	}

	.el-select .el-input {
		width: 130px;
	}

	.input-with-select .el-input-group__prepend {
		background-color: #fff;
	}
</style>
