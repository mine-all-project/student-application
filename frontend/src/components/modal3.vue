<template>
  <a-modal :title="null" :footer="null" :closable="false" :centered="true"
           :visible="showModal" :dialogStyle="settings" :bodyStyle="bodyStyle"
           class="modal" width="85%">
    <p class="modal-p1">分享成功 ✅️</p>
    <p class="modal-p2">请继续分享到
      <span class="modal-p2-1">{{ shareCount }}</span>
      个不同的群
    </p>
    <p class="modal-p3">保证
      <span class="modal-p3-1">100%</span>
      可以立即
    </p>
    <p class="modal-p4">领取
      <span class="modal-p4-1">{{ receiveAmount.toFixed(2) }}元</span>
    </p>
    <div class="black-1px" style="margin: 5px"></div>
    <p class="modal-p9" @click="closeModal">确定</p>
  </a-modal>
</template>

<script>
export default {
  name: "modal3",
  props: {
    receiveAmount: {
      default: 166.52,
      type: Number,
    },
    shareCount: {
      default: 3,
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
  color: #2fe32f;
  font-size: 22px;
  font-weight: 500;
  margin: 0;
}

.modal-p2 {
  color: #000;
  font-size: 18px;
  font-weight: 400;
  margin: 0;
}

.modal-p2-1 {
  color: #f00;
  font-size: 18px;
  font-weight: 600;
  margin: 0;
}


.modal-p3 {
  color: #000;
  font-size: 18px;
  margin: 0;
}

.modal-p3-1 {
  color: #f00;
  font-size: 18px;
  margin: 0;
}

.modal-p4 {
  color: #000;
  font-size: 18px;
  margin: 0;
}

.modal-p4-1 {
  font-family: '黑体';
  color: #f00;
  font-size: 18px;
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
  font-size: 22px;
  margin: 0 0 0 0;
  padding: 0;
}

.modal-p8 {
  color: #a39f9f;
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