<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
                  :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
                  :border="contents.tableBorder"
                  :fit="contents.tableFit"
                  :stripe="contents.tableStripe"
                  :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
                  v-if="isAuth('forum','查看')"
                  :data="dataList"
                  v-loading="dataListLoading">
          <el-table-column label="索引" :align="contents.tableAlign" v-if="contents.tableIndex" type="index" width="50"/>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="date" :header-align="contents.tableAlign" label="时间">
            <template slot-scope="scope">
              {{ scope.row.date[0] }}-{{ scope.row.date[1] }}-{{ scope.row.date[2] }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="username"
                           :header-align="contents.tableAlign"
                           label="经销店">
            <template slot-scope="scope">
              {{ scope.row.store.name }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="status" :header-align="contents.tableAlign" label="状态">
            <template slot-scope="scope"> {{ scope.row.status }}</template>
          </el-table-column>
          <el-table-column width="300" :align="contents.tableAlign"
                           :header-align="contents.tableAlign"
                           label="操作">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status !=='已完成'" type="success" size="mini"
                         @click="finish(scope.row.id,)">完成
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

  </div>
</template>
<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";

export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag: false,
      contents: {
        "searchBtnFontColor": "#333",
        "pagePosition": "1",
        "inputFontSize": "14px",
        "inputBorderRadius": "10px",
        "tableBtnDelFontColor": "#333",
        "tableBtnIconPosition": "1",
        "searchBtnHeight": "40px",
        "tableBgColor": "#f5f5f5",
        "inputIconColor": "#C0C4CC",
        "searchBtnBorderRadius": "10px",
        "tableStripe": true,
        "btnAdAllWarnFontColor": "#333",
        "tableBtnDelBgColor": "rgba(184, 230, 253, 1)",
        "searchBtnIcon": "1",
        "tableSize": "medium",
        "searchBtnBorderStyle": "solid",
        "text": {
          "padding": "0 40px",
          "boxShadow": "0 0 0px rgba(0,0,0,.1)",
          "margin": "0 auto",
          "borderColor": "rgba(0,0,0,.3)",
          "backgroundColor": "rgba(247, 247, 247, 0)",
          "color": "#333",
          "borderRadius": "6px",
          "borderWidth": "0",
          "width": "auto",
          "lineHeight": "650px",
          "fontSize": "24px",
          "borderStyle": "solid"
        },
        "tableSelection": true,
        "searchBtnBorderWidth": "5px",
        "tableContentFontSize": "14px",
        "searchBtnBgColor": "rgba(185, 231, 254, 1)",
        "inputTitleSize": "14px",
        "btnAdAllBorderColor": "rgba(238, 233, 207, 1)",
        "pageJumper": true,
        "btnAdAllIconPosition": "1",
        "searchBoxPosition": "1",
        "tableBtnDetailFontColor": "#333",
        "tableBtnHeight": "40px",
        "pagePager": true,
        "searchBtnBorderColor": "rgba(238, 233, 207, 1)",
        "tableHeaderFontColor": "#909399",
        "inputTitle": "1",
        "tableBtnBorderRadius": "10px",
        "btnAdAllFont": "1",
        "btnAdAllDelFontColor": "#333",
        "tableBtnIcon": "1",
        "btnAdAllHeight": "40px",
        "btnAdAllWarnBgColor": "rgba(185, 231, 254, 1)",
        "btnAdAllBorderWidth": "5px",
        "tableStripeFontColor": "#606266",
        "tableBtnBorderStyle": "solid",
        "inputHeight": "40px",
        "btnAdAllBorderRadius": "10px",
        "btnAdAllDelBgColor": "rgba(185, 231, 254, 1)",
        "pagePrevNext": true,
        "btnAdAllAddBgColor": "rgba(185, 231, 254, 1)",
        "searchBtnFont": "1",
        "tableIndex": true,
        "btnAdAllIcon": "1",
        "tableSortable": true,
        "pageSizes": true,
        "tableFit": true,
        "pageBtnBG": false,
        "searchBtnFontSize": "14px",
        "tableBtnEditBgColor": "rgba(184, 230, 253, 1)",
        "box": {
          "padding": "10px 20px",
          "boxShadow": "0 0 6px rgba(0,0,0,0)",
          "flag": "2",
          "backgroundImage": "",
          "background": "#FFFDF1"
        },
        "inputBorderWidth": "6px",
        "inputFontPosition": "1",
        "inputFontColor": "#333",
        "pageEachNum": 10,
        "tableHeaderBgColor": "rgba(218, 214, 192, 1)",
        "inputTitleColor": "#333",
        "btnAdAllBoxPosition": "1",
        "tableBtnDetailBgColor": "rgba(184, 230, 253, 1)",
        "inputIcon": "1",
        "searchBtnIconPosition": "1",
        "btnAdAllFontSize": "14px",
        "inputBorderStyle": "solid",
        "tableHoverFontColor": "#333",
        "inputBgColor": "rgba(184, 230, 253, 1)",
        "pageStyle": false,
        "pageTotal": true,
        "btnAdAllAddFontColor": "#333",
        "tableBtnFont": "1",
        "tableContentFontColor": "#606266",
        "inputBorderColor": "rgba(238, 233, 207, 1)",
        "tableShowHeader": true,
        "tableHoverBgColor": "rgba(184, 230, 253, 1)",
        "tableBtnFontSize": "14px",
        "tableBtnBorderColor": "rgba(238, 233, 207, 1)",
        "inputIconPosition": "2",
        "tableBorder": true,
        "btnAdAllBorderStyle": "solid",
        "tableBtnBorderWidth": "6px",
        "tableStripeBgColor": "rgba(184, 230, 253, 0.78)",
        "tableBtnEditFontColor": "#333",
        "tableAlign": "center"
      },
      layouts: '',


    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {

  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {
    finish(id) {
      this.$confirm(`确定已完成?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "yuyue/finish/"+id,
          method: "post",
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
          let textAlign = 'left'
          if (this.contents.inputFontPosition == 2) textAlign = 'center'
          if (this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if (this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(() => {
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
            el.style.lineHeight = this.contents.inputHeight
          })
        }, 10)

      })
    },
    // 表格
    // rowStyle({ row, rowIndex}) {
    //   if (rowIndex % 2 == 1) {
    //     if(this.contents.tableStripe) {
    //       return {color:this.contents.tableStripeFontColor}
    //     }
    //   } else {
    //     return ''
    //   }
    // },
    // cellStyle({ row, rowIndex}){
    //   if (rowIndex % 2 == 1) {
    //     if(this.contents.tableStripe) {
    //       return {backgroundColor:this.contents.tableStripeBgColor}
    //     }
    //   } else {
    //     return ''
    //   }
    // },
    headerRowStyle({row, rowIndex}) {
      return {color: this.contents.tableHeaderFontColor}
    },
    headerCellStyle({row, rowIndex}) {
      return {backgroundColor: this.contents.tableHeaderBgColor}
    },
    // 表格按钮
    contentTableBtnStyleChange() {
    },

    init() {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.$http({url: "yuyue/flist/试驾", method: "get"}).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.data;
        }
        this.dataListLoading = false;
      });
    },


  }

};
</script>
<style lang="scss" scoped>
.slt {
  margin: 0 !important;
  display: flex;
}

.ad {
  margin: 0 !important;
  display: flex;
}

.pages {
  & /deep/ el-pagination__sizes {
    & /deep/ el-input__inner {
      height: 22px;
      line-height: 22px;
    }
  }
}


.el-button + .el-button {
  margin: 0;
}

.tables {
  & /deep/ .el-button--success {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 6px;
    border-style: solid;
    border-color: rgba(238, 233, 207, 1);
    border-radius: 10px;
    background-color: rgba(184, 230, 253, 1);
  }

  & /deep/ .el-button--primary {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 6px;
    border-style: solid;
    border-color: rgba(238, 233, 207, 1);
    border-radius: 10px;
    background-color: rgba(184, 230, 253, 1);
  }

  & /deep/ .el-button--danger {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 6px;
    border-style: solid;
    border-color: rgba(238, 233, 207, 1);
    border-radius: 10px;
    background-color: rgba(184, 230, 253, 1);
  }

  & /deep/ .el-button {
    margin: 4px;
  }
}

.form-content {
  background: transparent;
}

.table-content {
  background: transparent;
}

.tables /deep/ .el-table__body tr {
  background-color: #f5f5f5 !important;
  color: #606266 !important;
}

.tables /deep/ .el-table__body tr.el-table__row--striped td {
  background: transparent;
}

.tables /deep/ .el-table__body tr.el-table__row--striped {
  background-color: rgba(184, 230, 253, 0.78) !important;
  color: #606266 !important;
}

.tables /deep/ .el-table__body tr:hover > td {
  background-color: rgba(184, 230, 253, 1) !important;
  color: #333 !important;
}

</style>
