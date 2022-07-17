import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 导入ElementUI(plus)组件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 导入axios
import axios from 'axios'
import VueAxios from 'vue-axios'
import './axios.js'

const app = createApp(App)

app.use(store)
    .use(router)
    .use(ElementPlus)
    .use(VueAxios, axios)
    .mount('#app')



