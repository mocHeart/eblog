import axios from 'axios'
import { ElMessage } from 'element-plus'
import store from "./store";
import router from "./router";

axios.defaults.baseURL='http://127.0.0.1:8088/eblog'


// axios 前置拦截
axios.interceptors.request.use(config => {
    // console.log("前置拦截")
    // 可以统一设置请求头
    return config
})

// axios 后置拦截
axios.interceptors.response.use(response => {
    console.log(response)
    if (response.status === 200) {
        return response
    } else {
        // 弹窗异常信息
        ElMessage({
            message: response.data.msg,
            type: 'error',
            duration: 2 * 1000
        })
        // 直接拒绝往下面返回结果信息
        return Promise.reject(response.data.msg)
    }
},error => {
    // console.log('后置拦截：', error)
    if(error.response.data) {
        error.message = error.response.data.msg
    }
    // 根据请求状态觉得是否登录或者提示其他
    if (error.response.status === 401) {
        store.commit('REMOVE_INFO');
        router.push({
            path: '/login'
        });
        error.message = '请重新登录';
    }
    if (error.response.status === 403) {
        error.message = '权限不足，无法访问';
    }
    ElMessage({
        message: error.message,
        type: 'error',
        duration: 3 * 1000
    })
    return Promise.reject(error)
})
