import axios from 'axios'
import store from '@/store/'
import {Message} from 'element-ui'
import router from '../router'
// 设置后台请求路径
let baseUrl = process.env.VUE_APP_BASE_URL

const Axios = axios.create({
    baseURL: baseUrl, // 后台服务地址
    timeout: 30000,	 // 请求超时时间1分钟
    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
})
// 添加请求拦截器
Axios.interceptors.request.use(function (config) {
    config.headers['Authorization'] = store.state.userInfo.token
    return config;
}, function (error) {
    return Promise.reject(error);
});
// 添加相应拦截器
Axios.interceptors.response.use(function (response) {
    if(!response.data.success){
        Message.error(response.data.message)
        if(response.data.code === 21017){
            router.push('/login')
        }
    }
    return response;
}, function (error) {
    return Promise.reject(error);
});
export default Axios

