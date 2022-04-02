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
      <a-button>Accessibility</a-button>
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
            <a href="javascript:">
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
  components: {},
  mounted() {
  },
  methods: {
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
</style>
