<template>
  <div>
    <a-modal :visible="show.discuss" @cancel="closeDiscuss" :footer="null" width="50%">
      <div class="discuss">
        <a-comment v-for="item in discussList">
          <span slot="actions" @click="reply(item)">回复</span>
          <span slot="actions" @click="removeDiscuss(item)">删除</span>
          <p slot="author">{{ item.user.name }} <span>{{ item.createTime }}</span></p>
          <p slot="content"> {{ item.content }} </p>
          <a-comment v-for="item in item.children">
            <span slot="actions" @click="removeDiscuss(item)">删除</span>
            <p slot="author">{{ item.user.name }} <span>{{ item.createTime }}</span></p>
            <p slot="content"> {{ item.content }} </p>
          </a-comment>
        </a-comment>
      </div>
      <a-comment>
        <div slot="content">
          <a-form-item>
            <a-textarea :rows="4" v-model="form.content" :placeholder="contentPlaceholder"/>
          </a-form-item>
          <a-form-item>
            <a-button html-type="submit" :loading="false" type="primary" @click="submitDiscuss">发表</a-button>
          </a-form-item>
        </div>
      </a-comment>
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
        <a-tag v-if="status === 0" color="green">{{ status }}</a-tag>
        <a-tag v-if="status === 1" color="geekblue">{{ status }}</a-tag>
        <a-tag v-if="status === 2" color="pink">{{ status }}</a-tag>
      </span>
      <span slot="createBy" slot-scope="createBy">{{ createBy.name }}</span>
      <span slot="beginTime" slot-scope="text">{{ text }}</span>
      <span slot="endTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="primary" size="small" @click="showDiscuss(record)">查看评论</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="showStepList(record)">查看详情</a-button>
        <a-divider type="vertical"/>
      </span>
    </a-table>
  </div>
</template>

<script>
export default {
  name: "subject-discuss",
  data() {
    return {
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
      subjectId: '',
      parentId: '',
      show: {
        stepList: false,
        resultList: false,
        discuss: false,
      },
      userInfo: {
        name: ''
      },
      form: {
        type: 0,
        subjectId: '',
        parentId: '',
        content: '',
      },
      discussList: [],
      contentPlaceholder: '新留言'
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    refreshDiscuss() {
      this.$http.get(`/api/discuss/list/${this.subjectId}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.discussList = result.data;
          console.log('data--->', result.data)
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    removeDiscuss(e) {
      this.$http.get(`/api/discuss/removeById/${e.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.$message.success(result.message);
        this.refreshDiscuss()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    resetForm() {
      this.form.type = 0
      this.form.content = ''
      this.contentPlaceholder = '新留言'
      this.$http.get(`/api/discuss/list/${this.subjectId}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.discussList = result.data;
          console.log('data--->', result.data)
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    reply(e) {
      this.contentPlaceholder = `回复${e.user.name}`
      this.parentId = e.id
      this.form.type = 1
    },
    submitDiscuss() {
      if (this.form.type === 0) {
        this.form.subjectId = this.subjectId
        this.form.parentId = ''
      } else {
        this.form.parentId = this.parentId
        this.form.subjectId = ''
      }
      this.$http.post(`/api/discuss/save`, this.form).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.$message.success(result.message);
        this.resetForm()
        this.refreshDiscuss()
        console.log('add--->', result.data)
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    showDiscuss(e) {
      this.subjectId = e.id
      this.show.discuss = true
      this.$http.get(`/api/discuss/list/${e.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.discussList = result.data;
          console.log('data--->', result.data)
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    closeDiscuss() {
      this.show.discuss = false
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
    },
    showStepList(e) {
      this.stepDataSource = e.stepList
      this.show.stepList = true
    },
    closeStepList() {
      this.show.stepList = false
    },
    showResultList(e) {
      this.resultDataSource = e.resultInfos
      this.show.resultList = true
    },
    closeResultList() {
      this.show.resultList = false
    },
    downloadFile(e) {
      let fileName = e.fileName
      this.$http.get(e.url, {responseType: 'blob'}).then((res) => {
        let blob = res;
        let reader = new FileReader();
        reader.readAsDataURL(blob);
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
.discuss {
  height: 50vh;
  overflow: auto;
  margin: 10px 10px 0 10px;
}

.discuss::-webkit-scrollbar { /*滚动条整体样式*/
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
  margin-right: 10px;
}

.discuss::-webkit-scrollbar-thumb { /*滚动条里面小方块*/
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #535353;
}

.discuss::-webkit-scrollbar-track { /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background: #EDEDED;
}
</style>