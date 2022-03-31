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
      <a-button @click="showLogin">Sign in/up</a-button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PageHeader',
  props:{
    login:{
      type:Function
    }
  },
  components: {},
  mounted() {
  },
  methods:{
    showLogin(){
      this.$emit('login')
    }
  }
}
</script>
<style scoped>
.container {
  width: 60%;
  margin: 0 auto;
  margin-top: 5%;

}
</style>
