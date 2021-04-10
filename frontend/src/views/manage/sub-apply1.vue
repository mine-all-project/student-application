<template>
  <div>
    <a-button @click="addSub">发起申报</a-button>
    <a-divider/>
    <a-modal :visible="show.addSub" @cancel="closeAddSub" @ok="submitSubject" cancelText="取消" okText="保存">
      <a-form-model ref="ruleForm" :model="form.addSub" :rules="rules.addSub" :label-col="labelCol"
                    :wrapper-col="wrapperCol">
        <a-form-model-item label="课题名称" prop="title">
          <a-input v-model="form.addSub.title" placeholder="请输入课题名称"/>
        </a-form-model-item>
        <a-form-model-item label="科研人员" prop="personList">
          <a-select mode="multiple" :default-value="[]" v-model="form.addSub.personList" placeholder="请选择参与的科研人员">
            <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
              {{ (i + 9).toString(36) + i }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="标签" prop="tagsList">
          <a-select mode="multiple" :default-value="[]" v-model="form.addSub.tagsList" placeholder="请选择标签">
            <a-select-option v-for="item in tagsOptions" :key="item.id">
              {{ item.name }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item ref="stepList" label="阶段目标" prop="stepList">
          <a-form-model-item v-for="(item, index) in form.addSub.stepList" :key="index">
            <a-input v-model="form.addSub.stepList[index].content" placeholder="请输入当前阶段目标"/>
          </a-form-model-item>
          <a-button type="dashed" style="width: 45%" @click="addStep">
            <a-icon type="plus-circle" style="color: #109bf1"/>
            增加目标
          </a-button>
          <a-divider type="vertical"/>
          <a-button type="dashed" style="width: 45%" @click="removeStep">
            <a-icon type="minus-circle" style="color: red"/>
            减少目标
          </a-button>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
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
      <a-table :columns="stepColumns" :data-source="stepDataSource" rowKey="id" :pagination="false">
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
        <a-button type="primary" size="small" @click="endStep(record)">结束</a-button>
        <a-divider type="vertical"/>
        <a-button type="default" size="small" @click="showResultList(record)">查看成果</a-button>
        <a-divider type="vertical"/>
        <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers"
                  :showUploadList="false" @change="uploadResultFile">
          <a-button type="dashed" size="small" @click="addResult(record)">添加成果</a-button>
        </a-upload>
      </span>
      </a-table>
    </a-modal>
    <a-modal :visible="show.resultList" @cancel="closeResultList" :footer="null" width="60%">
      <a-table :columns="resultColumns" :data-source="resultDataSource" rowKey="id" :pagination="false">
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
        <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers"
                  :showUploadList="false" @change="uploadResultFile">
          <a-button type="default" size="small" @click="updateResult(record)">更新</a-button>
        </a-upload>
      </span>
      </a-table>
    </a-modal>
    <a-table :columns="columns" :data-source="dataSource" rowKey="id" :pagination="false">
      <span slot="customTitle"> 课题名称</span>
      <span slot="name" slot-scope="text">{{ text }}</span>
      <span slot="tags" slot-scope="tags">
        <a-tag v-for="tag in tags" :rowKey="tag.id" :color="tag.color">{{ tag.name }}</a-tag>
      </span>
      <span slot="status" slot-scope="status">
        <a-tag v-if="status === 0" color="green">{{ status }}</a-tag>
        <a-tag v-if="status === 1" color="geekblue">{{ status }}</a-tag>
        <a-tag v-if="status === 2" color="pink">{{ status }}</a-tag>
      </span>
      <span slot="createBy" slot-scope="createBy">{{ createBy.name }}</span>
      <span slot="beginTime" slot-scope="text">{{ text }}</span>
      <span slot="endTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="danger" size="small" @click="endSub(record)">申请结题</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small">测试</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="showStepList(record)">查看详情</a-button>
        <a-divider type="vertical"/>
        <!--        <a-dropdown>-->
        <!--          <a-button type="default" size="small">成果-->
        <!--            <a class="ant-dropdown-link"><a-icon type="down" :style="{color:'#000'}"/></a>-->
        <!--          </a-button>-->
        <!--          <a-menu slot="overlay">-->
        <!--                <a-menu-item key="1">-->
        <!--                   <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers">-->
        <!--                      <a-button type="default" size="small">添加</a-button>-->
        <!--                   </a-upload>-->
        <!--                </a-menu-item>-->
        <!--                <a-menu-item key="2" @click="showResultList(record)">-->
        <!--                  <a-button type="default" size="small">查看</a-button>-->
        <!--                </a-menu-item>-->
        <!--              </a-menu>-->
        <!--          <a-button style="margin-left: 8px"> Button-->
        <!--            <a-icon type="down"/>-->
        <!--          </a-button>-->
        <!--        </a-dropdown>-->
      </span>
    </a-table>
  </div>
</template>

<script>
export default {
  name: "sub-apply",
  data() {
    return {
      rules: {
        addSub: {
          title: [
            {required: true, message: '请输入课题名称', trigger: 'change'},
            {min: 2, max: 16, message: '长度为2-16个字符', trigger: 'change'},
          ],
          personList: [
            // {required: true, message: '请选择参与科研人员', trigger: 'change'},
          ],
          tagsList: [
            {required: true, message: '请选择标签', trigger: 'change'},
          ],
          stepList: [
            {required: true, message: '阶段目标至少有一个', trigger: 'change'},
          ],
        },
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
          dataIndex: 'createBy',
          title: '发起人',
          scopedSlots: {customRender: 'createBy'},
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
      openKeys: [],
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
      show: {
        addSub: false,
        stepList: false,
        addResultInfo: false,
        resultList: false,
      },
      tagsOptions: [],
      tags: [],
      userInfo: {
        name: ''
      },
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
      this.$http.get('/api/subject/list').then(result => {
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
    resetSubForm() {
      this.form.addSub = {
        title: '',
        personList: [],
        stepList: [
          {
            index: 0,
            content: '',
          }
        ]
      }
    },
    addSub() {
      this.show.addSub = true
      this.getTagsList()
    },
    closeAddSub() {
      this.show.addSub = false
      this.resetSubForm()
    },
    submitSubject() {
      this.$http.post('/api/subject/save', this.form.addSub).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
      this.resetSubForm()
    },
    removeStep() {
      if (this.form.addSub.stepList.length > 1)
        this.form.addSub.stepList.pop()
    },
    addStep() {
      let step = {indexNum: this.form.addSub.stepList.length, content: ''}
      this.form.addSub.stepList.push(step)
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
      this.resultDataSource = e.resultInfos
      this.show.resultList = true
    },
    closeResultList() {
      this.show.resultList = false
    },
    endStep(e) {
      const _this = this
      this.$confirm({
        title: '确定要结束该阶段吗吗?',
        content: '如您已完成该阶段目标可点击确定',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          _this.$http.get(`/api/subject/step/end/${e.id}`).then(result => {
            if (result.status !== 200) {
              _this.$message.error(result.message);
              return;
            }
            if (result.data !== null) {
              _this.dataSource = result.data;
            }
            _this.$message.success(result.message);
            _this.refreshData()
          }).catch(function (error) {
            console.error('出现错误:', error);
          });
        },
        onCancel() {
        },
      });
    },
    downloadFile(e) {
      let fileName = e.fileName
      // window.location.href = e.url
      this.$http.get(e.url, {responseType: 'blob'}).then((res) => {
        let blob = res;
        // FileReader主要用于将文件内容读入内存
        let reader = new FileReader();
        reader.readAsDataURL(blob);
        // onload当读取操作成功完成时调用
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
    endSub(e) {
      const _this = this
      this.$confirm({
        title: '确定要结束课题吗?',
        content: '确定要结束课题吗?结束后无法继续！',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          _this.$http.get(`/api/subject/end/${e.id}`).then(result => {
            if (result.status !== 200) {
              _this.$message.error(result.message);
              return;
            }
            if (result.data !== null) {
              _this.dataSource = result.data;
            }
            _this.$message.success(result.message);
            _this.refreshData()
          }).catch(function (error) {
            console.error('出现错误:', error);
          });
        },
      });
    },
  }
}
</script>

<style scoped>
</style>