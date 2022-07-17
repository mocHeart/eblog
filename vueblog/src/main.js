import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 导入ElementUI(plus)组件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 导入 mavonEditor 组件
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// 导入axios
import axios from 'axios'
import VueAxios from 'vue-axios'
import './axios.js'

const app = createApp(App)

app.use(store)
    .use(router)
    .use(ElementPlus)
    .use(mavonEditor)
    .use(VueAxios, axios)
    .mount('#app')



