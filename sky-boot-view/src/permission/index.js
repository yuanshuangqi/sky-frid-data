import store from '../store'

const skyPermission = {
    // 判断是否有某个权限
    hasPermission(value){
        return store.getters.permissions.indexOf(value) !== -1
    }
}

export default skyPermission
