<template>
  <div>
    <a-col class="flex-column" style="align-items: center">
      <a-input v-model="form.email" placeholder="Email" style="width: 80%">
        <a-icon slot="prefix" type="mail"/>
      </a-input>
      <a-input v-model="form.password" placeholder="password" style="width: 80%;margin-top: 1vh">
        <a-icon slot="prefix" type="lock"/>
      </a-input>
      <a-button style="width: 80%;margin-top: 1vh" @click="clickLogin" :loading="loading"> Sign in</a-button>
      <h4 style="width: 80%;margin-top: 1vh;text-align: right">Forgot Password?</h4>
      <a-button type="primary" style="width: 80%;margin-top: 1vh" @click="clickRegister">New to CoolGames? Register
        now!
      </a-button>
    </a-col>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Login',
  components: {},
  props: {
    login: {
      type: Function,
      default: () => {
      }
    },
    register: {
      type: Function,
      required: false,
      default: () => {
      }
    },
  },
  data() {
    return {
      loading: false,
      form: {
        email: 'administrator@gmail.com',
        password: 'abc123456',
      },
      userInfo:{
        email: 'administrator@gmail.com',
        password: 'abc123456',
        name:'Tom'
      }
    }
  },
  mounted() {
  },
  methods: {
    clickLogin() {
      this.loading = true
      setTimeout(() => {
        if(this.form.email===this.userInfo.email)
          if(this.form.password===this.userInfo.password){
            sessionStorage.setItem('userInfo', JSON.stringify(this.userInfo))
            this.$message.success("Login success!")
            this.loading = false
            this.$emit('login')
            return
          }
        this.$message.error("Login Fail! Email or password invalid")
        this.loading = false
      }, 3000)

    },
    clickRegister() {
      this.$emit('register', true)
    }
  }
}
</script>
