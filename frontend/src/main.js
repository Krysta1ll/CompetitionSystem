import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios';

axios.defaults.baseURL = "http://localhost:8080/"


import Router from "../router.js";

createApp(App).use(Router).mount('#app')

