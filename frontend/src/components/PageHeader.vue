<template>
  <div style="display: flex;justify-content: space-around;align-items: center">
    <div style="display: flex;justify-content: space-around;align-items: center">
      <div style="font-size: 28px;font-weight: 600">CoolGames</div>
      <div>
        <!--        <a-input-search placeholder="Search..." enter-button style="width: 80vh"/>-->
        <a-input placeholder="Search" style="width: 80vh">
          <span slot="suffix">
            <a-icon type="audio"/>
            <a-icon type="search"/>
          </span>
        </a-input>
      </div>
    </div>
    <div style="display: flex;width:20%;justify-content: space-around">
      <a-popover v-model="show.accessibility" placement="bottom" trigger="click">
        <template slot="content">
          <div  style="padding-top: 10px;display: flex;justify-content: center;width: 100vh;margin: 0 -20vh 0 -25vh">
<!--          <div  style="">-->
            <div style="width: 8%;">
              <i class="iconfont icon">&#xec13;</i>
              <p class="text">Zoom in Screen</p>
            </div>
            <div style="width: 8%;">
              <i class="iconfont icon">&#xec32;</i>
              <p class="text">Zoom out Screen</p>
            </div>
            <div style="width: 8%;">
              <i class="iconfont icon">&#xe645;</i>
              <p class="text">Turn up Volume</p>
            </div>
            <div style="width: 8%;">
              <i class="iconfont icon">&#xe644;</i>
              <p class="text">Turn down Volume</p>
            </div>
            <div style="width: 8%;">
              <i class="iconfont icon">&#xe63f;</i>
              <p class="text">Adjust Colours</p>
            </div>
            <div style="width: 8%;">
              <i class=" icon1">
                <i class="iconfont icon2">&#xe7c4;</i>
                <i class="iconfont icon2">&#xe605;</i>
              </i>
              <p class="text">Read Screen</p>
            </div>
          </div>

        </template>
        <a-button>Accessibility</a-button>
      </a-popover>
      <a-dropdown v-if="isLogin">
        <span>
          <a-avatar icon="user"/>
          <span>{{ userInfo.name }}</span>
        </span>
        <a-menu slot="overlay">
          <a-menu-item>
            <a href="javascript:" @click="clickMyAccount">
              <a-icon type="home"/>
              <span>My Account</span>
            </a>
          </a-menu-item>
          <a-menu-item>
            <a href="javascript:">
              <a-icon type="mail"/>
              <span>My Message</span>
            </a>
          </a-menu-item>
          <a-menu-item>
            <a href="javascript:">
              <a-icon type="setting"/>
              <span>Settings</span>
            </a>
          </a-menu-item>
          <a-menu-item>
            <a href="javascript:" @click="logout">
              <i class="iconfont">&#xe64b;</i>
              <span>Sign out</span>
            </a>
          </a-menu-item>
        </a-menu>
      </a-dropdown>
      <a-button @click="clickLogin" v-else>Sign in/up</a-button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PageHeader',
  props: {
    login: {
      type: Function
    },
    showMyAccount: {
      type: Function
    },
    showAccessibility: {
      type: Function
    },
    userInfo: {
      type: Object,
      default: () => {
        return {
          name: ''
        }
      }
    },
    isLogin: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      show: {
        accessibility: false
      }
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    logout(){
      sessionStorage.clear()
      window.location.reload()
    },
    clickAccessibility() {
      this.show.accessibility = true
      this.$emit('showAccessibility')
    },
    clickLogin() {
      this.$emit('login')
    },
    clickMyAccount() {
      this.$emit('showMyAccount')
    }
  }
}
</script>
<style scoped>
.text {
  width: 90%;
  overflow: hidden;
  margin-top: 12px;
  color: #000;
  font-weight: 500;
}

.icon {
  font-weight: 700;
  font-size: 2em;
  color: #000;
  border: 3px solid #a6a0e1;
  padding: 16px;
}

.icon1 {
  font-weight: 700;
  color: #000;
  border: 3px solid #a6a0e1;
  padding: 14px;
  padding-top: 24px;
}

.icon2 {
  font-weight: 700;
  font-size: 2em;
  color: #000;
  padding: 10px;
}
</style>
