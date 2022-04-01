<template>
  <div class="container">
    <PageHeader @login="showLogin" :isLogin="show.isLogin" :userInfo="userInfo"
                @showMyAccount="showMyAccount"></PageHeader>
    <div class="contact-us">
      <h3 @click="showContactUs">
        <span>Contact Us </span>
        <a-icon type="message"/>
      </h3>
    </div>
    <a-tabs default-active-key="1" @change="changeTab" :tabBarStyle="tabStyle" :animated="false" :activeKey="activeTab">
      <a-tab-pane key="demo" tab="demo">
        <Community @showInfo="showCommunityInfo"></Community>
      </a-tab-pane>
      <a-tab-pane key="Home" tab="Home">
        <Home></Home>
      </a-tab-pane>
      <a-tab-pane key="Game" tab="Game">
        Content of Tab Pane 1
      </a-tab-pane>
      <a-tab-pane key="Collection" tab="Collection">
        <Collection></Collection>
      </a-tab-pane>
      <a-tab-pane key="Community" tab="Community">
        <Community @showInfo="showCommunityInfo"></Community>
      </a-tab-pane>
      <a-tab-pane key="Club" tab="Club">
        <Club></Club>
      </a-tab-pane>
      <a-tab-pane key="Market" tab="Market">
        <Market></Market>
      </a-tab-pane>
    </a-tabs>
    <a-modal v-model="show.myFollow" :footer="null" :closable="false" width="30%" title="My Follow">
      <MyFollow></MyFollow>
    </a-modal>
    <a-modal v-model="show.myClub" :footer="null" :closable="false" width="30%" title="My Club">
      <MyClub></MyClub>
    </a-modal>
    <a-modal v-model="show.myFans" :footer="null" :closable="false" width="30%" title="My Fans">
      <MyFans></MyFans>
    </a-modal>
    <a-modal v-model="show.myAccount" :footer="null" :closable="false" width="30%" title="My Account">
      <MyAccount :userInfo="userInfo"
                 @showFollowing="showFollowing"
                 @showFans="showFans"
                 @showClub="showClub"/>
    </a-modal>
    <a-modal v-model="show.createPost" :footer="null" :closable="false" width="20%">
      <div class="flex-row around" style="align-items: baseline">
        <h2>Write a New Post</h2>
        <a-button type="primary" size="small">Publish</a-button>
      </div>
      <a-input placeholder="Input Subject..."/>
      <a-textarea placeholder="Input Details..." :rows="8" style="margin-top: 20px"/>
      <a-col class="flex-row" style="justify-content: space-around;width:50%;align-items: center">
        <a-icon type="smile" style="font-size: 1.5em"/>
        <a-icon type="picture" style="font-size: 1.5em"/>
        <i class="iconfont" style="font-size: 1.5em;font-weight: 700">&#xe60e;</i>
        <i class="iconfont" style="font-size: 1.5em">&#xe650;</i>
      </a-col>
    </a-modal>
    <a-modal v-model="show.communityInfo" :footer="null" :closable="false" width="40%">
      <CommunityInfo @showCreatePost="showCreatePost"></CommunityInfo>
    </a-modal>
    <a-modal v-model="show.contactUs" :footer="null" :closable="false" width="30%">
      <ContactUs></ContactUs>
    </a-modal>
    <a-modal v-model="show.login" :footer="null" :closable="false">
      <div style="text-align: center">
        <h1>CoolGames</h1>
        <a-tabs default-active-key="Email" @change="changeLoginTab" :animated="false" :activeKey="loginTab">
          <a-tab-pane key="Email" tab="Email">
            <Login @login="login" @register="register" :isLogon="show.isLogin"></Login>
          </a-tab-pane>
          <a-tab-pane key="Telephone" tab="Telephone">
            <Register :show="show.register"></Register>
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-modal>
    <a-modal v-model="show.register" :footer="null" :closable="false">
      <Register @register="register"></Register>
    </a-modal>
  </div>
