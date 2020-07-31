import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import store from './store'
import vueCookies from 'vue-cookies'

Vue.config.productionTip = false

Vue.use(vueCookies)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
