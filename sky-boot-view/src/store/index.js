import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './actions'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: {
            username: '',
            password: '',
            token: '',
            email: '',
            phone: '',
            roles: [],
            treeMenus:[],
            allMenus:[]
        }
    },
    getters: {
        treeMenus(state) {
            return state.userInfo.treeMenus
        },
        permissions(state){
            return state.userInfo.allMenus.filter(e=>e.menuOrBtn).map(e=>e.menuCode)
        }
    },
    mutations,
    actions,
    modules: {}
})
