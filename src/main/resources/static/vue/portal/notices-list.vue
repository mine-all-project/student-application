<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<h1>公告列表</h1>

			<el-col :span="24" class="col-line" v-for="item in dataList" :key="item.id">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<el-input style="width: 80%" v-if="isEdit" v-model="form.title"></el-input>
						<span v-else>{{item.title}}</span>
						<el-button style="float: right; padding: 3px 0" type="text" @click="save()" v-if="isEdit">保存</el-button>
						<el-button style="float: right; padding: 3px 0" type="text" @click="edit(item)" v-else>编辑</el-button>
					</div>
					<div class="text item" v-if="isEdit">
						<el-input type="textarea" v-model="form.content"></el-input>
					</div>
					<div class="text item" v-else>{{item.content}}</div>
				</el-card>
			</el-col>
			<el-col :span="24" class="col-line">
				<el-button type="primary" round class="button" @click="add" v-if="!isEdit">发布公告</el-button>
			</el-col>
		</el-row>
	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                isEdit: false,
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
            edit(item) {
                const _this = this;
                _this.isEdit = true
                _this.form = item;
            },
            save() {
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
                    _this.isEdit = false
                    _this.getTableDataList(this.keyWords)
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
            add() {
                router.push({path: '/add-notices'})
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
