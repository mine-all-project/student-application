<template>
	<div>
		<div id="page">
			<page-header @search="search" :show="false"></page-header>
			<!-- /header -->
			<main>
				<div class="container margin_60_35">
					<div class="main_title">
					</div>
					<div class="row justify-content-center">
						<div class="col-xl-6 col-lg-6 col-md-8">
							<div class="box_account">
								<h3 class="new_client">发布房源</h3>
								<div class="form_container">
									<div class="form-group">
										<input autocomplete="off" placeholder="标题" type="text" class="form-control"
										       v-model="form.title">
									</div>
									<div class="form-group">
										<input autocomplete="off" placeholder="备注" type="text" class="form-control"
										       v-model="form.note">
									</div>
									<el-row class="form-group">
										<el-col :span="6">
											<el-select v-model="form.province" placeholder="请选择省份" @change="getCitiesList">
												<el-option :label="item.province" :value="item.provinceid" v-for="item in provincesList"
												           :key="item.province"></el-option>
											</el-select>
										</el-col>
										<el-col :span="6" :offset="1">
											<el-select v-model="form.city" placeholder="请选择城市" @change="getAreasList">
												<el-option :label="item.city" :value="item.cityid" v-for="item in citiesList"
												           :key="item.cityid"></el-option>
											</el-select>
										</el-col>
										<el-col :span="6" :offset="1">
											<el-select v-model="form.area" placeholder="请选择区县">
												<el-option :label="item.area" :value="item.areaid" v-for="item in areasList" :key="item.area">
												</el-option>
											</el-select>
										</el-col>
									</el-row>

									<div class="form-group" style="margin-top: 10px">
										<input autocomplete="off" placeholder="详细地址" type="text" class="form-control" v-model="form.address">
									</div>
									<div class="form-group">
										<input autocomplete="off" placeholder="价格" type="number" class="form-control" v-model="form.price">
									</div>
									<div class="form-group ">
										<div class="row">
											<el-upload action="/api/uploadFile" list-type="picture-card" :file-list="fileList"
											           :on-preview="handlePictureCardPreview" :on-remove="handleRemove"
											           :on-success="uploadSuccess">
												<i class="el-icon-plus"></i>
											</el-upload>
											<el-dialog :visible.sync="dialogVisible">
												<img width="100%" :src="dialogImageUrl" alt="">
											</el-dialog>
										</div>
									</div>
									<div class="form-group">
										<el-select v-model="form.contract_id" @change="changeContracts" filterable placeholder="请选择合同模板">
											<el-option v-for="item in contractsList" :key="item.id" :label="item.title" :value="item.id">
											</el-option>
										</el-select>
									</div>
									<div class="form-group" v-if="contracts !== null">
										<h1>模板预览</h1>
									</div>
									<div class="form-group">
										<div v-html="contracts.content">
										</div>
									</div>
									<div class="text-center">
										<input @click="submit" type="submit" value="发布" class="btn_1 full-width">
									</div>
								</div>
								<!-- /form_container -->
							</div>
							<!-- /box_account -->
						</div>
					</div>
				</div>
			</main>
			<page-footer></page-footer>
		</div>
		<div id="toTop"></div><!-- Back to top button -->
	</div>
</template>
<script>
    module.exports = {
        components: {
            'page-header': httpVueLoader('/vue/portal/page-header.vue'),
            'page-footer': httpVueLoader('/vue/portal/page-footer.vue'),
        },
        data() {
            return {
                contractsList: [],
                contracts: 0,
                fileList: [],
                dialogImageUrl: '',
                dialogVisible: false,
                form: {
                    address: '',
                    contract_id: '',
                    img_src: '',
                    note: '',
                    price: '',
                    title: '',
                    province: '',
                    city: '',
                    area: '',
                },
                userInfo: {},
                provincesList: [],
                citiesList: [],
                areasList: [],
            };
        },
        mounted() {
            this.getContractsList()
            this.getProvincesList()
        },
        methods: {
            search(keywords) {
                if (keywords) {
                    axios.get('/api/getHousesListByTitle?title=' + keywords).then(({data: res}) => {
                        if (res.success) {
                            this.housesList = res.data;
                        } else {
                            this.housesList = [];
                        }
                    });
                } else {
                    this.getHousesList()
                }
            },
            getContractsList() {
                const _this = this
                axios.get('/api/getContractsList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.contractsList = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            checkForm(form) {
                const _this = this
                console.log(form)
                for (let value of Object.values(form)) {
                    if (value === '') {
                        _this.$message.error('房源信息不完整');
                        return false;
                    }
                }
                return true;
            },
            submit() {
                const _this = this

                if (_this.checkForm(_this.form)) {
                    let province = _this.provincesList.filter(e => {
                        return e.provinceid === _this.form.province
                    })
                    let city = _this.citiesList.filter(e => {
                        return e.cityid === _this.form.city
                    })
                    let area = _this.areasList.filter(e => {
                        return e.areaid === _this.form.area
                    })
                    let provinceText = province.length > 0 ? province[0].province : ''
                    let cityText = city.length > 0 ? city[0].city : ''
                    let areaText = area.length > 0 ? area[0].area : ''
                    console.log(provinceText, cityText, areaText)
                    _this.form.address = provinceText + cityText + areaText + _this.form.address
                    axios.post('/api/saveHousesInfo', _this.form).then(({data: res}) => {
                        if (res.success) {
                            layer.msg(res.message, {icon: 6});
                            setTimeout(() => {
                                _this.type = 0;
                                _this.$router.back(-1)
                            }, 1000);

                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    });
                }
            },
            changeContracts(item) {
                let selector = this.contractsList.filter(e => {
                    return e.id === item
                })
                this.contracts = selector[0]
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleRemove(file, fileList) {
                const _this = this;
                const url = file.response.data.url
                _this.form.img_src.replace(url, '')
            },
            uploadSuccess(result) {
                console.log('通过api获取到的数据:', result);
                if (result.status !== 200) {
                    this.$message.error('上传失败');
                    return
                }
                let src = this.form.img_src;
                this.form.img_src = src ? `${src}${result.data.url}` : `${result.data.url},`;
                this.$message.success('上传成功');
            },
            getProvincesList() {
                const _this = this
                axios.get('/getProvincesList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.provincesList = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            getCitiesList() {
                const _this = this
                let pid = _this.form.province
                axios.get(`/getCitiesList/${pid}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.citiesList = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            getAreasList() {
                const _this = this
                let pid = _this.form.city
                axios.get(`/getAreasList/${pid}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.areasList = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
        }
    };
</script>
<style>
	.el-dropdown {
		vertical-align: top;
	}

	.el-dropdown + .el-dropdown {
		margin-left: 15px;
	}

	.el-icon-arrow-down {
		font-size: 12px;
	}
</style>
