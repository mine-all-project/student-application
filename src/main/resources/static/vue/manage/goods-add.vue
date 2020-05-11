<template>
	<div class="parent-body">
		<!--产品名称&电话号码-->
		<el-row class="form-line">
			<el-col :span="2"><label>产品名称</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.name" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.name}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>电话号码</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.phone" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.phone}}</span>
			</el-col>
		</el-row>
		<!--产品批号&生产地址-->
		<el-row class="form-line">
			<el-col :span="2"><label>产品批号</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.number" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.number}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>生产地址</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.address" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.number}}</span>
			</el-col>
		</el-row>
		<!--产品条码&入库数量-->
		<el-row class="form-line">
			<el-col :span="2"><label>产品条码</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.barCode" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.barCode}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>入库数量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.amount" clearable type="number"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.amount}}</span>
			</el-col>
		</el-row>
		<!--生产日期&有效期至-->
		<el-row class="form-line">
			<el-col :span="2"><label>生产日期</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.producedTime" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.producedTime}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>有效期至</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.shelLife" clearable></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.shelLife}}</span>
			</el-col>
		</el-row>
		<!--性状&功能主治-->
		<el-row class="form-line">
			<el-col :span="2"><label>性状</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.character" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.character}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>功能主治</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.majorFunction" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.majorFunction}}</span>
			</el-col>
		</el-row>
		<!--规格&用法用量-->
		<el-row class="form-line">
			<el-col :span="2"><label>规格</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.specification" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.specification}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>用法用量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.usage" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.usage}}</span>
			</el-col>
		</el-row>
		<!--不良反应&注意事项-->
		<el-row class="form-line">
			<el-col :span="2"><label>不良反应</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.adverseReactions" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.adverseReactions}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>注意事项</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.mattersNeeding" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.mattersNeeding}}</span>
			</el-col>
		</el-row>
		<!--禁忌&储藏环境-->
		<el-row class="form-line">
			<el-col :span="2"><label>禁忌</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.taboo" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.taboo}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>储藏环境</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.storageEnvironment" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.storageEnvironment}}</span>
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
                    phone: '测试',
                    number: '测试',
                    address: '测试',
                    barCode: '测试',
                    amount: '测试',
                    producedTime: '测试',
                    shelLife: '测试',
                    character: '测试',
                    majorFunction: '测试',
                    specification: '测试',
                    usage: '测试',
                    adverseReactions: '测试',
                    mattersNeeding: '测试',
                    taboo: '测试',
                    storageEnvironment: '测试',
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
