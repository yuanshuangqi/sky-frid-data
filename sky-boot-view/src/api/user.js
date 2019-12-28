import Axios from '../axios'

// 获取登录用户信息
export const getUserData = (data) => {
    return Axios.request({
        url: '/user/list',
        method: 'post',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
//用户角色分配
export const roleAssign = (data) => {
    return Axios.request({
        url: '/user/roleAssign',
        method:'put',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
// 新增用户
export const addUser = (user) => {
    return Axios.request({
        url: '/user/add',
        method:'post',
        data:JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
export const del = (data) => {
    return Axios.request({
        url: `/user/id/${data}`,
        method:'DELETE'
    })
}
export const edit = (data) => {
    return Axios.request({
        url: '/user/edit',
        method:'put',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}

