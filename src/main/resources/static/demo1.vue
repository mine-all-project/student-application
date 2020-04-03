<template>
	<el-row :gutter="20">
		<el-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8" v-for="item in pictures">
			<el-upload
					action="#"
					list-type="picture-card"
					:auto-upload="false">
				<i slot="default" class="el-icon-plus"></i>
				<div slot="file" slot-scope="{file}">
					<img class="el-upload-list__item-thumbnail" :src="file.url" alt="">

					<span class="el-upload-list__item-actions">
        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
          <i class="el-icon-zoom-in"></i>
        </span>
      </span>
				</div>
			</el-upload>
			<el-dialog :visible.sync="dialogVisible">
				<img width="100%" :src="dialogImageUrl" alt="">
			</el-dialog>

		</el-col>
	</el-row>
</template>

<script>
    module.exports = {
        data() {
            return {
                pictures: [],
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false
            }
        },
        mounted() {
            this.getPictures()
        },
        methods: {
            getPictures() {
                const _this = this
                axios.get('/api/getPictures').then(response => {
                    const result = response.data
                    console.log(result)
                    if (result.status !== 200) {
                        layer.msg(result.message)
                        return
                    }
                    _this.pictures = result.data
                    while (_this.pictures.length < 9) {
                        _this.pictures.push({})
                    }
                }).catch(function (error) {
                    // 请求失败处理
                    console.log(error);
                });
            },

            handleRemove(file) {
                console.log(file);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            }
        }
    }
</script>

<style scoped>
	.picture {
		height: 200px;
		margin-top: 5px;
	}

	.el-row {
		margin-bottom: 20px;

	&
	:last-child {
		margin-bottom: 0;
	}

	}


	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
