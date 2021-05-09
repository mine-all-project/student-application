import Vue from 'vue'
import App from './App'
import VueAxios from 'vue-axios'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import router from './router'
import instance from '@/utils/AxiosUtils'
import wx from 'weixin-js-sdk'
// import Vconsole from 'vconsole'
// const vConsole = new Vconsole()
Vue.config.productionTip = false
Vue.use(
    Antd,
    VueAxios,
    // vConsole
)
Vue.prototype.$http = instance
Vue.prototype.$wx = wx
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
