<template>
  <div>
    <a-modal :visible="show.addResultInfo" @cancel="closeAddResultInfo" @ok="submitResultInfo" cancelText="取消"
             okText="保存" width="50%" :zIndex="9999">
      <a-form-model ref="ruleForm" :model="form.addResultInfo" :rules="rules.addResultInfo" :label-col="labelCol"
                    :wrapper-col="wrapperCol">
        <a-form-model-item label="文件名" prop="fileName">
          <a-input v-model="form.addResultInfo.fileName" disabled/>
        </a-form-model-item>
        <a-form-model-item label="文件地址" prop="url">
          <a-input v-model="form.addResultInfo.url" disabled/>
        </a-form-model-item>
        <a-form-model-item label="描述" prop="content">
          <a-textarea v-model="form.addResultInfo.content" :rows="4"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <a-modal :visible="show.stepList" @cancel="closeStepList" :footer="null" width="70%">
      <a-table :columns="stepColumns" :data-source="stepDataSource" rowKey="id">
        <span slot="indexNum" slot-scope="text">{{ text + 1 }}</span>
        <span slot="content" slot-scope="text">
          <a-tooltip placement="topLeft">
            <template slot="title">
              <span>{{ text }}</span>
            </template>
            <span class="step-content">{{ text }}</span>
          </a-tooltip>
        </span>
        <span slot="status" slot-scope="text">
          <a-tag :color="!text ?'green':'red'">{{ !text ? '进行中' : '已结束' }}</a-tag>
        </span>
        <span slot="resultInfos" slot-scope="record">
          <template v-if="record.length>0">
            <a-popover placement="topLeft">
              <template slot="content">
                <p v-for="item in record">{{ item.content }}</p>
              </template>
              <a-tag>预览</a-tag>
            </a-popover>
          </template>
          <template v-else>
            <a-tag>暂无成果</a-tag>
          </template>
        </span>
        <span slot="action" slot-scope="text, record">
        <a-button type="default" size="small" @click="showResultList(record)">查看成果</a-button>
        <a-divider type="vertical" v-if="record.status === 0"/>
        <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers"
                  :showUploadList="false" @change="uploadResultFile" v-if="record.status === 0">
          <a-button type="dashed" size="small" @click="addResult(record)">添加成果</a-button>
        </a-upload>
      </span>
      </a-table>
    </a-modal>
    <a-modal :visible="show.resultList" @cancel="closeResultList" :footer="null" width="60%">
      <a-table :columns="resultColumns" :data-source="resultDataSource" rowKey="id">
        <span slot="indexNum" slot-scope="text">{{ text + 1 }}</span>
        <span slot="content" slot-scope="text">
          <a-tooltip placement="topLeft">
            <template slot="title">
              <span>{{ text }}</span>
            </template>
            <span class="step-content">{{ text }}</span>
          </a-tooltip>
        </span>
        <span slot="action" slot-scope="text, record">
          <a-button type="primary" size="small" @click="downloadFile(record)">下载附件</a-button>
          <a-divider type="vertical"/>
          <a-upload name="file" :multiple="true" :showUploadList="false" action="/api/uploadFile"
                    :headers="headers" @change="uploadResultFile">
            <a-button type="default" size="small" @click="updateResult(record)" v-if="stepStatus === 0">更新</a-button>
          </a-upload>
        </span>
      </a-table>
    </a-modal>
    <a-table :columns="columns" :data-source="dataSource" rowKey="id">
      <span slot="customTitle"> 课题名称</span>
      <span slot="name" slot-scope="text">{{ text }}</span>
      <span slot="tags" slot-scope="tags">
        <a-tag v-for="tag in tags" :rowKey="tag.id" :color="tag.color">{{ tag.name }}</a-tag>
      </span>
      <span slot="status" slot-scope="status">
        <a-tag v-if="status === 0" color="green">立项</a-tag>
        <a-tag v-if="status === 1" color="geekblue">在研</a-tag>
        <a-tag v-if="status === 2" color="pink">结题</a-tag>
      </span>
      <span slot="isShare" slot-scope="isShare">
        <a-tag v-if="isShare === 0" color="green">已共享</a-tag>
        <a-tag v-if="isShare === 1" color="blue">未共享</a-tag>
      </span>
      <span slot="createBy" slot-scope="createBy">{{ createBy.name }}</span>
      <span slot="beginTime" slot-scope="text">{{ text }}</span>
      <span slot="endTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="primary" size="small" @click="showStepList(record)">查看详情</a-button>
      </span>
    </a-table>
  </div>
</template>

