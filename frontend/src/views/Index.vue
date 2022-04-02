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
      <a-tab-pane key="Home" tab="Home">
        <Home></Home>
      </a-tab-pane>
      <a-tab-pane key="Game" tab="Game">
        <Game :games="games" @showGameDetail="showGameDetail"></Game>
      </a-tab-pane>
      <a-tab-pane key="Collection" tab="Collection">
        <Collection></Collection>
      </a-tab-pane>
      <a-tab-pane key="Community" tab="Community">
        <Community @showDetail="showCommunityDetail" @showCommunityComment="showCommunityComment"></Community>
      </a-tab-pane>
      <a-tab-pane key="Club" tab="Club">
        <Club @showClubDetail="showClubDetail"></Club>
      </a-tab-pane>
      <a-tab-pane key="Market" tab="Market">
        <Market></Market>
      </a-tab-pane>
    </a-tabs>
    <a-modal v-model="show.gameDetail" :footer="null" :closable="false" width="40%" title="Game Detail">
      <GameDetail :detail="gameDetail" @saveGameDetail="saveGameDetail"></GameDetail>
    </a-modal>
    <a-modal v-model="show.clubDetail" :footer="null" :closable="false" width="50%" title="Club Detail">
      <ClubDetail></ClubDetail>
    </a-modal>
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
      <a-col class="flex-row around" style="width:50%;align-items: center">
        <a-icon type="smile" style="font-size: 1.5em"/>
        <a-icon type="picture" style="font-size: 1.5em"/>
        <i class="iconfont" style="font-size: 1.5em;font-weight: 700">&#xe60e;</i>
        <i class="iconfont" style="font-size: 1.5em">&#xe650;</i>
      </a-col>
    </a-modal>
    <a-modal v-model="show.communityDetail" :footer="null" :closable="false" width="40%">
      <CommunityDetail @showCreatePost="showCreatePost"></CommunityDetail>
    </a-modal>
    <a-modal v-model="show.communityComment" :footer="null" :closable="false" width="40%">
      <a-col class="flex-column" style="border: 1px solid #858282">
        <a-col class="flex-row" style="justify-content: center">
          <h3>Subject</h3>
        </a-col>
        <a-col class="flex-column" style="justify-content: center">
          <a-col class="flex-row"
                 style="align-items: center;border-top: 1px solid #858282;border-bottom: 1px solid #858282;">
            <a-col flex="1" class="flex-row" style="justify-content: center;">
              <a-avatar size="large" icon="user"/>
            </a-col>
            <a-col flex="4" class="flex-column" style="align-items:center;justify-content: center;">
              <h3>Details</h3>
              <h3>Details</h3>
              <h3>Details</h3>
            </a-col>
          </a-col>
          <a-col class="flex-row">
            <a-col flex="5"></a-col>
            <a-col flex="1" class="flex-row around" style="margin-top: 1em">
              <a-badge count="4"
                       :number-style="{border:'none',backgroundColor: 'rgba(255,255,255,0)',color: '#999'}">
                <a-icon type="like" style="font-size: 1.5em"/>
              </a-badge>
              <a-badge count="5"
                       :number-style="{border:'none',backgroundColor: 'rgba(255,255,255,0)',color: '#999'}">
                <a-icon type="star" style="font-size: 1.5em"/>
              </a-badge>
            </a-col>
          </a-col>
        </a-col>
        <a-col class="flex-column" style="justify-content: center">
          <a-col class="flex-row" style="align-items: center;border-top: 1px solid #858282;">
            <a-col flex="1" class="flex-row" style="justify-content: center;">
              <a-avatar size="large" icon="user"/>
            </a-col>
            <a-col flex="4" class="flex-column" style="align-items:center;justify-content: center">
              <h3>Details</h3>
              <h3>Details</h3>
              <h3>Details</h3>
            </a-col>
          </a-col>
          <a-col class="flex-row">
            <a-col flex="5"></a-col>
            <a-col flex="1" class="flex-row around">
              <a-badge count="4"
                       :number-style="{border:'none',backgroundColor: 'rgba(255,255,255,0)',color: '#999'}">
                <a-icon type="like" style="font-size: 1.5em"/>
              </a-badge>
            </a-col>
          </a-col>
          <a-col class="flex-row" style="align-items: center">
            <a-col flex="1"></a-col>
            <a-col flex="4" class="flex-column"
                   style="align-items:center;justify-content: start;border: 1px solid #858282;border-right: none">
              <a-col class="flex-column" style="justify-content: start;width: 100%">
                <a-col class="flex-row" style="align-items: center;border-bottom: 1px solid #858282;padding: 10px">
                  <a-col flex="1" class="flex-column" style="justify-content: center">
                    <a-avatar size="large" icon="user"/>
                  </a-col>
                  <a-col flex="4">
                    <h3>Details</h3>
                  </a-col>
                </a-col>
                <a-col class="flex-row" style="align-items: center;border-bottom: 1px solid #858282;padding: 10px">
                  <a-col flex="1" class="flex-column" style="justify-content: center">
                    <a-avatar size="large" icon="user"/>
                  </a-col>
                  <a-col flex="4">
                    <h3>Details</h3>
                  </a-col>
                </a-col>
                <a-col class="flex-row" style="align-items: center;padding: 10px">
                  <a-input placeholder="Reply to this followup discussion..." style="width: 80%"/>
                  <a-button type="primary" style="padding: 0 20px;margin-left: 2vh">Submit</a-button>
                </a-col>
              </a-col>
            </a-col>
          </a-col>
          <a-col class="flex-row" style="align-items: center;width: 100%">
            <a-col class="flex-row around" style="align-items: center;width: 100%;margin-top: 1vh">
              <a-input placeholder="Reply..." style="width: 80%"/>
              <a-button type="primary" style="padding: 0 20px;margin-left: 2vh">Submit</a-button>
            </a-col>
          </a-col>
          <br>
        </a-col>
      </a-col>
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
import Market from "@/components/Market"
import Collection from "@/components/Collection"
import Home from "@/components/Home"
import Register from "@/components/Register"
import Login from "@/components/Login"
import MyAccount from "@/components/MyAccount"
import MyClub from "@/components/MyClub"
import MyFans from "@/components/MyFans"
import MyFollow from "@/components/MyFollow"
import Community from "@/components/Community"
import CommunityDetail from "@/components/CommunityDetail"
import Club from "@/components/Club"
import ClubDetail from "@/components/ClubDetail"

