<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<h1>编辑公告</h1>
			<el-col :span="24" class="col-line">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<el-input style="width: 80%" v-model="form.title"></el-input>
					</div>
					<div class="text item">
						<el-input type="textarea" v-model="form.content"></el-input>
					</div>
				</el-card>
			</el-col>
			<el-col :span="24" class="col-line">
				<el-button type="primary" round class="button" @click="publish">立即发布</el-button>
			</el-col>
			<el-col :span="24" class="col-line">
				<el-button type="danger" round class="button" @click="remove">删除</el-button>
			</el-col>
		</el-row>
	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                form: {
                    id: '',
                    keyWords: '',
                    title: '',
                    content: '',
                },
                keyWords: 'notice',
            };
        },
        mounted() {
            this.getDataById(this.$route.query.id)
        },
        methods: {
            remove() {
                const _this = this;
                const id = this.$route.query.id;
                _this.$confirm('确认删除？').then(e => {
                    axios.delete(`/api/removePapersById/${id}`).then(response => {
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                        _this.$router.back(-1)
                    }).catch(function (error) {
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getDataById(id) {
                const _this = this;
                axios.get(`/api/getPapersById?id=${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.form = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            publish() {
                const _this = this;
                _this.form.keyWords = this.keyWords;
                axios.post(`/api/savePapers`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    this.$router.back(-1)
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
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