<script>
export default {
  name: "mine-join",
  data() {
    return {
      rules: {
        addResultInfo: {
          url: [
            {required: true, message: '文件链接不能为空', trigger: 'change'},
          ],
          content: [
            {required: true, message: '请输入成果描述', trigger: 'change'},
          ],
          fileName: [
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
          dataIndex: 'status',
          title: '状态',
          scopedSlots: {customRender: 'status'},
        },
        {
          dataIndex: 'tags',
          title: '标签',
          scopedSlots: {customRender: 'tags'},
        },
        {
          dataIndex: 'beginTime',
          title: '开始时间',
          scopedSlots: {customRender: 'beginTime'},
        },
        {
          dataIndex: 'endTime',
          title: '结束时间',
          scopedSlots: {customRender: 'endTime'},
        },
        {
          dataIndex: 'isShare',
          title: '共享状态',
          scopedSlots: {customRender: 'isShare'},
        },
        {
          dataIndex: 'action',
          title: '操作',
          scopedSlots: {customRender: 'action'},
        },
      ],
      dataSource: [],
      stepColumns: [
        {
          dataIndex: 'indexNum',
          title: '阶段',
          scopedSlots: {customRender: 'indexNum'},
          width: '10%'
        },
        {
          dataIndex: 'content',
          title: '目标',
          scopedSlots: {customRender: 'content'},
          ellipsis: true,
          width: '40%'
        },
        {
          dataIndex: 'status',
          title: '状态',
          scopedSlots: {customRender: 'status'},
          width: '10%'
        },
        {
          dataIndex: 'resultInfos',
          title: '成果',
          scopedSlots: {customRender: 'resultInfos'},
          width: '10%'
        },
        {
          dataIndex: 'action',
          title: '操作',
          scopedSlots: {customRender: 'action'},
        },
      ],
      stepDataSource: [],
      resultColumns: [
        {
          dataIndex: 'content',
          title: '成果详情',
          scopedSlots: {customRender: 'content'},
          ellipsis: true,
          width: '30%'
        },
        {
          dataIndex: 'fileName',
          title: '文件名',
          ellipsis: true,
          width: '30%'
        },
        {
          dataIndex: 'action',
          title: '操作',
          scopedSlots: {customRender: 'action'},

        },
      ],
      resultDataSource: [],
      labelCol: {span: 5},
      wrapperCol: {span: 16},
      form: {
        addSub: {
          title: '',
          personList: [],
          stepList: [
            {
              indexNum: 0,
              content: '',
            }
          ],
          tagsList: []
        },
        addResultInfo: {
          id: '',
          stepId: '',
          url: '',
          content: '',
          fileName: '',
          isAdd: false,
          isEdit: false,
        },
      },
      stepId: '',
      stepStatus: 0,
      show: {
        addSub: false,
        stepList: false,
        addResultInfo: false,
        resultList: false,
      },
      tagsOptions: [],
      tags: [],
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    refreshData() {
      this.getList()
      this.show = {
        addSub: false,
        stepList: false,
        addResultInfo: false,
        resultList: false,
      }
    },
    getList() {
      this.$http.get('/api/subject/mine-join').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.dataSource = result.data;
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
      // this.resetSubForm()
    },
    addResult(e) {
      this.form.addResultInfo.stepId = e.id
      this.form.addResultInfo.isAdd = true
      this.form.addResultInfo.isEdit = false
    },
    uploadResultFile(info) {
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} 上传成功`);
        this.showAddResultInfo()
        this.form.addResultInfo.url = info.file.response.data.path
        this.form.addResultInfo.fileName = info.file.name
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} 上传失败`);
      }
    },
    showStepList(e) {
      this.stepDataSource = e.stepList
      this.show.stepList = true
    },
    closeStepList() {
      this.show.stepList = false
    },
    resetResultInfoForm() {
      this.form.addResultInfo = {
        id: '',
        stepId: '',
        url: '',
        content: '',
        fileName: '',
        isAdd: false,
        isEdit: false,
      }
    },
    showAddResultInfo() {
      this.show.addResultInfo = true
    },
    closeAddResultInfo() {
      this.show.addResultInfo = false
      this.resetResultInfoForm()
    },
    updateResult(e) {
      this.form.addResultInfo = e
      this.form.addResultInfo.stepId = this.stepId
      this.form.addResultInfo.isAdd = false
      this.form.addResultInfo.isEdit = true
    },
    submitResultInfo() {
      this.$http.post('/api/subject/result-info/save', this.form.addResultInfo).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
      this.resetResultInfoForm()
      this.closeAddResultInfo()
    },
    showResultList(e) {
      this.stepId = e.id
      this.form.addResultInfo.stepId = e.id
      this.stepStatus = e.status
      console.log(e)
      this.resultDataSource = e.resultInfos
      this.show.resultList = true
    },
    closeResultList() {
      this.show.resultList = false
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
</style>