import Game from "@/views/Game"
import GameDetail from "@/views/GameDetail"

export default {
  name: 'Index',
  components: {
    PageHeader, ContactUs, Club, Market, Collection,
    Home, Register, Login, Community,
    MyAccount, MyClub, MyFans, MyFollow, ClubDetail,
    GameDetail, Game, CommunityDetail
  },
  data() {
    return {
      show: {
        isLogin: false, register: false, login: false, contactUs: false,
        communityDetail: false, createPost: false, myAccount: false,
        myFollow: false, myFans: false, myClub: false,
        clubDetail: false, communityComment: false, gameDetail: false
      },
      games: [
        {id: '1', name: 'Game 1', userName: 'user 1', image: require('@/assets/image1.png')},
        {id: '2', name: 'Game 2', userName: 'user 2', image: require('@/assets/image1.png')},
        {id: '3', name: 'Game 3', userName: 'user 3', image: require('@/assets/image1.png')},
      ],
      gameDetail: {image: {}},
      userInfo: {name: ''},
      image: require('@/assets/image1.png'),
      activeTab: 'Game',
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
    this.getGameList()
  },
  methods: {
    saveGameDetail(e) {
      this.$http.post('/api/gameComment/save', e).then(result => {
        if (result.status !== 200) {
          this.$message.error(result.message)
        } else {
          this.$message.success('success')
        }
        this.getGameList()
      })
    },
    getGameList() {
      this.$http.get('/api/game/list').then(result => {
        this.games = result.data.map(e => {
          e.gameComments = e.gameComments.sort((a, b) => {
            let createTimeA = this.$moment(a.createTime)
            let createTimeB = this.$moment(b.createTime)
            return createTimeB.diff(createTimeA, 's');
          })
          return e
        })
      })
    },
    showGameDetail(e) {
      console.log(e)
      this.gameDetail = e
      this.show.gameDetail = true
    },
    showClubDetail() {
      this.show.clubDetail = true
    },
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
    showCommunityDetail(e) {
      this.show.communityDetail = true
    },
    showCommunityComment(e) {
      this.show.communityComment = true
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
.openTimeTable {
  text-align: right;
}

.openTimeTd {
  width: 70%;
}

table {
  border-collapse: collapse;
}

td {
  border: 1px solid #f1f1f1;
}

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
