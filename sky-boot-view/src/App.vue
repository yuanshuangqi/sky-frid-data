<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    import store from 'storejs'
    import {REFRESH_LOGIN_INFO} from "./store/mutations-type";

    export default {
        name: 'app',
        created() {
            // localstorage中如果没有token，需要重新进行登录认证
            if(this.ObjUtil.isNotEmpty(store.get('loginInfo'))){
                let token = store.get('loginInfo').token
                if(this.ObjUtil.isEmpty(token)){
                    this.$router.push('/login')
                    return
                }
                // 在页面加载时读取localStorage里的状态信息
                if(this.$store.state.userInfo.token === ''){
                    this.$store.commit(REFRESH_LOGIN_INFO, store.get('loginInfo'))
                }
            }
        }
    }
</script>

<style>
</style>
