import {LOGIN_INFO,SET_USER_INFO} from "./mutations-type";
import {login,getUserInfo} from 'api/login'
import store from 'storejs'
const actions = {
    loginHandler(context,{username,password}){
        login({username,password}).then(res => {
            if (res.data.code === 20000){ // 登录认证通过,将token存储在localstorage,vuex中
                // 存储在vuex
                context.commit(LOGIN_INFO,res.data.data)
                // 存储登录信息在localstorage
                store.set('loginInfo',res.data.data)
            }
        })
    },
    getUserInfo(context, username){
        getUserInfo(username).then(res => {
            if(res.data.code === 20000){
                context.commit(SET_USER_INFO,res.data.data.items)
            }
        })
    },
    setUserInfo(context, userInfo){
        context.commit(SET_USER_INFO,userInfo)
    }
}
export default actions
