import Axios from '../axios'
const API = 'quartz';
//修改cron
export const modifyCron = (data) => {
    return Axios.request({
        url: `/${API}/modifyCron`,
        method: 'put',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}
//立即执行
export const executeImmediately = (data) => {
    return Axios.request({
        url: `/${API}/executeImmediately/${data.name}/${data.group}`,
        method: 'put'
    })
}
//恢复全部任务
export const resumeAll = () => {
    return Axios.request({
        url: `/${API}/resumeAll`,
        method: 'put'
    })
}
//暂停全部任务
export const pauseAll = () => {
    return Axios.request({
        url: `/${API}/pauseAll`,
        method: 'put'
    })
}
//暂停任务
export const pause = (data) => {
    return Axios.request({
        url: `/${API}/pause/${data.name}/${data.group}`,
        method: 'put'
    })
}
//恢复任务
export const start = (data) => {
    return Axios.request({
        url: `/${API}/start/${data.name}/${data.group}`,
        method: 'put'
    })
}
// 获取信息
export const getData = (data) => {
    return Axios.request({
        url: `/${API}/list`,
        method: 'get',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
};
// 获取信息
export const getList = () => {
    return Axios.request({
        url: `/${API}/list`,
        method: 'get'
    })
};
// 新增
export const add = (data) => {
    return Axios.request({
        url: `/${API}/add`,
        method:'post',
        data:JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
};
//删除任务
export const del = (data) => {
    return Axios.request({
        url: `/${API}/del/${data.name}/${data.group}`,
        method: 'put'
    })
}
//编辑
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
