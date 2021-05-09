<template>
  <a-modal :title="null" :footer="null" :closable="false" :centered="true"
           :visible="showModal" :dialogStyle="settings" :bodyStyle="bodyStyle"
           class="modal" width="85%">
    <p class="modal-p1">感谢您的参与 :-)</p>
    <p class="modal-p2">注意:系统将会验证朋友圈信息判断是否完成分享任务 </p>
    <p class="modal-p3">请保留朋友圈信息至红包到账</p>
    <p class="modal-p4">删除分享链接可能导致无法到账</p>
    <p class="modal-p5">点击下方“立即领取”</p>
    <p class="modal-p6">完成最后一步即可到账</p>
    <div class="black-1px" style="margin: 5px"></div>
    <p class="modal-p9" @click="toOtherUrl">立即领取</p>
  </a-modal>
</template>

<script>
export default {
  name: "modal5",
  props: {
    receiveAmount: {
      default: 166.52,
      type: Number,
    },
    shareCount: {
      default: 2,
      type: Number,
    },
    showModal: {
      default: false,
      type: Boolean,
    },
    close: {
      type: Function
    },
    otherUrl: {
      type: String,
      default: ''
    },
  },
  data: () => {
    return {
      location: '',
      shareUrl: 'http://linux.crabapples.cn',
      settings: {
        padding: '0',
        width: '100%'
      },
      bodyStyle: {
        padding: '12px',
      },
    }
  },
  methods: {
    closeModal() {
      this.$emit('close')
    },
    toOtherUrl() {
      this.closeModal()
      window.location.href = this.otherUrl
    }
  }
}
</script>

<style scoped>

p {
  padding: 0;
  margin: 0;
}

span {
  padding: 0;
  margin: 0;
}

.black-1px {
  width: 100%;
  height: 1px;
  background-color: #f1f1f1;
}

.modal {
  text-align: center;
}

.modal p {
  font-family: '黑体';
  font-weight: 500;
  text-align: center;
}

.modal-p1 {
  color: #000;
  font-size: 18px;
  font-weight: 500;
  margin: 0;
}

.modal-p2 {
  color: #000;
  font-size: 18px;
  font-weight: 500;
  margin: 0 0 20px;
}

.modal-p3 {
  color: #000;
  font-size: 16px;
  margin: 0;
}

.modal-p4 {
  color: #000;
  font-size: 16px;
  margin: 0;
  padding: 0 0 20px 0;

}


.modal-p5 {
  color: #f00;
  font-size: 16px;
  margin: 0 0 0 0;
  padding: 0;
}

.modal-p6 {
  color: #f00;
  font-size: 16px;
  margin: 0 0 0 0;
  padding: 0;
}

.modal-p9 {
  color: #63af29;
  font-size: 22px;
  margin: 5px 0 0 0;
  padding: 0;
}
</style>