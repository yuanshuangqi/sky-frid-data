import Axios from '../axios'
// 登录
export const login = (data) =>{
    return Axios.request({
        url: '/login',
        method: 'post',
        data: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
// 获取登录用户信息
export const getUserInfo = (data) =>{
    return Axios.request({
        url: '/user/username/' + data,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
