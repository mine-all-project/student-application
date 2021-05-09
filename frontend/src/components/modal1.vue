<template>
  <a-modal :title="null" :footer="null" :closable="false" :centered="true"
           :visible="showModal" :dialogStyle="settings" :bodyStyle="bodyStyle"
           class="modal" width="85%">
    <p class="modal-p1">💖恭喜你</p>
    <p class="modal-p2">您获得现_金奖励</p>
    <p class="modal-p3">{{ receiveAmount.toFixed(2) }}元</p>
    <p class="modal-p4">活动宗旨:
      <span class="modal-p4-1">感恩回馈粉丝</span>
    </p>
    <p class="modal-p5">领取要求:
      <span class="modal-p4-1">您只需分享到</span>
      <span class="modal-p4-2">朋友圈</span>
    </p>
    <p class="modal-p6">
      完成后自动存入您的微信钱包</p>
    <p class="modal-p7">红包总金额仅剩余167万，先到先得，活动经微信认证，真实有效
    <div class="black-1px" style="margin: 5px"></div>
    <p class="modal-p9" @click="closeModal">我知道了</p>
  </a-modal>
</template>

<script>
export default {
  name: "modal1",
  props: {
    receiveAmount: {
      default: 0,
      type: Number,
    },
    showModal: {
      default: false,
      type: Boolean,
    },
    close: {
      type: Function
    }
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
  mounted() {
    this.show = this.showModal
  },
  methods: {
    closeModal() {
      this.$emit('close')
    },
  }
}
</script>

<style scoped>
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
  color: #2a822a;
  font-size: 22px;
  margin: 0;
}

.modal-p2 {
  color: #000;
  font-size: 18px;
  font-weight: 600;
  margin: 0;
}

.modal-p3 {
  color: #E1604C;
  font-size: 22px;
  margin: 0;
}

.modal-p4 {
  color: #E1604C;
  font-size: 16px;
  margin: 0;
}

.modal-p4-1 {
  font-family: '黑体';
  color: #000;
  font-size: 16px;
  margin: 0;
}

.modal-p4-2 {
  font-family: '黑体';
  font-weight: 500;
  color: #f00;
  font-size: 16px;
  margin: 0;
}

.modal-p5 {
  color: #E1604C;
  font-size: 16px;
  margin: 0;
}

.modal-p6 {
  color: #000;
  font-size: 14px;
  margin: 0 0 20px;
}

.modal-p6-1 {
  font-family: '黑体';
  color: #E1604C;
  font-size: 14px;
  margin: 0 0 0 0;
}

.modal-p7 {
  color: #a39f9f;
  font-size: 16px;
  margin: 0 0 0 0;
  padding: 0;

}

.modal-p7-1 {
  font-family: '黑体';
  color: #E1604C;
  font-size: 24px;
  margin: 0 0 0 0;
  padding: 0;
}

.modal-p8 {
  color: #a39f9f;
  font-size: 18px;
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