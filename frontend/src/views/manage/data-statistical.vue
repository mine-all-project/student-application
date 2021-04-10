<template>
  <div>
    <a-button @click="showBaseCharts">数据总览</a-button>
    <a-divider/>
    <a-modal :visible="show.baseCharts" @cancel="closeBaseCharts" :footer="null" width="50%">
      <div id="base-charts" style="width: 100%;height:400px;"></div>
    </a-modal>
    <a-modal :visible="show.itemCharts" @cancel="closeItemCharts" :footer="null" width="70%">
      <div id="item-charts" style="width: 100%;height:400px;"></div>
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
        <a-button type="default" size="small" @click="showResultList(record)">查看成果</a-button>
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
        <a-button type="primary" size="small" @click="showItemCharts(record)">查看分析</a-button>
        <a-divider type="vertical"/>
        <a-button type="primary" size="small" @click="showStepList(record)">查看详情</a-button>
        <a-divider type="vertical"/>
      </span>
    </a-table>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "data-statistical",
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
      stepId: '',
      show: {
        stepList: false,
        resultList: false,
        itemCharts: false,
        baseCharts: false,
      },
      userInfo: {
        name: ''
      },
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    showBaseCharts() {
      this.show.baseCharts = true
      console.log(this.dataSource)
      this.$nextTick(() => {
        let seriesData = {
          status0: {name: '立项', value: 0},
          status1: {name: '在研', value: 0},
          status2: {name: '结题', value: 0},
          isEnd: {name: '立项', value: 0},
          isShare: {name: '共享', value: 0},
        }
        this.dataSource.forEach((e => {
          if (e.status === 0) {
            seriesData.status0.value++
          }
          if (e.status === 1) {
            seriesData.status1.value++
          }
          if (e.status === 2) {
            seriesData.status2.value++
          }
          if (e.endTime) {
            seriesData.isEnd.value++
          }
          if (!e.isShare) {
            seriesData.isShare.value++
          }
          console.log(e)

        }))
        console.log(seriesData)
        let myChart = echarts.init(document.getElementById('base-charts'));
        let option = {
          title: {
            text: '科研项目数据统计',
            subtext: '',
            left: 'center'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          tooltip: {
            trigger: "item",
            axisPointer: {
              type: "shadow"
            },
            // formatter: ((e) => {
            //   console.log(e[0])
            //   return `<p><b>阶段目标：</b>${e[0].axisValue}</p><p><b>成果：</b>${e[0].data}</p>`
            // })
          },
          series: [
            {
              name: '项目状态',
              type: 'pie',
              radius: '50%',
              data: [
                seriesData.status0,
                seriesData.status1,
                seriesData.status2,
                // seriesData.isEnd,
                seriesData.isShare,
              ],
              areaStyle: {},
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            },
          ]
        };
        console.log(myChart)
        myChart.setOption(option);
      })
    },
    closeBaseCharts() {
      this.show.baseCharts = false
    },
    showItemCharts(e) {
      this.show.itemCharts = true
      this.$nextTick(() => {
        let xData = []
        let yData = []
        let seriesData = []
        e.stepList.forEach((r => {
          console.log(r)
          xData.push(r.content)
          seriesData.push(r.resultInfos.length)
        }))
        let myChart = echarts.init(document.getElementById('item-charts'));
        let option = {
          legend: {},
          color: ["#3398DB"],
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow"
            },
            formatter: ((e) => {
              console.log(e[0])
              return `<p><b>阶段目标：</b>${e[0].axisValue}</p><p><b>成果：</b>${e[0].data}</p>`
            })
          },
          xAxis: {
            type: 'category',
            boundaryGap: true,
            data: xData,
            name: '阶段目标',
            nameLocation: 'end',
          },
          yAxis: {
            type: 'value',
            name: '成果',
            show: true,
            minInterval: 1,
          },
          series: [{
            data: seriesData,
            type: 'bar',
            areaStyle: {},
          }]
        };
        console.log(myChart)
        myChart.setOption(option);
      })
    },
    closeItemCharts() {
      this.show.itemCharts = false
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
      this.stepId = e.id
      this.resultDataSource = e.resultInfos
      this.show.resultList = true
    },
    closeResultList() {
      this.show.resultList = false
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
  }
}
</script>

<style scoped>
</style>