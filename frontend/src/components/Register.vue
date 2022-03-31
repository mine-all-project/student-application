<template>
  <div>
    <a-col class="flex-column" style="align-items: center">
      <h2 style="width: 80%;margin-top: 1vh;text-align: left">Create an account</h2>
      <h3 style="width: 80%;margin-top: 1vh;text-align: left">Your Name</h3>
      <a-input v-model="form.name" style="width: 80%"/>
      <h3 style="width: 80%;margin-top: 1vh;text-align: left">Email Address or Mobile Number</h3>
      <a-col class="flex-row around" style="width: 100%;margin-top: 1vh">
        <a-input v-model="form.email" style="width: 40%;"/>
        <a-button style="width: 20%;">Verify</a-button>
      </a-col>
      <h3 style="width: 80%;margin-top: 1vh;text-align: left">Verification Code</h3>
      <a-input v-model="form.code" style="width: 80%"/>
      <h3 style="width: 80%;margin-top: 1vh;text-align: left">Password</h3>
      <a-input v-model="form.password" style="width: 80%"/>
      <h3 style="width: 80%;margin-top: 1vh;text-align: left">Confirm Password</h3>
      <a-input v-model="form.againPassword" style="width: 80%"/>
      <a-button type="primary" style="width: 40%;margin-top: 1vh" @click="clickRegister" :loading="loading">Register
      </a-button>
    </a-col>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Register',
  components: {},
  props: {
    show: {
      type: Boolean,
      default: false
    },
    register: {
      type: Function,
    }

  },
  data() {
    return {
      form: {
        email: '',
        name: '',
        code: '',
        password: '',
        againPassword: '',
      },
      loading: false
    }
  },
  mounted() {
  },
  methods: {
    clickRegister() {
      this.loading = true
      setTimeout(() => {
        this.$message.success("Register success!")
        this.loading = false
        this.$emit('register', false)
      }, 3000)
    }
  }
}
</script>
