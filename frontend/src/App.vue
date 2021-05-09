<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data: () => {
    return {
      wxConfig: {}
    }
  },
  mounted() {
    window.sessionStorage.setItem('firstUrl', window.location.href)
    window.sessionStorage.setItem('toIndex', true)

    console.log('setFirstUrl', window.location.href)
  },
  methods: {
    isWx() {
      let ua = window.navigator.userAgent.toLowerCase();
      if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
      } else {
        return false;
      }
    }
  }
}
</script>

<style>
#app {
  margin: 0;
  padding: 0;
}

</style>
