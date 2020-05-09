<template>
	<div class="body-parent">
		<h1>修改密码</h1>
		<p>&nbsp;</p>
		<el-col :span="6">原密码:</el-col>
		<el-col :span="18">
			<el-input v-model="form.password" size="mini"></el-input>
		</el-col>
		<p>&nbsp;</p>
		<el-col :span="6">新密码:</el-col>
		<el-col :span="18">
			<el-input v-model="form.password" size="mini"></el-input>
		</el-col>
		<p>&nbsp;</p>
		<el-col :span="6">重复密码:</el-col>
		<el-col :span="18">
			<el-input v-model="form.password" size="mini"></el-input>
		</el-col>
		<p>&nbsp;</p>
		<el-col :span="8" :offset="2" class="col-line">
			<el-button type="primary" round class="button" @click="savePassword" size="mini">确认修改</el-button>
		</el-col>

		<el-col :span="8" :offset="4" class="col-line">
			<el-button round class="button" @click="$router.back(-1)" size="mini">返回</el-button>
		</el-col>
	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                form: {
                    password: '',
                    newPassword: '',
                    rePassword: '',
                },
            };
        },
        mounted() {
        },
        methods: {
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
            },
        }
    }
</script>
<style>
	.button {
		width: 100%;
		padding: 16px;
	}
</style>
