import {createApp} from 'vue'
import App from './App.vue'
import router from '../router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import VueWordCloud from "vue-wordcloud";

const app = createApp(App)
//全局设置axios
app.component('vue-wordcloud', VueWordCloud)
app.config.globalProperties.$axios = axios;
axios.defaults.baseURL = "http://localhost:8080"
app.use(router)
app.use(ElementPlus)

app.mount('#app')