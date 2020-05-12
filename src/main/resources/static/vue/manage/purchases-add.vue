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
				<el-input placeholder="请输入内容" v-model="form.supply" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.supply}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>联系电话</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.phone" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.phone}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="3"><label>数量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.counts" clearable size="small" type="number"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.counts}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2" :offset="16">
				<el-button type="primary" @click="saveForm" size="mini" v-if="isEdit">保存</el-button>
			</el-col>
		</el-row>

	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                isEdit: true,
                form: {
                    id: '',
                    name: '测试',
                    number: '测试',
                    address: '测试',
                    supply: '测试',
                    phone: '测试',
                    counts: '11',
                },
            };
        },
        activated() {
            let id = this.$route.query
            this.isEdit = true
            console.log(this.$route.query)
            if (!!id) {
                this.findDataById(id)
            }
            // if (this.$route.query.isEdit === false) {
            //     this.isEdit = false
            // }

        },
        mounted() {
        },
        methods: {
            findDataById(id) {
                const _this = this;
                axios.get(`/api/getPurchasesById?id=${id}`).then(response => {
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
                axios.post(`/api/savePurchasesInfo`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    _this.$router.push('/purchases-list')
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
