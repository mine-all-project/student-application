<template>
	<div class="parent-body">
		<!--药品信息&入库数量-->
		<el-row class="form-line">
			<el-col :span="2"><label>药品信息</label></el-col>
			<el-col :span="8">
				<el-select v-model="form.purchasesId" style="width:100%" size="small" @change="selectChange">
					<el-option v-for="item in purchasesList" :key="item.id" :label="item.name" :value="item.id" size="small">
					</el-option>
				</el-select>
			</el-col>

			<el-col :span="2" :offset="2"><label>入库数量</label></el-col>
			<el-col :span="8">
				<el-input-number v-model="form.counts" size="small" style="width:100%" disabled></el-input-number>
				<!--				<el-input v-model="form.counts" clearable type="number" size="small" :max="55"></el-input>-->
			</el-col>

		</el-row>
		<!--生产日期&有效期至-->
		<el-row class="form-line">
			<el-col :span="2"><label>生产日期</label></el-col>
			<el-col :span="8">
				<el-date-picker v-model="form.goods.producedTime" type="date" style="width: 100%" size="small"
				                disabled></el-date-picker>
			</el-col>
			<el-col :span="2" :offset="2"><label>有效期至</label></el-col>
			<el-col :span="8">
				<el-date-picker v-model="form.goods.shelLife" type="date" style="width: 100%" size="small"
				                disabled></el-date-picker>
			</el-col>
		</el-row>
		<!--性状&功能主治-->
		<el-row class="form-line">
			<el-col :span="2"><label>性状</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.characte" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
			<el-col :span="2" :offset="2"><label>功能主治</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.majorFunction" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
		</el-row>
		<!--规格&用法用量-->
		<el-row class="form-line">
			<el-col :span="2"><label>规格</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.specification" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
			<el-col :span="2" :offset="2"><label>用法用量</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.usages" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
		</el-row>
		<!--不良反应&注意事项-->
		<el-row class="form-line">
			<el-col :span="2"><label>不良反应</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.adverseReactions" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
			<el-col :span="2" :offset="2"><label>注意事项</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.mattersNeeding" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
		</el-row>
		<!--禁忌&储藏环境-->
		<el-row class="form-line">
			<el-col :span="2"><label>禁忌</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.taboo" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
			<el-col :span="2" :offset="2"><label>储藏环境</label></el-col>
			<el-col :span="8">
				<el-input v-model="form.goods.storageEnvironment" type="textarea" :rows="3" resize="none" disabled></el-input>
			</el-col>
		</el-row>
		<el-row class="form-line">
			<el-col :span="2">
				<el-button type="primary" @click="saveForm" size="">确认入库</el-button>
			</el-col>
		</el-row>
	</div>
</template>

<script>
    module.exports = {
        data() {
            return {
                isEdit: true,
                purchasesList: [],
                form: {
                    id: '',
                    purchasesId: '',
                    counts: 0,
                    goods: {
                        id: '',
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
                    type: 0,
                },
            };
        },
        activated() {
        },
        mounted() {
            this.getPurchasesList()
        },
        methods: {
            selectChange(id) {
                this.getPurchasesById(id)
            },
            getPurchasesById(id) {
                const _this = this;
                axios.get(`/api/getPurchasesById?id=${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.form = result.data;
                    _this.form.purchasesId = result.data.id;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            saveForm() {
                const _this = this;
                console.log(_this.form)
                axios.get(`/api/addGoodsCountsById?id=` + _this.form.id).then(response => {
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
            getPurchasesList() {
                const _this = this;
                axios.get('/api/getPurchasesListByStatus').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.purchasesList = result.data;
                }).catch(function (error) {
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
