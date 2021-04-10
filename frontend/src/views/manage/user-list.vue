<template>
  <div>
    <a-button @click="addTags">添加用户</a-button>
    <a-divider/>
    <!--    <a-drawer width="30%" :visible="show.userInfo" @close="closeForm">-->
    <!--      <a-form-model :model="form.addPaper" :rules="rules.addPaper" :label-col="labelCol" :wrapper-col="wrapperCol">-->
    <!--        <a-form-model-item label="标题" prop="title">-->
    <!--          <a-input v-model="form.addPaper.title" placeholder="请输入标题"/>-->
    <!--        </a-form-model-item>-->
    <!--        <a-form-model-item label="标签" prop="tagsList">-->
    <!--          <a-select mode="multiple" :default-value="[]" v-model="form.addPaper.tagsList" placeholder="请选择标签">-->
    <!--            <a-select-option v-for="item in tagsOptions" :key="item.id">-->
    <!--              {{ item.name }}-->
    <!--            </a-select-option>-->
    <!--          </a-select>-->
    <!--        </a-form-model-item>-->
    <!--        <a-form-model-item label="附件">-->
    <!--          <a-upload name="file" :multiple="true" action="/api/uploadFile" :headers="headers" @change="uploadFile"-->
    <!--                    :default-file-list="form.addPaper.fileList">-->
    <!--            <a-button type="dashed" size="small">添加附件</a-button>-->
    <!--          </a-upload>-->
    <!--        </a-form-model-item>-->
    <!--        <a-form-model-item label="正文" prop="content">-->
    <!--          <a-form-model-item key="index">-->
    <!--            <div id="editor"></div>-->
    <!--          </a-form-model-item>-->
    <!--        </a-form-model-item>-->
    <!--      </a-form-model>-->
    <!--      <div class="drawer-bottom-button">-->
    <!--        <a-button :style="{ marginRight: '8px' }" @click="closeForm"> 关闭</a-button>-->
    <!--        <a-button type="primary" @click="submitForm"> 发布</a-button>-->
    <!--      </div>-->
    <!--    </a-drawer>-->
    <a-table :data-source="dataSource" rowKey="id" :columns="columns">
      <span slot="tags" slot-scope="tags">
        <a-tag v-for="tag in tags" :rowKey="tag.id" :color="tag.color">{{ tag.name }}</a-tag>
      </span>
      <span slot="role" slot-scope="role">
          <a-tag color="green">管理员</a-tag>
          <a-tag color="green">学生</a-tag>
          <a-tag color="green">教师</a-tag>
        <!--          <a-tag color="green" v-if="scope.row.role === '0'">教师</a-tag>-->
      </span>
      <span slot="status" slot-scope="status">
        <a-tag color="green" v-if="status === 0">正常</a-tag>
        <a-tag color="red" v-else>禁用</a-tag>
      </span>
      <span slot="action" slot-scope="text, record">
        <a-button type="default" size="small" @click="changeStatus(record)" v-if="record.status === 0">禁用</a-button>
        <a-button type="primary" size="small" @click="changeStatus(record)" v-if="record.status === 1">启用</a-button>
        <a-divider type="vertical"/>
        <a-button type="danger" size="small" @click="removeUser(record)">删除</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="editUser(record)">编辑</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="changePassword(record)">修改密码</a-button>
        <a-divider type="vertical"/>
      </span>
    </a-table>
  </div>
</template>

<script>
export default {
  name: "sub-apply",
  data() {
    return {
      tagsColors: [
        {key: '红色', value: 'red'},
        {key: '绿色', value: 'green'},
        {key: '蓝色', value: 'blue'},
        {key: '粉色', value: 'pink'},
      ],
      rules: {
        tags: {
          name: [
            {required: true, message: '请输入标签名称', trigger: 'change'},
            {min: 2, max: 16, message: '长度为2-16个字符', trigger: 'change'},
          ],
        },
      },
      columns: [
        {
          dataIndex: 'username',
          key: 'username',
          title: '用户名',
        },
        {
          dataIndex: 'name',
          title: '姓名',
          key: 'name',
        },
        {
          dataIndex: 'mail',
          title: '邮箱',
          key: 'mail',
        },
        {
          dataIndex: 'tags',
          title: '标签',
          key: 'tags',
          scopedSlots: {customRender: 'tags'}
        },
        {
          dataIndex: 'role',
          title: '角色',
          key: 'role',
          scopedSlots: {customRender: 'role'}
        },
        {
          dataIndex: 'status',
          title: '状态',
          key: 'status',
          scopedSlots: {customRender: 'status'}
        },
        {
          title: '操作',
          key: 'action',
          scopedSlots: {customRender: 'action'},
        },
      ],
      dataSource: [],

      labelCol: {span: 5},
      wrapperCol: {span: 16},
      openKeys: [],
      form: {
        status: 0,
        tags: {
          id: '',
          name: '',
          color: '',
        },
      },
      show: {
        tags: false,
        userInfo: false
      },
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    refreshData() {
      this.getList()
    },
    getList() {
      this.$http.get('/api/user/list').then(result => {
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
      this.resetTagsForm()
    },

    changeStatus(e) {
      const _this = this;
      this.$confirm({
        title: '确认操作?',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          _this.form = e
          _this.form.status = Math.abs(_this.form.status - 1)
          _this.$http.post('/api/user/changeStatus', _this.form).then(result => {
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              _this.$message.error(result.message);
              return
            }
            _this.$message.success(result.message)
          }).catch(function (error) {
            console.log('请求出现错误:', error);
          });
        },
      });
    },
    removeUser(e) {
      const _this = this;
      this.$confirm({
        title: '确认操作?',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          console.log(e)
          _this.$http.post('/api/user/delUser', e).then(result => {
            console.log('通过api获取到的数据:', result);
            if (result.status !== 200) {
              _this.$message.error(result.message);
              return
            }
            _this.$message.success(result.message)
          }).catch(function (error) {
            console.log('请求出现错误:', error);
          });
        },
      });
    },
    editUser(e) {
      this.show.userInfo = true
      const _this = this;
      _this.$confirm('确认操作？').then(e => {
        _this.$http.post('/user/delUser', _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            _this.$message.error(result.message);
            return
          }
          _this.$message.success(result.message)
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      });
    },
    changePassword(e) {
      this.show.userInfo = true
      const _this = this;
      _this.$confirm('确认操作？').then(e => {
        _this.$http.post('/user/delUser', _this.form).then(response => {
          const result = response.data;
          console.log('通过api获取到的数据:', result);
          if (result.status !== 200) {
            _this.$message.error(result.message);
            return
          }
          _this.$message.success(result.message)
        }).catch(function (error) {
          console.log('请求出现错误:', error);
        });
      });
    },


    resetTagsForm() {
      this.form.tags = {
        id: '',
        name: '',
        color: '',
      }
    },
    submitTags() {
      this.$http.post('/api/tags/save', this.form.tags).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        this.show.tags = false
        this.resetTagsForm()
        this.refreshData()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });

    },
    removeTags(e) {
      const _this = this
      _this.$confirm({
        title: '确定要删除标签吗?',
        content: '确定要删除标签吗?删除后无法恢复！',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          _this.$http.get(`/api/tags/removeById/${e.id}`).then(result => {
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