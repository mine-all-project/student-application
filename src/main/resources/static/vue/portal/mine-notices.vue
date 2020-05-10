<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<h1>我发布的</h1>

			<el-col :span="24" class="col-line" v-for="item in dataList" :key="item.id">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>{{item.title}}</span>
						<el-button style="float: right; padding: 3px 0" type="text" @click="edit(item)">编辑</el-button>
					</div>
					<div class="text item">{{item.content}}</div>
				</el-card>
			</el-col>
			<el-col :span="24" class="col-line">
				<el-button type="primary" round class="button" @click="add">发布公告</el-button>
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
                router.push({path: '/edit-notices', query: {id: item.id}})
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
