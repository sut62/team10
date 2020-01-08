import Vue from 'vue'
import VueRouter from 'vue-router'
import Diagnose from '../components/Diagnose.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    redirect: '/diagnose'
  },
  {
    path: '/diagnose',
    component: Diagnose
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