</template>

<script>
// @ is an alias to /src
import PageHeader from "@/components/PageHeader"
import ContactUs from "@/components/ContactUs"
import Club from "@/components/Club"
import Market from "@/components/Market"
import Collection from "@/components/Collection"
import Home from "@/components/Home"
import Register from "@/components/Register"
import Login from "@/components/Login"
import Community from "@/components/Community"
import CommunityInfo from "@/components/CommunityInfo"
import MyAccount from "@/components/MyAccount"
import MyClub from "@/components/MyClub"
import MyFans from "@/components/MyFans"
import MyFollow from "@/components/MyFollow"
import ClubDetail from "@/views/ClubDetail"
import GameDetail from "@/views/GameDetail"
import Game from "@/views/Game"
import CommunityDetail from "@/views/CommunityDetail"

export default {
  name: 'Index',
  components: {
    PageHeader, ContactUs, Club, Market, Collection,
    Home, Register, Login, Community, CommunityInfo,
    MyAccount, MyClub, MyFans, MyFollow,ClubDetail,
    GameDetail,Game,CommunityDetail
  },
  data() {
    return {
      show: {
        isLogin: false, register: false, login: false, contactUs: false,
        communityInfo: false, createPost: false, myAccount: false,
        myFollow: false, myFans: false, myClub: false,
      },
      games: [
        {id: '1', gameName: 'Game 1', userName: 'user 1', image: require('@/assets/image1.png')},
        {id: '2', gameName: 'Game 2', userName: 'user 2', image: require('@/assets/image1.png')},
        {id: '3', gameName: 'Game 3', userName: 'user 3', image: require('@/assets/image1.png')},
      ],
      userInfo: {name: ''},
      image: require('@/assets/image1.png'),
      activeTab: 'demo',
      loginTab: 'Email',
      tabStyle: {display: "flex", justifyContent: 'space-around', flex: 1, marginTop: '5vh'},
    }
  },
  mounted() {
    const userInfo = sessionStorage.getItem('userInfo')
    if (userInfo) {
      this.userInfo = JSON.parse(userInfo)
      this.show.isLogin = true
    }
  },
  methods: {
    showFollowing() {
      this.show.myFollow = true
      this.show.myAccount = false
    },
    showFans() {
      this.show.myFans = true
      this.show.myAccount = false
    },
    showClub() {
      this.show.myClub = true
      this.show.myAccount = false
    },
    showMyAccount() {
      this.show.myAccount = true
    },
    showCreatePost() {
      this.show.createPost = true
      this.show.communityInfo = false
    },
    showCommunityInfo() {
      this.show.communityInfo = true
    },
    showContactUs() {
      this.show.contactUs = true
    },
    register(e) {
      console.log(e)
      this.show.register = e
    },
    login() {
      const userInfo = sessionStorage.getItem('userInfo')
      this.userInfo = JSON.parse(userInfo)
      this.show.login = false
      this.show.isLogin = true
    },
    showLogin() {
      this.show.login = true
    },
    changeTab(e) {
      console.log(e)
      this.activeTab = e
    },
    changeLoginTab(e) {
      this.loginTab = e
    }
  }
}
</script>
<style>
.contact-us {
  position: fixed;
  left: 0;
  top: 50vh;
  writing-mode: tb-rl;
  border: 3px solid rgb(65, 80, 88);
  border-radius: 5px;
  border-left: none;
  padding: 10px 0;
}

p {
  margin: 0;
  padding: 0;
}

.container {
  width: 60%;
  margin: 5% auto 0;
}

.border {
  border: 3px solid rgb(65, 80, 88);
  border-radius: 5px;
}

.around {
  justify-content: space-around;

}

.between {
  justify-content: space-between;
}

.flex-row {
  display: flex;
  flex-direction: row;
}

.flex-column {
  display: flex;
  flex-direction: column;
}

li {
  list-style: none;
}
</style>
