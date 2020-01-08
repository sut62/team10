import Vue from 'vue'
import VueRouter from 'vue-router'
import Contagion from '../components/Contagion.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/Contagion',
    component: Contagion
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
