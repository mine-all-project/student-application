<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<h1>个人中心</h1>
			<p>&nbsp;</p>
			<el-col :span="12">用户名:</el-col>
			<el-col :span="12">{{userInfo.username}}</el-col>
			<p>&nbsp;</p>
			<el-col :span="12">姓名:</el-col>
			<el-col :span="12" v-if="this.isEdit">
				<el-input v-model="userInfo.name" size="mini"></el-input>
			</el-col>
			<el-col :span="12" v-else>{{userInfo.name}}</el-col>
			<p>&nbsp;</p>
			<el-col :span="12">电话:</el-col>
			<el-col :span="12" v-if="this.isEdit">
				<el-input v-model="userInfo.phone" size="mini"></el-input>
			</el-col>
			<el-col :span="12" v-else>{{userInfo.phone}}</el-col>
			<p>&nbsp;</p>
			<el-col :span="12">邮箱:</el-col>
			<el-col :span="12" v-if="this.isEdit">
				<el-input v-model="userInfo.mail" size="mini"></el-input>
			</el-col>
			<el-col :span="12" v-else>{{userInfo.mail}}</el-col>
			<p>&nbsp;</p>

			<el-col :span="8" :offset="2" class="col-line" v-if="isEdit">
				<el-button type="primary" round class="button" @click="saveUserInfo" size="mini">保存</el-button>
			</el-col>

			<el-col :span="8" :offset="4" class="col-line" v-if="isEdit">
				<el-button round class="button" @click="exitEdit" size="mini">取消</el-button>
			</el-col>

			<el-col :span="8" :offset="2" class="col-line" v-if="!isEdit">
				<router-link to="/change-password">
					<el-button type="primary" plain class="button" size="mini">修改密码</el-button>
				</router-link>
			</el-col>

			<el-col :span="8" :offset="4" class="col-line" v-if="!isEdit">
				<router-link to="/mine-notices">
					<el-button type="primary" plain class="button" size="mini">我的公告</el-button>
				</router-link>
			</el-col>

			<el-col :span="24" class="col-line">
				<el-button type="primary" round class="button" @click="edit" v-if="!isEdit">编辑个人信息</el-button>
			</el-col>

		</el-row>
	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                isEdit: false,
                showSave: false,
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
                    if (result.data === null) {
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
            edit() {
                this.isEdit = true
            },
            exitEdit() {
                this.isEdit = false
                this.getUserInfo()
            }
        }
    }
</script>
<style>
	.button {
		width: 100%;
		padding: 16px;
	}

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
