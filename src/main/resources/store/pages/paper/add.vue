<template>
	<div>
		<van-nav-bar left-text="" right-text="保存" left-arrow @click-left="back()" @click-right="savePaper">
		</van-nav-bar>
		<van-field v-model="form.paper.title" label="标题"></van-field>
		<van-cell is-link @click="showPaperTypes">{{tips}}</van-cell>
		<van-popup v-model="show.paperTypes" position="bottom" :style="{ height: '40%' }" style="z-index:200"
			:close-on-click-overlay="false">
			<van-picker show-toolbar :columns="options.paperTypes" :default-index="0" cancel-button-text=" " @cancel=""
				@confirm="closePaperTypes" @change="changePaperType">
				<template slot="option" slot-scope="option">
					<span>{{option.name}}</span>
				</template>
			</van-picker>
		</van-popup>
		<div id="editor" style="height: 40vh"></div>
	</div>
</template>
<script>
	module.exports = {
		data() {
			return {
				active: 0,
				tips: '选择文章分类',
				editor: null,
				options: {
					paperTypes: []
				},
				show: {
					paperTypes: false
				},
				form: {
					type: 0,
					paper: {
						id: '',
						title: '',
						content: '',
						typeId: ''
					},
				},
				paperType: '',
				editor: {
					instance: null,
					create: true,
					toolbar: [
						['bold', 'italic', 'underline', 'strike'], //加粗，斜体，下划线，删除线
						['blockquote', 'code-block'], //引用，代码块
						[{
							'list': 'ordered'
						}, {
							'list': 'bullet'
						}], //列表
						[{
							'color': []
						}, {
							'background': []
						}], // 字体颜色，字体背景颜色
						[{
							'align': []
						}], //对齐方式
						['clean'], //清除字体样式
						['image'] //上传图片、上传视频
					]
				},
			}
		},
		activated() {
			this.createEditor()
			this.initPaperTypes()
			this.form = {
				type: 0,
				paper: {
					id: '',
					title: '',
					content: '',
					typeId: ''
				},
			}
		},
		mounted() {

		},
		methods: {
			initPaperTypes() {
				axios.get(`${api.baseApi}/api/paperType/list`).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					if (result.data !== null) {
						this.options.paperTypes = result.data
					}
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
			createEditor() {
				setTimeout(() => {
					if (this.editor.create) {
						this.editor.instance = new window.Quill('#editor', {
							theme: 'snow',
							modules: {
								toolbar: this.editor.toolbar
							}
						})
						this.editor.create = false
					}
					this.editor.instance.root.innerHTML= ''
				}, 500)
			},
			showPaperTypes() {
				this.show.paperTypes = true
			},
			closePaperTypes(paperType) {
				this.form.paper.typeId = paperType.id
				this.tips = paperType.name
				this.show.paperTypes = false
			},
			changePaperType() {
				console.log('changePaperType-->', this.paperType)
			},
			back() {
				this.$router.back()
			},
			addPaper() {},
			savePaper() {
				this.form.paper.content = this.editor.instance.root.innerHTML
				axios.post(`${api.baseApi}/api/paper/add`, this.form.paper).then(response => {
					const result = response.data
					if (result.status !== 200) {
						window.vant.Toast(result.message)
						return;
					}
					window.vant.Toast(result.message);
					this.back()
				}).catch(function(error) {
					console.error('出现错误:', error);
				});
			},
		}
	}
</script>
<style scoped>
</style>
