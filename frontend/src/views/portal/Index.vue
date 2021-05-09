<template>
  <div>
    <red-package/>
  </div>
</template>

<script>
// @ is an alias to /src
import RedPackage from '@/components/RedPackcge.vue'

export default {
  name: 'portal-index',
  components: {
    RedPackage
  },
  mounted() {
    window.sessionStorage.setItem('firstUrl',window.location.href)
    console.log('setFirstUrl',window.location.href)
  }
}
</script>
