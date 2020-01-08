import Vue from 'vue'
import VueRouter from 'vue-router'
import Patient from '../components/Patient.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/patient',
    component: Patient
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
