import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.css'
import locale from 'element-ui/lib/locale/lang/en'
import axios from './utils/request'
// 引入nprogress
import NProgress from 'nprogress'
import 'nprogress/nprogress.css' // 这个样式必须引入

Vue.use(ElementUI)
Vue.use(ElementUI, { locale })

// 简单配置
NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 500, showSpinner: false })
Vue.config.productionTip = false
Vue.prototype.$http = axios;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
