import Axios from '../axios'
const API = 'role';
export const roleMenuAssign = (data) => {
    return Axios.request({
        url: `/${API}/menuAssign`,
        method:'put',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
// 获取角色信息
export const getRoleData = (data) => {
    return Axios.request({
        url: `/${API}/list/search`,
        method: 'post',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
};
// 获取角色信息
export const getList = () => {
    return Axios.request({
        url: `/${API}/list`,
        method: 'get'
    })
};
// 新增角色
export const add = (user) => {
    return Axios.request({
        url: `/${API}/add`,
        method:'post',
        data:JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
};
//删除角色
export const del = (data) => {
    return Axios.request({
        url: `/${API}/id/${data}`,
        method:'DELETE'
    })
};
//编辑角色
export const edit = (data) => {
    return Axios.request({
        url: `/${API}/edit`,
        method:'put',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
};
