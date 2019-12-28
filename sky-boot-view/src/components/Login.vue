<template>
    <div class="login_container">
        <div class="login_box">
            <!--logo-->
            <div class="logo_box">
                <img src="../assets/logo.png" alt="">
            </div>
            <!--login form-->
            <el-form label-width="0px" :model="loginForm" :rules="loginFormRules" ref="loginFormRef" class="login_form">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"/>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" show-password prefix-icon="iconfont icon-password"/>
                </el-form-item>
                <el-form class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="info" @click="resetLoginForm">重置</el-button>
                </el-form>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    username: 'kenan',
                    password: '111111'
                },
                loginFormRules: {
                    username: [
                        {required: true, message: '请输入登录名称', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入登录密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            // 重置表单
            resetLoginForm() {
                this.$refs.loginFormRef.resetFields()
            },
            // 登录操作
            login() {
                this.$refs.loginFormRef.validate(valid => {
                    // 校验
                   if (!valid) return
                    // 校验通过，执行登录请求
                   this.$store.dispatch('loginHandler',this.loginForm)
                })
            }
        },
    }
</script>

<style lang="less" scoped>
    .login_container {
        background-color: #409EFF;
        height: 100%;
    }

    .login_box {
        width: 450px;
        height: 300px;
        background-color: #FFF;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

        .logo_box {
            width: 150px;
            height: 150px;
            border: 1px solid #eee;
            border-radius: 50%;
            box-shadow: 0 0 10px #ddd;
            position: absolute;
            left: 50%;
            transform: translate(-50%, -50%);

            img {
                width: 100%;
                height: 100%;
                border-radius: 50%;
                background-color: #eee;
            }
        }
    }

    .btns {
        display: flex;
        justify-content: flex-end;
    }

    .login_form {
        position: absolute;
        bottom: 20px;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    }
</style>
