<template>
	<view class="content">
		<form class="app-update-pv">
			<view
				:style='{"padding":" 0 20rpx 0 0","boxShadow":"0 0 0px rgba(0,0,0,.3)","margin":"0 0 20rpx 0","borderColor":"#ccc","backgroundColor":"rgba(255, 255, 255, 1)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"108rpx"}'
				class="cu-form-group">
				<view
					:style='{"padding":"0","boxShadow":"0 0 16rpx rgba(0,0,0,0)","margin":"0","borderColor":"#ccc","backgroundColor":"rgba(0,0,0,0)","color":"rgba(0, 0, 0, 1)","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'
					class="title">业务</view>
				<picker mode="selector" :range="options.type" :value="ruleForm.type" @change="typeChange">
					<view class="uni-input"
						:style='{"padding":"0 20rpx","boxShadow":"0 2rpx 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"#ccc","backgroundColor":"rgba(0,0,0,0)","color":"#333","textAlign":"left","borderRadius":"0","borderWidth":"2rpx","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'>
						{{ruleForm.type}}
					</view>
				</picker>
			</view>


			<!-- ${location} -->
			<view v-if="isJiuyuan"
				:style='{"padding":" 0 20rpx 0 0","boxShadow":"0 0 0px rgba(0,0,0,.3)","margin":"0 0 20rpx 0","borderColor":"#ccc","backgroundColor":"rgba(255, 255, 255, 1)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"108rpx"}'
				class="cu-form-group">
				<view
					:style='{"padding":"0","boxShadow":"0 0 16rpx rgba(0,0,0,0)","margin":"0","borderColor":"#ccc","backgroundColor":"rgba(0,0,0,0)","color":"rgba(0, 0, 0, 1)","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'
					class="title">地址</view>
				<input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"rgba(0,0,0,.6)","backgroundColor":"rgba(255, 255, 255, 0)","color":"rgba(0, 0, 0, 1)","textAlign":"left","borderRadius":"16rpx","borderWidth":"2rpx","width":"calc(100% - 160rpx)","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'
					:disabled="ro.address" v-model="ruleForm.address" placeholder="地址">
				</input>
			</view>
			<view v-else>
				<view
					:style='{"padding":" 0 20rpx 0 0","boxShadow":"0 0 0px rgba(0,0,0,.3)","margin":"0 0 20rpx 0","borderColor":"#ccc","backgroundColor":"rgba(255, 255, 255, 1)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"108rpx"}'
					class="cu-form-group">
					<view
						:style='{"padding":"0","boxShadow":"0 0 16rpx rgba(0,0,0,0)","margin":"0","borderColor":"#ccc","backgroundColor":"rgba(0,0,0,0)","color":"rgba(0, 0, 0, 1)","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'
						class="title">4S店</view>

					<picker mode="selector" :range="options.storeOptions" :value="ruleForm.storeIndex"
						@change="storeChange">
						<view class="uni-input"
							:style='{"padding":"0 20rpx","boxShadow":"0 2rpx 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"#ccc","backgroundColor":"rgba(0,0,0,0)","color":"#333","textAlign":"left","borderRadius":"0","borderWidth":"2rpx","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'>
							{{ruleForm.store.name}}
						</view>
					</picker>
				</view>
				<view style="z-index: 999;">
					<uni-datetime-picker type="date" @change="changeLog" />
				</view>

			</view>
			<view class="btn">
				<button
					:style='{"padding":"0","boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","margin":"0","backgroundColor":"rgba(223, 114, 11, 1)","borderColor":"#409EFF","borderRadius":"80rpx","color":"#fff","borderWidth":"0","width":"60%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'
					@tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>


	</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";

	export default {
		data() {
			return {
				cross: '',
				options: {
					type: ['保修', '试驾', '救援'],
					store: [],
					storeOptions: [],
				},
				ruleForm: {
					type: '保修',
					typeIndex: 0,
					store: {
						name: '请选择'
					},
					storeIndex: 0,
					date: '',
					address: '',
				},
				isJiuyuan: false,
				// 登陆用户信息
				user: {},
				ro: {
					type: false,
					introduction: false,
					picture: false,
					content: false,
				},
			}
		},
		components: {
			wPicker
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onShow(options) {
			let res = await this.$http.get('jingxiaodian/flist');
			this.options.store = res.data
			this.options.storeOptions = this.options.store.map(e => {
				return e.name
			})
			console.log(JSON.stringify(this.options.storeOptions))
		},
		async onLoad(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;




			// 如果有登陆，获取登陆后保存的userid
			this.ruleForm.userid = uni.getStorageSync("userid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = options.refid;
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`news`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if (options.cross) {
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj) {
					if (o == 'title') {
						this.ruleForm.title = obj[o];
						this.ro.title = true;
						continue;
					}
					if (o == 'introduction') {
						this.ruleForm.introduction = obj[o];
						this.ro.introduction = true;
						continue;
					}
					if (o == 'picture') {
						this.ruleForm.picture = obj[o];
						this.ro.picture = true;
						continue;
					}
					if (o == 'content') {
						this.ruleForm.content = obj[o];
						this.ro.content = true;
						continue;
					}
				}
			}
			this.styleChange()
		},
		methods: {
			storeChange(e) {
				console.log(JSON.stringify(e))
				this.ruleForm.storeIndex = e.detail.value
				this.ruleForm.store = this.options.store[e.detail.value]
			},
			typeChange(e) {
				console.log(JSON.stringify(e.detail))
				this.storeruleFormIndex.typeIndex = e.detail.value
				this.ruleForm.type = this.options.type[e.detail.value]
				this.isJiuyuan = false
				if (this.ruleForm.type === '救援') {
					this.isJiuyuan = true
				}
			},
			changeType() {
				console.log(this.ruleForm.type)
				this.isJiuyuan = false
				if (this.ruleForm.type === '救援') {
					this.isJiuyuan = true
				}
			},
			changeLog(e) {
				this.ruleForm.date = e
				console.log("-change事件:", e);
			},

			getUUID() {
				return new Date().getTime();
			},
			async onSubmitTap() {
				console.log(this.ruleForm)
				if (this.ruleForm.type === '救援') {
					this.ruleForm.store = null
				}
				let res = await this.$http.post('yuyue/save', this.ruleForm);
				this.$utils.msgBack('提交成功');

			},
			styleChange() {
				this.$nextTick(() => {})
			},
		}
	}
</script>

<style lang="scss" scoped>
	.cu-form-group uni-picker::after {
		font-family: cuIcon;
		display: block;
		content: "";
		position: absolute;
		font-size: 17px;
		color: #8799a3;
		line-height: 50px;
		width: 30px;
		text-align: center;
		top: 0;
		bottom: 0;
		right: -10px;
		margin: auto;
	}

	.container {
		padding: 20upx;
	}

	.content:after {
		position: fixed;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		content: '';
		background-attachment: fixed;
		background-size: cover;
		background-position: center;
	}

	textarea {
		border: 1upx solid #EEEEEE;
		border-radius: 20upx;
		padding: 20upx;
	}

	.title {
		width: 180upx;
	}

	.avator {
		width: 150upx;
		height: 60upx;
	}

	.right-input {
		flex: 1;
		text-align: left;
		padding: 0 24upx;
	}

	.cu-form-group.active {
		justify-content: space-between;
	}

	.btn {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-wrap: wrap;
		padding: 20upx 0;
	}

	.cu-form-group {
		padding: 0 24upx;
		background-color: transparent;
		min-height: inherit;
	}

	.cu-form-group+.cu-form-group {
		border: 0;
	}

	.cu-form-group uni-input {
		padding: 0 30upx;
	}

	.uni-input {
		padding: 0 30upx;
	}

	.cu-form-group uni-textarea {
		padding: 30upx;
		margin: 0;
	}

	.cu-form-group uni-picker::after {
		line-height: 80rpx;
	}

	.select .uni-input {
		line-height: 80rpx;
	}

	.input .right-input {
		line-height: 80rpx;
	}
</style>
