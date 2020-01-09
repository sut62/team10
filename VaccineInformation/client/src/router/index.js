import Vue from 'vue'
import router from 'vue-router';

import vaccineinformation from '../components/vaccineinformation';
import viewvaccineinformation from '../components/viewvaccineinformation';
Vue.use(router);

export default new router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/vaccineinformation',
            component: vaccineinformation
        },
        {
            path: '/viewvaccineinformation',
            component: viewvaccineinformation
        },
      
        
    ]
});