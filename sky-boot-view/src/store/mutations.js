import {SET_USER_INFO,LOGOUT,LOGIN_INFO,REFRESH_LOGIN_INFO} from "./mutations-type";
import router from '../router'
import store from 'storejs'

const mutations = {
    [REFRESH_LOGIN_INFO](state, loginInfo){
        //用户刷新后，重新设置用户登录信息,token和用户名
        state.userInfo.username = loginInfo.username;
        state.userInfo.token = loginInfo.token;
    },
    [LOGIN_INFO](state, loginInfo) {
        // 设置用户登录信息,token和用户名
        state.userInfo.username = loginInfo.username;
        state.userInfo.token = loginInfo.token;
        // 跳转至Home页面
        router.push('/home')
    },
    [SET_USER_INFO](state, userInfo) {
        // 设置用户信息
        state.userInfo.password = userInfo.password;
        state.userInfo.email = userInfo.email;
        state.userInfo.phone = userInfo.phone;
        state.userInfo.roles = userInfo.securityRoles === undefined ?userInfo.roles:userInfo.securityRoles
        state.userInfo.treeMenus = userInfo.treeMenus
        state.userInfo.allMenus = userInfo.allMenus
    },
    [LOGOUT](state){
        // 清空vuex中的token
        state.userInfo.token = ''
        // 清空local storage中的用户信息
        store.set('userInfo','')
        router.push('/login')
    }
};
export default mutations
