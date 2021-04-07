<template>
  <div>
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
      <span slot="requestBy" slot-scope="requestBy">{{ requestBy.name }}</span>
      <span slot="beginTime" slot-scope="text">{{ text }}</span>
      <span slot="endTime" slot-scope="text">{{ text }}</span>
      <span slot="action" slot-scope="text, record">
        <a-button type="primary" size="small" @click="acceptRequest(record)">同意</a-button>
        <a-divider type="vertical"/>
        <a-button type="danger" size="small" @click="unAcceptedRequest(record)">拒绝</a-button>
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
          dataIndex: 'subject.title',
          slots: {title: 'customTitle'},
          scopedSlots: {customRender: 'title'},
        },
        {
          dataIndex: 'subject.status',
          title: '状态',
          scopedSlots: {customRender: 'status'},
        },
        {
          dataIndex: 'subject.tags',
          title: '标签',
          scopedSlots: {customRender: 'tags'},
        },
        {
          dataIndex: 'subject.beginTime',
          title: '开始时间',
          scopedSlots: {customRender: 'beginTime'},
        },
        {
          dataIndex: 'subject.endTime',
          title: '结束时间',
          scopedSlots: {customRender: 'endTime'},
        },
        {
          dataIndex: 'subject.createBy',
          title: '创建人',
          scopedSlots: {customRender: 'createBy'},
        },
        {
          dataIndex: 'user.name',
          title: '申请人',
          scopedSlots: {customRender: 'user'},
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
    };
  },
  mounted() {
    this.initData()
  },
  methods: {
    initData() {
      this.getList()
    },
    refreshData() {
      this.getList()
    },
    getList() {
      this.$http.get('/api/request-pull/list').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        console.log(result.data)
        this.dataSource = result.data;
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    requestPull(e) {
      console.log(e)
      this.$http.get(`/api/subject/request-pull/${e.subject.id}`).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    acceptRequest(e) {
      console.log(e)
      this.$http.get(`/api/request-pull/accept/${e.id}`).then(result => {
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
    unAcceptedRequest(e) {
      console.log(e)
      this.$http.get(`/api/request-pull/unaccepted/${e.id}`).then(result => {
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