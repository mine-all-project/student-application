<template>
	<div class="parent-body">
		<!--产品名称&供应商-->
		<el-row class="form-line">
			<el-col :span="2"><span>产品名称</span></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.name" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.name}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>供应商</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.supply" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.supply}}</span>
			</el-col>

		</el-row>
		<!--联系电话&数量-->
		<el-row class="form-line">
			<el-col :span="2"><label>联系电话</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.phone" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.phone}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>数量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.counts" clearable size="small" type="number"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.counts}}</span>
			</el-col>
		</el-row>

		<!--采购信息&入库数量-->
		<el-row class="form-line" v-if="false">
			<el-col :span="2"><label>采购信息</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-select v-model="form.purchasesId" placeholder="请选择" style="width:100%" size="small">
					<el-option v-for="item in purchasesList" :key="item.id" :label="item.name" :value="item.id" size="small">
					</el-option>
				</el-select>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.purchases}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>入库数量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.counts" clearable type="number" size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.counts}}</span>
			</el-col>
		</el-row>
		<!--生产地址&产品批号-->
		<el-row class="form-line">
			<el-col :span="2"><label>生产地址</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.address" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.number}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>产品批号</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.number" clearable size="small"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.number}}</span>
			</el-col>
		</el-row>
		<!--生产日期&有效期至-->
		<el-row class="form-line">
			<el-col :span="2"><label>生产日期</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-date-picker v-model="form.goods.producedTime" type="date" style="width: 100%" size="small"
				                @change="dateChange('producedTime')" size="small"></el-date-picker>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.producedTime}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>有效期至</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-date-picker v-model="form.goods.shelLife" type="date" style="width: 100%" size="small"
				                @change="dateChange('shelLife')"></el-date-picker>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.shelLife}}</span>
			</el-col>
		</el-row>
		<!--性状&功能主治-->
		<el-row class="form-line">
			<el-col :span="2"><label>性状</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.characte" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.characte}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>功能主治</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.majorFunction" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.majorFunction}}</span>
			</el-col>
		</el-row>
		<!--规格&用法用量-->
		<el-row class="form-line">
			<el-col :span="2"><label>规格</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.specification" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.specification}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>用法用量</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.usages" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.usages}}</span>
			</el-col>
		</el-row>
		<!--不良反应&注意事项-->
		<el-row class="form-line">
			<el-col :span="2"><label>不良反应</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.adverseReactions" type="textarea" :rows="3"
				          resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.adverseReactions}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>注意事项</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.mattersNeeding" type="textarea" :rows="3"
				          resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.mattersNeeding}}</span>
			</el-col>
		</el-row>
		<!--禁忌&储藏环境-->
		<el-row class="form-line">
			<el-col :span="2"><label>禁忌</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.taboo" type="textarea" :rows="3" resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.taboo}}</span>
			</el-col>
			<el-col :span="2" :offset="2"><label>储藏环境</label></el-col>
			<el-col :span="8" v-if="isEdit">
				<el-input placeholder="请输入内容" v-model="form.goods.storageEnvironment" type="textarea" :rows="3"
				          resize="none"></el-input>
			</el-col>
			<el-col :span="8" v-else>
				<span>{{form.goods.storageEnvironment}}</span>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="24">
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
                    name: '',
                    supply: '',
                    phone: '',
                    counts: '',
                    goods: {
                        address: '',
                        number: '',
                        producedTime: '',
                        shelLife: '',
                        characte: '',
                        majorFunction: '',
                        specification: '',
                        usages: '',
                        adverseReactions: '',
                        mattersNeeding: '',
                        taboo: '',
                        storageEnvironment: '',
                        status: 0,
                    },
                },
                formDebug: {
                    id: '',
                    name: '',
                    supply: '贵州省贵阳市观山湖制药公司',
                    phone: '13198761234',
                    counts: '999',
                    goods: {
                        address: '贵州省贵阳市',
                        number: '1960770',
                        producedTime: '2020-05-12',
                        shelLife: '2020-05-20',
                        characte: '性状',
                        majorFunction: '主要治疗咳嗽',
                        specification: '规格',
                        usages: '每日一次，每次半斤',
                        adverseReactions: '不良反应',
                        mattersNeeding: '注意事项',
                        taboo: '忌辛辣，烟酒',
                        storageEnvironment: '避光，密闭储存',
                        status: 0,
                    }
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
        },
        mounted() {
        },
        methods: {
            dateChange(key) {
                this.form.goods[key] = new moment(this.form.goods[key]).format('YYYY-MM-DD')
            },
            findDataById(id) {
                id = !!id ? id : ''
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
