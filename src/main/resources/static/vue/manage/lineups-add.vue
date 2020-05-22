<template>
	<div class="parent-body">
		<!--实验室-->
		<el-row class="form-line">
			<el-col :span="3"><label>实验室</label></el-col>
			<el-col :span="8">
				<el-select v-model="form.roomsId" style="width:100%" size="small" @change="selectChange">
					<el-option v-for="item in roomsList" :key="item.id" :label="item.name" :value="item.id" size="small">
					</el-option>
				</el-select>
			</el-col>
		</el-row>

		<!--实验设备-->
		<el-row class="form-line">
			<el-col :span="3"><label>实验设备</label></el-col>
			<el-col :span="8">
				<el-select v-model="form.roomsId" style="width:100%" size="small" @change="selectChange">
					<el-option v-for="item in machineList" :key="item.id" :label="item.name" :value="item.id" size="small">
					</el-option>
				</el-select>
			</el-col>
		</el-row>

		<!--运行时长-->
		<el-row class="form-line">
			<el-col :span="3"><label>实验设备</label></el-col>
			<el-col :span="8">
				<el-input placeholder="请输入内容" v-model="form.phone" clearable size="small"></el-input>
			</el-col>
		</el-row>

		<!--开始时间-->
		<el-row class="form-line">
			<el-col :span="3"><label>预约时间</label></el-col>
			<el-col :span="8">
				<el-date-picker v-model="form.goods.shelLife" type="datetime" style="width: 100%" size="small"
				                placeholder="选择日期时间">
				</el-date-picker>
			</el-col>
		</el-row>

		<!--结束时间-->
		<el-row class="form-line">
			<el-col :span="3"><label>预约时间</label></el-col>
			<el-col :span="8">
				<el-date-picker v-model="form.goods.shelLife" type="datetime" style="width: 100%" size="small"
				                placeholder="结束时间自动计算">
				</el-date-picker>
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
                roomsList: [],
                machineList: ['选中且禁用', '复选框 A'],
                form: {
                    id: '',
                    roomsId: '',
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
            this.getRoomsList()
        },
        mounted() {
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
                    _this.form.roomsId = result.data.id;
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
            getRoomsList() {
                const _this = this;
                axios.get('/api/getRoomsList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.roomsList = result.data;
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
