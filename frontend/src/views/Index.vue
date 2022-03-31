<template>
  <div class="container">
    <PageHeader @login="showLogin"></PageHeader>
    <a-tabs default-active-key="1" @change="changeTab" :tabBarStyle="tabStyle" :animated="false" :activeKey="activeTab">
      <a-tab-pane key="demo" tab="demo">
        <Collection></Collection>
      </a-tab-pane>
      <a-tab-pane key="ContactUs" tab="ContactUs">
        <ContactUs></ContactUs>
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
        Content of Tab Pane 1
      </a-tab-pane>
      <a-tab-pane key="Club" tab="Club">
        <Club></Club>
      </a-tab-pane>
      <a-tab-pane key="Market" tab="Market">
        <Market></Market>
      </a-tab-pane>
    </a-tabs>
    <a-modal v-model="show.login" :footer="null" :closable="false">
      <div style="text-align: center">
        <h1>CoolGames</h1>
        <a-tabs default-active-key="1" @change="changeLoginTab" :animated="false" :activeKey="loginTab">
          <a-tab-pane key="Email" tab="Email">
            <a-col class="flex-column" style="align-items: center">
              <a-input v-model="form.email" placeholder="Email" style="width: 80%">
                <a-icon slot="prefix" type="mail"/>
              </a-input>
              <a-input v-model="form.password" placeholder="password" style="width: 80%;margin-top: 1vh">
                <a-icon slot="prefix" type="lock"/>
              </a-input>
              <a-button style="width: 80%;margin-top: 1vh"> Sign in</a-button>
              <h4 style="width: 80%;margin-top: 1vh;text-align: right">Forgot Password?</h4>
              <a-button type="primary" style="width: 80%;margin-top: 1vh">New to CoolGames? Register now!</a-button>
            </a-col>
          </a-tab-pane>
          <a-tab-pane key="Telephone" tab="Telephone">
            <Register></Register>
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-modal>
  </div>
</template>

<script>
// @ is an alias to /src
import PageHeader from "@/components/PageHeader"
import ContactUs from "@/views/ContactUs"
import Club from "@/views/Club"
import Market from "@/views/Market"
import Collection from "@/views/Collection"
import Home from "@/views/Home"
import Register from "@/views/Register"

export default {
  name: 'Index',
  components: {PageHeader, ContactUs, Club, Market, Collection, Home,Register},
  data() {
    return {
      form: {
        email: '',
        name: '',
        code: '',
        password: '',
        againPassword: '',
      },
      show: {
        login: true,
      },
      img: require('@/assets/image1.png'),
      collections: [
        {id: '1', name: 'Game1', rate: '4.7', image: require('@/assets/image1.png')},
        {id: '2', name: 'Game2', rate: '4.8', image: require('@/assets/image1.png')},
        {id: '3', name: 'Game3', rate: '2.4', image: require('@/assets/image1.png')},
        {id: '4', name: 'Game4', rate: '3.9', image: require('@/assets/image1.png')},
        {id: '5', name: 'Game4', rate: '3.9', image: require('@/assets/image1.png')},
        {id: '6', name: 'Game4', rate: '3.9', image: require('@/assets/image1.png')},
      ],
      activeTab: 'demo',
      loginTab: 'Telephone',
      tabStyle: {display: "flex", justifyContent: 'space-around', flex: 1, marginTop: '5vh'},
    }
  },
  mounted() {
  },
  methods: {
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
p {
  margin: 0;
  padding: 0;
}

.container {
  width: 60%;
  margin: 5% auto 0;
}

.border {
  border: 1px solid red
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
