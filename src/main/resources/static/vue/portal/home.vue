<template>
	<div class="body-parent">
		<el-row style="font-size: 1em">
			<el-col :span="24">
				<el-autocomplete placeholder="请输入内容" v-model="form.name" class="input-with-select"
				                 :trigger-on-focus="false" :fetch-suggestions="search" @select="select">
					<el-select v-model="form.type" slot="prepend" placeholder="请选择">
						<el-option label="线路" value="1"></el-option>
						<el-option label="站台" value="2"></el-option>
					</el-select>
				</el-autocomplete>
			</el-col>
			<el-col class="col-steps">
				<el-col :span="24" class="col-line" v-if="this.number !== '' ">
					<span>{{number}}</span>
					<span>路公交线路图</span>
				</el-col>
				<el-col :span="24" style="text-align: center;" class="col-line">
					<el-steps direction="vertical" :space="100" :align-center="true">
						<el-step :title="item.name" status="finish" :key="item.id" v-for="item in standsList"></el-step>
					</el-steps>
				</el-col>
			</el-col>

			<el-col :span="24" class="col-line">
				<router-link to="/mine-info">
					<el-button type="primary" round style="width: 100%;padding: 16px">个人中心</el-button>
				</router-link>
			</el-col>

			<el-col :span="24" class="col-line">
				<router-link to="/notices-list">
					<el-button type="primary" round style="width: 100%;padding: 16px">公告列表</el-button>
				</router-link>
			</el-col>
		</el-row>
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
                number: '',
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
                this.number = item.data.number
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
