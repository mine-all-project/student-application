<template>
  <div>
    <a-button @click="addTags">添加标签</a-button>
    <a-divider/>
    <a-modal :visible="show.tags" @cancel="closeAddTags" @ok="submitTags" cancelText="取消" okText="保存">
      <a-form-model ref="ruleForm" :model="form.tags" :rules="rules.tags" :label-col="labelCol"
                    :wrapper-col="wrapperCol">
        <a-form-model-item label="标签名称" prop="name">
          <a-input v-model="form.tags.name" placeholder="请输入标签名称"/>
        </a-form-model-item>
        <a-form-model-item label="显示样式" prop="color">
          <a-select :default-value="[]" v-model="form.tags.color" placeholder="请选择标签样式">
            <a-select-option v-for="item in tagsColors" :key="item.key" :value="item.value">
              {{ item.key }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-table :columns="columns" :data-source="dataSource">
      <span slot="name" slot-scope="text">{{ text }}</span>
      <span slot="color" slot-scope="color,record">
        <a-tag :color="color">{{ record.name }}</a-tag>
      </span>
      <span slot="action" slot-scope="text, record">
        <a-button type="danger" size="small" @click="removeTags(record)">删除</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="editTags(record)">编辑</a-button>
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
            {required: true, message: '请输入课题名称', trigger: 'change'},
            {min: 2, max: 16, message: '长度为2-16个字符', trigger: 'change'},
          ],
        },
      },
      columns: [
        {
          dataIndex: 'name',
          key: 'name',
          title: '标签',
          scopedSlots: {customRender: 'name'},
        },
        {
          dataIndex: 'color',
          title: '样式',
          key: 'color',
          scopedSlots: {customRender: 'color'},
        },
        {
          dataIndex: 'updateTime',
          title: '最后修改时间',
          key: 'updateTime',
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
        tags: {
          id: '',
          name: '',
          color: '',
        },
      },
      show: {
        tags: false,
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
      this.$http.get('/api/tags/list').then(result => {
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
    resetTagsForm() {
      this.form.tags = {
        id: '',
        name: '',
        color: '',
      }
    },
    addTags() {
      this.show.tags = true
    },
    editTags(e) {
      this.show.tags = true
      this.form.tags = e
    },
    closeAddTags() {
      this.show.tags = false
      this.resetSubForm()
    },
    submitTags() {
      this.$http.post('/api/tags/save', this.form.tags).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
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