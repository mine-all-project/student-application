<template>
	<div class="body-parent">
		<p>123</p>

	</div>
</template>
<script>
    module.exports = {
        data() {
            return {
                form: {
                    type: '1',
                    name: '',
                },
                linesList: [],
                standsList: [],
            };
        },
        mounted() {
        },
        methods: {
            search(name, cb) {
                const _this = this;
                let url = ''
                if (_this.form.type === '1') {
                    url = `/api/searchLinesByNumber?name=${_this.form.name}`
                } else if (_this.form.type === '2') {
                    url = `/api/searchLinesByStands?name=${_this.form.name}`
                }
                console.log(_this.form)
                axios.get(url).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        _this.$message.error('数据加载失败');
                        return
                    }
                    _this.linesList = result.data.map(e => {
                        return {
                            value: e.number,
                            data: e
                        }
                    });
                    cb(_this.linesList)
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            select(item) {
                this.standsList = item.data.standsList
            },
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
