<template>
  <div>
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
        <a-button type="default" size="small" @click="showResultList(record)">查看成果</a-button>
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
        <a-tag v-if="status === 0" color="green">立项</a-tag>
        <a-tag v-if="status === 1" color="geekblue">在研</a-tag>
        <a-tag v-if="status === 2" color="pink">结题</a-tag>
      </span>
      <span slot="isShare" slot-scope="isShare">
        <a-tag v-if="isShare === 0" color="green">已共享</a-tag>
        <a-tag v-if="isShare === 1" color="blue">未共享</a-tag>
      </span>
      <span slot="createBy" slot-scope="createBy">{{ createBy.name }}</span>
      <span slot="createTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="primary" size="small" @click="showStepList(record)">查看详情</a-button>
         <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="acceptAudit(record)">通过审批</a-button>
         <a-divider type="vertical"/>
        <a-button type="default" size="small" @click="unAcceptedAudit(record)">请求驳回</a-button>
      </span>
    </a-table>
  </div>
</template>

<script>
import commonApi from '@/api/CommonApi'

export default {
  name: "mine-pull",
  data() {
    return {
      userInfo: {},
      columns: [
        {
          dataIndex: 'title',
          slots: {title: 'customTitle'},
          scopedSlots: {customRender: 'title'},
        },
        {
          dataIndex: 'tags',
          title: '标签',
          scopedSlots: {customRender: 'tags'},
        },
        {
          dataIndex: 'createTime',
          title: '发起时间',
          scopedSlots: {customRender: 'createTime'},
        },
        {
          dataIndex: 'createBy',
          title: '发起',
          scopedSlots: {customRender: 'createBy'},
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
        },
        {
          dataIndex: 'content',
          title: '目标',
          scopedSlots: {customRender: 'content'},
          ellipsis: true,
        },
      ],
      stepDataSource: [],
      labelCol: {span: 5},
      wrapperCol: {span: 16},
      show: {
        stepList: false,
      },
    };
  },
  mounted() {
    this.initData()
  },
  methods: {
    initData() {
      commonApi.getUserInfo().then((result) => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.userInfo = result.data;
        this.getList()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    refreshData() {
      this.getList()
      this.show.stepList = false
    },
    getList() {
      this.$http.get('/api/subject/audit-list').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        console.log(result.data)
        // result.data.forEach((e) => {
        //   e.userListId = e.shareUserList.map((r => {
        //     return r.id
        //   }))
        // })
        this.dataSource = result.data;
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    requestPull(e) {
      console.log(e)
      this.$http.get(`/api/request-pull/save/${e.subject.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.$message.success('申请成功，请等待审核 ');
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
    acceptAudit(e) {
      this.$http.get(`/api/subject/audit/accept/${e.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.$message.success(result.message);
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    unAcceptedAudit(e) {
      this.$http.get(`/api/subject/audit/unaccepted/${e.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.$message.success(result.message);
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
  }
}
</script>

<style scoped>
</style>