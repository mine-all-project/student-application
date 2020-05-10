<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<h1>公告列表</h1>

			<el-col :span="24" class="col-line" v-for="item in dataList" :key="item.id">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>{{item.title}}</span>
						<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
					</div>
					<div class="text item">{{item.content}}</div>
				</el-card>
			</el-col>


			<!--			<el-col :span="24">标题:</el-col>-->
			<!--			<el-col :span="24">-->
			<!--				<el-input v-model="form.title"></el-input>-->
			<!--			</el-col>-->
			<!--			<p>&nbsp;</p>-->
			<!--			<el-col :span="24">内容:</el-col>-->
			<!--			<el-col :span="24">-->
			<!--				<el-input v-model="form.content" type="textarea" :autosize="{ minRows: 2}"></el-input>-->
			<!--			</el-col>-->
			<!--			<el-col :span="8" :offset="2" class="col-line" v-if="isEdit">-->
			<!--				<el-button type="primary" round class="button" @click="saveUserInfo" size="mini">发布</el-button>-->
			<!--			</el-col>-->
			<!--			<el-col :span="8" :offset="4" class="col-line" v-if="isEdit">-->
			<!--				<el-button round class="button" @click="exitEdit" size="mini">取消</el-button>-->
			<!--			</el-col>-->

			<!--			<el-col :span="8" :offset="4" class="col-line" v-if="!isEdit">-->
			<!--				<router-link to="/notices-list">-->
			<!--					<el-button type="primary" plain class="button" size="mini">我的公告</el-button>-->
			<!--				</router-link>-->
			<!--			</el-col>-->

			<!--			<el-col :span="24" class="col-line">-->
			<!--				<el-button type="primary" round class="button" @click="edit" v-if="!isEdit">编辑个人信息</el-button>-->
			<!--			</el-col>-->
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
                    id: '',
                    keyWords: '',
                    title: '',
                    content: '',
                },
                keyWords: 'notice',
                dataList: []
            };
        },
        mounted() {
            this.getTableDataList(this.keyWords)
        },
        methods: {
            getTableDataList(keyWord) {
                const _this = this;
                axios.get(`/api/getMinePapersByKeyWords/${keyWord}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.dataList = result.data;
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

	.body-parent {
		padding: 16px;
	}

	.col-line {
		margin-top: 16px;
	}

	.text {
		font-size: 14px;
	}

	.item {
		margin-bottom: 18px;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}

	.box-card {
		width: 100%;
	}
</style>
