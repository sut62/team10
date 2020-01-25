import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import 'v-slim-dialog/dist/v-slim-dialog.css'
import SlimDialog from 'v-slim-dialog';

Vue.config.productionTip = false
Vue.use(SlimDialog)

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
