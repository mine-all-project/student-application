<template>
  <div>
    <a-button @click="addPaper">发布信息</a-button>
    <a-divider/>
    <a-table :columns="columns" :data-source="dataSource" rowKey="id">
      <span slot="customTitle"> 标题</span>
      <span slot="tags" slot-scope="tags">
        <a-tag v-for="tag in tags" :rowKey="tag.id" :color="tag.color">{{ tag.name }}</a-tag>
      </span>
      <span slot="readerCount" slot-scope="readerCount">{{ readerCount }}</span>
      <span slot="createTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="warn" size="small" @click="editPaper(record)">编辑文章</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="showPaperInfo(record)">查看详情</a-button>
        <a-divider type="vertical"/>
        <a-button type="danger" size="small" @click="removePaper(record)">删除</a-button>
      </span>
    </a-table>
    <a-drawer title="发布信息" width="60%" :visible="show.addPaper" @close="closeForm">
      <a-form-model :model="form.addPaper" :rules="rules.addPaper" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-model-item label="标题" prop="title">
          <a-input v-model="form.addPaper.title" placeholder="请输入标题"/>
        </a-form-model-item>
        <a-form-model-item label="标签" prop="tagsList">
          <a-select mode="multiple" :default-value="[]" v-model="form.addPaper.tagsList" placeholder="请选择标签">
            <a-select-option v-for="item in tagsOptions" :key="item.id">
              {{ item.name }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="附件">
          <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers" @change="uploadFile">
            <a-button type="dashed" size="small">添加附件</a-button>
          </a-upload>
        </a-form-model-item>
        <a-form-model-item label="正文" prop="content">
          <a-form-model-item key="index">
            <div id="editor"></div>
          </a-form-model-item>
        </a-form-model-item>
      </a-form-model>
      <div class="drawer-bottom-button">
        <a-button :style="{ marginRight: '8px' }" @click="closeForm"> 关闭</a-button>
        <a-button type="primary" @click="submitForm"> 发布</a-button>
      </div>
    </a-drawer>
  </div>
</template>

<script>
import Editor from 'wangeditor'

export default {
  name: "user-list",
  data() {
    return {
      rules: {
        addPaper: {
          title: [
            {required: true, message: '请输入课题名称', trigger: 'change'},
            {min: 2, max: 16, message: '长度为2-16个字符', trigger: 'change'},
          ],
          tagsList: [
            {required: true, message: '请选择标签', trigger: 'change'},
          ],
          content: [
            {required: true, message: '阶段目标至少有一个', trigger: 'change'},
          ],
          fileList: [
            {required: true, message: '文件名不能为空', trigger: 'change'},
          ],
        },
      },
      headers: {
        'crabapples-token': sessionStorage.getItem('crabapples-token'),
      },
      columns: [
        {
          dataIndex: 'title',
          slots: {title: 'customTitle'},
          scopedSlots: {customRender: 'title'},
        },
        {
          dataIndex: 'tagsList',
          title: '标签',
          scopedSlots: {customRender: 'tags'},
        },
        {
          dataIndex: 'createTime',
          title: '发布时间',
          scopedSlots: {customRender: 'createTime'},
        },
        {
          dataIndex: 'readerCount',
          title: '阅读量',
          scopedSlots: {customRender: 'readerCount'},
        },
        {
          dataIndex: 'action',
          title: '操作',
          scopedSlots: {customRender: 'action'},
        },
      ],
      dataSource: [],
      labelCol: {span: 4},
      wrapperCol: {span: 18},
      openKeys: [],
      form: {
        addPaper: {
          id: '',
          title: '',
          content: '',
          tagsList: [],
          fileList: []
        },
      },
      stepId: '',
      show: {
        addPaper: false,
        paperInfo: false,
      },
      tagsOptions: [],
      tags: [],
      userInfo: {
        name: ''
      },
      editor: null,
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    getTagsList() {
      this.$http.get('/api/tags/list').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.tagsOptions = result.data;
          this.tags = result.data;
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    getList() {
      this.$http.get('/api/paper1/list').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.dataSource = result.data.map(e => {
            e.createTime = e.createTime && e.createTime.substring(0, 10)
            return e
          })
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    addPaper() {
      const _this = this
      this.show.addPaper = true
      this.getTagsList()
      this.$nextTick(() => {
        _this.editor = new Editor('#editor')
        _this.editor.config.onchange = (e) => {
          _this.editorChange(e)
        }
        _this.editor.create()
      })
    },
    removePaper(e) {
      this.$http.get(`/api/paper1/removeById/${e.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    editPaper(e) {
      const _this = this
      _this.form.addPaper.fileList = e.fileList.map(r => {
        return {
          uid: r.id,
          name: r.name,
          url: r.url,
        }
      })
      _this.show.addPaper = true
      _this.getTagsList()
      _this.$nextTick(() => {
        _this.editor = new Editor('#editor')
        _this.editor.config.onchange = (e) => {
          _this.editorChange(e)
        }
        _this.editor.create()
        _this.form.addPaper.id = e.id
        _this.form.addPaper.title = e.title
        _this.form.addPaper.content = e.content
        _this.editor.txt.html(e.content)
        _this.form.addPaper.tagsList = e.tagsList.map(e => {
          return e.id
        })
        console.log(_this.form.addPaper)
      })
    },
    editorChange(e) {
      this.form.addPaper.content = e
    },
    closeForm() {
      this.show.addPaper = false
      this.resetPaperForm()
      this.editor.destroy()
      this.editor = null
    },
    submitForm() {
      this.form.addPaper.fileList = this.form.addPaper.fileList.map(e => {
        return {
          name: e.name,
          status: e.status,
          url: e.url,
        }
      })
      this.$http.post('/api/paper1/save', this.form.addPaper).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.resetPaperForm()
        this.closeForm()
        this.refreshData()
        // window.location.reload()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });


    },
    showPaperInfo(e) {
    },
    refreshData() {
      this.getList()
      this.show = {
        addPaper: false,
        paperInfo: false,
      }
    },
    resetPaperForm() {
      this.form.addPaper = {
        id: '',
        title: '',
        content: '',
        tagsList: [],
        fileList: []
      }
    },
    uploadFile(info) {
      let fileList = []
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} 上传成功`);
        info.fileList.forEach(e => {
          if (e.response) {
            fileList.push({
              uid: e.uid,
              name: e.name,
              status: e.status,
              url: e.response.data.path,
            })
          }
        })
      } else if (info.file.status === 'removed') {
        info.fileList.forEach(e => {
          if (e.response) {
            fileList.push({
              uid: e.uid,
              name: e.name,
              status: e.status,
              url: e.response.data.path,
            })
          }
        })
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} 上传失败`);
      }
      this.form.addPaper.fileList = fileList
    },
    downloadFile(e) {
      let fileName = e.fileName
      this.$http.get(e.url, {responseType: 'blob'}).then((res) => {
        let reader = new FileReader();
        reader.readAsDataURL(res);
        reader.onload = function (e) {
          let a = document.createElement('a');
          a.download = fileName;
          a.href = e.target.result;
          document.body.appendChild(a);
          a.click();
          document.body.removeChild(a);
        }
      });


    },
  }
}
</script>

<style scoped>
.drawer-bottom-button {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 100%;
  border-top: 1px solid #e9e9e9;
  padding: 10px 16px;
  background: #fff;
  text-align: right;
  z-index: 1;
}
</style>