<template>
	<div class="parent-body">
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><span>产品名称</span></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.name" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.name}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>产品批号</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.number" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.number}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>生产地址</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.address" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.number}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>供应商</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-select v-model="form.supplier" placeholder="供应商" size="small" style="width: 100%">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.supplier}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>电话号码</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.phone" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.phone}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="16">
				<el-button type="primary" @click="drawerOpen(undefined)" size="mini">添加</el-button>
			</el-col>
		</el-row>

	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                options: [
                    {
                        value: '选项1',
                        label: '黄金糕'
                    },
                    {
                        value: '选项2',
                        label: '双皮奶'
                    },
                    {
                        value: '选项3',
                        label: '蚵仔煎'
                    },
                    {
                        value: '选项4',
                        label: '龙须面'
                    },
                    {
                        value: '选项5',
                        label: '北京烤鸭'
                    }
                ],
                isEdit: true,
                form: {
                    id: '',
                    name: '测试',
                    number: '测试',
                    address: '测试',
                    supplier: '测试',
                    phone: '测试',
                },
            };
        },
        activated() {
            let id = this.$router.query.id
            if (!!id) {
                this.findDataById(id)
            }
        },
        mounted() {
        },
        methods: {
            findDataById(id) {
                const _this = this;
                axios.get(`/manage/findDataById?id=${id}`).then(response => {
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
            saveForm() {
                const _this = this;
                axios.post(`/manage/saveData`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>

<style scoped>
	.form-line {
		margin-top: 16px;
	}
</style>
