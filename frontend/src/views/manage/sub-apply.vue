<template>
  <div>
    <a-divider/>
    <a-form-model ref="ruleForm" :model="form.addSub" :rules="rules.addSub" :label-col="labelCol"
                  :wrapper-col="wrapperCol">
      <a-form-model-item label="项目名称" prop="title">
        <a-input v-model="form.addSub.title" placeholder="请输入项目名称"/>
      </a-form-model-item>
      <a-form-model-item label="科研人员" prop="personList">
        <a-select mode="multiple" :default-value="[]" v-model="form.addSub.personList" placeholder="请选择参与的科研人员">
          <a-select-option v-for="item in userList" :key="item.id" :value="item.id">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="标签" prop="tagsList">
        <a-select mode="multiple" :default-value="[]" v-model="form.addSub.tagsList" placeholder="请选择标签">
          <a-select-option v-for="item in tagsOptions" :key="item.id">{{ item.name }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item ref="stepList" label="阶段目标" prop="stepList">
        <a-form-model-item v-for="(item, index) in form.addSub.stepList" :key="index">
          <a-textarea v-model="form.addSub.stepList[index].content" placeholder="请输入当前阶段目标" :rows="4"/>
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
    <p class="bottom-button">
      <a-button @click="submitSubject" type="primary" style="width: 30%">提交</a-button>
      <a-button @click="resetSubForm" style="width: 30%">重置</a-button>
    </p>
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
            {required: true, message: '请选择参与的科研人员', trigger: 'change'},
          ],
          tagsList: [
            {required: true, message: '请选择标签', trigger: 'change'},
          ],
          stepList: [
            {required: true, message: '阶段目标至少有一个', trigger: 'change'},
          ],
        },
      },
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
      },
      stepId: '',
      tagsOptions: [],
      userList: [],
      tags: [],
      userInfo: {
        name: ''
      },
    };
  },
  activated() {
    this.getTagsList()
    this.getUserList()
  },
  mounted() {
    // this.getTagsList()
    // this.getUserList()
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
    getUserList() {
      this.$http.get('/api/user/getUserListDTO').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.userList = result.data;
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    resetSubForm() {
      const _this = this
      this.$confirm({
        title: '确定要重置吗?',
        content: '当前数据将全部丢失，确定要继续吗',
        cancelText: '取消',
        okText: '确定',
        onOk() {
          _this.form.addSub = {
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
      });
    },
    submitSubject() {
      const _this = this
      _this.$http.post('/api/subject/save', _this.form.addSub).then(result => {
        if (result.status !== 200) {
          _this.$message.error(result.message);
          return;
        }
        _this.$message.success(result.message);
        setTimeout(() => {
          _this.$router.push({path: '/manage-index/mine-subject'})
        }, 2000)
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    removeStep() {
      if (this.form.addSub.stepList.length > 1)
        this.form.addSub.stepList.pop()
    },
    addStep() {
      let step = {indexNum: this.form.addSub.stepList.length, content: ''}
      this.form.addSub.stepList.push(step)
    },
  }
}
</script>

<style scoped>
.bottom-button {
  margin-top: 8vh;
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-around;
}
</style>