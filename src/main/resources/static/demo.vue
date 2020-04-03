<template>
	<el-row :gutter="20">
		<el-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8" v-for="item in pictures">
			<div class="grid-content bg-purple picture">
				<img :src="item.url" alt="">

			</div>
		</el-col>
	</el-row>
</template>

<script>
    module.exports = {
        data() {
            return {
                pictures: [],
            }
        },
        mounted() {
            this.getPictures()
        },
        methods: {
            getPictures() {
                const _this = this
                axios.get('/api/getPictures').then(response => {
                    const result = response.data
                    console.log(result)
                    if (result.status !== 200) {
                        layer.msg(result.message)
                        return
                    }
                    _this.pictures = result.data
                    while (_this.pictures.length < 9) {
                        _this.pictures.push({})
                    }
                }).catch(function (error) {
                    // 请求失败处理
                    console.log(error);
                });
            },
        }
    }
</script>

<style scoped>
	.picture {
		height: 200px;
		margin-top: 10px;
	}

	.el-row {
		margin-bottom: 20px;

	&
	:last-child {
		margin-bottom: 0;
	}

	}
	.el-col {
		border-radius: 4px;
	}

	.bg-purple-dark {
		background: #99a9bf;
	}

	.bg-purple {
		background: #d3dce6;
	}

	.bg-purple-light {
		background: #e5e9f2;
	}

	.grid-content {
		border-radius: 4px;
		min-height: 36px;
	}

	.row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}
</style>
