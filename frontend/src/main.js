import {createApp, ref} from 'vue'
import App from './App.vue'
import router from '../router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import VueWordCloud from "vue-wordcloud";
import * as echarts from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import 'echarts-wordcloud'
const globalVariable = ref([]);
const app = createApp(App)
echarts.use([ CanvasRenderer]);
//全局设置axios
app.component('vue-wordcloud', VueWordCloud)
app.config.globalProperties.$axios = axios;
app.provide('globalVar', globalVariable);
axios.defaults.baseURL = "http://localhost:8080"

app.use(router)
app.use(ElementPlus)

app.mount('#app')