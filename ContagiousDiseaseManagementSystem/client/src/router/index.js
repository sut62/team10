import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Contagion from '../components/Contagion.vue'
import Diagnose from '../components/Diagnose.vue'
import Patient from '../components/Patient.vue'
import Register from '../components/Register.vue'
import searchregister from '../components/sprint2/SearchRegister.vue'
import Riskarea from '../components/Riskarea.vue'
import vaccineinformation from '../components/vaccineinformation.vue'
import viewvaccineinformation from '../components/viewvaccineinformation.vue'
import DiagnoseView from '../components/DiagnoseView.vue'
import searchriskarea from '../components/sprint2/SearchRiskarea.vue'
import viewDataVaccine from '../components/sprint2/viewDataVaccine.vue'
import Statistics from '../components/sprint2/Statistics.vue'
import Patientshow from '../components/sprint2/Patientshow.vue'
//path
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
     path: '/statistics',
     component: Statistics
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
    path: '/searchregister',
    component: searchregister
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
    path: '/viewDataVaccine',
    component: viewDataVaccine
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
