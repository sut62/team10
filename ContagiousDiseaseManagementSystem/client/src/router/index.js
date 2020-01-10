import Vue from 'vue'
import router from 'vue-router'
import Home from '../views/Home.vue'
import Contagion from '../components/Contagion.vue'
import Diagnose from '../components/Diagnose.vue'
import Patient from '../components/Patient.vue'
import Register from '../components/Register.vue'
import Riskarea from '../components/Riskarea.vue'
import vaccineinformation from '../components/vaccineinformation.vue'
import viewvaccineinformation from '../views/viewvaccineinformation.vue'

Vue.use(router);

export default new router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
 
    path: '/home',
    component: Home
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
  }
]
})



