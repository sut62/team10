import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Contagion from '../components/Contagion.vue'
import Diagnose from '../components/Diagnose.vue'
import Patient from '../components/Patient.vue'
import Register from '../components/Register.vue'
import Riskarea from '../components/Riskarea.vue'
import vaccineinformation from '../components/vaccineinformation.vue'
import viewvaccineinformation from '../components/viewvaccineinformation.vue'
import DiagnoseView from '../components/DiagnoseView.vue'
import searchriskarea from '../components/sprint2/SearchRiskarea.vue'
import Patientshow from '../components/sprint2/Patientshow.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    component: Home
  },
  {
    path: '/',
    component: Login
  },
  {
    path: '/contagion',
    component: Contagion
  },
  {
    path: '/diagnose',
    component: Diagnose
  },
  {
    path: '/patient',
    component: Patient
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/riskarea',
    component: Riskarea
  },
  {
    path: '/vaccineinformation',
    component: vaccineinformation
  },
  {
    path: '/viewvaccineinformation',
    component: viewvaccineinformation
  },
  {
    path: '/diagnoseView',
    component: DiagnoseView
  },
  // searchriskarea
  {
    path: '/searchriskarea',
    component: searchriskarea
  },
  {
    path: '/patientshow',
    component: Patientshow
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
